package com.example.journal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AddEntry extends AppCompatActivity {
    EditText title, content;
    private int mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_entry);
        mood = 1;
    }

    public void mood_button(View v){
        String tag = v.getTag().toString();
        ImageView verysad = (ImageView)findViewById(R.id.verysad);
        ImageView neutral = (ImageView)findViewById(R.id.neutral);
        ImageView happy = (ImageView)findViewById(R.id.happy);
        ImageView veryhappy = (ImageView)findViewById(R.id.veryhappy);

        verysad.setBackgroundResource(0);
        neutral.setBackgroundResource(0);
        happy.setBackgroundResource(0);
        veryhappy.setBackgroundResource(0);

        ImageView moodimage = (ImageView)findViewById(R.id.neutral);
        switch (tag){
            case "verysad":
                mood = 0;
                moodimage = (ImageView)findViewById(R.id.verysad);
                break;
            case "neutral":
                mood = 1;
                moodimage = (ImageView)findViewById(R.id.neutral);
                break;
            case "happy":
                mood = 2;
                moodimage = (ImageView)findViewById(R.id.happy);
                break;
            case "veryhappy":
                mood = 3;
                moodimage = (ImageView)findViewById(R.id.veryhappy);
                break;
        }
        moodimage.setBackgroundResource(R.drawable.border);
    }
    public void submitEntry(View v){
        title = findViewById(R.id.textitle);
        content = findViewById(R.id.textcontent);

        String titletext = title.getText().toString();
        String contenttext = content.getText().toString();

        Entry newentry = new Entry(0, titletext, contenttext, "", mood);
        EntryDatabase.getInstance(this).insert(newentry);

        finish();
    }

}
