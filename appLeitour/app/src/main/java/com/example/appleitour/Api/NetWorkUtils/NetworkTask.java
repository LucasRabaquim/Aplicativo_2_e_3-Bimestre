package com.example.appleitour.Api.NetWorkUtils;



import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class NetworkTask extends AsyncTask<String, Void, String>{
    Activity context;
    public NetworkTask(Activity _context){
        context = _context;
    }
    public static AsyncResponse delegate = null;
    @Override
    protected String doInBackground(String... params) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();

            if(!(networkInfo != null && networkInfo.isConnectedOrConnecting()))
                return "";
            switch (params[0]) {
                case NetworkUtils.GET:
                    return NetworkUtils.HttpGet(params[1], params[2]);
                case NetworkUtils.POST:
                    return NetworkUtils.HttpPost(params[1], params[2], params[3]);
                case NetworkUtils.PUT:
                    return NetworkUtils.HttpPut(params[1], params[2], params[3]);
                case NetworkUtils.DELETE:
                    return NetworkUtils.HttpDelete(params[1], params[2]);
                case NetworkUtils.AUTO_LOGIN:
                    return NetworkUtils.HttpAutoLogin(params[1], params[2]);
                default:
                    return "";
            }
        } catch (UnsupportedEncodingException e) {
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
