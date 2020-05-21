package com.winery.winerymobile.ui.VerifikatorTransaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.snackbar.Snackbar;
import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.APIhelper.BaseApiService;
import com.winery.winerymobile.ui.APIhelper.UtilsApi;
import com.winery.winerymobile.ui.CreditCardSubmission.DialogSuccess;
import com.winery.winerymobile.ui.CreditCardSubmission.FormUploadDocumentSelfie;
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

public class UploadDataKotorVerif extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.appBar)
    com.google.android.material.appbar.AppBarLayout appBar;
    @BindView(R.id.toolbar_main)
    com.google.android.material.appbar.MaterialToolbar toolbarMain;
    @BindView(R.id.toolbar_title)
    ImageView toolbarTitle;
    @BindView(R.id.container_iv_ktp)
    LinearLayout containerIvKtp;
    @BindView(R.id.iv_ktp)
    ImageView ivKtp;
    @BindView(R.id.btn_submit)
    com.google.android.material.button.MaterialButton btnSubmit;
    /** ButterKnife Code **/

    @OnClick(R.id.container_iv_ktp) void getPhotoKTP(){
//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image/*");
//        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_DATA_KOTOR);
        selectImage(REQUEST_CAMERA_DATA_KOTOR, SELECT_FILE_DATA_KOTOR);
    }

    @OnClick(R.id.btn_submit) void submitData(){
        if(selectedImageDataKotor == null) {
            errorValidation("Foto Dokumen tidak boleh ksosong");
        }else {
            containerIvKtp.setEnabled(false);
            submitCollectionData(selectedImageDataKotor);
        }
    }

    private static final int CHANGE_IMAGE_DATA_KOTOR = 0;

    ProgressDialog loading;
    BaseApiService mApiService;
    SessionManagement sessionManagement;
    StateTransactionSales stateTransactionSales;
    Bitmap selectedImageDataKotor = null;

    Intent intent;
    Uri fileUri;

    Bitmap bitmap, decoded;
    public final int REQUEST_CAMERA_DATA_KOTOR = 0;
    public final int SELECT_FILE_DATA_KOTOR = 1;


    int bitmap_size = 50; // image quality 1 - 100;
    int max_resolution_image = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_upload_file_datakotor_verif);
        ButterKnife.bind(this);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        mApiService = UtilsApi.getAPIService();
        sessionManagement = new SessionManagement(this);
        stateTransactionSales = new StateTransactionSales(this);

        initToolbar();
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (reqCode == REQUEST_CAMERA_DATA_KOTOR) {
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
                    selectedImageDataKotor = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
//                    selectedImageDataKotor = BitmapFactory.decodeFile(fileUri.getPath());
//                    setToImageView(getResizedBitmap(selectedImageDataKotor, max_resolution_image),ivKtp);
//                    selectedImageDataKotor = getBitmap(fileUri.getPath());
                    if(selectedImageDataKotor != null) {
                        Glide.with(UploadDataKotorVerif.this).
                                load(selectedImageDataKotor)
                                .placeholder(R.drawable.ic_camera)
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .skipMemoryCache(true)
                                .transition(DrawableTransitionOptions.withCrossFade(100))
                                .into(ivKtp);
                    }else{
                    Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
                }
            } else if (reqCode == SELECT_FILE_DATA_KOTOR && data != null && data.getData() != null) {
                try {

//                    selectedImageDataKotor = MediaStore.Images.Media.getBitmap(UploadDataKotorVerif.this.getContentResolver(), data.getData());
//                    setToImageView(getResizedBitmap(selectedImageDataKotor, max_resolution_image), ivKtp);
                    Uri imageUri = data.getData();
                    if(imageUri != null){
                        Uri selectedImage = imageUri;
                        getContentResolver().notifyChange(selectedImage, null);
                        selectedImageDataKotor = getBitmap(imageUri);
                        if(selectedImageDataKotor != null){
                            Glide.with(UploadDataKotorVerif.this).
                                    load(selectedImageDataKotor)
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
            }
        }
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

    private void selectImage(int RequestCamera, int requestFile) {
//        ivKtp.setImageResource(0);
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(UploadDataKotorVerif.this);
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

    public void submitCollectionData(Bitmap gambarDataKotor) {

        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);
        loading.setCanceledOnTouchOutside(false);

        HashMap<String, String> session = sessionManagement.getUserDetails();
        HashMap<String, String> transactionID = sessionManagement.getTransactionID();

        // get list state of bank list
        HashMap<String, String> statusBank = stateTransactionSales.getListBank();


        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("verify_code", createPartFromString(session.get(sessionManagement.KEY_SALES_CODE)));
        map.put("transaction_id", createPartFromString(transactionID.get(sessionManagement.KEY_TRANSACTION_ID)));
        map.put("verifbri", createPartFromString(statusBank.get(stateTransactionSales.KEY_STATUS_BRI)));
        map.put("verifbni", createPartFromString(statusBank.get(stateTransactionSales.KEY_STATUS_BNI)));
        map.put("verifbca", createPartFromString(statusBank.get(stateTransactionSales.KEY_STATUS_BCA)));
        map.put("verifniaga", createPartFromString(statusBank.get(stateTransactionSales.KEY_STATUS_CIMB)));
        map.put("verifmayapada", createPartFromString(statusBank.get(stateTransactionSales.KEY_STATUS_MAYAPADA)));
        map.put("verifuob", createPartFromString(statusBank.get(stateTransactionSales.KEY_STATUS_UOB)));
        map.put("verifmnc", createPartFromString(statusBank.get(stateTransactionSales.KEY_STATUS_MNC)));
        map.put("verifpanin", createPartFromString(statusBank.get(stateTransactionSales.KEY_STATUS_PANIN)));
        map.put("verifdbs", createPartFromString(statusBank.get(stateTransactionSales.KEY_STATUS_DBS)));
        map.put("verifmega", createPartFromString(statusBank.get(stateTransactionSales.KEY_STATUS_MEGA)));



        //convert gambar jadi File terlebih dahulu dengan memanggil createTempFile.
        // foto data kotor
        File filedatakotor = Utils.createTempFile(gambarDataKotor, getApplicationContext());
        RequestBody reqFiledatakotor = RequestBody.create(MediaType.parse("image/*"), filedatakotor);

        MultipartBody.Part fileData = MultipartBody.Part.createFormData("file_data_kotor", filedatakotor.getName(), reqFiledatakotor);


        // finally, kirim map dan body pada param interface retrofit
        Call<ResponseBody> call = mApiService.verifySalesInput(fileData,map);
        call.enqueue(
                new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getInt("status") == 200){

                                    Toast.makeText(UploadDataKotorVerif.this, "success", Toast.LENGTH_SHORT).show();
                                    DialogSuccess bottomSheetFragment = new DialogSuccess();
                                    bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
                                    selectedImageDataKotor.recycle();

                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(UploadDataKotorVerif.this, error_message, Toast.LENGTH_SHORT).show();
                                    selectedImageDataKotor.recycle();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                selectedImageDataKotor.recycle();
                            } catch (IOException e) {
                                e.printStackTrace();
                                selectedImageDataKotor.recycle();
                            }
                        } else {
                            loading.dismiss();
                            selectedImageDataKotor.recycle();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        String error_message = "server error silahkan coba lagi";
                        Toast.makeText(UploadDataKotorVerif.this, error_message, Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                        selectedImageDataKotor.recycle();
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

    public void errorValidation(String message){
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
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "winery");

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

    private Bitmap getBitmap(Uri path) {

        Uri uri = path;
        InputStream in = null;
        try {
            final int IMAGE_MAX_SIZE = 1200000; // 1.2MP
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
