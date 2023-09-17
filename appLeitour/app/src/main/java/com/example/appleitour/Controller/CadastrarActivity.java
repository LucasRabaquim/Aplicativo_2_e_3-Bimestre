package com.example.appleitour.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appleitour.Api.NetWorkUtils.NetworkUtils;
import com.example.appleitour.Model.ViewUtilities;
import com.example.appleitour.R;

public class CadastrarActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        Button btnSignUp = findViewById(R.id.btn_cadastrar);
        TextView btnSignIn = findViewById(R.id.txt_tem_conta);

        ViewUtilities viewUtilities = new ViewUtilities();
        NetworkUtils http = new NetworkUtils();
/*
        btnSignUp.setOnClickListener(view -> {
            String nome = viewUtilities.GetTextFromEditView(R.id.edit_cadastro_username);
            String email = viewUtilities.GetTextFromEditView(R.id.edit_cadastro_email);
            String senha = viewUtilities.GetTextFromEditView(R.id.edit_cadastro_password);
            CheckBox keepLogged = findViewById(R.id.checkbox_lembrarLogin2);
            User user = new User(nome,email,senha);

            try{
                String usu = http.ObjectToString(user);
                URL url = http.buildUrl("User/Login");
                String result = http.HttpSign(url,http.POST,usu);
                viewUtilities.SetTextToEditView(R.id.edit_cadastro_username,nome);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        });*/
        btnSignIn.setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(),LoginActivity.class)));
    }
}