package com.asifsanjary.myapplication.todo_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.asifsanjary.myapplication.R;
import com.asifsanjary.myapplication.repository.database.entity.Todo;

public class TodosListAdapter extends ListAdapter<Todo, TodosListAdapter.TodoViewHolder> {

    private static final String TAG = TodosListAdapter.class.getSimpleName();

    public interface onRecyclerViewItemClickListener {
        void onItemClickListener(View view, Todo todo);
    }

    private static onRecyclerViewItemClickListener itemClickListener;

    public TodosListAdapter(@NonNull DiffUtil.ItemCallback<Todo> diffCallback, onRecyclerViewItemClickListener clickListener) {
        super(diffCallback);
        itemClickListener = clickListener;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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

    static class TodoViewHolder extends RecyclerView.ViewHolder {
        private final TextView todoText;
        private final ImageButton todoCheckboxChecked;
        private final ImageButton todoCheckboxNotChecked;
        private final FrameLayout todoCheckboxWrapper;
        private Todo todo;

        private TodoViewHolder(View itemView) {
            super(itemView);
            todoText = itemView.findViewById(R.id.todoText);
            todoCheckboxChecked = itemView.findViewById(R.id.todoCheckboxChecked);
            todoCheckboxNotChecked = itemView.findViewById(R.id.todoCheckboxNotChecked);
            todoCheckboxWrapper = itemView.findViewById(R.id.todoCheckboxWrapper);
        }

        public void bind(Todo todo) {
            todoText.setText(todo.toDoText);
            this.todo = todo;
            setTodoCheckbox(todo);
            this.todoCheckboxWrapper.setOnClickListener(v -> {

                if (itemClickListener != null) {
                    todo.isToDoComplete = !todo.isToDoComplete;
                    setTodoCheckbox(todo);

                    itemClickListener.onItemClickListener(v, todo);
                }
            });
        }

        private void setTodoCheckbox(Todo todo) {

            if(todo.isToDoComplete) {
                if(todoCheckboxNotChecked.getVisibility() == View.VISIBLE) {
                    todoCheckboxNotChecked.setVisibility(View.GONE);
                }
                todoCheckboxChecked.setVisibility(View.VISIBLE);
            }
            else{
                if(todoCheckboxChecked.getVisibility() == View.VISIBLE) {
                    todoCheckboxChecked.setVisibility(View.GONE);
                }
                todoCheckboxNotChecked.setVisibility(View.VISIBLE);
            }
        }

        static TodoViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.todos_recyclerview_item, parent, false);
            return new TodoViewHolder(view);
        }
    }
}
