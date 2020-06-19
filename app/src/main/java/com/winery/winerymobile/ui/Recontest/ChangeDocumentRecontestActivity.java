package com.winery.winerymobile.ui.Recontest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.snackbar.Snackbar;
import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.APIhelper.BaseApiService;
import com.winery.winerymobile.ui.APIhelper.UtilsApi;
import com.winery.winerymobile.ui.CreditCardSubmission.DialogSuccess;
import com.winery.winerymobile.ui.ParentHomeActivity;
import com.winery.winerymobile.ui.dbhelper.SessionManagement;
import com.winery.winerymobile.ui.dbhelper.StateTransactionSales;
import com.winery.winerymobile.ui.helper.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.StringTokenizer;

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

import static java.security.AccessController.getContext;

public class ChangeDocumentRecontestActivity extends AppCompatActivity {


    @BindView(R.id.appBar)
    com.google.android.material.appbar.AppBarLayout appBar;
    @BindView(R.id.toolbar_main)
    com.google.android.material.appbar.MaterialToolbar toolbarMain;
    @BindView(R.id.toolbar_title)
    ImageView toolbarTitle;
    @BindView(R.id.container_content_review)
    RelativeLayout containerContentReview;
    @BindView(R.id.sc_filter_recontest)
    ScrollView scFilterRecontest;
    @BindView(R.id.container_iv_ktp)
    RelativeLayout containerIvKtp;
    @BindView(R.id.iv_ktp)
    ImageView ivKtp;
    @BindView(R.id.btn_change_ktp)
    com.google.android.material.button.MaterialButton btnChangeKtp;
    @BindView(R.id.container_iv_npwp)
    RelativeLayout containerIvNpwp;
    @BindView(R.id.iv_npwp)
    ImageView ivNpwp;
    @BindView(R.id.btn_change_npwp)
    com.google.android.material.button.MaterialButton btnChangeNpwp;
    @BindView(R.id.container_iv_idcard)
    RelativeLayout containerIvIdcard;
    @BindView(R.id.iv_idcard)
    ImageView ivIdcard;
    @BindView(R.id.btn_change_idcard)
    com.google.android.material.button.MaterialButton btnChangeIdcard;
    @BindView(R.id.container_iv_cc)
    RelativeLayout containerIvCc;
    @BindView(R.id.iv_cc)
    ImageView ivCc;
    @BindView(R.id.btn_change_cc)
    com.google.android.material.button.MaterialButton btnChangeCc;
    @BindView(R.id.container_iv_supportdoc1)
    RelativeLayout containerIvSupportdoc1;
    @BindView(R.id.iv_supportdoc1)
    ImageView ivSupportdoc1;
    @BindView(R.id.btn_change_supportdoc1)
    com.google.android.material.button.MaterialButton btnChangeSupportdoc1;
    @BindView(R.id.container_iv_supportdoc2)
    RelativeLayout containerIvSupportdoc2;
    @BindView(R.id.iv_supportdoc2)
    ImageView ivSupportdoc2;
    @BindView(R.id.btn_change_supportdoc2)
    com.google.android.material.button.MaterialButton btnChangeSupportdoc2;
    @BindView(R.id.container_iv_bri)
    RelativeLayout containerIvBri;
    @BindView(R.id.iv_bri)
    ImageView ivBri;
    @BindView(R.id.btn_change_bri)
    com.google.android.material.button.MaterialButton btnChangeBri;
    @BindView(R.id.container_iv_bni)
    RelativeLayout containerIvBni;
    @BindView(R.id.iv_bni)
    ImageView ivBni;
    @BindView(R.id.btn_change_bni)
    com.google.android.material.button.MaterialButton btnChangeBni;
    @BindView(R.id.container_iv_cimb)
    RelativeLayout containerIvCimb;
    @BindView(R.id.iv_cimb)
    ImageView ivCimb;
    @BindView(R.id.btn_change_cimb)
    com.google.android.material.button.MaterialButton btnChangeCimb;
    @BindView(R.id.tv_container_mayapada)
    TextView tvContainerMayapada;
    @BindView(R.id.container_iv_mayapada)
    RelativeLayout containerIvMayapada;
    @BindView(R.id.iv_selfie_mayapada)
    ImageView ivSelfieMayapada;
    @BindView(R.id.btn_change_mayapada)
    com.google.android.material.button.MaterialButton btnChangeMayapada;
    @BindView(R.id.checkbox_validation_data)
    CheckBox checkboxValidationData;
    @BindView(R.id.rl_footer)
    RelativeLayout rlFooter;
    @BindView(R.id.btn_recontest)
    com.google.android.material.button.MaterialButton btnRecontest;

