package com.example.velev.eventreminder.views.eventDetailsViewTest;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.velev.eventreminder.R;
import com.example.velev.eventreminder.data.models.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventDetailsActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvDate;
    private TextView tvTimer;
    private TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_event);

        Event event = (Event) getIntent().getSerializableExtra("EVENT_KEY");

        tvName = (TextView) findViewById(R.id.tv_name);
        tvName.setText(event.getName());

        tvDescription = (TextView) findViewById(R.id.tv_description);
        tvDescription.setText(event.getDescription());
        tvDate = (TextView) findViewById(R.id.tv_starting_date);

        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        String formatedDate = dateFormat.format(event.getDate());
        tvDate.setText(formatedDate);

        tvTimer = (TextView) findViewById(R.id.tv_timer);

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        Date startingDate = event.getDate();

        final long diffInMilliSeconds = startingDate.getTime() - today.getTime();
        final EventDetailsViewModel viewModel = new EventDetailsViewModel();
        new CountDownTimer(diffInMilliSeconds, 1000) {
            long mSec = diffInMilliSeconds;

            public void onTick(long millisUntilFinished) {
                tvTimer.setText(viewModel.convertMilliSeconds(mSec));
                mSec -= 1000;
            }

            public void onFinish() {
                tvTimer.setText("finished!");
            }
        }.start();



//        EventDetailsViewModel viewModel = new EventDetailsViewModel();
//        viewModel.setTimer(diffInMilliSeconds)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(@NonNull String s) throws Exception {
//                        if(s.equals("finished!")){
//                            tvTimer.setTextColor(getResources().getColor(R.color.colorDanger));
//                        }
//                        tvTimer.setText(s);
//
//                    }
//                });
    }
}