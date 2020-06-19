package com.winery.winerymobile.ui.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.winery.winerymobile.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static File createTempFile(Bitmap bitmap, Context context) {
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                , System.currentTimeMillis() +"_image.webp");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.WEBP,60, bos);
        byte[] bitmapdata = bos.toByteArray();
        //write the bytes in file

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static String getDateThreeLetterTransaction(String datePromo){
        String cur = "";
        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat format1 = new SimpleDateFormat("d MMM yyyy hh:mm:ss");
        Date date = new Date();
        try {
            date = formattedDate.parse(datePromo);
            cur = formattedDate.format(date);
            cur = format1.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cur;
    }

    public static String getDateThreeLetter(String datePromo){
        String cur = "";
        SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat format1 = new SimpleDateFormat("d MMM yyyy");
        Date date = new Date();
        try {
            date = formattedDate.parse(datePromo);
            cur = formattedDate.format(date);
            cur = format1.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cur;
    }

//    public static Date getDate(String datePromo) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date date = format.parse(datePromo);
//            System.out.println(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }

    public static int getDaysDifference(Date fromDate,Date toDate)
    {
        if(fromDate==null||toDate==null)
            return 0;

        return (int)( (toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
    }

    public static String getCurrentDateTime(){
        SimpleDateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String cur = iso8601Format.format(new Date());
        return cur;
    }

    // setup dimension of bottom sheet dialog
    public static void setupFullHeight(BottomSheetDialog bottomSheetDialog, Context context, Double percentPoint) {
        FrameLayout bottomSheet = (FrameLayout) bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
        bottomSheet.setBackground(null);
        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        BottomSheetBehavior.from(bottomSheet).setSkipCollapsed(true);
        BottomSheetBehavior.from(bottomSheet).setHideable(true);
        ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();

        int windowHeight = getWindowHeight(context, percentPoint);
        if (layoutParams != null) {
            layoutParams.height = windowHeight;
        }
        bottomSheet.setLayoutParams(layoutParams);
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private static int getWindowHeight(Context context, Double percentPoint) {
        // Calculate window height for fullscreen use
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) (displayMetrics.heightPixels *percentPoint);
    }

}