    @BindView(R.id.ti_name)
    com.google.android.material.textfield.TextInputLayout tiName;
    @BindView(R.id.et_name)
    com.google.android.material.textfield.TextInputEditText etName;
    @BindView(R.id.ti_phone)
    com.google.android.material.textfield.TextInputLayout tiPhone;
    @BindView(R.id.et_phone)
    com.google.android.material.textfield.TextInputEditText etPhone;
    @BindView(R.id.ti_handphone)
    com.google.android.material.textfield.TextInputLayout tiHandphone;
    @BindView(R.id.et_handphone)
    com.google.android.material.textfield.TextInputEditText etHandphone;
    @BindView(R.id.ti_mother_name)
    com.google.android.material.textfield.TextInputLayout tiMotherName;
    @BindView(R.id.et_mother_name)
    com.google.android.material.textfield.TextInputEditText etMotherName;
    @BindView(R.id.ti_company_name)
    com.google.android.material.textfield.TextInputLayout tiCompanyName;
    @BindView(R.id.et_company_name)
    com.google.android.material.textfield.TextInputEditText etCompanyName;
    @BindView(R.id.ti_company_phone)
    com.google.android.material.textfield.TextInputLayout tiCompanyPhone;
    @BindView(R.id.et_company_phone)
    com.google.android.material.textfield.TextInputEditText etCompanyPhone;
    @BindView(R.id.ti_company_phone_ext)
    com.google.android.material.textfield.TextInputLayout tiCompanyPhoneExt;
    @BindView(R.id.et_company_phone_ext)
    com.google.android.material.textfield.TextInputEditText etCompanyPhoneExt;
    @BindView(R.id.ti_emergency_name)
    com.google.android.material.textfield.TextInputLayout tiEmergencyName;
    @BindView(R.id.et_emergency_name)
    com.google.android.material.textfield.TextInputEditText etEmergencyName;
    @BindView(R.id.ti_emergency_telp)
    com.google.android.material.textfield.TextInputLayout tiEmergencyTelp;
    @BindView(R.id.et_emergency_telp)
    com.google.android.material.textfield.TextInputEditText etEmergencyTelp;



    @BindView(R.id.container_content_message)
    RelativeLayout containerContentMessage;
    @BindView(R.id.rl_footers)
    RelativeLayout rlFooters;
    @BindView(R.id.btn_ok)
    com.google.android.material.button.MaterialButton btnOk;

    @OnClick(R.id.btn_ok) void gotoHome(){
        Intent intent = new Intent(this, ParentHomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        deleteCache(this);
        startActivity(intent);
    }

    @OnClick(R.id.checkbox_validation_data) void checkData(){
        if(checkboxValidationData.isChecked()){
            btnRecontest.setEnabled(true);
        }else{
            btnRecontest.setEnabled(false);
        }
    }

    String VerifikasiBri, VerifikasiBni, VerifikasiMayapada, VerifikasiNiaga;

    @OnClick(R.id.btn_recontest) void gotoReview(){
//        intentImageBitmap();
        Log.d("statusRjVerif", "gotoReview: "+statusRjVerif);
        Log.d("VerifikasiBni", "gotoReview: "+VerifikasiBni);
        Log.d("selectedImageKtp", "gotoReview: "+selectedImageKtp.toString());


        if(selectedImageKtp == null){
            bankValidation("Foto KTP tidak Boleh Kosong");
        }
//        else if(selectedImageNpwp == null) {
//            bankValidation("Foto NPWP tidak Boleh Kosong");
//        }
//        else if(selectedImageIdCard == null) {
//            bankValidation("Foto ID Card tidak Boleh Kosong");
//        }
//        else if(selectedImageCC == null) {
//            bankValidation("Foto Kredit Card tidak Boleh Kosong");
//        }
//        else if(selectedImageBri == null && VerifikasiBri.equals(statusRjVerif)) {
//            bankValidation("Foto selfie BRI tidak Boleh Kosong");
//
//        }
//        else if(selectedBni == null && VerifikasiBni.equals(statusRjVerif)) {
//            bankValidation("Foto selfie BNI tidak Boleh Kosong");
//
//        }
//        else if(selectedCimb == null && VerifikasiNiaga.equals(statusRjVerif)) {
//            bankValidation("Foto selfie CIMB Niaga tidak Boleh Kosong");
//
//        }
//        else if(selectedMayapada == null && VerifikasiMayapada.equals(statusRjVerif)) {
//            bankValidation("Foto selfie Mayapada tidak Boleh Kosong");
//
//        }
        else {
//            containerIvKtp.setEnabled(false);
//            containerIvNpwp.setEnabled(false);
//            containerIvIdcard.setEnabled(false);
//            containerIvCc.setEnabled(false);
//            containerIvSupportdoc1.setEnabled(false);
//            containerIvSupportdoc2.setEnabled(false);
//            containerIvBri.setEnabled(false);
//            containerIvBni.setEnabled(false);
//            containerIvCimb.setEnabled(false);
//            containerIvMayapada.setEnabled(false);
            UpdateDataForRecontest(selectedImageKtp, selectedImageNpwp, selectedImageIdCard, selectedImageCC,
                    selectedImageDoc1, selectedImageDoc2, selectedImageBri, selectedBni, selectedCimb, selectedMayapada);
//        }

        }
    }

    ProgressDialog loading;
    BaseApiService mApiService;
    String urlImageKtp, urlImageNpwp, urlImageIdCard, urlImageKartuKredit, urlDataPendukung,
            urlImageBri, urlImageBni, urlImageCimb, urlImageDataKotor, urlImageSlip, urlImageSpt,
            urlImageMayapada;

    private static final int CHANGE_IMAGE_KTP = 0;
    private static final int CHANGE_IMAGE_NPWP = 1;
    private static final int CHANGE_IMAGE_IDCARD = 2;
    private static final int CHANGE_IMAGE_CC = 3;
    private static final int CHANGE_IMAGE_SUPPORT1 = 4;
    private static final int CHANGE_IMAGE_SUPPORT2 = 5;
    private static final int CHANGE_IMAGE_BRI = 6;
    private static final int CHANGE_IMAGE_BNI = 7;
    private static final int CHANGE_IMAGE_CIMB = 8;
    private static final int CHANGE_IMAGE_MAYAPADA = 9;

    StateTransactionSales stateTransactionSales;
    SessionManagement sessionManagement;

    String nama, telepon, handphone, ibu, perusahaan, teleponkantor,
            econtact, teleponecontact, hubunganecontact;

    String idTransaction, statusActivity, statusRjVerif;

    Bitmap selectedImageIdCard = null , selectedImageKtp = null, selectedImageNpwp = null,
            selectedImageCC = null, selectedImageDoc1 = null, selectedImageDoc2 = null;
    Bitmap selectedImageBri = null, selectedBni = null, selectedCimb = null, selectedMayapada = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_document_recontest);
        ButterKnife.bind(this);

