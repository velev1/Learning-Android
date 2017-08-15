package com.example.velev.customdialogdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by velev on 14.8.2017 г..
 */

public class CustomDialog extends AlertDialog {


    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        // TODO set onClick to the buttons and don't forget to dismiss()

        Button btnText = (Button) findViewById(R.id.btn_text);
        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "text", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        TextView btnCancel = (TextView) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // cancel();
            }
        });
    }
}
