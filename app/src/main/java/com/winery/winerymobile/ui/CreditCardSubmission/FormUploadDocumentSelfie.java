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
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.APIhelper.BaseApiService;
import com.winery.winerymobile.ui.APIhelper.UtilsApi;
import com.winery.winerymobile.ui.DialogOptionUserLogin;
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
    }

    @OnClick(R.id.container_iv_ktp) void getPhotoKTP(){
//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image/*");
//        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_KTP);
        selectImage(REQUEST_CAMERA_KTP, SELECT_FILE_KTP);
    }

    @OnClick(R.id.container_iv_npwp) void getPhotoNPWP(){
//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image/*");
//        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_NPWP);
        selectImage(REQUEST_CAMERA_NPWP, SELECT_FILE_NPWP);
    }

    @OnClick(R.id.container_iv_idcard) void getPhotoIDcard(){
//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image/*");
//        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_IDCARD);

        selectImage(REQUEST_CAMERA_ID_CARD, SELECT_FILE_ID_CARD);
    }

    @OnClick(R.id.container_iv_cc) void getPhotoCC(){
//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image/*");
//        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_CC);

        selectImage(REQUEST_CAMERA_KARTU_KREDIT, SELECT_FILE_KARTU_KREDIT);
    }

    @OnClick(R.id.container_iv_supportdoc1) void getPhotoSupport1(){
//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image/*");
//        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_SUPPORT1);
        selectImage(REQUEST_CAMERA_DOC_PENDUKUNG1, SELECT_FILE_DOC_PENDUKUNG1);
    }

    @OnClick(R.id.container_iv_supportdoc2) void getPhotoSupport2(){
//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image/*");
//        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_SUPPORT2);
        selectImage(REQUEST_CAMERA_DOC_PENDUKUNG2, SELECT_FILE_DOC_PENDUKUNG2);

    }

    @OnClick(R.id.container_iv_bri) void getPhotobri(){
//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image/*");
//        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_BRI);
        selectImage(REQUEST_CAMERA_BRI, SELECT_FILE_BRI);

    }

    @OnClick(R.id.container_iv_bni) void getPhotobni(){
//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image/*");
//        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_BNI);
        selectImage(REQUEST_CAMERA_BNI, SELECT_FILE_BNI);

    }

    @OnClick(R.id.container_iv_cimb) void getPhotoCimb(){
//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image/*");
//        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_CIMB);
        selectImage(REQUEST_CAMERA_CIMB, SELECT_FILE_CIMB);

    }

    @OnClick(R.id.container_iv_mayapada) void getPhotoMayapada(){
//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image/*");
//        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_MAYAPADA);
        selectImage(REQUEST_CAMERA_MAYAPADA, SELECT_FILE_DOC_MAYAPADA);

    }


    @OnClick(R.id.btn_submit) void submitData(){
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

            submitCollectionData(selectedImageKtp, selectedImageNpwp, selectedImageIdCard, selectedImageCC,
                    selectedImageDoc1, selectedImageDoc2, selectedImageBri, selectedBni, selectedCimb, selectedMayapada);
        }
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

    int bitmap_size = 40; // image quality 1 - 100;
    int max_resolution_image = 800;

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
            ivSelfieMayapada.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (reqCode == REQUEST_CAMERA_KTP) {
                try {
                    Log.e("CAMERA", fileUri.getPath());

                    String file = fileUri.getPath();
                    BitmapFactory.Options bounds = new BitmapFactory.Options();
                    bounds.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(file, bounds);

                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    Bitmap bm = BitmapFactory.decodeFile(file, opts);
                    ExifInterface exif = new ExifInterface(file);
                    String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                    int orientation = orientString != null ? Integer.parseInt(orientString) :  ExifInterface.ORIENTATION_NORMAL;

                    int rotationAngle = 0;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;

                    Matrix matrix = new Matrix();
                    matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                    selectedImageKtp = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
                    setToImageView(getResizedBitmap(selectedImageKtp, max_resolution_image),ivKtp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (reqCode == SELECT_FILE_KTP && data != null && data.getData() != null) {
                try {

                    selectedImageKtp = MediaStore.Images.Media.getBitmap(FormUploadDocumentSelfie.this.getContentResolver(), data.getData());
                    setToImageView(getResizedBitmap(selectedImageKtp, max_resolution_image), ivKtp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (resultCode == Activity.RESULT_OK) {
            if (reqCode == REQUEST_CAMERA_NPWP) {
                try {
                    Log.e("CAMERA", fileUri.getPath());
                    String file = fileUri.getPath();
                    BitmapFactory.Options bounds = new BitmapFactory.Options();
                    bounds.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(file, bounds);

                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    Bitmap bm = BitmapFactory.decodeFile(file, opts);
                    ExifInterface exif = new ExifInterface(file);
                    String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                    int orientation = orientString != null ? Integer.parseInt(orientString) :  ExifInterface.ORIENTATION_NORMAL;

                    int rotationAngle = 0;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;

                    Matrix matrix = new Matrix();
                    matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                    selectedImageNpwp = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
//                    selectedImageNpwp = BitmapFactory.decodeFile(fileUri.getPath());
                    setToImageView(getResizedBitmap(selectedImageNpwp, max_resolution_image), ivNpwp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (reqCode == SELECT_FILE_NPWP && data != null && data.getData() != null) {
                try {

                    selectedImageNpwp = MediaStore.Images.Media.getBitmap(FormUploadDocumentSelfie.this.getContentResolver(), data.getData());
                    setToImageView(getResizedBitmap(selectedImageNpwp, max_resolution_image), ivNpwp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (resultCode == Activity.RESULT_OK) {
            if (reqCode == REQUEST_CAMERA_ID_CARD) {
                try {
                    Log.e("CAMERA", fileUri.getPath());
                    String file = fileUri.getPath();
                    BitmapFactory.Options bounds = new BitmapFactory.Options();
                    bounds.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(file, bounds);

                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    Bitmap bm = BitmapFactory.decodeFile(file, opts);
                    ExifInterface exif = new ExifInterface(file);
                    String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                    int orientation = orientString != null ? Integer.parseInt(orientString) :  ExifInterface.ORIENTATION_NORMAL;

                    int rotationAngle = 0;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;

                    Matrix matrix = new Matrix();
                    matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                    selectedImageIdCard = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
//                    selectedImageIdCard = BitmapFactory.decodeFile(fileUri.getPath());
                    setToImageView(getResizedBitmap(selectedImageIdCard, max_resolution_image), ivIdcard);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (reqCode == SELECT_FILE_ID_CARD && data != null && data.getData() != null) {
                try {

                    selectedImageIdCard = MediaStore.Images.Media.getBitmap(FormUploadDocumentSelfie.this.getContentResolver(), data.getData());
                    setToImageView(getResizedBitmap(selectedImageIdCard, max_resolution_image), ivIdcard);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (resultCode == Activity.RESULT_OK) {
            if (reqCode == REQUEST_CAMERA_KARTU_KREDIT) {
                try {
                    Log.e("CAMERA", fileUri.getPath());

                    String file = fileUri.getPath();
                    BitmapFactory.Options bounds = new BitmapFactory.Options();
                    bounds.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(file, bounds);

                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    Bitmap bm = BitmapFactory.decodeFile(file, opts);
                    ExifInterface exif = new ExifInterface(file);
                    String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                    int orientation = orientString != null ? Integer.parseInt(orientString) :  ExifInterface.ORIENTATION_NORMAL;

                    int rotationAngle = 0;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;

                    Matrix matrix = new Matrix();
                    matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                    selectedImageCC = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
//                    selectedImageCC = BitmapFactory.decodeFile(fileUri.getPath());
                    setToImageView(getResizedBitmap(selectedImageCC, max_resolution_image), ivCc);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (reqCode == SELECT_FILE_KARTU_KREDIT && data != null && data.getData() != null) {
                try {

                    selectedImageCC = MediaStore.Images.Media.getBitmap(FormUploadDocumentSelfie.this.getContentResolver(), data.getData());
                    setToImageView(getResizedBitmap(selectedImageCC, max_resolution_image), ivCc);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (resultCode == Activity.RESULT_OK) {
            if (reqCode == REQUEST_CAMERA_DOC_PENDUKUNG1) {
                try {
                    Log.e("CAMERA", fileUri.getPath());
                    String file = fileUri.getPath();
                    BitmapFactory.Options bounds = new BitmapFactory.Options();
                    bounds.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(file, bounds);

                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    Bitmap bm = BitmapFactory.decodeFile(file, opts);
                    ExifInterface exif = new ExifInterface(file);
                    String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                    int orientation = orientString != null ? Integer.parseInt(orientString) :  ExifInterface.ORIENTATION_NORMAL;

                    int rotationAngle = 0;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;

                    Matrix matrix = new Matrix();
                    matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                    selectedImageDoc1 = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
//                    selectedImageDoc1 = BitmapFactory.decodeFile(fileUri.getPath());
                    setToImageView(getResizedBitmap(selectedImageDoc1, max_resolution_image), ivSupportdoc1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (reqCode == SELECT_FILE_DOC_PENDUKUNG1 && data != null && data.getData() != null) {
                try {

                    selectedImageDoc1 = MediaStore.Images.Media.getBitmap(FormUploadDocumentSelfie.this.getContentResolver(), data.getData());
                    setToImageView(getResizedBitmap(selectedImageDoc1, max_resolution_image), ivSupportdoc1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (resultCode == Activity.RESULT_OK) {
            if (reqCode == REQUEST_CAMERA_DOC_PENDUKUNG2) {
                try {
                    Log.e("CAMERA", fileUri.getPath());

                    String file = fileUri.getPath();
                    BitmapFactory.Options bounds = new BitmapFactory.Options();
                    bounds.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(file, bounds);

                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    Bitmap bm = BitmapFactory.decodeFile(file, opts);
                    ExifInterface exif = new ExifInterface(file);
                    String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                    int orientation = orientString != null ? Integer.parseInt(orientString) :  ExifInterface.ORIENTATION_NORMAL;

                    int rotationAngle = 0;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;

                    Matrix matrix = new Matrix();
                    matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                    selectedImageDoc2 = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
//                    selectedImageDoc2 = BitmapFactory.decodeFile(fileUri.getPath());
                    setToImageView(getResizedBitmap(selectedImageDoc2, max_resolution_image), ivSupportdoc2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (reqCode == SELECT_FILE_DOC_PENDUKUNG2 && data != null && data.getData() != null) {
                try {

                    selectedImageDoc2 = MediaStore.Images.Media.getBitmap(FormUploadDocumentSelfie.this.getContentResolver(), data.getData());
                    setToImageView(getResizedBitmap(selectedImageDoc2, max_resolution_image), ivSupportdoc2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (resultCode == Activity.RESULT_OK) {
            if (reqCode == REQUEST_CAMERA_BRI) {
                try {
                    Log.e("CAMERA", fileUri.getPath());

                    String file = fileUri.getPath();
                    BitmapFactory.Options bounds = new BitmapFactory.Options();
                    bounds.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(file, bounds);

                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    Bitmap bm = BitmapFactory.decodeFile(file, opts);
                    ExifInterface exif = new ExifInterface(file);
                    String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                    int orientation = orientString != null ? Integer.parseInt(orientString) :  ExifInterface.ORIENTATION_NORMAL;

                    int rotationAngle = 0;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;

                    Matrix matrix = new Matrix();
                    matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                    selectedImageBri = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
//                    selectedImageBri = BitmapFactory.decodeFile(fileUri.getPath());
                    setToImageView(getResizedBitmap(selectedImageBri, max_resolution_image), ivBriSelfie);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (reqCode == SELECT_FILE_BRI && data != null && data.getData() != null) {
                try {

                    selectedImageBri = MediaStore.Images.Media.getBitmap(FormUploadDocumentSelfie.this.getContentResolver(), data.getData());
                    setToImageView(getResizedBitmap(selectedImageBri, max_resolution_image), ivBriSelfie);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (resultCode == Activity.RESULT_OK) {
            if (reqCode == REQUEST_CAMERA_BNI) {
                try {
                    Log.e("CAMERA", fileUri.getPath());
                    String file = fileUri.getPath();
                    BitmapFactory.Options bounds = new BitmapFactory.Options();
                    bounds.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(file, bounds);

                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    Bitmap bm = BitmapFactory.decodeFile(file, opts);
                    ExifInterface exif = new ExifInterface(file);
                    String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                    int orientation = orientString != null ? Integer.parseInt(orientString) :  ExifInterface.ORIENTATION_NORMAL;

                    int rotationAngle = 0;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;

                    Matrix matrix = new Matrix();
                    matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                    selectedBni = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);

//                    selectedBni = BitmapFactory.decodeFile(fileUri.getPath());
                    setToImageView(getResizedBitmap(selectedBni, max_resolution_image), ivBniSelfie);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (reqCode == SELECT_FILE_BNI && data != null && data.getData() != null) {
                try {

                    selectedBni = MediaStore.Images.Media.getBitmap(FormUploadDocumentSelfie.this.getContentResolver(), data.getData());
                    setToImageView(getResizedBitmap(selectedBni, max_resolution_image), ivBniSelfie);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (resultCode == Activity.RESULT_OK) {
            if (reqCode == REQUEST_CAMERA_CIMB) {
                try {
                    Log.e("CAMERA", fileUri.getPath());
                    String file = fileUri.getPath();
                    BitmapFactory.Options bounds = new BitmapFactory.Options();
                    bounds.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(file, bounds);

                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    Bitmap bm = BitmapFactory.decodeFile(file, opts);
                    ExifInterface exif = new ExifInterface(file);
                    String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                    int orientation = orientString != null ? Integer.parseInt(orientString) :  ExifInterface.ORIENTATION_NORMAL;

                    int rotationAngle = 0;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;

                    Matrix matrix = new Matrix();
                    matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                    selectedCimb = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
//                    selectedCimb = BitmapFactory.decodeFile(fileUri.getPath());
                    setToImageView(getResizedBitmap(selectedCimb, max_resolution_image), ivSelfieCimb);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (reqCode == SELECT_FILE_CIMB && data != null && data.getData() != null) {
                try {

                    selectedCimb = MediaStore.Images.Media.getBitmap(FormUploadDocumentSelfie.this.getContentResolver(), data.getData());
                    setToImageView(getResizedBitmap(selectedCimb, max_resolution_image), ivSelfieCimb);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (resultCode == Activity.RESULT_OK) {
            if (reqCode == REQUEST_CAMERA_MAYAPADA) {
                try {
                    Log.e("CAMERA", fileUri.getPath());
                    String file = fileUri.getPath();
                    BitmapFactory.Options bounds = new BitmapFactory.Options();
                    bounds.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(file, bounds);

                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    Bitmap bm = BitmapFactory.decodeFile(file, opts);
                    ExifInterface exif = new ExifInterface(file);
                    String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                    int orientation = orientString != null ? Integer.parseInt(orientString) :  ExifInterface.ORIENTATION_NORMAL;

                    int rotationAngle = 0;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;

                    Matrix matrix = new Matrix();
                    matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                    selectedMayapada = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
//                    selectedMayapada = BitmapFactory.decodeFile(fileUri.getPath());
                    setToImageView(getResizedBitmap(selectedMayapada, max_resolution_image), ivSelfieMayapada);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (reqCode == SELECT_FILE_DOC_MAYAPADA && data != null && data.getData() != null) {
                try {

                    selectedMayapada = MediaStore.Images.Media.getBitmap(FormUploadDocumentSelfie.this.getContentResolver(), data.getData());
                    setToImageView(getResizedBitmap(selectedMayapada, max_resolution_image), ivSelfieMayapada);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        if (reqCode == CHANGE_IMAGE_KTP) {
//            if (resultCode == RESULT_OK) {
//                try {
//                    final Uri imageUri = data.getData();
//                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    selectedImageKtp = BitmapFactory.decodeStream(imageStream);
//                    ivKtp.setImageBitmap(selectedImageKtp);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            } else {
//
//            }
//        }else if (reqCode == CHANGE_IMAGE_NPWP){
//            if (resultCode == RESULT_OK) {
//                try {
//                    final Uri imageUri = data.getData();
//                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    selectedImageNpwp = BitmapFactory.decodeStream(imageStream);
//                    ivNpwp.setImageBitmap(selectedImageNpwp);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            } else {
//
//            }
//        }else if (reqCode == CHANGE_IMAGE_IDCARD){
//            if (resultCode == RESULT_OK) {
//                try {
//                    final Uri imageUri = data.getData();
//                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    selectedImageIdCard = BitmapFactory.decodeStream(imageStream);
//                    ivIdcard.setImageBitmap(selectedImageIdCard);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            } else {
//
//            }
//        }
//        else if (reqCode == CHANGE_IMAGE_CC){
//            if (resultCode == RESULT_OK) {
//                try {
//                    final Uri imageUri = data.getData();
//                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    selectedImageCC = BitmapFactory.decodeStream(imageStream);
//                    ivCc.setImageBitmap(selectedImageCC);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            } else {
//
//            }
//        }
//        else if (reqCode == CHANGE_IMAGE_SUPPORT1){
//            if (resultCode == RESULT_OK) {
//                try {
//                    final Uri imageUri = data.getData();
//                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    selectedImageDoc1 = BitmapFactory.decodeStream(imageStream);
//                    ivSupportdoc1.setImageBitmap(selectedImageDoc1);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            } else {
//
//            }
//        }
//        else if (reqCode == CHANGE_IMAGE_SUPPORT2) {
//            if (resultCode == RESULT_OK) {
//                try {
//                    final Uri imageUri = data.getData();
//                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    selectedImageDoc2 = BitmapFactory.decodeStream(imageStream);
//                    ivSupportdoc2.setImageBitmap(selectedImageDoc2);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            } else {
//
//            }
//        }else if (reqCode == CHANGE_IMAGE_BRI) {
//            if (resultCode == RESULT_OK) {
//                try {
//                    final Uri imageUri = data.getData();
//                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    selectedImageBri = BitmapFactory.decodeStream(imageStream);
//                    ivBriSelfie.setImageBitmap(selectedImageBri);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            } else {
//
//            }
//        }
//        if (reqCode == CHANGE_IMAGE_BNI) {
//            if (resultCode == RESULT_OK) {
//                try {
//                    final Uri imageUri = data.getData();
//                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    selectedBni = BitmapFactory.decodeStream(imageStream);
//                    ivBniSelfie.setImageBitmap(selectedBni);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            } else {
//
//            }
//        }
//        if (reqCode == CHANGE_IMAGE_CIMB) {
//            if (resultCode == RESULT_OK) {
//                try {
//                    final Uri imageUri = data.getData();
//                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    selectedCimb = BitmapFactory.decodeStream(imageStream);
//                    ivSelfieCimb.setImageBitmap(selectedCimb);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            } else {
//
//            }
//        }
//        if (reqCode == CHANGE_IMAGE_MAYAPADA) {
//            if (resultCode == RESULT_OK) {
//                try {
//                    final Uri imageUri = data.getData();
//                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    selectedMayapada = BitmapFactory.decodeStream(imageStream);
//                    ivSelfieMayapada.setImageBitmap(selectedMayapada);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            } else {
//
//            }
//        }
    }

    private void selectImage(int RequestCamera, int requestFile) {
//        ivKtp.setImageResource(0);
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(FormUploadDocumentSelfie.this);
        builder.setTitle("Add Photo!");
        builder.setIcon(R.mipmap.ic_winery2);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    fileUri = getOutputMediaFileUri();
                    intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, RequestCamera);
                } else if (items[item].equals("Choose from Library")) {
                    intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), requestFile);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    // Set Image
    private void setToImageView(Bitmap bmp, ImageView imageView) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imageView.setImageBitmap(decoded);
    }

    public void submitCollectionData(Bitmap gambarbitmapKtp, Bitmap gambarbitmapNpwp, Bitmap gambarbitmapKtpIdcard,
                            Bitmap gambarbitmapCC, Bitmap gambarbitmapDoc1, Bitmap gambarbitmapDoc2,
                            Bitmap gambarbitmapBri, Bitmap gambarbitmapBni, Bitmap gambarbitmapCimb,
                            Bitmap gambarbitmapMayapada) {

        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);

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
        call.enqueue(
                new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getInt("status") == 200){

                                    Toast.makeText(FormUploadDocumentSelfie.this, "success", Toast.LENGTH_SHORT).show();
                                    DialogSuccess bottomSheetFragment = new DialogSuccess();
                                    bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());

                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(FormUploadDocumentSelfie.this, error_message, Toast.LENGTH_SHORT).show();
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

    // Untuk resize bitmap
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    private static File getOutputMediaFile() {

        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "DeKa");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.e("Monitoring", "Oops! Failed create Monitoring directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "Win" + timeStamp + ".jpg");

        return mediaFile;
    }

    public void rotate(Uri imageUri){
        File imageFile = new File(imageUri.toString());

        try {
            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            int rotate = 0;
            switch(orientation) {
                case  ExifInterface.ORIENTATION_ROTATE_270:
                    rotate-=90;break;
                case  ExifInterface.ORIENTATION_ROTATE_180:
                    rotate-=90;break;
                case  ExifInterface.ORIENTATION_ROTATE_90:
                    rotate-=90;break;
            }
        } catch (IOException e) {
        }
    }
}
