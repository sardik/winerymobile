package com.winery.winerymobile.ui.CreditCardSubmission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.snackbar.Snackbar;
import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.APIhelper.BaseApiService;
import com.winery.winerymobile.ui.APIhelper.UtilsApi;
import com.winery.winerymobile.ui.AppMain;
import com.winery.winerymobile.ui.DialogOptionUserLogin;
import com.winery.winerymobile.ui.JurtulTransaction.ListTransactionDetailPendingJurtul;
import com.winery.winerymobile.ui.LoginActivity;
import com.winery.winerymobile.ui.ParentHomeActivity;
import com.winery.winerymobile.ui.dbhelper.SessionManagement;
import com.winery.winerymobile.ui.dbhelper.StateTransactionSales;
import com.winery.winerymobile.ui.helper.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

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

public class FormUploadDocumentSelfie extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.appBar)
    com.google.android.material.appbar.AppBarLayout appBar;
    @BindView(R.id.toolbar_main)
    com.google.android.material.appbar.MaterialToolbar toolbarMain;
    @BindView(R.id.toolbar_title)
    ImageView toolbarTitle;
    @BindView(R.id.nested)
    androidx.core.widget.NestedScrollView nested;
    @BindView(R.id.container_iv_ktp)
    LinearLayout containerIvKtp;
    @BindView(R.id.iv_ktp)
    ImageView ivKtp;
    @BindView(R.id.container_iv_npwp)
    LinearLayout containerIvNpwp;
    @BindView(R.id.iv_npwp)
    ImageView ivNpwp;
    @BindView(R.id.container_iv_idcard)
    LinearLayout containerIvIdcard;
    @BindView(R.id.iv_idcard)
    ImageView ivIdcard;
    @BindView(R.id.container_iv_cc)
    LinearLayout containerIvCc;
    @BindView(R.id.iv_cc)
    ImageView ivCc;
    @BindView(R.id.container_iv_supportdoc1)
    LinearLayout containerIvSupportdoc1;
    @BindView(R.id.iv_supportdoc1)
    ImageView ivSupportdoc1;
    @BindView(R.id.container_iv_supportdoc2)
    LinearLayout containerIvSupportdoc2;
    @BindView(R.id.iv_supportdoc2)
    ImageView ivSupportdoc2;
    @BindView(R.id.container_iv_bri)
    LinearLayout containerIvBri;
    @BindView(R.id.iv_bri_selfie)
    ImageView ivBriSelfie;
    @BindView(R.id.container_iv_bni)
    LinearLayout containerIvBni;
    @BindView(R.id.iv_bni_selfie)
    ImageView ivBniSelfie;
    @BindView(R.id.container_iv_cimb)
    LinearLayout containerIvCimb;
    @BindView(R.id.iv_selfie_cimb)
    ImageView ivSelfieCimb;
    @BindView(R.id.tv_container_mayapada)
    TextView tvContainerMayapada;
    @BindView(R.id.container_iv_mayapada)
    LinearLayout containerIvMayapada;
    @BindView(R.id.iv_selfie_mayapada)
    ImageView ivSelfieMayapada;
    @BindView(R.id.btn_submit)
    com.google.android.material.button.MaterialButton btnSubmit;
    @BindView(R.id.btn_back)
    com.google.android.material.button.MaterialButton btnBack;
    /** ButterKnife Code **/

    @OnClick(R.id.btn_back) void back(){
        onBackPressed();
        finish();
    }

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

    ProgressDialog loading;
    BaseApiService mApiService;

    StateTransactionSales stateTransactionSales;
    SessionManagement sessionManagement;

    Bitmap selectedImageIdCard = null , selectedImageKtp = null, selectedImageNpwp = null,
            selectedImageCC = null, selectedImageDoc1 = null, selectedImageDoc2 = null;
    Bitmap selectedImageBri, selectedBni, selectedCimb, selectedMayapada;


    Intent intent;
    Uri fileUri;
    Button btn_choose_image;
    ImageView imageView;
    Bitmap bitmap, decoded;
    public final int REQUEST_CAMERA_KTP = 0;
    public final int SELECT_FILE_KTP = 1;

    public final int REQUEST_CAMERA_NPWP = 2;
    public final int SELECT_FILE_NPWP = 3;

    public final int REQUEST_CAMERA_ID_CARD = 4;
    public final int SELECT_FILE_ID_CARD = 5;

    public final int REQUEST_CAMERA_KARTU_KREDIT = 6;
    public final int SELECT_FILE_KARTU_KREDIT = 7;

    public final int REQUEST_CAMERA_DOC_PENDUKUNG1 = 8;
    public final int SELECT_FILE_DOC_PENDUKUNG1 = 9;

    public final int REQUEST_CAMERA_BRI = 10;
    public final int SELECT_FILE_BRI = 11;

    public final int REQUEST_CAMERA_BNI = 12;
    public final int SELECT_FILE_BNI = 13;

    public final int REQUEST_CAMERA_CIMB = 14;
    public final int SELECT_FILE_CIMB = 15;

    public final int REQUEST_CAMERA_MAYAPADA = 16;
    public final int SELECT_FILE_DOC_MAYAPADA = 17;

    public final int REQUEST_CAMERA_DOC_PENDUKUNG2 = 18;
    public final int SELECT_FILE_DOC_PENDUKUNG2 = 19;

    int bitmap_size = 70; // image quality 1 - 100;
    int max_resolution_image = 400; //800

    Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_upload_document_selfie);

        ButterKnife.bind(this);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        mApiService = UtilsApi.getAPIService();
        stateTransactionSales = new StateTransactionSales(this);
        sessionManagement = new SessionManagement(this);

        // get user data from session
        HashMap<String, String> user = sessionManagement.getUserDetails();
        String region = user.get(SessionManagement.KEY_REGION);

        if(region.equals("MAKASSAR")){
            containerIvMayapada.setVisibility(View.INVISIBLE);
            tvContainerMayapada.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    protected void onResume() {

        super.onResume();

        containerIvKtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, CHANGE_IMAGE_KTP);
                //        selectImage(REQUEST_CAMERA_KTP, SELECT_FILE_KTP);
            }
        });

        containerIvNpwp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, CHANGE_IMAGE_NPWP);
