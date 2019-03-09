package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


import static cs361.battleships.models.AtackStatus.*;

public class Game {

    @JsonProperty private Board playersBoard = new Board();
    @JsonProperty private Board opponentsBoard = new Board();


    /*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
    public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {
        boolean successful = playersBoard.placeShip(ship, x, y, isVertical);
        if (!successful)
            return false;

        boolean opponentPlacedSuccessfully;
        do {
            // AI places random ships, so it might try and place overlapping ships
            // let it try until it gets it right
            opponentPlacedSuccessfully = opponentsBoard.placeShip(ship, randRow(), randCol(), randVertical());
        } while (!opponentPlacedSuccessfully);

        return true;
    }

    /*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
    public boolean attack(int x, char  y) {
        Result playerAttack = opponentsBoard.attack(x, y);
        if (playerAttack.getResult() == INVALID) {
            return false;
        }

        Result opponentAttackResult;
        do {
            // AI does random attacks, so it might attack the same spot twice
            // let it try until it gets it right
            opponentAttackResult = playersBoard.attack(randRow(), randCol());
        } while(opponentAttackResult.getResult() == INVALID);

        return true;
    }

    public boolean sonar(int x, char y)
    {
        //this could probably look better with some loops or something
        //but i think this will do fine for now, if it was bigger I would use some loops
        opponentsBoard.sonar(x, y);
        opponentsBoard.sonar(x+1, y);
        opponentsBoard.sonar(x+2, y);
        opponentsBoard.sonar(x-1, y);
        opponentsBoard.sonar(x-2, y);
        opponentsBoard.sonar(x, (char)(y+1));
        opponentsBoard.sonar(x, (char)(y+2));
        opponentsBoard.sonar(x, (char)(y-1));
        opponentsBoard.sonar(x, (char)(y-2));
        opponentsBoard.sonar(x+1, (char)(y+1));
        opponentsBoard.sonar(x+1, (char)(y-1));
        opponentsBoard.sonar(x-1, (char)(y+1));
        opponentsBoard.sonar(x-1, (char)(y-1));
        return true; //always return true because we don't care if the player uses it out of bounds
                     //or on an already clicked square
    }

    public boolean laser(int x, char y)
    {
        opponentsBoard.laser(x, y);
        opponentsBoard.laser(x+1, y);
        opponentsBoard.laser(x-1, y);
        opponentsBoard.laser(x, (char)(y+1));
        opponentsBoard.laser(x, (char)(y-1));
        opponentsBoard.laser(x+1, (char)(y+1));
        opponentsBoard.laser(x+1, (char)(y-1));
        opponentsBoard.laser(x-1, (char)(y+1));
        opponentsBoard.laser(x-1, (char)(y-1));
        return true;
    }


    private char randCol() {
        int random = new Random().nextInt(10);
        return (char) ('A' + random);
    }

    private int randRow() {
        return  new Random().nextInt(10) + 1;
    }

    private boolean randVertical() {
        return new Random().nextBoolean();
    }


    /*public void GameCounter(){

        System.out.print("Enter type of counter: ");
        Scanner scanner = new Scanner(System. in);
        String CounterName = scanner. nextLine();
        System.out.println("Name of counter is : \n"+CounterName);

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter max of the counter: ");
        int max = reader.nextInt(); // Scans the next token of the input as an int once finished

        Counter RoundCounter = new Counter(CounterName,max);


        // printing
        RoundCounter.toString();
        for (int i = 0; i < max; i++) {

            RoundCounter.increment();
            System.out.println("Current value: " + RoundCounter.value());

        }
    }*/

}