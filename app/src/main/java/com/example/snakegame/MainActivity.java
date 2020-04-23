package com.example.snakegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.snakegame.logic.Game;

public class MainActivity extends AppCompatActivity {

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
            Game game = new Game(big);
        }

        /** implement play button */
        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGameActivity();
            }
        });


    }

    /** switches screen to game activity */
    public void openGameActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
