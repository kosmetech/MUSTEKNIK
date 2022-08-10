package com.project.musteknik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.project.musteknik.adapter.TicketAdapter;
import com.project.musteknik.api.Api;
import com.project.musteknik.api.ApiInterface;
import com.project.musteknik.model.Login;
import com.project.musteknik.model.masterdata.ResponseMasterData;
import com.project.musteknik.model.ticket.DataItem;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTicketActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ApiInterface apiInterface;
    TextView username;
    ImageView logout;
    FloatingActionButton btnCreate;
    SharedPreferences sharedPreferences;
    ProgressDialog loading;
    SwipeRefreshLayout refreshLayout;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ticket);


        apiInterface = Api.getClient().create(ApiInterface.class);
        recyclerView = findViewById(R.id.rv_ticket);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        username = findViewById(R.id.txt_username);
        btnCreate = findViewById(R.id.btn_create);
        logout = findViewById(R.id.btn_logout);
        refreshLayout = findViewById(R.id.swipe_refresh);

        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("User", null);
        Login login = gson.fromJson(json, Login.class);


        getMasterData();

        if (login.getDataLogin().getDepartemenId().equals("1") || login.getDataLogin().getDepartemenId().equals("2")){
            btnCreate.setVisibility(View.GONE);
        } else {
            btnCreate.setVisibility(View.VISIBLE);
        }

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListTicketActivity.this, CameraActivity.class);
                i.putExtra("code", "1");
                startActivity(i);
            }
        });
        loading = ProgressDialog.show(ListTicketActivity.this, null, "loading", true, false);
        getList(login.getDataLogin().getId());

        username.setText("Halo, " + login.getDataLogin().getName() + "!");

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences settings = getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
                settings.edit().clear().commit();
                Intent i = new Intent(ListTicketActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getList(login.getDataLogin().getId());
                refreshLayout.setRefreshing(false);
            }
        });

    }



    void getList(String userid){
        Call<com.project.musteknik.model.ticket.Response> response = apiInterface.listTicket(userid);
        response.enqueue(new Callback<com.project.musteknik.model.ticket.Response>() {
            @Override
            public void onResponse(Call<com.project.musteknik.model.ticket.Response> call, Response<com.project.musteknik.model.ticket.Response> response) {
                loading.dismiss();
                com.project.musteknik.model.ticket.Response data = response.body();
                Collections.reverse(data.getData());
                TicketAdapter ticketAdapter = new TicketAdapter(ListTicketActivity.this, data.getData());
                recyclerView.setAdapter(ticketAdapter);
                ticketAdapter.setOnItemClickListener(new TicketAdapter.ClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {
                        TextView id = v.findViewById(R.id.txt_id);
                        Intent i = new Intent(ListTicketActivity.this, DetailsTicketActivity.class);
                        i.putExtra("id", id.getText().toString());
                        startActivity(i);
                    }
                });

            }

            @Override
            public void onFailure(Call<com.project.musteknik.model.ticket.Response> call, Throwable t) {
                loading.dismiss();
                Log.d("thisfailure : ", t.getLocalizedMessage());
                Toast.makeText(ListTicketActivity.this, "Failed to get List", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void getMasterData(){
        Call<ResponseMasterData> response = apiInterface.getMasterData();
        response.enqueue(new Callback<ResponseMasterData>() {
            @Override
            public void onResponse(Call<ResponseMasterData> call, Response<ResponseMasterData> response) {
                ResponseMasterData data = response.body();

                sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
                editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(data);
                editor.putString("Master", json);
                editor.commit();
            }

            @Override
            public void onFailure(Call<ResponseMasterData> call, Throwable t) {
                Log.d("thisfailure : ", t.getLocalizedMessage());
                Toast.makeText(ListTicketActivity.this, "Failed to get Master Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}