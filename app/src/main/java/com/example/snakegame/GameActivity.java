package com.example.snakegame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.snakegame.logic.Game;
import com.example.snakegame.logic.GameStateID;

public class GameActivity extends AppCompatActivity {

    /** game state. */
    private int gameState = GameStateID.PAUSED;

    /** game */
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        /** sets default score of 0 and updates based on game */
        Game temp = new Game(true);
        String gameScore = String.valueOf(game.getScore());
        TextView updateScore = findViewById(R.id.currentScore);
        updateScore.setText(gameScore);
    }
}
