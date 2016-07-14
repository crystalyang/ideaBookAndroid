package com.android.crystal.ideabook;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        Toast.makeText(MainActivity.this, "Happy creating!", Toast.LENGTH_LONG).show();


        Intent i = new Intent(this,IdeaAddActivity.class); // pass the activity name to start
        startActivity(i);
    }
}
