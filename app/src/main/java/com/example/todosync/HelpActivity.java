package com.example.todosync;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        VideoView videoView = findViewById(R.id.videoView);
        Uri uri = Uri.parse("http://docs.evostream.com/sample_content/assets/bun33s.mp4");
        videoView.setVideoURI(uri);



        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        ProgressDialog progressDialog = new ProgressDialog(HelpActivity.this);
        progressDialog.setMessage("Loading online video...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progressDialog.dismiss();
                videoView.requestFocus();

            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.d("MainActivity","WHAT: "+ what + " EXTRA: "+extra);
                progressDialog.setMessage("Error: "+what);
                progressDialog.dismiss();
                return false;
            }
        });
    }

    public void closeHelp(View v) {
        finish();
        this.overridePendingTransition(R.anim.slidedown,R.anim.slidedown);
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.slidedown,R.anim.slidedown);
    }
}