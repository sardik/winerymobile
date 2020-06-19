package com.winery.winerymobile.ui.APIhelper;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface BaseApiService {

    // login
    @FormUrlEncoded
    @POST("sales_login")
    Call<ResponseBody> loginRequestSales(@Field("sales_code") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("verify_login")
    Call<ResponseBody> loginRequestVerif(@Field("verify_code") String email,
                                         @Field("password") String password);

    @FormUrlEncoded
    @POST("juru_tulis_login")
    Call<ResponseBody> loginRequestJurtul(@Field("juru_tulis_code") String email,
                                         @Field("password") String password);

//    @Multipart
//    @POST("sales_input")
//    Call<ResponseBody> salesInput(@Field("bri") String bri,
//                                  @Field("bni") String bni,
//                                  @Field("bca") String bca,
//                                  @Field("cimb_niaga") String cimb_niaga,
//                                  @Field("mayapada") String mayapada,
//                                  @Field("dbs") String dbs,
//                                  @Field("mnc") String mnc,
//                                  @Field("uob") String uob,
//                                  @Field("mega") String mega,
//                                  @Field("nama") String nama,
//                                  @Field("nik") String nik,
//                                  @Field("tanggal_lahir") String tanggal_lahir,
//                                  @Field("handphone_1") String handphone_1,
//                                  @Field("handphone_2") String handphone_2,
//                                  @Field("nama_ibu_kandung") String nama_ibu_kandung,
//                                  @Field("nama_perusahaan") String nama_perusahaan,
//                                  @Field("alamat_perusahaan") String alamat_perusahaan,
//                                  @Field("telephone_kantor") String telephone_kantor,
//                                  @Field("nama_emergency_contact") String nama_emergency_contact,
//                                  @Field("hubungan") String hubungan,
//                                  @Field("sales_code") String sales_code,
//                                  @Field("sales_name") String sales_name);


    // get list hadiah

    @GET("hadiah_list")
    Call<ResponseBody> getListDataHadiah();

    @Multipart
    @POST("sales_input")
    Call<ResponseBody> salesInput(@Part MultipartBody.Part photoKtp,
                                  @Part MultipartBody.Part photoNpwp,
                                  @Part MultipartBody.Part photoIDCard,
                                  @Part MultipartBody.Part photoCC,
                                  @Part MultipartBody.Part photoDoc1,
                                  @Part MultipartBody.Part photoDoc2,
                                  @Part MultipartBody.Part photoBri,
                                  @Part MultipartBody.Part photoBni,
                                  @Part MultipartBody.Part photoCimb,
                                  @Part MultipartBody.Part photoMayapada,
                                   @PartMap Map<String, RequestBody> text);

    // change password sales
    @FormUrlEncoded
    @POST("sales_change_password")
    Call<ResponseBody> ChangePasswordSalesRequest(@Field("sales_code") String salesCode,
                                    @Field("new_password") String newPassword,
                                                  @Field("old_password") String oldPassword);

    // change password verif
    @FormUrlEncoded
    @POST("verify_change_password")
    Call<ResponseBody> ChangePasswordVerifRequest(@Field("verify_code") String salesCode,
                                                  @Field("new_password") String newPassword,
                                                  @Field("old_password") String oldPassword);

    // change password jurtul
    @FormUrlEncoded
    @POST("juru_tulis_change_password")
    Call<ResponseBody> ChangePasswordJurtulRequest(@Field("juru_tulis_code") String salesCode,
                                                  @Field("new_password") String newPassword,
                                                  @Field("old_password") String oldPassword);

    // get history cc
    @FormUrlEncoded
    @POST("history_input_sales")
    Call<ResponseBody> getListHistoryCc(@Field("start_date") String startDate,
                                                   @Field("end_date") String endDate,
                                                   @Field("sales_code") String salesCode);

    // get verify transaction history cc
    @FormUrlEncoded
    @POST("verify_transaction_list")
    Call<ResponseBody> getListVerifyTransactionList(@Field("start_date") String startDate,
                                                    @Field("end_date") String endDate,
                                                    @Field("verify_code") String verifyCode);


    // history input verif
    @FormUrlEncoded
    @POST("history_input_verify")
    Call<ResponseBody> getListVerifyHistoryInputVerif(@Field("start_date") String startDate,
                                                    @Field("end_date") String endDate,
                                                    @Field("verify_code") String verifyCode);

    // get history cc detail
    @FormUrlEncoded
    @POST("detail_history_input_sales")
    Call<ResponseBody> getListDetailHistoryCc(@Field("transaction_id") String transactionId);


    // verivy input
    @Multipart
    @POST("verify_sales_input")
    Call<ResponseBody> verifySalesInput(@Part MultipartBody.Part photoDataKotor,
                                  @PartMap Map<String, RequestBody> text);


    // get list pending data juru tulis
    @FormUrlEncoded
    @POST("juru_tulis_list")
    Call<ResponseBody> getListdataPendingJurtul(@Field("start_date") String startDate,
                                                @Field("end_date") String endDate,
                                                @Field("juru_tulis_code") String jurtulCode);


    // jurtul input
    @Multipart
    @POST("juru_tulis_input")
    Call<ResponseBody> jurtulInput(@PartMap Map<String, RequestBody> text);

    // history input jurtul
    @FormUrlEncoded
    @POST("juru_tulis_history")
    Call<ResponseBody> getListJurtulHistoryInputJurtul(@Field("start_date") String startDate,
                                                      @Field("end_date") String endDate,
                                                      @Field("juru_tulis_code") String juruTulisCode);

    // get check dup
    @FormUrlEncoded
    @POST("check_duplicate")
    Call<ResponseBody> getCheckDuplicateData(@Field("name") String nik,
                                                @Field("dob") String dob);


    // get message recontest
    @FormUrlEncoded
    @POST("list_transaction")
    Call<ResponseBody> getListMessageRecontest(@Field("sales_code") String salesCode,
                                        @Field("bank") String bank,
                                        @Field("verif") String verif,
                                        @Field("sort") String sort);


    // update data for recontest
    @Multipart
    @POST("transaction_edit")
    Call<ResponseBody> UpdateDataForRecontest(@Part MultipartBody.Part photoKtp,
                                  @Part MultipartBody.Part photoNpwp,
                                  @Part MultipartBody.Part photoIDCard,
                                  @Part MultipartBody.Part photoCC,
                                  @Part MultipartBody.Part photoDoc1,
                                  @Part MultipartBody.Part photoDoc2,
                                  @Part MultipartBody.Part photoBri,
                                  @Part MultipartBody.Part photoBni,
                                  @Part MultipartBody.Part photoCimb,
                                  @Part MultipartBody.Part photoMayapada,
                                  @PartMap Map<String, RequestBody> text);
}
