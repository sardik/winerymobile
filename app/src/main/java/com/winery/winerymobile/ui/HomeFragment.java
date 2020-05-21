package com.winery.winerymobile.ui;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.CreditCardSubmission.BankSubmissionForm;
import com.winery.winerymobile.ui.DuplicateCheckCustomer.ChildMenuOtherFragment;
import com.winery.winerymobile.ui.JurtulTransaction.ListTransacationPendingJurtul;
import com.winery.winerymobile.ui.VerifikatorTransaction.ListTransactionWaitingVerif;
import com.winery.winerymobile.ui.dbhelper.SessionManagement;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    /** ButterKnife Code **/
    @BindView(R.id.rl_header_home)
    LinearLayout rlHeaderHome;
    @BindView(R.id.search_bar)
    androidx.cardview.widget.CardView searchBar;
    @BindView(R.id.iv_user)
    ImageView ivUser;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_position)
    TextView tvPosition;
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
    @BindView(R.id.ll_product)
    LinearLayout llProduct;
    @BindView(R.id.ll_menu_container_lainnya)
    LinearLayout llMenuContainerLainnya;
    /** ButterKnife Code **/


    @OnClick(R.id.ll_menu_kartu_kredit) void transactionCC(){
        HashMap<String, String> user = sessionManagement.getUserDetails();
        String loginAs = user.get(SessionManagement.KEY_LOGIN_AS);
        if(loginAs.equals("sales")){
            Intent intent = new Intent(getContext(), BankSubmissionForm.class);
            startActivity(intent);
        }else if(loginAs.equals("verifikator")){
            Intent intent = new Intent(getContext(), ListTransactionWaitingVerif.class);
            startActivity(intent);
        }else if(loginAs.equals("jurutulis")){
            Intent intent = new Intent(getContext(), ListTransacationPendingJurtul.class);
            startActivity(intent);
        }

    }

    @OnClick(R.id.ll_menu_container_lainnya) void getMenuOther(){
        ChildMenuOtherFragment bottomSheetFragment = new ChildMenuOtherFragment();
        bottomSheetFragment.show(getFragmentManager(), bottomSheetFragment.getTag());

    }


    SessionManagement sessionManagement;
    private Unbinder unbinder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sessionManagement = new SessionManagement(getContext());

        // get user data from session
        HashMap<String, String> user = sessionManagement.getUserDetails();
        String name = user.get(SessionManagement.KEY_NAME);
        String code = user.get(SessionManagement.KEY_SALES_CODE);
        String position = user.get(SessionManagement.KEY_POSITION);

        String loginAs = user.get(SessionManagement.KEY_LOGIN_AS);

        if(loginAs.equals("sales")){
            llMenuContainerLainnya.setVisibility(View.VISIBLE);
        }else{
            llMenuContainerLainnya.setVisibility(View.INVISIBLE);
        }

        tvName.setText(name);
        tvCode.setText(code);
        tvPosition.setText(position);
    }

    @Override
    public void onStop(){
        Log.d("onstop", "onStop: jalanHomeFragment");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d("odestroy", "onDestroy: jalanHomeFragment");
        super.onDestroy();
    }


}
