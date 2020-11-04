package com.example.pastpaper2019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class GameList extends AppCompatActivity {

    RecyclerView recyclerView;
    GameAdapter adapter;
    ArrayList nameList;
    ArrayList yearList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        DBHelper dbHelper=new DBHelper(this);
        nameList=dbHelper.viewGames("game");
        yearList=dbHelper.viewGames("year");
        setContentView(R.layout.activity_game_list);
        recyclerView=findViewById(R.id.recycleview);
        adapter=new GameAdapter(nameList,yearList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




    }
}