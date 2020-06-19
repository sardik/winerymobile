package com.winery.winerymobile.ui.Recontest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.APIhelper.BaseApiService;
import com.winery.winerymobile.ui.APIhelper.UtilsApi;
import com.winery.winerymobile.ui.adapter.CarouselImageDocument;
import com.winery.winerymobile.ui.dbhelper.SessionManagement;
import com.winery.winerymobile.ui.dbhelper.StateTransactionSales;
import com.winery.winerymobile.ui.helper.ViewAnimation;
import com.winery.winerymobile.ui.model.HadiahList;
import com.winery.winerymobile.ui.model.ImageDocumentDataCustomer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailDataCustomerRecontestActivity extends AppCompatActivity {

    @BindView(R.id.appBar)
    com.google.android.material.appbar.AppBarLayout appBar;
    @BindView(R.id.toolbar_main)
    com.google.android.material.appbar.MaterialToolbar toolbarMain;
    @BindView(R.id.titlebar_username)
    TextView titlebarUsername;
    @BindView(R.id.titlebar_status_aplikasi)
    TextView titlebarStatusAplikasi;
    @BindView(R.id.titlebar_tgl_pengajuan)
    TextView titlebarTglPengajuan;
    @BindView(R.id.titlebar_tgl_verif)
    TextView titlebarTglVerif;
    @BindView(R.id.sc_filter_recontest)
    ScrollView scFilterRecontest;
    @BindView(R.id.viewPager)
    androidx.viewpager.widget.ViewPager viewPager;
    @BindView(R.id.id)
    com.itsronald.widget.ViewPagerIndicator id;
    @BindView(R.id.checkbox_validation_data)
    CheckBox checkboxValidationData;
    @BindView(R.id.rl_footer)
    RelativeLayout rlFooter;
    @BindView(R.id.btn_change_document)
    com.google.android.material.button.MaterialButton btnChangeDocument;
    @BindView(R.id.btn_recontest)
    com.google.android.material.button.MaterialButton btnRecontest;

    String idTransaction;
    String nama, nik, tempat_lahir,tanggal_lahir, handphone_1,
            handphone_2, nama_ibu_kandung, nama_perusahaan,
            alamat_perusahaan, telephone_kantor, jabatan_kantor, bagian_kantor,nama_emergency_contact, telp_emergency_contact,
            hubungan, hadiah, hadiah_referensi, hadiah_explode;

    SessionManagement sessionManagement;
    StateTransactionSales stateTransactionSales;

    @OnClick(R.id.checkbox_validation_data) void checkData(){
        if(checkboxValidationData.isChecked()){
            btnRecontest.setEnabled(true);
        }else{
            btnRecontest.setEnabled(false);
        }
    }

    @OnClick(R.id.btn_change_document) void intentChangeDocument(){
        Intent intentChangeDoc = new Intent(this, ChangeDocumentRecontestActivity.class);
        startActivity(intentChangeDoc);
    }

    @OnClick(R.id.btn_recontest) void ajukanLagi() {
        if (etName.getText().toString().equals("")) {
            tiName.setError("Nama harus di isi");
            etName.requestFocus();
        } else if (etNik.getText().toString().equals("")) {
            tiNik.setError("Nik harus di isi");
            etNik.requestFocus();
        } else if (etDob.getText().toString().equals("")) {
            tiDob.setError("Tanggal harus di isi");
            etDob.requestFocus();
        } else if (etHp1.getText().toString().equals("") && etHp2.getText().toString().equals("")) {
            tiHp1.setError("minimal 1 nomor harus di isi");
            etHp1.requestFocus();

        } else if (etMotherName.getText().toString().equals("")) {
            tiMotherName.setError("nama ibu harus di isi");
            etMotherName.requestFocus();
        } else if (etCompanyName.getText().toString().equals("")) {
            tiCompanyName.setError("nama perusahaan harus di isi");
            etCompanyName.requestFocus();
        } else if (etCompanyAddress.getText().toString().equals("")) {
            tiCompanyAddress.setError("alamat perusahaan harus di isi");
            etCompanyAddress.requestFocus();
        } else if (etCompanyPhone.getText().toString().equals("")) {
            tiCompanyPhone.setError("telpon kantor harus di isi");
            etCompanyPhone.requestFocus();
        } else if (etCompanyBagian.getText().toString().equals("")) {
            tiCompanyBagian.setError("bagian harus di isi");
            etCompanyBagian.requestFocus();
        } else if (etCompanyJabatan.getText().toString().equals("")) {
            tiCompanyJabatan.setError("jabatan harus di isi");
            etCompanyJabatan.requestFocus();
        } else if (etEmergencyName.getText().toString().equals("")) {
            tiEmergencyName.setError("nama emergency contact harus di isi");
            etEmergencyName.requestFocus();
        } else if (etEmergencyTelp.getText().toString().equals("")) {
            tiEmergencyTelp.setError("telp emergency contact harus di isi");
            etEmergencyTelp.requestFocus();
        } else if (etEmergencyRelationship.getText().toString().equals("")) {
            tiEmergencyRelationship.setError("hubungan harus di isi");
            tiEmergencyRelationship.requestFocus();

        } else if (etHadiah.getText().toString().equals("")) {
            tiHadiah.setError("hadiah harus di isi");
            tiHadiah.requestFocus();

        } else {

            HashMap<String, String> user = sessionManagement.getUserDetails();

            nama = etName.getText().toString();
            nik = etNik.getText().toString();
            tempat_lahir = etTempatLahir.getText().toString();
            tanggal_lahir = etDob.getText().toString();

            handphone_1 = etHp1.getText().toString();
            handphone_2 = etHp2.getText().toString();
            nama_ibu_kandung = etMotherName.getText().toString();
            nama_perusahaan = etCompanyName.getText().toString();
            alamat_perusahaan = etCompanyAddress.getText().toString();
            if(etCompanyPhoneExt.getText().toString().length() > 0){
                telephone_kantor = etCompanyPhone.getText().toString()+"-"+etCompanyPhoneExt.getText().toString();
            }else{
                telephone_kantor = etCompanyPhone.getText().toString();
            }
            jabatan_kantor = etCompanyJabatan.getText().toString();
            bagian_kantor = etCompanyBagian.getText().toString();
            nama_emergency_contact = etEmergencyName.getText().toString();
            telp_emergency_contact = etEmergencyTelp.getText().toString();

            hubungan = etEmergencyRelationship.getText().toString();
            hadiah = etHadiah.getText().toString();

            if (etHadiahReferensi.getText().toString().equals("")) {
                hadiah_referensi = "NON HADIAH";
            } else {
                hadiah_referensi = etHadiahReferensi.getText().toString();
            }

            hadiah_explode = hadiah + "-" + hadiah_referensi;


            stateTransactionSales.updateStateInpuForm(idTransaction,nama, nik,
                    tempat_lahir, tanggal_lahir, handphone_1, handphone_2, nama_ibu_kandung,
                    nama_perusahaan, alamat_perusahaan, telephone_kantor, bagian_kantor, jabatan_kantor,nama_emergency_contact, hubungan, hadiah_explode, telp_emergency_contact);
            Intent intent = new Intent(this, ChangeDocumentRecontestActivity.class);
            intent.putExtra("param", idTransaction);
            intent.putExtra("status", "indirect");
            Intent intents = getIntent();
            String statusRjVerif = intents.getStringExtra("statusrjverif");
            intent.putExtra("statusrjverif", statusRjVerif);

            startActivity(intent);
        }
    }
    
    
    
    // content document personal data
    @BindView(R.id.card_recontest_detail_personal_data)
    com.google.android.material.card.MaterialCardView cardRecontestDetailPersonalData;
    @BindView(R.id.parent_container_item_data_car)
    RelativeLayout parentContainerItemDataCar;
    @BindView(R.id.ivChevronDetailPersonalData)
    ImageView ivChevronDetailPersonalData;
    @BindView(R.id.ti_name)
    com.google.android.material.textfield.TextInputLayout tiName;
    @BindView(R.id.et_name)
    com.google.android.material.textfield.TextInputEditText etName;
    @BindView(R.id.ti_nik)
    com.google.android.material.textfield.TextInputLayout tiNik;
    @BindView(R.id.et_nik)
    com.google.android.material.textfield.TextInputEditText etNik;
    @BindView(R.id.ti_tempat_lahir)
    com.google.android.material.textfield.TextInputLayout tiTempatLahir;
    @BindView(R.id.et_tempat_lahir)
    com.google.android.material.textfield.TextInputEditText etTempatLahir;
    com.google.android.material.textfield.TextInputLayout tiDob;
    @BindView(R.id.et_dob)
    com.google.android.material.textfield.TextInputEditText etDob;
//    @BindView(R.id.ti_date_birth)
//    com.google.android.material.textfield.TextInputLayout tiDateBirth;
//    @BindView(R.id.et_date_brith)
//    com.google.android.material.textfield.TextInputEditText etDateBrith;
//    @BindView(R.id.ti_month_birth)
//    com.google.android.material.textfield.TextInputLayout tiMonthBirth;
//    @BindView(R.id.et_month_birth)
//    com.google.android.material.textfield.TextInputEditText etMonthBirth;
//    @BindView(R.id.ti_year_birth)
//    com.google.android.material.textfield.TextInputLayout tiYearBirth;
//    @BindView(R.id.et_year_birth)
    com.google.android.material.textfield.TextInputEditText etYearBirth;
    @BindView(R.id.ti_hp1)
    com.google.android.material.textfield.TextInputLayout tiHp1;
    @BindView(R.id.et_hp1)
    com.google.android.material.textfield.TextInputEditText etHp1;
    @BindView(R.id.ti_hp2)
    com.google.android.material.textfield.TextInputLayout tiHp2;
    @BindView(R.id.et_hp2)
    com.google.android.material.textfield.TextInputEditText etHp2;
    @BindView(R.id.ti_mother_name)
    com.google.android.material.textfield.TextInputLayout tiMotherName;
    @BindView(R.id.et_mother_name)
    com.google.android.material.textfield.TextInputEditText etMotherName;

    @OnClick(R.id.card_recontest_detail_personal_data) void showDataCustomer(){

        if(ivChevronDetailPersonalData.isSelected()) {
            ivChevronDetailPersonalData.setSelected(false);
            ivChevronDetailPersonalData.setImageResource(R.drawable.ic_expand_more_black_24dp);
            ViewAnimation.collapse(parentContainerItemDataCar);

        }else{
            ivChevronDetailPersonalData.setSelected(true);
            ivChevronDetailPersonalData.setImageResource(R.drawable.ic_expand_less_black_24dp);
            ViewAnimation.expand(parentContainerItemDataCar);
        }
    }


    
    // content document profession data
    @BindView(R.id.card_recontest_detail_profession_data)
    com.google.android.material.card.MaterialCardView cardRecontestDetailProfessionData;
    @BindView(R.id.ivChevronDetailProfessionData)
    ImageView ivChevronDetailProfessionData;
    @BindView(R.id.parent_container_item_data_profession)
    RelativeLayout parentContainerItemDataProfession;
    @BindView(R.id.ti_company_name)
    com.google.android.material.textfield.TextInputLayout tiCompanyName;
    @BindView(R.id.et_company_name)
    com.google.android.material.textfield.TextInputEditText etCompanyName;
    @BindView(R.id.ti_company_address)
    com.google.android.material.textfield.TextInputLayout tiCompanyAddress;
    @BindView(R.id.et_company_address)
    com.google.android.material.textfield.TextInputEditText etCompanyAddress;
    @BindView(R.id.ti_company_phone)
    com.google.android.material.textfield.TextInputLayout tiCompanyPhone;
    @BindView(R.id.et_company_phone)
    com.google.android.material.textfield.TextInputEditText etCompanyPhone;
    @BindView(R.id.ti_company_phone_ext)
    com.google.android.material.textfield.TextInputLayout tiCompanyPhoneExt;
    @BindView(R.id.et_company_phone_ext)
    com.google.android.material.textfield.TextInputEditText etCompanyPhoneExt;
    @BindView(R.id.ti_company_jabatan)
    com.google.android.material.textfield.TextInputLayout tiCompanyJabatan;
    @BindView(R.id.et_company_jabatan)
    com.google.android.material.textfield.TextInputEditText etCompanyJabatan;
    @BindView(R.id.ti_company_bagian)
    com.google.android.material.textfield.TextInputLayout tiCompanyBagian;
    @BindView(R.id.et_company_bagian)
    com.google.android.material.textfield.TextInputEditText etCompanyBagian;

    @OnClick(R.id.card_recontest_detail_profession_data) void showDataProfession() {

        if (ivChevronDetailProfessionData.isSelected()) {
            ivChevronDetailProfessionData.setSelected(false);
            ivChevronDetailProfessionData.setImageResource(R.drawable.ic_expand_more_black_24dp);
            ViewAnimation.collapse(parentContainerItemDataProfession);

        } else {
            ivChevronDetailProfessionData.setSelected(true);
            ivChevronDetailProfessionData.setImageResource(R.drawable.ic_expand_less_black_24dp);
            ViewAnimation.expand(parentContainerItemDataProfession);
        }
    }
    

    // content document data emergency contact
    @BindView(R.id.card_recontest_detail_emergency_contact)
    com.google.android.material.card.MaterialCardView cardRecontestDetailEmergencyContact;
    @BindView(R.id.ivChevronDetailEmergencyContact)
    ImageView ivChevronDetailEmergencyContact;
    @BindView(R.id.parent_container_item_data_emergency_contact)
    RelativeLayout parentContainerItemDataEmergencyContact;
    @BindView(R.id.ti_emergency_name)
    com.google.android.material.textfield.TextInputLayout tiEmergencyName;
    @BindView(R.id.et_emergency_name)
    com.google.android.material.textfield.TextInputEditText etEmergencyName;
    @BindView(R.id.ti_emergency_telp)
    com.google.android.material.textfield.TextInputLayout tiEmergencyTelp;
    @BindView(R.id.et_emergency_telp)
    com.google.android.material.textfield.TextInputEditText etEmergencyTelp;
    @BindView(R.id.ti_emergency_relationship)
    com.google.android.material.textfield.TextInputLayout tiEmergencyRelationship;
    @BindView(R.id.et_emergency_relationship)
    AutoCompleteTextView etEmergencyRelationship;
    @OnClick(R.id.card_recontest_detail_emergency_contact) void showDataEmergencyContact() {

        if (ivChevronDetailEmergencyContact.isSelected()) {
            ivChevronDetailEmergencyContact.setSelected(false);
            ivChevronDetailEmergencyContact.setImageResource(R.drawable.ic_expand_more_black_24dp);
            ViewAnimation.collapse(parentContainerItemDataEmergencyContact);

        } else {
            ivChevronDetailEmergencyContact.setSelected(true);
            ivChevronDetailEmergencyContact.setImageResource(R.drawable.ic_expand_less_black_24dp);
            ViewAnimation.expand(parentContainerItemDataEmergencyContact);
        }
    }

    
    // content data hadiah
    @BindView(R.id.card_recontest_detail_data_hadiah)
    com.google.android.material.card.MaterialCardView cardRecontestDetailDataHadiah;
    @BindView(R.id.ivChevronDetailDataHadiah)
    ImageView ivChevronDetailDataHadiah;
    @BindView(R.id.parent_container_item_data_hadiah)
    RelativeLayout parentContainerItemDataHadiah;
    @BindView(R.id.ti_hadiah)
    com.google.android.material.textfield.TextInputLayout tiHadiah;
    @BindView(R.id.et_hadiah)
    AutoCompleteTextView etHadiah;
    @BindView(R.id.ti_hadiah_referensi)
    com.google.android.material.textfield.TextInputLayout tiHadiahReferensi;
    @BindView(R.id.et_hadiah_referensi)
    AutoCompleteTextView etHadiahReferensi;

    @OnClick(R.id.card_recontest_detail_data_hadiah) void showDataHadiah() {

        if (ivChevronDetailDataHadiah.isSelected()) {
            ivChevronDetailDataHadiah.setSelected(false);
            ivChevronDetailDataHadiah.setImageResource(R.drawable.ic_expand_more_black_24dp);
            ViewAnimation.collapse(parentContainerItemDataHadiah);

        } else {
            ivChevronDetailDataHadiah.setSelected(true);
            ivChevronDetailDataHadiah.setImageResource(R.drawable.ic_expand_less_black_24dp);
            ViewAnimation.expand(parentContainerItemDataHadiah);
        }
    }
    
    
    // content data status bank bca
    @BindView(R.id.ll_parent_recontest_detail_status_bca)
    LinearLayout llParentRecontestDetailStatusBca;
    @BindView(R.id.card_recontest_detail_status_bca)
    com.google.android.material.card.MaterialCardView cardRecontestDetailStatusBca;
    @BindView(R.id.ivChevronDetailStatusBca)
    ImageView ivChevronDetailStatusBca;
    @BindView(R.id.parent_container_item_status_bca)
    RelativeLayout parentContainerItemStatusBca;
    @BindView(R.id.tv_status_verif_bca)
    TextView tvStatusVerifBca;
    @BindView(R.id.tv_date_verif_bca)
    TextView tvDateVerifBca;
    @BindView(R.id.tv_tanggal_input_bca)
    TextView tvTanggalInputBca;
    @BindView(R.id.tv_date_inc_verif_bca)
    TextView tvDateIncVerifBca;
    @BindView(R.id.tv_salin_date_verif_bca)
    TextView tvSalinDateVerifBca;
    @BindView(R.id.tv_analyst_date_verif_bca)
    TextView tvAnalystDateVerifBca;
    @BindView(R.id.tv_submit_date_verif_bca)
    TextView tvSubmitDateVerifBca;
    @BindView(R.id.tv_status_aplikasi_verif_bca)
    TextView tvStatusAplikasiVerifBca;
    @BindView(R.id.tv_status_date_verif_bca)
    TextView tvStatusDateVerifBca;

    @OnClick(R.id.card_recontest_detail_status_bca) void statusBca(){
        if (ivChevronDetailStatusBca.isSelected()) {
            ivChevronDetailStatusBca.setSelected(false);
            ivChevronDetailStatusBca.setImageResource(R.drawable.ic_expand_more_black_24dp);
            ViewAnimation.collapse(parentContainerItemStatusBca);

        } else {
            ivChevronDetailStatusBca.setSelected(true);
            ivChevronDetailStatusBca.setImageResource(R.drawable.ic_expand_less_black_24dp);
            ViewAnimation.expand(parentContainerItemStatusBca);
        }
    }


    // content data status bni
    @BindView(R.id.ll_parent_recontest_detail_status_bni)
    LinearLayout llParentRecontestDetailStatusBni;
    @BindView(R.id.card_recontest_detail_status_bni)
    com.google.android.material.card.MaterialCardView cardRecontestDetailStatusBni;
    @BindView(R.id.ivChevronDetailStatusBni)
    ImageView ivChevronDetailStatusBni;
    @BindView(R.id.parent_container_item_status_bni)
    RelativeLayout parentContainerItemStatusBni;
    @BindView(R.id.tv_status_verif_bni)
    TextView tvStatusVerifBni;
    @BindView(R.id.tv_date_verif_bni)
    TextView tvDateVerifBni;
    @BindView(R.id.tv_tanggal_input_bni)
    TextView tvTanggalInputBni;
    @BindView(R.id.tv_date_inc_verif_bni)
    TextView tvDateIncVerifBni;
    @BindView(R.id.tv_salin_date_verif_bni)
    TextView tvSalinDateVerifBni;
    @BindView(R.id.tv_analyst_date_verif_bni)
    TextView tvAnalystDateVerifBni;
    @BindView(R.id.tv_submit_date_verif_bni)
    TextView tvSubmitDateVerifBni;
    @BindView(R.id.tv_status_aplikasi_verif_bni)
    TextView tvStatusAplikasiVerifBni;
    @BindView(R.id.tv_status_date_verif_bni)
    TextView tvStatusDateVerifBni;


    @OnClick(R.id.card_recontest_detail_status_bni) void statusBni(){
        if (ivChevronDetailStatusBni.isSelected()) {
            ivChevronDetailStatusBni.setSelected(false);
            ivChevronDetailStatusBni.setImageResource(R.drawable.ic_expand_more_black_24dp);
            ViewAnimation.collapse(parentContainerItemStatusBni);

        } else {
            ivChevronDetailStatusBni.setSelected(true);
            ivChevronDetailStatusBni.setImageResource(R.drawable.ic_expand_less_black_24dp);
            ViewAnimation.expand(parentContainerItemStatusBni);
        }
    }

    // content data status bri
    @BindView(R.id.ll_parent_recontest_detail_status_bri)
    LinearLayout llParentRecontestDetailStatusBri;
    @BindView(R.id.card_recontest_detail_status_bri)
    com.google.android.material.card.MaterialCardView cardRecontestDetailStatusBri;
    @BindView(R.id.ivChevronDetailStatusBri)
    ImageView ivChevronDetailStatusBri;
    @BindView(R.id.parent_container_item_status_bri)
    RelativeLayout parentContainerItemStatusBri;
    @BindView(R.id.tv_status_verif_bri)
    TextView tvStatusVerifBri;
    @BindView(R.id.tv_date_verif_bri)
    TextView tvDateVerifBri;
    @BindView(R.id.tv_tanggal_input_bri)
    TextView tvTanggalInputBri;
    @BindView(R.id.tv_date_inc_verif_bri)
    TextView tvDateIncVerifBri;
    @BindView(R.id.tv_salin_date_verif_bri)
    TextView tvSalinDateVerifBri;
    @BindView(R.id.tv_analyst_date_verif_bri)
    TextView tvAnalystDateVerifBri;
    @BindView(R.id.tv_submit_date_verif_bri)
    TextView tvSubmitDateVerifBri;
    @BindView(R.id.tv_status_aplikasi_verif_bri)
    TextView tvStatusAplikasiVerifBri;
    @BindView(R.id.tv_status_date_verif_bri)
    TextView tvStatusDateVerifBri;

    @OnClick(R.id.card_recontest_detail_status_bri) void statusBri(){
        if (ivChevronDetailStatusBri.isSelected()) {
            ivChevronDetailStatusBri.setSelected(false);
            ivChevronDetailStatusBri.setImageResource(R.drawable.ic_expand_more_black_24dp);
            ViewAnimation.collapse(parentContainerItemStatusBri);

        } else {
            ivChevronDetailStatusBri.setSelected(true);
            ivChevronDetailStatusBri.setImageResource(R.drawable.ic_expand_less_black_24dp);
            ViewAnimation.expand(parentContainerItemStatusBri);
        }
    }

    // content data status cimb
    /** ButterKnife Code **/
    @BindView(R.id.ll_parent_recontest_detail_status_cimb)
    LinearLayout llParentRecontestDetailStatusCimb;
    @BindView(R.id.card_recontest_detail_status_cimb)
    com.google.android.material.card.MaterialCardView cardRecontestDetailStatusCimb;
    @BindView(R.id.ivChevronDetailStatuscimb)
    ImageView ivChevronDetailStatuscimb;
    @BindView(R.id.parent_container_item_status_cimb)
    RelativeLayout parentContainerItemStatusCimb;
    @BindView(R.id.tv_status_verif_cimb)
    TextView tvStatusVerifCimb;
    @BindView(R.id.tv_date_verif_cimb)
    TextView tvDateVerifCimb;
    @BindView(R.id.tv_tanggal_input_cimb)
    TextView tvTanggalInputCimb;
    @BindView(R.id.tv_date_inc_verif_cimb)
    TextView tvDateIncVerifCimb;
    @BindView(R.id.tv_salin_date_verif_cimb)
    TextView tvSalinDateVerifCimb;
    @BindView(R.id.tv_analyst_date_verif_cimb)
    TextView tvAnalystDateVerifCimb;
    @BindView(R.id.tv_submit_date_verif_cimb)
    TextView tvSubmitDateVerifCimb;
    @BindView(R.id.tv_status_aplikasi_verif_cimb)
    TextView tvStatusAplikasiVerifCimb;
    @BindView(R.id.tv_status_date_verif_cimb)
    TextView tvStatusDateVerifCimb;
    /** ButterKnife Code **/


    @OnClick(R.id.card_recontest_detail_status_cimb) void statusCimb(){
        if (ivChevronDetailStatuscimb.isSelected()) {
            ivChevronDetailStatuscimb.setSelected(false);
            ivChevronDetailStatuscimb.setImageResource(R.drawable.ic_expand_more_black_24dp);
            ViewAnimation.collapse(parentContainerItemStatusCimb);

        } else {
            ivChevronDetailStatuscimb.setSelected(true);
            ivChevronDetailStatuscimb.setImageResource(R.drawable.ic_expand_less_black_24dp);
            ViewAnimation.expand(parentContainerItemStatusCimb);
        }
    }
    
    // content data status dbs
    /** ButterKnife Code **/
    @BindView(R.id.ll_parent_recontest_detail_status_dbs)
    LinearLayout llParentRecontestDetailStatusDbs;
    @BindView(R.id.card_recontest_detail_status_dbs)
    com.google.android.material.card.MaterialCardView cardRecontestDetailStatusDbs;
    @BindView(R.id.ivChevronDetailStatusdbs)
    ImageView ivChevronDetailStatusdbs;
    @BindView(R.id.parent_container_item_status_dbs)
    RelativeLayout parentContainerItemStatusDbs;
    @BindView(R.id.tv_status_verif_dbs)
    TextView tvStatusVerifDbs;
    @BindView(R.id.tv_date_verif_dbs)
    TextView tvDateVerifDbs;
    @BindView(R.id.tv_tanggal_input_dbs)
    TextView tvTanggalInputDbs;
    @BindView(R.id.tv_date_inc_verif_dbs)
    TextView tvDateIncVerifDbs;
    @BindView(R.id.tv_salin_date_verif_dbs)
    TextView tvSalinDateVerifDbs;
    @BindView(R.id.tv_analyst_date_verif_dbs)
    TextView tvAnalystDateVerifDbs;
    @BindView(R.id.tv_submit_date_verif_dbs)
    TextView tvSubmitDateVerifDbs;
    @BindView(R.id.tv_status_aplikasi_verif_dbs)
    TextView tvStatusAplikasiVerifDbs;
    @BindView(R.id.tv_status_date_verif_dbs)
    TextView tvStatusDateVerifDbs;
    /** ButterKnife Code **/

    @OnClick(R.id.card_recontest_detail_status_dbs) void statusDbs(){
        if (ivChevronDetailStatusdbs.isSelected()) {
            ivChevronDetailStatusdbs.setSelected(false);
            ivChevronDetailStatusdbs.setImageResource(R.drawable.ic_expand_more_black_24dp);
            ViewAnimation.collapse(parentContainerItemStatusDbs);

        } else {
            ivChevronDetailStatusdbs.setSelected(true);
            ivChevronDetailStatusdbs.setImageResource(R.drawable.ic_expand_less_black_24dp);
            ViewAnimation.expand(parentContainerItemStatusDbs);
        }
    }


    // content data status mayapada
    /** ButterKnife Code **/
    @BindView(R.id.ll_parent_recontest_detail_status_mayapada)
    LinearLayout llParentRecontestDetailStatusMayapada;
    @BindView(R.id.card_recontest_detail_status_mayapada)
    com.google.android.material.card.MaterialCardView cardRecontestDetailStatusMayapada;
    @BindView(R.id.ivChevronDetailStatusmayapada)
    ImageView ivChevronDetailStatusmayapada;
    @BindView(R.id.parent_container_item_status_mayapada)
    RelativeLayout parentContainerItemStatusMayapada;
    @BindView(R.id.tv_status_verif_mayapada)
    TextView tvStatusVerifMayapada;
    @BindView(R.id.tv_date_verif_mayapada)
    TextView tvDateVerifMayapada;
    @BindView(R.id.tv_tanggal_input_mayapada)
    TextView tvTanggalInputMayapada;
    @BindView(R.id.tv_date_inc_verif_mayapada)
    TextView tvDateIncVerifMayapada;
    @BindView(R.id.tv_salin_date_verif_mayapada)
    TextView tvSalinDateVerifMayapada;
    @BindView(R.id.tv_analyst_date_verif_mayapada)
    TextView tvAnalystDateVerifMayapada;
    @BindView(R.id.tv_submit_date_verif_mayapada)
    TextView tvSubmitDateVerifMayapada;
    @BindView(R.id.tv_status_aplikasi_verif_mayapada)
    TextView tvStatusAplikasiVerifMayapada;
    @BindView(R.id.tv_status_date_verif_mayapada)
    TextView tvStatusDateVerifMayapada;
    /** ButterKnife Code **/

    @OnClick(R.id.card_recontest_detail_status_mayapada) void statusMayapada(){
        if (ivChevronDetailStatusmayapada.isSelected()) {
            ivChevronDetailStatusmayapada.setSelected(false);
            ivChevronDetailStatusmayapada.setImageResource(R.drawable.ic_expand_more_black_24dp);
            ViewAnimation.collapse(parentContainerItemStatusMayapada);

        } else {
            ivChevronDetailStatusmayapada.setSelected(true);
            ivChevronDetailStatusmayapada.setImageResource(R.drawable.ic_expand_less_black_24dp);
            ViewAnimation.expand(parentContainerItemStatusMayapada);
        }
    }
    
    //content data status mega
    /** ButterKnife Code **/
    @BindView(R.id.ll_parent_recontest_detail_status_mega)
    LinearLayout llParentRecontestDetailStatusMega;
    @BindView(R.id.card_recontest_detail_status_mega)
    com.google.android.material.card.MaterialCardView cardRecontestDetailStatusMega;
    @BindView(R.id.ivChevronDetailStatusmega)
    ImageView ivChevronDetailStatusmega;
    @BindView(R.id.parent_container_item_status_mega)
    RelativeLayout parentContainerItemStatusMega;
    @BindView(R.id.tv_status_verif_mega)
    TextView tvStatusVerifMega;
    @BindView(R.id.tv_date_verif_mega)
    TextView tvDateVerifMega;
    @BindView(R.id.tv_tanggal_input_mega)
    TextView tvTanggalInputMega;
    @BindView(R.id.tv_date_inc_verif_mega)
    TextView tvDateIncVerifMega;
    @BindView(R.id.tv_salin_date_verif_mega)
    TextView tvSalinDateVerifMega;
    @BindView(R.id.tv_analyst_date_verif_mega)
    TextView tvAnalystDateVerifMega;
    @BindView(R.id.tv_submit_date_verif_mega)
    TextView tvSubmitDateVerifMega;
    @BindView(R.id.tv_status_aplikasi_verif_mega)
    TextView tvStatusAplikasiVerifMega;
    @BindView(R.id.tv_status_date_verif_mega)
    TextView tvStatusDateVerifMega;
    /** ButterKnife Code **/

    @OnClick(R.id.card_recontest_detail_status_mega) void statusMega(){
        if (ivChevronDetailStatusmega.isSelected()) {
            ivChevronDetailStatusmega.setSelected(false);
            ivChevronDetailStatusmega.setImageResource(R.drawable.ic_expand_more_black_24dp);
            ViewAnimation.collapse(parentContainerItemStatusMega);

        } else {
            ivChevronDetailStatusmega.setSelected(true);
            ivChevronDetailStatusmega.setImageResource(R.drawable.ic_expand_less_black_24dp);
            ViewAnimation.expand(parentContainerItemStatusMega);
        }
    }

    //content data status mnc
    /** ButterKnife Code **/
    @BindView(R.id.ll_parent_recontest_detail_status_mnc)
    LinearLayout llParentRecontestDetailStatusMnc;
    @BindView(R.id.card_recontest_detail_status_mnc)
    com.google.android.material.card.MaterialCardView cardRecontestDetailStatusMnc;
    @BindView(R.id.ivChevronDetailStatusmnc)
    ImageView ivChevronDetailStatusmnc;
    @BindView(R.id.parent_container_item_status_mnc)
    RelativeLayout parentContainerItemStatusMnc;
    @BindView(R.id.tv_status_verif_mnc)
    TextView tvStatusVerifMnc;
    @BindView(R.id.tv_date_verif_mnc)
    TextView tvDateVerifMnc;
    @BindView(R.id.tv_tanggal_input_mnc)
    TextView tvTanggalInputMnc;
    @BindView(R.id.tv_date_inc_verif_mnc)
    TextView tvDateIncVerifMnc;
    @BindView(R.id.tv_salin_date_verif_mnc)
    TextView tvSalinDateVerifMnc;
    @BindView(R.id.tv_analyst_date_verif_mnc)
    TextView tvAnalystDateVerifMnc;
    @BindView(R.id.tv_submit_date_verif_mnc)
    TextView tvSubmitDateVerifMnc;
    @BindView(R.id.tv_status_aplikasi_verif_mnc)
    TextView tvStatusAplikasiVerifMnc;
    @BindView(R.id.tv_status_date_verif_mnc)
    TextView tvStatusDateVerifMnc;
    /** ButterKnife Code **/

    @OnClick(R.id.card_recontest_detail_status_mnc) void statusMnc(){
        if (ivChevronDetailStatusmnc.isSelected()) {
            ivChevronDetailStatusmnc.setSelected(false);
            ivChevronDetailStatusmnc.setImageResource(R.drawable.ic_expand_more_black_24dp);
            ViewAnimation.collapse(parentContainerItemStatusMnc);

        } else {
            ivChevronDetailStatusmnc.setSelected(true);
            ivChevronDetailStatusmnc.setImageResource(R.drawable.ic_expand_less_black_24dp);
            ViewAnimation.expand(parentContainerItemStatusMnc);
        }
    }


    // content data status panin
    /** ButterKnife Code **/
    @BindView(R.id.ll_parent_recontest_detail_status_panin)
    LinearLayout llParentRecontestDetailStatusPanin;
    @BindView(R.id.card_recontest_detail_status_panin)
    com.google.android.material.card.MaterialCardView cardRecontestDetailStatusPanin;
    @BindView(R.id.ivChevronDetailStatuspanin)
    ImageView ivChevronDetailStatuspanin;
    @BindView(R.id.parent_container_item_status_panin)
    RelativeLayout parentContainerItemStatusPanin;
    @BindView(R.id.tv_status_verif_panin)
    TextView tvStatusVerifPanin;
    @BindView(R.id.tv_date_verif_panin)
    TextView tvDateVerifPanin;
    @BindView(R.id.tv_tanggal_input_panin)
    TextView tvTanggalInputPanin;
    @BindView(R.id.tv_date_inc_verif_panin)
    TextView tvDateIncVerifPanin;
    @BindView(R.id.tv_salin_date_verif_panin)
    TextView tvSalinDateVerifPanin;
    @BindView(R.id.tv_analyst_date_verif_panin)
    TextView tvAnalystDateVerifPanin;
    @BindView(R.id.tv_submit_date_verif_panin)
    TextView tvSubmitDateVerifPanin;
    @BindView(R.id.tv_status_aplikasi_verif_panin)
    TextView tvStatusAplikasiVerifPanin;
    @BindView(R.id.tv_status_date_verif_panin)
    TextView tvStatusDateVerifPanin;
    /** ButterKnife Code **/

    @OnClick(R.id.card_recontest_detail_status_panin) void statusPanin(){
        if (ivChevronDetailStatuspanin.isSelected()) {
            ivChevronDetailStatuspanin.setSelected(false);
            ivChevronDetailStatuspanin.setImageResource(R.drawable.ic_expand_more_black_24dp);
            ViewAnimation.collapse(parentContainerItemStatusPanin);

        } else {
            ivChevronDetailStatuspanin.setSelected(true);
            ivChevronDetailStatuspanin.setImageResource(R.drawable.ic_expand_less_black_24dp);
            ViewAnimation.expand(parentContainerItemStatusPanin);
        }
    }
    
    
    // content data status uob
    /** ButterKnife Code **/
    @BindView(R.id.ll_parent_recontest_detail_status_uob)
    LinearLayout llParentRecontestDetailStatusUob;
    @BindView(R.id.card_recontest_detail_status_uob)
    com.google.android.material.card.MaterialCardView cardRecontestDetailStatusUob;
    @BindView(R.id.ivChevronDetailStatusuob)
    ImageView ivChevronDetailStatusuob;
    @BindView(R.id.parent_container_item_status_uob)
    RelativeLayout parentContainerItemStatusUob;
    @BindView(R.id.tv_status_verif_uob)
    TextView tvStatusVerifUob;
    @BindView(R.id.tv_date_verif_uob)
    TextView tvDateVerifUob;
    @BindView(R.id.tv_tanggal_input_uob)
    TextView tvTanggalInputUob;
    @BindView(R.id.tv_date_inc_verif_uob)
    TextView tvDateIncVerifUob;
    @BindView(R.id.tv_salin_date_verif_uob)
    TextView tvSalinDateVerifUob;
    @BindView(R.id.tv_analyst_date_verif_uob)
    TextView tvAnalystDateVerifUob;
    @BindView(R.id.tv_submit_date_verif_uob)
    TextView tvSubmitDateVerifUob;
    @BindView(R.id.tv_status_aplikasi_verif_uob)
    TextView tvStatusAplikasiVerifUob;
    @BindView(R.id.tv_status_date_verif_uob)
    TextView tvStatusDateVerifUob;
    /** ButterKnife Code **/

    @OnClick(R.id.card_recontest_detail_status_uob) void statusUob(){
        if (ivChevronDetailStatusuob.isSelected()) {
            ivChevronDetailStatusuob.setSelected(false);
            ivChevronDetailStatusuob.setImageResource(R.drawable.ic_expand_more_black_24dp);
            ViewAnimation.collapse(parentContainerItemStatusUob);

        } else {
            ivChevronDetailStatusuob.setSelected(true);
            ivChevronDetailStatusuob.setImageResource(R.drawable.ic_expand_less_black_24dp);
            ViewAnimation.expand(parentContainerItemStatusUob);
        }
    }


    
    
    List<ImageDocumentDataCustomer> imageDocumentDataCustomers;
    CarouselImageDocument adapterCarousel;
    int currentPage = 0;
    private Handler handler;
    private Runnable runnable;
    Timer timer;
    final long DELAY_MS = 500;
    final long PERIOD_MS = 3000;
    ProgressDialog loading;
    BaseApiService mApiService;
    String urlImageKtp, urlImageNpwp, urlImageIdCard, urlImageKartuKredit, urlDataPendukung,
            urlImageBri, urlImageBni, urlImageCimb, urlImageDataKotor, urlImageSlip, urlImageSpt,
            urlImageMayapada;

    List<HadiahList> hadiahList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data_customer_recontest);

        mApiService = UtilsApi.getAPIService();
        initToolbar();

        sessionManagement = new SessionManagement(this);
        stateTransactionSales = new StateTransactionSales(this);

        getListDetailCustomer();
        ButterKnife.bind(this);

        Intent intent = getIntent();
        idTransaction = intent.getStringExtra("idtransaction");

        String[] relationship = new String[] {"ORANG TUA", "SUAMI / ISTRI", "KAKAK", "ADIK",
                "IPAR","PAMAN","BIBI","SEPUPU","KEPONAKAN","ANAK","MERTUA","SAUDARA"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, relationship);
        etEmergencyRelationship.setAdapter(adapter);

        // Allcaps text
        etName.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        etTempatLahir.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        etMotherName.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        etCompanyName.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        etCompanyAddress.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        etCompanyJabatan.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        etCompanyBagian.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        etEmergencyName.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        etEmergencyRelationship.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        etHadiah.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        etHadiahReferensi.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

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

    public void initImageDetailCarousel(){
        imageDocumentDataCustomers = new ArrayList<>();
        imageDocumentDataCustomers.add(new ImageDocumentDataCustomer(1, urlImageKtp));
        imageDocumentDataCustomers.add(new ImageDocumentDataCustomer(2, urlImageNpwp));
        imageDocumentDataCustomers.add(new ImageDocumentDataCustomer(3, urlImageIdCard));
        imageDocumentDataCustomers.add(new ImageDocumentDataCustomer(4, urlImageKartuKredit));
        imageDocumentDataCustomers.add(new ImageDocumentDataCustomer(5, urlImageSlip));
        imageDocumentDataCustomers.add(new ImageDocumentDataCustomer(6, urlDataPendukung));

        if (urlImageBri != null || !urlImageBri.equals("null") || !urlImageBri.equals("")){
            imageDocumentDataCustomers.add(new ImageDocumentDataCustomer(7, urlImageBri));
        }

        if (urlImageBni != null || !urlImageBni.equals("null") || !urlImageBni.equals("")){
            imageDocumentDataCustomers.add(new ImageDocumentDataCustomer(8, urlImageBni));
        }

        if (urlImageCimb != null || !urlImageCimb.equals("null") || !urlImageCimb.equals("")){
            imageDocumentDataCustomers.add(new ImageDocumentDataCustomer(9, urlImageCimb));
        }

        if (urlImageMayapada != null || !urlImageMayapada.equals("null") || !urlImageMayapada.equals("")){
            imageDocumentDataCustomers.add(new ImageDocumentDataCustomer(9, urlImageMayapada));
        }

        adapterCarousel = new CarouselImageDocument(imageDocumentDataCustomers, DetailDataCustomerRecontestActivity.this);

        viewPager.setAdapter(adapterCarousel);
        viewPager.setPadding(50, 0, 50, 0);
        viewPager.setCurrentItem(0);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

//                if (position < (adapter.getCount() -1) && position < (colors.length - 1)) {
//                    containerDashboard.setBackgroundColor(
//
//                            (Integer) argbEvaluator.evaluate(
//                                    positionOffset,
//                                    colors[position],
//                                    colors[position + 1]
//                            )
//                    );
//                }
//
//                else {
//                    viewPager.setBackgroundColor(colors[colors.length - 1]);
//                }
//                // Change the current position intimation
//                int pos=position+1;
//
//                if(pos==dotsCount&&previous_pos==(dotsCount-1))
//                    show_animation();
//                else if(pos==(dotsCount-1)&&previous_pos==dotsCount)
//                    hide_animation();
//
//                previous_pos=pos;
            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                if (adapterCarousel.getCount() == currentPage) {
                    currentPage = 0 - 1;
                } else {
                    currentPage++;
                }
                viewPager.setCurrentItem(currentPage, true);
                handler.postDelayed(this, PERIOD_MS);
            }
        };
    }

    private void getListDetailCustomer(){
        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);
        loading.setCanceledOnTouchOutside(false);


        Intent intent = getIntent();
        String idTransaction = intent.getStringExtra("idtransaction");
        String statusRjVerif = intent.getStringExtra("statusrjverif");
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
                                    String tanggal = jsonRESULTS.getJSONObject("data").getString("tanggalinput");
                                    String tanggalVerif = jsonRESULTS.getJSONObject("data").getString("tanggalverif");
                                    String nik = jsonRESULTS.getJSONObject("data").getString("nik");
                                    String nama = jsonRESULTS.getJSONObject("data").getString("nama");
                                    String tempatLahir = jsonRESULTS.getJSONObject("data").getString("lahir");
                                    String dob = jsonRESULTS.getJSONObject("data").getString("dob");
                                    String telepon = jsonRESULTS.getJSONObject("data").getString("telepon");
                                    String handphone = jsonRESULTS.getJSONObject("data").getString("handphone");

                                    String ibu = jsonRESULTS.getJSONObject("data").getString("ibu");
                                    String perusahaan = jsonRESULTS.getJSONObject("data").getString("perusahaan");
                                    String alamatkantor = jsonRESULTS.getJSONObject("data").getString("alamatkantor");
                                    String teleponkantor = jsonRESULTS.getJSONObject("data").getString("teleponkantor");
                                    String jabatankantor = jsonRESULTS.getJSONObject("data").getString("jabatan");
                                    String bagiankantor = jsonRESULTS.getJSONObject("data").getString("bagian");


                                    String econtact = jsonRESULTS.getJSONObject("data").getString("econtact");
                                    String hubunganecontact = jsonRESULTS.getJSONObject("data").getString("hubunganecontact");
                                    String teleponecontact = jsonRESULTS.getJSONObject("data").getString("teleponecontact");

                                    String hadiahAll = jsonRESULTS.getJSONObject("data").getString("hadiah");




                                    // image source
                                    urlImageKtp = jsonRESULTS.getJSONObject("data").getString("url_ktp");
                                    urlImageNpwp = jsonRESULTS.getJSONObject("data").getString("url_npwp");
                                    urlImageIdCard = jsonRESULTS.getJSONObject("data").getString("url_fotoid");
                                    urlImageKartuKredit = jsonRESULTS.getJSONObject("data").getString("url_kartu_kredit");
                                    urlDataPendukung = jsonRESULTS.getJSONObject("data").getString("url_date_pendukung");
                                    urlImageBri = jsonRESULTS.getJSONObject("data").getString("url_bri");
                                    urlImageBni = jsonRESULTS.getJSONObject("data").getString("url_bni");
                                    urlImageCimb = jsonRESULTS.getJSONObject("data").getString("url_cimb");
                                    urlImageDataKotor = jsonRESULTS.getJSONObject("data").getString("url_data_kotor");
                                    urlImageSlip = jsonRESULTS.getJSONObject("data").getString("url_slip_gaji");
                                    urlImageSpt = jsonRESULTS.getJSONObject("data").getString("url_spt");
                                    urlImageMayapada = jsonRESULTS.getJSONObject("data").getString("url_mayapada");


                                    // filling to titlebar
                                    titlebarUsername.setText(nama);
                                    titlebarStatusAplikasi.setText(statusRjVerif);
                                    titlebarTglPengajuan.setText(tanggal);
                                    titlebarTglVerif.setText(tanggalVerif);

                                    // filling to text input personal data
                                    etName.setText(nama);
                                    etNik.setText(nik);
                                    etTempatLahir.setText(tempatLahir);
                                    etDob.setText(dob);
                                    etHp1.setText(telepon);
                                    etHp2.setText(handphone);
                                    etMotherName.setText(ibu);

                                    // filling to text emergency contact
                                    etEmergencyName.setText(econtact);
                                    etEmergencyRelationship.setText(hubunganecontact);
                                    etEmergencyTelp.setText(teleponecontact);

                                    // filling to text input pekerjaan
                                    String noTelepon = "";
                                    String ext = "";
                                    etCompanyName.setText(perusahaan);
                                    etCompanyAddress.setText(alamatkantor);
                                    StringTokenizer tokensPhone = new StringTokenizer(teleponkantor, "-");

                                    String[] splitTeleponKantor = teleponkantor.split("-");
                                    if(splitTeleponKantor.length > 1){
                                        noTelepon = tokensPhone.nextToken();
                                        ext = tokensPhone.nextToken();
                                        etCompanyPhoneExt.setText(ext);
                                    }else {
                                        noTelepon = tokensPhone.nextToken();

                                    }
                                    etCompanyPhone.setText(noTelepon);
                                    etCompanyJabatan.setText(jabatankantor);
                                    etCompanyBagian.setText(bagiankantor);


                                    // filling to text input hadiah
                                    String hadiah = "";
                                    String hadiahReferensi = "";
                                    StringTokenizer tokens = new StringTokenizer(hadiahAll, "-");
                                    hadiah = tokens.nextToken();
                                    hadiahReferensi = tokens.nextToken();

                                    etHadiah.setText(hadiah);
                                    etHadiahReferensi.setText(hadiahReferensi);


                                    // general bank
                                    String tglIncDoc, tglVerif, tglInput;

                                    tglVerif = jsonRESULTS.getJSONObject("data").getString("tanggalverif");
                                    tglInput = jsonRESULTS.getJSONObject("data").getString("tanggalinput");
                                    tglIncDoc = jsonRESULTS.getJSONObject("data").getString("tanggalicd");
