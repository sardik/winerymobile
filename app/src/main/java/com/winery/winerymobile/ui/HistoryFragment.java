package com.winery.winerymobile.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.winery.winerymobile.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    /** ButterKnife Code **/
    @BindView(R.id.ll_menu_kartu_kredit)
    LinearLayout llMenuKartuKredit;
    @BindView(R.id.ll_menu_kartu_tambahan)
    LinearLayout llMenuKartuTambahan;
    @BindView(R.id.ll_menu_kta)
    LinearLayout llMenuKta;
    /** ButterKnife Code **/

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

}
