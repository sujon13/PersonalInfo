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

public class ImportantInformationListAdapter extends ArrayAdapter<ImportantData> {
    //to reference the Activity
    private final Activity context;

    //
    private ArrayList<ImportantData>list = new ArrayList<>();
    public ImportantInformationListAdapter(Activity context, ArrayList<ImportantData>list){

        super(context, R.layout.important_information_list_view ,list);
        this.context=context;
        this.list=list;


    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.important_information_list_view, null,true);
        ImportantData importantDataList=list.get(position);
        //this code gets references to objects in the listview_row.xml file
        TextView listType = rowView.findViewById(R.id.listTypeId);
        TextView listName =  rowView.findViewById(R.id.listNameId);
        TextView listRelation =  rowView.findViewById(R.id.listRelationId);
        TextView listDescription =  rowView.findViewById(R.id.listDescriptionId);

        //this code sets the values of the objects to values from the arrays
        listType.setText("Type: "+String.valueOf(importantDataList.getType()));
        listName.setText("Name : "+importantDataList.getName());
        listRelation.setText("Relation: "+importantDataList.getRelation());
        listDescription.setText("Description: "+importantDataList.getDescription());
        //formatting date
        /*String date=helplist.getDate();
        String day=date.substring(8,10);
        String month=date.substring(5,7);
        String year=date.substring(0,4);
        textView.setText("Date: "+day+"-"+month+"-"+year);*/

        return rowView;

    };
}