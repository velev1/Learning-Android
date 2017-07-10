package com.example.velev.phonebook.views.tabs.dialer;

import android.content.Context;

import com.example.velev.phonebook.BasePresenter;
import com.example.velev.phonebook.BaseView;
import com.example.velev.phonebook.data.models.CallModel;

import java.util.List;

import io.reactivex.Observable;

public interface DialerContract {

    interface View extends BaseView<DialerContract.Presenter> {

        void openCallLogDetails();
    }

    interface Presenter extends BasePresenter<DialerContract.View>{

        Observable<List<CallModel>> getCallLog(Context context);
    }
}
