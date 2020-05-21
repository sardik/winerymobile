package com.winery.winerymobile.ui.DuplicateCheckCustomer;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.DetailHistoryTransactionInputJurtul;
import com.winery.winerymobile.ui.helper.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildMenuOtherFragment extends BottomSheetDialogFragment {

    /** ButterKnife Code **/
    @BindView(R.id.rl_parent_view)
    RelativeLayout rlParentView;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.card_menu_check_dup)
    com.google.android.material.card.MaterialCardView CardMenuCheckDup;
    /** ButterKnife Code **/

    @OnClick(R.id.card_menu_check_dup) void checkDup(){
        DialogForm();
    }



    public ChildMenuOtherFragment() {
        // Required empty public constructor
    }


    public static ChildMenuOtherFragment newInstance() {
        ChildMenuOtherFragment childMenuOtherFragment = new ChildMenuOtherFragment();
        return childMenuOtherFragment;
    }

    @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override public void onShow(DialogInterface dialogInterface) {
                BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
                Utils.setupFullHeight(bottomSheetDialog, getContext(), 1.0);

            }
        });

        return  dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_child_menu_other, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private void DialogForm() {

//        AlertDialog dialogs;
        LayoutInflater inflater;
        View dialogView;

        AlertDialog.Builder dialogs = new AlertDialog.Builder(getContext());


//        dialogs = new AlertDialog.Builder(getContext());
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_search_customer_dup, null);
        dialogs.setView(dialogView);
        dialogs.setCancelable(true);

        final AlertDialog dialog = dialogs.create();

        MaterialButton btnSearch;
        ContentLoadingProgressBar loading;
        ImageView ivClose;
        TextInputLayout tiName, tiDateBirth, tiMonthBirth, tiYearBirth;
        TextInputEditText etName, etDateBrith, etMonthBirth, etYearBirth;

        ivClose = (ImageView) dialogView.findViewById(R.id.iv_close);

        tiName = (TextInputLayout) dialogView.findViewById(R.id.ti_name);
        tiDateBirth = (TextInputLayout) dialogView.findViewById(R.id.ti_date_birth);
        tiMonthBirth = (TextInputLayout) dialogView.findViewById(R.id.ti_month_birth);
        tiYearBirth = (TextInputLayout) dialogView.findViewById(R.id.ti_year_birth);

        etName = (TextInputEditText) dialogView.findViewById(R.id.et_name);
        etDateBrith = (TextInputEditText) dialogView.findViewById(R.id.et_date_brith);
        etMonthBirth = (TextInputEditText) dialogView.findViewById(R.id.et_month_birth);
        etYearBirth = (TextInputEditText) dialogView.findViewById(R.id.et_year_birth);

        btnSearch = (MaterialButton) dialogView.findViewById(R.id.btn_search);
        loading = (ContentLoadingProgressBar) dialogView.findViewById(R.id.content_loading);

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

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().equals("")){
                    tiName.setError("Nama harus di isi");
                    etName.requestFocus();
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
                }else{
                    Intent intent = new Intent(getContext(), ListCheckDataDuplicate.class);
                    intent.putExtra("name", etName.getText().toString());
                    intent.putExtra("dob", etDateBrith.getText().toString()+"/"
                            +etMonthBirth.getText().toString()+"/"+etYearBirth.getText().toString());
//                    intent.putExtra("dob", etYearBirth.getText().toString()+"-"
//                            +etMonthBirth.getText().toString()+"-"+etDateBrith.getText().toString());
                    startActivity(intent);
                    dialog.dismiss();
                }
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

}
