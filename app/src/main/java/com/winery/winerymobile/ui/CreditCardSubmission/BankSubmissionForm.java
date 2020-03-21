package com.winery.winerymobile.ui.CreditCardSubmission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.winery.winerymobile.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BankSubmissionForm extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.appBar)
    com.google.android.material.appbar.AppBarLayout appBar;
    @BindView(R.id.toolbar_main)
    com.google.android.material.appbar.MaterialToolbar toolbarMain;
    @BindView(R.id.toolbar_title)
    ImageView toolbarTitle;
    @BindView(R.id.nested)
    androidx.core.widget.NestedScrollView nested;
    @BindView(R.id.card_bank_bri)
    com.google.android.material.card.MaterialCardView cardBankBri;
    @BindView(R.id.tv_bank_bri)
    TextView tvBankBri;
    @BindView(R.id.iv_bri)
    ImageView ivBri;
    @BindView(R.id.card_bank_bni)
    com.google.android.material.card.MaterialCardView cardBankBni;
    @BindView(R.id.tv_bank_ni)
    TextView tvBankNi;
    @BindView(R.id.iv_bni)
    ImageView ivBni;
    @BindView(R.id.card_bank_bca)
    com.google.android.material.card.MaterialCardView cardBankBca;
    @BindView(R.id.tv_bank_bca)
    TextView tvBankBca;
    @BindView(R.id.iv_bca)
    ImageView ivBca;
    @BindView(R.id.card_bank_cimb)
    com.google.android.material.card.MaterialCardView cardBankCimb;
    @BindView(R.id.tv_bank_cimb)
    TextView tvBankCimb;
    @BindView(R.id.iv_cimb)
    ImageView ivCimb;
    @BindView(R.id.card_bank_mayapada)
    com.google.android.material.card.MaterialCardView cardBankMayapada;
    @BindView(R.id.tv_bank_mayapada)
    TextView tvBankMayapada;
    @BindView(R.id.iv_mayapada)
    ImageView ivMayapada;
    @BindView(R.id.card_bank_dbs)
    com.google.android.material.card.MaterialCardView cardBankDbs;
    @BindView(R.id.tv_bank_dbs)
    TextView tvBankDbs;
    @BindView(R.id.iv_dbs)
    ImageView ivDbs;
    @BindView(R.id.card_bank_mnc)
    com.google.android.material.card.MaterialCardView cardBankMnc;
    @BindView(R.id.tv_bank_mnc)
    TextView tvBankMnc;
    @BindView(R.id.iv_mnc)
    ImageView ivMnc;
    @BindView(R.id.card_bank_uob)
    com.google.android.material.card.MaterialCardView cardBankUob;
    @BindView(R.id.tv_bank_uob)
    TextView tvBankUob;
    @BindView(R.id.iv_uob)
    ImageView ivUob;
    @BindView(R.id.btn_next)
    com.google.android.material.button.MaterialButton btnNext;
    @BindView(R.id.btn_back)
    com.google.android.material.button.MaterialButton btnBack;
    /** ButterKnife Code **/

    @OnClick(R.id.card_bank_bri) void selectedBri(){
        Log.d("tag", "selectedBri: "+ivBri.isSelected());
        if(ivBri.isSelected()){
            ivBri.setSelected(false);
            ivBri.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), android.graphics.PorterDuff.Mode.MULTIPLY);
        }else{
            ivBri.setSelected(true);
            ivBri.setColorFilter(ContextCompat.getColor(this, R.color.green_600), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

    @OnClick(R.id.card_bank_bni) void selectedBni(){
        if(ivBni.isSelected()){
            ivBni.setSelected(false);
            ivBni.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), android.graphics.PorterDuff.Mode.MULTIPLY);
        }else{
            ivBni.setSelected(true);
            ivBni.setColorFilter(ContextCompat.getColor(this, R.color.green_600), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

    @OnClick(R.id.card_bank_bca) void selectedBca(){
        if(ivBca.isSelected()){
            ivBca.setSelected(false);
            ivBca.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), android.graphics.PorterDuff.Mode.MULTIPLY);
        }else{
            ivBca.setSelected(true);
            ivBca.setColorFilter(ContextCompat.getColor(this, R.color.green_600), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

    @OnClick(R.id.card_bank_cimb) void selectedCimb(){
        if(ivCimb.isSelected()){
            ivCimb.setSelected(false);
            ivCimb.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), android.graphics.PorterDuff.Mode.MULTIPLY);
        }else{
            ivCimb.setSelected(true);
            ivCimb.setColorFilter(ContextCompat.getColor(this, R.color.green_600), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

    @OnClick(R.id.card_bank_mayapada) void selectedMayapada(){
        if(ivMayapada.isSelected()){
            ivMayapada.setSelected(false);
            ivMayapada.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), android.graphics.PorterDuff.Mode.MULTIPLY);
        }else{
            ivMayapada.setSelected(true);
            ivMayapada.setColorFilter(ContextCompat.getColor(this, R.color.green_600), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

    @OnClick(R.id.card_bank_dbs) void selectedDbs(){
        if(ivDbs.isSelected()){
            ivDbs.setSelected(false);
            ivDbs.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), android.graphics.PorterDuff.Mode.MULTIPLY);
        }else{
            ivDbs.setSelected(true);
            ivDbs.setColorFilter(ContextCompat.getColor(this, R.color.green_600), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

    @OnClick(R.id.card_bank_mnc) void selectedMnc(){
        if(ivMnc.isSelected()){
            ivMnc.setSelected(false);
            ivMnc.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), android.graphics.PorterDuff.Mode.MULTIPLY);
        }else{
            ivMnc.setSelected(true);
            ivMnc.setColorFilter(ContextCompat.getColor(this, R.color.green_600), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

    @OnClick(R.id.card_bank_uob) void selectedUob(){
        if(ivUob.isSelected()){
            ivUob.setSelected(false);
            ivUob.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), android.graphics.PorterDuff.Mode.MULTIPLY);
        }else{
            ivUob.setSelected(true);
            ivUob.setColorFilter(ContextCompat.getColor(this, R.color.green_600), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

    @OnClick(R.id.btn_back) void back(){
        onBackPressed();
    }

    @OnClick(R.id.btn_next) void next(){
        if( !ivBri.isSelected() &&
            !ivBni.isSelected() &&
            !ivBca.isSelected() &&
            !ivCimb.isSelected() &&
            !ivMayapada.isSelected() &&
            !ivDbs.isSelected() &&
            !ivMnc.isSelected() &&
            !ivUob.isSelected()){

            selectionValidate();
        }
    }




    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_submission_form);
        ButterKnife.bind(this);

        ivBri.setSelected(false);
        ivBni.setSelected(false);
        ivBca.setSelected(false);
        ivCimb.setSelected(false);
        ivMayapada.setSelected(false);
        ivDbs.setSelected(false);
        ivMnc.setSelected(false);
        ivUob.setSelected(false);
    }

    public void selectionValidate(){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.nested), "Pilih minimal satu Bank", Snackbar.LENGTH_SHORT)
                .setAction("Action", null);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        snackbar.show();
    }
}
