package com.asifsanjary.myapplication.reminder_view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asifsanjary.myapplication.R;

public class RemindersListFragment extends Fragment {

    public RemindersListFragment() {}

    public static RemindersListFragment newInstance() {
        RemindersListFragment fragment = new RemindersListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reminders_list, container, false);
    }
}