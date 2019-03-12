package com.example.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView title = findViewById(R.id.title);
        TextView content = findViewById(R.id.content);
        TextView datetime = findViewById(R.id.datetime);
        TextView mood = findViewById(R.id.mood);

        Intent intent = getIntent();
        Entry e = (Entry) intent.getSerializableExtra("entry");

        title.setText(e.getTitle());
        content.setText(e.getContent());
        datetime.setText(e.getDatatime());

        switch (e.getMood()){
            case 0:
                mood.setText("Very sad");
                break;
            case 1:
                mood.setText("Neutral");
                break;
            case 2:
                mood.setText("Happy");
                break;
            case 3:
                mood.setText("Very happy");
                break;
        }

    }
}
