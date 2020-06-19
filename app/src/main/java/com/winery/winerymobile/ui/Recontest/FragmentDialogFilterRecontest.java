package com.winery.winerymobile.ui.Recontest;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.helper.Utils;
import com.winery.winerymobile.ui.model.FilterRecontest;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FragmentDialogFilterRecontest extends BottomSheetDialogFragment {

    @BindView(R.id.rl_view_top)
    RelativeLayout rlViewTop;
    @BindView(R.id.sc_filter_recontest)
    ScrollView scFilterRecontest;
    @BindView(R.id.hapus_filter)
    TextView hapusFilter;
    @BindView(R.id.parent_filter_bank)
    RelativeLayout parentFilterBank;
    @BindView(R.id.image_arrow_filter_bank)
    ImageView imageArrowFilterBank;
    @BindView(R.id.parent_filter_rjverif)
    RelativeLayout parentFilterRjverif;
    @BindView(R.id.image_arrow_filter_rj_feriv)
    ImageView imageArrowFilterRjFeriv;
    @BindView(R.id.radio_group_filter_bank)
    RadioGroup radioGroupFilterBank;
    @BindView(R.id.rb_bank_all)
    RadioButton rbBankAll;
    @BindView(R.id.rb_bank_bri)
    RadioButton rbBankBri;
    @BindView(R.id.rb_bank_bni)
    RadioButton rbBankBni;
    @BindView(R.id.rb_bank_bca)
    RadioButton rbBankBca;
    @BindView(R.id.rb_bank_cimb)
    RadioButton rbBankCimb;
    @BindView(R.id.rb_bank_mayapada)
    RadioButton rbBankMayapada;
    @BindView(R.id.rb_bank_dbs)
    RadioButton rbBankDbs;
    @BindView(R.id.rb_bank_MNC)
    RadioButton rbBankMNC;
    @BindView(R.id.rb_bank_uob)
    RadioButton rbBankUob;
    @BindView(R.id.rb_bank_mega)
    RadioButton rbBankMega;
    @BindView(R.id.rb_bank_panin)
    RadioButton rbBankPanin;
    @BindView(R.id.radio_group_filter_rj_verif)
    RadioGroup radioGroupFilterRjVerif;
    @BindView(R.id.rb_rj_verif_all)
    RadioButton rbRjVerifAll;
    @BindView(R.id.rb_rj_verif_cbc)
    RadioButton rbRjVerifCbc;
    @BindView(R.id.rb_rj_verif_htc)
    RadioButton rbRjVerifHtc;
    @BindView(R.id.rb_rj_verif_ivn)
    RadioButton rbRjVerifIvn;
    @BindView(R.id.rb_rj_verif_nte)
    RadioButton rbRjVerifNte;
    @BindView(R.id.rb_rj_verif_nva)
    RadioButton rbRjVerifNva;
    @BindView(R.id.rl_footer)
    RelativeLayout rlFooter;
    @BindView(R.id.btn_filter)
    com.google.android.material.button.MaterialButton btnFilter;

    String bank, rejectVerif;
    @OnClick(R.id.btn_filter) void filter(){
        getDataFilter();
        EventBus.getDefault().post(new FilterRecontest(bank, rejectVerif));
        dismiss();
    }

    @OnClick(R.id.parent_filter_bank) void getlistfilterbank(){
        imageArrowFilterRjFeriv.setVisibility(View.INVISIBLE);
        radioGroupFilterRjVerif.setVisibility(View.GONE);
        imageArrowFilterBank.setVisibility(View.VISIBLE);
        radioGroupFilterBank.setVisibility(View.VISIBLE);

    }

    @OnClick(R.id.parent_filter_rjverif) void getlistfilterrjverif(){
        imageArrowFilterBank.setVisibility(View.INVISIBLE);
        radioGroupFilterBank.setVisibility(View.GONE);
        imageArrowFilterRjFeriv.setVisibility(View.VISIBLE);
        radioGroupFilterRjVerif.setVisibility(View.VISIBLE);
    }




    public FragmentDialogFilterRecontest() {
        // Required empty public constructor
    }

    public static FragmentDialogFilterRecontest newInstance() {
        FragmentDialogFilterRecontest fragmentDialogFilterRecontest = new FragmentDialogFilterRecontest();
        return fragmentDialogFilterRecontest;
    }

    @NonNull
    @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override public void onShow(DialogInterface dialogInterface) {
                BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
                Utils.setupFullHeight(bottomSheetDialog, getContext(), 0.90);
            }
        });
        return  dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_filter_recontest, container, false);

        ButterKnife.bind(this, view);

        return view;

    }

    public void getDataFilter(){

        // find the radiobutton by returned id
        RadioButton radioButton;

        if(radioGroupFilterBank.getCheckedRadioButtonId() != -1) {
            int selectedId = radioGroupFilterBank.getCheckedRadioButtonId();
            radioButton = (RadioButton) getView().findViewById(selectedId);
            Toast.makeText(getActivity(),
                    radioButton.getText(), Toast.LENGTH_SHORT).show();
            bank = radioButton.getText().toString();

        }else{
            bank = "SEMUA";
        }

        if(radioGroupFilterRjVerif.getCheckedRadioButtonId() != -1) {
            int selectedId = radioGroupFilterRjVerif.getCheckedRadioButtonId();
            radioButton = (RadioButton) getView().findViewById(selectedId);
            Toast.makeText(getActivity(),
                    radioButton.getText(), Toast.LENGTH_SHORT).show();
            rejectVerif = radioButton.getText().toString();

        }else{
            rejectVerif = "SEMUA";
        }

    }

}
