package com.example.appleitour.Model;

import android.app.Activity;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SharedSettings extends AppCompatActivity{
    final static String SHARED_NAME = "com.example.appleitour";
    final static String TOKEN = "Token";
    final static String THEME = "Theme";
    final static String KEEP_LOGGED = "Keep_Logged";

    Activity context;
    SharedPreferences settings;
    public SharedSettings(Activity context){
        this.context = context;
        this.settings = context.getSharedPreferences(SHARED_NAME, 0);
    }

    public String GetToken(){
        return settings.getString(TOKEN, null);
    }
    public void SetToken(String token){
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(TOKEN,token);
        editor.apply();
    }
    public void SetTheme(int theme){
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(THEME,theme);
        editor.apply();
    }
    public int GetTheme(){
        return settings.getInt(THEME, 0);
    }
    public void ApplyTheme(int theme){
        AppCompatDelegate.setDefaultNightMode(theme);
    }

    public void SetKeepLogged(boolean keepLogged){
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(KEEP_LOGGED,keepLogged);
        editor.apply();
    }
    public boolean GetKeepLogged(){
        return settings.getBoolean(KEEP_LOGGED, false);
    }
}