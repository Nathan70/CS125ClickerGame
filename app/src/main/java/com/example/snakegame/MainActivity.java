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
    /** big state */
    private boolean big;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch bigSwitch = findViewById(R.id.bigSwitch);
        big = bigSwitch.isChecked();

        Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game = new Game(big);
                openGameActivity();
            }
        });
    }

    public void openGameActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
