package com.bootcamp.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter {
    List<String> listToDo;
    Context context;

    public MyAdapter(@NonNull Context context, List<String> toDoList) {
        super(context,R.layout.row,toDoList);
        this.listToDo = toDoList;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.row,parent,false);
        TextView textView = view.findViewById(R.id.todo_view);
        Button task_completed = view.findViewById(R.id.btn_task);
        textView.setText(listToDo.get(position));
        view.setOnClickListener(view12 -> Toast.makeText(context, "Position "+position, Toast.LENGTH_SHORT).show());
        task_completed.setOnClickListener(view1 -> {
            DatabaseHelper helper = new DatabaseHelper(context);
                if (helper.delete(String.valueOf(getItem(position)))) {
                Toast.makeText(context, "Deleted index " + position, Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(context, "no deletion occurred at position "+position, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
