package com.example.sujon4002.personalinfo.period_info.show_period_information;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sujon4002.personalinfo.R;
import com.example.sujon4002.personalinfo.important_information.show_important_information.ImportantInformation;
import com.example.sujon4002.personalinfo.important_information.show_important_information.ImportantInformationListAdapter;
import com.example.sujon4002.personalinfo.model.DatabaseQueryClass;
import com.example.sujon4002.personalinfo.period_info.create_period_information.EditNameDialogFragment;
import com.example.sujon4002.personalinfo.period_info.create_period_information.PeriodData;
import com.example.sujon4002.personalinfo.period_info.create_period_information.PeriodDataCreateListener;

import java.util.ArrayList;

public class PeriodInformation extends AppCompatActivity implements PeriodDataCreateListener {

    private ListView listView;
    private ArrayList<PeriodData> periodDataArrayList = new ArrayList<>();
    private PeriodInformationListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_information);
        Toolbar toolbar = findViewById(R.id.toolbar);
        try{
            setSupportActionBar(toolbar);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(PeriodInformation.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = findViewById(R.id.period_list_item);
        showList();
        //showMessage();
        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //displayInputDialog(-1);
                showEditDialog();
            }
        });
    }
    private void showList()
    {
        DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(this);
        try{
            periodDataArrayList.addAll(databaseQueryClass.getAllPeriodInformation() );
            listAdapter = new PeriodInformationListAdapter(this,periodDataArrayList);
            listView.setAdapter(listAdapter);
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Size: ", Toast.LENGTH_LONG).show();
        }

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
    private void showEditDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        EditNameDialogFragment editNameDialogFragment = EditNameDialogFragment.newInstance("Some Title", this);
        editNameDialogFragment.show(fragmentManager, "fragment_edit_period_info");
    }
    @Override
    public void onPeriodDataCreated(PeriodData periodData)
    {
        periodDataArrayList.add(periodData);
        listAdapter.notifyDataSetChanged();
    }
    public void showMessage()
    {
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra("EXTRA_MESSAGE");

        // Capture the layout's TextView and set the string as its text
        //TextView textView = findViewById(R.id.messageId);
        //textView.setText(message);
    }
}
