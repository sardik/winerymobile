package com.winery.winerymobile.ui.APIhelper;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface BaseApiService {

    // login
    @FormUrlEncoded
    @POST("sales_login")
    Call<ResponseBody> loginRequest(@Field("sales_code") String email,
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
                                    @Field("new_password") String newPassword);
}
