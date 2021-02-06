package com.bootcamp.todolist;


import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
ListView listView;
List<String> todo;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_view);
        toolbar = findViewById(R.id.nav_bar);
        setSupportActionBar(toolbar);
        todo = new ArrayList<>();

            DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
            Cursor cursor = helper.retrieve();
            if (cursor.moveToFirst()) {
                do {
                    todo.add(cursor.getString(1));
                } while (cursor.moveToNext());
            } else {
                Toast.makeText(MainActivity.this, "No data found", Toast.LENGTH_SHORT).show();
            }
            MyAdapter adapter = new MyAdapter(MainActivity.this, todo);
            listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.items,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add) {
            Toast.makeText(MainActivity.this, "You clicked add button on toolbar", Toast.LENGTH_SHORT).show();
            DialogBox dialogBox = new DialogBox();
            retrieveData();
            dialogBox.show(getSupportFragmentManager(), "Dialog Box");
        } else if (id == R.id.delete) {
            DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
            if(helper.deleteAll()) {
                retrieveData();
            }
        }else if(id == R.id.viewAll){
            retrieveData();
        }else if(id == R.id.search){
            Toast.makeText(MainActivity.this, "Search view is in progress", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

   public void retrieveData() {
        todo = new ArrayList<>();
       DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
        Cursor cursor = helper.retrieve();
        if (cursor.moveToFirst()) {
            do {
                todo.add(cursor.getString(1));
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(MainActivity.this, "No data found", Toast.LENGTH_SHORT).show();
        }
        MyAdapter adapter = new MyAdapter(MainActivity.this, todo);
        listView.setAdapter(adapter);
   }
}