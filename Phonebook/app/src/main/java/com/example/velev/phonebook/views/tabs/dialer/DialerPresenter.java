package com.example.velev.phonebook.views.tabs.dialer;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;

import com.example.velev.phonebook.data.models.CallModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

public class DialerPresenter {

    // this is the synchronous method which retrieve the callLog
    public List<CallModel> getCallLogDetails(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return null;
        }
        String[] strFields = {
                CallLog.Calls.CACHED_NAME,
                CallLog.Calls.NUMBER,
                CallLog.Calls.TYPE,
                CallLog.Calls.DATE,
                CallLog.Calls.DURATION
        };

        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, strFields ,
                null, null, null);
        List<CallModel> calls = new ArrayList<>();

        cursor.moveToLast();
        cursor.moveToNext();
        while (cursor.moveToPrevious()) {
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

            CallModel call = new CallModel(name, number, date.toString(), duration, callType);
            calls.add(call);
        }

        cursor.close();
        //Collections.reverse(calls);
        return calls;
    }

    public Observable<List<CallModel>> getCallLog(Context context) {
        final Context mContext = context;
        return Observable.create(new ObservableOnSubscribe<List<CallModel>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<CallModel>> e) throws Exception {
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //return null;
                }
                String[] strFields = {
                        CallLog.Calls.CACHED_NAME,
                        CallLog.Calls.NUMBER,
                        CallLog.Calls.TYPE,
                        CallLog.Calls.DATE,
                        CallLog.Calls.DURATION
                };


                Cursor cursor = mContext.getContentResolver().query(CallLog.Calls.CONTENT_URI, strFields ,
                        null, null, null);


                List<CallModel> calls = new ArrayList<>();

                cursor.moveToLast();
                cursor.moveToNext();

                final int nameIndex = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
                final int numberIndex = cursor.getColumnIndex(CallLog.Calls.NUMBER);
                final int durationIndex = cursor.getColumnIndex(CallLog.Calls.DURATION);
                final int callDateIndex = cursor.getColumnIndex(CallLog.Calls.DATE);
                final int callTypeIndex = cursor.getColumnIndex(CallLog.Calls.TYPE);

                while (cursor.moveToPrevious()) {
                    String name = cursor.getString(nameIndex);
                    String number = cursor.getString(numberIndex);
                    String duration = cursor.getString(durationIndex);
                    String callDate = cursor.getString(callDateIndex);
                    Date date = new Date(Long.valueOf(callDate));
                    String indexCallType = cursor.getString(callTypeIndex);

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

                    CallModel call = new CallModel(name, number, date.toString(), duration, callType);

                    calls.add(call);
                    e.onNext(calls);
                }

                cursor.close();
                e.onComplete();
            }
        });
    }
}