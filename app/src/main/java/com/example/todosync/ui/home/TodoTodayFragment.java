package com.example.todosync.ui.home;

import  com.example.todosync.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todosync.databinding.FragmentHomeBinding;
import com.example.todosync.databinding.FragmentTodoTodayBinding;

class TodoTodayFragment extends Fragment {

    private TodoTodayFragment TodoTodayFragment;
    private FragmentTodoTodayBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_todo_today, container, false);
    }
}