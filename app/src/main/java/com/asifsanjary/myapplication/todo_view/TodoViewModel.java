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
    private LiveData<List<Todo>> allCompletedTodos;
    private LiveData<List<Todo>> allUncompletedTodos;

    public void initViewModel (Context context) {
        repository = Repository.getRepositoryInstance(context);
        allTodos = repository.getTodoList();
        allCompletedTodos = repository.getCompletedTodoList();
        allUncompletedTodos = repository.getAllIncompleteTodos();
    }

    public LiveData<List<Todo>> getAllTodos() { return allTodos; }

    public LiveData<List<Todo>> getAllCompletedTodos() { return allCompletedTodos; }

    public LiveData<List<Todo>> getAllUncompletedTodos() { return allUncompletedTodos; }

    public void insertTodo(Todo todo) { repository.insertTodo(todo); }

    public void updateTodo(Todo todo) { repository.updateTodo(todo); }
}
