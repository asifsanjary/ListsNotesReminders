package com.asifsanjary.myapplication.repository.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.asifsanjary.myapplication.repository.database.entity.Note;
import com.asifsanjary.myapplication.repository.database.entity.Todo;

import java.util.List;

@Dao
public interface TodoDao {

    @Query("SELECT * FROM todo")
    LiveData<List<Todo>> getTodoList();

    @Query("SELECT * FROM todo WHERE uid IN (:todoIds)")
    LiveData<List<Todo>> loadAllTodosByIds(int[] todoIds); // TODO : should return only 1

    // TODO: filter by completeness of todo

    @Query("SELECT * FROM todo WHERE todo_text LIKE :text LIMIT 1")
    Todo findTodosByText(String text);

    @Insert
    void insertTodos(Todo... todos);

    @Update
    void updateTodos(Todo... todos);

    @Delete
    void deleteTodo(Todo todo);
}
