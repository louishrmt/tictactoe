package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tictactoe.activities.GameActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartGame = findViewById(R.id.btnStartGame);

        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });


    }

    private void startGame() {
        // TODO Choix du pion

        // TODO direction activite jeu
        Intent gameActivity = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(gameActivity);
    }

}