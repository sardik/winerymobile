package com.winery.winerymobile.ui;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

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
public class DialogOptionUserLogin extends BottomSheetDialogFragment {


    /** ButterKnife Code **/
    @BindView(R.id.sales)
    RelativeLayout sales;
    @BindView(R.id.verifikator)
    RelativeLayout verifikator;
    @BindView(R.id.juru_tulis)
    RelativeLayout juruTulis;
    /** ButterKnife Code **/

    @OnClick(R.id.sales) void loginsales(){
        dismiss();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.putExtra("loginAs", "sales");
        startActivity(intent);
        getActivity().finishAffinity();
    }

    @OnClick(R.id.verifikator) void loginverif(){
        dismiss();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.putExtra("loginAs", "verifikator");
        startActivity(intent);
        getActivity().finishAffinity();
    }

    @OnClick(R.id.juru_tulis) void loginjurutulis(){
        dismiss();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.putExtra("loginAs", "jurutulis");
        startActivity(intent);
        getActivity().finishAffinity();
    }

    public DialogOptionUserLogin() {
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
        View view =  inflater.inflate(R.layout.fragment_dialog_option_user_login, container, false);
        ButterKnife.bind(this, view);

        return view;

    }

}
