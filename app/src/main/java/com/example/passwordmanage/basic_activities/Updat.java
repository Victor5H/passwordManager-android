package com.example.passwordmanage.basic_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passwordmanage.CustomHelper;
import com.example.passwordmanage.R;
import com.example.passwordmanage.models.Entries_Model;
import com.google.android.material.snackbar.Snackbar;

public class Updat extends AppCompatActivity {
    EditText id,name,word,tag;
    Button update;
    CustomHelper ch ;
    int idi;
    Entries_Model entries_model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updat);

        id=findViewById(R.id.editTextTextPersonName4);
        name=findViewById(R.id.editTextTextPersonName5);
        word=findViewById(R.id.editTextTextPersonName6);
        tag=findViewById(R.id.editTextTextPersonName8);
        update=findViewById(R.id.button7);
        entries_model = new Entries_Model();
        Intent intent =getIntent();
        idi = intent.getExtras().getInt("id");
        id.setText(String.valueOf(idi));
        id.setEnabled(false);
        ch=new CustomHelper(this);
        update.setOnClickListener(v -> {
            String i,n,p,t;
            i=id.getText().toString();
            entries_model.setId(Integer.parseInt(i));
            n=name.getText().toString();
            entries_model.setName(n);
            p=word.getText().toString();
            entries_model.setWord(p);
            t=tag.getText().toString();
            entries_model.setTag(t);
            if(i.length()!=0 && n.length()!=0 && p.length()!=0 && t.length()!=0){
                int ret= ch.update(entries_model);
                if(ret==1) {
                    View parentLayout;
                    parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "Successful" + ret, Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
                else{
                    View parentLayout;
                    parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "Unsuccessful" + ret, Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            }
            else{
                View parentLayout;
                parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, "Enter Something", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }
}