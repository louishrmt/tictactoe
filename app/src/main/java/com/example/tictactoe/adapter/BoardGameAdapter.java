package com.example.tictactoe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tictactoe.R;
import com.example.tictactoe.model.GameSquare;
import com.example.tictactoe.model.GameSquareList;

import java.util.ArrayList;

public class BoardGameAdapter extends BaseAdapter {

    Context context;
    ArrayList<GameSquare> gameSquareList;

    LayoutInflater inflater;

    public BoardGameAdapter(Context context, ArrayList<GameSquare> gameSquareList) {
        this.context = context;
        this.gameSquareList = gameSquareList;
    }

    @Override
    public int getCount() {
        return gameSquareList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null){
            convertView = inflater.inflate(R.layout.game_square_item, null);
        }

        ImageView mark = convertView.findViewById(R.id.ivSquareMark);

        return convertView;
    }
}
