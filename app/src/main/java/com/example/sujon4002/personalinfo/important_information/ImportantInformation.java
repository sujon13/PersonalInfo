package com.example.sujon4002.personalinfo.important_information;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sujon4002.personalinfo.R;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImportantInformation extends AppCompatActivity implements ImportantDataCreateListener {
    ListView lv;
    ArrayAdapter<String> adapter;
    Crud crud = new Crud();
    Dialog d;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important_information);
        showEditDialog();

        //showMessage();
       /* Toolbar toolbar = findViewById(R.id.toolbar);
        try{
            setSupportActionBar(toolbar);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(ImportantInformation.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        lv = findViewById(R.id.lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(d != null) {
                    if(!d.isShowing())
                    {
                        displayInputDialog(i);
                    }else
                    {
                        d.dismiss();
                    }
                }
            }
        });

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayInputDialog(-1);
            }
        });*/
    }
    private void displayInputDialog(final int pos)
    {
        d = new Dialog(this);
        d.setTitle("LISTVIEW CRUD");
        d.setContentView(R.layout.input_dialog);

        final EditText nameEditTxt = d.findViewById(R.id.nameEditText);
        //final EditText idEditTExt = d.findViewById(R.id.idEditText);
        Button addBtn= d.findViewById(R.id.addBtn);
        Button updateBtn = d.findViewById(R.id.updateBtn);
        Button deleteBtn = d.findViewById(R.id.deleteBtn);

        if(pos == -1)
        {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }else
        {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
            nameEditTxt.setText(crud.getNames().get(pos));
        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                String name=nameEditTxt.getText().toString();

                //VALIDATE
                if(name.length()>0)
                {
                    //save
                    crud.save(name);
                    nameEditTxt.setText("");
                    adapter = new ArrayAdapter<>(ImportantInformation.this,android.R.layout.simple_list_item_1,crud.getNames());
                    lv.setAdapter(adapter);


                }else
                {
                    //Toast.makeText(getApplicationContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(ImportantInformation.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                String newName=nameEditTxt.getText().toString();

                //VALIDATE
                if(newName.length()>0)
                {
                    //save
                    if(crud.update(pos,newName))
                    {
                        nameEditTxt.setText(newName);
                        adapter=new ArrayAdapter<>(ImportantInformation.this,android.R.layout.simple_list_item_1,crud.getNames());
                        lv.setAdapter(adapter);

                    }

                }else
                {
                    Toast.makeText(ImportantInformation.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //DELETE
                if( crud.delete(pos))
                {
                    nameEditTxt.setText("");
                    adapter=new ArrayAdapter<>(ImportantInformation.this,android.R.layout.simple_list_item_1,crud.getNames());
                    lv.setAdapter(adapter);

                }
            }
        });

        d.show();
    }

    @Override
    public void onImportantDataCreated(ImportantData importantData) {

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
