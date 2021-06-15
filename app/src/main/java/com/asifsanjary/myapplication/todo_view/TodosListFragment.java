package com.asifsanjary.myapplication.todo_view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.asifsanjary.myapplication.R;
import com.asifsanjary.myapplication.note_view.NoteEditorActivity;
import com.asifsanjary.myapplication.todo_view.completed_todos_view.CompletedTodoActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TodosListFragment extends Fragment {
    private static final String TAG = TodosListFragment.class.getSimpleName();

    public TodosListFragment() {}

    public static TodosListFragment newInstance() {
        return new TodosListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todos_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        FloatingActionButton addNewTodoButton = view.findViewById(R.id.add_new_todo_fab);
        ImageButton seeCompletedTodoButton = view.findViewById(R.id.todos_list_action_see_completed_tasks);
        RecyclerView recyclerView = view.findViewById(R.id.todos_list_recyclerview);
        TodoViewModel todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        todoViewModel.initViewModel(view.getContext());

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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        todoViewModel.getAllUncompletedTodos().observe(getViewLifecycleOwner(), adapter::submitList);

        addNewTodoButton.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), TodosEditorActivity.class);
            startActivity(intent);
        });

        seeCompletedTodoButton.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CompletedTodoActivity.class);
            startActivity(intent);
        });
    }
}