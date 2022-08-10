package com.project.musteknik;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.gson.Gson;
import com.google.zxing.Result;
import com.project.musteknik.api.Api;
import com.project.musteknik.api.ApiInterface;
import com.project.musteknik.model.Login;
import com.project.musteknik.model.detail.ResponseDetail;
import com.project.musteknik.model.postTimeline.ResponsePostTimeline;

import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;

public class CameraActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView mScannerView;
    FrameLayout camera;
    ApiInterface apiInterface;
    String kode;
    String code, id;
    SharedPreferences sharedPreferences;
    Login login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        apiInterface = Api.getClient().create(ApiInterface.class);

        camera = findViewById(R.id.frame_layout_camera);
        code = getIntent().getStringExtra("code");
        id = getIntent().getStringExtra("idtiket");


        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("User", null);
        login = gson.fromJson(json, Login.class);

        initScannerView();

    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    private void initScannerView() {
        mScannerView = new ZXingScannerView(CameraActivity.this);
        mScannerView.setAutoFocus(true);
        mScannerView.setResultHandler(this);
        camera.addView(mScannerView);
        mScannerView.startCamera();
    }

    @Override
    public void onStart() {
        mScannerView.startCamera();
        doRequestPermission();
        super.onStart();
    }

    @Override
    public void handleResult(Result rawResult) {
//
//        if (code.equals("1")){
//                kode = rawResult.getText();
//                Intent i = new Intent(CameraActivity.this, CreateTicketActivity.class);
//                i.putExtra("kode", kode);
//                startActivity(i);
//                finish();
//            } else {
//            post(login.getDataLogin().getId(), id, "17");
//        }
        if (code.equals("1")) {
            kode = rawResult.getText();
            Intent i = new Intent(CameraActivity.this, CreateTicketActivity.class);
            i.putExtra("kode", kode);
            startActivity(i);
            finish();
        }
     else {
        if (code.equals("2")) {
            kode = rawResult.getText();
            post(login.getDataLogin().getId(), id, "17", kode);
        }
    }

}

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void doRequestPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
            }
        }
    }

    private void initView(){
        mScannerView.resumeCameraPreview(this::handleResult);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initScannerView();
            } else {

            }
        }
    }
    void post(String userid, String tiketid, String status, String kodemesin){
        Call<ResponsePostTimeline> response = apiInterface.postTimeline(userid, tiketid, status, kodemesin);
        response.enqueue(new Callback<ResponsePostTimeline>() {
            @Override
            public void onResponse(Call<ResponsePostTimeline> call, retrofit2.Response<ResponsePostTimeline> response) {
                ResponsePostTimeline data = response.body();
                if (String.valueOf(data.getCode()).equals("404")){
                    Toast.makeText(CameraActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
                    initView();
                } else {
                    Toast.makeText(CameraActivity.this, "Success to checkin", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(CameraActivity.this, ListTicketActivity.class);
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponsePostTimeline> call, Throwable t) {
                Log.d("thisfailure : ", t.getLocalizedMessage());
                Toast.makeText(CameraActivity.this, "Failed to checkin", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
