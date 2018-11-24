package com.example.sujon4002.personalinfo.period_info.create_period_information;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.TextView;

import com.example.sujon4002.personalinfo.R;
import com.example.sujon4002.personalinfo.model.Config;
import com.example.sujon4002.personalinfo.model.DatabaseQueryClass;

import java.util.Calendar;

// ...

public class EditNameDialogFragment extends DialogFragment {
    private  static PeriodDataCreateListener periodDataCreateListener;
    private Button startButton;
    private Button endButton;
    private TextView startDateTextView;
    private TextView endDateTextView;
    private EditText descriptionEditText=null;
    private Button addButton;
    private Button cancelButton;
    private String startDate;
    private String endDate;
    private String description;

    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;


    public EditNameDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static EditNameDialogFragment newInstance(String title, PeriodDataCreateListener listener) {
        periodDataCreateListener = listener;
        EditNameDialogFragment fragment = new EditNameDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        //EditNameDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_period_info, container,false);

        startButton = view.findViewById(R.id.start_btn_id);
        startDateTextView = view.findViewById(R.id.textStartDateId);
        endButton = view.findViewById(R.id.end_btn_id);
        endDateTextView = view.findViewById(R.id.textEndDateId);
        descriptionEditText = view.findViewById(R.id.descriptionId);
        addButton = view.findViewById(R.id.add_Btn);
        cancelButton = view.findViewById(R.id.cancel_Btn);

        String title = getArguments().getString(Config.TITLE, "Enter information");
        getDialog().setTitle(title);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                startDateTextView.setText(String.valueOf(day)+"-"+String.valueOf(month)+
                                        "-"+String.valueOf(year));
                            }
                        },year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                endDateTextView.setText(String.valueOf(day)+"-"+String.valueOf(month)+
                                        "-"+String.valueOf(year));
                            }
                        },year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDate = startDateTextView.getText().toString();
                endDate = endDateTextView.getText().toString();
                //registrationNumber = Integer.parseInt(registrationEditText.getText().toString());
                description = descriptionEditText.getText().toString();

                PeriodData periodData = new PeriodData(-1, startDate, endDate, description);

                DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(getContext());

                long id = databaseQueryClass.insertPeriodInformation(periodData);

                if(id>0){
                    periodData.setId(id);
                    periodDataCreateListener.onPeriodDataCreated(periodData);
                    getDialog().dismiss();
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