//        selectImage(REQUEST_CAMERA_NPWP, SELECT_FILE_NPWP);
            }
        });

        containerIvIdcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, CHANGE_IMAGE_IDCARD);

//        selectImage(REQUEST_CAMERA_ID_CARD, SELECT_FILE_ID_CARD);
            }
        });

        containerIvCc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, CHANGE_IMAGE_CC);

//        selectImage(REQUEST_CAMERA_ID_CARD, SELECT_FILE_ID_CARD);
            }
        });

        ivSupportdoc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, CHANGE_IMAGE_SUPPORT1);

//        selectImage(REQUEST_CAMERA_ID_CARD, SELECT_FILE_ID_CARD);
            }
        });

        ivSupportdoc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, CHANGE_IMAGE_SUPPORT2);

//        selectImage(REQUEST_CAMERA_ID_CARD, SELECT_FILE_ID_CARD);
            }
        });

        containerIvBri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, CHANGE_IMAGE_BRI);

//        selectImage(REQUEST_CAMERA_ID_CARD, SELECT_FILE_ID_CARD);
            }
        });

        containerIvBni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, CHANGE_IMAGE_BNI);

//        selectImage(REQUEST_CAMERA_ID_CARD, SELECT_FILE_ID_CARD);
            }
        });

        containerIvCimb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, CHANGE_IMAGE_CIMB);

//        selectImage(REQUEST_CAMERA_ID_CARD, SELECT_FILE_ID_CARD);
            }
        });

        containerIvMayapada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, CHANGE_IMAGE_MAYAPADA);

