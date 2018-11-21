package com.example.sujon4002.personalinfo.important_information;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.sujon4002.personalinfo.R;
import com.example.sujon4002.personalinfo.important_information.ImportantInformation;
import com.example.sujon4002.personalinfo.model.Config;
import com.example.sujon4002.personalinfo.model.DatabaseQueryClass;

// ...

public class ImportantInformationDialogFragment extends  DialogFragment {

    private  static ImportantDataCreateListener importantDataCreateListener;
    private EditText typeEditText=null;
    private EditText nameEditText;
    private EditText relationEditText=null;
    private EditText descriptionEditText=null;
    private Button addButton;
    private Button cancelButton;
    private String type;
    private String name;
    private String relation;
    private  String date;
    private String description;


    public ImportantInformationDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static ImportantInformationDialogFragment newInstance(String title, ImportantDataCreateListener listener) {
        importantDataCreateListener = listener;
        ImportantInformationDialogFragment fragment = new ImportantInformationDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        //EditNameDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_information, container,false);

        typeEditText = view.findViewById(R.id.typeId);
        nameEditText = view.findViewById(R.id.nameId);
        relationEditText = view.findViewById(R.id.relationId);
        descriptionEditText = view.findViewById(R.id.descriptionId);
        addButton = view.findViewById(R.id.add_Btn);
        cancelButton = view.findViewById(R.id.cancel_Btn);

        String title = getArguments().getString(Config.TITLE, "Enter information");
        getDialog().setTitle(title);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = typeEditText.getText().toString();
                if(type.length()==0)type=null;
                //registrationNumber = Integer.parseInt(registrationEditText.getText().toString());
                name = nameEditText.getText().toString();
                if(name.length()==0)name=null;
                relation = relationEditText.getText().toString();
                date = "11 ";
                description = descriptionEditText.getText().toString();

                ImportantData importantData = new ImportantData(-1, type, name, relation, date, description);

                DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(getContext());

                long id = databaseQueryClass.insertStudent(importantData);

                if(id > 0)
                {
                    importantData.setId(id);
                    importantDataCreateListener.onImportantDataCreated(importantData);
                    getDialog().dismiss();
                }
                else{
                    showAlertDialog();
                    typeEditText.setText("");
                    nameEditText.setText("");
                    relationEditText.setText("");
                    descriptionEditText.setText("");

                }


            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        return view;
    }
    public void showAlertDialog()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("WARNING!!");
        alertDialog.setMessage("Type or Name must not be null");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view

        // Fetch arguments from bundle and set title
        // Show soft keyboard automatically and request focus to field

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

}