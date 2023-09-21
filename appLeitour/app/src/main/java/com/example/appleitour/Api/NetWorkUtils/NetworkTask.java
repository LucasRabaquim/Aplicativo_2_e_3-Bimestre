package com.example.appleitour.Api.NetWorkUtils;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.URISyntaxException;

public class NetworkTask extends AsyncTask<String, Integer, String>{
    Activity context;
    public NetworkTask(Activity _context){
        super();
        context = _context;
    }
    public static AsyncResponse delegate = null;
    @Override
    protected String doInBackground(String... params) {
        if(!isNetworkAvailable(context))
            return "Verifique a conexão";
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
    protected void onProgressUpdate(Integer... progress){
        Log.d("Progresso: ",progress[0].toString());
       // super.onProgressUpdate(progress);
        context.setProgress(progress[0]);
    }
    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
