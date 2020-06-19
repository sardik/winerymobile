package com.winery.winerymobile.ui.Recontest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.winery.winerymobile.R;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewDataCustomerRecontestActivity extends AppCompatActivity {

    @BindView(R.id.appBar)
    com.google.android.material.appbar.AppBarLayout appBar;
    @BindView(R.id.toolbar_main)
    com.google.android.material.appbar.MaterialToolbar toolbarMain;
    @BindView(R.id.toolbar_title)
    ImageView toolbarTitle;
    @BindView(R.id.sc_filter_recontest)
    ScrollView scFilterRecontest;
    @BindView(R.id.container_iv_ktp)
    RelativeLayout containerIvKtp;
    @BindView(R.id.iv_ktp)
    ImageView ivKtp;
    @BindView(R.id.container_iv_npwp)
    RelativeLayout containerIvNpwp;
    @BindView(R.id.iv_npwp)
    ImageView ivNpwp;
    @BindView(R.id.container_iv_idcard)
    RelativeLayout containerIvIdcard;
    @BindView(R.id.iv_idcard)
    ImageView ivIdcard;
    @BindView(R.id.container_iv_cc)
    RelativeLayout containerIvCc;
    @BindView(R.id.iv_cc)
    ImageView ivCc;
    @BindView(R.id.container_iv_supportdoc1)
    RelativeLayout containerIvSupportdoc1;
    @BindView(R.id.iv_supportdoc1)
    ImageView ivSupportdoc1;
    @BindView(R.id.container_iv_supportdoc2)
    RelativeLayout containerIvSupportdoc2;
    @BindView(R.id.iv_supportdoc2)
    ImageView ivSupportdoc2;
    @BindView(R.id.container_iv_bri)
    RelativeLayout containerIvBri;
    @BindView(R.id.iv_bri)
    ImageView ivBri;
    @BindView(R.id.container_iv_bni)
    RelativeLayout containerIvBni;
    @BindView(R.id.iv_bni)
    ImageView ivBni;
    @BindView(R.id.container_iv_cimb)
    RelativeLayout containerIvCimb;
    @BindView(R.id.iv_cimb)
    ImageView ivCimb;
    @BindView(R.id.tv_container_mayapada)
    TextView tvContainerMayapada;
    @BindView(R.id.container_iv_mayapada)
    RelativeLayout containerIvMayapada;
    @BindView(R.id.iv_selfie_mayapada)
    ImageView ivSelfieMayapada;
    @BindView(R.id.rl_footer)
    RelativeLayout rlFooter;
    @BindView(R.id.btn_recontest)
    com.google.android.material.button.MaterialButton btnRecontest;

    Intent intent;
    Bitmap bitmapKtp, bitmapNpwp;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_data_customer_recontest);

        intent = getIntent();
        initImage();

        ButterKnife.bind(this);
        initToolbar();
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

    public void initImage(){
//        intent.putExtra("imageKtp", selectedImageKtp);
//        intent.putExtra("imageNpwp", selectedImageNpwp);
//        intent.putExtra("imageCC", selectedImageCC);
//        intent.putExtra("imageDoc1", selectedImageDoc1);
//        intent.putExtra("imageDoc2", selectedImageDoc2);
//        intent.putExtra("imageIdCard", selectedImageIdCard);
//        intent.putExtra("imageBri", selectedImageBri);
//        intent.putExtra("imageBni", selectedBni);
//        intent.putExtra("imageCimb", selectedCimb);
//        intent.putExtra("imageMayapada", selectedMayapada);
        bitmapKtp = (Bitmap) intent.getParcelableExtra("imageKtp");
        loadImage(bitmapKtp,ivKtp);
        bitmapNpwp = (Bitmap) intent.getParcelableExtra("imageNpwp");
        loadImage(bitmapNpwp,ivNpwp);

        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        loadImage(bmp,ivKtp);
    }

    private byte[] bitmapToByte(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void loadImage(Bitmap bitmap, ImageView imageView){

                Glide.with(this).
                asBitmap()
                .load(bitmap)
                .placeholder(R.color.grey_20)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .fitCenter()
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
}
