package com.asifsanjary.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        TODO: Handle Connection with Server and Sync Text before going to edit text
         */
        Intent intent = new Intent(this, TextEditor.class);
        startActivity(intent);
    }
}