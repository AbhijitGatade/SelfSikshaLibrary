package com.igaptechnologies.mysslibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.igaptechnologies.mysslibrary.general.LoginActivity;
import com.igaptechnologies.mysslibrary.igap.DBClass;
import com.igaptechnologies.mysslibrary.igap.Utilities;
import com.igaptechnologies.mysslibrary.teacher.TeacherHomeActivity;
import com.igaptechnologies.mysslibrary.user.HomeActivity;

import mysslibrary.R;

public class DBActivity extends AppCompatActivity {

    Utilities utilities = new Utilities();
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbactivity);

        createDatabase();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String query = "SELECT * FROM Configuration";
                Intent intent = new Intent(DBActivity.this, LoginActivity.class);
                if (DBClass.checkIfRecordExist(query)) {
                    query = "SELECT CValue FROM Configuration WHERE CName = 'type'";
                    if(DBClass.getSingleValue(query).equals("teacher"))
                        intent = new Intent(getApplicationContext(), TeacherHomeActivity.class);
                    else
                        intent = new Intent(getApplicationContext(), HomeActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    public void createDatabase() {
        String query;
        DBClass.database = openOrCreateDatabase(DBClass.dbname, MODE_PRIVATE, null);
        query = "CREATE TABLE IF NOT EXISTS Configuration(CName VARCHAR, CValue VARCHAR);";
        DBClass.execNonQuery(query);
        query = "CREATE TABLE IF NOT EXISTS Sliders(URL VARCHAR);";
        DBClass.execNonQuery(query);
        query = "CREATE TABLE IF NOT EXISTS NotificationSound(CName VARCHAR, CValue VARCHAR);";
        DBClass.execNonQuery(query);
        query = "CREATE TABLE IF NOT EXISTS Notifications(Id INTEGER PRIMARY KEY AUTOINCREMENT, Title VARCHAR, Notification VARCHAR, NotificationDateTime VARCHAR);";
        DBClass.execNonQuery(query);
        query = "SELECT * FROM NotificationSound";
        if(!DBClass.checkIfRecordExist(query))
        {
            query = "INSERT INTO NotificationSound(CName, CValue) VALUES('Sound', 'ON')";
            DBClass.execNonQuery(query);
        }
    }
}