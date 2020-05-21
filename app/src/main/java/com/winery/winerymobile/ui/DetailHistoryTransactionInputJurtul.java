package com.winery.winerymobile.ui;

import androidx.appcompat.app.AlertDialog;
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
import com.github.chrisbanes.photoview.PhotoView;
import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.APIhelper.BaseApiService;
import com.winery.winerymobile.ui.APIhelper.UtilsApi;
import com.winery.winerymobile.ui.JurtulTransaction.ListTransactionDetailPendingJurtul;
import com.winery.winerymobile.ui.VerifikatorTransaction.ListTransactionDetailWaitingVerif;
import com.winery.winerymobile.ui.dbhelper.SessionManagement;
import com.winery.winerymobile.ui.dbhelper.StateTransactionSales;
import com.winery.winerymobile.ui.helper.ViewAnimation;

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

public class DetailHistoryTransactionInputJurtul extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.appBar)
    com.google.android.material.appbar.AppBarLayout appBar;
    @BindView(R.id.toolbar_main)
    com.google.android.material.appbar.MaterialToolbar toolbarMain;
    @BindView(R.id.toolbar_title)
    ImageView toolbarTitle;
    @BindView(R.id.nested)
    androidx.core.widget.NestedScrollView nested;
    @BindView(R.id.iv_data_kotor)
    ImageView ivDataKotor;
    @BindView(R.id.ll_section1)
    LinearLayout llSection1;
    @BindView(R.id.ll_child1_ll_section2)
    LinearLayout llChild1LlSection2;
    @BindView(R.id.rl_child1_ll_section2)
    RelativeLayout rlChild1LlSection2;
    @BindView(R.id.iv_chevron_payment_method1)
    ImageView ivChevronPaymentMethod1;
    @BindView(R.id.ll_data_customer)
    LinearLayout llDataCustomer;
    @BindView(R.id.tv_nik)
    TextView tvNik;
    @BindView(R.id.tv_npwp)
    TextView tvNpwp;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_tempat_lahir)
    TextView tvTempatLahir;
    @BindView(R.id.tv_dob)
    TextView tvDob;
    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.tv_telepon)
    TextView tvTelepon;
    @BindView(R.id.tv_handphone)
    TextView tvHandphone;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_kewarganegaraan)
    TextView tvKewarganegaraan;
    @BindView(R.id.tv_religion)
    TextView tvReligion;
    @BindView(R.id.tv_marital_status)
    TextView tvMaritalStatus;
    @BindView(R.id.tv_tanggungan)
    TextView tvTanggungan;
    @BindView(R.id.tv_pendidikan)
    TextView tvPendidikan;
    @BindView(R.id.tv_mother_name)
    TextView tvMotherName;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_rt)
    TextView tvRt;
    @BindView(R.id.tv_rw)
    TextView tvRw;
    @BindView(R.id.tv_kelurahan)
    TextView tvKelurahan;
    @BindView(R.id.tv_kecamatan)
    TextView tvKecamatan;
    @BindView(R.id.tv_kabupaten)
    TextView tvKabupaten;
    @BindView(R.id.tv_kode_pos)
    TextView tvKodePos;
    @BindView(R.id.tv_status_rumah)
    TextView tvStatusRumah;
    @BindView(R.id.tv_lama_tinggal)
    TextView tvLamaTinggal;
    @BindView(R.id.ll_child2_ll_section2)
    LinearLayout llChild2LlSection2;
    @BindView(R.id.rl_child2_ll_section2)
    RelativeLayout rlChild2LlSection2;
    @BindView(R.id.iv_chevron_payment_method2)
    ImageView ivChevronPaymentMethod2;
    @BindView(R.id.ll_data_pekerjaan)
    LinearLayout llDataPekerjaan;
    @BindView(R.id.tv_jenis_pekerjaan)
    TextView tvJenisPekerjaan;
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.tv_bidang_usaha)
    TextView tvBidangUsaha;
    @BindView(R.id.tv_bagian)
    TextView tvBagian;
    @BindView(R.id.tv_company_phone)
    TextView tvCompanyPhone;
    @BindView(R.id.tv_jabatan)
    TextView tvJabatan;
    @BindView(R.id.tv_gaji)
    TextView tvGaji;
    @BindView(R.id.tv_lama_bekerja)
    TextView tvLamaBekerja;
    @BindView(R.id.tv_status_karyawan)
    TextView tvStatusKaryawan;
    @BindView(R.id.tv_jumlah_karyawan)
    TextView tvJumlahKaryawan;
    @BindView(R.id.tv_company_address)
    TextView tvCompanyAddress;
    @BindView(R.id.tv_kelurahan_kantor)
    TextView tvKelurahanKantor;
    @BindView(R.id.tv_kecamatan_kantor)
    TextView tvKecamatanKantor;
    @BindView(R.id.tv_kabupaten_kantor)
    TextView tvKabupatenKantor;
    @BindView(R.id.tv_kodepos_kantor)
    TextView tvKodeposKantor;
    @BindView(R.id.ll_child3_ll_section2)
    LinearLayout llChild3LlSection2;
    @BindView(R.id.rl_child3_ll_section2)
    RelativeLayout rlChild3LlSection2;
    @BindView(R.id.iv_chevron_payment_method3)
    ImageView ivChevronPaymentMethod3;
    @BindView(R.id.ll_data_emergency_kontak)
    LinearLayout llDataEmergencyKontak;
    @BindView(R.id.tv_emergency_contact)
    TextView tvEmergencyContact;
    @BindView(R.id.tv_alamat_emergency_contact)
    TextView tvAlamatEmergencyContact;
    @BindView(R.id.tv_kelurahan_emergency_contact)
    TextView tvKelurahanEmergencyContact;
    @BindView(R.id.tv_kecamatan_emergency_contact)
    TextView tvKecamatanEmergencyContact;
    @BindView(R.id.tv_kabupaten_emergency_contact)
    TextView tvKabupatenEmergencyContact;
    @BindView(R.id.tv_telepon_emergency_contact)
    TextView tvTeleponEmergencyContact;
    @BindView(R.id.tv_handphone_emergency_contact)
    TextView tvHandphoneEmergencyContact;
    @BindView(R.id.tv_relationship)
    TextView tvRelationship;
    @BindView(R.id.ll_child4_ll_section2)
    LinearLayout llChild4LlSection2;
    @BindView(R.id.rl_child4_ll_section2)
    RelativeLayout rlChild4LlSection2;
    @BindView(R.id.iv_chevron_payment_method4)
    ImageView ivChevronPaymentMethod4;
    @BindView(R.id.ll_data_account)
    LinearLayout llDataAccount;
    @BindView(R.id.tv_data_cc)
    TextView tvDataCc;
    @BindView(R.id.tv_nomor_cc)
    TextView tvNomorCc;
    @BindView(R.id.tv_limit_cc)
    TextView tvLimitCc;
    @BindView(R.id.tv_valid_cc)
    TextView tvValidCc;
    @BindView(R.id.tv_jenis_rekening)
    TextView tvJenisRekening;
    @BindView(R.id.tv_nomor_rekening)
    TextView tvNomorRekening;
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
    @BindView(R.id.parent_tgl_jurtul_bri)
    RelativeLayout parentTglJurtulBri;
    @BindView(R.id.tgl_jurtul_bri)
    TextView tglJurtulBri;
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
    @BindView(R.id.parent_tgl_jurtul_bni)
    RelativeLayout parentTglJurtulBni;
    @BindView(R.id.tgl_jurtul_bni)
    TextView tglJurtulBni;
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
    @BindView(R.id.parent_tgl_jurtul_bca)
    RelativeLayout parentTglJurtulBca;
    @BindView(R.id.tgl_jurtul_bca)
    TextView tglJurtulBca;
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
    @BindView(R.id.parent_tgl_jurtul_cimb)
    RelativeLayout parentTglJurtulCimb;
    @BindView(R.id.tgl_jurtul_cimb)
    TextView tglJurtulCimb;
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
    @BindView(R.id.parent_tgl_jurtul_mayapada)
    RelativeLayout parentTglJurtulMayapada;
    @BindView(R.id.tgl_jurtul_mayapada)
    TextView tglJurtulMayapada;
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
    @BindView(R.id.parent_tgl_jurtul_dbs)
    RelativeLayout parentTglJurtulDbs;
    @BindView(R.id.tgl_jurtul_dbs)
    TextView tglJurtulDbs;
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
    @BindView(R.id.parent_tgl_jurtul_mnc)
    RelativeLayout parentTglJurtulMnc;
    @BindView(R.id.tgl_jurtul_mnc)
    TextView tglJurtulMnc;
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
    @BindView(R.id.parent_tgl_jurtul_uob)
    RelativeLayout parentTglJurtulUob;
    @BindView(R.id.tgl_jurtul_uob)
    TextView tglJurtulUob;
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
    @BindView(R.id.parent_tgl_jurtul_mega)
    RelativeLayout parentTglJurtulMega;
    @BindView(R.id.tgl_jurtul_mega)
    TextView tglJurtulMega;
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
    @BindView(R.id.parent_tgl_jurtul_panin)
    RelativeLayout parentTglJurtulPanin;
    @BindView(R.id.tgl_jurtul_panin)
    TextView tglJurtulPanin;
    @BindView(R.id.parent_status_balikan_panin)
    RelativeLayout parentStatusBalikanPanin;
    @BindView(R.id.tv_status_panin)
    TextView tvStatusPanin;
    /** ButterKnife Code **/

