package com.example.sujon4002.personalinfo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sujon4002.personalinfo.important_information.AlarmReceiver;
import com.example.sujon4002.personalinfo.important_information.show_important_information.ImportantInformation;
import com.example.sujon4002.personalinfo.period_info.PeriodInformation;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

    }
    public void startAlarm(View view)
    {
        AlarmManager alarmMgr;
        PendingIntent alarmIntent;

        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        // Set the alarm to start at 8:30 a.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 22);
        calendar.set(Calendar.MINUTE, 40);

        // setRepeating() lets you specify a precise custom interval--in this case,
        // 20 minutes.
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 2, alarmIntent);
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
        startActivity(intent);
    }



}
