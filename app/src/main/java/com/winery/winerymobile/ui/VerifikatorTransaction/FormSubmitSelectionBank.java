package com.winery.winerymobile.ui.VerifikatorTransaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.CreditCardSubmission.CustomerDataForm;
import com.winery.winerymobile.ui.dbhelper.StateTransactionSales;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;

public class FormSubmitSelectionBank extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.appBar)
    com.google.android.material.appbar.AppBarLayout appBar;
    @BindView(R.id.toolbar_main)
    com.google.android.material.appbar.MaterialToolbar toolbarMain;
    @BindView(R.id.toolbar_title)
    ImageView toolbarTitle;
    @BindView(R.id.nested)
    androidx.core.widget.NestedScrollView nested;
    @BindView(R.id.parent_bri)
    LinearLayout parentBri;
    @BindView(R.id.ti_status_bri)
    com.google.android.material.textfield.TextInputLayout tiStatusBri;
    @BindView(R.id.et_status_bri)
    AutoCompleteTextView etStatusBri;
    @BindView(R.id.parent_bni)
    LinearLayout parentBni;
    @BindView(R.id.ti_status_bni)
    com.google.android.material.textfield.TextInputLayout tiStatusBni;
    @BindView(R.id.et_status_bni)
    AutoCompleteTextView etStatusBni;
    @BindView(R.id.parent_bca)
    LinearLayout parentBca;
    @BindView(R.id.ti_status_bca)
    com.google.android.material.textfield.TextInputLayout tiStatusBca;
    @BindView(R.id.et_status_bca)
    AutoCompleteTextView etStatusBca;
    @BindView(R.id.parent_cimb)
    LinearLayout parentCimb;
    @BindView(R.id.ti_status_cimb)
    com.google.android.material.textfield.TextInputLayout tiStatusCimb;
    @BindView(R.id.et_status_cimb)
    AutoCompleteTextView etStatusCimb;
    @BindView(R.id.parent_mayapada)
    LinearLayout parentMayapada;
    @BindView(R.id.ti_status_mayapada)
    com.google.android.material.textfield.TextInputLayout tiStatusMayapada;
    @BindView(R.id.et_status_mayapada)
    AutoCompleteTextView etStatusMayapada;
    @BindView(R.id.parent_dbs)
    LinearLayout parentDbs;
    @BindView(R.id.ti_status_dbs)
    com.google.android.material.textfield.TextInputLayout tiStatusDbs;
    @BindView(R.id.et_status_dbs)
    AutoCompleteTextView etStatusDbs;
    @BindView(R.id.parent_mnc)
    LinearLayout parentMnc;
    @BindView(R.id.ti_status_mnc)
    com.google.android.material.textfield.TextInputLayout tiStatusMnc;
    @BindView(R.id.et_status_mnc)
    AutoCompleteTextView etStatusMnc;
    @BindView(R.id.parent_uob)
    LinearLayout parentUob;
    @BindView(R.id.ti_status_uob)
    com.google.android.material.textfield.TextInputLayout tiStatusUob;
    @BindView(R.id.et_status_uob)
    AutoCompleteTextView etStatusUob;
    @BindView(R.id.parent_mega)
    LinearLayout parentMega;
    @BindView(R.id.ti_status_mega)
    com.google.android.material.textfield.TextInputLayout tiStatusMega;
    @BindView(R.id.et_status_mega)
    AutoCompleteTextView etStatusMega;
    @BindView(R.id.parent_panin)
    LinearLayout parentPanin;
    @BindView(R.id.ti_status_panin)
    com.google.android.material.textfield.TextInputLayout tiStatusPanin;
    @BindView(R.id.et_status_panin)
    AutoCompleteTextView etStatusPanin;
    @BindView(R.id.btn_next)
    com.google.android.material.button.MaterialButton btnNext;
    @BindView(R.id.btn_back)
    com.google.android.material.button.MaterialButton btnBack;
    /** ButterKnife Code **/

    @OnClick(R.id.btn_back) void back(){
        onBackPressed();
        finish();
    }

    @OnClick(R.id.btn_next) void next() {
            // saving state
            stateTransactionSales.createStateBank(etStatusBri.getText().toString(), etStatusBni.getText().toString(), 
                    etStatusBca.getText().toString(), etStatusCimb.getText().toString(), etStatusMayapada.getText().toString(), 
                    etStatusDbs.getText().toString(), etStatusMnc.getText().toString(), 
                    etStatusUob.getText().toString(), etStatusMega.getText().toString(), etStatusPanin.getText().toString());
            Intent intent = new Intent(this, UploadDataKotorVerif.class);
            startActivity(intent);
            
    }

    StateTransactionSales stateTransactionSales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_submit_selection_bank);
        ButterKnife.bind(this);

        // inisialisasi list status bank
        String[] statusBank = new String[] {"CTN", "HTC", "IVN", "NVA",
                "CBC","UDF","ADN","BCU","NTE"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, statusBank);

        stateTransactionSales = new StateTransactionSales(this);

        HashMap<String, String> bank = stateTransactionSales.getListBank();
        String statusBri = bank.get(StateTransactionSales.KEY_STATUS_BRI);
        String statusBni = bank.get(StateTransactionSales.KEY_STATUS_BNI);
        String statusBca = bank.get(StateTransactionSales.KEY_STATUS_BCA);
        String statusCimb = bank.get(StateTransactionSales.KEY_STATUS_CIMB);
        String statusMayapada = bank.get(StateTransactionSales.KEY_STATUS_MAYAPADA);
        String statusDbs = bank.get(StateTransactionSales.KEY_STATUS_DBS);
        String statusMnc = bank.get(StateTransactionSales.KEY_STATUS_MNC);
        String statusUob = bank.get(StateTransactionSales.KEY_STATUS_UOB);
        String statusMega = bank.get(StateTransactionSales.KEY_STATUS_MEGA);
        String statusPanin = bank.get(StateTransactionSales.KEY_STATUS_PANIN);

        Log.d("status", "onCreate: "+statusBri.toString());
        // visibility status BRI
        if(!statusBri.equals("YES")){
            parentBri.setVisibility(GONE);
            if(statusBri.equals("NO")){
                etStatusBri.setText("UDF");
            }else if(statusBri.equals("DUP")){
                etStatusBri.setText("DUP");
            }
        }else if(statusBri.equals("YES")){
            etStatusBri.setText("CTN");
        }

        // visibility status BNI
        if(!statusBni.equals("YES")){
            parentBni.setVisibility(GONE);
            if(statusBni.equals("NO")){
                etStatusBni.setText("UDF");
            }else if(statusBni.equals("DUP")){
                etStatusBni.setText("DUP");
            }
        }else if(statusBni.equals("YES")){
            etStatusBni.setText("CTN");
        }

        // visibility status BCA
        if(!statusBca.equals("YES")){
            parentBca.setVisibility(GONE);
            if(statusBca.equals("NO")){
                etStatusBca.setText("UDF");
            }else if(statusBca.equals("DUP")){
                etStatusBca.setText("DUP");
            }
        }else if(statusBca.equals("YES")){
            etStatusBca.setText("CTN");
        }

        // visibility status CIMB
        if(!statusCimb.equals("YES")){
            parentCimb.setVisibility(GONE);
            if(statusCimb.equals("NO")){
                etStatusCimb.setText("UDF");
            }else if(statusCimb.equals("DUP")){
                etStatusCimb.setText("DUP");
            }
        }else if(statusCimb.equals("YES")){
            etStatusCimb.setText("CTN");
        }

        // visibility status MAYAPADA
        if(!statusMayapada.equals("YES")){
            parentMayapada.setVisibility(GONE);
            if(statusMayapada.equals("NO")){
                etStatusMayapada.setText("UDF");
            }else if(statusMayapada.equals("DUP")){
                etStatusMayapada.setText("DUP");
            }
        }else if(statusMayapada.equals("YES")){
            etStatusMayapada.setText("CTN");
        }

        // visibility status DBS
        if(!statusDbs.equals("YES")){
            parentDbs.setVisibility(GONE);
            if(statusDbs.equals("NO")){
                etStatusDbs.setText("UDF");
            }else if(statusDbs.equals("DUP")){
                etStatusDbs.setText("DUP");
            }
        }else if(statusDbs.equals("YES")){
            etStatusDbs.setText("CTN");
        }

        // visibility status MNC
        if(!statusMnc.equals("YES")){
            parentMnc.setVisibility(GONE);
            if(statusMnc.equals("NO")){
                etStatusMnc.setText("UDF");
            }else if(statusMnc.equals("DUP")){
                etStatusMnc.setText("DUP");
            }
        }else if(statusMnc.equals("YES")){
            etStatusMnc.setText("CTN");
        }

        // visibility status UOB
        if(!statusUob.equals("YES")){
            parentUob.setVisibility(GONE);
            if(statusUob.equals("NO")){
                etStatusUob.setText("UDF");
            }else if(statusUob.equals("DUP")){
                etStatusUob.setText("DUP");
            }
        }else if(statusUob.equals("YES")){
            etStatusUob.setText("CTN");
        }

        // visibility status MEGA
        if(!statusMega.equals("YES")){
            parentMega.setVisibility(GONE);
            if(statusMega.equals("NO")){
                etStatusMega.setText("UDF");
            }else if(statusMega.equals("DUP")){
                etStatusMega.setText("DUP");
            }
        }else if(statusMega.equals("YES")){
            etStatusMega.setText("CTN");
        }

        // visibility status PANIN
        if(!statusPanin.equals("YES")){
            parentPanin.setVisibility(GONE);
            if(statusPanin.equals("NO")){
                etStatusPanin.setText("UDF");
            }else if(statusPanin.equals("DUP")){
                etStatusPanin.setText("DUP");
            }
        }else if(statusPanin.equals("YES")){
            etStatusPanin.setText("CTN");
        }

        // inisial ettext value list
        etStatusBri.setAdapter(adapter);
        etStatusBni.setAdapter(adapter);
        etStatusBca.setAdapter(adapter);
        etStatusCimb.setAdapter(adapter);
        etStatusDbs.setAdapter(adapter);
        etStatusMayapada.setAdapter(adapter);
        etStatusMnc.setAdapter(adapter);
        etStatusUob.setAdapter(adapter);
        etStatusMega.setAdapter(adapter);
        etStatusPanin.setAdapter(adapter);
    }

    @Override
    protected void onStop(){
        Log.d("onstop", "onStop: jalanbanksubmissionform");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("odestroy", "onDestroy: jalanbanksubmissionform");
        super.onDestroy();
    }
}
