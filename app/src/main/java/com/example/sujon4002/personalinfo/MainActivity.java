package com.example.sujon4002.personalinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.sujon4002.personalinfo.important_information.ImportantInformation;
import com.example.sujon4002.personalinfo.period_info.PeriodInformation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
