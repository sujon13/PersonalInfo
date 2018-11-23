package com.example.sujon4002.personalinfo.important_information.update_important_information;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.sujon4002.personalinfo.R;
import com.example.sujon4002.personalinfo.important_information.create_important_information.ImportantData;
import com.example.sujon4002.personalinfo.important_information.create_important_information.ImportantDataCreateListener;
import com.example.sujon4002.personalinfo.model.Config;
import com.example.sujon4002.personalinfo.model.DatabaseQueryClass;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.sujon4002.personalinfo.R;
import com.example.sujon4002.personalinfo.model.Config;
import com.example.sujon4002.personalinfo.model.DatabaseQueryClass;
// ...

public class ImportantInfoUpdateDiaFragment extends DialogFragment {

    private  static ImportantDataUpdateListener importantDataUpdateListener;
    private EditText typeEditText=null;
    private EditText nameEditText;
    private EditText relationEditText=null;
    private DatePicker datePicker;
    private EditText descriptionEditText=null;
    private Button updateButton;
    private Button cancelButton;
    private static int id;
    private String type;
    private String name;
    private String relation;
    private String date;
    private String description;


    public ImportantInfoUpdateDiaFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static com.example.sujon4002.personalinfo.important_information.update_important_information.
            ImportantInfoUpdateDiaFragment newInstance(int _id,String title, ImportantDataUpdateListener listener) {
        importantDataUpdateListener = listener;
        id = _id;
        com.example.sujon4002.personalinfo.important_information.update_important_information.
                ImportantInfoUpdateDiaFragment fragment = new com.example.sujon4002.personalinfo.important_information.
                update_important_information.ImportantInfoUpdateDiaFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        //EditNameDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_information, container,false);

        typeEditText = view.findViewById(R.id.typeId);
        nameEditText = view.findViewById(R.id.nameId);
        relationEditText = view.findViewById(R.id.relationId);
        datePicker = view.findViewById(R.id.datePicker1);
        descriptionEditText = view.findViewById(R.id.descriptionId);
        updateButton = view.findViewById(R.id.update_Btn);
        cancelButton = view.findViewById(R.id.cancel_Btn);

        String title = getArguments().getString(Config.TITLE, "Enter information");
        getDialog().setTitle(title);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = typeEditText.getText().toString();
                if(type.length()==0)type=null;
                //registrationNumber = Integer.parseInt(registrationEditText.getText().toString());
                name = nameEditText.getText().toString();
                if(name.length()==0)name=null;
                relation = relationEditText.getText().toString();
                date = Integer.toString(datePicker.getDayOfMonth())+"-" + Integer.toString(datePicker.getMonth())+
                        "-" + Integer.toString(datePicker.getYear());
                description = descriptionEditText.getText().toString();

                ImportantData importantData = new ImportantData(-1, type, name, relation, date, description);

                DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(getContext());

                long affectedRow = databaseQueryClass.updateImportantInformation(importantData);

                if(affectedRow > 0)
                {
                    importantData.setId(id);
                    importantDataUpdateListener.onDataUpdated(importantData);
                    getDialog().dismiss();
                }
                else showAlertDialog();
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
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            //noinspection ConstantConditions
            dialog.getWindow().setLayout(width, height);
        }
    }

}