package com.example.pastpaper2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText UserName,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserName=findViewById(R.id.etuserName);
        Password=findViewById(R.id.etPassword);


    }

    public  void registerUser(View view){
        DBHelper dbHelper=new DBHelper(this);
        long val=dbHelper.egisterUser(UserName.getText().toString(), Password.getText().toString());
        if(val>0){
            Toast.makeText(this,"Registtration succesful",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Registtration  unsuccesful",Toast.LENGTH_SHORT).show();
        }

    }

    public void login(View view){
        DBHelper dbHelper=new DBHelper(this);
        int value=dbHelper.loginUser(UserName.getText().toString(),Password.getText().toString());
        if (value>0){

            Toast.makeText(this,"login succesful",Toast.LENGTH_SHORT).show();
            Intent intent;
            if(UserName.getText().toString().equals("Admin")){
                 intent=new Intent(getApplicationContext(),AddGame.class);
            }
            else{
                intent=new Intent(getApplicationContext(),GameList.class);
            }
            startActivity(intent);
        }
        else {
            Toast.makeText(this,"login failed",Toast.LENGTH_SHORT).show();
        }

    }
}