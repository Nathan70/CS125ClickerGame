package com.example.clickergame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class ClickerGame extends AppCompatActivity {

    /** something */
    private TextView scoreText;

    /** score*/
    private int score;

    /** something */
    private int multiplier;

    /** something */
    private TextView multiplierText;

    /** earth clicker button */
    private int moon;

    /** something */
    private TextView moonText;

    /** moon clicker button */
    private ImageButton moonButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicker_game);

        multiplier = 1;
        score = 0;
        moon = 1;
        scoreText = findViewById(R.id.updateClickerScore);
        multiplierText = findViewById(R.id.updateMultiplier);
        moonText = findViewById(R.id.updateMoon);

        ImageButton quitButton = findViewById(R.id.quitClicker);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quitCheck();
            }
        });

        ImageButton earthButton = findViewById(R.id.earthClicker);
        earthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click();
            }
        });

        Button multiplierButton = findViewById(R.id.upgradeButton);
        multiplierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiplier();
            }
        });

        Button addMoonButton = findViewById(R.id.addMoon);
        addMoonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMoon();
            }
        });

        // add rotation animation to earth button
        Animation rotate = AnimationUtils.loadAnimation(ClickerGame.this, R.anim.rotation);

        // add rotation animation to moon button when clicked
        moonButton = findViewById(R.id.moonClicker);
        moonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moonButton.animate().rotation(moonButton.getRotation() + 360).start();
            }
        });

        // add rotation to astronauts

        ImageView a1 = findViewById(R.id.astronaut1);
        ImageView a2 = findViewById(R.id.astronaut2);
        a1.startAnimation(rotate);
        a2.startAnimation(rotate);
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
        scoreText.setText(String.valueOf(score));
    }

    private void addMoon() {
        if (score < (100 * moon)) {
            return;
        }
        score -= (100 * moon);
        moon++;
        moonText.setText(String.valueOf(moon));
        scoreText.setText(String.valueOf(score));
    }
}
