package com.example.snakegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    /** big state */
    private static boolean big;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.playButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Switch bigSwitch = findViewById(R.id.bigSwitch);
                big = bigSwitch.isChecked();
                openGameActivity();
            }
        });
    }

    public void openGameActivity() {
        startActivity(new Intent(this, ClickerGame.class));
    }

    public static boolean getBig() {
        return big;
    }
}
