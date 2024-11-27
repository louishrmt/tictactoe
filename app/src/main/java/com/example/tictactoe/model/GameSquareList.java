package com.example.tictactoe.model;

import static com.example.tictactoe.model.AppConstants.MATRIX_SIZE;

import java.util.ArrayList;

public class GameSquareList {

    public ArrayList<GameSquare> getAllSquares() {
        ArrayList<GameSquare>allSquares = new ArrayList<>();
        int totalSquares = MATRIX_SIZE*MATRIX_SIZE;
        for(int i = 0; i<totalSquares; i++){
            allSquares.add(new GameSquare(i));
        }
        return allSquares;
    }


}
