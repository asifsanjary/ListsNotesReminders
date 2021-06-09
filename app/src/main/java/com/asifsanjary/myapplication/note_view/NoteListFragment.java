package com.asifsanjary.myapplication.note_view;

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

import com.asifsanjary.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NoteListFragment extends Fragment {
    private static final String TAG = NoteListFragment.class.getSimpleName();
    private NoteViewModel mNoteViewModel;

    public NoteListFragment() {
        // Required empty public constructor
    }

    public static NoteListFragment newInstance() {
        NoteListFragment fragment = new NoteListFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note_list_view, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        mNoteViewModel.initViewModel(view.getContext());

        RecyclerView recyclerView = view.findViewById(R.id.note_list_recyclerview);
        final NoteListAdapter adapter = new NoteListAdapter(new NoteListAdapter.NoteDiff(), (v, note) -> {
            Intent intent = new Intent(v.getContext(), NoteEditorActivity.class);

            Log.d(TAG, note.uid + " "+ note.noteTitle+ " " + note.noteContent);
            Bundle bundle = new Bundle();
            bundle.putInt(NoteEditorActivity.NOTE_ID_KEY, note.uid);
            bundle.putString(NoteEditorActivity.NOTE_TITLE_KEY, note.noteTitle);
            bundle.putString(NoteEditorActivity.NOTE_CONTENT_KEY, note.noteContent);
            intent.putExtra(NoteEditorActivity.NOTE_KEY, bundle);

            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mNoteViewModel.getAllNotes().observe(getViewLifecycleOwner(), adapter::submitList);

        FloatingActionButton fab = view.findViewById(R.id.add_new_note_fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), NoteEditorActivity.class);
            startActivity(intent);
        });
    }
}