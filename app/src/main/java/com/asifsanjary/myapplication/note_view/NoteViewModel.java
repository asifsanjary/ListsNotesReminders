package com.asifsanjary.myapplication.note_view;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.asifsanjary.myapplication.repository.Repository;
import com.asifsanjary.myapplication.repository.database.entity.Note;

import java.util.List;

public class NoteViewModel extends ViewModel {
    private Repository repository;

    private LiveData<List<Note>> mAllNotes;

    public void initViewModel (Application application) {
        repository = Repository.getRepositoryInstance(application);
        mAllNotes = repository.getNoteList();
    }

    public LiveData<List<Note>> getAllNotes() { return mAllNotes; }

    public void insertNote(Note note) { repository.insertNote(note); }
}
