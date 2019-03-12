package com.example.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

//    private EntryDatabase db = EntryDatabase.getInstance(this);
//    EntryAdapter adapter;

    private static final String TAG = "CURSOR_CHECK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newEntry();
            }
        });

        setAdapter();
        setOnClickListeners();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setAdapter();
    }

    private class EntryClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra("entry", new Entry(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4))
            );
            startActivityForResult(intent, 2);
        }
    }

    private class EntryLongClickListener implements ListView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);
            db.delete(cursor.getInt(cursor.getColumnIndex(EntryDatabase.ID)));
            setAdapter();
            return false;
        }
    }

    public void newEntry(){
        Intent intent = new Intent(this, AddEntry.class);
        startActivityForResult(intent, 1);
    }

    public void setAdapter () {
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        ListView list = findViewById(R.id.entries);
        EntryAdapter adapter = new EntryAdapter(this, R.layout.entry_row, db.selectAll());
        list.setAdapter(adapter);
    }

    public void setOnClickListeners() {
        ListView view = findViewById(R.id.entries);
        view.setOnItemLongClickListener(new EntryLongClickListener());
        view.setOnItemClickListener(new EntryClickListener());
    }
}
