package com.example.appleitour.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.appleitour.Database.DatabaseHelper;
import com.example.appleitour.Model.User;
import com.example.appleitour.R;

public class CadastrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        EditText editNome = findViewById(R.id.edit_cadastro_username);
        EditText editEmail = findViewById(R.id.edit_cadastro_email);
        EditText editSenha = findViewById(R.id.edit_cadastro_password);
        Button btnCadastrar = findViewById(R.id.btn_cadastrar);

        btnCadastrar.setOnClickListener(view -> {
            String nome = editNome.getText().toString().trim();
            String email = editEmail.getText().toString().trim();
            String senha = editSenha.getText().toString().trim();
            CheckBox keepLogged = findViewById(R.id.checkbox_lembrarLogin2);
            User user = new User(nome,email,senha,(byte)0,1);
            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
            db.getWritableDatabase();
            db.insertUser(user);
            SharedPreferences settings = getSharedPreferences("com.example.appleitour", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("UserId",db.selectLastInsert());
            editor.putBoolean("keepLogged",keepLogged.isChecked());
            editor.apply();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            finish();
            startActivity(intent);
        });
    }
}