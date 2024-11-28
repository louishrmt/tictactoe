package com.example.tictactoe.activities;


import static com.example.tictactoe.model.AppConstants.MATRIX_SIZE;
import static com.example.tictactoe.model.AppConstants.ORANGE;
import static com.example.tictactoe.model.AppConstants.TEAL;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.tictactoe.R;
import com.example.tictactoe.adapter.BoardGameAdapter;
import com.example.tictactoe.databinding.ActivityGameBinding;
import com.example.tictactoe.model.GameSquareList;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    ActivityGameBinding binding;
    private int mSize = MATRIX_SIZE;
    private GridView gameBoard;
    private TextView tvCurrentPlaying;
    private ArrayList<int[]> winningCombinations = new ArrayList<int[]>();
    private ArrayList<Integer> boardGameTab = new ArrayList<Integer>();
    private ArrayList<Integer> playerSquares = new ArrayList<Integer>();
    private ArrayList<Integer> botSquares = new ArrayList<Integer>();
    private int count = 0;
    private int playerMark;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createGameBoard();

        createBoardGameTab();
        defineWinningCombination();

        pickAColor();
    }


    private void createGameBoard() {

        gameBoard = findViewById(R.id.gvGameBoard);

        BoardGameAdapter boardGameAdapter = new BoardGameAdapter(GameActivity.this, new GameSquareList().getAllSquares());
        gameBoard.setAdapter(boardGameAdapter);

        binding.gvGameBoard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(boardGameTab.contains(position)) {
                    count++;
                    playerSquares.add(position);
                    boardGameTab.remove(Integer.valueOf(position));

                    ImageView ivSquare = view.findViewById(R.id.ivSquare);
                    if (playerMark == ORANGE)
                        ivSquare.setBackgroundResource(R.drawable.square_orange);
                    else
                        ivSquare.setBackgroundResource(R.drawable.square_teal);


                    if (isItAWin(playerSquares)) {
                        Dialog victoryDialog = new Dialog(GameActivity.this);
                        victoryDialog.setContentView(R.layout.dialog_victory);
                        victoryDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        victoryDialog.setCancelable(false);

                        Button btnRestart = victoryDialog.findViewById(R.id.btnReplayVictory);
                        btnRestart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                restartGame();
                            }
                        });

                        victoryDialog.show();
                    } else
                        botPlays();
                }
                else
                    Toast.makeText(GameActivity.this, R.string.you_can_t_pick_this_square, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createBoardGameTab() {
        int totalCase = mSize*mSize;
        for(int i = 0; i < totalCase; i++){
            boardGameTab.add(i);
        }
    }

    private void defineWinningCombination() {

        // diagonal combination
        int[] firstDiagCombination = new int[mSize];
        firstDiagCombination[0] = 0;
        for(int i = 1; i < mSize; i++){
            firstDiagCombination[i] = (mSize+1)*i;
        }
        winningCombinations.add(firstDiagCombination);

        int[] reverseDiagCombination = new int[mSize];
        reverseDiagCombination[0] = mSize-1;
        for(int i = 1; i < mSize; i++){
            reverseDiagCombination[i] = reverseDiagCombination[i-1]+mSize-1;
        }
        winningCombinations.add(reverseDiagCombination);

        // vertical combination
        for(int v = 0; v<mSize; v++){
            int[] vertCombination = new int[mSize];
            vertCombination[0] = v;
            for(int i = 1; i<mSize; i++){
                vertCombination[i] = v+(mSize*i);
            }
            winningCombinations.add(vertCombination);
        }

        // horizontal combination
        for(int h = 0; h<mSize; h++){
            int[] horiCombination = new int[mSize];
            horiCombination[0] = h*mSize;
            for(int i = 1; i<mSize; i++){
                horiCombination[i] = (h*mSize)+i;
            }
            winningCombinations.add(horiCombination);
        }
    }

    private boolean isItAWin(ArrayList<Integer>squareList){
        // safe check to not check every combinations if there's no win possibility
        if(count >= (mSize*2)-1){
            for(int[] combination : winningCombinations){
                int nCombination = 0;
                for(int squarePos : combination){
                    if(squareList.contains(squarePos)){
                        // contains a possible winning combination
                        nCombination++;
                    }
                }
                if(nCombination == mSize)
                    return true;
            }
        }
        return false;
    }

    private void pickAColor() {
        Dialog colorChoiceDialog = new Dialog(GameActivity.this);
        colorChoiceDialog.setContentView(R.layout.dialog_mark_choice);
        colorChoiceDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        colorChoiceDialog.setCancelable(true);

        CardView orangeCard = colorChoiceDialog.findViewById(R.id.cvOrangeMark);
        CardView tealCard = colorChoiceDialog.findViewById(R.id.cvTealMark);

        orangeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerMark = ORANGE;
                colorChoiceDialog.dismiss();
                startGame();
            }
        });

        tealCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerMark = TEAL;
                colorChoiceDialog.dismiss();
                startGame();
            }
        });
        colorChoiceDialog.show();
    }

    private void startGame() {
        tvCurrentPlaying = findViewById(R.id.tvPlaying);

        if(playerMark == 1){
            // means player start
            tvCurrentPlaying.setText(getString(R.string.your_turn));
        }
        else{
            tvCurrentPlaying.setText(getString(R.string.bot_playing));
            botPlays();
        }
    }

    private void botPlays() {
        // disable user actions to let the bot plays
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        // bot picks a random available number between 0 and 8 in a delay of 2 seconds
        tvCurrentPlaying.setText(getString(R.string.bot_playing));

        count++;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // bot pick a random number
                Random randomizer = new Random();
                int randomNumber = randomizer.ints(0, boardGameTab.size()-1).findFirst().getAsInt();
                int pickedSquare = boardGameTab.get(randomNumber);
                botSquares.add(pickedSquare);

                ImageView ivSquare = gameBoard.getChildAt(pickedSquare).findViewById(R.id.ivSquare);

                boardGameTab.remove(Integer.valueOf(pickedSquare));

                if(playerMark == TEAL)
                    ivSquare.setBackgroundResource(R.drawable.square_orange);
                else
                    ivSquare.setBackgroundResource(R.drawable.square_teal);

                // Giving user posibility to play
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                if(isItAWin(botSquares)){
                    Dialog defeatDialog = new Dialog(GameActivity.this);
                    defeatDialog.setContentView(R.layout.dialog_defeat);
                    defeatDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    defeatDialog.setCancelable(false);

                    Button btnRestart = defeatDialog.findViewById(R.id.btnReplayDefeat);
                    btnRestart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            restartGame();
                        }
                    });

                    defeatDialog.show();
                }

                tvCurrentPlaying.setText(getString(R.string.your_turn));
            }
        }, 2000);
    }

    private void restartGame(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}