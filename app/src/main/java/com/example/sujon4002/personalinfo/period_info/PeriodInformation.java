package com.example.sujon4002.personalinfo.period_info;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sujon4002.personalinfo.R;

public class PeriodInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_information);
        showMessage();
    }
    public void showMessage()
    {
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra("EXTRA_MESSAGE");

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.messageId);
        textView.setText(message);
    }
}
