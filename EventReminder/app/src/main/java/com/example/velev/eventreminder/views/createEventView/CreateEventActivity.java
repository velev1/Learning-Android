package com.example.velev.eventreminder.views.createEventView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.velev.eventreminder.R;
import com.example.velev.eventreminder.views.displayEventView.DisplayEventActivity;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.util.Calendar;

public class CreateEventActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, CreateEventContract.View{

    private TextView tvDate;
    private TextView tvTime;
    private EditText etDescription;
    private EditText etName;
    private ViewGroup vgSetDate;
    private ViewGroup vgSetTime;
    private Button btnCreateEvent;

    private DatePickerDialog dialogDate;
    private TimePickerDialog dialogTime;
    private CreateEventViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        this.viewModel = new CreateEventViewModel(this);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String strMonth = (new DateFormatSymbols().getMonths()[month]).substring(0,3);

        this.dialogDate = new DatePickerDialog(this, CreateEventActivity.this, year, month, day);

        String strDate = String.valueOf(day) + "/" + strMonth + "/" + String.valueOf(year);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvDate.setText(strDate);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        String strHour = addLeadingZero(hour);
        String strMinute = addLeadingZero(minute);
        String time  = strHour + ":" + strMinute;
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvTime.setText(time);

        vgSetDate = (ViewGroup) findViewById(R.id.vg_set_date);
        vgSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDate.show();
            }
        });

        this.dialogTime = new TimePickerDialog(this, CreateEventActivity.this, hour, minute, true);

        vgSetTime = (ViewGroup) findViewById(R.id.vg_set_time);
        vgSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTime.show();
            }
        });

        etName = (EditText) findViewById(R.id.et_name);
        etDescription = (EditText) findViewById(R.id.et_description);

        btnCreateEvent = (Button) findViewById(R.id.btn_create_event);
        onInsertEvent();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String strHour = addLeadingZero(hourOfDay);
        String strMinute = addLeadingZero(minute);
        String time  = strHour + ":" + strMinute;
        tvTime.setText(time);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String strMonth = (new DateFormatSymbols().getMonths()[month]).substring(0,3);
        String strDate = String.valueOf(dayOfMonth) + "-" + strMonth + "-" + String.valueOf(year);
        tvDate.setText(strDate);
    }

    @Override
    public void onInsertEvent(){
        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String strDate = tvDate.getText().toString() +
                        " " + tvTime.getText().toString();
                String description = etDescription.getText().toString();
                boolean isInserted = false;
                try {
                    isInserted = CreateEventActivity.this.viewModel.insertEvent(name, strDate, description);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(isInserted) {
                    Toast.makeText(CreateEventActivity.this, "Event created.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateEventActivity.this, "insertion is not successful.", Toast.LENGTH_SHORT).show();
                }

                openDisplayEventsView();
            }
        });
    }

    @Override
    public void openDisplayEventsView() {
        Intent intent = new Intent(this, DisplayEventActivity.class);
        startActivity(intent);
    }

    private String addLeadingZero(int number) {
        if(number > 9) {
            return String.valueOf(number);
        } else {
            return "0" + number;
        }
    }
}
