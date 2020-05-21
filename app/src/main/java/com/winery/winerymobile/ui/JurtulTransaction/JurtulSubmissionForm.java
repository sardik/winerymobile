package com.winery.winerymobile.ui.JurtulTransaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.APIhelper.BaseApiService;
import com.winery.winerymobile.ui.APIhelper.UtilsApi;
import com.winery.winerymobile.ui.CreditCardSubmission.CustomerDataForm;
import com.winery.winerymobile.ui.CreditCardSubmission.DialogSuccess;
import com.winery.winerymobile.ui.DetailHistoryTransaksiInputVerif;
import com.winery.winerymobile.ui.VerifikatorTransaction.UploadDataKotorVerif;
import com.winery.winerymobile.ui.dbhelper.SessionManagement;
import com.winery.winerymobile.ui.dbhelper.StateTransactionSales;
import com.winery.winerymobile.ui.helper.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JurtulSubmissionForm extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.appBar)
    com.google.android.material.appbar.AppBarLayout appBar;
    @BindView(R.id.toolbar_main)
    com.google.android.material.appbar.MaterialToolbar toolbarMain;
    @BindView(R.id.toolbar_title)
    ImageView toolbarTitle;
    @BindView(R.id.nested)
    androidx.core.widget.NestedScrollView nested;
    @BindView(R.id.card_bank_bri)
    com.google.android.material.card.MaterialCardView cardBankBri;
    @BindView(R.id.tv_bank_bri)
    TextView tvBankBri;
    @BindView(R.id.iv_bri)
    ImageView ivBri;
    @BindView(R.id.card_bank_bni)
    com.google.android.material.card.MaterialCardView cardBankBni;
    @BindView(R.id.tv_bank_ni)
    TextView tvBankNi;
    @BindView(R.id.iv_bni)
    ImageView ivBni;
    @BindView(R.id.card_bank_bca)
    com.google.android.material.card.MaterialCardView cardBankBca;
    @BindView(R.id.tv_bank_bca)
    TextView tvBankBca;
    @BindView(R.id.iv_bca)
    ImageView ivBca;
    @BindView(R.id.card_bank_cimb)
    com.google.android.material.card.MaterialCardView cardBankCimb;
    @BindView(R.id.tv_bank_cimb)
    TextView tvBankCimb;
    @BindView(R.id.iv_cimb)
    ImageView ivCimb;
    @BindView(R.id.card_bank_mayapada)
    com.google.android.material.card.MaterialCardView cardBankMayapada;
    @BindView(R.id.tv_bank_mayapada)
    TextView tvBankMayapada;
    @BindView(R.id.iv_mayapada)
    ImageView ivMayapada;
    @BindView(R.id.card_bank_dbs)
    com.google.android.material.card.MaterialCardView cardBankDbs;
    @BindView(R.id.tv_bank_dbs)
    TextView tvBankDbs;
    @BindView(R.id.iv_dbs)
    ImageView ivDbs;
    @BindView(R.id.card_bank_mnc)
    com.google.android.material.card.MaterialCardView cardBankMnc;
    @BindView(R.id.tv_bank_mnc)
    TextView tvBankMnc;
    @BindView(R.id.iv_mnc)
    ImageView ivMnc;
    @BindView(R.id.card_bank_uob)
    com.google.android.material.card.MaterialCardView cardBankUob;
    @BindView(R.id.tv_bank_uob)
    TextView tvBankUob;
    @BindView(R.id.iv_uob)
    ImageView ivUob;
    @BindView(R.id.card_bank_mega)
    com.google.android.material.card.MaterialCardView cardBankMega;
    @BindView(R.id.tv_bank_mega)
    TextView tvBankMega;
    @BindView(R.id.iv_mega)
    ImageView ivMega;
    @BindView(R.id.card_bank_panin)
    com.google.android.material.card.MaterialCardView cardBankPanin;
    @BindView(R.id.tv_bank_panin)
    TextView tvBankPanin;
    @BindView(R.id.iv_panin)
    ImageView ivPanin;
    @BindView(R.id.btn_submit)
    com.google.android.material.button.MaterialButton btnSubmit;
    @BindView(R.id.btn_back)
    com.google.android.material.button.MaterialButton btnBack;
    /** ButterKnife Code **/

    @OnClick(R.id.card_bank_bri) void selectedBri(){
        Log.d("tag", "selectedBri: "+ivBri.isSelected());
        if(ivBri.isSelected()){
            ivBri.setSelected(false);
            dateJurtulBri = "";
            JurtulBri = "";
            ivBri.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), android.graphics.PorterDuff.Mode.MULTIPLY);
        }else{
            ivBri.setSelected(true);
            ivBri.setColorFilter(ContextCompat.getColor(this, R.color.green_600), android.graphics.PorterDuff.Mode.MULTIPLY);
            dateJurtulBri = currentDate;
            JurtulBri = nikCode;
        }
    }

    @OnClick(R.id.card_bank_bni) void selectedBni(){
        if(ivBni.isSelected()){
            ivBni.setSelected(false);
            ivBni.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), android.graphics.PorterDuff.Mode.MULTIPLY);
            dateJurtulBni = "";
            JurtulBni = "";
        }else{
            ivBni.setSelected(true);
            ivBni.setColorFilter(ContextCompat.getColor(this, R.color.green_600), android.graphics.PorterDuff.Mode.MULTIPLY);
            dateJurtulBni = currentDate;
            JurtulBni = nikCode;
        }
    }

    @OnClick(R.id.card_bank_bca) void selectedBca(){
        if(ivBca.isSelected()){
            ivBca.setSelected(false);
            ivBca.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), android.graphics.PorterDuff.Mode.MULTIPLY);
            dateJurtulBca = "";
            JurtulBca = "";
        }else{
            ivBca.setSelected(true);
            ivBca.setColorFilter(ContextCompat.getColor(this, R.color.green_600), android.graphics.PorterDuff.Mode.MULTIPLY);
            dateJurtulBca = currentDate;
            JurtulBca = nikCode;
        }
    }

    @OnClick(R.id.card_bank_cimb) void selectedCimb(){
        if(ivCimb.isSelected()){
            ivCimb.setSelected(false);
            ivCimb.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), android.graphics.PorterDuff.Mode.MULTIPLY);
            dateJurtulCimb = "";
            JurtulCimb = "";
        }else{
            ivCimb.setSelected(true);
            ivCimb.setColorFilter(ContextCompat.getColor(this, R.color.green_600), android.graphics.PorterDuff.Mode.MULTIPLY);
            dateJurtulCimb = currentDate;
            JurtulCimb = nikCode;
        }
    }

    @OnClick(R.id.card_bank_mayapada) void selectedMayapada(){
        if(ivMayapada.isSelected()){
            ivMayapada.setSelected(false);
            ivMayapada.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), android.graphics.PorterDuff.Mode.MULTIPLY);
            dateJurtulMayapada = "";
            JurtulMayapada = "";
        }else{
            ivMayapada.setSelected(true);
            ivMayapada.setColorFilter(ContextCompat.getColor(this, R.color.green_600), android.graphics.PorterDuff.Mode.MULTIPLY);
            dateJurtulMayapada = currentDate;
            JurtulMayapada = nikCode;
        }
    }

    @OnClick(R.id.card_bank_dbs) void selectedDbs(){
        if(ivDbs.isSelected()){
            ivDbs.setSelected(false);
            ivDbs.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), android.graphics.PorterDuff.Mode.MULTIPLY);
            dateJurtulDbs = "";
            JurtulDbs = "";
        }else{
            ivDbs.setSelected(true);
            ivDbs.setColorFilter(ContextCompat.getColor(this, R.color.green_600), android.graphics.PorterDuff.Mode.MULTIPLY);
            dateJurtulDbs = currentDate;
            JurtulDbs = nikCode;
        }
    }

    @OnClick(R.id.card_bank_mnc) void selectedMnc(){
        if(ivMnc.isSelected()){
            ivMnc.setSelected(false);
            ivMnc.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), android.graphics.PorterDuff.Mode.MULTIPLY);
            dateJurtulMnc = "";
            JurtulMnc = "";
        }else{
            ivMnc.setSelected(true);
            ivMnc.setColorFilter(ContextCompat.getColor(this, R.color.green_600), android.graphics.PorterDuff.Mode.MULTIPLY);
            dateJurtulMnc = currentDate;
            JurtulMnc = nikCode;
        }
    }

    @OnClick(R.id.card_bank_uob) void selectedUob(){
        if(ivUob.isSelected()){
            ivUob.setSelected(false);
            ivUob.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), android.graphics.PorterDuff.Mode.MULTIPLY);
            dateJurtulUob = "";
            JurtulUob = "";
        }else{
            ivUob.setSelected(true);
            ivUob.setColorFilter(ContextCompat.getColor(this, R.color.green_600), android.graphics.PorterDuff.Mode.MULTIPLY);
            dateJurtulUob = currentDate;
            JurtulUob = nikCode;
        }
    }

    @OnClick(R.id.card_bank_mega) void selectedMega(){
        if(ivMega.isSelected()){
            ivMega.setSelected(false);
            ivMega.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), android.graphics.PorterDuff.Mode.MULTIPLY);
            dateJurtulMega = "";
            JurtulMega = "";
        }else{
            ivMega.setSelected(true);
            ivMega.setColorFilter(ContextCompat.getColor(this, R.color.green_600), android.graphics.PorterDuff.Mode.MULTIPLY);
            dateJurtulMega = currentDate;
            JurtulMega = nikCode;
        }
    }

    @OnClick(R.id.card_bank_panin) void selectedPanin(){
        if(ivPanin.isSelected()){
            ivPanin.setSelected(false);
            ivPanin.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), android.graphics.PorterDuff.Mode.MULTIPLY);
            dateJurtulPanin = "";
            JurtulPanin = "";
        }else{
            ivPanin.setSelected(true);
            ivPanin.setColorFilter(ContextCompat.getColor(this, R.color.green_600), android.graphics.PorterDuff.Mode.MULTIPLY);
            dateJurtulPanin = currentDate;
            JurtulPanin = nikCode;
        }
    }

    @OnClick(R.id.btn_back) void back(){
        onBackPressed();
        finish();
    }

    @OnClick(R.id.btn_submit) void next(){
        if( !ivBri.isSelected() &&
                !ivBni.isSelected() &&
                !ivBca.isSelected() &&
                !ivCimb.isSelected() &&
                !ivMayapada.isSelected() &&
                !ivDbs.isSelected() &&
                !ivMnc.isSelected() &&
                !ivUob.isSelected() &&
                !ivMega.isSelected() &&
                !ivPanin.isSelected()){

            selectionValidate();
        }else{
            // posting data
            submitCollectionData();

        }
    }


    SessionManagement sessionManagement;
    StateTransactionSales stateTransactionSales;

    String dateJurtulBri = "", dateJurtulBni = "", dateJurtulBca = "",
            dateJurtulCimb = "", dateJurtulMayapada = "", dateJurtulDbs = "",
            dateJurtulMnc = "" , dateJurtulUob = "", dateJurtulMega = "", dateJurtulPanin = "";

    String JurtulBri = "", JurtulBni = "", JurtulBca = "",
            JurtulCimb = "", JurtulMayapada = "", JurtulDbs = "",
            JurtulMnc = "" , JurtulUob = "", JurtulMega = "", JurtulPanin = "";

    String transactionId, nikCode;
    String currentDate;
    ProgressDialog loading;
    BaseApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jurtul_submission_form);
        ButterKnife.bind(this);

        sessionManagement = new SessionManagement(this);
        stateTransactionSales = new StateTransactionSales(this);
        mApiService = UtilsApi.getAPIService();


        currentDate = Utils.getCurrentDateTime();

        ivBri.setSelected(false);
        ivBni.setSelected(false);
        ivBca.setSelected(false);
        ivCimb.setSelected(false);
        ivMayapada.setSelected(false);
        ivDbs.setSelected(false);
        ivMnc.setSelected(false);
        ivUob.setSelected(false);
        ivMega.setSelected(false);
        ivPanin.setSelected(false);

        HashMap<String, String> session = sessionManagement.getUserDetails();
        HashMap<String, String> transactionID = sessionManagement.getTransactionID();

        // get list state of bank list
        HashMap<String, String> statusBank = stateTransactionSales.getListBank();

        // get status from back stack
        nikCode = session.get(sessionManagement.KEY_SALES_CODE);
        transactionId = transactionID.get(sessionManagement.KEY_TRANSACTION_ID);
        String StatusBri = statusBank.get(stateTransactionSales.KEY_STATUS_BRI);
        String StatusBni = statusBank.get(stateTransactionSales.KEY_STATUS_BNI);
        String StatusBca = statusBank.get(stateTransactionSales.KEY_STATUS_BCA);
        String StatusNiaga = statusBank.get(stateTransactionSales.KEY_STATUS_CIMB);
        String StatusMayapada = statusBank.get(stateTransactionSales.KEY_STATUS_MAYAPADA);
        String StatusUob = statusBank.get(stateTransactionSales.KEY_STATUS_UOB);
        String StatusMnc = statusBank.get(stateTransactionSales.KEY_STATUS_MNC);
        String StatusPanin = statusBank.get(stateTransactionSales.KEY_STATUS_PANIN);
        String StatusDbs = statusBank.get(stateTransactionSales.KEY_STATUS_DBS);
        String StatusMega = statusBank.get(stateTransactionSales.KEY_STATUS_MEGA);

        if(StatusBri.equals("NO")){
           cardBankBri.setVisibility(View.GONE);
        }
        if(StatusBni.equals("NO")){
            cardBankBni.setVisibility(View.GONE);
        }
        if(StatusBca.equals("NO")){
            cardBankBca.setVisibility(View.GONE);
        }
        if(StatusNiaga.equals("NO")){
            cardBankCimb.setVisibility(View.GONE);
        }
        if(StatusMayapada.equals("NO")){
            cardBankMayapada.setVisibility(View.GONE);
        }
        if(StatusUob.equals("NO")){
            cardBankUob.setVisibility(View.GONE);
        }
        if(StatusMnc.equals("NO")){
            cardBankMnc.setVisibility(View.GONE);
        }
        if(StatusPanin.equals("NO")){
            cardBankPanin.setVisibility(View.GONE);
        }
        if(StatusDbs.equals("NO")){
            cardBankDbs.setVisibility(View.GONE);
        }
        if(StatusMega.equals("NO")){
            cardBankMega.setVisibility(View.GONE);
        }
    }


    public void submitCollectionData() {

        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);
        loading.setCanceledOnTouchOutside(false);

        HashMap<String, String> session = sessionManagement.getUserDetails();
        HashMap<String, String> transactionID = sessionManagement.getTransactionID();


        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("transaction_id", createPartFromString(transactionID.get(sessionManagement.KEY_TRANSACTION_ID)));
        map.put("tanggal_bri", createPartFromString(dateJurtulBri));
        map.put("juru_tulis_bri", createPartFromString(JurtulBri));
        map.put("tanggal_bni", createPartFromString(dateJurtulBni));
        map.put("juru_tulis_bni", createPartFromString(JurtulBni));
        map.put("tanggal_bca", createPartFromString(dateJurtulBca));
        map.put("juru_tulis_bca", createPartFromString(JurtulBca));
        map.put("tanggal_niaga", createPartFromString(dateJurtulCimb));
        map.put("juru_tulis_niaga", createPartFromString(JurtulCimb));
        map.put("tanggal_uob", createPartFromString(dateJurtulUob));
        map.put("juru_tulis_uob", createPartFromString(JurtulUob));
        map.put("tanggal_mnc", createPartFromString(dateJurtulMnc));
        map.put("juru_tulis_mnc", createPartFromString(JurtulMnc));
        map.put("tanggal_dbs", createPartFromString(dateJurtulDbs));
        map.put("juru_tulis_dbs", createPartFromString(JurtulDbs));
        map.put("tanggal_mayapada", createPartFromString(dateJurtulMayapada));
        map.put("juru_tulis_mayapada", createPartFromString(JurtulMayapada));
        map.put("tanggal_mega", createPartFromString(dateJurtulMega));
        map.put("juru_tulis_mega", createPartFromString(JurtulMega));
        map.put("tanggal_panin", createPartFromString(dateJurtulPanin));
        map.put("juru_tulis_panin", createPartFromString(JurtulPanin));


        // finally, kirim map dan body pada param interface retrofit
        Call<ResponseBody> call = mApiService.jurtulInput(map);
        call.enqueue(
                new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getInt("status") == 200){

                                    Toast.makeText(JurtulSubmissionForm.this, "success", Toast.LENGTH_SHORT).show();
                                    DialogSuccess bottomSheetFragment = new DialogSuccess();
                                    bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());

                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(JurtulSubmissionForm.this, error_message, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(JurtulSubmissionForm.this, error_message, Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                }
        );
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        if(descriptionString == null || descriptionString.equals(null) || descriptionString.isEmpty()){
            descriptionString = "";
        }
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }

    public void selectionValidate(){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.nested), "Pilih Bank yang sudah diselesaikan", Snackbar.LENGTH_SHORT)
                .setAction("Action", null);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        snackbar.show();
    }

    @Override
    protected void onStop(){
        Log.d("onstop", "onStop: jalanbanksubmissionform");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("odestroy", "onDestroy: jalanbanksubmissionform");
        super.onDestroy();
    }
}
