package com.example.pastpaper2019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameOverview extends AppCompatActivity {

    TextView title,avgRating;
    EditText etComent;
    RatingBar ratingBar;
    RecyclerView recyclerView;
    ArrayList commentList;
    CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_overview);
        Intent intent=getIntent();
        String gameName=intent.getStringExtra("game");
        title=findViewById(R.id.GameTitle);
        avgRating=findViewById(R.id.txtAvgRate);
        etComent=findViewById(R.id.etComment);
        ratingBar=findViewById(R.id.GameRating);

        title.setText(gameName);
        DBHelper dbHelper=new DBHelper(this);
        recyclerView=findViewById(R.id.commentRecycle);
        commentList=dbHelper.viewComments(title.getText().toString());
        adapter=new CommentAdapter(commentList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        avgRating.setText(String.valueOf(dbHelper.getAvg(title.getText().toString())));


    }

    public void insertComment(View view){
        DBHelper dbHelper=new DBHelper(this);
        long val=dbHelper.insertComments(title.getText().toString(),Math.round(ratingBar.getRating()),etComent.getText().toString());
        if (val>0){
            Toast.makeText(this,"comment successfully added",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"comment addition failed",Toast.LENGTH_SHORT).show();
        }
    }
}