package com.example.dzik;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;

public class AnulujDialog {

    private Activity activity;
    private AlertDialog alertDialog;
    String TAG="Mroziu";

    AnulujDialog(Activity myActivity){
        activity=myActivity;
    }

    void startAnulujDialog(){
        Log.d(TAG, "startBtConnectDialog: Start dialogu");
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_anuluj,null));
        builder.setCancelable(false);



        alertDialog =builder.create();
        alertDialog.show();
    }

    void dismissAnulujDialog(){
        Log.d(TAG, "dismissBtConnectDialog: Koniec dialogu");
        alertDialog.dismiss();
    }
}
