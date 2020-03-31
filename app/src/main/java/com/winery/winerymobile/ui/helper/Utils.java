package com.winery.winerymobile.ui.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static File createTempFile(Bitmap bitmap, Context context) {
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                , System.currentTimeMillis() +"_image.webp");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.WEBP,0, bos);
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
        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd");
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
}