//    @OnClick(R.id.iv_data_kotor) void pinchZoom(){
//        AlertDialog.Builder mBuilder = new AlertDialog.Builder(DetailHistoryTransactionInputJurtul.this);
//        View mView = getLayoutInflater().inflate(R.layout.layout_photo_viewer, null);
//        PhotoView photoView = mView.findViewById(R.id.imageView);
//        Glide.with(DetailHistoryTransactionInputJurtul.this).
//                load(imageDataKotor)
//                .placeholder(R.drawable.ic_camera)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true)
//                .transition(DrawableTransitionOptions.withCrossFade(100))
//                .into(photoView);
////        photoView.setIMa(imageDataKotor);
//        mBuilder.setView(mView);
//        AlertDialog mDialog = mBuilder.create();
//        mDialog.show();
//    }

    @OnClick(R.id.rl_child1_ll_section2) void showDataCustomer(){

        if(ivChevronPaymentMethod1.isSelected()) {
            ivChevronPaymentMethod1.setSelected(false);
            ivChevronPaymentMethod1.setImageResource(R.drawable.ic_expand_more_black_24dp);
            ViewAnimation.collapse(llDataCustomer);

        }else{
            ivChevronPaymentMethod1.setSelected(true);
            ivChevronPaymentMethod1.setImageResource(R.drawable.ic_expand_less_black_24dp);
            ViewAnimation.expand(llDataCustomer);

        }
    }

    @OnClick(R.id.rl_child2_ll_section2) void showDataPekerjaan(){

        if(ivChevronPaymentMethod2.isSelected()) {
            ivChevronPaymentMethod2.setSelected(false);
            ivChevronPaymentMethod2.setImageResource(R.drawable.ic_expand_more_black_24dp);
            ViewAnimation.collapse(llDataPekerjaan);

        }else{
            ivChevronPaymentMethod2.setSelected(true);
            ivChevronPaymentMethod2.setImageResource(R.drawable.ic_expand_less_black_24dp);
            ViewAnimation.expand(llDataPekerjaan);

        }
    }

    @OnClick(R.id.rl_child3_ll_section2) void showDataEmergencyContact(){

        if(ivChevronPaymentMethod3.isSelected()) {
            ivChevronPaymentMethod3.setSelected(false);
            ivChevronPaymentMethod3.setImageResource(R.drawable.ic_expand_more_black_24dp);
            ViewAnimation.collapse(llDataEmergencyKontak);

        }else{
            ivChevronPaymentMethod3.setSelected(true);
            ivChevronPaymentMethod3.setImageResource(R.drawable.ic_expand_less_black_24dp);
            ViewAnimation.expand(llDataEmergencyKontak);

        }
    }

    @OnClick(R.id.rl_child4_ll_section2) void showDataAccount(){

        if(ivChevronPaymentMethod4.isSelected()) {
            ivChevronPaymentMethod4.setSelected(false);
            ivChevronPaymentMethod4.setImageResource(R.drawable.ic_expand_more_black_24dp);
            ViewAnimation.collapse(llDataAccount);

        }else{
            ivChevronPaymentMethod4.setSelected(true);
            ivChevronPaymentMethod4.setImageResource(R.drawable.ic_expand_less_black_24dp);
            ViewAnimation.expand(llDataAccount);

        }
    }

    ProgressDialog loading;
    BaseApiService mApiService;
    StateTransactionSales stateTransactionSales;
    SessionManagement sessionManagement;

    String imageDataKotor;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history_transaction_input_jurtul);
        ButterKnife.bind(this);

        mApiService = UtilsApi.getAPIService();
        stateTransactionSales = new StateTransactionSales(this);
        sessionManagement = new SessionManagement(this);
        initToolbar();

        HashMap<String, String> transactionID = sessionManagement.getTransactionID();

        // get status from back stack
        Log.d("transactionId", "onCreate: "+transactionID.get(sessionManagement.KEY_SALES_CODE));


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

    private void getListDetailHistoryCc() {
        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);
        loading.setCanceledOnTouchOutside(false);

        Intent intent = getIntent();
        String idTransaction = intent.getStringExtra("param");
        mApiService.getListDetailHistoryCc(idTransaction)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getInt("status") == 200) {

                                    String id = jsonRESULTS.getJSONObject("data").getString("id");
                                    String tanggal = jsonRESULTS.getJSONObject("data").getString("tanggal");


                                    // data customer
                                    String nik = jsonRESULTS.getJSONObject("data").getString("nik");
                                    String npwp = jsonRESULTS.getJSONObject("data").getString("npwp");
                                    String nama = jsonRESULTS.getJSONObject("data").getString("nama");
                                    String tempatLahir = jsonRESULTS.getJSONObject("data").getString("lahir");
                                    String dob = jsonRESULTS.getJSONObject("data").getString("dob");
                                    String jenisKelamin = jsonRESULTS.getJSONObject("data").getString("kelamin");
                                    String telepon = jsonRESULTS.getJSONObject("data").getString("telepon");
                                    String handphone = jsonRESULTS.getJSONObject("data").getString("handphone");
                                    String email = jsonRESULTS.getJSONObject("data").getString("email");
                                    String kewarganegaraan = jsonRESULTS.getJSONObject("data").getString("kewarganegaraan");
                                    String agama = jsonRESULTS.getJSONObject("data").getString("agama");
                                    String statuspernikahan = jsonRESULTS.getJSONObject("data").getString("statuspernikahan");
                                    String tanggungan = jsonRESULTS.getJSONObject("data").getString("tanggungan");
                                    String pendidikan = jsonRESULTS.getJSONObject("data").getString("pendidikan");
                                    String ibu = jsonRESULTS.getJSONObject("data").getString("ibu");

                                    imageDataKotor = jsonRESULTS.getJSONObject("data").getString("url_data_kotor");

                                    // fetch image
                                    Glide.with(DetailHistoryTransactionInputJurtul.this).
                                            load(imageDataKotor)
                                            .placeholder(R.drawable.ic_camera)
                                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                                            .skipMemoryCache(true)
                                            .transition(DrawableTransitionOptions.withCrossFade(100))
                                            .into(ivDataKotor);


                                    tvName.setText(nama);
                                    tvNik.setText(nik);
                                    tvNpwp.setText(npwp);
                                    tvTempatLahir.setText(tempatLahir);
                                    tvDob.setText(dob);
                                    tvGender.setText(jenisKelamin);
                                    tvHandphone.setText(telepon);
                                    tvEmail.setText(email);
                                    tvKewarganegaraan.setText(kewarganegaraan);
                                    tvReligion.setText(agama);
                                    tvMaritalStatus.setText(statuspernikahan);
                                    tvTanggungan.setText(tanggungan);
                                    tvPendidikan.setText(pendidikan);
                                    tvMotherName.setText(ibu);


                                    // alamat customer
                                    String alamat = jsonRESULTS.getJSONObject("data").getString("alamat");
                                    String rt = jsonRESULTS.getJSONObject("data").getString("rt");
                                    String rw = jsonRESULTS.getJSONObject("data").getString("rw");
                                    String kelurahan = jsonRESULTS.getJSONObject("data").getString("kelurahan");
                                    String kecamatan = jsonRESULTS.getJSONObject("data").getString("kecamatan");
                                    String kabupaten = jsonRESULTS.getJSONObject("data").getString("kabupaten");
                                    String kodepos = jsonRESULTS.getJSONObject("data").getString("kodepos");
                                    String status_rumah = jsonRESULTS.getJSONObject("data").getString("status_rumah");
                                    String lama_tinggal = jsonRESULTS.getJSONObject("data").getString("lama_tinggal");

                                    tvAddress.setText(alamat);
                                    tvRt.setText(rt);
                                    tvRw.setText(rw);
                                    tvKelurahan.setText(kelurahan);
                                    tvKecamatan.setText(kecamatan);
                                    tvKabupaten.setText(kabupaten);
                                    tvKodePos.setText(kodepos);
                                    tvStatusRumah.setText(status_rumah);
                                    tvLamaTinggal.setText(lama_tinggal);

                                    // data pekerjaan
                                    String jenispekerjaan = jsonRESULTS.getJSONObject("data").getString("jenispekerjaan");
                                    String perusahaan = jsonRESULTS.getJSONObject("data").getString("perusahaan");
                                    String usaha = jsonRESULTS.getJSONObject("data").getString("usaha");
                                    String bagian = jsonRESULTS.getJSONObject("data").getString("bagian");
                                    String teleponkantor = jsonRESULTS.getJSONObject("data").getString("teleponkantor");

                                    String jabatan = jsonRESULTS.getJSONObject("data").getString("jabatan");
                                    String gaji = jsonRESULTS.getJSONObject("data").getString("gaji");
                                    String lamabekerja = jsonRESULTS.getJSONObject("data").getString("lamabekerja");
                                    String statuskaryawan = jsonRESULTS.getJSONObject("data").getString("statuskaryawan");
                                    String jumlahkaryawan = jsonRESULTS.getJSONObject("data").getString("jumlahkaryawan");
                                    String alamatkantor = jsonRESULTS.getJSONObject("data").getString("alamatkantor");
                                    String kelurahankantor = jsonRESULTS.getJSONObject("data").getString("kelurahankantor");
                                    String kecamatankantor = jsonRESULTS.getJSONObject("data").getString("kecamatankantor");
                                    String kabupatenkantor = jsonRESULTS.getJSONObject("data").getString("kabupatenkantor");
                                    String kodeposkantor = jsonRESULTS.getJSONObject("data").getString("kodeposkantor");

                                    tvCompanyName.setText(perusahaan);
                                    tvCompanyAddress.setText(alamatkantor);
                                    tvCompanyPhone.setText(teleponkantor);
                                    tvJenisPekerjaan.setText(jenispekerjaan);
                                    tvBidangUsaha.setText(usaha);
                                    tvBagian.setText(bagian);
                                    tvJabatan.setText(jabatan);
                                    tvGaji.setText(gaji);
                                    tvLamaBekerja.setText(lamabekerja);
                                    tvStatusKaryawan.setText(statuskaryawan);
                                    tvJumlahKaryawan.setText(jumlahkaryawan);
                                    tvKelurahanKantor.setText(kelurahankantor);
                                    tvKecamatanKantor.setText(kecamatankantor);
                                    tvKabupatenKantor.setText(kabupatenkantor);
                                    tvKodeposKantor.setText(kodeposkantor);


                                    // data econtact
                                    String econtact = jsonRESULTS.getJSONObject("data").getString("econtact");
                                    String alamatecontact = jsonRESULTS.getJSONObject("data").getString("alamatecontact");
                                    String kelurahanecontact = jsonRESULTS.getJSONObject("data").getString("kelurahanecontact");
                                    String kecamatanecontact = jsonRESULTS.getJSONObject("data").getString("kecamatanecontact");
                                    String kabupatenecontact = jsonRESULTS.getJSONObject("data").getString("kabupatenecontact");
                                    String teleponecontact = jsonRESULTS.getJSONObject("data").getString("teleponecontact");
                                    String handphoneecontact = jsonRESULTS.getJSONObject("data").getString("handphoneecontact");
                                    String hubunganecontact = jsonRESULTS.getJSONObject("data").getString("hubunganecontact");

                                    tvEmergencyContact.setText(econtact);
                                    tvAlamatEmergencyContact.setText(alamatecontact);
                                    tvKelurahanEmergencyContact.setText(kelurahanecontact);
                                    tvKecamatanEmergencyContact.setText(kecamatanecontact);
                                    tvKabupatenEmergencyContact.setText(kabupatenecontact);
                                    tvTeleponEmergencyContact.setText(teleponecontact);
                                    tvHandphoneEmergencyContact.setText(handphoneecontact);
                                    tvRelationship.setText(hubunganecontact);

                                    // data rekening
                                    String cc = jsonRESULTS.getJSONObject("data").getString("cc");
                                    String nocc = jsonRESULTS.getJSONObject("data").getString("nocc");
                                    String limitcc = jsonRESULTS.getJSONObject("data").getString("limitcc");
                                    String validcc = jsonRESULTS.getJSONObject("data").getString("validcc");
                                    String jenisrekening = jsonRESULTS.getJSONObject("data").getString("jenisrekening");
                                    String norekening = jsonRESULTS.getJSONObject("data").getString("norekening");

                                    tvDataCc.setText(cc);
                                    tvNomorCc.setText(nocc);
                                    tvLimitCc.setText(limitcc);
                                    tvValidCc.setText(validcc);
                                    tvJenisRekening.setText(jenisrekening);
                                    tvNomorRekening.setText(norekening);

                                    String salesname = jsonRESULTS.getJSONObject("data").getString("salesname");
                                    String salescode = jsonRESULTS.getJSONObject("data").getString("salescode");


                                    tvSales.setText(salesname + " - " + salescode);


                                    // bank BRI
                                    String StatusBri, VerifikasiBri, tanggalSubmitBri, statusappBri, tanggalJurtulBri;
                                    StatusBri = jsonRESULTS.getJSONObject("data").getString("bri");
                                    String tanggalPengajuanBri = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiBri = jsonRESULTS.getJSONObject("data").getString("verifbri");
                                    tanggalSubmitBri = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitbri");
                                    statusappBri = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanbri");
                                    tanggalJurtulBri = jsonRESULTS.getJSONObject("data").getString("tanggaljurtulbri");


                                    if (VerifikasiBri.equals("CTN") && !tanggalJurtulBri.equals("null")) {
                                        cardBankBri.setVisibility(View.VISIBLE);
                                    } else {
                                        cardBankBri.setVisibility(View.GONE);
                                    }

                                    // bank
                                    statusBri.setText(StatusBri);
                                    tglPengajuanBri.setText(tanggalPengajuanBri);
                                    statusVerifBri.setText(VerifikasiBri);
                                    tglSubmitBri.setText(tanggalSubmitBri);
                                    tvStatusBri.setText(statusappBri);
                                    tglJurtulBri.setText(tanggalJurtulBri);

                                    parentTglSubmitBri.setVisibility(View.GONE);
                                    parentStatusBalikanBri.setVisibility(View.GONE);

                                    // bank BNI
                                    String StatusBni, VerifikasiBni, tanggalSubmitBni, statusappBni, tanggalJurtulBni;
                                    StatusBni = jsonRESULTS.getJSONObject("data").getString("bni");
                                    String tanggalPengajuanBni = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiBni = jsonRESULTS.getJSONObject("data").getString("verifbni");
                                    tanggalSubmitBni = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitbni");
                                    statusappBni = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanbni");
                                    tanggalJurtulBni = jsonRESULTS.getJSONObject("data").getString("tanggaljurtulbni");

                                    if (VerifikasiBni.equals("CTN") && !tanggalJurtulBni.equals("null")) {
                                        cardBankBni.setVisibility(View.VISIBLE);
                                    } else {
                                        cardBankBni.setVisibility(View.GONE);
                                    }

                                    // bank
                                    statusBni.setText(StatusBni);
                                    tglPengajuanBni.setText(tanggalPengajuanBni);
                                    statusVerifBni.setText(VerifikasiBni);
                                    tglSubmitBni.setText(tanggalSubmitBni);
                                    tvStatusBni.setText(statusappBni);
                                    tglJurtulBni.setText(tanggalJurtulBni);

                                    parentTglSubmitBni.setVisibility(View.GONE);
                                    parentStatusBalikanBni.setVisibility(View.GONE);

                                    // bank BCA
                                    String StatusBca, VerifikasiBca, tanggalSubmitBca, statusappBca, tanggalJurtulBca;
                                    StatusBca = jsonRESULTS.getJSONObject("data").getString("bca");
                                    String tanggalPengajuanBca = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiBca = jsonRESULTS.getJSONObject("data").getString("verifbca");
                                    tanggalSubmitBca = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitbca");
                                    statusappBca = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanbca");
                                    tanggalJurtulBca = jsonRESULTS.getJSONObject("data").getString("tanggaljurtulbca");


                                    if (VerifikasiBca.equals("CTN") && !tanggalJurtulBca.equals("null")) {
                                        cardBankBca.setVisibility(View.VISIBLE);
                                    } else {
                                        cardBankBca.setVisibility(View.GONE);
                                    }

                                    // bank
                                    statusBca.setText(StatusBca);
                                    tglPengajuanBca.setText(tanggalPengajuanBca);
                                    statusVerifBca.setText(VerifikasiBca);
                                    tglSubmitBca.setText(tanggalSubmitBca);
                                    tvStatusBca.setText(statusappBca);
                                    tglJurtulBca.setText(tanggalJurtulBca);

                                    parentTglSubmitBca.setVisibility(View.GONE);
                                    parentStatusBalikanBca.setVisibility(View.GONE);

                                    // bank CIMB
                                    String StatusCimb, VerifikasiCimb, tanggalSubmitCimb, statusappCimb, tanggalJurtulCimb;
                                    StatusCimb = jsonRESULTS.getJSONObject("data").getString("niaga");
                                    String tanggalPengajuanCimb = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiCimb = jsonRESULTS.getJSONObject("data").getString("verifniaga");
                                    tanggalSubmitCimb = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitniaga");
                                    statusappCimb = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanniaga");
                                    tanggalJurtulCimb = jsonRESULTS.getJSONObject("data").getString("tanggaljurtulniaga");


                                    if (VerifikasiCimb.equals("CTN") && !tanggalJurtulCimb.equals("null")) {
                                        cardBankCimb.setVisibility(View.VISIBLE);
                                    } else {
                                        cardBankCimb.setVisibility(View.GONE);
                                    }

                                    // bank
                                    statusCimb.setText(StatusCimb);
                                    tglPengajuanCimb.setText(tanggalPengajuanCimb);
                                    statusVerifCimb.setText(VerifikasiCimb);
                                    tglSubmitCimb.setText(tanggalSubmitCimb);
                                    tvStatusCimb.setText(statusappCimb);
                                    tglJurtulCimb.setText(tanggalJurtulCimb);

                                    parentTglSubmitCimb.setVisibility(View.GONE);
                                    parentStatusBalikanCimb.setVisibility(View.GONE);

                                    // bank Mayapada
                                    String StatusMayapada, VerifikasiMayapada, tanggalSubmitMayapada, statusappMayapada, tanggalJurtulMayapada;
                                    StatusMayapada = jsonRESULTS.getJSONObject("data").getString("mayapada");
                                    String tanggalPengajuanMayapada = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiMayapada = jsonRESULTS.getJSONObject("data").getString("verifmayapada");
                                    tanggalSubmitMayapada = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitmayapada");
                                    statusappMayapada = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanmayapada");
                                    tanggalJurtulMayapada = jsonRESULTS.getJSONObject("data").getString("tanggaljurtulmayapada");


                                    if (VerifikasiMayapada.equals("CTN") && !tanggalJurtulMayapada.equals("null")) {
                                        cardBankMayapada.setVisibility(View.VISIBLE);
                                    } else {
                                        cardBankMayapada.setVisibility(View.GONE);
                                    }

                                    // bank
                                    statusMayapada.setText(StatusMayapada);
                                    tglPengajuanMayapada.setText(tanggalPengajuanMayapada);
                                    statusVerifMayapada.setText(VerifikasiMayapada);
                                    tglSubmitMayapada.setText(tanggalSubmitMayapada);
                                    tvStatusMayapada.setText(statusappMayapada);
                                    tglJurtulMayapada.setText(tanggalJurtulMayapada);

                                    parentTglSubmitMayapada.setVisibility(View.GONE);
                                    parentStatusBalikanMayapada.setVisibility(View.GONE);

                                    // bank Dbs
                                    String StatusDbs, VerifikasiDbs, tanggalSubmitDbs, statusappDbs, tanggalJurtulDbs;
                                    StatusDbs = jsonRESULTS.getJSONObject("data").getString("dbs");
                                    String tanggalPengajuanDbs = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiDbs = jsonRESULTS.getJSONObject("data").getString("verifdbs");
                                    tanggalSubmitDbs = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitdbs");
                                    statusappDbs = jsonRESULTS.getJSONObject("data").getString("keteranganbalikandbs");
                                    tanggalJurtulDbs = jsonRESULTS.getJSONObject("data").getString("tanggaljurtuldbs");


                                    if (VerifikasiDbs.equals("CTN") && !tanggalJurtulDbs.equals("null")) {
                                        cardBankDbs.setVisibility(View.VISIBLE);
                                    } else {
                                        cardBankDbs.setVisibility(View.GONE);
                                    }

                                    // bank
                                    statusDbs.setText(StatusDbs);
                                    tglPengajuanDbs.setText(tanggalPengajuanDbs);
                                    statusVerifDbs.setText(VerifikasiDbs);
                                    tglSubmitDbs.setText(tanggalSubmitDbs);
                                    tvStatusDbs.setText(statusappDbs);
                                    tglJurtulDbs.setText(tanggalJurtulDbs);

                                    parentTglSubmitDbs.setVisibility(View.GONE);
                                    parentStatusBalikanDbs.setVisibility(View.GONE);

                                    // bank MNC
                                    String StatusMnc, VerifikasiMnc, tanggalSubmitMnc, statusappMnc, tanggalJurtulMnc;
                                    StatusMnc = jsonRESULTS.getJSONObject("data").getString("mnc");
                                    String tanggalPengajuanMnc = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiMnc = jsonRESULTS.getJSONObject("data").getString("verifmnc");
                                    tanggalSubmitMnc = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitmnc");
                                    statusappMnc = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanmnc");
                                    tanggalJurtulMnc = jsonRESULTS.getJSONObject("data").getString("tanggaljurtulmnc");


                                    if (VerifikasiMnc.equals("CTN") && !tanggalJurtulMnc.equals("null")) {
                                        cardBankMnc.setVisibility(View.VISIBLE);
                                    } else {
                                        cardBankMnc.setVisibility(View.GONE);
                                    }

                                    // bank
                                    statusMnc.setText(StatusMnc);
                                    tglPengajuanMnc.setText(tanggalPengajuanMnc);
                                    statusVerifMnc.setText(VerifikasiMnc);
                                    tglSubmitMnc.setText(tanggalSubmitMnc);
                                    tvStatusMnc.setText(statusappMnc);
                                    tglJurtulMnc.setText(tanggalJurtulMnc);

                                    parentTglSubmitMnc.setVisibility(View.GONE);
                                    parentStatusBalikanMnc.setVisibility(View.GONE);

                                    // bank UOB
                                    String StatusUob, VerifikasiUob, tanggalSubmitUob, statusappUob, tanggalJurtulUob;
                                    StatusUob = jsonRESULTS.getJSONObject("data").getString("uob");
                                    String tanggalPengajuanUob = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiUob = jsonRESULTS.getJSONObject("data").getString("verifuob");
                                    tanggalSubmitUob = jsonRESULTS.getJSONObject("data").getString("tanggalsubmituob");
                                    statusappUob = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanuob");
                                    tanggalJurtulUob = jsonRESULTS.getJSONObject("data").getString("tanggaljurtuluob");


                                    if (VerifikasiUob.equals("CTN") && !tanggalJurtulUob.equals("null")) {
                                        cardBankUob.setVisibility(View.VISIBLE);
                                    } else {
                                        cardBankUob.setVisibility(View.GONE);

                                    }

                                    // bank
                                    statusUob.setText(StatusUob);
                                    tglPengajuanUob.setText(tanggalPengajuanUob);
                                    statusVerifUob.setText(VerifikasiUob);
                                    tglSubmitUob.setText(tanggalSubmitUob);
                                    tvStatusUob.setText(statusappUob);
                                    tglJurtulUob.setText(tanggalJurtulUob);


                                    parentTglSubmitUob.setVisibility(View.GONE);
                                    parentStatusBalikanUob.setVisibility(View.GONE);

                                    // bank Mega
                                    String StatusMega, VerifikasiMega, tanggalSubmitMega, statusappMega, tanggalJurtulmega;
                                    StatusMega = jsonRESULTS.getJSONObject("data").getString("mega");
                                    String tanggalPengajuanMega = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiMega = jsonRESULTS.getJSONObject("data").getString("verifmega");
                                    tanggalSubmitMega = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitmega");
                                    statusappMega = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanmega");
                                    tanggalJurtulmega = jsonRESULTS.getJSONObject("data").getString("tanggaljurtulmega");


                                    if (VerifikasiMega.equals("CTN") && !tanggalJurtulmega.equals("null")) {
                                        cardBankMega.setVisibility(View.VISIBLE);
                                    } else {
                                        cardBankMega.setVisibility(View.GONE);
                                    }

                                    // bank
                                    statusMega.setText(StatusMega);
                                    tglPengajuanMega.setText(tanggalPengajuanMega);
                                    statusVerifMega.setText(VerifikasiMega);
                                    tglSubmitMega.setText(tanggalSubmitMega);
                                    tvStatusMega.setText(statusappMega);
                                    tglJurtulMega.setText(tanggalJurtulmega);

                                    parentTglSubmitMega.setVisibility(View.GONE);
                                    parentStatusBalikanMega.setVisibility(View.GONE);

                                    // bank Mega
                                    String StatusPanin, VerifikasiPanin, tanggalSubmitPanin, statusappPanin, tanggalJurtulPanin;
                                    StatusPanin = jsonRESULTS.getJSONObject("data").getString("panin");
                                    String tanggalPengajuanPanin = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiPanin = jsonRESULTS.getJSONObject("data").getString("verifpanin");
                                    tanggalSubmitPanin = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitpanin");
                                    statusappPanin = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanpanin");
                                    tanggalJurtulPanin = jsonRESULTS.getJSONObject("data").getString("tanggaljurtulpanin");


                                    if (VerifikasiPanin.equals("CTN") && !tanggalJurtulPanin.equals("null")) {
                                        cardBankPanin.setVisibility(View.VISIBLE);
                                    } else {
                                        cardBankPanin.setVisibility(View.GONE);
                                    }

                                    // bank
                                    statusPanin.setText(StatusPanin);
                                    tglPengajuanPanin.setText(tanggalPengajuanPanin);
                                    statusVerifPanin.setText(VerifikasiPanin);
                                    tglSubmitPanin.setText(tanggalSubmitPanin);
                                    tvStatusPanin.setText(statusappPanin);
                                    tglJurtulPanin.setText(tanggalJurtulPanin);

                                    parentTglSubmitPanin.setVisibility(View.GONE);
                                    parentStatusBalikanPanin.setVisibility(View.GONE);

                                    nested.setVisibility(View.VISIBLE);

                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(DetailHistoryTransactionInputJurtul.this, error_message, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(DetailHistoryTransactionInputJurtul.this, error_message, Toast.LENGTH_SHORT).show();
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
