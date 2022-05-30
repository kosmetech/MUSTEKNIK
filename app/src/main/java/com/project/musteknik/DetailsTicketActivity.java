package com.project.musteknik;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.project.musteknik.adapter.TimelineAdapter;
import com.project.musteknik.api.Api;
import com.project.musteknik.api.ApiInterface;
import com.project.musteknik.model.Login;
import com.project.musteknik.model.create.ResponseCreate;
import com.project.musteknik.model.detail.ResponseDetail;
import com.project.musteknik.model.postTimeline.ResponsePostTimeline;
import com.project.musteknik.model.timeline.ResponseTimeline;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class DetailsTicketActivity extends AppCompatActivity {

    String id, sts, kode;
    ApiInterface apiInterface;
    TextView nama, dept, wa, line, status, deskripsi, kodemesin;
    ImageView back;
    RecyclerView recyclerView;
    ExtendedFloatingActionButton checkin, checkout, done, pending;
    SharedPreferences sharedPreferences;
    Login login;


    ProgressDialog loading;

    private static final String[] CHOICE = new String[]{
            "Teknik perlu melakukan eksalasi", "Perbaikan sudah selesai, menunggu verifikasi produksi"
    };

    private String pilih_jk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_ticket);
        apiInterface = Api.getClient().create(ApiInterface.class);



        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("User", null);
        login = gson.fromJson(json, Login.class);


        id = getIntent().getStringExtra("id");

        loading = ProgressDialog.show(DetailsTicketActivity.this, null, "loading", true, false);

        getDetail(id);
        getTimeline(id);

        checkin = findViewById(R.id.checkin_fab);
        checkout = findViewById(R.id.checkout_fab);
        done = findViewById(R.id.done_fab);
        pending = findViewById(R.id.pending_fab);

        checkout.setVisibility(View.GONE);
        checkin.setVisibility(View.GONE);
        done.setVisibility(View.GONE);
        pending.setVisibility(View.GONE);

        nama = findViewById(R.id.txt_nama);
        dept = findViewById(R.id.txt_dept);
        wa = findViewById(R.id.txt_whatsapp);
        back = findViewById(R.id.btn_back);
        line = findViewById(R.id.txt_line);
        status = findViewById(R.id.txt_status);
        kodemesin = findViewById(R.id.txt_kode_mesin);
        deskripsi = findViewById(R.id.txt_desc);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailsTicketActivity.this, CameraActivity.class);
                i.putExtra("code", "2");
                i.putExtra("idtiket", id);
                startActivity(i);
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilih_jk = CHOICE[0];
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailsTicketActivity.this);
                builder.setTitle("Pilih salah satu");
                builder.setSingleChoiceItems(CHOICE, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        pilih_jk = CHOICE[i];
                        if (pilih_jk.equals("Teknik perlu melakukan eksalasi")){
                            sts = "18";
                        } else {
                            sts = "19";
                        }
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(DetailsTicketActivity.this, CheckoutActivity.class);
                        intent.putExtra("status", sts);
                        intent.putExtra("tiket_id", id);
                        intent.putExtra("user_id", login.getDataLogin().getId());
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("CANCEL", null);
                builder.show();
            }

        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post(login.getDataLogin().getId(), id, "20", kode);
            }
        });

        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post(login.getDataLogin().getId(), id, "22", kode);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }

    void getDetail(String id){
        Call<ResponseDetail> response = apiInterface.detail(id);
        response.enqueue(new Callback<ResponseDetail>() {
            @Override
            public void onResponse(Call<ResponseDetail> call, retrofit2.Response<ResponseDetail> response) {
                loading.dismiss();
                ResponseDetail data = response.body();
                nama.setText(data.getData().getPelaporName());
                dept.setText(data.getData().getNamaPlant());
                wa.setText(data.getData().getPelaporWa());
                line.setText(data.getData().getNamaMesin());
                status.setText(data.getData().getNamaStatus());
                deskripsi.setText(data.getData().getDeskripsi());
                kodemesin.setText(data.getData().getKodeMesin());
                kode = data.getData().getKodeMesin();
            }

            @Override
            public void onFailure(Call<ResponseDetail> call, Throwable t) {
                loading.dismiss();
                Log.d("thisfailure : ", t.getLocalizedMessage());
                Toast.makeText(DetailsTicketActivity.this, "Failed to get List", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void getTimeline(String id){
        Call<ResponseTimeline> response = apiInterface.timeline(id);
        response.enqueue(new Callback<ResponseTimeline>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<ResponseTimeline> call, retrofit2.Response<ResponseTimeline> response) {
                loading.dismiss();
                ResponseTimeline data = response.body();
                TimelineAdapter timelineAdapter = new TimelineAdapter(DetailsTicketActivity.this, data.getData().getTimelines());
                recyclerView.setAdapter(timelineAdapter);
                if (login.getDataLogin().getDepartemenId().equals("2")) {
                    String nulldata = new Gson().toJson(data.getChoice().getTeknik());
                    if (nulldata.equals("[{}]")){
                        done.setVisibility(View.GONE);
                        pending.setVisibility(View.GONE);
                        checkout.setVisibility(View.GONE);
                        checkin.setVisibility(View.GONE);
                    } else {
                        if (data.getChoice().getTeknik().get(0).getId().equals("17")){
                            done.setVisibility(View.GONE);
                            pending.setVisibility(View.GONE);
                            checkout.setVisibility(View.GONE);
                            checkin.setVisibility(View.VISIBLE);
                        } else {
                            done.setVisibility(View.GONE);
                            pending.setVisibility(View.GONE);
                            checkout.setVisibility(View.VISIBLE);
                            checkin.setVisibility(View.GONE);
                        }
                    }
                } else {
                    String nulldata = new Gson().toJson(data.getChoice().getProduksi());
                    if (nulldata.equals("[{}]")){
                        done.setVisibility(View.GONE);
                        pending.setVisibility(View.GONE);
                        checkout.setVisibility(View.GONE);
                        checkin.setVisibility(View.GONE);
                    } else {
                        done.setVisibility(View.VISIBLE);
                        pending.setVisibility(View.VISIBLE);
                        checkout.setVisibility(View.GONE);
                        checkin.setVisibility(View.GONE);
                    }
                }
            }


            @Override
            public void onFailure(Call<ResponseTimeline> call, Throwable t) {
                loading.dismiss();
                Log.d("thisfailure : ", t.getLocalizedMessage());
                Toast.makeText(DetailsTicketActivity.this, "Failed to get List", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void post(String userid, String tiketid, String status, String kodemesin){
        Call<ResponsePostTimeline> response = apiInterface.postTimeline(userid, tiketid, status, kodemesin);
        response.enqueue(new Callback<ResponsePostTimeline>() {
            @Override
            public void onResponse(Call<ResponsePostTimeline> call, retrofit2.Response<ResponsePostTimeline> response) {
                ResponsePostTimeline data = response.body();
                Toast.makeText(DetailsTicketActivity.this, "Success", Toast.LENGTH_SHORT).show();
                finish();

            }

            @Override
            public void onFailure(Call<ResponsePostTimeline> call, Throwable t) {
                Log.d("thisfailure : ", t.getLocalizedMessage());
                Toast.makeText(DetailsTicketActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
