package com.winery.winerymobile.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;

import com.winery.winerymobile.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.btn_admin)
    com.google.android.material.button.MaterialButton btnAdmin;
    @BindView(R.id.btn_user)
    com.google.android.material.button.MaterialButton btnUser;
    /** ButterKnife Code **/

    @OnClick(R.id.btn_admin) void loginAdmin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_user) void loginUser(){
        DialogOptionUserLogin bottomSheetFragment = new DialogOptionUserLogin();
        bottomSheetFragment.show(this.getSupportFragmentManager(), bottomSheetFragment.getTag());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
