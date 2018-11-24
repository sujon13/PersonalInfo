package com.example.sujon4002.personalinfo.period_info.show_period_information;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sujon4002.personalinfo.R;
import com.example.sujon4002.personalinfo.important_information.create_important_information.ImportantData;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sujon4002.personalinfo.R;
import com.example.sujon4002.personalinfo.important_information.create_important_information.ImportantData;
import com.example.sujon4002.personalinfo.period_info.create_period_information.PeriodData;

import java.util.ArrayList;
import java.util.HashMap;

public class PeriodInformationListAdapter extends ArrayAdapter<PeriodData> {
    //to reference the Activity
    private final Activity context;
    HashMap<Integer,String> integerStringHashMap = new HashMap<>();
    //
    private ArrayList<PeriodData> list = new ArrayList<>();
    public PeriodInformationListAdapter(Activity context, ArrayList<PeriodData>list){

        super(context, R.layout.period_information_list_view ,list);
        this.context=context;
        this.list=list;
        createHashMap();

    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.period_information_list_view, null,true);
        PeriodData periodData=list.get(position);
        //this code gets references to objects in the listview_row.xml file
        TextView startDateTextView = rowView.findViewById(R.id.start_date_id);
        TextView endDateTextView = rowView.findViewById(R.id.end_date_id);
        TextView descriptionTextView = rowView.findViewById(R.id.description_id);

        //string type date to printable date conversion
        String[] startDate = periodData.getStartDate().split("-");
        String day = startDate[0];
        String month = integerStringHashMap.get(Integer.valueOf(startDate[1]));
        String year = startDate[2];
        String text_for_show = "Start: "+day+"th "+month+" "+year;
        startDateTextView.setText(text_for_show);

        String[] endDate = periodData.getEndDate().split("-");
        day = startDate[0];
        month = integerStringHashMap.get(Integer.valueOf(startDate[1]));
        year = startDate[2];
        text_for_show = "End: "+day+"th "+month+" "+year;
        endDateTextView.setText(text_for_show);

        text_for_show = periodData.getDescription();
        descriptionTextView.setText(text_for_show);

        return rowView;

    };
    public void createHashMap()
    {
        integerStringHashMap.put(0,"January");
        integerStringHashMap.put(1,"February");
        integerStringHashMap.put(2,"March");
        integerStringHashMap.put(3,"April");
        integerStringHashMap.put(4,"May");
        integerStringHashMap.put(5,"June");
        integerStringHashMap.put(6,"July");
        integerStringHashMap.put(7,"August");
        integerStringHashMap.put(8,"September");
        integerStringHashMap.put(9,"October");
        integerStringHashMap.put(10,"November");
        integerStringHashMap.put(11,"December");

    }
}