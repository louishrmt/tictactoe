package com.example.tictactoe.activities;


import static com.example.tictactoe.model.AppConstants.MATRIX_SIZE;
import static com.example.tictactoe.model.AppConstants.ORANGE;
import static com.example.tictactoe.model.AppConstants.TEAL;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.tictactoe.R;
import com.example.tictactoe.adapter.BoardGameAdapter;
import com.example.tictactoe.databinding.ActivityGameBinding;
import com.example.tictactoe.model.GameSquareList;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    ActivityGameBinding binding;
    private int mSize = MATRIX_SIZE;
    private GridView gameBoard;
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

                Toast.makeText(getApplicationContext(), "Clicked on "+position, Toast.LENGTH_SHORT).show();

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
            }
        });

        tealCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerMark = TEAL;
                colorChoiceDialog.dismiss();
            }
        });
        colorChoiceDialog.show();
    }
}