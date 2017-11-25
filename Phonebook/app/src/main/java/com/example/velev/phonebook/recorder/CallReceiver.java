package com.example.velev.phonebook.recorder;

import android.content.Context;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by velev on 23.11.2017 г..
 */

public class CallReceiver extends PhonecallReceiver {

    private CallAudioRecorder mRecorder;

    @Override
    protected void onIncomingCallStarted(Context ctx, String number, Date start) {
        Toast.makeText(ctx , "Incomiiiiing", Toast.LENGTH_SHORT).show();
        mRecorder = CallAudioRecorder.getCallAudioRecorder(ctx);
        mRecorder.startRecording(number, start);
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {
        Toast.makeText(ctx , "Eeeend", Toast.LENGTH_SHORT).show();
        mRecorder = CallAudioRecorder.getCallAudioRecorder(ctx);
        mRecorder.stopRecording();
    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end) {
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start) {
    }
}
