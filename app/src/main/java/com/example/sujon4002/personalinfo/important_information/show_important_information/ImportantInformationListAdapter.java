package com.example.sujon4002.personalinfo.important_information.show_important_information;

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

public class ImportantInformationListAdapter extends ArrayAdapter<ImportantData> {
    //to reference the Activity
    private final Activity context;
    HashMap<Integer,String> integerStringHashMap = new HashMap<>();
    //
    private ArrayList<ImportantData>list = new ArrayList<>();
    public ImportantInformationListAdapter(Activity context, ArrayList<ImportantData>list){

        super(context, R.layout.important_information_list_view ,list);
        this.context=context;
        this.list=list;
        createHashMap();

    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.important_information_list_view, null,true);
        ImportantData importantDataList=list.get(position);
        //this code gets references to objects in the listview_row.xml file
        TextView shortInfo = rowView.findViewById(R.id.shortInfoId);

        //string type date to printable date conversion
        String[] date = importantDataList.getDate().split("-");
        String day = date[0];
        String month = integerStringHashMap.get(Integer.valueOf(date[1]));
        String year = date[2];
        String text_for_show = importantDataList.getName()+"'s "+importantDataList.getType()+" on "+day;

        switch(Integer.valueOf(day)){
            case 1:
                text_for_show+="st ";
                break;
            case 2:
                text_for_show+="nd ";
                break;
            case 3:
                text_for_show+="rd ";
                break;
            default:
                text_for_show+="th ";

        }
        text_for_show+=month+" "+year;
        shortInfo.setText(text_for_show);

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