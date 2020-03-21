package com.winery.winerymobile.ui;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.CreditCardSubmission.BankSubmissionForm;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    /** ButterKnife Code **/
    @BindView(R.id.ll_menu_kartu_kredit)
    LinearLayout llMenuKartuKredit;
    @BindView(R.id.ll_menu_kartu_tambahan)
    LinearLayout llMenuKartuTambahan;
    @BindView(R.id.ll_menu_kta)
    LinearLayout llMenuKta;
    @BindView(R.id.ll_report_kartu_kredit)
    LinearLayout llReportKartuKredit;
    @BindView(R.id.ll_report_kartu_tambahan)
    LinearLayout llReportKartuTambahan;
    @BindView(R.id.ll_report_kta)
    LinearLayout llReportKta;
    /** ButterKnife Code **/


    @OnClick(R.id.ll_menu_kartu_kredit) void transactionCC(){
        Intent intent = new Intent(getContext(), BankSubmissionForm.class);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
