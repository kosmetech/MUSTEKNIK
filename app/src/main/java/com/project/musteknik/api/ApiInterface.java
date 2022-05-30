package com.project.musteknik.api;


import com.project.musteknik.model.Login;
import com.project.musteknik.model.checkout.ResponseCheckout;
import com.project.musteknik.model.create.ResponseCreate;
import com.project.musteknik.model.detail.ResponseDetail;
import com.project.musteknik.model.masterdata.ResponseMasterData;
import com.project.musteknik.model.postTimeline.ResponsePostTimeline;
import com.project.musteknik.model.timeline.ResponseTimeline;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/login")
    Call<Login> loginRequest(@Field("email") String email,
                             @Field("password") String password);

    @FormUrlEncoded
    @POST("api/listTiketonAllMachine")
    Call<com.project.musteknik.model.ticket.Response> listTicket(@Field("user_id") String userid);


    @FormUrlEncoded
    @POST("api/detailInfoTiket")
    Call<ResponseDetail> detail(@Field("tiket_id") String tiketid);

    @FormUrlEncoded
    @POST("api/getTimeline")
    Call<ResponseTimeline> timeline(@Field("tiket_id") String tiketid);


    @FormUrlEncoded
    @POST("api/createFormTiket")
    Call<ResponseCreate> createTiket(@Field("user_id") String userId,
                                     @Field("kode_mesin") String kode,
                                     @Field("deskripsi") String deskripsi);

    @FormUrlEncoded
    @POST("api/postTimeline")
    Call<ResponsePostTimeline> postTimeline(@Field("user_id") String userId,
                                            @Field("tiket_id") String kode,
                                            @Field("updateStatus") String status,
                                            @Field("kode_mesin") String kodemesin);


    @FormUrlEncoded
    @POST("api/postTimelineLast")
    Call<ResponseCheckout> checkout(@Field("user_id") String userId,
                                    @Field("tiket_id") String kode,
                                    @Field("updateStatus") String status,
                                    @Field("deskripsi_analisis") String analisis,
                                    @Field("deskripsi_perbaikan") String perbaikan,
                                    @Field("anggotaTeknik") String anggota,
                                    @Field("group") String group,
                                    @Field("shift") String shift,
                                    @Field("catatan") String catatan,
                                    @Field("abnormality") String abnormalty,
                                    @Field("running_hours") String hours,
                                    @Field("sparepart") String sparepart,
                                    @Field("utility_outages") String utility,
                                    @Field("terpakai") String terpakai);

    @GET("api/getMasterDataMustKosme")
    Call<ResponseMasterData> getMasterData();




}
