package com.example.velev.audiorecorder;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "MediaRecording";

    private Button btnStart;
    private Button btnStop;
    private TextView recStatus;
    private MediaRecorder recorder = null;
    private File audiofile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
    }

    private void initUi() {

        btnStart = (Button) findViewById(R.id.btn_start_recording);
        onStartRecording();
        btnStop = (Button) findViewById(R.id.btn_stop_recording);
        onStopRecording();
        recStatus = (TextView) findViewById(R.id.tv_recording_status);
        recStatus.setText("Not Recording Tap the start button to start");
    }


    private void onStartRecording() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRecording();
                recStatus.setText("Recording...");
            }
        });
    }

    private void startRecording() {
//        File dir = Environment.getExternalStorageDirectory();
        File filePath = new File(Environment.getExternalStorageDirectory() + "/AudioRecorder");
        try {
//            audiofile = File.createTempFile("AudioRecorder/pesho", ".3gp", dir);
            if (filePath.exists()) {
                audiofile = File.createTempFile("prefix", ".3gp",filePath);
            } else {
                filePath.mkdir();
                audiofile = File.createTempFile("prefix", ".3gp",filePath);
            }
        } catch (IOException e) {
            Log.e(TAG, "external storage access error");
            return;
        }
        //Creating MediaRecorder and specifying audio source, output format, encoder & output format
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(audiofile.getAbsolutePath());
        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recorder.start();
    }

    private void onStopRecording() {
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopRecording();
                recStatus.setText("Not Recording Tap the start button to start");
            }
        });
    }

    private void stopRecording() {
        //stopping recorder
        recorder.stop();
        recorder.release();
        //after stopping the recorder, create the sound file and add it to media library.
        addRecordingToMediaLibrary();
    }

    protected void addRecordingToMediaLibrary() {
        //creating content values of size 4
        ContentValues values = new ContentValues(4);
        long current = System.currentTimeMillis();
        Date date = new Date(current);
        String strDate = date.toString();


        String name = audiofile.getName();
        values.put(MediaStore.Audio.Media.TITLE, "audio" + name);
        values.put(MediaStore.Audio.Media.DATE_ADDED, strDate);
        values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/3gpp");
        values.put(MediaStore.Audio.Media.DATA, audiofile.getAbsolutePath());

        //creating content resolver and storing it in the external content uri
        ContentResolver contentResolver = getContentResolver();
        Uri base = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Uri newUri = contentResolver.insert(base, values);

        //sending broadcast message to scan the media file so that it can be available
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, newUri));
        Toast.makeText(this, "Added File " + newUri, Toast.LENGTH_LONG).show();
    }
}
