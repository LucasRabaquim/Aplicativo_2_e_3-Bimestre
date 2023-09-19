package com.example.appleitour.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appleitour.Api.NetWorkUtils.AsyncResponse;
import com.example.appleitour.Api.NetWorkUtils.NetworkTask;
import com.example.appleitour.Api.NetWorkUtils.NetworkUtils;
import com.example.appleitour.Model.SharedSettings;
import com.example.appleitour.R;

import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity implements AsyncResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedSettings  settings = new SharedSettings(SplashActivity.this);
        if (settings.GetKeepLogged()) {
            String token = settings.GetToken();
            String url = "User/login/auto";
            NetworkTask task = new NetworkTask(SplashActivity.this);
            task.execute(NetworkUtils.AUTO_LOGIN, url, token, null);
        }else{
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();
        }
    }

    @Override
    public void processFinish(String output) {
        try {
            JSONObject jsonResponse = new JSONObject(output);
            JSONObject user = jsonResponse.getJSONObject("user");
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            Toast.makeText(getApplicationContext(), "Seja bem-vindo(a): " + user.getString("nameUser") +"\n token: "+ jsonResponse.getString("token"), Toast.LENGTH_SHORT).show();
            finish();
        }
        catch(Exception ex){
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }
    }


}