package com.example.snakegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.snakegame.logic.Game;

public class MainActivity extends AppCompatActivity {

    /** game */
    private Game game;

    /** button to play the game. */
    private Button playButton;

    /** switch for big **/
    private Switch bigSwitch;

    /** big state */
    private boolean big;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** implement switch **/
        bigSwitch = findViewById(R.id.bigSwitch);
        if (bigSwitch.isChecked()) {
            big = true;
        } else {
            big = false;
        }

        /** implement play button */
        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game = new Game(big);
            }
        });
    }
}