        initToolbar();
        mApiService = UtilsApi.getAPIService();

        getListDataandImage();

        sessionManagement = new SessionManagement(this);
        stateTransactionSales = new StateTransactionSales(this);

        Intent intent = getIntent();
        idTransaction = intent.getStringExtra("param");
        statusActivity = intent.getStringExtra("status");
        statusRjVerif = intent.getStringExtra("statusrjverif");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

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

    private void getListDataandImage(){
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
                                    
                                    // image source
                                    urlImageKtp = jsonRESULTS.getJSONObject("data").getString("url_ktp");
                                    loadImage(urlImageKtp, ivKtp);
                                    if(!urlImageKtp.equals("") || urlImageKtp != null || !urlImageKtp.equals("null")){
                                        selectedImageKtp = getBitmapFromUrl(urlImageKtp);
                                    }

                                    urlImageNpwp = jsonRESULTS.getJSONObject("data").getString("url_npwp");
                                    loadImage(urlImageNpwp, ivNpwp);
                                    if(!urlImageNpwp.equals("") || urlImageNpwp != null || !urlImageNpwp.equals("null")) {
                                        selectedImageNpwp = getBitmapFromUrl(urlImageNpwp);
                                    }

                                    urlImageIdCard = jsonRESULTS.getJSONObject("data").getString("url_fotoid");
                                    loadImage(urlImageIdCard, ivIdcard);
                                    if(!urlImageIdCard.equals("") || urlImageIdCard != null || !urlImageIdCard.equals("null")) {
                                        selectedImageIdCard = getBitmapFromUrl(urlImageIdCard);
                                    }

                                    urlImageKartuKredit = jsonRESULTS.getJSONObject("data").getString("url_kartu_kredit");
                                    loadImage(urlImageKartuKredit, ivCc);
                                    if(!urlImageKartuKredit.equals("") || urlImageKartuKredit != null || !urlImageKartuKredit.equals("null")) {
                                        selectedImageCC = getBitmapFromUrl(urlImageKartuKredit);
                                    }

                                    urlDataPendukung = jsonRESULTS.getJSONObject("data").getString("url_date_pendukung");
                                    loadImage(urlImageSlip, ivSupportdoc1);
                                    if(!urlDataPendukung.equals("") || urlDataPendukung != null || !urlDataPendukung.equals("null")) {
                                        selectedImageDoc1 = getBitmapFromUrl(urlDataPendukung);
                                    }


                                    urlImageBri = jsonRESULTS.getJSONObject("data").getString("url_bri");
                                    loadImage(urlImageBri, ivBri);
                                    if(!urlImageBri.equals("") || urlImageBri != null || !urlImageBri.equals("null")) {
                                        selectedImageBri = getBitmapFromUrl(urlImageBri);
                                    }

                                    urlImageBni = jsonRESULTS.getJSONObject("data").getString("url_bni");
                                    loadImage(urlImageBni, ivBni);
                                    if(!urlImageBni.equals("") || urlImageBni != null || !urlImageBni.equals("null")) {
                                        selectedBni = getBitmapFromUrl(urlImageBni);
                                    }

                                    urlImageCimb = jsonRESULTS.getJSONObject("data").getString("url_cimb");
                                    loadImage(urlImageCimb, ivCimb);
                                    if(!urlImageCimb.equals("") || urlImageCimb != null || !urlImageCimb.equals("null")) {
                                        selectedCimb = getBitmapFromUrl(urlImageCimb);
                                    }

                                    urlImageDataKotor = jsonRESULTS.getJSONObject("data").getString("url_data_kotor");
                                    urlImageSpt = jsonRESULTS.getJSONObject("data").getString("url_spt");

                                    urlImageSlip = jsonRESULTS.getJSONObject("data").getString("url_slip_gaji");
                                    loadImage(urlImageSlip, ivSupportdoc2);
                                    if(!urlImageSlip.equals("") || urlImageSlip != null || !urlImageSlip.equals("null")) {
                                        selectedImageDoc2 = getBitmapFromUrl(urlImageSlip);
                                    }

                                    urlImageMayapada = jsonRESULTS.getJSONObject("data").getString("url_mayapada");
                                    loadImage(urlImageMayapada, ivSelfieMayapada);
                                    if(!urlImageMayapada.equals("") || urlImageMayapada != null || !urlImageMayapada.equals("null")) {
                                        selectedMayapada = getBitmapFromUrl(urlImageMayapada);
                                    }


                                    String id = jsonRESULTS.getJSONObject("data").getString("id");
                                    String tanggal = jsonRESULTS.getJSONObject("data").getString("tanggalinput");
                                    String tanggalVerif = jsonRESULTS.getJSONObject("data").getString("tanggalverif");
                                    String nik = jsonRESULTS.getJSONObject("data").getString("nik");
                                    nama = jsonRESULTS.getJSONObject("data").getString("nama");
                                    String tempatLahir = jsonRESULTS.getJSONObject("data").getString("lahir");
                                    String dob = jsonRESULTS.getJSONObject("data").getString("dob");
                                    telepon = jsonRESULTS.getJSONObject("data").getString("telepon");
                                    handphone = jsonRESULTS.getJSONObject("data").getString("handphone");

                                    ibu = jsonRESULTS.getJSONObject("data").getString("ibu");
                                    perusahaan = jsonRESULTS.getJSONObject("data").getString("perusahaan");
                                    String alamatkantor = jsonRESULTS.getJSONObject("data").getString("alamatkantor");
                                    teleponkantor = jsonRESULTS.getJSONObject("data").getString("teleponkantor");
                                    String jabatankantor = jsonRESULTS.getJSONObject("data").getString("jabatan");
                                    String bagiankantor = jsonRESULTS.getJSONObject("data").getString("bagian");


                                    econtact = jsonRESULTS.getJSONObject("data").getString("econtact");
                                    hubunganecontact = jsonRESULTS.getJSONObject("data").getString("hubunganecontact");
                                    teleponecontact = jsonRESULTS.getJSONObject("data").getString("teleponecontact");

                                    String hadiah = jsonRESULTS.getJSONObject("data").getString("hadiah");

                                    Log.d("status", "onCreate: "+statusActivity);

                                    VerifikasiBri = jsonRESULTS.getJSONObject("data").getString("verifbri");
                                    VerifikasiBni = jsonRESULTS.getJSONObject("data").getString("verifbni");
                                    VerifikasiMayapada = jsonRESULTS.getJSONObject("data").getString("verifmayapada");
                                    VerifikasiNiaga = jsonRESULTS.getJSONObject("data").getString("verifniaga");

                                    if(statusActivity.equals("indirect")){
                                        HashMap<String, String> user = stateTransactionSales.getUpdateForm();
                                        etName.setText(user.get(StateTransactionSales.KEY_NAMA));
                                        Log.d("etName", "onCreate: "+user.get(StateTransactionSales.KEY_NAMA));
                                        etPhone.setText(user.get(StateTransactionSales.KEY_HANDPHONE1));
                                        etHandphone.setText(user.get(StateTransactionSales.KEY_HANDPHONE2));
                                        etMotherName.setText(user.get(StateTransactionSales.KEY_NAMA_IBU_KANDUNG));
                                        etCompanyName.setText(user.get(StateTransactionSales.KEY_NAMA_PERUSAHAAN));
                                        String noTelepon = "";
                                        String ext = "";
                                        StringTokenizer tokensPhone = new StringTokenizer(user.get(StateTransactionSales.KEY_TELEPHONE_KANTOR), "-");

                                        String[] splitTeleponKantor = user.get(StateTransactionSales.KEY_TELEPHONE_KANTOR).split("-");
                                        if(splitTeleponKantor.length > 1){
                                            noTelepon = tokensPhone.nextToken();
                                            ext = tokensPhone.nextToken();
                                            etCompanyPhoneExt.setText(ext);
                                        }else {
                                            noTelepon = tokensPhone.nextToken();

                                        }
                                        etCompanyPhone.setText(noTelepon);
                                        etEmergencyName.setText(user.get(StateTransactionSales.KEY_NAMA_EMERGENCY_KONTAK));
                                        etEmergencyTelp.setText(user.get(StateTransactionSales.KEY_TELP_EMERGENCY_KONTAK));

                                    }else{
                                        etName.setText(nama);
                                        etPhone.setText(telepon);
                                        etHandphone.setText(handphone);
                                        etMotherName.setText(ibu);
                                        etCompanyName.setText(perusahaan);


                                        String noTelepon = "";
                                        String ext = "";
                                        String teleponkantors;
                                        StringTokenizer tokensPhone = new StringTokenizer(teleponkantor, "-");

                                        String[] splitTeleponKantor = teleponkantor.split("-");
                                        if(splitTeleponKantor.length > 1){
                                            noTelepon = tokensPhone.nextToken();
                                            ext = tokensPhone.nextToken();
                                            etCompanyPhoneExt.setText(ext);
                                            teleponkantors = noTelepon+"-"+ext;
                                        }else {
                                            noTelepon = tokensPhone.nextToken();
                                            teleponkantors = noTelepon;

                                        }
                                        etCompanyPhone.setText(noTelepon);
                                        etEmergencyName.setText(econtact);
                                        etEmergencyTelp.setText(teleponecontact);

                                        stateTransactionSales.updateStateInpuForm(idTransaction,nama, nik,
                                                tempatLahir, dob, telepon, handphone, ibu,
                                                perusahaan, alamatkantor,
                                                teleponkantors
                                                , bagiankantor
                                                , jabatankantor,econtact, hubunganecontact, hadiah, teleponecontact);
                                    }

                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(ChangeDocumentRecontestActivity.this, error_message, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(ChangeDocumentRecontestActivity.this, error_message, Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                });
    }

