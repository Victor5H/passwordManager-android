package com.example.passwordmanage.basic_activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passwordmanage.CustomHelper;
import com.example.passwordmanage.R;
import com.google.android.material.snackbar.Snackbar;

public class Del extends AppCompatActivity {
    Button del;
    EditText id;
    int ret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del);
        del = findViewById(R.id.button6);
        id = findViewById(R.id.editTextTextPersonName3);
        del.setOnClickListener(v -> {
            String d = id.getText().toString();
            if (d.length() != 0) {
                CustomHelper ch = new CustomHelper(Del.this);
                try {
                    ret = ch.delete(Integer.parseInt(d));
                } catch (Exception e) {
                    View parentLayout;
                    parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, e.toString(), Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();

                }
                if (ret == 1) {
                    View parentLayout;
                    parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "Successfully deleted Row ", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                } else {
                    View parentLayout;
                    parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "Unsuccessful ", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }

            } else {
                View parentLayout;
                parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, "Enter something", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }
}