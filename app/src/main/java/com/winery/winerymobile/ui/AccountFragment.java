package com.winery.winerymobile.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.CreditCardSubmission.DialogSuccess;

import java.security.PrivateKey;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    /** ButterKnife Code **/
    @BindView(R.id.ll_section1)
    LinearLayout llSection1;
    @BindView(R.id.rl_parent_change_password)
    RelativeLayout rlParentChangePassword;
    @BindView(R.id.ll_section2)
    LinearLayout llSection2;
    @BindView(R.id.rl_parent_history_order)
    RelativeLayout rlParentHistoryOrder;
    @BindView(R.id.rl_parent_pay_slip)
    RelativeLayout rlParentPaySlip;
    @BindView(R.id.ll_section3)
    LinearLayout llSection3;
    @BindView(R.id.rl_logout)
    RelativeLayout rlLogout;
    /** ButterKnife Code **/

    private Unbinder unbinder;

    @OnClick(R.id.rl_logout) void logout(){
        DialogLogout bottomSheetFragment = new DialogLogout();
        bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());
    }

    @OnClick(R.id.rl_parent_change_password) void changePassword(){
        UpdatePassword bottomSheetFragment = new UpdatePassword();
        bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());
    }

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
