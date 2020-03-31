package com.winery.winerymobile.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.APIhelper.BaseApiService;
import com.winery.winerymobile.ui.APIhelper.UtilsApi;
import com.winery.winerymobile.ui.adapter.LIstHistoriCCAdapter;
import com.winery.winerymobile.ui.dbhelper.SessionManagement;
import com.winery.winerymobile.ui.model.HistoryCc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailHistoryTransactionInputSales extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.appBar)
    com.google.android.material.appbar.AppBarLayout appBar;
    @BindView(R.id.toolbar_main)
    com.google.android.material.appbar.MaterialToolbar toolbarMain;
    @BindView(R.id.toolbar_title)
    ImageView toolbarTitle;
    @BindView(R.id.nested)
    androidx.core.widget.NestedScrollView nested;
    @BindView(R.id.ll_section1)
    LinearLayout llSection1;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_nik)
    TextView tvNik;
    @BindView(R.id.tv_dob)
    TextView tvDob;
    @BindView(R.id.tv_handphone)
    TextView tvHandphone;
    @BindView(R.id.tv_mother_name)
    TextView tvMotherName;
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.tv_company_address)
    TextView tvCompanyAddress;
    @BindView(R.id.tv_company_phone)
    TextView tvCompanyPhone;
    @BindView(R.id.tv_emergency_contact)
    TextView tvEmergencyContact;
    @BindView(R.id.tv_relationship)
    TextView tvRelationship;
    @BindView(R.id.card_bank_bri)
    com.google.android.material.card.MaterialCardView cardBankBri;
    @BindView(R.id.status_bri)
    TextView statusBri;
    @BindView(R.id.tgl_pengajuan_bri)
    TextView tglPengajuanBri;
    @BindView(R.id.status_verif_bri)
    TextView statusVerifBri;
    @BindView(R.id.tgl_submit_bri)
    TextView tglSubmitBri;
    @BindView(R.id.tv_status_bri)
    TextView tvStatusBri;
    @BindView(R.id.card_bank_bni)
    com.google.android.material.card.MaterialCardView cardBankBni;
    @BindView(R.id.status_bni)
    TextView statusBni;
    @BindView(R.id.tgl_pengajuan_bni)
    TextView tglPengajuanBni;
    @BindView(R.id.status_verif_bni)
    TextView statusVerifBni;
    @BindView(R.id.tgl_submit_bni)
    TextView tglSubmitBni;
    @BindView(R.id.tv_status_bni)
    TextView tvStatusBni;
    @BindView(R.id.card_bank_bca)
    com.google.android.material.card.MaterialCardView cardBankBca;
    @BindView(R.id.status_bca)
    TextView statusBca;
    @BindView(R.id.tgl_pengajuan_bca)
    TextView tglPengajuanBca;
    @BindView(R.id.status_verif_bca)
    TextView statusVerifBca;
    @BindView(R.id.tgl_submit_bca)
    TextView tglSubmitBca;
    @BindView(R.id.tv_status_bca)
    TextView tvStatusBca;
    @BindView(R.id.card_bank_cimb)
    com.google.android.material.card.MaterialCardView cardBankCimb;
    @BindView(R.id.status_cimb)
    TextView statusCimb;
    @BindView(R.id.tgl_pengajuan_cimb)
    TextView tglPengajuanCimb;
    @BindView(R.id.status_verif_cimb)
    TextView statusVerifCimb;
    @BindView(R.id.tgl_submit_cimb)
    TextView tglSubmitCimb;
    @BindView(R.id.tv_status_cimb)
    TextView tvStatusCimb;
    @BindView(R.id.card_bank_mayapada)
    com.google.android.material.card.MaterialCardView cardBankMayapada;
    @BindView(R.id.status_mayapada)
    TextView statusMayapada;
    @BindView(R.id.tgl_pengajuan_mayapada)
    TextView tglPengajuanMayapada;
    @BindView(R.id.status_verif_mayapada)
    TextView statusVerifMayapada;
    @BindView(R.id.tgl_submit_mayapada)
    TextView tglSubmitMayapada;
    @BindView(R.id.tv_status_mayapada)
    TextView tvStatusMayapada;
    @BindView(R.id.card_bank_dbs)
    com.google.android.material.card.MaterialCardView cardBankDbs;
    @BindView(R.id.status_dbs)
    TextView statusDbs;
    @BindView(R.id.tgl_pengajuan_dbs)
    TextView tglPengajuanDbs;
    @BindView(R.id.status_verif_dbs)
    TextView statusVerifDbs;
    @BindView(R.id.tgl_submit_dbs)
    TextView tglSubmitDbs;
    @BindView(R.id.tv_status_dbs)
    TextView tvStatusDbs;
    @BindView(R.id.card_bank_mnc)
    com.google.android.material.card.MaterialCardView cardBankMnc;
    @BindView(R.id.status_mnc)
    TextView statusMnc;
    @BindView(R.id.tgl_pengajuan_mnc)
    TextView tglPengajuanMnc;
    @BindView(R.id.status_verif_mnc)
    TextView statusVerifMnc;
    @BindView(R.id.tgl_submit_mnc)
    TextView tglSubmitMnc;
    @BindView(R.id.tv_status_mnc)
    TextView tvStatusMnc;
    @BindView(R.id.card_bank_uob)
    com.google.android.material.card.MaterialCardView cardBankUob;
    @BindView(R.id.status_uob)
    TextView statusUob;
    @BindView(R.id.tgl_pengajuan_uob)
    TextView tglPengajuanUob;
    @BindView(R.id.status_verif_uob)
    TextView statusVerifUob;
    @BindView(R.id.tgl_submit_uob)
    TextView tglSubmitUob;
    @BindView(R.id.tv_status_uob)
    TextView tvStatusUob;
    @BindView(R.id.card_bank_mega)
    com.google.android.material.card.MaterialCardView cardBankMega;
    @BindView(R.id.status_mega)
    TextView statusMega;
    @BindView(R.id.tgl_pengajuan_mega)
    TextView tglPengajuanMega;
    @BindView(R.id.status_verif_mega)
    TextView statusVerifMega;
    @BindView(R.id.tgl_submit_mega)
    TextView tglSubmitMega;
    @BindView(R.id.tv_status_mega)
    TextView tvStatusMega;
    /** ButterKnife Code **/


    ProgressDialog loading;
    BaseApiService mApiService;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history_transaction_input_sales);
        ButterKnife.bind(this);

        mApiService = UtilsApi.getAPIService();
        initToolbar();

        getListDetailHistoryCc();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setNavigationIcon(R.drawable.ic_nav_white);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getListDetailHistoryCc(){
        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);

        Intent intent = getIntent();
        String idTransaction = intent.getStringExtra("param");
        mApiService.getListDetailHistoryCc(idTransaction)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getInt("status") == 200){

                                    String id = jsonRESULTS.getJSONObject("data").getString("id");
                                    String tanggal = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    String nik = jsonRESULTS.getJSONObject("data").getString("nik");
                                    String nama = jsonRESULTS.getJSONObject("data").getString("nama");
                                    String dob = jsonRESULTS.getJSONObject("data").getString("dob");
                                    String telepon = jsonRESULTS.getJSONObject("data").getString("telepon");
                                    String ibu = jsonRESULTS.getJSONObject("data").getString("ibu");
                                    String perusahaan = jsonRESULTS.getJSONObject("data").getString("perusahaan");
                                    String alamatkantor = jsonRESULTS.getJSONObject("data").getString("alamatkantor");
                                    String teleponkantor = jsonRESULTS.getJSONObject("data").getString("teleponkantor");
                                    String econtact = jsonRESULTS.getJSONObject("data").getString("econtact");
                                    String hubunganecontact = jsonRESULTS.getJSONObject("data").getString("hubunganecontact");

                                    tvName.setText(nama);
                                    tvNik.setText(nik);
                                    tvDob.setText(dob);
                                    tvHandphone.setText(telepon);
                                    tvMotherName.setText(ibu);
                                    tvCompanyName.setText(perusahaan);
                                    tvCompanyAddress.setText(alamatkantor);
                                    tvCompanyPhone.setText(teleponkantor);
                                    tvEmergencyContact.setText(econtact);
                                    tvRelationship.setText(hubunganecontact);

                                    // bank BRI
                                    String StatusBri,VerifikasiBri,tanggalSubmitBri, statusappBri;
                                    StatusBri = jsonRESULTS.getJSONObject("data").getString("bri");
                                    String tanggalPengajuanBri = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiBri = jsonRESULTS.getJSONObject("data").getString("verifbri");
                                    tanggalSubmitBri = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitbri");
                                    statusappBri = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanbri");

                                    if(StatusBri.equals("") || StatusBri.equals("null")){
                                        StatusBri = "-";
                                    }
                                    if(VerifikasiBri.equals("") || VerifikasiBri.equals("null")){
                                        VerifikasiBri = "-";
                                    }
                                    if(tanggalSubmitBri.equals("") || tanggalSubmitBri.equals("null")){
                                        tanggalSubmitBri = "-";
                                    }
                                    if(statusappBri.equals("") || statusappBri.equals("null")){
                                        statusappBri = "-";
                                    }

                                    // bank
                                    statusBri.setText(StatusBri);
                                    tglPengajuanBri.setText(tanggalPengajuanBri);
                                    statusVerifBri.setText(VerifikasiBri);
                                    tglSubmitBri.setText(tanggalSubmitBri);
                                    tvStatusBri.setText(statusappBri);

                                    // bank BNI
                                    String StatusBni, VerifikasiBni, tanggalSubmitBni, statusappBni;
                                    StatusBni = jsonRESULTS.getJSONObject("data").getString("bni");
                                    String tanggalPengajuanBni = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiBni = jsonRESULTS.getJSONObject("data").getString("verifbni");
                                    tanggalSubmitBni = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitbni");
                                    statusappBni = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanbni");

                                    if(StatusBni.equals("") || StatusBni.equals("null")){
                                        StatusBni = "-";
                                    }
                                    if(VerifikasiBni.equals("") || VerifikasiBni.equals("null")){
                                        VerifikasiBni = "-";
                                    }
                                    if(tanggalSubmitBni.equals("") || tanggalSubmitBni.equals("null")){
                                        tanggalSubmitBni = "-";
                                    }
                                    if(statusappBni.equals("") || statusappBni.equals("null")){
                                        statusappBni = "-";
                                    }

                                    // bank
                                    statusBni.setText(StatusBni);
                                    tglPengajuanBni.setText(tanggalPengajuanBni);
                                    statusVerifBni.setText(VerifikasiBni);
                                    tglSubmitBni.setText(tanggalSubmitBni);
                                    tvStatusBni.setText(statusappBni);

                                    // bank BCA
                                    String StatusBca, VerifikasiBca, tanggalSubmitBca, statusappBca;
                                    StatusBca = jsonRESULTS.getJSONObject("data").getString("bca");
                                    String tanggalPengajuanBca = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiBca = jsonRESULTS.getJSONObject("data").getString("verifbca");
                                    tanggalSubmitBca = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitbca");
                                    statusappBca = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanbca");

                                    if(StatusBca.equals("") || StatusBca.equals("null")){
                                        StatusBca = "-";
                                    }
                                    if(VerifikasiBca.equals("") || VerifikasiBca.equals("null")){
                                        VerifikasiBca = "-";
                                    }
                                    if(tanggalSubmitBca.equals("") || tanggalSubmitBca.equals("null")){
                                        tanggalSubmitBca = "-";
                                    }
                                    if(statusappBca.equals("") || statusappBca.equals("null")){
                                        statusappBca = "-";
                                    }

                                    // bank
                                    statusBca.setText(StatusBca);
                                    tglPengajuanBca.setText(tanggalPengajuanBca);
                                    statusVerifBca.setText(VerifikasiBca);
                                    tglSubmitBca.setText(tanggalSubmitBca);
                                    tvStatusBca.setText(statusappBca);

                                    // bank CIMB
                                    String StatusCimb,VerifikasiCimb, tanggalSubmitCimb,statusappCimb;
                                    StatusCimb = jsonRESULTS.getJSONObject("data").getString("niaga");
                                    String tanggalPengajuanCimb = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiCimb = jsonRESULTS.getJSONObject("data").getString("verifniaga");
                                    tanggalSubmitCimb = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitniaga");
                                    statusappCimb = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanniaga");

                                    if(StatusCimb.equals("") || StatusCimb.equals("null")){
                                        StatusCimb = "-";
                                    }
                                    if(VerifikasiCimb.equals("") || VerifikasiCimb.equals("null")){
                                        VerifikasiCimb = "-";
                                    }
                                    if(tanggalSubmitCimb.equals("") || tanggalSubmitCimb.equals("null")){
                                        tanggalSubmitCimb = "-";
                                    }
                                    if(statusappCimb.equals("") || statusappCimb.equals("null")){
                                        statusappCimb = "-";
                                    }

                                    // bank
                                    statusCimb.setText(StatusCimb);
                                    tglPengajuanCimb.setText(tanggalPengajuanCimb);
                                    statusVerifCimb.setText(VerifikasiCimb);
                                    tglSubmitCimb.setText(tanggalSubmitCimb);
                                    tvStatusCimb.setText(statusappCimb);

                                    // bank Mayapada
                                    String StatusMayapada, VerifikasiMayapada, tanggalSubmitMayapada, statusappMayapada;
                                    StatusMayapada = jsonRESULTS.getJSONObject("data").getString("mayapada");
                                    String tanggalPengajuanMayapada = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiMayapada = jsonRESULTS.getJSONObject("data").getString("verifmayapada");
                                    tanggalSubmitMayapada = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitmayapada");
                                    statusappMayapada= jsonRESULTS.getJSONObject("data").getString("keteranganbalikanmayapada");

                                    if(StatusMayapada.equals("") || StatusMayapada.equals("null")){
                                        StatusMayapada = "-";
                                    }
                                    if(VerifikasiMayapada.equals("") || VerifikasiMayapada.equals("null")){
                                        VerifikasiMayapada = "-";
                                    }
                                    if(tanggalSubmitMayapada.equals("") || tanggalSubmitMayapada.equals("null")){
                                        tanggalSubmitMayapada = "-";
                                    }
                                    if(statusappMayapada.equals("") || statusappMayapada.equals("null")){
                                        statusappMayapada = "-";
                                    }

                                    // bank
                                    statusMayapada.setText(StatusMayapada);
                                    tglPengajuanMayapada.setText(tanggalPengajuanMayapada);
                                    statusVerifMayapada.setText(VerifikasiMayapada);
                                    tglSubmitMayapada.setText(tanggalSubmitMayapada);
                                    tvStatusMayapada.setText(statusappMayapada);

                                    // bank Dbs
                                    String StatusDbs, VerifikasiDbs, tanggalSubmitDbs, statusappDbs;
                                    StatusDbs = jsonRESULTS.getJSONObject("data").getString("dbs");
                                    String tanggalPengajuanDbs = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiDbs = jsonRESULTS.getJSONObject("data").getString("verifdbs");
                                    tanggalSubmitDbs = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitdbs");
                                    statusappDbs = jsonRESULTS.getJSONObject("data").getString("keteranganbalikandbs");

                                    if(StatusDbs.equals("") || StatusDbs.equals("null")){
                                        StatusDbs = "-";
                                    }
                                    if(VerifikasiDbs.equals("") || VerifikasiDbs.equals("null")){
                                        VerifikasiDbs = "-";
                                    }
                                    if(tanggalSubmitDbs.equals("") || tanggalSubmitDbs.equals("null")){
                                        tanggalSubmitDbs = "-";
                                    }
                                    if(statusappDbs.equals("") || statusappDbs.equals("null")){
                                        statusappDbs = "-";
                                    }

                                    // bank
                                    statusDbs.setText(StatusDbs);
                                    tglPengajuanDbs.setText(tanggalPengajuanDbs);
                                    statusVerifDbs.setText(VerifikasiDbs);
                                    tglSubmitDbs.setText(tanggalSubmitDbs);
                                    tvStatusDbs.setText(statusappDbs);

                                    // bank MNC
                                    String StatusMnc, VerifikasiMnc, tanggalSubmitMnc, statusappMnc;
                                    StatusMnc = jsonRESULTS.getJSONObject("data").getString("mnc");
                                    String tanggalPengajuanMnc = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiMnc = jsonRESULTS.getJSONObject("data").getString("verifmnc");
                                    tanggalSubmitMnc = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitmnc");
                                    statusappMnc = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanmnc");

                                    if(StatusMnc.equals("") || StatusMnc.equals("null")){
                                        StatusMnc = "-";
                                    }
                                    if(VerifikasiMnc.equals("") || VerifikasiMnc.equals("null")){
                                        VerifikasiMnc = "-";
                                    }
                                    if(tanggalSubmitMnc.equals("") || tanggalSubmitMnc.equals("null")){
                                        tanggalSubmitMnc = "-";
                                    }
                                    if(statusappMnc.equals("") || statusappMnc.equals("null")){
                                        statusappMnc = "-";
                                    }

                                    // bank
                                    statusMnc.setText(StatusMnc);
                                    tglPengajuanMnc.setText(tanggalPengajuanMnc);
                                    statusVerifMnc.setText(VerifikasiMnc);
                                    tglSubmitMnc.setText(tanggalSubmitMnc);
                                    tvStatusMnc.setText(statusappMnc);

                                    // bank UOB
                                    String StatusUob, VerifikasiUob, tanggalSubmitUob, statusappUob;
                                    StatusUob = jsonRESULTS.getJSONObject("data").getString("uob");
                                    String tanggalPengajuanUob = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiUob = jsonRESULTS.getJSONObject("data").getString("verifuob");
                                    tanggalSubmitUob = jsonRESULTS.getJSONObject("data").getString("tanggalsubmituob");
                                    statusappUob = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanuob");

                                    if(StatusUob.equals("") || StatusUob.equals("null")){
                                        StatusUob = "-";
                                    }
                                    if(VerifikasiUob.equals("") || VerifikasiUob.equals("null")){
                                        VerifikasiUob = "-";
                                    }
                                    if(tanggalSubmitUob.equals("") || tanggalSubmitUob.equals("null")){
                                        tanggalSubmitUob = "-";
                                    }
                                    if(statusappUob.equals("") || statusappUob.equals("null")){
                                        statusappUob = "-";
                                    }

                                    // bank
                                    statusUob.setText(StatusUob);
                                    tglPengajuanUob.setText(tanggalPengajuanUob);
                                    statusVerifUob.setText(VerifikasiUob);
                                    tglSubmitUob.setText(tanggalSubmitUob);
                                    tvStatusUob.setText(statusappUob);

                                    // bank Mega
                                    String StatusMega, VerifikasiMega, tanggalSubmitMega, statusappMega;
                                    StatusMega = jsonRESULTS.getJSONObject("data").getString("mega");
                                    String tanggalPengajuanMega = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiMega = jsonRESULTS.getJSONObject("data").getString("verifmega");
                                    tanggalSubmitMega = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitmega");
                                    statusappMega = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanmega");

                                    if(StatusMega.equals("") || StatusMega.equals("null")){
                                        StatusMega = "-";
                                    }
                                    if(VerifikasiMega.equals("") || VerifikasiMega.equals("null")){
                                        VerifikasiMega = "-";
                                    }
                                    if(tanggalSubmitMega.equals("") || tanggalSubmitMega.equals("null")){
                                        tanggalSubmitMega = "-";
                                    }
                                    if(statusappMega.equals("") || statusappMega.equals("null")){
                                        statusappMega = "-";
                                    }

                                    // bank
                                    statusMega.setText(StatusMega);
                                    tglPengajuanMega.setText(tanggalPengajuanMega);
                                    statusVerifMega.setText(VerifikasiMega);
                                    tglSubmitMega.setText(tanggalSubmitMega);
                                    tvStatusMega.setText(statusappMega);



                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(DetailHistoryTransactionInputSales.this, error_message, Toast.LENGTH_SHORT).show();
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
                        loading.dismiss();
                    }
                });
    }
}
