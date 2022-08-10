package com.project.musteknik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.musteknik.adapter.TicketAdapter;
import com.project.musteknik.api.Api;
import com.project.musteknik.api.ApiInterface;
import com.project.musteknik.model.Login;
import com.project.musteknik.model.create.ResponseCreate;
import com.project.musteknik.model.ticket.Response;

import retrofit2.Call;
import retrofit2.Callback;

public class CreateTicketActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView nama, plan, kodemesin;
    ApiInterface apiInterface;
    ProgressDialog loading;
    EditText desc;
    ImageView back;
    Button create;
    String kode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ticket);

        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("User", null);
        Login login = gson.fromJson(json, Login.class);

        apiInterface = Api.getClient().create(ApiInterface.class);

        kode = getIntent().getStringExtra("kode");

        nama = findViewById(R.id.txt_nama);
        plan = findViewById(R.id.txt_dept);
        kodemesin = findViewById(R.id.txt_kode);
        back = findViewById(R.id.btn_back);
        create = findViewById(R.id.btn_create);
        desc = findViewById(R.id.edt_desc);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        nama.setText(login.getDataLogin().getName());
        kodemesin.setText(kode);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(CreateTicketActivity.this, null, "loading", true, false);
                postTicket(login.getDataLogin().getId(), kode, desc.getText().toString());
            }
        });


    }

    void postTicket(String userid, String kode, String desc){
        Call<ResponseCreate> response = apiInterface.createTiket(userid, kode, desc);
        response.enqueue(new Callback<ResponseCreate>() {
            @Override
            public void onResponse(Call<ResponseCreate> call, retrofit2.Response<ResponseCreate> response) {
                ResponseCreate data = response.body();
                loading.dismiss();
                Toast.makeText(CreateTicketActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(CreateTicketActivity.this, ListTicketActivity.class);
                startActivity(i);
                finish();

            }

            @Override
            public void onFailure(Call<ResponseCreate> call, Throwable t) {
                loading.dismiss();
                Log.d("thisfailure : ", t.getLocalizedMessage());
                Toast.makeText(CreateTicketActivity.this, "Failed to create Ticket", Toast.LENGTH_SHORT).show();
            }
        });
    }
}