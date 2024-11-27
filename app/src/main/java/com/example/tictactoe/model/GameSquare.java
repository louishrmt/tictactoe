package com.example.tictactoe.model;

import static com.example.tictactoe.model.AppConstants.MATRIX_SIZE;

import java.util.ArrayList;

public class GameSquare {
    private int position;

    public GameSquare(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
