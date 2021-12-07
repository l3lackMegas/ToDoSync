package com.example.todosync.ui.label;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todosync.MainActivity;

public class LabelViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LabelViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is label fragment");
        MainActivity.current_page = 1;
    }

    public LiveData<String> getText() {
        return mText;
    }
}