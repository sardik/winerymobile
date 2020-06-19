package com.winery.winerymobile.ui.CreditCardSubmission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.APIhelper.BaseApiService;
import com.winery.winerymobile.ui.APIhelper.UtilsApi;
import com.winery.winerymobile.ui.AppMain;
import com.winery.winerymobile.ui.VerifikatorTransaction.ListTransactionWaitingVerif;
import com.winery.winerymobile.ui.adapter.LIstHistoriCCAdapter;
import com.winery.winerymobile.ui.dbhelper.SessionManagement;
import com.winery.winerymobile.ui.dbhelper.StateTransactionSales;
import com.winery.winerymobile.ui.model.HadiahList;
import com.winery.winerymobile.ui.model.HistoryCc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

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
    @BindView(R.id.ti_company_phone_ext)
    com.google.android.material.textfield.TextInputLayout tiCompanyPhoneExt;
    @BindView(R.id.et_company_phone_ext)
    com.google.android.material.textfield.TextInputEditText etCompanyPhoneExt;
    @BindView(R.id.ti_emergency_telp)
    com.google.android.material.textfield.TextInputLayout tiEmergencyTelp;
    @BindView(R.id.et_emergency_telp)
    com.google.android.material.textfield.TextInputEditText etEmergencyTelp;
    @BindView(R.id.ti_emergency_name)
    com.google.android.material.textfield.TextInputLayout tiEmergencyName;
    @BindView(R.id.et_emergency_name)
    com.google.android.material.textfield.TextInputEditText etEmergencyName;
    @BindView(R.id.ti_emergency_relationship)
    com.google.android.material.textfield.TextInputLayout tiEmergencyRelationship;
    @BindView(R.id.ti_hadiah)
    com.google.android.material.textfield.TextInputLayout tiHadiah;
    @BindView(R.id.et_hadiah)
    AutoCompleteTextView etHadiah;
    @BindView(R.id.ti_hadiah_referensi)
    com.google.android.material.textfield.TextInputLayout tiHadiahReferensi;
    @BindView(R.id.et_hadiah_referensi)
    AutoCompleteTextView etHadiahReferensi;
