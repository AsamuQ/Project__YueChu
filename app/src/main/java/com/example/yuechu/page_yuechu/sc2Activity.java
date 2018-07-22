package com.example.yuechu.page_yuechu;


import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.yuechu.R;


public class sc2Activity extends Activity {
    private Button btn_start;
    private Button btn_end;
    private ImageView back;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sc2);

        btn_start=(Button)findViewById(R.id.btn_start);
        btn_end=(Button)findViewById(R.id.btn_end);
        back=(ImageView)findViewById(R.id.toolbar_back);
        //返回事件
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //网络视频
        String videoUrl2 = Utils.videoUrl;


        Uri uri = Uri.parse(videoUrl2);

        videoView = (VideoView) this.findViewById(R.id.videoView);

        //设置视频控制器
        videoView.setMediaController(new MediaController(this));

        //播放完成回调
        videoView.setOnCompletionListener(new MyPlayerOnCompletionListener());

        //设置视频路径
        videoView.setVideoURI(uri);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_start.getText().equals("开始")){
                    //开始播放视频
                    videoView.start();
                    btn_start.setText("暂停");
                }else {
                    videoView.pause();
                    btn_start.setText("开始");
                }
            }
        });

        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.stopPlayback();
            }
        });

    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText(sc2Activity.this, "播放完成了", Toast.LENGTH_SHORT).show();
        }
    }

    public static class Utils {

        public static final String videoUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    }

}