    public void loadImage(String Url, ImageView imageView){
        Glide.with(this).
                asBitmap().
                load(Url)
                .placeholder(R.color.grey_20)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .fitCenter()
//                .transition(DrawableTransitionOptions.withCrossFade(100))
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        imageView.setImageBitmap(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
    }

    @Override
    protected void onResume() {

        super.onResume();

        btnChangeKtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, CHANGE_IMAGE_KTP);
                //        selectImage(REQUEST_CAMERA_KTP, SELECT_FILE_KTP);
            }
        });

        btnChangeNpwp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, CHANGE_IMAGE_NPWP);
//        selectImage(REQUEST_CAMERA_NPWP, SELECT_FILE_NPWP);
            }
        });

        btnChangeIdcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, CHANGE_IMAGE_IDCARD);

//        selectImage(REQUEST_CAMERA_ID_CARD, SELECT_FILE_ID_CARD);
            }
        });

        btnChangeCc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, CHANGE_IMAGE_CC);

//        selectImage(REQUEST_CAMERA_ID_CARD, SELECT_FILE_ID_CARD);
            }
        });

        btnChangeSupportdoc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, CHANGE_IMAGE_SUPPORT1);

//        selectImage(REQUEST_CAMERA_ID_CARD, SELECT_FILE_ID_CARD);
            }
        });

        btnChangeSupportdoc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, CHANGE_IMAGE_SUPPORT2);

