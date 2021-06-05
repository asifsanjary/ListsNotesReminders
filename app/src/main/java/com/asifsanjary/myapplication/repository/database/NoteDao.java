package com.asifsanjary.myapplication.repository.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.asifsanjary.myapplication.repository.database.entity.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM note WHERE uid IN (:noteIds)")
    LiveData<List<Note>> loadAllNotesByIds(int[] noteIds); // TODO : should return only 1

    @Query("SELECT * FROM note WHERE note_title LIKE :title LIMIT 1")
    Note findNotesByTitle(String title);

    @Insert
    void insertNotes(Note... notes);

    @Update
    void updateNotes(Note... notes);

    @Delete
    void deleteNote(Note note);
}
