package com.example.sujon4002.personalinfo.important_information.show_important_information;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.sujon4002.personalinfo.R;
import com.example.sujon4002.personalinfo.important_information.create_important_information.ImportantData;

public class DetailImportantInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_important_information);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        showTestData();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
