package com.asifsanjary.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.asifsanjary.myapplication.note_view.NoteListFragment;
import com.asifsanjary.myapplication.reminder_view.RemindersListFragment;
import com.asifsanjary.myapplication.todo_view.TodosListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        initView();
        /*
        TODO: Handle Connection with Server and Sync Text before going to edit text
         */
    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.front_page_bottom_navigation);
        final FragmentManager fragmentManager = getSupportFragmentManager();

        final Fragment todosListFragment = TodosListFragment.newInstance();
        final Fragment noteListFragment = NoteListFragment.newInstance();
        final Fragment remindersListFragment = RemindersListFragment.newInstance();

        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    Fragment fragment;
                    switch (item.getItemId()) {
                        case R.id.action_notes:
                            fragment = noteListFragment;
                            break;
                        case R.id.action_reminders:
                            fragment = remindersListFragment;
                            break;
                        case R.id.action_todos:
                        default:
                            fragment = todosListFragment;
                            break;
                    }
                    fragmentManager.beginTransaction().replace(R.id.front_page_frame_container, fragment).commit();
                    return true;
                });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_todos);
    }
}