//    @BindView(R.id.et_emergency_relationship)
//    com.google.android.material.textfield.TextInputEditText etEmergencyRelationship;
    @BindView(R.id.et_emergency_relationship)
    AutoCompleteTextView etEmergencyRelationship;
    @BindView(R.id.btn_next)
    com.google.android.material.button.MaterialButton btnNext;
    @BindView(R.id.btn_back)
    com.google.android.material.button.MaterialButton btnBack;
    /** ButterKnife Code **/

    @OnClick(R.id.btn_back) void back(){
        onBackPressed();
        finish();
    }

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
        else if(etEmergencyTelp.getText().toString().equals("")){
            tiEmergencyTelp.setError("telp emergency contact harus di isi");
            etEmergencyTelp.requestFocus();
        }
        else if(etEmergencyRelationship.getText().toString().equals("")){
            tiEmergencyRelationship.setError("hubungan harus di isi");
            tiEmergencyRelationship.requestFocus();

        }
        else if(etHadiah.getText().toString().equals("")){
            tiHadiah.setError("hadiah harus di isi");
            tiHadiah.requestFocus();

        }
        else{

            HashMap<String, String> user = sessionManagement.getUserDetails();
            sales_name = user.get(SessionManagement.KEY_NAME);
            sales_code = user.get(SessionManagement.KEY_SALES_CODE);

            nama = etName.getText().toString();
            nik = etNik.getText().toString();
            tanggal_lahir = etDateBrith.getText().toString()+"/"+etMonthBirth.getText().toString()+"/"+etYearBirth.getText().toString();
            bln_lahir = etMonthBirth.getText().toString();
            thn_lahir = etYearBirth.getText().toString();
            handphone_1 = etHp1.getText().toString();
            handphone_2 = etHp2.getText().toString();
            nama_ibu_kandung = etMotherName.getText().toString();
            nama_perusahaan = etCompanyName.getText().toString();
            alamat_perusahaan = etCompanyAddress.getText().toString();
            if(etCompanyPhoneExt.getText().toString().length() > 0){
                telephone_kantor = etCompanyPhone.getText().toString()+"-"+etCompanyPhoneExt.getText().toString();
            }else{
                telephone_kantor = etCompanyPhone.getText().toString();
            }
            nama_emergency_contact = etEmergencyName.getText().toString();
            telp_emergency_contact = etEmergencyTelp.getText().toString();

            hubungan = etEmergencyRelationship.getText().toString();
            hadiah = etHadiah.getText().toString();

            if(etHadiahReferensi.getText().toString().equals("")){
                hadiah_referensi = "NON HADIAH";
            }else{
                hadiah_referensi = etHadiahReferensi.getText().toString();
            }

            hadiah_explode = hadiah+"-"+hadiah_referensi;


            stateTransactionSales.createStateInpuForm(nama, nik, tanggal_lahir, handphone_1, handphone_2,nama_ibu_kandung,
                    nama_perusahaan,alamat_perusahaan,telephone_kantor,nama_emergency_contact,hubungan,sales_code,sales_name, hadiah_explode, telp_emergency_contact);
            Intent intent = new Intent(this, FormUploadDocumentSelfie.class);
            startActivity(intent);
        }
    }

    String nama, nik, tanggal_lahir,bln_lahir,thn_lahir, handphone_1,
    handphone_2, nama_ibu_kandung, nama_perusahaan,
    alamat_perusahaan, telephone_kantor, nama_emergency_contact, telp_emergency_contact,
    hubungan, sales_code, sales_name, hadiah, hadiah_referensi, hadiah_explode;

    SessionManagement sessionManagement;
    StateTransactionSales stateTransactionSales;

    BaseApiService mApiService;
    ProgressDialog loading;
    List<HadiahList> hadiahList;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_data_form);
        ButterKnife.bind(this);

        mApiService = UtilsApi.getAPIService();

        requestGetDataHadiah();

        StringBuilder sb=new StringBuilder();

        String[] relationship = new String[] {"ORANG TUA", "SUAMI / ISTRI", "KAKAK", "ADIK",
                                                "IPAR","PAMAN","BIBI","SEPUPU","KEPONAKAN","ANAK","MERTUA","SAUDARA"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, relationship);
        etEmergencyRelationship.setAdapter(adapter);

        etDateBrith.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    if(etDateBrith.getText().toString().length() == 1){
                        etDateBrith.setText("0"+etDateBrith.getText().toString());
                    }
                }
            }
        });

        etMonthBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    if(etMonthBirth.getText().toString().length() == 1){
                        etMonthBirth.setText("0"+etMonthBirth.getText().toString());
                    }
                }
            }
        });

        // Allcaps text
        etName.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        etMotherName.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        etCompanyName.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        etCompanyAddress.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        etEmergencyRelationship.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        etEmergencyName.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        etHadiah.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        etHadiahReferensi.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        sessionManagement = new SessionManagement(this);
        stateTransactionSales = new StateTransactionSales(this);
    }

    private void requestGetDataHadiah(){
        loading = ProgressDialog.show(CustomerDataForm.this, null, "Harap Tunggu...", true, false);

        mApiService.getListDataHadiah()
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){

                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getInt("status") == 200){

                                    JSONArray m_jsonData = jsonRESULTS.getJSONArray("data");
//                                        JSONArray arr_jsonArticle = m_jsonData.getJSONArray("articles");
                                    ArrayList<String> hadiah = new ArrayList<String>();

                                    hadiahList = new ArrayList<>();
                                    for(int i = 0; i < m_jsonData.length(); i++) {
                                        JSONObject jsonObject = m_jsonData.getJSONObject(i);

                                        String id = jsonObject.getString("id");
                                        String namaBarang = jsonObject.getString("nama_barang");
                                        String jenis = jsonObject.getString("jenis");
                                        String foto = jsonObject.getString("foto");

                                        hadiah.add(namaBarang);

                                    }
                                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_popup_item, hadiah);
                                    etHadiah.setAdapter(adapter);
                                    etHadiahReferensi.setAdapter(adapter);
                                    loading.dismiss();

                                } else {
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(CustomerDataForm.this, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        String error_message = "server error silahkan coba lagi";
                        Toast.makeText(CustomerDataForm.this, error_message, Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                });
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
