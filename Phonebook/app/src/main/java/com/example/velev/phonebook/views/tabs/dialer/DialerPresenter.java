package com.example.velev.phonebook.views.tabs.dialer;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;

import com.example.velev.phonebook.data.models.CallModel;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DialerPresenter {


    public List<CallModel> getCallLogDetails(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return null;
        }
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
                null, null, null);


        List<CallModel> calls = new ArrayList<>();

        while (cursor.moveToNext()) {
            //String name, String phoneNumber, Date callDateTime, String duration, CallType callType
            String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
            String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
            String duration = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));
            String callDate = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));
            Date date = new Date(Long.valueOf(callDate));
            String indexCallType = cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE));

            String callType = "";
            switch (indexCallType) {
                case "1":
                    callType = "INCOMING";
                    break;
                case "2":
                    callType = "OUTGOING";
                    break;
                case "3":
                    callType = "MISSED";
                    break;
            }

            CallModel call = new CallModel(name, number, date, duration, callType);
            calls.add(call);
        }

        Collections.reverse(calls);

        return calls;
    }
}


