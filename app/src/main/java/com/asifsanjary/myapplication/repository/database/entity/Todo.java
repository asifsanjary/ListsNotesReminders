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

    @ColumnInfo(name = "is_todo_complete")
    public boolean isToDoComplete;

    @ColumnInfo(name = "todo_creation_time")
    public long toDoCreationTime;

    @ColumnInfo(name = "todo_update_time")
    public long toDoUpdateTime;
    //TODO: Note Tagging with Tasks and Lists via uid
}
