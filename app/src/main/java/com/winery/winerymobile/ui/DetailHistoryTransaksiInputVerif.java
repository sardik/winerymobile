package com.winery.winerymobile.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.APIhelper.BaseApiService;
import com.winery.winerymobile.ui.APIhelper.UtilsApi;
import com.winery.winerymobile.ui.VerifikatorTransaction.ListTransactionDetailWaitingVerif;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailHistoryTransaksiInputVerif extends AppCompatActivity {

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
    @BindView(R.id.iv_ktp)
    ImageView ivKtp;
    @BindView(R.id.iv_doc_upload)
    ImageView ivDocUpload;
    @BindView(R.id.tv_sales)
    TextView tvSales;
    @BindView(R.id.card_bank_bri)
    com.google.android.material.card.MaterialCardView cardBankBri;
    @BindView(R.id.parent_status_bri)
    RelativeLayout parentStatusBri;
    @BindView(R.id.status_bri)
    TextView statusBri;
    @BindView(R.id.parent_tgl_pengajuan_bri)
    RelativeLayout parentTglPengajuanBri;
    @BindView(R.id.tgl_pengajuan_bri)
    TextView tglPengajuanBri;
    @BindView(R.id.parent_verif_bri)
    RelativeLayout parentVerifBri;
    @BindView(R.id.status_verif_bri)
    TextView statusVerifBri;
    @BindView(R.id.parent_tgl_submit_bri)
    RelativeLayout parentTglSubmitBri;
    @BindView(R.id.tgl_submit_bri)
    TextView tglSubmitBri;
    @BindView(R.id.parent_status_balikan_bri)
    RelativeLayout parentStatusBalikanBri;
    @BindView(R.id.tv_status_bri)
    TextView tvStatusBri;
    @BindView(R.id.card_bank_bni)
    com.google.android.material.card.MaterialCardView cardBankBni;
    @BindView(R.id.parent_status_bni)
    RelativeLayout parentStatusBni;
    @BindView(R.id.status_bni)
    TextView statusBni;
    @BindView(R.id.parent_tgl_pengajuan_bni)
    RelativeLayout parentTglPengajuanBni;
    @BindView(R.id.tgl_pengajuan_bni)
    TextView tglPengajuanBni;
    @BindView(R.id.parent_status_verif_bni)
    RelativeLayout parentStatusVerifBni;
    @BindView(R.id.status_verif_bni)
    TextView statusVerifBni;
    @BindView(R.id.parent_tgl_submit_bni)
    RelativeLayout parentTglSubmitBni;
    @BindView(R.id.tgl_submit_bni)
    TextView tglSubmitBni;
    @BindView(R.id.parent_status_balikan_bni)
    RelativeLayout parentStatusBalikanBni;
    @BindView(R.id.tv_status_bni)
    TextView tvStatusBni;
    @BindView(R.id.card_bank_bca)
    com.google.android.material.card.MaterialCardView cardBankBca;
    @BindView(R.id.parent_status_bca)
    RelativeLayout parentStatusBca;
    @BindView(R.id.status_bca)
    TextView statusBca;
    @BindView(R.id.parent_tgl_pengajuan_bca)
    RelativeLayout parentTglPengajuanBca;
    @BindView(R.id.tgl_pengajuan_bca)
    TextView tglPengajuanBca;
    @BindView(R.id.parent_status_verif_bca)
    RelativeLayout parentStatusVerifBca;
    @BindView(R.id.status_verif_bca)
    TextView statusVerifBca;
    @BindView(R.id.parent_tgl_submit_bca)
    RelativeLayout parentTglSubmitBca;
    @BindView(R.id.tgl_submit_bca)
    TextView tglSubmitBca;
    @BindView(R.id.parent_status_balikan_bca)
    RelativeLayout parentStatusBalikanBca;
    @BindView(R.id.tv_status_bca)
    TextView tvStatusBca;
    @BindView(R.id.card_bank_cimb)
    com.google.android.material.card.MaterialCardView cardBankCimb;
    @BindView(R.id.parent_status_cimb)
    RelativeLayout parentStatusCimb;
    @BindView(R.id.status_cimb)
    TextView statusCimb;
    @BindView(R.id.parent_tgl_pengajuan_cimb)
    RelativeLayout parentTglPengajuanCimb;
    @BindView(R.id.tgl_pengajuan_cimb)
    TextView tglPengajuanCimb;
    @BindView(R.id.parent_status_verif_cimb)
    RelativeLayout parentStatusVerifCimb;
    @BindView(R.id.status_verif_cimb)
    TextView statusVerifCimb;
    @BindView(R.id.parent_tgl_submit_cimb)
    RelativeLayout parentTglSubmitCimb;
    @BindView(R.id.tgl_submit_cimb)
    TextView tglSubmitCimb;
    @BindView(R.id.parent_status_balikan_cimb)
    RelativeLayout parentStatusBalikanCimb;
    @BindView(R.id.tv_status_cimb)
    TextView tvStatusCimb;
    @BindView(R.id.card_bank_mayapada)
    com.google.android.material.card.MaterialCardView cardBankMayapada;
    @BindView(R.id.parent_status_mayapada)
    RelativeLayout parentStatusMayapada;
    @BindView(R.id.status_mayapada)
    TextView statusMayapada;
    @BindView(R.id.parent_tgl_pengajuan_mayapada)
    RelativeLayout parentTglPengajuanMayapada;
    @BindView(R.id.tgl_pengajuan_mayapada)
    TextView tglPengajuanMayapada;
    @BindView(R.id.parent_status_verif_mayapada)
    RelativeLayout parentStatusVerifMayapada;
    @BindView(R.id.status_verif_mayapada)
    TextView statusVerifMayapada;
    @BindView(R.id.parent_tgl_submit_mayapada)
    RelativeLayout parentTglSubmitMayapada;
    @BindView(R.id.tgl_submit_mayapada)
    TextView tglSubmitMayapada;
    @BindView(R.id.parent_status_balikan_mayapada)
    RelativeLayout parentStatusBalikanMayapada;
    @BindView(R.id.tv_status_mayapada)
    TextView tvStatusMayapada;
    @BindView(R.id.card_bank_dbs)
    com.google.android.material.card.MaterialCardView cardBankDbs;
    @BindView(R.id.parent_status_dbs)
    RelativeLayout parentStatusDbs;
    @BindView(R.id.status_dbs)
    TextView statusDbs;
    @BindView(R.id.parent_tgl_pengajuan_dbs)
    RelativeLayout parentTglPengajuanDbs;
    @BindView(R.id.tgl_pengajuan_dbs)
    TextView tglPengajuanDbs;
    @BindView(R.id.parent_status_verif_dbs)
    RelativeLayout parentStatusVerifDbs;
    @BindView(R.id.status_verif_dbs)
    TextView statusVerifDbs;
    @BindView(R.id.parent_tgl_submit_dbs)
    RelativeLayout parentTglSubmitDbs;
    @BindView(R.id.tgl_submit_dbs)
    TextView tglSubmitDbs;
    @BindView(R.id.parent_status_balikan_dbs)
    RelativeLayout parentStatusBalikanDbs;
    @BindView(R.id.tv_status_dbs)
    TextView tvStatusDbs;
    @BindView(R.id.card_bank_mnc)
    com.google.android.material.card.MaterialCardView cardBankMnc;
    @BindView(R.id.parent_status_mnc)
    RelativeLayout parentStatusMnc;
    @BindView(R.id.status_mnc)
    TextView statusMnc;
    @BindView(R.id.parent_tgl_pengajuan_mnc)
    RelativeLayout parentTglPengajuanMnc;
    @BindView(R.id.tgl_pengajuan_mnc)
    TextView tglPengajuanMnc;
    @BindView(R.id.parent_status_verif_mnc)
    RelativeLayout parentStatusVerifMnc;
    @BindView(R.id.status_verif_mnc)
    TextView statusVerifMnc;
    @BindView(R.id.parent_tgl_submit_mnc)
    RelativeLayout parentTglSubmitMnc;
    @BindView(R.id.tgl_submit_mnc)
    TextView tglSubmitMnc;
    @BindView(R.id.parent_status_balikan_mnc)
    RelativeLayout parentStatusBalikanMnc;
    @BindView(R.id.tv_status_mnc)
    TextView tvStatusMnc;
    @BindView(R.id.card_bank_uob)
    com.google.android.material.card.MaterialCardView cardBankUob;
    @BindView(R.id.parent_status_uob)
    RelativeLayout parentStatusUob;
    @BindView(R.id.status_uob)
    TextView statusUob;
    @BindView(R.id.parent_tgl_pengajuan_uob)
    RelativeLayout parentTglPengajuanUob;
    @BindView(R.id.tgl_pengajuan_uob)
    TextView tglPengajuanUob;
    @BindView(R.id.parent_status_verif_uob)
    RelativeLayout parentStatusVerifUob;
    @BindView(R.id.status_verif_uob)
    TextView statusVerifUob;
    @BindView(R.id.parent_tgl_submit_uob)
    RelativeLayout parentTglSubmitUob;
    @BindView(R.id.tgl_submit_uob)
    TextView tglSubmitUob;
    @BindView(R.id.parent_status_balikan_uob)
    RelativeLayout parentStatusBalikanUob;
    @BindView(R.id.tv_status_uob)
    TextView tvStatusUob;
    @BindView(R.id.card_bank_mega)
    com.google.android.material.card.MaterialCardView cardBankMega;
    @BindView(R.id.parent_status_mega)
    RelativeLayout parentStatusMega;
    @BindView(R.id.status_mega)
    TextView statusMega;
    @BindView(R.id.parent_tgl_pengajuan_mega)
    RelativeLayout parentTglPengajuanMega;
    @BindView(R.id.tgl_pengajuan_mega)
    TextView tglPengajuanMega;
    @BindView(R.id.parent_status_verif_mega)
    RelativeLayout parentStatusVerifMega;
    @BindView(R.id.status_verif_mega)
    TextView statusVerifMega;
    @BindView(R.id.parent_tgl_submit_mega)
    RelativeLayout parentTglSubmitMega;
    @BindView(R.id.tgl_submit_mega)
    TextView tglSubmitMega;
    @BindView(R.id.parent_status_balikan_mega)
    RelativeLayout parentStatusBalikanMega;
    @BindView(R.id.tv_status_mega)
    TextView tvStatusMega;
    @BindView(R.id.card_bank_panin)
    com.google.android.material.card.MaterialCardView cardBankPanin;
    @BindView(R.id.parent_status_panin)
    RelativeLayout parentStatusPanin;
    @BindView(R.id.status_panin)
    TextView statusPanin;
    @BindView(R.id.parent_tgl_pengajuan_panin)
    RelativeLayout parentTglPengajuanPanin;
    @BindView(R.id.tgl_pengajuan_panin)
    TextView tglPengajuanPanin;
    @BindView(R.id.parent_status_verif_panin)
    RelativeLayout parentStatusVerifPanin;
    @BindView(R.id.status_verif_panin)
    TextView statusVerifPanin;
    @BindView(R.id.parent_tgl_submit_panin)
    RelativeLayout parentTglSubmitPanin;
    @BindView(R.id.tgl_submit_panin)
    TextView tglSubmitPanin;
    @BindView(R.id.parent_status_balikan_panin)
    RelativeLayout parentStatusBalikanPanin;
    @BindView(R.id.tv_status_panin)
    TextView tvStatusPanin;
    /** ButterKnife Code **/


    ProgressDialog loading;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history_transaksi_input_verif);
        ButterKnife.bind(this);

        initToolbar();

        mApiService = UtilsApi.getAPIService();

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
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getListDetailHistoryCc(){
        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);
        loading.setCanceledOnTouchOutside(false);


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
//                                    String tanggal = jsonRESULTS.getJSONObject("data").getString("tanggalverif");
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
                                    String salesname = jsonRESULTS.getJSONObject("data").getString("salesname");
                                    String salescode = jsonRESULTS.getJSONObject("data").getString("salescode");
                                    String imageKtp = jsonRESULTS.getJSONObject("data").getString("url_ktp");
                                    String imageDataKotor = jsonRESULTS.getJSONObject("data").getString("url_data_kotor");


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
                                    tvSales.setText(salesname+" - "+salescode);

                                    // fetch image
                                    Glide.with(DetailHistoryTransaksiInputVerif.this).
                                            load(imageKtp)
                                            .placeholder(R.color.grey_20)
                                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                                            .skipMemoryCache(true)
                                            .transition(DrawableTransitionOptions.withCrossFade(100))
                                            .into(ivKtp);


                                    // fetch image dokumen upload
                                    Glide.with(DetailHistoryTransaksiInputVerif.this).
                                            load(imageDataKotor)
                                            .placeholder(R.color.grey_20)
                                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                                            .skipMemoryCache(true)
                                            .transition(DrawableTransitionOptions.withCrossFade(100))
                                            .into(ivDocUpload);

                                    // bank BRI
                                    String StatusBri,VerifikasiBri,tanggalSubmitBri, statusappBri;
                                    StatusBri = jsonRESULTS.getJSONObject("data").getString("bri");
                                    String tanggalPengajuanBri = jsonRESULTS.getJSONObject("data").getString("tanggalverif");
                                    VerifikasiBri = jsonRESULTS.getJSONObject("data").getString("verifbri");
                                    tanggalSubmitBri = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitbri");
                                    statusappBri = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanbri");

                                    if(!StatusBri.equals("YES")){
                                        cardBankBri.setVisibility(View.GONE);
                                    }
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

                                    parentTglSubmitBri.setVisibility(View.GONE);
                                    parentStatusBalikanBri.setVisibility(View.GONE);

                                    // bank BNI
                                    String StatusBni, VerifikasiBni, tanggalSubmitBni, statusappBni;
                                    StatusBni = jsonRESULTS.getJSONObject("data").getString("bni");
                                    String tanggalPengajuanBni = jsonRESULTS.getJSONObject("data").getString("tanggalverif");
                                    VerifikasiBni = jsonRESULTS.getJSONObject("data").getString("verifbni");
                                    tanggalSubmitBni = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitbni");
                                    statusappBni = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanbni");

                                    if(!StatusBni.equals("YES")){
                                        cardBankBni.setVisibility(View.GONE);
                                    }

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

                                    parentTglSubmitBni.setVisibility(View.GONE);
                                    parentStatusBalikanBni.setVisibility(View.GONE);

                                    // bank BCA
                                    String StatusBca, VerifikasiBca, tanggalSubmitBca, statusappBca;
                                    StatusBca = jsonRESULTS.getJSONObject("data").getString("bca");
                                    String tanggalPengajuanBca = jsonRESULTS.getJSONObject("data").getString("tanggalverif");
                                    VerifikasiBca = jsonRESULTS.getJSONObject("data").getString("verifbca");
                                    tanggalSubmitBca = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitbca");
                                    statusappBca = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanbca");

                                    if(!StatusBca.equals("YES")){
                                        cardBankBca.setVisibility(View.GONE);
                                    }

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

                                    parentTglSubmitBca.setVisibility(View.GONE);
                                    parentStatusBalikanBca.setVisibility(View.GONE);

                                    // bank CIMB
                                    String StatusCimb,VerifikasiCimb, tanggalSubmitCimb,statusappCimb;
                                    StatusCimb = jsonRESULTS.getJSONObject("data").getString("niaga");
                                    String tanggalPengajuanCimb = jsonRESULTS.getJSONObject("data").getString("tanggalverif");
                                    VerifikasiCimb = jsonRESULTS.getJSONObject("data").getString("verifniaga");
                                    tanggalSubmitCimb = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitniaga");
                                    statusappCimb = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanniaga");

                                    if(!StatusCimb.equals("YES")){
                                        cardBankCimb.setVisibility(View.GONE);
                                    }

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

                                    parentTglSubmitCimb.setVisibility(View.GONE);
                                    parentStatusBalikanCimb.setVisibility(View.GONE);

                                    // bank Mayapada
                                    String StatusMayapada, VerifikasiMayapada, tanggalSubmitMayapada, statusappMayapada;
                                    StatusMayapada = jsonRESULTS.getJSONObject("data").getString("mayapada");
                                    String tanggalPengajuanMayapada = jsonRESULTS.getJSONObject("data").getString("tanggalverif");
                                    VerifikasiMayapada = jsonRESULTS.getJSONObject("data").getString("verifmayapada");
                                    tanggalSubmitMayapada = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitmayapada");
                                    statusappMayapada= jsonRESULTS.getJSONObject("data").getString("keteranganbalikanmayapada");

                                    if(!StatusMayapada.equals("YES")){
                                        cardBankMayapada.setVisibility(View.GONE);
                                    }

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

                                    parentTglSubmitMayapada.setVisibility(View.GONE);
                                    parentStatusBalikanMayapada.setVisibility(View.GONE);

                                    // bank Dbs
                                    String StatusDbs, VerifikasiDbs, tanggalSubmitDbs, statusappDbs;
                                    StatusDbs = jsonRESULTS.getJSONObject("data").getString("dbs");
                                    String tanggalPengajuanDbs = jsonRESULTS.getJSONObject("data").getString("tanggalverif");
                                    VerifikasiDbs = jsonRESULTS.getJSONObject("data").getString("verifdbs");
                                    tanggalSubmitDbs = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitdbs");
                                    statusappDbs = jsonRESULTS.getJSONObject("data").getString("keteranganbalikandbs");

                                    if(!StatusDbs.equals("YES")){
                                        cardBankDbs.setVisibility(View.GONE);
                                    }

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

                                    parentTglSubmitDbs.setVisibility(View.GONE);
                                    parentStatusBalikanDbs.setVisibility(View.GONE);


                                    // bank MNC
                                    String StatusMnc, VerifikasiMnc, tanggalSubmitMnc, statusappMnc;
                                    StatusMnc = jsonRESULTS.getJSONObject("data").getString("mnc");
                                    String tanggalPengajuanMnc = jsonRESULTS.getJSONObject("data").getString("tanggalverif");
                                    VerifikasiMnc = jsonRESULTS.getJSONObject("data").getString("verifmnc");
                                    tanggalSubmitMnc = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitmnc");
                                    statusappMnc = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanmnc");

                                    if(!StatusMnc.equals("YES")){
                                        cardBankMnc.setVisibility(View.GONE);
                                    }

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

                                    parentTglSubmitMnc.setVisibility(View.GONE);
                                    parentStatusBalikanMnc.setVisibility(View.GONE);

                                    // bank UOB
                                    String StatusUob, VerifikasiUob, tanggalSubmitUob, statusappUob;
                                    StatusUob = jsonRESULTS.getJSONObject("data").getString("uob");
                                    String tanggalPengajuanUob = jsonRESULTS.getJSONObject("data").getString("tanggalverif");
                                    VerifikasiUob = jsonRESULTS.getJSONObject("data").getString("verifuob");
                                    tanggalSubmitUob = jsonRESULTS.getJSONObject("data").getString("tanggalsubmituob");
                                    statusappUob = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanuob");

                                    if(!StatusUob.equals("YES")){
                                        cardBankUob.setVisibility(View.GONE);
                                    }

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

                                    parentTglSubmitUob.setVisibility(View.GONE);
                                    parentStatusBalikanUob.setVisibility(View.GONE);

                                    // bank Mega
                                    String StatusMega, VerifikasiMega, tanggalSubmitMega, statusappMega;
                                    StatusMega = jsonRESULTS.getJSONObject("data").getString("mega");
                                    String tanggalPengajuanMega = jsonRESULTS.getJSONObject("data").getString("tanggalverif");
                                    VerifikasiMega = jsonRESULTS.getJSONObject("data").getString("verifmega");
                                    tanggalSubmitMega = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitmega");
                                    statusappMega = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanmega");

                                    if(!StatusMega.equals("YES")){
                                        cardBankMega.setVisibility(View.GONE);
                                    }

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

                                    parentTglSubmitMega.setVisibility(View.GONE);
                                    parentStatusBalikanMega.setVisibility(View.GONE);


                                    // bank Panin
                                    String StatusPanin, VerifikasiPanin, tanggalSubmitPanin, statusappPanin;
                                    StatusPanin = jsonRESULTS.getJSONObject("data").getString("panin");
                                    String tanggalPengajuanPanin = jsonRESULTS.getJSONObject("data").getString("tanggalverif");
                                    VerifikasiPanin= jsonRESULTS.getJSONObject("data").getString("verifpanin");
                                    tanggalSubmitPanin = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitpanin");
                                    statusappPanin = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanpanin");

                                    if(!StatusPanin.equals("YES")){
                                        cardBankPanin.setVisibility(View.GONE);
                                    }

                                    if(StatusPanin.equals("") || StatusPanin.equals("null")){
                                        StatusPanin = "-";
                                    }
                                    if(VerifikasiPanin.equals("") || VerifikasiPanin.equals("null")){
                                        VerifikasiPanin = "-";
                                    }
                                    if(tanggalSubmitPanin.equals("") || tanggalSubmitPanin.equals("null")){
                                        tanggalSubmitPanin = "-";
                                    }
                                    if(statusappPanin.equals("") || statusappPanin.equals("null")){
                                        statusappPanin = "-";
                                    }

                                    // bank
                                    statusPanin.setText(StatusPanin);
                                    tglPengajuanPanin.setText(tanggalPengajuanPanin);
                                    statusVerifPanin.setText(VerifikasiPanin);

                                    tglSubmitPanin.setText(tanggalSubmitPanin);
                                    tvStatusPanin.setText(statusappPanin);

                                    parentTglSubmitPanin.setVisibility(View.GONE);
                                    parentStatusBalikanPanin.setVisibility(View.GONE);

                                    nested.setVisibility(View.VISIBLE);



                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(DetailHistoryTransaksiInputVerif.this, error_message, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(DetailHistoryTransaksiInputVerif.this, error_message, Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                });
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
