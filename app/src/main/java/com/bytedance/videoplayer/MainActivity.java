package com.bytedance.videoplayer;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView;
    private MediaController mc;
    private static final String TAG = "zlj";
    private static RelativeLayout.LayoutParams originLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("VideoView");
        videoView = (VideoView) findViewById(R.id.videoView);
        originLayout = (RelativeLayout.LayoutParams) videoView.getLayoutParams();
        mc = new MediaController(this);
        videoView.setMediaController(mc);
        Uri uri = getIntent().getData();
        videoView.setVideoURI(uri);
//        videoView.setVideoPath(getVideoPath(R.raw.bytedance));
        Configuration cfg = getResources().getConfiguration();
        videoView.start();


    }

    private String getVideoPath(int resId) {
        return "android.resource://" + this.getPackageName() + "/" + resId;
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.i(TAG, "in changed");
        super.onConfigurationChanged(newConfig);
        int orientation = newConfig.orientation;
        int position = videoView.getCurrentPosition();
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.i(TAG, "IN PORTRAIT");
            videoView.seekTo(position);
            videoView.setLayoutParams(originLayout);
        }
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i(TAG, "IN LANDSCAPE");
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            videoView.setLayoutParams(layoutParams);
            videoView.seekTo(position);
        }
    }
}
