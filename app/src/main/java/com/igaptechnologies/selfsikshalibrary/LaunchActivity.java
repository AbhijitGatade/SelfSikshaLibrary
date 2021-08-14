package com.igaptechnologies.selfsikshalibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.igaptechnologies.mysslibrary.DBActivity;
import com.igaptechnologies.mysslibrary.igap.Configuration;

public class LaunchActivity extends AppCompatActivity {

    public static int trainerid = 1;
    public static String appPkgName = "com.igaptechnologies.mysslibrary";
    public static String channelId = "AIzaSyATi_2vN8yfhk_MaAtYgdWfTO1wpB99wE0";
    public static String contactno = "9561320192";
    public static String whatsappno = "919561320192";
    public static String email = "info.selfsiksha@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_launch);

        Intent appIntent =  getIntent();

        Configuration.trainerid = appIntent.getIntExtra("trainerid", LaunchActivity.trainerid);
        Configuration.appPkgName = "com.igaptechnologies.mysslibrary";
        Configuration.channelId = "AIzaSyATi_2vN8yfhk_MaAtYgdWfTO1wpB99wE0";
        Configuration.contactno = appIntent.getStringExtra("contactno");
        Configuration.whatsappno = appIntent.getStringExtra("whatsappno");
        Configuration.email = appIntent.getStringExtra("email");
        if(Configuration.contactno == null)
        {
            Configuration.contactno = LaunchActivity.contactno;
            Configuration.whatsappno = LaunchActivity.whatsappno;
            Configuration.email = LaunchActivity.email;
        }
        Intent intent = new Intent(this, DBActivity.class);
        startActivity(intent);
        this.finish();
    }
}