//        selectImage(REQUEST_CAMERA_ID_CARD, SELECT_FILE_ID_CARD);
            }
        });

        btnChangeBri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, CHANGE_IMAGE_BRI);

//        selectImage(REQUEST_CAMERA_ID_CARD, SELECT_FILE_ID_CARD);
            }
        });

        btnChangeBni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, CHANGE_IMAGE_BNI);

//        selectImage(REQUEST_CAMERA_ID_CARD, SELECT_FILE_ID_CARD);
            }
        });

        btnChangeCimb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, CHANGE_IMAGE_CIMB);

//        selectImage(REQUEST_CAMERA_ID_CARD, SELECT_FILE_ID_CARD);
            }
        });

        btnChangeMayapada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, CHANGE_IMAGE_MAYAPADA);

//        selectImage(REQUEST_CAMERA_ID_CARD, SELECT_FILE_ID_CARD);
            }
        });

    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (reqCode == CHANGE_IMAGE_KTP) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
//                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    selectedImageKtp = BitmapFactory.decodeStream(imageStream);
//                    ivKtp.setImageBitmap(selectedImageKtp);

                    if(imageUri != null){
                        Uri selectedImage = imageUri;
                        getContentResolver().notifyChange(selectedImage, null);
                        selectedImageKtp = getBitmap(imageUri);

                        if(selectedImageKtp != null){
                            Glide.with(ChangeDocumentRecontestActivity.this).
                                    load(selectedImageKtp)
                                    .placeholder(R.drawable.ic_camera)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .transition(DrawableTransitionOptions.withCrossFade(100))
                                    .into(ivKtp);
                        }else{
                            Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
            }
        } else if (reqCode == CHANGE_IMAGE_NPWP) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
//                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    selectedImageNpwp = BitmapFactory.decodeStream(imageStream);
//                    ivNpwp.setImageBitmap(selectedImageNpwp);
                    if(imageUri != null){
                        Uri selectedImage = imageUri;
                        getContentResolver().notifyChange(selectedImage, null);
                        selectedImageNpwp = getBitmap(imageUri);
                        if(selectedImageNpwp != null){
                            Glide.with(ChangeDocumentRecontestActivity.this).
                                    load(selectedImageNpwp)
                                    .placeholder(R.drawable.ic_camera)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .transition(DrawableTransitionOptions.withCrossFade(100))
                                    .into(ivNpwp);
                        }else{
                            Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
            }
        } else if (reqCode == CHANGE_IMAGE_IDCARD) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
//                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    selectedImageIdCard = BitmapFactory.decodeStream(imageStream);
//                    ivIdcard.setImageBitmap(selectedImageIdCard);
                    if(imageUri != null){
                        Uri selectedImage = imageUri;
                        getContentResolver().notifyChange(selectedImage, null);
                        selectedImageIdCard = getBitmap(imageUri);
                        if(selectedImageIdCard != null){
                            Glide.with(ChangeDocumentRecontestActivity.this).
                                    load(selectedImageIdCard)
                                    .placeholder(R.drawable.ic_camera)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .transition(DrawableTransitionOptions.withCrossFade(100))
                                    .into(ivIdcard);
                        }else{
                            Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
            }
        } else if (reqCode == CHANGE_IMAGE_CC) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
//                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    selectedImageCC = BitmapFactory.decodeStream(imageStream);
//                    ivCc.setImageBitmap(selectedImageCC);
                    if(imageUri != null){
                        Uri selectedImage = imageUri;
                        getContentResolver().notifyChange(selectedImage, null);
                        selectedImageCC = getBitmap(imageUri);
                        if(selectedImageCC != null){
                            Glide.with(ChangeDocumentRecontestActivity.this).
                                    load(selectedImageCC)
                                    .placeholder(R.drawable.ic_camera)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .transition(DrawableTransitionOptions.withCrossFade(100))
                                    .into(ivCc);
                        }else{
                            Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();

                }

            } else {
                Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
            }
        } else if (reqCode == CHANGE_IMAGE_SUPPORT1) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
//                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    selectedImageDoc1 = BitmapFactory.decodeStream(imageStream);
//                    ivSupportdoc1.setImageBitmap(selectedImageDoc1);
                    if(imageUri != null){
                        Uri selectedImage = imageUri;
                        getContentResolver().notifyChange(selectedImage, null);
                        selectedImageDoc1 = getBitmap(imageUri);
                        if(selectedImageDoc1 != null){
                            Glide.with(ChangeDocumentRecontestActivity.this).
                                    load(selectedImageDoc1)
                                    .placeholder(R.drawable.ic_camera)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .transition(DrawableTransitionOptions.withCrossFade(100))
                                    .into(ivSupportdoc1);
                        }else{
                            Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();

                }

            } else {
                Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
            }
        } else if (reqCode == CHANGE_IMAGE_SUPPORT2) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
