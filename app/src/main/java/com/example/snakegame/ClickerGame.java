package com.example.snakegame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class ClickerGame extends AppCompatActivity {

    /**
     * something
     */
    private TextView scoreText;

    /**
     * score
     */
    private int score;

    /**
     *
     */
    private int multiplier;

    /**
     *
     */
    private int itemsBought;

    /**
     * something
     */
    private TextView multiplierText;

    /**
     * something
     */
    private TextView itemsText;

    /**
     *
     */
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicker_game);

        multiplier = 1;
        score = 0;
        itemsBought = 0;
        scoreText = findViewById(R.id.updateScore);
        scoreText.setText("0");

        ImageButton quitButton = findViewById(R.id.endButton);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quitCheck();
            }
        });

        Button cookie = findViewById(R.id.rightButton);
        cookie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click();
            }
        });

        Button multiplierButton = findViewById(R.id.leftButton);
        multiplierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiplier();
            }
        });

        Button buyButton = findViewById(R.id.upButton);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy();
            }
        });

        timer = new Timer();
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

    public class Task extends TimerTask {
        public void run() {
            score++;
        }
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

    /**
     *
     */
    private void click() {
        score = score + (multiplier);
        scoreText.setText(String.valueOf(score));
    }

    private void multiplier() {
        if (score < (100 * multiplier)) {
            return;
        }
        score -= (100 * multiplier);
        multiplier++;
        multiplierText.setText(String.valueOf(multiplier));
    }

    private void buy() {
        if (score < (100 * itemsBought)) {
            return;
        }
        score -= 100 * itemsBought;
        itemsBought++;
        timer.purge();
        timer.scheduleAtFixedRate(new Task(), 1000 / itemsBought, 1000 / itemsBought);
    }
}
