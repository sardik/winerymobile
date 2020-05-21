package com.winery.winerymobile.ui.CreditCardSubmission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.winery.winerymobile.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.sql.Types.NULL;

public class FormUploadDocument extends AppCompatActivity {

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
    @BindView(R.id.btn_next)
    com.google.android.material.button.MaterialButton btnNext;
    @BindView(R.id.btn_back)
    com.google.android.material.button.MaterialButton btnBack;
    /** ButterKnife Code **/

    @OnClick(R.id.btn_back) void back(){
        onBackPressed();
        finish();
    }

    @OnClick(R.id.btn_next) void fotoselfie(){
        if(selectedImageKtp == null){
            bankValidation("Foto KTP tidak Boleh Kosong");
        }else if(selectedImageNpwp == null) {
            bankValidation("Foto NPWP tidak Boleh Kosong");
        }else if(selectedImageIdCard == null) {
            bankValidation("Foto ID Card tidak Boleh Kosong");
        }else{
            Intent intent = new Intent(this, FormUploadDocumentSelfie.class);
            //Convert to byte array
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            // ktp
            selectedImageKtp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArrayKtp = stream.toByteArray();

            // npwp
            selectedImageNpwp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArrayNpwp = stream.toByteArray();

            // ID Card
            selectedImageIdCard.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArrayIdCard = stream.toByteArray();


            intent.putExtra("imageKtp",byteArrayKtp);
            intent.putExtra("imageNpwp",byteArrayNpwp);
            intent.putExtra("imageIDCard",byteArrayIdCard);

//            // CC
//            if(selectedImageCC == null){
//                intent.putExtra("imageCC","");
//            }else{
//                selectedImageIdCard.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                byte[] byteArrayCC = stream.toByteArray();
//                intent.putExtra("imageCC",byteArrayCC);
//            }
//
//            // Doc 1
//            if(selectedImageDoc1 == null){
//                intent.putExtra("imageDoc1","");
//            }else{
//                selectedImageDoc1.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                byte[] byteArrayDoc1 = stream.toByteArray();
//                intent.putExtra("imageDoc1",byteArrayDoc1);
//            }
//
//            // Doc 1
//            if(selectedImageDoc2 == null){
//                intent.putExtra("imageDoc2","");
//            }else{
//                selectedImageDoc2.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                byte[] byteArrayDoc2 = stream.toByteArray();
//                intent.putExtra("imageDoc2",byteArrayDoc2);
//            }

//            intent.putExtra("imageKtp", selectedImageKtp);
//            Log.d("imageKtp", "fotoselfie: "+selectedImageKtp);
//            intent.putExtra("imageNpwp", selectedImageNpwp);
//            intent.putExtra("imageIDCard", selectedImageIdCard);
//            intent.putExtra("imageCC", selectedImageCC);
//            intent.putExtra("imageDoc1", selectedImageDoc1);
//            intent.putExtra("imageDoc2", selectedImageDoc2);




            startActivity(intent);
        }
    }

    @OnClick(R.id.container_iv_ktp) void getPhotoKTP(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_KTP);
    }

    @OnClick(R.id.container_iv_npwp) void getPhotoNPWP(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_NPWP);
    }

    @OnClick(R.id.container_iv_idcard) void getPhotoIDcard(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_IDCARD);
    }

    @OnClick(R.id.container_iv_cc) void getPhotoCC(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_CC);
    }

    @OnClick(R.id.container_iv_supportdoc1) void getPhotoSupport1(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_SUPPORT1);
    }

    @OnClick(R.id.container_iv_supportdoc2) void getPhotoSupport2(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_SUPPORT2);
    }

    private static final int CHANGE_IMAGE_KTP = 0;
    private static final int CHANGE_IMAGE_NPWP = 1;
    private static final int CHANGE_IMAGE_IDCARD = 2;
    private static final int CHANGE_IMAGE_CC = 3;
    private static final int CHANGE_IMAGE_SUPPORT1 = 4;
    private static final int CHANGE_IMAGE_SUPPORT2 = 5;

    Bitmap selectedImageIdCard = null , selectedImageKtp = null, selectedImageNpwp = null,
            selectedImageCC = null, selectedImageDoc1 = null, selectedImageDoc2 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_upload_document);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (reqCode == CHANGE_IMAGE_KTP) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    selectedImageKtp = BitmapFactory.decodeStream(imageStream);
                    ivKtp.setImageBitmap(selectedImageKtp);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else {

            }
        }else if (reqCode == CHANGE_IMAGE_NPWP){
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    selectedImageNpwp = BitmapFactory.decodeStream(imageStream);
                    ivNpwp.setImageBitmap(selectedImageNpwp);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else {

            }
        }else if (reqCode == CHANGE_IMAGE_IDCARD){
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    selectedImageIdCard = BitmapFactory.decodeStream(imageStream);
                    ivIdcard.setImageBitmap(selectedImageIdCard);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else {

            }
        }
        else if (reqCode == CHANGE_IMAGE_CC){
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    selectedImageCC = BitmapFactory.decodeStream(imageStream);
                    ivCc.setImageBitmap(selectedImageCC);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else {

            }
        }
        else if (reqCode == CHANGE_IMAGE_SUPPORT1){
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    selectedImageDoc1 = BitmapFactory.decodeStream(imageStream);
                    ivSupportdoc1.setImageBitmap(selectedImageDoc1);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else {

            }
        }
        else if (reqCode == CHANGE_IMAGE_SUPPORT2) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    selectedImageDoc2 = BitmapFactory.decodeStream(imageStream);
                    ivSupportdoc2.setImageBitmap(selectedImageDoc2);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else {

            }
        }
    }

    public void bankValidation(String message){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.nested), message, Snackbar.LENGTH_SHORT)
                .setAction("Action", null);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        snackbar.show();
    }

}
