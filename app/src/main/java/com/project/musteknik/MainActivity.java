package com.project.musteknik;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.musteknik.api.Api;
import com.project.musteknik.api.ApiInterface;
import com.project.musteknik.model.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText edtemail, edtpassword;
    Button login;
    ApiInterface apiInterface;
    ProgressDialog loading;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtemail = findViewById(R.id.edt_email);
        edtpassword = findViewById(R.id.edt_password);
        login = findViewById(R.id.btn_login);

        apiInterface = Api.getClient().create(ApiInterface.class);

//        edtemail.setText("line1@produksi.sier");
//        edtpassword.setText("kosme1234");

        edtemail.setText("leader_mekanik1@teknik.sier");
        edtpassword.setText("@kosme1234");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(MainActivity.this, null, "loading", true, false);
                loginReq(edtemail.getText().toString(), edtpassword.getText().toString());
            }
        });

    }

    void loginReq(String email, String pass){
        Call<Login> response = apiInterface.loginRequest(email, pass);
        response.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                loading.dismiss();
                Login data = response.body();
                String hasil = new Gson().toJson(data);
                if (!hasil.equals("null")){
                    sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(data);
                    editor.putString("User", json);
                    editor.commit();

                    Intent i = new Intent(MainActivity.this, ListTicketActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "No Credential Exist", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(MainActivity.this, "Maintenance : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}