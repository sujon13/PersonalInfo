package com.example.sujon4002.personalinfo.important_information.show_important_information;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sujon4002.personalinfo.R;
import com.example.sujon4002.personalinfo.important_information.create_important_information.ImportantData;

public class DetailImportantInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_important_information);
        showTestData();
    }
    public void showTestData()
    {
        Intent intent = getIntent();
        ImportantData data = (ImportantData) intent.getSerializableExtra("SERIALIZE_DATA");

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.testId);
        textView.setText(data.getType());
    }
}
