package com.example.appleitour.Controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appleitour.R;

public class SplashActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
       // SharedSettings shared = new SharedSettings();
       // shared.ApplyTheme(shared.GetTheme());
        /*boolean keepLogged = shared.GetKeepLogged();
        if(keepLogged)
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        else{*/
            //shared.SetToken(null);
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
       // }
    }

}