//                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    selectedImageDoc2 = BitmapFactory.decodeStream(imageStream);
//                    ivSupportdoc2.setImageBitmap(selectedImageDoc2);
                    if(imageUri != null){
                        Uri selectedImage = imageUri;
                        getContentResolver().notifyChange(selectedImage, null);
                        selectedImageDoc2 = getBitmap(imageUri);
                        if(selectedImageDoc2 != null){
                            Glide.with(ChangeDocumentRecontestActivity.this).
                                    load(selectedImageDoc2)
                                    .placeholder(R.drawable.ic_camera)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .transition(DrawableTransitionOptions.withCrossFade(100))
                                    .into(ivSupportdoc2);
                        }else{
                            Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
            }
        } else if (reqCode == CHANGE_IMAGE_BRI) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
//                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    selectedImageBri = BitmapFactory.decodeStream(imageStream);
//                    ivBriSelfie.setImageBitmap(selectedImageBri);
                    if(imageUri != null){
                        Uri selectedImage = imageUri;
                        getContentResolver().notifyChange(selectedImage, null);
                        selectedImageBri = getBitmap(imageUri);
                        if(selectedImageBri != null){
                            Glide.with(ChangeDocumentRecontestActivity.this).
                                    load(selectedImageBri)
                                    .placeholder(R.drawable.ic_camera)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .transition(DrawableTransitionOptions.withCrossFade(100))
                                    .into(ivBri);
                        }else{
                            Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
            }
        }
        if (reqCode == CHANGE_IMAGE_BNI) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
//                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    selectedBni = BitmapFactory.decodeStream(imageStream);
//                    ivBniSelfie.setImageBitmap(selectedBni);
                    if(imageUri != null){
                        Uri selectedImage = imageUri;
                        getContentResolver().notifyChange(selectedImage, null);
                        selectedBni = getBitmap(imageUri);
                        if(selectedBni != null){
                            Glide.with(ChangeDocumentRecontestActivity.this).
                                    load(selectedBni)
                                    .placeholder(R.drawable.ic_camera)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .transition(DrawableTransitionOptions.withCrossFade(100))
                                    .into(ivBni);
                        }else{
                            Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
            }
        }
        if (reqCode == CHANGE_IMAGE_CIMB) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
//                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    selectedCimb = BitmapFactory.decodeStream(imageStream);
//                    ivSelfieCimb.setImageBitmap(selectedCimb);

                    if(imageUri != null){
                        Uri selectedImage = imageUri;
                        getContentResolver().notifyChange(selectedImage, null);
                        selectedCimb = getBitmap(imageUri);
                        if(selectedCimb != null){
                            Glide.with(ChangeDocumentRecontestActivity.this).
                                    load(selectedCimb)
                                    .placeholder(R.drawable.ic_camera)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .transition(DrawableTransitionOptions.withCrossFade(100))
                                    .into(ivCimb);
                        }else{
                            Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
            }
        }
        if (reqCode == CHANGE_IMAGE_MAYAPADA) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
