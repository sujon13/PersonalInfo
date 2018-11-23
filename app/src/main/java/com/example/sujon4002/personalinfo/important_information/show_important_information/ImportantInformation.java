package com.example.sujon4002.personalinfo.important_information.show_important_information;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.sujon4002.personalinfo.MainActivity;
import com.example.sujon4002.personalinfo.R;
import com.example.sujon4002.personalinfo.important_information.create_important_information.*;
import com.example.sujon4002.personalinfo.model.DatabaseQueryClass;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ImportantInformation extends AppCompatActivity implements ImportantDataCreateListener {
    private ListView listView;
    private ArrayList<ImportantData> importantDataArrayList = new ArrayList<>();
    Dialog d;
    private ImportantInformationListAdapter listAdapter;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important_information);

        //showEditDialog();

        Toolbar toolbar = findViewById(R.id.toolbar);
        try{
            setSupportActionBar(toolbar);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(ImportantInformation.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        //it should be called after toolbar initializing
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.list_item);
        showList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(ImportantInformation.this, DetailImportantInformation.class);
                //Object selectedItem =  parent.getItemAtPosition(position);
                ImportantData data = importantDataArrayList.get(position);

                intent.putExtra("SERIALIZE_DATA", data);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showEditDialog();
                return true;
            }
        });

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
            importantDataArrayList.addAll(databaseQueryClass.getAllImportantInformation() );
            listAdapter = new ImportantInformationListAdapter(this,importantDataArrayList);
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
    @Override
    public void onImportantDataCreated(ImportantData importantData) {
        importantDataArrayList.add(importantData);
        listAdapter.notifyDataSetChanged();
        Toast.makeText(ImportantInformation.this, importantData.getType()+" "+importantData.getName(), Toast.LENGTH_LONG).show();
    }
    private void showEditDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ImportantInformationDialogFragment importantInformationDialogFragment = ImportantInformationDialogFragment.newInstance("Some Title", this);
        importantInformationDialogFragment.show(fragmentManager, "fragment_edit_information");
    }
    public void showMessage()
    {
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra("EXTRA_MESSAGE");

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }
}
