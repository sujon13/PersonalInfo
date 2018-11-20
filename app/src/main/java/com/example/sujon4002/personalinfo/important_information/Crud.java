package com.example.sujon4002.personalinfo.important_information;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Crud {

    private ArrayList<String> names = new ArrayList<>();
    public void save(String name)
    {
        names.add(name);

    }

    public ArrayList<String> getNames()
    {

        return  names;
    }
    public void setNames(List<String> list)
    {
        names = new ArrayList<String>(list);
    }
    public Boolean update(int position,String newName)
    {
        try {
            names.remove(position);
            names.add(position,newName);

            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(int position)
    {
        try {
            names.remove(position);

            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;

        }
    }
}