package com.example.todosync.ui.label;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.todosync.MainActivity;
import com.example.todosync.databinding.FragmentLabelBinding;

public class LabelFragment extends Fragment {

    private LabelViewModel labelViewModel;
    private FragmentLabelBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        labelViewModel =
                new ViewModelProvider(this).get(LabelViewModel.class);

        binding = FragmentLabelBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //MainActivity.setCurrentPage(1, this.getContext());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}