package com.asifsanjary.myapplication.repository.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM note WHERE uid IN (:noteIds)")
    LiveData<List<Note>> loadAllNotesByIds(int[] noteIds);

    @Query("SELECT * FROM note WHERE note_title LIKE :title LIMIT 1")
    Note findByTitle(String title);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Note... notes);

    @Delete
    void delete(Note note);
}
