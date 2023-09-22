package com.example.appleitour.Controller;

import static android.view.View.GONE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appleitour.Adapter.AnnotationAdapter;
import com.example.appleitour.Api.NetWorkUtils.AsyncResponse;
import com.example.appleitour.Api.NetWorkUtils.NetworkTask;
import com.example.appleitour.Api.NetWorkUtils.NetworkUtils;
import com.example.appleitour.Database.DatabaseHelper;
import com.example.appleitour.Model.Annotation;
import com.example.appleitour.Model.Book;
import com.example.appleitour.Model.SharedSettings;
import com.example.appleitour.Model.User;
import com.example.appleitour.R;
import com.example.appleitour.SimpleAppWidget;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class UserOptionsActivity extends AppCompatActivity implements AsyncResponse {

    String strUser;
    Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useroptionsactivity);
        EditText editName = findViewById(R.id.edit_data_username);
        EditText editSenha = findViewById(R.id.edit_data_password);

        Button btnEditar = findViewById(R.id.btn_user_editar);
        Button btnExcluir = findViewById(R.id.btn_user_excluir);

        Intent thisIntent = getIntent();
        strUser = thisIntent.getStringExtra("USER");
        Gson gson = new Gson();
        User user = gson.fromJson(strUser,  User.class);
        JSONObject json = null;
        try {
            json = new JSONObject(strUser);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
            user.setEmail(json.getString("email"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        Log.d("Valor usuario",strUser);
        editSenha.setText(user.getPassword());
        editName.setText(user.getNameUser());

        btnEditar.setOnClickListener(view -> {
            try{
                String senha = editSenha.getText().toString();
                String nome = editName.getText().toString();
                user.setNameUser(nome);
                user.setPassword(senha);
                String usu = NetworkUtils.ObjectToString(user);
                Log.d("Valor usuario",usu);
                SharedSettings sharedSettings = new SharedSettings(this);
                String token = sharedSettings.GetToken();
                String url ="User/alter";
                intent = new Intent(getApplicationContext(),MainActivity.class);
                NetworkTask task = new NetworkTask(UserOptionsActivity.this);
                task.execute(NetworkUtils.PUT,url,token,usu);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        btnExcluir.setOnClickListener(view -> {
            try{
                String senha = editSenha.getText().toString();
                String nome = editName.getText().toString();
                user.setNameUser(nome);
                user.setPassword(senha);
                SharedSettings sharedSettings = new SharedSettings(this);
                String token = sharedSettings.GetToken();
                String url ="User/deactivate";
                intent = new Intent(getApplicationContext(),LoginActivity.class);
                NetworkTask task = new NetworkTask(UserOptionsActivity.this);
                task.execute(NetworkUtils.DELETE,url,token,null);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    @Override
    public void processFinish(String output){
        try {
            Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show();
            Intent intent;
            Log.d("Erro", "processFinish: "+output);
            if(output == "Você desativou o usuário")
                intent = new Intent(getApplicationContext(), LoginActivity.class);
            else
                intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("USER",strUser);
            finish();
        }
        catch(Exception ex){
        }
    }

}
