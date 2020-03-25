package com.winery.winerymobile.ui;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.winery.winerymobile.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdatePassword extends BottomSheetDialogFragment {

    /** ButterKnife Code **/
    @BindView(R.id.ti_password)
    com.google.android.material.textfield.TextInputLayout tiPassword;
    @BindView(R.id.et_password)
    com.google.android.material.textfield.TextInputEditText etPassword;
    @BindView(R.id.btn_Yes)
    com.google.android.material.button.MaterialButton btnYes;
    @BindView(R.id.btn_cancel)
    com.google.android.material.button.MaterialButton btnCancel;
    /** ButterKnife Code **/


    @OnClick(R.id.btn_Yes) void update(){
        if(etPassword.getText().toString().equals("") || etPassword.getText().toString().equals(null)) {
            tiPassword.setError("password harus di isi");
        }else{

        }
    }

    @OnClick(R.id.btn_cancel) void no(){
        dismiss();
    }


    public UpdatePassword() {
        // Required empty public constructor
    }

    @NonNull
    @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override public void onShow(DialogInterface dialogInterface) {
                BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
                FrameLayout bottomSheet = (FrameLayout) bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
                bottomSheet.setBackground(null);
                BottomSheetBehavior.from(bottomSheet).setSkipCollapsed(true);
                BottomSheetBehavior.from(bottomSheet).setHideable(true);
            }
        });
        return  dialog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_password, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
