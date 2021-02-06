package com.bootcamp.todolist;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogBox extends AppCompatDialogFragment {
    EditText editText;
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogbox, null);
        builder.setView(view)
                .setTitle("Add A New Task")
                .setMessage("what to be included next ?")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "Task cancelled", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("ok", (dialogInterface, i) -> {
                    String text = editText.getText().toString();
                    if(!text.isEmpty()) {
                        DatabaseHelper helper = new DatabaseHelper(getActivity());
                        if (helper.add(text)) {
                            Toast.makeText(getActivity(), "Task added", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Task not added", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getActivity(), "first add task", Toast.LENGTH_SHORT).show();
                    }
                });
        editText=view.findViewById(R.id.edit_todo);
        return builder.create();
    }

}
