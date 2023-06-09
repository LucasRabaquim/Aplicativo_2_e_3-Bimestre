package com.example.appleitour.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.appleitour.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        /*
        SharedPreferences settings = getSharedPreferences("com.example.appleitour", 0);
        int Theme = settings.getInt("Theme", 1);
        AppCompatDelegate.setDefaultNightMode(Theme);
        boolean keepLogged = settings.getBoolean("Theme", false);
        if(keepLogged)
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        else
            if(settings.getInt("userId", 0) == 0)
                startActivity(new Intent(getApplicationContext(),CadastrarActivity.class));
            else
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
*/

    }
}