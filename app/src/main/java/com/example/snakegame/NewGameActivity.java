package com.example.snakegame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.snakegame.logic.Direction;
import com.example.snakegame.logic.GameStateID;
import com.example.snakegame.logic.InitialPositions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static android.view.View.VISIBLE;

public class NewGameActivity extends AppCompatActivity {

    /**
     * something
     */
    TextView scoreText;

    //MARKER


    /**
     * game state
     */
    private int gameState;

    /**
     * score
     */
    private int score;

    /**
     * range
     */
    private int range;

    /**
     * path
     */
    private List<int[]> path = new ArrayList<>();

    /**
     * position of target
     */
    private int[] targetPosition;

    /**
     * timer
     */
    private Timer timer;

    /**
     * direction snake is currently facing
     */
    private int facing = Direction.right;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        // hi
        if (MainActivity.getBig()) {
            TextView bigText = findViewById(R.id.mode);
            bigText.setVisibility(VISIBLE);
        }

        scoreText = findViewById(R.id.updateScore);
        scoreText.setText("0");

        ImageButton pauseButton = findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePause();
            }
        });

        ImageButton quitButton = findViewById(R.id.endButton);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quitCheck();
            }
        });

        Button upButton = findViewById(R.id.upButton);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turn(Direction.up);
            }
        });
        Button rightButton = findViewById(R.id.rightButton);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turn(Direction.right);
                eat();
            }
        });
        Button downButton = findViewById(R.id.downButton);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turn(Direction.down);
            }
        });
        Button leftButton = findViewById(R.id.leftButton);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turn(Direction.left);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("move left");
                builder.create().show();
            }
        });
        gameState = GameStateID.PAUSED;
        if (MainActivity.getBig()) {
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

    /**
     *
     */
    private Context getActivity() {
        return this;
    }

    /**
     * popup dialogue asking if you are sure you want to quit
     */
    private void quitCheck() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Quit?");
        builder.setNegativeButton("No", null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                endGame();
            }
        });
        builder.create().show();
    }

    /**
     * what happens when you die or after you confirm you want to quit
     */
    private DialogInterface.OnClickListener endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Game Over. Score: " + score);
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mainMenu();
            }
        });
        builder.create().show();
        return null;
    }

    /**
     * return to Main Menu where you can set size and create a new game
     */
    private DialogInterface.OnClickListener mainMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return null;
    }


    //MARKER


    /**
     * hi
     */
    class runMove extends TimerTask {

        @Override
        public void run() {
            move(facing);
        }
    }

    /**
     * moving
     */
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

    /**
     * eat a target
     */
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
        scoreText.setText(String.valueOf(score));
    }

    /**
     * you lose game, game over
     */
    private void gameOver() {
        gameState = GameStateID.ENDED;
        //timer.cancel();
        endGame();
        //You lose message, brings up score
    }

    /**
     * turn the snake, direction facing
     */
    public void turn(int toFace) {
        if (gameState == GameStateID.RUNNING) {
            facing = toFace;
        }
        //visual indicator of direction facing change
    }

    /**
     * toggle gameState between RUNNING and PAUSED
     */
    public void togglePause() {
        if (gameState == GameStateID.PAUSED) {
            gameState = GameStateID.RUNNING;
            timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    move(facing);
                }
            };
            timer.scheduleAtFixedRate(task, InitialPositions.moveDelay, InitialPositions.moveDelay);
        } else if (gameState == GameStateID.RUNNING) {
            gameState = GameStateID.PAUSED;
            timer.purge();
        }
    }
}
