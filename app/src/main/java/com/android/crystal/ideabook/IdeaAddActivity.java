package com.android.crystal.ideabook;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;
import android.view.MenuItem;
import android.view.Menu;

public class IdeaAddActivity extends AppCompatActivity {

    EditText ideaTitle;
    EditText ideaContent;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_add);

        ideaTitle = (EditText) findViewById(R.id.txtInputTitle);
        ideaContent = (EditText) findViewById(R.id.txtInputContent);
        dbHandler = new DBHandler(this, null, null, 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_back:
                startActivity(new Intent(this,MainActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickAdd(View view) {
        try {
            //Toast.makeText(IdeaAddActivity.this, "New Idea Created!", Toast.LENGTH_LONG).show();
            AlertDialog dialog = new AlertDialog.Builder(IdeaAddActivity.this).create();
            dialog.setTitle("new idea");
            dialog.setMessage("new idea created!");
            dialog.show();

            Ideas idea = new Ideas(ideaTitle.getText().toString(), ideaContent.getText().toString());
            dbHandler.addIdea(idea);
            finish(); //return to main activity
        }catch (RemoteViews.ActionException e){
            e.printStackTrace();
        }
    }

}
