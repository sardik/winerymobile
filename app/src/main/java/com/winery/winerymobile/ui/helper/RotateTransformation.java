package com.winery.winerymobile.ui.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

public class RotateTransformation extends BitmapTransformation {

    private float rotateRotationAngle = 0f;
    Context ctx;

    public RotateTransformation(Context context, float rotateRotationAngle) {
//        super(context);


        this.rotateRotationAngle = rotateRotationAngle;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Matrix matrix = new Matrix();

        matrix.postRotate(rotateRotationAngle);

        return Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(("rotate" + rotateRotationAngle).getBytes());
    }
}