package com.example.yuechu.page_yuechu;

import java.io.File;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.yuechu.R;

public class sc1Activity extends Activity  implements  OnClickListener,SurfaceHolder.Callback,RadioGroup.OnCheckedChangeListener
{

    private Button start;// 开始录制按钮
    private Button stop;
    private Button card;
    private RadioGroup radioGroup1,radioGroup2;
    private RadioButton rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8;
    private static MediaRecorder mediarecorder;// 录制视频的类
    private SurfaceView surfaceview;// 显示视频的控件
    private String  resolution_str_;
    String pose_mode_str_;
    String camera_mode_str_;
    String  Indoor_str_;
    String idcard;
    //String device = "Phone";
    private SurfaceHolder surfaceHolder;
    AlertDialog aler = null;

    @SuppressWarnings({ "deprecation", "unused" })
    private static Camera myCamera = null;
    @SuppressWarnings({ "deprecation", "unused" })
    private Camera.Parameters myParameters;
    private Camera.AutoFocusCallback mAutoFocusCallback=null;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化屏幕设置
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // 设置横屏显示
        // 选择支持半透明模式,在有surfaceview的activity中使用。
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_main);
        init();
    }

    @SuppressWarnings("deprecation")
    private void init() {

        start = (Button) this.findViewById(R.id.start);
        stop = (Button) this.findViewById(R.id.stop);
        card = (Button) this.findViewById(R.id.card);

        radioGroup1 = (RadioGroup) this.findViewById(R.id.radiogroup1);
        radioGroup2 = (RadioGroup) this.findViewById(R.id.radiogroup2);

        radioGroup1.setOnCheckedChangeListener(this);
        radioGroup2.setOnCheckedChangeListener(this);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        card.setOnClickListener(this);

        surfaceview = (SurfaceView) this.findViewById(R.id.surfaceview);

        SurfaceHolder holder = surfaceview.getHolder();// 取得holder
        holder.addCallback(this); // holder加入回调接口
        // setType必须设置，要不出错.
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        if(null == myCamera) {
            // 打开前置摄像头
            int cameraCount = 0;
            @SuppressWarnings("unused")
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            cameraCount = Camera.getNumberOfCameras(); // get cameras number

            for ( int camIdx = 0; camIdx < cameraCount;camIdx++ ) {
                Camera.getCameraInfo( camIdx, cameraInfo ); // get camerainfo
                if ( cameraInfo.facing ==Camera.CameraInfo.CAMERA_FACING_FRONT ) {
                    // 代表摄像头的方位，目前有定义值两个分别为CAMERA_FACING_FRONT前置和CAMERA_FACING_BACK后置
                    try {
                        myCamera = Camera.open( camIdx );
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup1, int checkedId) {
        switch (checkedId)
        {
            case R.id.rb1:
                pose_mode_str_ = "warpCopperPhoto";
                Log.i("tag","warpCopperPhoto");
                break;
            case R.id.rb2:
                Log.i("tag","warpA4Photo");
                pose_mode_str_ = "warpA4Photo";
                break;
            case R.id.rb3:
                pose_mode_str_ = "cutEyeCopperPhoto";
                Log.i("tag","cutEyeCopperPhoto");
                break;
            case R.id.rb4:
                pose_mode_str_ = "moveEyeA4Photo";
                Log.i("tag","moveEyeA4Photo");
                break;
            case R.id.rb5:
                pose_mode_str_ = "netVideo";
                Log.i("tag","netVideo");
                break;
            case R.id.rb6:
                pose_mode_str_ = "netPicture";
                Log.i("tag","netPicture");
                break;
            case R.id.rb7:
                pose_mode_str_ = "netA4Photo";
                Log.i("tag","netA4Photo");
                break;
            case R.id.rb8:
                pose_mode_str_ = "netCopperPhoto";
                Log.i("tag","netCopperPhoto");
                break;
            case R.id.rb11:
                resolution_str_= "LR";
                Log.i("tag","LR");
                break;
            case R.id.rb22:
                resolution_str_= "NR";
                Log.i("tag","NR");
                break;
            case R.id.rb33:
                resolution_str_= "HR";
                Log.i("tag","HR");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                if(null == myCamera) {
                    // 打开前置摄像头
                    int cameraCount = 0;
                    @SuppressWarnings("unused")
                    Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                    cameraCount = Camera.getNumberOfCameras(); // get cameras number

                    for ( int camIdx = 0; camIdx < cameraCount;camIdx++ ) {
                        Camera.getCameraInfo( camIdx, cameraInfo ); // get camerainfo
                        if ( cameraInfo.facing ==Camera.CameraInfo.CAMERA_FACING_BACK) {
                            // 代表摄像头的方位，目前有定义值两个分别为CAMERA_FACING_FRONT前置和CAMERA_FACING_BACK后置
                            try {
                                myCamera = Camera.open( camIdx );
                            } catch (RuntimeException e) {
                                e.printStackTrace();
                                break;
                            }
                        }
                    }
                }
                myCamera.unlock();
                if (null == mediarecorder) {
                    mediarecorder = new MediaRecorder();// 创建mediarecorder对象
                }
                mediarecorder.setCamera(myCamera);
                // 设置录制视频源为Camera(相机)
                mediarecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
//                 mediarecorder.setOrientationHint(270);//视频旋转90度

                // 设置录制完成后视频的封装格式THREE_GPP为3gp.MPEG_4为mp4
                mediarecorder .setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

                // 设置录制的视频编码h263 h264
                mediarecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);

                // 设置视频录制的分辨率。必须放在设置编码和格式的后面，否则报错
                mediarecorder.setVideoSize(176, 144);

                // 设置录制的视频帧率。必须放在设置编码和格式的后面，否则报错
                mediarecorder.setVideoFrameRate(20);

                mediarecorder.setPreviewDisplay(surfaceHolder.getSurface());

                String name = "fake_"+resolution_str_+"_"+"Phone"+"_"+"Indoor_"+ pose_mode_str_+"_"+idcard+".mp4";
                File file = new File("/sdcard/video/");
                file.mkdirs();// 创建文件夹
                String fileName = "/sdcard/video/" + name;
                // 设置视频文件输出的路径
                mediarecorder.setOutputFile(fileName);
                try {
                    mediarecorder.prepare(); // 准备录制
                    mediarecorder.start(); // 开始录制
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.stop:
                if (mediarecorder != null) {
                    mediarecorder.stop();  // 停止录制
                    mediarecorder.release();   // 释放资源
                    mediarecorder = null;
                }
                if(myCamera !=null){
                    myCamera.release();
                    myCamera = null;
                }
                break;

            case R.id.card:
                final EditText filename = new EditText(this);
                filename.setInputType(EditorInfo.TYPE_CLASS_PHONE);//设置输入时是数字键盘
                Builder alerBuidler = new Builder(this);
                alerBuidler
                        .setTitle("请输入工号")
                        .setView(filename)
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        idcard = filename.getText().toString();
                                    }
                                });
                aler = alerBuidler.create();
                aler.setCanceledOnTouchOutside(false);
                aler.show();
                break;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

        // 将holder，这个holder为开始在oncreat里面取得的holder，将它赋给surfaceHolder
        surfaceHolder = holder;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // 将holder，这个holder为开始在oncreat里面取得的holder，将它赋给surfaceHolder
        surfaceHolder = holder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // surfaceDestroyed的时候同时对象设置为null
        surfaceview = null;
        surfaceHolder = null;
        mediarecorder = null;
    }
}
