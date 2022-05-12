package com.project.musteknik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.musteknik.adapter.SparepartAdapter;
import com.project.musteknik.api.Api;
import com.project.musteknik.api.ApiInterface;
import com.project.musteknik.model.Login;
import com.project.musteknik.model.Utility;
import com.project.musteknik.model.checkout.ResponseCheckout;
import com.project.musteknik.model.masterdata.ResponseMasterData;
import com.project.musteknik.model.masterdata.ShiftItem;
import com.project.musteknik.model.postTimeline.ResponsePostTimeline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;


public class CheckoutActivity extends AppCompatActivity {

    EditText idtiket, analisis, perbaikan, grup, anggota, sparepart, abnormality, hours, utility, catatan;
    Spinner shift;
    Button checkout;
    ImageView btnBack;
    //String[] utilityArray = {"Water Treatment Process","Chiller","HVAC","Compressor","PDAM","IPAL","Cooling Storage"};
    boolean[] selectedUtility, selectedAnggota, selectedSparepart;
    ArrayList<Integer> langList = new ArrayList<>();
    ArrayList<Integer> anggotalist = new ArrayList<>();
    ArrayList<Integer> sparepartlist = new ArrayList<>();
    ArrayList<String> newCount = new ArrayList<>();
    SharedPreferences sharedPreferences;
    String idshift, idutility, idanggota, idsparepart;
    String[] selUtility, selAnggota, selSparepart;
    ArrayList<Utility> utilityArrayList;
    RecyclerView recyclerView;
    SparepartAdapter sparepartAdapter;

    ApiInterface apiInterface;
    ResponseMasterData masterData;
    String status, tiket_id, user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        apiInterface = Api.getClient().create(ApiInterface.class);

        status = getIntent().getStringExtra("status");
        tiket_id = getIntent().getStringExtra("tiket_id");
        user_id = getIntent().getStringExtra("user_id");

        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Master", null);
        masterData = gson.fromJson(json, ResponseMasterData.class);

        btnBack = findViewById(R.id.btn_back);
        idtiket = findViewById(R.id.edt_id);
        analisis = findViewById(R.id.edt_analisis);
        perbaikan = findViewById(R.id.edt_perbaikan);
        grup = findViewById(R.id.edt_group);
        shift = findViewById(R.id.edt_shift);
        anggota = findViewById(R.id.edt_anggota);
        sparepart = findViewById(R.id.edt_sparepart);
        abnormality = findViewById(R.id.edt_abnormality);
        hours = findViewById(R.id.edt_hours);
        utility = findViewById(R.id.edt_utility);
        catatan = findViewById(R.id.edt_catatan);
        checkout = findViewById(R.id.btn_checkout);
        recyclerView = findViewById(R.id.rv_sparepart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        utilityArrayList = new ArrayList<Utility>();
        utilityArrayList.add(new Utility("1", "Water Treatment Process"));
        utilityArrayList.add(new Utility("2", "Chiller"));
        utilityArrayList.add(new Utility("3", "HVAC"));
        utilityArrayList.add(new Utility("4", "Compressor"));
        utilityArrayList.add(new Utility("5", "PDAM"));
        utilityArrayList.add(new Utility("6", "IPAL"));
        utilityArrayList.add(new Utility("7", "Cooling Storage"));


        utility.setFocusable(false);
        utility.setClickable(false);

        idtiket.setFocusable(false);
        idtiket.setClickable(false);

        idtiket.setText(tiket_id);
//
//        shift.setFocusable(false);
//        shift.setClickable(false);

        anggota.setFocusable(false);
        anggota.setClickable(false);

        sparepart.setFocusable(false);
        sparepart.setClickable(false);

        List<String> listutility = new ArrayList<String>();
        for (int a = 0; a <= utilityArrayList.size() - 1; a++){
            listutility.add(utilityArrayList.get(a).getNama());
        }

        String[] utilityArray = listutility.toArray(new String[0]);

        List<String> listanggota = new ArrayList<String>();
        for (int a = 0; a <= masterData.getData().getAnggotaTekniks().size() - 1; a++){
            listanggota.add(masterData.getData().getAnggotaTekniks().get(a).getName());
        }

        String[] anggotaArray = listanggota.toArray(new String[0]);

        List<String> listsparepart = new ArrayList<String>();
        for (int s = 0; s <= masterData.getData().getSparepart().size() - 1; s++){
            listsparepart.add(masterData.getData().getSparepart().get(s).getName());
        }

        String[] sparepartArray = listsparepart.toArray(new String[0]);



        selectedUtility = new boolean[utilityArray.length];
        selectedAnggota = new boolean[anggotaArray.length];
        selectedSparepart = new boolean[sparepartArray.length];

        utility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CheckoutActivity.this);

                // set title
                builder.setTitle("Select Utility Outages");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(utilityArray, selectedUtility, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            langList.add(i);
                            // Sort array list
                            Collections.sort(langList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            langList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < langList.size(); j++) {
                            // concat array value
                            stringBuilder.append(utilityArray[langList.get(j)]);
                            // check condition
                            if (j != langList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        utility.setText(stringBuilder.toString());
                        selUtility = stringBuilder.toString().split(", ");
                        ArrayList<String> idlist = new ArrayList<>();
                        for (int i1 = 0; i1 <= selUtility.length - 1; i1++){
                            String s = selUtility[i1].toString();
                            for (int j = 0; j <= utilityArrayList.size() - 1; j++){
                                if (utilityArrayList.get(j).getNama().contains(s)){
                                    idlist.add(utilityArrayList.get(j).getId());
                                }
                            }
                        }
                        idutility = TextUtils.join(",", idlist);

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedUtility.length; j++) {
                            // remove all selection
                            selectedUtility[j] = false;
                            // clear language list
                            langList.clear();
                            // clear text view value
                            utility.setText("");
                            idutility = "";
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });


        anggota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CheckoutActivity.this);

