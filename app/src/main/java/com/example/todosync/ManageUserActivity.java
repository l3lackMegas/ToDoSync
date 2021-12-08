package com.example.todosync;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ManageUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);

        TextView changePic = findViewById(R.id.changePicBtn);
        changePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent captureScreen = new Intent(ManageUserActivity.this, capture.class);
                startActivity(captureScreen);
            }
        });
    }

    public void closeManageAccount(View v) {
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.fadein,R.anim.slidedown);
    }
}