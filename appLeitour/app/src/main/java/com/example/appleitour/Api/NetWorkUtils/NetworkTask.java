package com.example.appleitour.Api.NetWorkUtils;


import android.app.Activity;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.URISyntaxException;

public class NetworkTask extends AsyncTask<String, Void, String>{
    Activity context;
    public NetworkTask(Activity _context){
        context = _context;
    }
    public static AsyncResponse delegate = null;
    @Override
    protected String doInBackground(String... params) {
        try {
            switch(params[0]){
                case NetworkUtils.GET: return NetworkUtils.HttpGet(params[1],params[2]);
                case NetworkUtils.POST: return NetworkUtils.HttpPost(params[1],params[2],params[3]);
                case NetworkUtils.PUT: return NetworkUtils.HttpPut(params[1],params[2],params[3]);
                case NetworkUtils.DELETE: return NetworkUtils.HttpDelete(params[1],params[2]);
                case NetworkUtils.AUTO_LOGIN: return NetworkUtils.HttpAutoLogin(params[1],params[2]);
                default: return "";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}
