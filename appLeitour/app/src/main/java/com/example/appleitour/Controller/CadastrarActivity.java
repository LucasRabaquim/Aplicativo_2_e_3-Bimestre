package com.example.appleitour.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appleitour.Api.NetWorkUtils.AsyncResponse;
import com.example.appleitour.Api.NetWorkUtils.NetworkTask;
import com.example.appleitour.Api.NetWorkUtils.NetworkUtils;
import com.example.appleitour.Model.SharedSettings;
import com.example.appleitour.Model.User;
import com.example.appleitour.R;

import org.json.JSONObject;

public class CadastrarActivity extends AppCompatActivity implements AsyncResponse {
    CheckBox keepLogged;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        EditText editName = findViewById(R.id.edit_cadastro_username);
        EditText editEmail = findViewById(R.id.edit_cadastro_email);
        EditText editSenha = findViewById(R.id.edit_cadastro_password);

        keepLogged = findViewById(R.id.checkbox_lembrarLogin2);
        Button btnSignUp = findViewById(R.id.btn_cadastrar);
        TextView btnSignIn = findViewById(R.id.txt_tem_conta);

        btnSignUp.setOnClickListener(view -> {
            try{
                String email = editEmail.getText().toString();
                String senha = editSenha.getText().toString();
                String nome = editName.getText().toString();
                User user = new User(nome,senha,email);
                NetworkUtils http = new NetworkUtils();
                String usu = http.ObjectToString(user);
                String url ="User/register";
                NetworkTask task = new NetworkTask(CadastrarActivity.this);
                task.execute(NetworkUtils.POST,url,null,usu);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        btnSignIn.setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(),LoginActivity.class)));
    }
    @Override
    public void processFinish(String output){
        try {
            JSONObject jsonResponse = new JSONObject(output);
            JSONObject user = jsonResponse.getJSONObject("user");
            SharedSettings settings = new SharedSettings(CadastrarActivity.this);
            settings.SetKeepLogged(keepLogged.isChecked());
            settings.SetToken(jsonResponse.getString("token"));
            Toast.makeText(getApplicationContext(), "Seja bem-vindo(a): " + user.getString("nameUser"), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        catch(Exception ex){
            Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show();
            Log.d("TAG", "processFinish: "+ex);
        }
    }

}