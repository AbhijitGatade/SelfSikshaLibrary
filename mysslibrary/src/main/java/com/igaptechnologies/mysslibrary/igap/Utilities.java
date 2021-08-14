package com.igaptechnologies.mysslibrary.igap;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Utilities {
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager;
        connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void showSnakBar(View view, String message)
    {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public void showToast(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
