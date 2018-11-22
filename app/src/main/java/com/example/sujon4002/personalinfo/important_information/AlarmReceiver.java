package com.example.sujon4002.personalinfo.important_information;

//import BroadcastReceiver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.example.sujon4002.personalinfo.R;

public class AlarmReceiver extends BroadcastReceiver {

    MediaPlayer mp;

    @Override
    public void onReceive(Context context, Intent intent) {
        try{
            mp = MediaPlayer.create(context, R.raw.alarm1);
            mp.start();
        }catch (Exception e)
        {
            Toast.makeText(context, "Alarm...."+e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();
    }

}
