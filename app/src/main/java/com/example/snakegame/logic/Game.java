package com.example.snakegame.logic;

import java.util.ArrayList;
import java.util.List;

public class Game {

    /** score */
    private int score;

    /** range */
    private int range;

    /** path */
    private List<int[]> path = new ArrayList<>();

    /** position of target*/
    private int[] targetPosition;


    public Game(boolean big) {
        if (big) {
            range = InitialPositions.bigRange;
            path.add(InitialPositions.bigFirst);
            path.add(InitialPositions.bigSecond);
            path.add(InitialPositions.bigThird);
            targetPosition = InitialPositions.bigGoal;
        } else {
            range = InitialPositions.smallRange;
            path.add(InitialPositions.smallFirst);
            path.add(InitialPositions.smallSecond);
            path.add(InitialPositions.smallThird);
            targetPosition = InitialPositions.smallGoal;
        }
    }

    /** moving */
    public void move(int direction) {
        int[] claim = path.get(path.size() - 1);
        if (direction == Direction.up) {
            claim[1]++;
        } else if (direction == Direction.right) {
            claim[0]++;
        } else if (direction == Direction.down) {
            claim[1]--;
        } else {
            claim[0]--;
        }
        path.add(claim);
        if (claim == targetPosition) {
            eat();
            return;
        }
        if (claim[0] < 0 || claim[1] < 0 || claim[0] >= range || claim[1] >= range) {
            gameOver();
        }
        path.remove(0);
        for (int i = 0; i < path.size(); i++) {
            if (claim[0] == path.get(i)[0] && claim[1] == path.get(i)[1]) {
                gameOver();
            }
        }
    }

    /** eat a target*/
    private void eat() {
        score++;
        // Randomize next target Position
    }

    /** you lose game, game over*/
    private void gameOver() {
        //You lose message, brings up score
    }
}
