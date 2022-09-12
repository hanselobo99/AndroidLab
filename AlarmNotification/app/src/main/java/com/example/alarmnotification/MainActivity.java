package com.example.alarmnotification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button addBtn;
    TextView dateTxt, timeTxt;
    Calendar cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateTxt = findViewById(R.id.dateTxt);
        timeTxt = findViewById(R.id.timeTxt);
        addBtn = findViewById(R.id.button);
        cal = Calendar.getInstance();
        dateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        cal.set(Calendar.DAY_OF_MONTH,day);
                        cal.set(Calendar.MONTH,month);
                        cal.set(Calendar.YEAR,year);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        dateTxt.setText(dateFormat.format(cal.getTime()));
                    }
                };
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, dateSetListener, year, month, day);
                datePickerDialog.show();
            }
        });

        timeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        cal.set(Calendar.HOUR_OF_DAY,selectedHour);
                        cal.set(Calendar.MINUTE,selectedMinute);
                        SimpleDateFormat timeFormat = new SimpleDateFormat("hh : mm a");
                        timeTxt.setText(timeFormat.format(cal.getTime()));
                    }
                };
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute =cal.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, onTimeSetListener, hour, minute, true);
                timePickerDialog.show();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AlarmReceiver.class);
                PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this,123,intent,PendingIntent.FLAG_MUTABLE);//0
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC,cal.getTimeInMillis(),pi);
                Toast.makeText(MainActivity.this,"The Date and Time You chose is "+cal.getTime().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}