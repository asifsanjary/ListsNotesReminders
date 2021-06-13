package com.asifsanjary.myapplication.todo_view;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.asifsanjary.myapplication.repository.Repository;
import com.asifsanjary.myapplication.repository.database.entity.Todo;

import java.util.List;

public class TodoViewModel extends ViewModel {
    private Repository repository;

    private LiveData<List<Todo>> allTodos;

    public void initViewModel (Context context) {
        repository = Repository.getRepositoryInstance(context);
        allTodos = repository.getTodoList();
    }

    public LiveData<List<Todo>> getAllTodos() { return allTodos; }

    public void insertTodo(Todo todo) { repository.insertTodo(todo); }

    public void updateTodo(Todo todo) { repository.updateTodo(todo); }
}
