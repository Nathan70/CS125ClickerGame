package com.example.snakegame.logic;

import androidx.appcompat.app.AlertDialog;

import com.example.snakegame.NewGameActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    /** game state */
    private int gameState;

    /** score */
    private int score;

    /** range */
    private int range;

    /** path */
    private List<int[]> path = new ArrayList<>();

    /** position of target*/
    private int[] targetPosition;

    /** timer */
    private Timer timer;

    /** direction snake is currently facing*/
    private int facing = Direction.right;


    public Game(boolean big) {
        gameState = GameStateID.PAUSED;
        if (big) {
            range = InitialPositions.bigRange;
            path.add(InitialPositions.bigFirst);
            path.add(InitialPositions.bigSecond);
            path.add(InitialPositions.bigThird);
            targetPosition = InitialPositions.bigGoal;
            //render positions of snake, grid, etc on ui
        } else {
            range = InitialPositions.smallRange;
            path.add(InitialPositions.smallFirst);
            path.add(InitialPositions.smallSecond);
            path.add(InitialPositions.smallThird);
            targetPosition = InitialPositions.smallGoal;
            //render positions of snake, grid, etc on ui
        }
    }

    /** get score*/
    public int getScore() {
        return score;
    }

    /** hi */

    class runMove extends TimerTask {

        @Override
        public void run() {
            move(facing);
        }
    }
    /** moving */
    private void move(int direction) {
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
        Random random = new Random();
        // Randomize next target Position
        int x = random.nextInt(range);
        int y = random.nextInt(range);
        int[] point = {x, y};
        for (int i = 0; i < path.size(); i++) {
            if (point[0] == path.get(i)[0] && point[1] == path.get(i)[1]) {
                eat();
            }
        }
        targetPosition = point;
        // visual position of target change
        score++;
    }

    /** you lose game, game over*/
    private void gameOver() {
        gameState = GameStateID.ENDED;
        timer.cancel();
        //You lose message, brings up score
    }

    /** turn the snake, direction facing */
    public void turn(int toFace) {
        facing = toFace;
    }

    /** toggle gameState between RUNNING and PAUSED*/
    public void togglePause() {
        if (gameState == GameStateID.PAUSED) {
            gameState = GameStateID.RUNNING;
            timer.schedule(new runMove(), 1000, 250);
        } else if (gameState == GameStateID.RUNNING) {
            gameState = GameStateID.PAUSED;
            timer.purge();
        }
    }
}
