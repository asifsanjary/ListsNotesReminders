package com.asifsanjary.myapplication.todo_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.asifsanjary.myapplication.R;
import com.asifsanjary.myapplication.repository.database.entity.Todo;

public class TodosListAdapter extends ListAdapter<Todo, TodosListAdapter.TodoViewHolder> {

    public interface onRecyclerViewItemClickListener {
        void onItemClickListener(View view, Todo todo);
    }

    private static onRecyclerViewItemClickListener mItemClickListener;

    public TodosListAdapter(@NonNull DiffUtil.ItemCallback<Todo> diffCallback, onRecyclerViewItemClickListener mItemClickListener) {
        super(diffCallback);
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return TodoViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        Todo current = getItem(position);
        holder.bind(current);
    }

    public static class TodoDiff extends DiffUtil.ItemCallback<Todo> {

        @Override
        public boolean areItemsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
            return oldItem.toDoText.equals(newItem.toDoText);
        }
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView todoText;
        private Todo todo;

        private TodoViewHolder(View itemView) {
            super(itemView);
            todoText = itemView.findViewById(R.id.todoText);
            itemView.setOnClickListener(this);
        }

        public void bind(Todo todo) {
            todoText.setText(todo.toDoText);
            this.todo = todo;
        }

        static TodoViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.todos_recyclerview_item, parent, false);
            return new TodoViewHolder(view);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClickListener(v, this.todo);
            }
        }
    }
}
