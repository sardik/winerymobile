package com.winery.winerymobile.ui.CreditCardSubmission;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.winery.winerymobile.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomerDataForm extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.appBar)
    com.google.android.material.appbar.AppBarLayout appBar;
    @BindView(R.id.toolbar_main)
    com.google.android.material.appbar.MaterialToolbar toolbarMain;
    @BindView(R.id.toolbar_title)
    ImageView toolbarTitle;
    @BindView(R.id.nested)
    androidx.core.widget.NestedScrollView nested;
    @BindView(R.id.ti_name)
    com.google.android.material.textfield.TextInputLayout tiName;
    @BindView(R.id.et_name)
    com.google.android.material.textfield.TextInputEditText etName;
    @BindView(R.id.ti_nik)
    com.google.android.material.textfield.TextInputLayout tiNik;
    @BindView(R.id.et_nik)
    com.google.android.material.textfield.TextInputEditText etNik;
    @BindView(R.id.ti_date_birth)
    com.google.android.material.textfield.TextInputLayout tiDateBirth;
    @BindView(R.id.et_date_brith)
    com.google.android.material.textfield.TextInputEditText etDateBrith;
    @BindView(R.id.ti_month_birth)
    com.google.android.material.textfield.TextInputLayout tiMonthBirth;
    @BindView(R.id.et_month_birth)
    com.google.android.material.textfield.TextInputEditText etMonthBirth;
    @BindView(R.id.ti_year_birth)
    com.google.android.material.textfield.TextInputLayout tiYearBirth;
    @BindView(R.id.et_year_birth)
    com.google.android.material.textfield.TextInputEditText etYearBirth;
    @BindView(R.id.ti_hp1)
    com.google.android.material.textfield.TextInputLayout tiHp1;
    @BindView(R.id.et_hp1)
    com.google.android.material.textfield.TextInputEditText etHp1;
    @BindView(R.id.ti_hp2)
    com.google.android.material.textfield.TextInputLayout tiHp2;
    @BindView(R.id.et_hp2)
    com.google.android.material.textfield.TextInputEditText etHp2;
    @BindView(R.id.ti_mother_name)
    com.google.android.material.textfield.TextInputLayout tiMotherName;
    @BindView(R.id.et_mother_name)
    com.google.android.material.textfield.TextInputEditText etMotherName;
    @BindView(R.id.ti_company_name)
    com.google.android.material.textfield.TextInputLayout tiCompanyName;
    @BindView(R.id.et_company_name)
    com.google.android.material.textfield.TextInputEditText etCompanyName;
    @BindView(R.id.ti_company_address)
    com.google.android.material.textfield.TextInputLayout tiCompanyAddress;
    @BindView(R.id.et_company_address)
    com.google.android.material.textfield.TextInputEditText etCompanyAddress;
//    @BindView(R.id.ti_company_phone_ext)
//    com.google.android.material.textfield.TextInputLayout tiCompanyPhoneExt;
//    @BindView(R.id.et_company_phone_ext)
//    com.google.android.material.textfield.TextInputEditText etCompanyPhoneExt;
    @BindView(R.id.ti_company_phone)
    com.google.android.material.textfield.TextInputLayout tiCompanyPhone;
    @BindView(R.id.et_company_phone)
    com.google.android.material.textfield.TextInputEditText etCompanyPhone;
    @BindView(R.id.ti_emergency_name)
    com.google.android.material.textfield.TextInputLayout tiEmergencyName;
    @BindView(R.id.et_emergency_name)
    com.google.android.material.textfield.TextInputEditText etEmergencyName;
    @BindView(R.id.ti_emergency_relationship)
    com.google.android.material.textfield.TextInputLayout tiEmergencyRelationship;
    @BindView(R.id.et_emergency_relationship)
    com.google.android.material.textfield.TextInputEditText etEmergencyRelationship;
    @BindView(R.id.btn_next)
    com.google.android.material.button.MaterialButton btnNext;
    @BindView(R.id.btn_back)
    com.google.android.material.button.MaterialButton btnBack;
    /** ButterKnife Code **/

    @OnClick(R.id.btn_next) void next(){
        if(etName.getText().toString().equals("")){
            tiName.setError("Nama harus di isi");
            etName.requestFocus();
        }
        else if(etNik.getText().toString().equals("")){
            tiNik.setError("Nik harus di isi");
            etNik.requestFocus();
        }
        else if(etDateBrith.getText().toString().equals("")){
            tiDateBirth.setError("Tanggal harus di isi");
            etDateBrith.requestFocus();

        }
        else if(etMonthBirth.getText().toString().equals("")){
            tiMonthBirth.setError("Bulan harus di isi");
            etMonthBirth.requestFocus();
        }
        else if(etYearBirth.getText().toString().equals("")){
            tiYearBirth.setError("Tahun harus di isi");
            etYearBirth.requestFocus();
        }
        else if(etHp1.getText().toString().equals("") && etHp2.getText().toString().equals("")){
            tiHp1.setError("minimal 1 nomor harus di isi");
            etHp1.requestFocus();

        }
        else if(etMotherName.getText().toString().equals("")){
            tiMotherName.setError("nama ibu harus di isi");
            etMotherName.requestFocus();
        }
        else if(etCompanyName.getText().toString().equals("")){
            tiCompanyName.setError("nama perusahaan harus di isi");
            etCompanyName.requestFocus();
        }
        else if(etCompanyAddress.getText().toString().equals("")){
            tiCompanyAddress.setError("alamat perusahaan harus di isi");
            etCompanyAddress.requestFocus();
        }
        else if(etCompanyPhone.getText().toString().equals("")){
            tiCompanyPhone.setError("telpon kantor harus di isi");
            etCompanyPhone.requestFocus();
        }
        else if(etEmergencyName.getText().toString().equals("")){
            tiEmergencyName.setError("nama emergency contact harus di isi");
            etEmergencyName.requestFocus();
        }
        else if(etEmergencyRelationship.getText().toString().equals("")){
            tiEmergencyRelationship.setError("hubungan harus di isi");
            tiEmergencyRelationship.requestFocus();

        }
        else{

        }
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_data_form);
        ButterKnife.bind(this);
    }
}
