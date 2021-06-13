package com.asifsanjary.myapplication.todo_view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.asifsanjary.myapplication.MainActivity;
import com.asifsanjary.myapplication.R;
import com.asifsanjary.myapplication.repository.database.entity.Todo;

public class TodosEditorActivity extends AppCompatActivity {

    private static final String TAG = TodosEditorActivity.class.getSimpleName();

    public static final String TODO_KEY = "TODO_KEY_37Y73";
    public static final String TODO_ID_KEY = "TODO_ID_KEY_37Y73";
    public static final String TODO_TEXT_KEY = "TODO_TEXT_KEY_37Y73";

    private EditText editTextTodoTitle;
    private TodoViewModel todoViewModel;

    private boolean todoFound = false;
    private String foundNoteTitle;
    private int foundNoteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_editor_activity_layout);

        Bundle bundle = getIntent().getBundleExtra(TODO_KEY);

        if(bundle != null) {
            foundNoteId = bundle.getInt(TODO_ID_KEY);
            foundNoteTitle = bundle.getString(TODO_TEXT_KEY);
            Log.d(TAG, foundNoteId + " " + foundNoteTitle);
            if (!foundNoteTitle.isEmpty()) todoFound = true;
        }

        initView();

    }

    private void initView() {
        editTextTodoTitle = findViewById(R.id.todo_text_edit_text);

        if(todoFound) {
            editTextTodoTitle.setText(foundNoteTitle);
        }

        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        todoViewModel.initViewModel(getApplication());
        //TODO: Load text from server
    }


    public void saveAndExit(View view) {
        String todoText = editTextTodoTitle.getText().toString();

        Todo todo = new Todo();
        if(todoFound) {
            todo.uid = foundNoteId;
        }
        todo.toDoText = todoText;
        todo.isToDoComplete = false;
        todoViewModel.insertTodo(todo);

        // TODO: Handle this, not following best practices
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}