package com.example.appleitour.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.example.appleitour.Api.NetWorkUtils.NetworkTask;
import com.example.appleitour.Api.NetWorkUtils.NetworkUtils;
import com.example.appleitour.Model.User;
import com.example.appleitour.R;

public class LoginActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText editEmail = findViewById(R.id.txtemail);
        EditText editSenha = findViewById(R.id.txtpassword);
        CheckBox keepLogged = findViewById(R.id.checkbox_lembrarLogin);
        Button btnLogin = findViewById(R.id.btn_login);
        findViewById(R.id.txt_nao_tem_conta).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(),CadastrarActivity.class)));
        btnLogin.setOnClickListener(view -> {
            String email = "lucas@gmail.com";//editEmail.getText().toString();
            String senha = "12345";//editSenha.getText().toString();
            User user = new User(email,senha);
            NetworkUtils http = new NetworkUtils();
            String usu = http.ObjectToString(user);
            String url ="User/login";
            NetworkTask task = new NetworkTask(LoginActivity.this);
            String stringResponse = String.valueOf(task.execute(NetworkUtils.POST,url,null,usu));
            Toast.makeText(getApplicationContext(),"Seja bem-vindo(a): " + stringResponse , Toast.LENGTH_SHORT).show();

            /* try {
                JSONObject jsonResponse = new JSONObject(stringResponse);
                SharedSettings settings = new SharedSettings();
                if(keepLogged.isChecked())
                    settings.SetToken(jsonResponse.getString("Token"));
                else
                    settings.SetToken("");
                Toast.makeText(getApplicationContext(),"Seja bem-vindo(a): " + jsonResponse.getString("userName") , Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }*/
            //SharedSettings settings = new SharedSettings();
            //settings.SetToken();
            /*   DatabaseHelper db = new DatabaseHelper(getApplicationContext());
            db.getReadableDatabase();*/


          /*  if(db.retornarUsuarioCadastrado(email,senha) != 0){
                SharedPreferences settings = getSharedPreferences("com.example.appleitour", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("UserId",db.retornarUsuarioCadastrado(email,senha));
                editor.putInt("LastUser",db.retornarUsuarioCadastrado(email,senha));
                editor.putBoolean("keepLogged",keepLogged.isChecked());
                editor.apply();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                finish();
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),"Usuario ou senha incorretos",Toast.LENGTH_SHORT).show();
            }*/
        });
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}