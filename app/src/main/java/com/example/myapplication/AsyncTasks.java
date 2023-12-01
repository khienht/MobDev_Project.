package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.Model.Device;
import com.example.myapplication.Model.User;
import com.example.myapplication.RestAPI.APIManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AsyncTasks extends AsyncTask<String, Long, Void> {
    private ProgressDialog pdWaiting;
    private Context context;
    public AsyncTasks(Context context){
        this.context = context;
    }
    @Override
    protected  void onPreExecute(){
        super.onPreExecute();

        //use UI thread here
        pdWaiting = new ProgressDialog(this.context);
        pdWaiting.setMessage(this.context.getString(R.string.wait_loading));
        pdWaiting.show();
    }
    @Override
    protected  Void doInBackground(String... params){
        //Call API
        if (User.getMe() == null) {
            APIManager.getUserInfo();
        }
        if (Device.getDevice() == null ) {
            APIManager.getDevice();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Long... values){
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(Void aVoid){
        super.onPostExecute(aVoid);

        if (pdWaiting.isShowing()) pdWaiting.dismiss();
        else Log.d("async", "done");

    }
}