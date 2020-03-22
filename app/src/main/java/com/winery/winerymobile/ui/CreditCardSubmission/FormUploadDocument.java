package com.winery.winerymobile.ui.CreditCardSubmission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.winery.winerymobile.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    ivKtp.setImageBitmap(selectedImage);
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
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    ivNpwp.setImageBitmap(selectedImage);
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
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    ivIdcard.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else {

            }
        }
    }

}
