package com.example.tictactoe.activities;


import static com.example.tictactoe.model.AppConstants.MATRIX_SIZE;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.tictactoe.R;
import com.example.tictactoe.adapter.BoardGameAdapter;
import com.example.tictactoe.model.GameSquare;
import com.example.tictactoe.model.GameSquareList;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private int mSize = MATRIX_SIZE;
    private GridView gameBoard;
    private ArrayList<Integer> boardGameTab = new ArrayList<Integer>();

    private ArrayList<int[]> winningCombination = new ArrayList<int[]>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        createGameBoard();

        createBoardGameTab();
        defineWinningCombination();
    }

    private void createGameBoard() {
        gameBoard = findViewById(R.id.gvGameBoard);
        BoardGameAdapter boardGameAdapter = new BoardGameAdapter(
                GameActivity.this, new GameSquareList().getAllSquares());
        gameBoard.setAdapter(boardGameAdapter);
//        gameBoard.setOnClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                GameSquare square = (GameSquare) parent.getItemAtPosition(position);
//            }
//        })
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
        winningCombination.add(firstDiagCombination);

        int[] reverseDiagCombination = new int[mSize];
        reverseDiagCombination[0] = mSize-1;
        for(int i = 1; i < mSize; i++){
            reverseDiagCombination[i] = reverseDiagCombination[i-1]+mSize-1;
        }
        winningCombination.add(reverseDiagCombination);

        // vertical combination
        for(int v = 0; v<mSize; v++){
            int[] vertCombination = new int[mSize];
            vertCombination[0] = v;
            for(int i = 1; i<mSize; i++){
                vertCombination[i] = v+(mSize*i);
            }
            winningCombination.add(vertCombination);
        }

        // horizontal combination
        for(int h = 0; h<mSize; h++){
            int[] horiCombination = new int[mSize];
            horiCombination[0] = h*mSize;
            for(int i = 1; i<mSize; i++){
                horiCombination[i] = (h*mSize)+i;
            }
            winningCombination.add(horiCombination);
        }
    }
}