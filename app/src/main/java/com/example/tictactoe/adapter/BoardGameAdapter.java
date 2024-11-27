package com.example.tictactoe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tictactoe.R;
import com.example.tictactoe.model.GameSquare;
import com.example.tictactoe.model.GameSquareList;

import java.util.ArrayList;

public class BoardGameAdapter extends ArrayAdapter<GameSquareList> {

    public BoardGameAdapter(@NonNull Context context, ArrayList<GameSquare> allSquares) {
        super(context, 0);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HolderView holderView;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.game_square_item, null);
            holderView = new HolderView(convertView);
            convertView.setTag(holderView);
        }
        else{
            holderView = (HolderView)convertView.getTag();
        }

        GameSquareList square = getItem(position);

        return super.getView(position, convertView, parent);
    }

    private static class HolderView{
        private final ImageView mark;

        private HolderView(View view) {
            this.mark = view.findViewById(R.id.ivSquareMark);
        }
    }
}
