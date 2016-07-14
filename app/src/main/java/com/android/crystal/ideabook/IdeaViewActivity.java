package com.android.crystal.ideabook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.TextView;

public class IdeaViewActivity extends AppCompatActivity {


    DBHandler dbHandler;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_view);

       dbHandler = new DBHandler(this, null, null, 1);

        Bundle extras = getIntent().getExtras();
        if(extras !=null){

            id =(int)extras.getLong("itemId");
        }

        Ideas idea = dbHandler.getIdeaFromId(id + 1);

        final TextView title= (TextView)findViewById(R.id.txtTitleView);
        final TextView content =(TextView)findViewById(R.id.txtContentView);
        title.setText(idea.get_ideaTitle());
        content.setText(idea.get_ideaContent());
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
}
