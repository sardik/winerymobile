package com.winery.winerymobile.ui;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.APIhelper.BaseApiService;
import com.winery.winerymobile.ui.APIhelper.UtilsApi;
import com.winery.winerymobile.ui.CreditCardSubmission.FormUploadDocumentSelfie;
import com.winery.winerymobile.ui.dbhelper.SessionManagement;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdatePassword extends BottomSheetDialogFragment {

    /** ButterKnife Code **/
    @BindView(R.id.ti_old_password)
    com.google.android.material.textfield.TextInputLayout tiOldPassword;
    @BindView(R.id.et_old_password)
    com.google.android.material.textfield.TextInputEditText etOldPassword;
    @BindView(R.id.ti_new_password)
    com.google.android.material.textfield.TextInputLayout tiNewPassword;
    @BindView(R.id.et_new_password)
    com.google.android.material.textfield.TextInputEditText etNewPassword;
    @BindView(R.id.btn_Yes)
    com.google.android.material.button.MaterialButton btnYes;
    @BindView(R.id.btn_cancel)
    com.google.android.material.button.MaterialButton btnCancel;
    /** ButterKnife Code **/

    ProgressDialog loading;



    @OnClick(R.id.btn_Yes) void update(){
        if(etOldPassword.getText().toString().equals("") || etOldPassword.getText().toString().equals(null)) {
            tiOldPassword.setError("password lama harus di isi");
        }else if(etNewPassword.getText().toString().equals("") || etNewPassword.getText().toString().equals(null)){
            tiNewPassword.setError("password baru harus di isi");
        }else{

            loading = ProgressDialog.show(getContext(), null, "Harap Tunggu...", true, false);

            // get user data from session
            HashMap<String, String> user = sessionManagement.getUserDetails();
            String loginAs = user.get(SessionManagement.KEY_LOGIN_AS);

            if(loginAs.equals("sales")){
                requestUpdatePassSales();
            }else if(loginAs.equals("verifikator")){
                requestUpdatePassVerif();
            }else if(loginAs.equals("jurutulis")){
                requestUpdateJurtul();
            }
        }
    }

    @OnClick(R.id.btn_cancel) void no(){
        dismiss();
    }

    SessionManagement sessionManagement;
    BaseApiService mApiService;


    public UpdatePassword() {
        // Required empty public constructor
    }

    @NonNull
    @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override public void onShow(DialogInterface dialogInterface) {
                BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
                FrameLayout bottomSheet = (FrameLayout) bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
                bottomSheet.setBackground(null);
                BottomSheetBehavior.from(bottomSheet).setSkipCollapsed(true);
                BottomSheetBehavior.from(bottomSheet).setHideable(true);
            }
        });
        return  dialog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_password, container, false);
        ButterKnife.bind(this, view);

        sessionManagement = new SessionManagement(getContext());
        mApiService = UtilsApi.getAPIService();


        return view;
    }

    private void requestUpdatePassSales(){
        HashMap<String, String> user = sessionManagement.getUserDetails();
        String code = user.get(SessionManagement.KEY_SALES_CODE);

        mApiService.ChangePasswordSalesRequest(code, etNewPassword.getText().toString(),etOldPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getInt("status") == 200){
                                    Toast.makeText(getContext(), "Update Password Berhasil", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(getContext(), error_message, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getContext(), error_message, Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                });
    }

    private void requestUpdatePassVerif(){
        HashMap<String, String> user = sessionManagement.getUserDetails();
        String code = user.get(SessionManagement.KEY_SALES_CODE);

        mApiService.ChangePasswordVerifRequest(code, etNewPassword.getText().toString(),etOldPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getInt("status") == 200){

                                    Toast.makeText(getContext(), "Update Password Berhasil", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(getContext(), error_message, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getContext(), error_message, Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                });
    }

    private void requestUpdateJurtul(){
        HashMap<String, String> user = sessionManagement.getUserDetails();
        String code = user.get(SessionManagement.KEY_SALES_CODE);

        mApiService.ChangePasswordJurtulRequest(code, etNewPassword.getText().toString(),etOldPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getInt("status") == 200){
                                    Toast.makeText(getContext(), "Update Password Berhasil", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(getContext(), error_message, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getContext(), error_message, Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                });
    }

    @Override
    public void onStop(){
        Log.d("onstop", "onStop: jalanbanksubmissionform");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d("odestroy", "onDestroy: jalanbanksubmissionform");
        super.onDestroy();
    }

}
