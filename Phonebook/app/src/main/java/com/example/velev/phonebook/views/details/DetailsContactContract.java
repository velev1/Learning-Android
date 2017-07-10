package com.example.velev.phonebook.views.details;

import android.content.Context;

import com.example.velev.phonebook.BasePresenter;
import com.example.velev.phonebook.BaseView;

public interface DetailsContactContract {

    interface View extends BaseView<DetailsContactContract.Presenter> {

        void showEditDialog();

        void onEdit(String id, String name, String phoneNumber);

        void showDeleteAlert();

        void onDelete();

    }

    interface Presenter extends BasePresenter<DetailsContactContract.View> {

        Integer deleteContact(Context context, String id);

        boolean updateContact(Context context, String id, String name, String phoneNumber);
    }
}
