package com.asifsanjary.myapplication.note_view;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.asifsanjary.myapplication.repository.NoteRepository;
import com.asifsanjary.myapplication.repository.database.Note;

import java.util.List;

public class NoteViewModel extends ViewModel {
    private NoteRepository mRepository;

    private LiveData<List<Note>> mAllNotes;

    public void initViewModel (Application application) {
        mRepository = new NoteRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() { return mAllNotes; }

    public void insertNote(Note note) { mRepository.insertNote(note); }
}