//                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    selectedMayapada = BitmapFactory.decodeStream(imageStream);
//                    ivSelfieMayapada.setImageBitmap(selectedMayapada);
                    if(imageUri != null){
                        Uri selectedImage = imageUri;
                        getContentResolver().notifyChange(selectedImage, null);
                        selectedMayapada = getBitmap(imageUri);
                        if(selectedMayapada != null){
                            Glide.with(ChangeDocumentRecontestActivity.this).
                                    load(selectedMayapada)
                                    .placeholder(R.drawable.ic_camera)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .transition(DrawableTransitionOptions.withCrossFade(100))
                                    .into(ivSelfieMayapada);
                        }else{
                            Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
            }
        }
    }

    public Bitmap getBitmap(Uri path) {

        Uri uri = path;
        InputStream in = null;
        try {
            final int IMAGE_MAX_SIZE = 700000; // 1.2MP
            in = getContentResolver().openInputStream(uri);

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(in, null, o);
            in.close();


            int scale = 1;
            while ((o.outWidth * o.outHeight) * (1 / Math.pow(scale, 2)) >
                    IMAGE_MAX_SIZE) {
                scale++;
            }
            Log.d("", "scale = " + scale + ", orig-width: " + o.outWidth + ", orig-height: " + o.outHeight);

            Bitmap b = null;
            in = getContentResolver().openInputStream(uri);
            if (scale > 1) {
                scale--;
                // scale to max possible inSampleSize that still yields an image
                // larger than target
                o = new BitmapFactory.Options();
                o.inSampleSize = scale;
                b = BitmapFactory.decodeStream(in, null, o);

                // resize to desired dimensions
                int height = b.getHeight();
                int width = b.getWidth();
                Log.d("", "1th scale operation dimenions - width: " + width + ", height: " + height);

                double y = Math.sqrt(IMAGE_MAX_SIZE
                        / (((double) width) / height));
                double x = (y / height) * width;

                Bitmap scaledBitmap = Bitmap.createScaledBitmap(b, (int) x,
                        (int) y, true);
                b.recycle();
                b = scaledBitmap;

                System.gc();
            } else {
                b = BitmapFactory.decodeStream(in);
            }
            in.close();

            Log.d("", "bitmap size - width: " + b.getWidth() + ", height: " +
                    b.getHeight());
            return b;
        } catch (IOException e) {
            Log.e("", e.getMessage(), e);
            return null;
        }
    }

    public void intentImageBitmap(){

        Intent intent = new Intent(this, ReviewDataCustomerRecontestActivity.class);
        intent.putExtra("imageKtp", selectedImageKtp);
        intent.putExtra("imageNpwp", selectedImageNpwp);
        intent.putExtra("imageCC", selectedImageCC);
        intent.putExtra("imageDoc1", selectedImageDoc1);
        intent.putExtra("imageDoc2", selectedImageDoc2);
        intent.putExtra("imageIdCard", selectedImageIdCard);
        intent.putExtra("imageBri", selectedImageBri);
        intent.putExtra("imageBni", selectedBni);
        intent.putExtra("imageCimb", selectedCimb);
        intent.putExtra("imageMayapada", selectedMayapada);

        startActivity(intent);

    }

    public void bankValidation(String message){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.appBar), message, Snackbar.LENGTH_SHORT)
                .setAction("Action", null);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        snackbar.show();
    }

    public Bitmap getBitmapFromUrl(String urls){
        Bitmap image = null;
        try {
            URL url = new URL(urls);
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch(IOException e) {
            System.out.println(e);
        }

        return image;
    }

    public void UpdateDataForRecontest(Bitmap gambarbitmapKtp, Bitmap gambarbitmapNpwp, Bitmap gambarbitmapKtpIdcard,
                                     Bitmap gambarbitmapCC, Bitmap gambarbitmapDoc1, Bitmap gambarbitmapDoc2,
                                     Bitmap gambarbitmapBri, Bitmap gambarbitmapBni, Bitmap gambarbitmapCimb,
                                     Bitmap gambarbitmapMayapada) {



//        HashMap<String, String> bank = stateTransactionSales.getListBank();
        HashMap<String, String> user = stateTransactionSales.getUpdateForm();
//        HashMap<String, String> session = sessionManagement.getUserDetails();

//        Log.d("getName", "name "+session.get(sessionManagement.KEY_NAME));


        HashMap<String, RequestBody> map = new HashMap<>();
//        map.put("bri", createPartFromString(bank.get(StateTransactionSales.KEY_STATUS_BRI)));
//        map.put("bni", createPartFromString(bank.get(StateTransactionSales.KEY_STATUS_BNI)));
//        map.put("bca", createPartFromString(bank.get(StateTransactionSales.KEY_STATUS_BCA)));
//        map.put("cimb_niaga", createPartFromString(bank.get(StateTransactionSales.KEY_STATUS_CIMB)));
//        map.put("mayapada", createPartFromString(bank.get(StateTransactionSales.KEY_STATUS_MAYAPADA)));
//        map.put("dbs", createPartFromString(bank.get(StateTransactionSales.KEY_STATUS_DBS)));
//        map.put("mnc", createPartFromString(bank.get(StateTransactionSales.KEY_STATUS_MNC)));
//        map.put("uob", createPartFromString(bank.get(StateTransactionSales.KEY_STATUS_UOB)));
//        map.put("mega", createPartFromString(bank.get(StateTransactionSales.KEY_STATUS_MEGA)));
//        map.put("panin", createPartFromString(bank.get(StateTransactionSales.KEY_STATUS_PANIN)));
        map.put("id", createPartFromString(idTransaction));
        map.put("verif", createPartFromString(statusRjVerif));
        map.put("nama", createPartFromString(user.get(StateTransactionSales.KEY_NAMA)));
        Log.d("nama", "UpdateDataForRecontest: "+user.get(StateTransactionSales.KEY_NAMA));
        map.put("nik", createPartFromString(user.get(StateTransactionSales.KEY_NIK)));
        map.put("tempat_lahir", createPartFromString(user.get(StateTransactionSales.KEY_TEMPAT_LAHIR)));
        map.put("tanggal_lahir", createPartFromString(user.get(StateTransactionSales.KEY_TANGGAL_LAHIR)));
        map.put("handphone_1", createPartFromString(user.get(StateTransactionSales.KEY_HANDPHONE1)));
        map.put("handphone_2", createPartFromString(user.get(StateTransactionSales.KEY_HANDPHONE2)));
        map.put("nama_ibu_kandung", createPartFromString(user.get(StateTransactionSales.KEY_NAMA_IBU_KANDUNG)));
        map.put("nama_perusahaan", createPartFromString(user.get(StateTransactionSales.KEY_NAMA_PERUSAHAAN)));
        map.put("alamat_perusahaan", createPartFromString(user.get(StateTransactionSales.KEY_ALAMAT_PERUSAHAAN)));
        map.put("telephone_kantor", createPartFromString(user.get(StateTransactionSales.KEY_TELEPHONE_KANTOR)));
        map.put("bagian_kantor", createPartFromString(user.get(StateTransactionSales.KEY_BAGIAN_KANTOR)));
        map.put("jabatan_kantor", createPartFromString(user.get(StateTransactionSales.KEY_JABATAN_KANTOR)));
        map.put("nama_emergency_contact", createPartFromString(user.get(StateTransactionSales.KEY_NAMA_EMERGENCY_KONTAK)));
        map.put("hubungan", createPartFromString(user.get(StateTransactionSales.KEY_HUBUNGAN)));
        map.put("hadiah", createPartFromString(user.get(StateTransactionSales.KEY_HADIAH)));
        map.put("emergency_contact", createPartFromString(user.get(StateTransactionSales.KEY_TELP_EMERGENCY_KONTAK)));

        //convert gambar jadi File terlebih dahulu dengan memanggil createTempFile.
        // foto ktp
        File filektp = Utils.createTempFile(gambarbitmapKtp, getApplicationContext());
        RequestBody reqFilektp = RequestBody.create(MediaType.parse("image/*"), filektp);

        // Npwp
        MultipartBody.Part fileNpwp = null;
        if(gambarbitmapNpwp != null) {
            File filenpwp = Utils.createTempFile(gambarbitmapNpwp, getApplicationContext());
            RequestBody reqFilenpwp = RequestBody.create(MediaType.parse("image/*"), filenpwp);
            fileNpwp = MultipartBody.Part.createFormData("file_npwp", filenpwp.getName(), reqFilenpwp);
        }

        // IDcard
        MultipartBody.Part fileFotoId = null;
        if(gambarbitmapKtpIdcard != null) {
            File fileidcard = Utils.createTempFile(gambarbitmapKtpIdcard, getApplicationContext());
            RequestBody reqFileidCard = RequestBody.create(MediaType.parse("image/*"), fileidcard);
            fileFotoId = MultipartBody.Part.createFormData("file_foto_id", fileidcard.getName(), reqFileidCard);
        }

        // CC
        MultipartBody.Part fileKartuKredit = null;
        if(gambarbitmapCC != null) {
            File fileCC = Utils.createTempFile(gambarbitmapCC, getApplicationContext());
            RequestBody reqFileCC = RequestBody.create(MediaType.parse("image/*"), fileCC);
            fileKartuKredit = MultipartBody.Part.createFormData("file_kartu_kredit", fileCC.getName(), reqFileCC);
        }

        // Doc1
        MultipartBody.Part fileDoc1 = null;
        if(gambarbitmapDoc1 != null) {
            File fileDocs1 = Utils.createTempFile(gambarbitmapDoc1, getApplicationContext());
            RequestBody reqFileDoc1 = RequestBody.create(MediaType.parse("image/*"), fileDocs1);
            fileDoc1 = MultipartBody.Part.createFormData("file_dokumen_pendukung_1", fileDocs1.getName(), reqFileDoc1);
        }

        // Doc2
        MultipartBody.Part fileDoc2 = null;
        if(gambarbitmapDoc2 != null) {
            File fileDocs2 = Utils.createTempFile(gambarbitmapDoc2, getApplicationContext());
            RequestBody reqFileDoc2 = RequestBody.create(MediaType.parse("image/*"), fileDocs2);
            fileDoc2 = MultipartBody.Part.createFormData("file_dokumen_pendukung_2", fileDocs2.getName(), reqFileDoc2);
        }

        // BRI
        MultipartBody.Part filebri = null;
        if(gambarbitmapBri !=null){
            File fileBri = Utils.createTempFile(gambarbitmapBri, getApplicationContext());
            RequestBody reqFilebri = RequestBody.create(MediaType.parse("image/*"), fileBri);
            filebri = MultipartBody.Part.createFormData("file_bri", fileBri.getName(), reqFilebri);

        }


        // BNI
        MultipartBody.Part filebni = null;
        if(gambarbitmapBni != null) {
            File fileBni = Utils.createTempFile(gambarbitmapBni, getApplicationContext());
            RequestBody reqFilebni = RequestBody.create(MediaType.parse("image/*"), fileBni);
            filebni= MultipartBody.Part.createFormData("file_bni", fileBni.getName(), reqFilebni);

        }

        // CIMB
        MultipartBody.Part filecimb = null;
        if(gambarbitmapCimb != null) {
            File fileCimb = Utils.createTempFile(gambarbitmapCimb, getApplicationContext());
            RequestBody reqFilecimb = RequestBody.create(MediaType.parse("image/*"), fileCimb);
            filecimb = MultipartBody.Part.createFormData("file_cimb_niaga", fileCimb.getName(), reqFilecimb);

        }

        // Mayapada
        MultipartBody.Part filemayapada = null;
        if(gambarbitmapMayapada != null) {
            File fileMayapada = Utils.createTempFile(gambarbitmapMayapada, getApplicationContext());
            RequestBody reqFilemayapada = RequestBody.create(MediaType.parse("image/*"), fileMayapada);
            filemayapada = MultipartBody.Part.createFormData("file_mayapada", fileMayapada.getName(), reqFilemayapada);
        }

        MultipartBody.Part fileKtp = MultipartBody.Part.createFormData("file_ktp", filektp.getName(), reqFilektp);


        // finally, kirim map dan body pada param interface retrofit
        Call<ResponseBody> call = mApiService.UpdateDataForRecontest(fileKtp,fileNpwp,fileFotoId,fileKartuKredit,fileDoc1,
                fileDoc2, filebri, filebni, filecimb, filemayapada,map);
        ProgressDialog loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);
        loading.setCanceledOnTouchOutside(false);
        call.enqueue(
                new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("response", "onResponse: "+response);
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getInt("status") == 200){
                                    String error_message = jsonRESULTS.getString("status");
//                                    Toast.makeText(ChangeDocumentRecontestActivity.this, error_message, Toast.LENGTH_SHORT).show();


//                                    Toast.makeText(ChangeDocumentRecontestActivity.this, "success", Toast.LENGTH_SHORT).show();
//                                    DialogSuccess bottomSheetFragment = new DialogSuccess();
//                                    bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
                                    containerContentReview.setVisibility(View.GONE);
                                    containerContentMessage.setVisibility(View.VISIBLE);


                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(ChangeDocumentRecontestActivity.this, error_message, Toast.LENGTH_SHORT).show();
                                    loading.dismiss();
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
                        String error_message = "server error "+t.toString()+" silahkan coba lagi";
                        Toast.makeText(ChangeDocumentRecontestActivity.this, error_message, Toast.LENGTH_SHORT).show();
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

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {}
    }
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroy() {
        deleteCache(this);
        Log.d("destroy", "onDestroy: bisa");
        super.onDestroy();
    }
}
