package com.example.todosync.ui.label;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.todosync.R;
import com.example.todosync.databinding.FragmentTodoTodayBinding;

class LabelContentFragment extends Fragment {

    private LabelContentFragment TodoTodayFragment;
    private FragmentTodoTodayBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_todo_today, container, false);
    }
}