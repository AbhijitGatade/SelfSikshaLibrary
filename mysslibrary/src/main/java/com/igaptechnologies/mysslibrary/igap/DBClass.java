package com.igaptechnologies.mysslibrary.igap;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Random;

public class DBClass extends SQLiteOpenHelper {

    public static String dbname = "SelfSiksha";
    public static String liveurl = "https://live.selfsiksha.com/";
    //public static String liveurl = "https://192.168.1.5:3000/";
    //public static String url = "http://192.168.1.5/selfsiksha.com/";
    public static String url = "https://selfsiksha.com/";
    public static String trainerpageurl = url + "trainerpage/" + Configuration.trainerid;
    public static String urlUserLogin = url + "authentication/login";
    public static String urlUserSignup = url + "authentication/signup";
    public static String urlsendRegisterOTP = url + "api/sendregisterotp";
    public static String urlUpdateProfile = url + "authentication/updateprofile";
    public static String urlForgotPassword = url + "authentication/forgotpassword";
    public static String urlUpdateFirebaseId = url + "authentication/updatefirebaseid";
    public static String urlSliders = url + "api/sliders";
    public static String urlNotifications = url + "api/notifications";
    public static String urlMyProducts = url + "api/myproducts";
    public static String urlProducts = url + "api/products";
    public static String urlProduct = url + "api/product";
    public static String urlPlaceOrder = url + "api/placeorder";
    public static String urlSuccessPayment = url + "api/successpayment";

    public static String urlCourse = url + "api/course";
    public static String urlTestSeries = url + "api/testseries";
    public static String urlTestDetails = url + "api/testdetails";
    public static String urlSubmitAnswer = url + "api/submit_test_answer";
    public static String urlFinishTest = url + "api/finishtest";
    public static String urlAttemptNewTest = url + "api/test_new_attempt";
    public static String urlAttemptTest = url + "api/attempttest";
    public static String urlCourseTest = url + "api/coursetest";
    public static String urlLiveLectures = url + "api/livelectures";

    public static String urlTeacherLogin = url + "authentication/teacherlogin";
    public static String urlUpdateTeacherProfile = url + "authentication/updateteacherprofile";
    public static String urlTeacherBatches = url + "api/teacherbatches";
    public static String urlTeacherLectures = url + "api/teacherlectures";
    public static String urlTeacherNotifications = url + "api/teachernotifications";
    public static String urlUpdateTeacherFirebaseId = url + "authentication/updateteacherfirebaseid";
    public static String urlMarkLectureStatus = url + "api/marklecturestatus";
    public static String urlGetLectureStatus = url + "api/getlecturestatus";

    public static String urlStudyMaterial = url + "api/studymaterial";

    public static SQLiteDatabase database;


    public DBClass(Context context){
            super(context, DBClass.dbname, null, 1);
    }

    public void onCreate(SQLiteDatabase arg) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    public static void execNonQuery(String query){
        //Execute Insert, Update, Delete, Create table queries
        //Log.e("Quesry", query);
        database.execSQL(query);
    }

    public static Cursor getCursorData(String query){
        //Log.d("SQuery", query);
        Cursor res =  database.rawQuery(query, null);
        return res;
    }

    public static String getSingleValue(String query) {
        try {
            Cursor res = getCursorData(query);
            String value = "";
            if (res.moveToNext()) {
                return res.getString(0);
            }
            return value;
        }
        catch (Exception ex)
        {
            return "";
        }
    }

    public static int getNoOfRows(String query){
        try {
            Cursor res = database.rawQuery(query, null);
            return res.getCount();
        }catch (Exception ex)
        {
            return 0;
        }
    }

    public static String getRandomName() {
        Random generator = new Random();
        String x = String.valueOf (generator.nextInt(96) + 32);
        return x;
    }

    public static boolean checkIfRecordExist(String query){
        //Log.e("CheckQuery", query);
        Cursor res =  database.rawQuery(query, null );
        if(res.getCount() > 0)
            return true;
        else
            return false;
    }

    public   static double distance(double lat1, double lon1, double lat2, double lon2) {
        if(lat1 == 0 || lat2 == 0)
            return 0;
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
            dist = Math.acos(dist);
            dist = rad2deg(dist);
            dist = dist * 60;
            dist = dist * 1852;
            dist = dist / 1000;
            return dist;
        }
    }

    public static boolean doesTableExists(String tableName)
    {
        try{
            Cursor cursor = getCursorData("SELECT * FROM " + tableName);
            return true;
        }
        catch (Exception ex)
        {
            return  false;
        }
    }

    public static boolean doesFieldExist(String tableName, String fieldName)
    {
        try {
            String query = "SELECT " + fieldName + " FROM " + tableName;
            Cursor cursor = getCursorData(query);
            return  true;
        }
        catch (Exception ex)
        {
            return  false;
        }
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}
