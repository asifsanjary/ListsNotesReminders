package com.asifsanjary.myapplication.repository.database;

import android.text.Html;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "note_title")
    public String noteTitle;

    @ColumnInfo(name = "note_creation_time")
    public long noteCreationTime;

    @ColumnInfo(name = "note_update_time")
    public long noteUpdateTime;

    @ColumnInfo(name = "note_content")
    public String noteContent;

    //TODO: Note Tagging with Tasks and Lists via uid
}