//        selectImage(REQUEST_CAMERA_ID_CARD, SELECT_FILE_ID_CARD);
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> bank = stateTransactionSales.getListBank();
                String statusBri = bank.get(StateTransactionSales.KEY_STATUS_BRI);
                String statusBni = bank.get(StateTransactionSales.KEY_STATUS_BNI);
                String statusCimb = bank.get(StateTransactionSales.KEY_STATUS_CIMB);
                String statusMayapada = bank.get(StateTransactionSales.KEY_STATUS_MAYAPADA);
                if(selectedImageKtp == null){
                    bankValidation("Foto KTP tidak Boleh Kosong");
                }
//        else if(selectedImageNpwp == null) {
//            bankValidation("Foto NPWP tidak Boleh Kosong");
//        }
//        else if(selectedImageIdCard == null) {
//            bankValidation("Foto ID Card tidak Boleh Kosong");
//        }
                else if(selectedImageBri == null && statusBri.equals("YES")) {
                    bankValidation("Foto selfie BRI tidak Boleh Kosong");

                }
                else if(selectedBni == null && statusBni.equals("YES")) {
                    bankValidation("Foto selfie BNI tidak Boleh Kosong");

                }
                else if(selectedCimb == null && statusCimb.equals("YES")) {
                    bankValidation("Foto selfie CIMB Niaga tidak Boleh Kosong");

                }
                else if(selectedMayapada == null && statusMayapada.equals("YES")) {
                    bankValidation("Foto selfie Mayapada tidak Boleh Kosong");

                }else {
                    containerIvKtp.setEnabled(false);
                    containerIvNpwp.setEnabled(false);
                    containerIvIdcard.setEnabled(false);
                    containerIvCc.setEnabled(false);
                    containerIvSupportdoc1.setEnabled(false);
                    containerIvSupportdoc2.setEnabled(false);
                    containerIvBri.setEnabled(false);
                    containerIvBni.setEnabled(false);
                    containerIvCimb.setEnabled(false);
                    containerIvMayapada.setEnabled(false);
                    submitCollectionData(selectedImageKtp, selectedImageNpwp, selectedImageIdCard, selectedImageCC,
                            selectedImageDoc1, selectedImageDoc2, selectedImageBri, selectedBni, selectedCimb, selectedMayapada);
                }

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
                            Glide.with(FormUploadDocumentSelfie.this).
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
                            Glide.with(FormUploadDocumentSelfie.this).
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
                            Glide.with(FormUploadDocumentSelfie.this).
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
                            Glide.with(FormUploadDocumentSelfie.this).
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
                            Glide.with(FormUploadDocumentSelfie.this).
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
                            Glide.with(FormUploadDocumentSelfie.this).
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
                            Glide.with(FormUploadDocumentSelfie.this).
                                    load(selectedImageBri)
                                    .placeholder(R.drawable.ic_camera)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .transition(DrawableTransitionOptions.withCrossFade(100))
                                    .into(ivBriSelfie);
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
                            Glide.with(FormUploadDocumentSelfie.this).
                                    load(selectedBni)
                                    .placeholder(R.drawable.ic_camera)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .transition(DrawableTransitionOptions.withCrossFade(100))
                                    .into(ivBniSelfie);
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
                            Glide.with(FormUploadDocumentSelfie.this).
                                    load(selectedCimb)
                                    .placeholder(R.drawable.ic_camera)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .transition(DrawableTransitionOptions.withCrossFade(100))
                                    .into(ivSelfieCimb);
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
                            Glide.with(FormUploadDocumentSelfie.this).
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

    public void submitCollectionData(Bitmap gambarbitmapKtp, Bitmap gambarbitmapNpwp, Bitmap gambarbitmapKtpIdcard,
                            Bitmap gambarbitmapCC, Bitmap gambarbitmapDoc1, Bitmap gambarbitmapDoc2,
                            Bitmap gambarbitmapBri, Bitmap gambarbitmapBni, Bitmap gambarbitmapCimb,
                            Bitmap gambarbitmapMayapada) {



        HashMap<String, String> bank = stateTransactionSales.getListBank();
        HashMap<String, String> user = stateTransactionSales.getInputForm();
        HashMap<String, String> session = sessionManagement.getUserDetails();

        Log.d("getName", "name "+session.get(sessionManagement.KEY_NAME));


        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("bri", createPartFromString(bank.get(StateTransactionSales.KEY_STATUS_BRI)));
        map.put("bni", createPartFromString(bank.get(StateTransactionSales.KEY_STATUS_BNI)));
        map.put("bca", createPartFromString(bank.get(StateTransactionSales.KEY_STATUS_BCA)));
        map.put("cimb_niaga", createPartFromString(bank.get(StateTransactionSales.KEY_STATUS_CIMB)));
        map.put("mayapada", createPartFromString(bank.get(StateTransactionSales.KEY_STATUS_MAYAPADA)));
        map.put("dbs", createPartFromString(bank.get(StateTransactionSales.KEY_STATUS_DBS)));
        map.put("mnc", createPartFromString(bank.get(StateTransactionSales.KEY_STATUS_MNC)));
        map.put("uob", createPartFromString(bank.get(StateTransactionSales.KEY_STATUS_UOB)));
        map.put("mega", createPartFromString(bank.get(StateTransactionSales.KEY_STATUS_MEGA)));
        map.put("panin", createPartFromString(bank.get(StateTransactionSales.KEY_STATUS_PANIN)));
        map.put("nama", createPartFromString(user.get(StateTransactionSales.KEY_NAMA)));
        map.put("nik", createPartFromString(user.get(StateTransactionSales.KEY_NIK)));
        map.put("tanggal_lahir", createPartFromString(user.get(StateTransactionSales.KEY_TANGGAL_LAHIR)));
        map.put("handphone_1", createPartFromString(user.get(StateTransactionSales.KEY_HANDPHONE1)));
        map.put("handphone_2", createPartFromString(user.get(StateTransactionSales.KEY_HANDPHONE2)));
        map.put("nama_ibu_kandung", createPartFromString(user.get(StateTransactionSales.KEY_NAMA_IBU_KANDUNG)));
        map.put("nama_perusahaan", createPartFromString(user.get(StateTransactionSales.KEY_NAMA_PERUSAHAAN)));
        map.put("alamat_perusahaan", createPartFromString(user.get(StateTransactionSales.KEY_ALAMAT_PERUSAHAAN)));
        map.put("telephone_kantor", createPartFromString(user.get(StateTransactionSales.KEY_TELEPHONE_KANTOR)));
        map.put("nama_emergency_contact", createPartFromString(user.get(StateTransactionSales.KEY_NAMA_EMERGENCY_KONTAK)));
        map.put("hubungan", createPartFromString(user.get(StateTransactionSales.KEY_HUBUNGAN)));
        map.put("sales_code", createPartFromString(session.get(sessionManagement.KEY_SALES_CODE)));
        map.put("sales_name", createPartFromString(session.get(sessionManagement.KEY_NAME)));
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
        Call<ResponseBody> call = mApiService.salesInput(fileKtp,fileNpwp,fileFotoId,fileKartuKredit,fileDoc1,
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
                                    Toast.makeText(FormUploadDocumentSelfie.this, error_message, Toast.LENGTH_SHORT).show();


                                    Toast.makeText(FormUploadDocumentSelfie.this, "success", Toast.LENGTH_SHORT).show();
                                    DialogSuccess bottomSheetFragment = new DialogSuccess();
                                    bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());


                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(FormUploadDocumentSelfie.this, error_message, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(FormUploadDocumentSelfie.this, error_message, Toast.LENGTH_SHORT).show();
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

    public void bankValidation(String message){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.nested), message, Snackbar.LENGTH_SHORT)
                .setAction("Action", null);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        snackbar.show();
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
