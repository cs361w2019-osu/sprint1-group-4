var isSetup = true;
var placedShips = 0;
var game;
var shipType;
var vertical;
var isSonar = false;
var sonarCount = 2;

function makeGrid(table, isPlayer)
{
    for (i=0; i<10; i++)
    {
        let row = document.createElement('tr');
        for (j=0; j<10; j++)
        {
            let column = document.createElement('td');
            column.addEventListener("click", cellClick);
            row.appendChild(column);
        }
        table.appendChild(row);
    }
}

function markHits(board, elementId, surrenderText) {
    //go through all our sonar elements in the sonar list
    board.sonars.forEach((sonar) => {
        let className;
        if(sonar.result === "MISS") //if we cant find a ship just set the color to gray
        {
            className = "empty";
        }
        else if(sonar.result === "HIT") //if we find a ship set the color to black
        {
            className = "occupied";
        }
        //actually add it to the grid so the user can see it
        document.getElementById(elementId).rows[sonar.location.row-1].cells[sonar.location.column.charCodeAt(0) - 'A'.charCodeAt(0)].classList.add(className);
    });

    board.attacks.forEach((attack) => {
        let className;
        if (attack.result === "MISS")
            className = "miss";
        else if (attack.result === "HIT")
            className = "hit";
        else if (attack.result === "SUNK")
        {
            //if an attack results in a ship sinking then set the sonar button to display so that the user can see it
            className = "hit"
            if(elementId === "opponent")
            {
                document.getElementById("sonar_button").style.display = 'block';
            }
        }
        else if (attack.result === "SURRENDER")
            alert(surrenderText);
        document.getElementById(elementId).rows[attack.location.row-1].cells[attack.location.column.charCodeAt(0) - 'A'.charCodeAt(0)].classList.add(className);
    });
}

function redrawGrid() {
    Array.from(document.getElementById("opponent").childNodes).forEach((row) => row.remove());
    Array.from(document.getElementById("player").childNodes).forEach((row) => row.remove());
    makeGrid(document.getElementById("opponent"), false);
    makeGrid(document.getElementById("player"), true);
    if (game === undefined) {
        return;
    }

    game.playersBoard.ships.forEach((ship) => ship.occupiedSquares.forEach((square) => {
        document.getElementById("player").rows[square.row-1].cells[square.column.charCodeAt(0) - 'A'.charCodeAt(0)].classList.add("occupied");
    }));
    markHits(game.opponentsBoard, "opponent", "You won the game");
    markHits(game.playersBoard, "player", "You lost the game");
}

var oldListener;
function registerCellListener(f) {
    let el = document.getElementById("player");
    for (i=0; i<10; i++) {
        for (j=0; j<10; j++) {
            let cell = el.rows[i].cells[j];
            cell.removeEventListener("mouseover", oldListener);
            cell.removeEventListener("mouseout", oldListener);
            cell.addEventListener("mouseover", f);
            cell.addEventListener("mouseout", f);
        }
    }
    oldListener = f;
}

function cellClick() {
    let row = this.parentNode.rowIndex + 1;
    let col = String.fromCharCode(this.cellIndex + 65);
    if (isSetup)
    {
        sendXhr("POST", "/place", {game: game, shipType: shipType, x: row, y: col, isVertical: vertical}, function(data) {
            game = data;
            redrawGrid();
            placedShips++;
        });
    }
    else if(isSonar) //do sonar logic
    {
        //send the site data to the sonar function on the backend
        sendXhr("POST", "/sonar", {game: game, x: row, y: col}, function(data) {
            game = data;
            redrawGrid();
            isSonar = false; //set sonar flag to false so that we don't enter it again when the user clicks
            sonarCount--; //decrement sonar count so that we can keep track of how many times it has been used
        });
    }
    else //do attack logic
    {
        sendXhr("POST", "/attack", {game: game, x: row, y:col}, function(data) {
            game = data;
            redrawGrid();
        });
    }

}

function sendXhr(method, url, data, handler) {
    var req = new XMLHttpRequest();
    req.addEventListener("load", function(event) {
        if (req.status != 200) {
            alert("Cannot complete the action");
            return;
        }
        handler(JSON.parse(req.responseText));
    });
    req.open(method, url);
    req.setRequestHeader("Content-Type", "application/json");
    req.send(JSON.stringify(data));
}

function place(size) {
    return function() {
        let row = this.parentNode.rowIndex;
        let col = this.cellIndex;
        vertical = document.getElementById("is_vertical").checked;
        let table = document.getElementById("player");
        for (let i=0; i<size; i++)
        {
            let cell;
            if(vertical)
            {
                let tableRow = table.rows[row+i];
                if (tableRow === undefined)
                {
                    // ship is over the edge; let the back end deal with it
                    break;
                }
                cell = tableRow.cells[col];
            }
            else
            {
                cell = table.rows[row].cells[col+i];
            }
            if (cell === undefined)
            {
                // ship is over the edge; let the back end deal with it
                break;
            }
            cell.classList.toggle("placed");
        }
    }
}

function initGame() {
    makeGrid(document.getElementById("opponent"), false);
    makeGrid(document.getElementById("player"), true);

    document.getElementById("sonar_button").style.display = 'none';

    document.getElementById("place_minesweeper").addEventListener("click", function(e) {
       shipType = "MINESWEEPER";
       registerCellListener(place(2));
       document.getElementById('rm1').remove();
       this.remove();
    });
    document.getElementById("place_destroyer").addEventListener("click", function(e) {
       shipType = "DESTROYER";
       registerCellListener(place(3));
       document.getElementById('rm2').remove();
       this.remove();
    });
    document.getElementById("place_battleship").addEventListener("click", function(e) {
       shipType = "BATTLESHIP";
       registerCellListener(place(4));
       document.getElementById('rm3').remove();
       this.remove();
    });
  
    //maybe have a counter for how many sonars are left?
    document.getElementById("sonar_button").addEventListener("click", function(e)
    {
        if(sonarCount > 0) //if the users uses both sonars don't let them use it again
        {
            isSonar = true;
        }
        else
        {
            //tell the user they can use anymore sonars once they have used two
            alert("You already used both of your sonars, you cannot use anymore");
        }
    });

    //initially hide the reset button
    document.getElementById("reset_button").style.display = 'none';

    document.getElementById("start_button").addEventListener("click", function(e) {
        if(placedShips < 3)
        {
            alert("Please place all 3 ships before starting the game");
        }
        else
        {
            isSetup = false;
            registerCellListener((e) => {});
            //reveal reset button
            document.getElementById("reset_button").style.display = 'block';
            document.getElementById("start_button").style.display = 'none';
        }
    });

    document.getElementById("reset_button").addEventListener("click", function(e) {
        if(confirm("Do you really want to reset?"))
        {
            window.location.reload(); //reloads the web-page
        }
    });

    sendXhr("GET", "/game", {}, function(data) {
        game = data;
    });
};
