package com.asifsanjary.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.asifsanjary.myapplication.note_view.NoteListAdapter;
import com.asifsanjary.myapplication.note_view.NoteViewModel;
import com.asifsanjary.myapplication.repository.database.entity.Note;
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
        final NoteListAdapter adapter = new NoteListAdapter(new NoteListAdapter.NoteDiff(), (view, note) -> {
            Intent intent = new Intent(view.getContext(), TextEditor.class);

            Log.d("MainActivity_sanjary", note.uid + " "+ note.noteTitle+ " " + note.noteContent);
            Bundle bundle = new Bundle();
            bundle.putInt(TextEditor.NOTE_ID_KEY, note.uid);
            bundle.putString(TextEditor.NOTE_TITLE_KEY, note.noteTitle);
            bundle.putString(TextEditor.NOTE_CONTENT_KEY, note.noteContent);
            intent.putExtra(TextEditor.NOTE_KEY, bundle);

            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mNoteViewModel.getAllNotes().observe(this, adapter::submitList);

        FloatingActionButton fab = findViewById(R.id.add_new_note_fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(view.getContext(), TextEditor.class);
            startActivity(intent);
        });
    }
}