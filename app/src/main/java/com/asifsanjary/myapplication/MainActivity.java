package com.asifsanjary.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.asifsanjary.myapplication.note_view.NoteListAdapter;
import com.asifsanjary.myapplication.note_view.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel mNoteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        /*
        TODO: Handle Connection with Server and Sync Text before going to edit text
         */
    }

    private void initView() {
        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        mNoteViewModel.initViewModel(getApplication());

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final NoteListAdapter adapter = new NoteListAdapter(new NoteListAdapter.NoteDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mNoteViewModel.getAllNotes().observe(this, notes -> {
            adapter.submitList(notes);
        });

        FloatingActionButton fab = findViewById(R.id.add_new_note_fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, TextEditor.class);
            startActivity(intent);
        });
    }
}