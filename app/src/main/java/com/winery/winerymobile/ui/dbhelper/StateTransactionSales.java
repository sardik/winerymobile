package com.winery.winerymobile.ui.dbhelper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class StateTransactionSales {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "winerypref";


        // status bank
    public static final String KEY_STATUS_BRI = "status_bri";
    public static final String KEY_STATUS_BNI = "status_bni";
    public static final String KEY_STATUS_BCA = "status_bca";
    public static final String KEY_STATUS_CIMB = "status_cimb";
    public static final String KEY_STATUS_MAYAPADA = "status_mayapada";
    public static final String KEY_STATUS_DBS = "status_dbs";
    public static final String KEY_STATUS_MNC = "status_mnc";
    public static final String KEY_STATUS_UOB = "status_uob";
    public static final String KEY_STATUS_MEGA = "status_mega";

    // input form
    public static final String KEY_NAMA = "nama";
    public static final String KEY_NIK = "nik";
    public static final String KEY_TANGGAL_LAHIR = "tanggal_lahir";
    public static final String KEY_HANDPHONE1 = "handphone_1";
    public static final String KEY_HANDPHONE2 = "handphone_2";
    public static final String KEY_NAMA_IBU_KANDUNG = "nama_ibu_kandung";
    public static final String KEY_NAMA_PERUSAHAAN = "nama_perusahaan";
    public static final String KEY_ALAMAT_PERUSAHAAN = "alamat_perusahaan";
    public static final String KEY_TELEPHONE_KANTOR = "telephone_kantor";
    public static final String KEY_NAMA_EMERGENCY_KONTAK = "nama_emergency_contact";
    public static final String KEY_HUBUNGAN = "hubungan";
    public static final String KEY_SALES_CODE = "sales_code";
    public static final String KEY_SALES_NAME = "sales_name";




    // Constructor
    public StateTransactionSales(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createStateBank(String bri, String bni, String bca, String cimb, String mayapada,
                                String dbs, String mnc, String uob, String mega){

        // Storing name in pref
        editor.putString(KEY_STATUS_BRI, bri);
        editor.putString(KEY_STATUS_BNI, bni);
        editor.putString(KEY_STATUS_BCA, bca);
        editor.putString(KEY_STATUS_CIMB, cimb);
        editor.putString(KEY_STATUS_MAYAPADA, mayapada);
        editor.putString(KEY_STATUS_DBS, dbs);
        editor.putString(KEY_STATUS_MNC, mnc);
        editor.putString(KEY_STATUS_UOB, uob);
        editor.putString(KEY_STATUS_MEGA, mega);
        // commit changes
        editor.commit();
    }

    // get data bank
    public HashMap<String, String> getListBank(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user data
        user.put(KEY_STATUS_BRI, pref.getString(KEY_STATUS_BRI, null));
        user.put(KEY_STATUS_BNI, pref.getString(KEY_STATUS_BNI, null));
        user.put(KEY_STATUS_BCA, pref.getString(KEY_STATUS_BCA, null));
        user.put(KEY_STATUS_CIMB, pref.getString(KEY_STATUS_CIMB, null));
        user.put(KEY_STATUS_MAYAPADA, pref.getString(KEY_STATUS_MAYAPADA, null));
        user.put(KEY_STATUS_DBS, pref.getString(KEY_STATUS_DBS, null));
        user.put(KEY_STATUS_MNC, pref.getString(KEY_STATUS_MNC, null));
        user.put(KEY_STATUS_UOB, pref.getString(KEY_STATUS_UOB, null));
        user.put(KEY_STATUS_MEGA, pref.getString(KEY_STATUS_MEGA, null));

        // return user
        return user;
    }

    public void createStateInpuForm(String nama, String nik, String tanggal_lahir, String handphone_1,
                                    String handphone_2,String nama_ibu_kandung,String nama_perusahaan,
                                    String alamat_perusahaan,String telephone_kantor,String nama_emergency_contact,
                                    String hubungan,String sales_code, String sales_name){

        // Storing name in pref
        editor.putString(KEY_NAMA, nama);
        editor.putString(KEY_NIK, nik);
        editor.putString(KEY_TANGGAL_LAHIR, tanggal_lahir);
        editor.putString(KEY_HANDPHONE1, handphone_1);
        editor.putString(KEY_HANDPHONE2, handphone_2);
        editor.putString(KEY_NAMA_IBU_KANDUNG, nama_ibu_kandung);
        editor.putString(KEY_NAMA_PERUSAHAAN, nama_perusahaan);
        editor.putString(KEY_ALAMAT_PERUSAHAAN, alamat_perusahaan);
        editor.putString(KEY_TELEPHONE_KANTOR, telephone_kantor);
        editor.putString(KEY_NAMA_EMERGENCY_KONTAK, nama_emergency_contact);
        editor.putString(KEY_HUBUNGAN, hubungan);
        editor.putString(KEY_SALES_CODE, sales_code);
        editor.putString(KEY_SALES_NAME, sales_name);

        // commit changes
        editor.commit();
    }

    // get data bank
    public HashMap<String, String> getInputForm(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user data
        user.put(KEY_NAMA, pref.getString(KEY_NAMA, null));
        user.put(KEY_NIK, pref.getString(KEY_NIK, null));
        user.put(KEY_TANGGAL_LAHIR, pref.getString(KEY_TANGGAL_LAHIR, null));
        user.put(KEY_HANDPHONE1, pref.getString(KEY_HANDPHONE1, null));
        user.put(KEY_HANDPHONE2, pref.getString(KEY_HANDPHONE2, null));
        user.put(KEY_NAMA_IBU_KANDUNG, pref.getString(KEY_NAMA_IBU_KANDUNG, null));
        user.put(KEY_NAMA_PERUSAHAAN, pref.getString(KEY_NAMA_PERUSAHAAN, null));
        user.put(KEY_ALAMAT_PERUSAHAAN, pref.getString(KEY_ALAMAT_PERUSAHAAN, null));
        user.put(KEY_TELEPHONE_KANTOR, pref.getString(KEY_TELEPHONE_KANTOR, null));
        user.put(KEY_NAMA_EMERGENCY_KONTAK, pref.getString(KEY_NAMA_EMERGENCY_KONTAK, null));
        user.put(KEY_HUBUNGAN, pref.getString(KEY_HUBUNGAN, null));
        user.put(KEY_SALES_CODE, pref.getString(KEY_SALES_CODE, null));
        user.put(KEY_SALES_NAME, pref.getString(KEY_SALES_NAME, null));

        // return user
        return user;
    }
}
