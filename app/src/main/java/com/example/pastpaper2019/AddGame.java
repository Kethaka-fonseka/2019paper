package com.example.pastpaper2019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddGame extends AppCompatActivity {

    EditText gameName,year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
gameName=findViewById(R.id.etGameName);
year=findViewById(R.id.etGameYear);

    }

    public void addGame(View view){
        DBHelper dbHelper=new DBHelper(this);
        boolean val=dbHelper.addGame(gameName.getText().toString(),Integer.parseInt(year.getText().toString()));
        if (val==true){
            Toast.makeText(this,"Game succesfully added",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Game addition failed",Toast.LENGTH_SHORT).show();
        }
    }

}