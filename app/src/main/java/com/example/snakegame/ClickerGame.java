package com.example.snakegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ClickerGame extends AppCompatActivity {

    /** earth clicker button */
    ImageButton earthButton;

    /** earth rotation animation */
    Animation rotate;

    /** moon clicker button */
    ImageButton moonButton;

    /** astronaut images */
    ImageView a1;
    ImageView a2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicker_game);

        /** add rotation animation to earth button */
        earthButton = findViewById(R.id.earthClicker);
        rotate = AnimationUtils.loadAnimation(ClickerGame.this, R.anim.rotation);
        earthButton.startAnimation(rotate);

        /** add rotation animation to moon button when clicked */
        moonButton = findViewById(R.id.moonClicker);
        moonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moonButton.animate().rotation(moonButton.getRotation() + 360).start();
            }
        });

        /** add rotation to astronauts */
        a1 = findViewById(R.id.astronaut1);
        a2 = findViewById(R.id.astronaut2);
        a1.startAnimation(rotate);
        a2.startAnimation(rotate);
    }
}
