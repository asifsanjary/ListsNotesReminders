package com.asifsanjary.myapplication.note_view;

import android.text.Html;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.asifsanjary.myapplication.R;
import com.asifsanjary.myapplication.repository.database.entity.Note;

public class NoteListAdapter extends ListAdapter<Note, NoteListAdapter.NoteViewHolder> {

    public interface onRecyclerViewItemClickListener {
        void onItemClickListener(View view, Note note);
    }

    private static onRecyclerViewItemClickListener mItemClickListener;

    public NoteListAdapter(@NonNull DiffUtil.ItemCallback<Note> diffCallback, onRecyclerViewItemClickListener mItemClickListener) {
        super(diffCallback);
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return NoteViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note current = getItem(position);
        holder.bind(current);
    }

    public static class NoteDiff extends DiffUtil.ItemCallback<Note> {

        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.noteTitle.equals(newItem.noteTitle);
        }
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView noteTitle;
        private final TextView noteContent;
        private Note note;

        private NoteViewHolder(View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.noteTitle);
            noteContent = itemView.findViewById(R.id.noteContent);
            itemView.setOnClickListener(this);
        }

        public void bind(Note note) {
            // TODO: use HTMLCompact
            SpannableString spannableNoteContent = new SpannableString(Html.fromHtml(note.noteContent));
            noteTitle.setText(note.noteTitle);
            noteContent.setText(spannableNoteContent);
            this.note = note;
        }

        static NoteViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.note_recyclerview_item, parent, false);
            return new NoteViewHolder(view);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClickListener(v, this.note);
            }
        }
    }
}