                // set title
                builder.setTitle("Select Anggota Teknik");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(anggotaArray, selectedAnggota, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            anggotalist.add(i);
                            // Sort array list
                            Collections.sort(anggotalist);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            anggotalist.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < anggotalist.size(); j++) {
                            // concat array value
                            stringBuilder.append(anggotaArray[anggotalist.get(j)]);
                            // check condition
                            if (j != anggotalist.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        anggota.setText(stringBuilder.toString());
                        selAnggota = stringBuilder.toString().split(", ");
                        ArrayList<String> idlist = new ArrayList<>();
                        for (int i1 = 0; i1 <= selAnggota.length - 1; i1++){
                            String s = selAnggota[i1].toString();
                            for (int j = 0; j <= masterData.getData().getAnggotaTekniks().size() - 1; j++){
                                if (masterData.getData().getAnggotaTekniks().get(j).getName().contains(s)){
                                    idlist.add(String.valueOf(masterData.getData().getAnggotaTekniks().get(j).getId()));
                                }
                            }
                        }
                        idanggota = TextUtils.join(",", idlist);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedAnggota.length; j++) {
                            // remove all selection
                            selectedAnggota[j] = false;
                            // clear language list
                            anggotalist.clear();
                            // clear text view value
                            anggota.setText("");
                            idanggota = "";
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });

        sparepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CheckoutActivity.this);

                // set title
                builder.setTitle("Select Sparepart");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(sparepartArray, selectedSparepart, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            sparepartlist.add(i);
                            // Sort array list
                            Collections.sort(sparepartlist);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            sparepartlist.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < sparepartlist.size(); j++) {
                            // concat array value
                            stringBuilder.append(sparepartArray[sparepartlist.get(j)]);
                            // check condition
                            if (j != sparepartlist.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        sparepart.setText(stringBuilder.toString());
                        selSparepart = stringBuilder.toString().split(", ");
                        List<String> listSp = Arrays.asList(stringBuilder.toString().split(", "));
                        sparepartAdapter = new SparepartAdapter(CheckoutActivity.this, listSp, new RunnableValue() {
                            @Override
                            public void run(String s) {
                                newCount.add(s);
                                Log.d("tesdata", new Gson().toJson(newCount));
                            }
                        });
                        recyclerView.setAdapter(sparepartAdapter);
                        ArrayList<String> idlist = new ArrayList<>();
                        for (int i1 = 0; i1 <= selSparepart.length - 1; i1++){
                            String s = selSparepart[i1].toString();
                            for (int j = 0; j <= masterData.getData().getSparepart().size() - 1; j++){
                                if (masterData.getData().getSparepart().get(j).getName().contains(s)){
                                    idlist.add(String.valueOf(masterData.getData().getSparepart().get(j).getId()));
                                }
                            }
                        }
                        idsparepart = TextUtils.join(",", idlist);
                    }

                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedSparepart.length; j++) {
                            // remove all selection
                            selectedSparepart[j] = false;
                            // clear language list
                            sparepartlist.clear();
                            // clear text view value
                            sparepart.setText("");
                            idsparepart = "";
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });

        List<String> list = new ArrayList<String>();
        for (int i = 0; i <= masterData.getData().getShift().size() - 1; i++){
            list.add(masterData.getData().getShift().get(i).getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        shift.setAdapter(adapter);

        shift.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String shiftItem = (String) adapterView.getSelectedItem();
                for (int j = 0; j <= masterData.getData().getShift().size() - 1; j++){
                    if (masterData.getData().getShift().get(j).getName().equals(shiftItem)){
                        idshift = String.valueOf(masterData.getData().getShift().get(j).getId());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (analisis.getText().toString().equals("")){
                    Toast.makeText(CheckoutActivity.this, "Harap isi Deskripsi Analisis", Toast.LENGTH_SHORT).show();
                } else if (perbaikan.getText().toString().equals("")){
                    Toast.makeText(CheckoutActivity.this, "Harap isi Deskripsi Perbaikan", Toast.LENGTH_SHORT).show();
                } else {
                    post(user_id, tiket_id, status, analisis.getText().toString(), perbaikan.getText().toString(), idanggota, grup.getText().toString(),
                            idshift, catatan.getText().toString(), abnormality.getText().toString(), hours.getText().toString(), idsparepart, idutility);

                }
            }
        });


    }



    void post(String userid, String tiketid, String status, String analisis, String perbaikan, String anggota, String group, String shift, String catatan,
              String abnormality, String hours, String sparepart, String utility){
        Call<ResponseCheckout> response = apiInterface.checkout(userid, tiketid, status, analisis, perbaikan, anggota, group, shift, catatan, abnormality, hours, sparepart, utility);
        response.enqueue(new Callback<ResponseCheckout>() {
            @Override
            public void onResponse(Call<ResponseCheckout> call, retrofit2.Response<ResponseCheckout> response) {
                ResponseCheckout data = response.body();
                Toast.makeText(CheckoutActivity.this, "Checkout Sukses!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(CheckoutActivity.this, ListTicketActivity.class);
                startActivity(i);
                finish();

            }

            @Override
            public void onFailure(Call<ResponseCheckout> call, Throwable t) {
                Log.d("thisfailure : ", t.getLocalizedMessage());
                Toast.makeText(CheckoutActivity.this, "Failed to checkout", Toast.LENGTH_SHORT).show();
            }
        });
    }
}