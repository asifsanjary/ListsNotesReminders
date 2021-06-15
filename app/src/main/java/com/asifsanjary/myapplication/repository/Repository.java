package com.asifsanjary.myapplication.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.asifsanjary.myapplication.repository.database.AppDatabase;
import com.asifsanjary.myapplication.repository.database.NoteDao;
import com.asifsanjary.myapplication.repository.database.TodoDao;
import com.asifsanjary.myapplication.repository.database.entity.Note;
import com.asifsanjary.myapplication.repository.database.entity.Todo;

import java.util.List;

public class Repository {
    private NoteDao noteDao;
    private TodoDao todoDao;
    private LiveData<List<Note>> noteList;
    private LiveData<List<Todo>> todoList;
    private LiveData<List<Todo>> completedTodoList;
    private LiveData<List<Todo>> incompleteTodoList;

    private Repository(Context context) {
        initDb(context);
    }

    private static Repository REPOSITORY_INSTANCE;

    public static Repository getRepositoryInstance(Context context) {
        if(REPOSITORY_INSTANCE == null) {
            synchronized (Repository.class) {
                if(REPOSITORY_INSTANCE == null) {
                    REPOSITORY_INSTANCE = new Repository(context);
                }
            }
        }
        return REPOSITORY_INSTANCE;
    }

    private void initDb(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        noteDao = db.noteDao();
        todoDao = db.todoDao();
        noteList = noteDao.getAllNotes();
        todoList = todoDao.getTodoList();
        completedTodoList = todoDao.getAllCompletedTodos();
        incompleteTodoList = todoDao.getAllIncompleteTodos();
    }

    public LiveData<List<Todo>> getTodoList() {
        return todoList;
    }

    public LiveData<List<Todo>> getCompletedTodoList() {
        return completedTodoList;
    }

    public LiveData<List<Todo>> getAllIncompleteTodos() {
        return incompleteTodoList;
    }

    public void insertTodo(Todo todo) {
        AppDatabase.databaseWriteExecutor.execute(() -> todoDao.insertTodos(todo));
    }

    public void updateTodo(Todo todo) {
        AppDatabase.databaseWriteExecutor.execute(() -> todoDao.updateTodos(todo));
    }

    public LiveData<List<Note>> getNoteList() {
        return noteList;
    }

    public void insertNote(Note note) {
        AppDatabase.databaseWriteExecutor.execute(() -> noteDao.insertNotes(note));
    }

    public void updateNote(Note note) {
        AppDatabase.databaseWriteExecutor.execute(() -> noteDao.updateNotes(note));
    }
}
