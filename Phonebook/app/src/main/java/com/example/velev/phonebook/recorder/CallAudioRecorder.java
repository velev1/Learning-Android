package com.example.velev.phonebook.recorder;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by velev on 23.11.2017 г..
 */

public class CallAudioRecorder {
    private static final String TAG = "MediaRecording";

    private static CallAudioRecorder instance;
    private MediaRecorder mRecorder;
    private File mAudioFile = null;
    private Context mContext;

    private CallAudioRecorder(Context context) {
        mContext = context;
        if(mRecorder == null) {
            mRecorder = new MediaRecorder();
        }
    }

    public static CallAudioRecorder getCallAudioRecorder(Context context) {
        if (instance == null) {
            instance = new CallAudioRecorder(context);
        }

        return instance;
    }

    public void startRecording(String phoneNumber, Date date) {
//        File dir = Environment.getExternalStorageDirectory();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy (HH'h' mm'm' ss's')");
        String todayDate = dateFormat.format(date);

        File filePath = new File(Environment.getExternalStorageDirectory() + "/PhoneBook" +
                "/" + phoneNumber);
        boolean success = true;
//        try {
//            audiofile = File.createTempFile("AudioRecorder/pesho", ".3gp", dir);
            if(!filePath.exists()) {
                success = filePath.mkdirs();
            }

            if (success) {
                mAudioFile = new File(filePath, todayDate + ".3gp");
            }

//            if (filePath.exists()) {
//                mAudioFile = File.createTempFile("test", ".3gp",filePath);
//            } else {
//                filePath.mkdir();
//                mAudioFile = File.createTempFile("test", ".3gp",filePath);
//            }
//        } catch (IOException e) {
//            Log.e(TAG, "external storage access error");
//            return;
//        }


        //Creating MediaRecorder and specifying audio source, output format, encoder & output format
        mRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mRecorder.setOutputFile(mAudioFile.getAbsolutePath());
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mRecorder.start();
    }

    public void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        addRecordingToMediaLibrary();
    }

    protected void addRecordingToMediaLibrary() {
        //creating content values of size 4
        ContentValues values = new ContentValues(4);
        long current = System.currentTimeMillis();
        Date date = new Date(current);
        String strDate = date.toString();


        String name = mAudioFile.getName();
        values.put(MediaStore.Audio.Media.TITLE, "audio" + name);
        values.put(MediaStore.Audio.Media.DATE_ADDED, strDate);
        values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/3gpp");
        values.put(MediaStore.Audio.Media.DATA, mAudioFile.getAbsolutePath());

        //creating content resolver and storing it in the external content uri
        ContentResolver contentResolver = mContext.getContentResolver();
        Uri base = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Uri newUri = contentResolver.insert(base, values);

        //sending broadcast message to scan the media file so that it can be available
        mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, newUri));
        Toast.makeText(mContext, "Added File " + newUri, Toast.LENGTH_LONG).show();
    }
}
