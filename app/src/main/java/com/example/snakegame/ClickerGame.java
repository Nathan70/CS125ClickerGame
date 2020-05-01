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

public class ClickerGame extends AppCompatActivity {

    /**
     * something
     */
    TextView scoreText;

    /**
     * score
     */
    private int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicker_game);

        scoreText = findViewById(R.id.updateScore);
        scoreText.setText("0");

        ImageButton quitButton = findViewById(R.id.endButton);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quitCheck();
            }
        });

        Button rightButton = findViewById(R.id.rightButton);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click();
            }
        });
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

    /**
     *
     */
    private void click() {
        score++;
        scoreText.setText(String.valueOf(score));
    }
}
