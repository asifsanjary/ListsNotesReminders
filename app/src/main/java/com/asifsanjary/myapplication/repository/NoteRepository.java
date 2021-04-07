package com.asifsanjary.myapplication.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.asifsanjary.myapplication.repository.database.AppDatabase;
import com.asifsanjary.myapplication.repository.database.Note;
import com.asifsanjary.myapplication.repository.database.NoteDao;

import java.util.List;

public class NoteRepository {
    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNotes;

    public NoteRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    public void insertNote(Note note) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mNoteDao.insertAll(note);
        });
    }
}
