package com.winery.winerymobile.ui.dbhelper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.android.material.appbar.MaterialToolbar;
import com.winery.winerymobile.ui.LoginActivity;
import com.winery.winerymobile.ui.MainActivity;
import com.winery.winerymobile.ui.ParentHomeActivity;
import com.winery.winerymobile.ui.SplashScreen;

import java.util.HashMap;

public class SessionManagement {
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

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_NAME = "name";
    public static final String KEY_ALIAS = "alias";
    public static final String KEY_POSITION = "position";
    public static final String KEY_LEVEL = "level";
    public static final String KEY_SALES_CODE = "sales_code";
    public static final String KEY_STATUS = "status";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_REGION = "password";
    public static final String KEY_LOGIN_AS = "login_as";

    public static final String KEY_TRANSACTION_ID = "transaction_id";

    // Constructor
    public SessionManagement(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String name, String alias, String position, String level, String sales_code, String status, String password, String region, String login_as){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_ALIAS, alias);
        editor.putString(KEY_POSITION, position);
        editor.putString(KEY_LEVEL, level);
        editor.putString(KEY_SALES_CODE, sales_code);
        editor.putString(KEY_STATUS, status);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_REGION, region);
        editor.putString(KEY_LOGIN_AS, login_as);
        // commit changes
        editor.commit();
    }

    public void savetransactionID(String transaction_id){

        // Storing name in pref
        editor.putString(KEY_TRANSACTION_ID, transaction_id);
        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, MainActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user data
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_ALIAS, pref.getString(KEY_ALIAS, null));
        user.put(KEY_SALES_CODE, pref.getString(KEY_SALES_CODE, null));
        user.put(KEY_POSITION, pref.getString(KEY_POSITION, null));
        user.put(KEY_REGION, pref.getString(KEY_REGION, null));
        user.put(KEY_LOGIN_AS, pref.getString(KEY_LOGIN_AS, null));

        // return user
        return user;
    }

    public HashMap<String, String> getTransactionID(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user data
        user.put(KEY_TRANSACTION_ID, pref.getString(KEY_TRANSACTION_ID, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, MainActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
