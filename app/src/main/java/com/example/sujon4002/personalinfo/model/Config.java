package com.example.sujon4002.personalinfo.model;

public class Config {

    public static final String DATABASE_NAME = "personal-db";

    //column names of important_information table
    public static final String TABLE_IMPORTANT_INFORMATION = "important_information";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_RELATION = "relation";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_DESCRIPTION = "description";

    //column names of period_information table
    public static final String TABLE_PERIOD_INFORMATION = "period_information";
    public static final String COLUMN_PERIOD_ID = "_id";
    public static final String COLUMN_PERIOD_START_DATE = "start_date";
    public static final String COLUMN_PERIOD_END_DATE = "end_date";
    public static final String COLUMN_PERIOD_DESCRIPTION = "description";

    //others for general purpose key-value pair data
    public static final String TITLE = "title";
    public static final String CREATE_IMPORTANT_INFORMATION = "create_important_information";
    public static final String UPDATE_IMPORTANT_INFORMATION  = "update_important_information";

    public static final String CREATE_PERIOD_INFORMATION = "create_period_information";
}
