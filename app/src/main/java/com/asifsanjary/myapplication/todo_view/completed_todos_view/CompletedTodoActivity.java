package com.asifsanjary.myapplication.todo_view.completed_todos_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.asifsanjary.myapplication.R;
import com.asifsanjary.myapplication.todo_view.TodoViewModel;
import com.asifsanjary.myapplication.todo_view.TodosEditorActivity;
import com.asifsanjary.myapplication.todo_view.TodosListAdapter;

public class CompletedTodoActivity extends AppCompatActivity {

    private static final String TAG = CompletedTodoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_todo);

        if(getSupportActionBar() != null) {
            getSupportActionBar().show();
            getSupportActionBar().setTitle("Completed Tasks");
        }

        initView();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.completed_todos_list_recyclerview);
        TodoViewModel todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        todoViewModel.initViewModel(getApplicationContext());

        final TodosListAdapter adapter = new TodosListAdapter(new TodosListAdapter.TodoDiff(), (v, todo) -> {
            // TODO: Add New / Edit / Mark Complete - Incomplete ToDo

            todoViewModel.updateTodo(todo);

            Intent intent = new Intent(v.getContext(), TodosEditorActivity.class);

            Log.d(TAG, todo.uid + " "+ todo.toDoText);

            Bundle bundle = new Bundle();
            bundle.putInt(TodosEditorActivity.TODO_ID_KEY, todo.uid);
            bundle.putString(TodosEditorActivity.TODO_TEXT_KEY, todo.toDoText);
            intent.putExtra(TodosEditorActivity.TODO_KEY, bundle);

//            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        todoViewModel.getAllCompletedTodos().observe(this, adapter::submitList);
    }
}