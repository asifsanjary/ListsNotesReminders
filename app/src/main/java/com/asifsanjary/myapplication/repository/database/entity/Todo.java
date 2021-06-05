package com.asifsanjary.myapplication.repository.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Todo {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "todo_text")
    public String toDoText;

    @ColumnInfo(name = "todo_creation_time")
    public long noteCreationTime;

    @ColumnInfo(name = "todo_update_time")
    public long noteUpdateTime;

    //TODO: Note Tagging with Tasks and Lists via uid
}