//

                                    // bank BRI
                                    String StatusBri,VerifikasiBri,tanggalSubmitBri, statusappBri,
                                            tglSalinJurtulBri, tglAnalystCheckerBri, tglSubmitBri,
                                            statusAplikasiBalikanBri,tglStatusReportBri;


                                    StatusBri = jsonRESULTS.getJSONObject("data").getString("bri");
                                    String tanggalPengajuanBri = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiBri = jsonRESULTS.getJSONObject("data").getString("verifbri");
                                    tglSalinJurtulBri = jsonRESULTS.getJSONObject("data").getString("tanggaljurtulbri");
                                    tglAnalystCheckerBri = jsonRESULTS.getJSONObject("data").getString("tanggalcheckerbri");
                                    tanggalSubmitBri = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitbri");
                                    statusAplikasiBalikanBri = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanbri");
                                    tglStatusReportBri = jsonRESULTS.getJSONObject("data").getString("reportbri");

                                    if(StatusBri.equals("") || StatusBri.equals("null")){
                                        StatusBri = "-";
                                    }
                                    if(VerifikasiBri.equals("") || VerifikasiBri.equals("null")){
                                        VerifikasiBri = "-";
                                    }
                                    if(tanggalSubmitBri.equals("") || tanggalSubmitBri.equals("null")){
                                        tanggalSubmitBri = "-";
                                    }
                                    if(statusAplikasiBalikanBri.equals("") || statusAplikasiBalikanBri.equals("null")){
                                        statusAplikasiBalikanBri = "-";
                                    }

                                    if(statusRjVerif.equals("All")){
                                        if(VerifikasiBri.equals("CBC") || VerifikasiBri.equals("HTC")
                                            || VerifikasiBri.equals("IVN") || VerifikasiBri.equals("NTE")
                                            || VerifikasiBri.equals("NVA")){
                                            llParentRecontestDetailStatusBri.setVisibility(View.VISIBLE);
                                        }
                                    }else{
                                        if(VerifikasiBri.equals(statusRjVerif)){
                                            llParentRecontestDetailStatusBri.setVisibility(View.VISIBLE);
                                        }
                                    }



                                    tvStatusVerifBri.setText(statusRjVerif);
                                    tvDateVerifBri.setText(tglVerif);
                                    tvTanggalInputBri.setText(tglInput);
                                    tvDateIncVerifBri.setText(tglIncDoc);
                                    tvSalinDateVerifBri.setText(tglSalinJurtulBri);
                                    tvAnalystDateVerifBri.setText(tglAnalystCheckerBri);
                                    tvSubmitDateVerifBri.setText(tanggalSubmitBri);
                                    tvStatusAplikasiVerifBri.setText(statusAplikasiBalikanBri);
                                    tvStatusDateVerifBri.setText(tglStatusReportBri);

                                    // bank BNI
                                    String StatusBni,VerifikasiBni,tanggalSubmitBni, statusappBni,
                                            tglSalinJurtulBni, tglAnalystCheckerBni, tglSubmitBni,
                                            statusAplikasiBalikanBni,tglStatusReportBni;


                                    StatusBni = jsonRESULTS.getJSONObject("data").getString("bni");
                                    String tanggalPengajuanBni = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiBni = jsonRESULTS.getJSONObject("data").getString("verifbni");
                                    tglSalinJurtulBni = jsonRESULTS.getJSONObject("data").getString("tanggaljurtulbni");
                                    tglAnalystCheckerBni = jsonRESULTS.getJSONObject("data").getString("tanggalcheckerbni");
                                    tanggalSubmitBni = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitbni");
                                    statusAplikasiBalikanBni = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanbni");
                                    tglStatusReportBni = jsonRESULTS.getJSONObject("data").getString("reportbni");

                                    if(StatusBni.equals("") || StatusBni.equals("null")){
                                        StatusBni = "-";
                                    }
                                    if(VerifikasiBni.equals("") || VerifikasiBni.equals("null")){
                                        VerifikasiBni = "-";
                                    }
                                    if(tanggalSubmitBni.equals("") || tanggalSubmitBni.equals("null")){
                                        tanggalSubmitBni = "-";
                                    }
                                    if(statusAplikasiBalikanBni.equals("") || statusAplikasiBalikanBni.equals("null")){
                                        statusAplikasiBalikanBni = "-";
                                    }

                                    if(statusRjVerif.equals("All")){
                                        if(VerifikasiBni.equals("CBC") || VerifikasiBni.equals("HTC")
                                                || VerifikasiBni.equals("IVN") || VerifikasiBni.equals("NTE")
                                                || VerifikasiBni.equals("NVA")){
                                            llParentRecontestDetailStatusBni.setVisibility(View.VISIBLE);
                                        }
                                    }else{
                                        if(VerifikasiBni.equals(statusRjVerif)){
                                            llParentRecontestDetailStatusBni.setVisibility(View.VISIBLE);
                                        }
                                    }

                                    tvStatusVerifBni.setText(statusRjVerif);
                                    tvDateVerifBni.setText(tglVerif);
                                    tvTanggalInputBni.setText(tglInput);
                                    tvDateIncVerifBni.setText(tglIncDoc);
                                    tvSalinDateVerifBni.setText(tglSalinJurtulBni);
                                    tvAnalystDateVerifBni.setText(tglAnalystCheckerBni);
                                    tvSubmitDateVerifBni.setText(tanggalSubmitBni);
                                    tvStatusAplikasiVerifBni.setText(statusAplikasiBalikanBni);
                                    tvStatusDateVerifBni.setText(tglStatusReportBni);

                                    // bank BCA
                                    String StatusBca,VerifikasiBca,tanggalSubmitBca, statusappBca,
                                            tglSalinJurtulBca, tglAnalystCheckerBca, tglSubmitBca,
                                            statusAplikasiBalikanBca,tglStatusReportBca;


                                    StatusBca = jsonRESULTS.getJSONObject("data").getString("bca");
                                    String tanggalPengajuanBca = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiBca = jsonRESULTS.getJSONObject("data").getString("verifbca");
                                    tglSalinJurtulBca = jsonRESULTS.getJSONObject("data").getString("tanggaljurtulbca");
                                    tglAnalystCheckerBca = jsonRESULTS.getJSONObject("data").getString("tanggalcheckerbca");
                                    tanggalSubmitBca = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitbca");
                                    statusAplikasiBalikanBca = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanbca");
                                    tglStatusReportBca = jsonRESULTS.getJSONObject("data").getString("reportbca");

                                    if(StatusBca.equals("") || StatusBca.equals("null")){
                                        StatusBca = "-";
                                    }
                                    if(VerifikasiBca.equals("") || VerifikasiBca.equals("null")){
                                        VerifikasiBca = "-";
                                    }
                                    if(tanggalSubmitBca.equals("") || tanggalSubmitBca.equals("null")){
                                        tanggalSubmitBca = "-";
                                    }
                                    if(statusAplikasiBalikanBca.equals("") || statusAplikasiBalikanBca.equals("null")){
                                        statusAplikasiBalikanBca = "-";
                                    }

                                    if(statusRjVerif.equals("All")){
                                        if(VerifikasiBca.equals("CBC") || VerifikasiBca.equals("HTC")
                                                || VerifikasiBca.equals("IVN") || VerifikasiBca.equals("NTE")
                                                || VerifikasiBca.equals("NVA")){
                                            llParentRecontestDetailStatusBca.setVisibility(View.VISIBLE);
                                        }
                                    }else{
                                        if(VerifikasiBca.equals(statusRjVerif)){
                                            llParentRecontestDetailStatusBca.setVisibility(View.VISIBLE);
                                        }
                                    }



                                    tvStatusVerifBca.setText(statusRjVerif);
                                    tvDateVerifBca.setText(tglVerif);
                                    tvTanggalInputBca.setText(tglInput);
                                    tvDateIncVerifBca.setText(tglIncDoc);
                                    tvSalinDateVerifBca.setText(tglSalinJurtulBca);
                                    tvAnalystDateVerifBca.setText(tglAnalystCheckerBca);
                                    tvSubmitDateVerifBca.setText(tanggalSubmitBca);
                                    tvStatusAplikasiVerifBca.setText(statusAplikasiBalikanBca);
                                    tvStatusDateVerifBca.setText(tglStatusReportBca);

                                    // bank CIMB
                                    String StatusNiaga,VerifikasiNiaga,tanggalSubmitNiaga, statusappNiaga,
                                            tglSalinJurtulNiaga, tglAnalystCheckerNiaga, tglSubmitNiaga,
                                            statusAplikasiBalikanNiaga,tglStatusReportNiaga;


                                    StatusNiaga = jsonRESULTS.getJSONObject("data").getString("niaga");
                                    String tanggalPengajuanNiaga = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiNiaga = jsonRESULTS.getJSONObject("data").getString("verifniaga");
                                    tglSalinJurtulNiaga = jsonRESULTS.getJSONObject("data").getString("tanggaljurtulniaga");
                                    tglAnalystCheckerNiaga = jsonRESULTS.getJSONObject("data").getString("tanggalcheckerniaga");
                                    tanggalSubmitNiaga = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitniaga");
                                    statusAplikasiBalikanNiaga = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanniaga");
                                    tglStatusReportNiaga = jsonRESULTS.getJSONObject("data").getString("reportniaga");

                                    if(StatusNiaga.equals("") || StatusNiaga.equals("null")){
                                        StatusNiaga = "-";
                                    }
                                    if(VerifikasiNiaga.equals("") || VerifikasiNiaga.equals("null")){
                                        VerifikasiNiaga = "-";
                                    }
                                    if(tanggalSubmitNiaga.equals("") || tanggalSubmitNiaga.equals("null")){
                                        tanggalSubmitNiaga = "-";
                                    }
                                    if(statusAplikasiBalikanNiaga.equals("") || statusAplikasiBalikanNiaga.equals("null")){
                                        statusAplikasiBalikanNiaga = "-";
                                    }

                                    if(statusRjVerif.equals("All")){
                                        if(VerifikasiNiaga.equals("CBC") || VerifikasiNiaga.equals("HTC")
                                                || VerifikasiNiaga.equals("IVN") || VerifikasiNiaga.equals("NTE")
                                                || VerifikasiNiaga.equals("NVA")){
                                            llParentRecontestDetailStatusCimb.setVisibility(View.VISIBLE);
                                        }
                                    }else{
                                        if(VerifikasiNiaga.equals(statusRjVerif)){
                                            llParentRecontestDetailStatusCimb.setVisibility(View.VISIBLE);
                                        }
                                    }

                                    // bank
                                    tvStatusVerifCimb.setText(statusRjVerif);
                                    tvDateVerifCimb.setText(tglVerif);
                                    tvTanggalInputCimb.setText(tglInput);
                                    tvDateIncVerifCimb.setText(tglIncDoc);
                                    tvSalinDateVerifCimb.setText(tglSalinJurtulNiaga);
                                    tvAnalystDateVerifCimb.setText(tglAnalystCheckerNiaga);
                                    tvSubmitDateVerifCimb.setText(tanggalSubmitNiaga);
                                    tvStatusAplikasiVerifCimb.setText(statusAplikasiBalikanNiaga);
                                    tvStatusDateVerifCimb.setText(tglStatusReportNiaga);

                                    // bank Mayapada
                                    String StatusMayapada,VerifikasiMayapada,tanggalSubmitMayapada,
                                            tglSalinJurtulMayapada, tglAnalystCheckerMayapada,
                                            statusAplikasiBalikanMayapada,tglStatusReportMayapada;


                                    StatusMayapada = jsonRESULTS.getJSONObject("data").getString("mayapada");
                                    String tanggalPengajuanMayapada = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiMayapada = jsonRESULTS.getJSONObject("data").getString("verifmayapada");
                                    tglSalinJurtulMayapada = jsonRESULTS.getJSONObject("data").getString("tanggaljurtulmayapada");
                                    tglAnalystCheckerMayapada = jsonRESULTS.getJSONObject("data").getString("tanggalcheckermayapada");
                                    tanggalSubmitMayapada = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitmayapada");
                                    statusAplikasiBalikanMayapada = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanmayapada");
                                    tglStatusReportMayapada = jsonRESULTS.getJSONObject("data").getString("reportmayapada");

                                    if(StatusMayapada.equals("") || StatusMayapada.equals("null")){
                                        StatusMayapada = "-";
                                    }
                                    if(VerifikasiMayapada.equals("") || VerifikasiMayapada.equals("null")){
                                        VerifikasiMayapada = "-";
                                    }
                                    if(tanggalSubmitMayapada.equals("") || tanggalSubmitMayapada.equals("null")){
                                        tanggalSubmitMayapada = "-";
                                    }
                                    if(statusAplikasiBalikanMayapada.equals("") || statusAplikasiBalikanMayapada.equals("null")){
                                        statusAplikasiBalikanMayapada = "-";
                                    }

                                    if(statusRjVerif.equals("All")){
                                        if(VerifikasiMayapada.equals("CBC") || VerifikasiMayapada.equals("HTC")
                                                || VerifikasiMayapada.equals("IVN") || VerifikasiMayapada.equals("NTE")
                                                || VerifikasiMayapada.equals("NVA")){
                                            llParentRecontestDetailStatusMayapada.setVisibility(View.VISIBLE);
                                        }
                                    }else{
                                        if(VerifikasiMayapada.equals(statusRjVerif)){
                                            llParentRecontestDetailStatusMayapada.setVisibility(View.VISIBLE);
                                        }
                                    }

                                    // bank
                                    tvStatusVerifMayapada.setText(statusRjVerif);
                                    tvDateVerifMayapada.setText(tglVerif);
                                    tvTanggalInputMayapada.setText(tglInput);
                                    tvDateIncVerifMayapada.setText(tglIncDoc);
                                    tvSalinDateVerifMayapada.setText(tglSalinJurtulMayapada);
                                    tvAnalystDateVerifMayapada.setText(tglAnalystCheckerMayapada);
                                    tvSubmitDateVerifMayapada.setText(tanggalSubmitMayapada);
                                    tvStatusAplikasiVerifMayapada.setText(statusAplikasiBalikanMayapada);
                                    tvStatusDateVerifMayapada.setText(tglStatusReportMayapada);

                                    // bank Dbs
                                    String StatusDbs,VerifikasiDbs,tanggalSubmitDbs,
                                            tglSalinJurtulDbs, tglAnalystCheckerDbs,
                                            statusAplikasiBalikanDbs,tglStatusReportDbs;


                                    StatusDbs = jsonRESULTS.getJSONObject("data").getString("dbs");
                                    String tanggalPengajuanDbs = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiDbs = jsonRESULTS.getJSONObject("data").getString("verifdbs");
                                    tglSalinJurtulDbs = jsonRESULTS.getJSONObject("data").getString("tanggaljurtuldbs");
                                    tglAnalystCheckerDbs = jsonRESULTS.getJSONObject("data").getString("tanggalcheckerdbs");
                                    tanggalSubmitDbs= jsonRESULTS.getJSONObject("data").getString("tanggalsubmitdbs");
                                    statusAplikasiBalikanDbs = jsonRESULTS.getJSONObject("data").getString("keteranganbalikandbs");
                                    tglStatusReportDbs = jsonRESULTS.getJSONObject("data").getString("reportdbs");

                                    if(StatusDbs.equals("") || StatusDbs.equals("null")){
                                        StatusDbs = "-";
                                    }
                                    if(VerifikasiDbs.equals("") || VerifikasiDbs.equals("null")){
                                        VerifikasiDbs = "-";
                                    }
                                    if(tanggalSubmitDbs.equals("") || tanggalSubmitDbs.equals("null")){
                                        tanggalSubmitDbs = "-";
                                    }
                                    if(statusAplikasiBalikanDbs.equals("") || statusAplikasiBalikanDbs.equals("null")){
                                        statusAplikasiBalikanDbs = "-";
                                    }

                                    if(statusRjVerif.equals("All")){
                                        if(VerifikasiDbs.equals("CBC") || VerifikasiDbs.equals("HTC")
                                                || VerifikasiDbs.equals("IVN") || VerifikasiDbs.equals("NTE")
                                                || VerifikasiDbs.equals("NVA")){
                                            llParentRecontestDetailStatusDbs.setVisibility(View.VISIBLE);
                                        }
                                    }else{
                                        if(VerifikasiDbs.equals(statusRjVerif)){
                                            llParentRecontestDetailStatusDbs.setVisibility(View.VISIBLE);
                                        }
                                    }

                                    // bank
                                    tvStatusVerifDbs.setText(statusRjVerif);
                                    tvDateVerifDbs.setText(tglVerif);
                                    tvTanggalInputDbs.setText(tglInput);
                                    tvDateIncVerifDbs.setText(tglIncDoc);
                                    tvSalinDateVerifDbs.setText(tglSalinJurtulDbs);
                                    tvAnalystDateVerifDbs.setText(tglAnalystCheckerDbs);
                                    tvSubmitDateVerifDbs.setText(tanggalSubmitDbs);
                                    tvStatusAplikasiVerifDbs.setText(statusAplikasiBalikanDbs);
                                    tvStatusDateVerifDbs.setText(tglStatusReportDbs);

                                    // bank MNC
                                    String StatusMnc,VerifikasiMnc,tanggalSubmitMnc,
                                            tglSalinJurtulMnc, tglAnalystCheckerMnc,
                                            statusAplikasiBalikanMnc,tglStatusReportMnc;


                                    StatusMnc = jsonRESULTS.getJSONObject("data").getString("mnc");
                                    String tanggalPengajuanMnc= jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiMnc = jsonRESULTS.getJSONObject("data").getString("verifmnc");
                                    tglSalinJurtulMnc = jsonRESULTS.getJSONObject("data").getString("tanggaljurtulmnc");
                                    tglAnalystCheckerMnc = jsonRESULTS.getJSONObject("data").getString("tanggalcheckermnc");
                                    tanggalSubmitMnc= jsonRESULTS.getJSONObject("data").getString("tanggalsubmitmnc");
                                    statusAplikasiBalikanMnc = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanmnc");
                                    tglStatusReportMnc = jsonRESULTS.getJSONObject("data").getString("reportmnc");

                                    if(StatusMnc.equals("") || StatusMnc.equals("null")){
                                        StatusMnc = "-";
                                    }
                                    if(VerifikasiMnc.equals("") || VerifikasiMnc.equals("null")){
                                        VerifikasiMnc = "-";
                                    }
                                    if(tanggalSubmitMnc.equals("") || tanggalSubmitMnc.equals("null")){
                                        tanggalSubmitMnc = "-";
                                    }
                                    if(statusAplikasiBalikanMnc.equals("") || statusAplikasiBalikanMnc.equals("null")){
                                        statusAplikasiBalikanMnc = "-";
                                    }

                                    if(statusRjVerif.equals("All")){
                                        if(VerifikasiMnc.equals("CBC") || VerifikasiMnc.equals("HTC")
                                                || VerifikasiMnc.equals("IVN") || VerifikasiMnc.equals("NTE")
                                                || VerifikasiMnc.equals("NVA")){
                                            llParentRecontestDetailStatusMnc.setVisibility(View.VISIBLE);
                                        }
                                    }else{
                                        if(VerifikasiMnc.equals(statusRjVerif)){
                                            llParentRecontestDetailStatusMnc.setVisibility(View.VISIBLE);
                                        }
                                    }

                                    // bank
                                    tvStatusVerifMnc.setText(statusRjVerif);
                                    tvDateVerifMnc.setText(tglVerif);
                                    tvTanggalInputMnc.setText(tglInput);
                                    tvDateIncVerifMnc.setText(tglIncDoc);
                                    tvSalinDateVerifMnc.setText(tglSalinJurtulMnc);
                                    tvAnalystDateVerifMnc.setText(tglAnalystCheckerMnc);
                                    tvSubmitDateVerifMnc.setText(tanggalSubmitMnc);
                                    tvStatusAplikasiVerifMnc.setText(statusAplikasiBalikanMnc);
                                    tvStatusDateVerifMnc.setText(tglStatusReportMnc);

                                    // bank UOB
                                    String StatusUob,VerifikasiUob,tanggalSubmitUob,
                                            tglSalinJurtulUob, tglAnalystCheckerUob,
                                            statusAplikasiBalikanUob,tglStatusReportUob;


                                    StatusUob = jsonRESULTS.getJSONObject("data").getString("uob");
                                    String tanggalPengajuanUob= jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiUob = jsonRESULTS.getJSONObject("data").getString("verifuob");
                                    tglSalinJurtulUob = jsonRESULTS.getJSONObject("data").getString("tanggaljurtuluob");
                                    tglAnalystCheckerUob = jsonRESULTS.getJSONObject("data").getString("tanggalcheckeruob");
                                    tanggalSubmitUob= jsonRESULTS.getJSONObject("data").getString("tanggalsubmituob");
                                    statusAplikasiBalikanUob = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanuob");
                                    tglStatusReportUob = jsonRESULTS.getJSONObject("data").getString("reportuob");

                                    if(StatusUob.equals("") || StatusUob.equals("null")){
                                        StatusUob = "-";
                                    }
                                    if(VerifikasiUob.equals("") || VerifikasiUob.equals("null")){
                                        VerifikasiUob = "-";
                                    }
                                    if(tanggalSubmitUob.equals("") || tanggalSubmitUob.equals("null")){
                                        tanggalSubmitUob = "-";
                                    }
                                    if(statusAplikasiBalikanUob.equals("") || statusAplikasiBalikanUob.equals("null")){
                                        statusAplikasiBalikanUob = "-";
                                    }

                                    if(statusRjVerif.equals("All")){
                                        if(VerifikasiUob.equals("CBC") || VerifikasiUob.equals("HTC")
                                                || VerifikasiUob.equals("IVN") || VerifikasiUob.equals("NTE")
                                                || VerifikasiUob.equals("NVA")){
                                            llParentRecontestDetailStatusUob.setVisibility(View.VISIBLE);
                                        }
                                    }else{
                                        if(VerifikasiUob.equals(statusRjVerif)){
                                            llParentRecontestDetailStatusUob.setVisibility(View.VISIBLE);
                                        }
                                    }

                                    // bank
                                    tvStatusVerifUob.setText(statusRjVerif);
                                    tvDateVerifUob.setText(tglVerif);
                                    tvTanggalInputUob.setText(tglInput);
                                    tvDateIncVerifUob.setText(tglIncDoc);
                                    tvSalinDateVerifUob.setText(tglSalinJurtulUob);
                                    tvAnalystDateVerifUob.setText(tglAnalystCheckerUob);
                                    tvSubmitDateVerifUob.setText(tanggalSubmitUob);
                                    tvStatusAplikasiVerifUob.setText(statusAplikasiBalikanUob);
                                    tvStatusDateVerifUob.setText(tglStatusReportUob);

                                    // bank Mega
                                    String StatusMega,VerifikasiMega,tanggalSubmitMega,
                                            tglSalinJurtulMega, tglAnalystCheckerMega,
                                            statusAplikasiBalikanMega,tglStatusReportMega;


                                    StatusMega = jsonRESULTS.getJSONObject("data").getString("mega");
                                    String tanggalPengajuanMega = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiMega = jsonRESULTS.getJSONObject("data").getString("verifmega");
                                    tglSalinJurtulMega = jsonRESULTS.getJSONObject("data").getString("tanggaljurtulmega");
                                    tglAnalystCheckerMega = jsonRESULTS.getJSONObject("data").getString("tanggalcheckermega");
                                    tanggalSubmitMega = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitmega");
                                    statusAplikasiBalikanMega = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanmega");
                                    tglStatusReportMega = jsonRESULTS.getJSONObject("data").getString("reportmega");

                                    if(StatusMega.equals("") || StatusMega.equals("null")){
                                        StatusMega = "-";
                                    }
                                    if(VerifikasiMega.equals("") || VerifikasiMega.equals("null")){
                                        VerifikasiMega = "-";
                                    }
                                    if(tanggalSubmitMega.equals("") || tanggalSubmitMega.equals("null")){
                                        tanggalSubmitMega = "-";
                                    }
                                    if(statusAplikasiBalikanMega.equals("") || statusAplikasiBalikanMega.equals("null")){
                                        statusAplikasiBalikanMega = "-";
                                    }

                                    if(statusRjVerif.equals("All")){
                                        if(VerifikasiMega.equals("CBC") || VerifikasiMega.equals("HTC")
                                                || VerifikasiMega.equals("IVN") || VerifikasiMega.equals("NTE")
                                                || VerifikasiMega.equals("NVA")){
                                            llParentRecontestDetailStatusMega.setVisibility(View.VISIBLE);
                                        }
                                    }else{
                                        if(VerifikasiMega.equals(statusRjVerif)){
                                            llParentRecontestDetailStatusMega.setVisibility(View.VISIBLE);
                                        }
                                    }

                                    // bank
                                    tvStatusVerifMega.setText(statusRjVerif);
                                    tvDateVerifMega.setText(tglVerif);
                                    tvTanggalInputMega.setText(tglInput);
                                    tvDateIncVerifMega.setText(tglIncDoc);
                                    tvSalinDateVerifMega.setText(tglSalinJurtulMega);
                                    tvAnalystDateVerifMega.setText(tglAnalystCheckerMega);
                                    tvSubmitDateVerifMega.setText(tanggalSubmitMega);
                                    tvStatusAplikasiVerifMega.setText(statusAplikasiBalikanMega);
                                    tvStatusDateVerifMega.setText(tglStatusReportMega);

                                    // bank Panin
                                    String StatusPanin,VerifikasiPanin,tanggalSubmitPanin,
                                            tglSalinJurtulPanin, tglAnalystCheckerPanin,
                                            statusAplikasiBalikanPanin,tglStatusReportPanin;


                                    StatusPanin = jsonRESULTS.getJSONObject("data").getString("panin");
                                    String tanggalPengajuanPanin = jsonRESULTS.getJSONObject("data").getString("tanggal");
                                    VerifikasiPanin = jsonRESULTS.getJSONObject("data").getString("verifpanin");
                                    tglSalinJurtulPanin = jsonRESULTS.getJSONObject("data").getString("tanggaljurtulpanin");
                                    tglAnalystCheckerPanin = jsonRESULTS.getJSONObject("data").getString("tanggalcheckerpanin");
                                    tanggalSubmitPanin = jsonRESULTS.getJSONObject("data").getString("tanggalsubmitpanin");
                                    statusAplikasiBalikanPanin = jsonRESULTS.getJSONObject("data").getString("keteranganbalikanpanin");
                                    tglStatusReportPanin = jsonRESULTS.getJSONObject("data").getString("reportpanin");

                                    if(StatusPanin.equals("") || StatusPanin.equals("null")){
                                        StatusPanin = "-";
                                    }
                                    if(VerifikasiPanin.equals("") || VerifikasiPanin.equals("null")){
                                        VerifikasiPanin = "-";
                                    }
                                    if(tanggalSubmitPanin.equals("") || tanggalSubmitPanin.equals("null")){
                                        tanggalSubmitPanin = "-";
                                    }
                                    if(statusAplikasiBalikanPanin.equals("") || statusAplikasiBalikanPanin.equals("null")){
                                        statusAplikasiBalikanPanin = "-";
                                    }

                                    if(statusRjVerif.equals("All")){
                                        if(VerifikasiPanin.equals("CBC") || VerifikasiPanin.equals("HTC")
                                                || VerifikasiPanin.equals("IVN") || VerifikasiPanin.equals("NTE")
                                                || VerifikasiPanin.equals("NVA")){
                                            llParentRecontestDetailStatusPanin.setVisibility(View.VISIBLE);
                                        }
                                    }else{
                                        if(VerifikasiPanin.equals(statusRjVerif)){
                                            llParentRecontestDetailStatusPanin.setVisibility(View.VISIBLE);
                                        }
                                    }

                                    // bank
                                    tvStatusVerifPanin.setText(statusRjVerif);
                                    tvDateVerifPanin.setText(tglVerif);
                                    tvTanggalInputPanin.setText(tglInput);
                                    tvDateIncVerifPanin.setText(tglIncDoc);
                                    tvSalinDateVerifPanin.setText(tglSalinJurtulPanin);
                                    tvAnalystDateVerifPanin.setText(tglAnalystCheckerPanin);
                                    tvSubmitDateVerifPanin.setText(tanggalSubmitPanin);
                                    tvStatusAplikasiVerifPanin.setText(statusAplikasiBalikanPanin);
                                    tvStatusDateVerifPanin.setText(tglStatusReportPanin);

                                    // image carousel
                                    initImageDetailCarousel();

                                    // get Hadiah Data
                                    requestGetDataHadiah();


                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(DetailDataCustomerRecontestActivity.this, error_message, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(DetailDataCustomerRecontestActivity.this, error_message, Toast.LENGTH_SHORT).show();
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

    private void requestGetDataHadiah(){
        loading = ProgressDialog.show(DetailDataCustomerRecontestActivity.this, null, "Harap Tunggu...", true, false);

        mApiService.getListDataHadiah()
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){

                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getInt("status") == 200){

                                    JSONArray m_jsonData = jsonRESULTS.getJSONArray("data");
//                                        JSONArray arr_jsonArticle = m_jsonData.getJSONArray("articles");
                                    ArrayList<String> hadiah = new ArrayList<String>();

                                    hadiahList = new ArrayList<>();
                                    for(int i = 0; i < m_jsonData.length(); i++) {
                                        JSONObject jsonObject = m_jsonData.getJSONObject(i);

                                        String id = jsonObject.getString("id");
                                        String namaBarang = jsonObject.getString("nama_barang");
                                        String jenis = jsonObject.getString("jenis");
                                        String foto = jsonObject.getString("foto");

                                        hadiah.add(namaBarang);

                                    }
                                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_popup_item, hadiah);
                                    etHadiah.setAdapter(adapter);
                                    etHadiahReferensi.setAdapter(adapter);
                                    loading.dismiss();

                                } else {
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(DetailDataCustomerRecontestActivity.this, error_message, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(DetailDataCustomerRecontestActivity.this, error_message, Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                });
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }


}
