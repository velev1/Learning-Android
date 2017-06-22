package com.example.velev.phonebook.views.callLogDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.velev.phonebook.R;
import com.example.velev.phonebook.data.models.CallModel;

public class CallLogDetails extends AppCompatActivity {
    private final static String CONTACT_KEY = "contact_key";

    private TextView tvName;
    private TextView tvCallType;
    private TextView tvDate;
    private TextView tvDuration;
    private CallLogDetailsPresenter presenter;

    private CallModel currentCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log_details);

        this.presenter = new CallLogDetailsPresenter();

        this.currentCall = (CallModel) getIntent().getSerializableExtra(CONTACT_KEY);

        this.tvName = (TextView) this.findViewById(R.id.tv_name);
        this.tvName.setText(this.currentCall.getName());

        this.tvCallType = (TextView) this.findViewById(R.id.tv_call_type);
        this.tvCallType.setText(this.currentCall.getCallType());

        this.tvDate = (TextView) this.findViewById(R.id.tv_date);
        this.tvDate.setText(this.currentCall.getCallDateTime());

        this.tvDuration = (TextView) this.findViewById(R.id.tv_duration);
        this.tvDuration.setText(
                this.presenter.parseCallDuration(this.currentCall.getDuration())
        );
    }
}
