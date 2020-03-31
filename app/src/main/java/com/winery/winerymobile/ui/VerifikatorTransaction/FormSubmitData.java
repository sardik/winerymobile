package com.winery.winerymobile.ui.VerifikatorTransaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

public class FormSubmitData extends AppCompatActivity {

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
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_DATA_KOTOR);
    }

    @OnClick(R.id.btn_submit) void submitData(){
        if(selectedImageDataKotor == null) {
            errorValidation("Foto Dokumen tidak boleh ksosong");
        }else {

            submitCollectionData(selectedImageDataKotor);
        }
    }

    private static final int CHANGE_IMAGE_DATA_KOTOR = 0;

    ProgressDialog loading;
    BaseApiService mApiService;
    SessionManagement sessionManagement;
    Bitmap selectedImageDataKotor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_submit_data);
        ButterKnife.bind(this);

        mApiService = UtilsApi.getAPIService();
        sessionManagement = new SessionManagement(this);

        initToolbar();
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (reqCode == CHANGE_IMAGE_DATA_KOTOR) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    selectedImageDataKotor = BitmapFactory.decodeStream(imageStream);
                    ivKtp.setImageBitmap(selectedImageDataKotor);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else {

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
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void submitCollectionData(Bitmap gambarDataKotor) {

        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);

        HashMap<String, String> session = sessionManagement.getUserDetails();
        HashMap<String, String> transactionID = sessionManagement.getTransactionID();


        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("verify_code", createPartFromString(session.get(sessionManagement.KEY_SALES_CODE)));
        map.put("transaction_id", createPartFromString(transactionID.get(sessionManagement.KEY_TRANSACTION_ID)));


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

                                    Toast.makeText(FormSubmitData.this, "success", Toast.LENGTH_SHORT).show();
                                    DialogSuccess bottomSheetFragment = new DialogSuccess();
                                    bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());

                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(FormSubmitData.this, error_message, Toast.LENGTH_SHORT).show();
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

    public void errorValidation(String message){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.nested), message, Snackbar.LENGTH_SHORT)
                .setAction("Action", null);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        snackbar.show();
    }
}
