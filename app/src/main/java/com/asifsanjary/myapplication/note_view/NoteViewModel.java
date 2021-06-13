package com.asifsanjary.myapplication.note_view;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.asifsanjary.myapplication.repository.Repository;
import com.asifsanjary.myapplication.repository.database.entity.Note;

import java.util.List;

public class NoteViewModel extends ViewModel {
    private Repository repository;

    private LiveData<List<Note>> allNotes;

    public void initViewModel (Context context) {
        repository = Repository.getRepositoryInstance(context);
        allNotes = repository.getNoteList();
    }

    public LiveData<List<Note>> getAllNotes() { return allNotes; }

    public void insertNote(Note note) { repository.insertNote(note); }
}
