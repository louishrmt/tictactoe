package com.example.tictactoe.activities;


import static com.example.tictactoe.AppConstants.MATRIX_SIZE;

import android.os.Bundle;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;
import com.example.tictactoe.R;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private int matrixSize = MATRIX_SIZE;
    private GridLayout gameBoard;

    private ArrayList<int[]> winningCombination = new ArrayList<int[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameBoard = findViewById(R.id.glGameBoard);

        gameBoard.setColumnCount(matrixSize);
        gameBoard.setRowCount(matrixSize);

        defineWinningCombination();
    }

    private void defineWinningCombination() {
        int mSize = MATRIX_SIZE;

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