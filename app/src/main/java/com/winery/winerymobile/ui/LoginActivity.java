package com.winery.winerymobile.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.winery.winerymobile.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.ti_nik)
    com.google.android.material.textfield.TextInputLayout tiNik;
    @BindView(R.id.et_nik)
    com.google.android.material.textfield.TextInputEditText etNik;
    @BindView(R.id.ti_password)
    com.google.android.material.textfield.TextInputLayout tiPassword;
    @BindView(R.id.et_password)
    com.google.android.material.textfield.TextInputEditText etPassword;
    @BindView(R.id.btn_login)
    com.google.android.material.button.MaterialButton btnLogin;
    @BindView(R.id.btn_back)
    com.google.android.material.button.MaterialButton btnBack;
    /** ButterKnife Code **/

    @OnClick(R.id.btn_login) void login(){

        if(etNik.getText().toString().equals("") || etNik.getText().toString().equals(null)){
            tiNik.setError("Nik harus di isi");
        }else if(etPassword.getText().toString().equals("") || etPassword.getText().toString().equals(null)){
            tiPassword.setError("password harus di isi");
        }else{
            Intent intent = new Intent(this, ParentHomeActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.btn_back) void back(){
        onBackPressed();
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

}
