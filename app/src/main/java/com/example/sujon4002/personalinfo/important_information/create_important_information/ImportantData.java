package com.example.sujon4002.personalinfo.important_information.create_important_information;

import java.io.Serializable;

public class ImportantData implements Serializable {
    private static final long serialVersionUID = -7060210544600464481L;
    private long id;
    private String type=null;
    private  String name=null;
    private String relation=null;
    private String date=null;
    private String description=null;
    public ImportantData(long id, String type, String name, String relation, String date, String description)
    {
        this.id = id;
        this.type = type;
        this.name = name;
        this.relation = relation;
        this.date = date;
        this.description  = description;
    }
    public ImportantData()
    {

    }
    public long getId(){
        return this.id;
    }
    public void setId(long id){
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getRelation() {
        return relation;
    }
    public void setRelation(String relation)
    {
        this.relation = relation;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date)
    {
        this.date=date;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
}