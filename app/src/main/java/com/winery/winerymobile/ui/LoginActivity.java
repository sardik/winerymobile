package com.winery.winerymobile.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.APIhelper.BaseApiService;
import com.winery.winerymobile.ui.APIhelper.UtilsApi;
import com.winery.winerymobile.ui.CreditCardSubmission.FormUploadDocumentSelfie;
import com.winery.winerymobile.ui.dbhelper.SessionManagement;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.ti_nik)
    com.google.android.material.textfield.TextInputLayout tiNik;
    @BindView(R.id.et_nik)
    com.google.android.material.textfield.TextInputEditText etNik;
    @BindView(R.id.ti_password)
    com.google.android.material.textfield.TextInputLayout tiPassword;
    @BindView(R.id.et_password)
    com.google.android.material.textfield.TextInputEditText etPassword;
    @BindView(R.id.btn_login)
    com.google.android.material.button.MaterialButton btnLogin;
    @BindView(R.id.btn_back)
    com.google.android.material.button.MaterialButton btnBack;
    /** ButterKnife Code **/


    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;

    SessionManagement sessionManagement;

    @OnClick(R.id.btn_login) void login(){

        Intent intent = getIntent();
        String loginAs = intent.getStringExtra("loginAs");

        if(etNik.getText().toString().equals("") || etNik.getText().toString().equals(null)){
            tiNik.setError("Nik harus di isi");
        }else if(etPassword.getText().toString().equals("") || etPassword.getText().toString().equals(null)){
            tiPassword.setError("password harus di isi");
        }else{
            loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);

            if(loginAs.equals("sales")){
                requestLoginSales();
            }else if(loginAs.equals("verifikator")){
                requestLoginVerif();
            }else if(loginAs.equals("jurutulis")){
                requestLoginJurtul();
            }

        }
    }

    @OnClick(R.id.btn_back) void back(){
        onBackPressed();
        finish();
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mContext = this;
        mApiService = UtilsApi.getAPIService();

        sessionManagement = new SessionManagement(getApplicationContext());
    }

    private void requestLoginSales(){
        mApiService.loginRequestSales(etNik.getText().toString(), etPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getInt("status") == 200){

                                    String nama = jsonRESULTS.getJSONObject("data").getString("name");
                                    String alias = jsonRESULTS.getJSONObject("data").getString("alias");
                                    String position = jsonRESULTS.getJSONObject("data").getString("position");
                                    String level = jsonRESULTS.getJSONObject("data").getString("level");
                                    String sales_code = jsonRESULTS.getJSONObject("data").getString("sales_code");
                                    String status = jsonRESULTS.getJSONObject("data").getString("status");
                                    String password = jsonRESULTS.getJSONObject("data").getString("password");
                                    String region = jsonRESULTS.getJSONObject("data").getString("region");



                                    // saving state
                                    sessionManagement.createLoginSession(nama, alias, position, level, sales_code, status, password, region, "sales");

                                    Intent intent = new Intent(LoginActivity.this, ParentHomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(LoginActivity.this, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        String error_message = "server error silahkan coba lagi";
                        Toast.makeText(LoginActivity.this, error_message, Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                });
    }

    private void requestLoginVerif(){
        mApiService.loginRequestVerif(etNik.getText().toString(), etPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getInt("status") == 200){

                                    String nama = jsonRESULTS.getJSONObject("data").getString("name");
                                    String alias = jsonRESULTS.getJSONObject("data").getString("alias");
                                    String position = jsonRESULTS.getJSONObject("data").getString("position");
                                    String level = jsonRESULTS.getJSONObject("data").getString("level");
                                    String sales_code = jsonRESULTS.getJSONObject("data").getString("verify_code");
                                    String status = jsonRESULTS.getJSONObject("data").getString("status");
                                    String password = jsonRESULTS.getJSONObject("data").getString("password");
                                    String region = "";


                                    Log.d("code ", "code "+sales_code);

                                    // saving state
                                    sessionManagement.createLoginSession(nama, alias, position, level, sales_code, status, password, region, "verifikator");

                                    Intent intent = new Intent(LoginActivity.this, ParentHomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(LoginActivity.this, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        String error_message = "server error silahkan coba lagi";
                        Toast.makeText(LoginActivity.this, error_message, Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                });
    }

    private void requestLoginJurtul(){
        mApiService.loginRequestJurtul(etNik.getText().toString(), etPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getInt("status") == 200){

                                    String nama = jsonRESULTS.getJSONObject("data").getString("name");
                                    String alias = jsonRESULTS.getJSONObject("data").getString("alias");
                                    String position = jsonRESULTS.getJSONObject("data").getString("position");
                                    String level = jsonRESULTS.getJSONObject("data").getString("level");
                                    String sales_code = jsonRESULTS.getJSONObject("data").getString("juru_tulis_code");
                                    String status = jsonRESULTS.getJSONObject("data").getString("status");
                                    String password = jsonRESULTS.getJSONObject("data").getString("password");
                                    String region = "";


                                    Log.d("code ", "code "+sales_code);

                                    // saving state
                                    sessionManagement.createLoginSession(nama, alias, position, level, sales_code, status, password, region, "jurutulis" );

                                    Intent intent = new Intent(LoginActivity.this, ParentHomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(LoginActivity.this, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        String error_message = "server error silahkan coba lagi";
                        Toast.makeText(LoginActivity.this, error_message, Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                });
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
