package com.example.sujon4002.personalinfo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sujon4002.personalinfo.important_information.AlarmReceiver;
import com.example.sujon4002.personalinfo.important_information.show_important_information.ImportantInformation;
import com.example.sujon4002.personalinfo.period_info.show_period_information.PeriodInformation;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Context context;
    AlarmManager alarmMgr;
    PendingIntent alarmIntent;
    Button alarmButton;
    Button cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        alarmManage();

    }
    public void alarmManage()
    {
        alarmButton = findViewById(R.id.alarmButtonId);
        cancelButton = findViewById(R.id.cancelButtonId);

        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                // Set the alarm to start at 8:30 a.m.
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, 04);
                calendar.set(Calendar.MINUTE, 01);

                // setRepeating() lets you specify a precise custom interval--in this case,
                // 20 minutes.
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        1000 * 60 * 1, alarmIntent);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alarmMgr!= null) {
                    alarmMgr.cancel(alarmIntent);
                }
            }
        });
    }

    public void goPersonalInfo(View view)
    {
        Intent intent = new Intent(this, ImportantInformation.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        String temp="birthday";
        intent.putExtra("EXTRA_MESSAGE", temp);
        startActivity(intent);
    }
    public void goPeriodInfo(View view)
    {
        Intent intent = new Intent(this, PeriodInformation.class);
        String temp="period";
        intent.putExtra("EXTRA_MESSAGE", temp);
        try{
            startActivity(intent);
        }catch (Exception e)
        {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }



}
