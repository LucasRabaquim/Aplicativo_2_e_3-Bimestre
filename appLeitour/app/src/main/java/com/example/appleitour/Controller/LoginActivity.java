package com.example.appleitour.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appleitour.Api.NetWorkUtils.AsyncResponse;
import com.example.appleitour.Api.NetWorkUtils.NetworkTask;
import com.example.appleitour.Api.NetWorkUtils.NetworkUtils;
import com.example.appleitour.Model.SharedSettings;
import com.example.appleitour.Model.User;
import com.example.appleitour.R;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements AsyncResponse {

    CheckBox keepLogged;
    SharedSettings settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        settings = new SharedSettings(LoginActivity.this);
        NetworkTask.delegate = this;
        EditText editEmail = findViewById(R.id.txtemail);
        EditText editSenha = findViewById(R.id.txtpassword);
        keepLogged = findViewById(R.id.checkbox_lembrarLogin);

        Button btnLogin = findViewById(R.id.btn_login);
        findViewById(R.id.txt_nao_tem_conta).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(),CadastrarActivity.class)));

        btnLogin.setOnClickListener(view -> {
            String email = editEmail.getText().toString();
            String senha = editSenha.getText().toString();
            User user = new User(email,senha);
            NetworkUtils http = new NetworkUtils();
            String usu = http.ObjectToString(user);
            String url ="User/login";
            NetworkTask task = new NetworkTask(LoginActivity.this);
            task.execute(NetworkUtils.POST,url,null,usu);
        });
    }

    @Override
    public void processFinish(String output){
    try {
        JSONObject jsonResponse = new JSONObject(output);
        JSONObject user = jsonResponse.getJSONObject("user");
        SharedSettings settings = new SharedSettings(LoginActivity.this);
        settings.SetKeepLogged(keepLogged.isChecked());
        settings.SetToken(jsonResponse.getString("token"));
        Toast.makeText(getApplicationContext(), "Seja bem-vindo(a): " + user.getString("nameUser"), Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
    catch(Exception ex){
        Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show();
    }
    }


}