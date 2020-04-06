package com.winery.winerymobile.ui.CreditCardSubmission;


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

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.ParentHomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogSuccess extends BottomSheetDialogFragment {

    /** ButterKnife Code **/
    @BindView(R.id.btn_home)
    com.google.android.material.button.MaterialButton btnHome;
    /** ButterKnife Code **/

    @OnClick(R.id.btn_home) void gotohome(){
            Intent intent = new Intent(getContext(), ParentHomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public DialogSuccess() {
        // Required empty public constructor
    }

    @NonNull
    @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override public void onShow(DialogInterface dialogInterface) {
                BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
                FrameLayout bottomSheet = (FrameLayout) bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
                bottomSheet.setBackground(null);
                BottomSheetBehavior.from(bottomSheet).setSkipCollapsed(false);
                BottomSheetBehavior.from(bottomSheet).setHideable(false);
            }
        });
        return  dialog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_dialog_success, container, false);
        ButterKnife.bind(this, view);

        return view;

    }

}
