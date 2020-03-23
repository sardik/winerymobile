package com.winery.winerymobile.ui.CreditCardSubmission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.DialogOptionUserLogin;

import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @OnClick(R.id.btn_submit) void submitData(){
        DialogSuccess bottomSheetFragment = new DialogSuccess();
        bottomSheetFragment.show(this.getSupportFragmentManager(), bottomSheetFragment.getTag());
    }

    @OnClick(R.id.container_iv_bri) void getPhotobri(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_BRI);
    }

    @OnClick(R.id.container_iv_bni) void getPhotobni(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_BNI);
    }

    @OnClick(R.id.container_iv_cimb) void getPhotoCimb(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_CIMB);
    }

    @OnClick(R.id.container_iv_mayapada) void getPhotoMayapada(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, CHANGE_IMAGE_MAYAPADA);
    }


    private static final int CHANGE_IMAGE_BRI = 0;
    private static final int CHANGE_IMAGE_BNI = 1;
    private static final int CHANGE_IMAGE_CIMB = 2;
    private static final int CHANGE_IMAGE_MAYAPADA = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_upload_document_selfie);

        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (reqCode == CHANGE_IMAGE_BRI) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    ivBriSelfie.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else {

            }
        }
        if (reqCode == CHANGE_IMAGE_BNI) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    ivBniSelfie.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else {

            }
        }
        if (reqCode == CHANGE_IMAGE_CIMB) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    ivSelfieCimb.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else {

            }
        }
        if (reqCode == CHANGE_IMAGE_MAYAPADA) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    ivSelfieMayapada.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else {

            }
        }
    }
}
