package com.winery.winerymobile.ui;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.gson.JsonArray;
import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.APIhelper.BaseApiService;
import com.winery.winerymobile.ui.APIhelper.UtilsApi;
import com.winery.winerymobile.ui.Recontest.FragmentDialogFilterRecontest;
import com.winery.winerymobile.ui.adapter.LIstHistoriCCAdapter;
import com.winery.winerymobile.ui.adapter.ListHistoriCCVerif_JurtulAdapter;
import com.winery.winerymobile.ui.adapter.MessageRecontestAdapter;
import com.winery.winerymobile.ui.dbhelper.SessionManagement;
import com.winery.winerymobile.ui.model.FilterRecontest;
import com.winery.winerymobile.ui.model.HistoryCc;
import com.winery.winerymobile.ui.model.messageRecontest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListMessageRecontest extends Fragment {

    @BindView(R.id.appBar)
    com.google.android.material.appbar.AppBarLayout appBar;
    @BindView(R.id.toolbar_main)
    com.google.android.material.appbar.MaterialToolbar toolbarMain;
    @BindView(R.id.toolbar_title)
    ImageView toolbarTitle;
    @BindView(R.id.btn_filter)
    com.google.android.material.button.MaterialButton btnFilter;
    @BindView(R.id.btn_sort)
    com.google.android.material.button.MaterialButton btnSort;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.rv_message_recontest)
    androidx.recyclerview.widget.RecyclerView rvMessageRecontest;


    @OnClick(R.id.btn_filter) void showFilterFragment(){
        FragmentDialogFilterRecontest fragmentDialogFilterRecontest = new FragmentDialogFilterRecontest();
        fragmentDialogFilterRecontest.show(getActivity().getSupportFragmentManager(), fragmentDialogFilterRecontest.getTag());
    }

    @OnClick(R.id.btn_sort) void showSort(){
        DialogSortData();
    }

    public ListMessageRecontest() {
        // Required empty public constructor
    }

    List<messageRecontest> messageRecontests;
    MessageRecontestAdapter messageRecontestAdapter;
    ListHistoriCCVerif_JurtulAdapter listHistoriCCVerif_jurtulAdapter;
    SessionManagement sessionManagement;
    BaseApiService mApiService;
    ProgressDialog loading;
    Calendar myCalendar;

    String bankList;
    String bankName = "";

    String paramBank, paramStatus;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResultReceived(FilterRecontest filterRecontest){
       paramBank = filterRecontest.getBank();
       paramStatus = filterRecontest.getRjVerif();

       if(paramBank.equals("") || paramBank.equals(null)){
           paramBank = "SEMUA";
       }

       if(paramStatus.equals("") || paramStatus.equals(null)){
           paramStatus="SEMUA";
       }

       // get data with param bank & paramstatus
        requestGetMessageRecontest(paramBank, paramStatus);
    }

    @Override public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_message_recontest, container, false);

        ButterKnife.bind(this, view);

        sessionManagement = new SessionManagement(getContext());
        mApiService = UtilsApi.getAPIService();
        myCalendar = Calendar.getInstance();

        requestGetMessageRecontest("SEMUA", "SEMUA");

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void requestGetMessageRecontest(String paramBank, String paramStatus){
        loading = ProgressDialog.show(getContext(), null, "Harap Tunggu...", true, false);
        HashMap<String, String> user = sessionManagement.getUserDetails();
        String code = user.get(SessionManagement.KEY_SALES_CODE);

        mApiService.getListMessageRecontest(code,paramBank, paramStatus,"DESC")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){

                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getInt("status") == 200){

                                    String statusBanks = jsonRESULTS.getString("search");
                                    JSONArray m_jsonData = jsonRESULTS.getJSONArray("data");
                                    messageRecontests = new ArrayList<>();
                                    for(int i = 0; i < m_jsonData.length(); i++) {
                                        JSONObject jsonObject = m_jsonData.getJSONObject(i);

                                        String id = jsonObject.getString("id");
                                        String nik = jsonObject.getString("nik");
                                        String name = jsonObject.getString("nama");
                                        String tanggal = jsonObject.getString("tanggal");
                                        String total_bank = jsonObject.getString("total_bank");
                                        String bankss = jsonObject.getString("bank_name");
                                        Log.d("json", "data "+bankList);


                                        messageRecontests.add(new messageRecontest(id, nik, name, tanggal,
                                                total_bank,bankss,statusBanks));
                                    }
                                    loading.dismiss();

                                    if(m_jsonData.length() == 0){
                                        tvNoData.setVisibility(View.VISIBLE);
                                        rvMessageRecontest.setVisibility(View.GONE);
                                    }else{
                                        tvNoData.setVisibility(View.GONE);
                                        rvMessageRecontest.setVisibility(View.VISIBLE);
                                        rvMessageRecontest.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                                        messageRecontestAdapter = new MessageRecontestAdapter(getActivity(), messageRecontests, R.layout.adapter_item_message_recontest);
                                        rvMessageRecontest.setAdapter(messageRecontestAdapter);
                                    }
                                } else {
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(getContext(), error_message, Toast.LENGTH_SHORT).show();
                                    loading.dismiss();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        String error_message = "server error silahkan coba lagi";
                        Toast.makeText(getContext(), error_message, Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                });
    }


    private void DialogSortData() {

//        AlertDialog dialogs;
        LayoutInflater inflater;
        View dialogView;
        RadioGroup radioGroupSort;
        RadioButton radioButtonSortAsc, radioButtonSortDesc, radioButtonSortAz, radioButtonSortZa;

        AlertDialog.Builder dialogs = new AlertDialog.Builder(getContext());


//        dialogs = new AlertDialog.Builder(getContext());
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_sort_list_data_recontest, null);
        dialogs.setView(dialogView);
        dialogs.setCancelable(true);

        final AlertDialog dialog = dialogs.create();

        MaterialButton btnSearch;
        ContentLoadingProgressBar loading;
        ImageView ivClose;


        ivClose = (ImageView) dialogView.findViewById(R.id.iv_close);
        radioGroupSort = (RadioGroup) dialogView.findViewById(R.id.radio_group_sort);
        radioButtonSortAsc = (RadioButton) dialogView.findViewById(R.id.rb_sort_asc);
        radioButtonSortDesc = (RadioButton) dialogView.findViewById(R.id.rb_sort_desc);
        radioButtonSortAz = (RadioButton) dialogView.findViewById(R.id.rb_sort_az);
        radioButtonSortZa = (RadioButton) dialogView.findViewById(R.id.rb_sort_za);

        btnSearch = (MaterialButton) dialogView.findViewById(R.id.btn_sort);
        loading = (ContentLoadingProgressBar) dialogView.findViewById(R.id.content_loading);



        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioButtonSortAsc.isChecked()){
                    messageRecontestAdapter.notifyDataSetChanged();
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    linearLayoutManager.setReverseLayout(false);
                    linearLayoutManager.setStackFromEnd(false);
                    rvMessageRecontest.setLayoutManager(linearLayoutManager);
                    btnSort.setText("Urutkan : Terlama");
                }else if(radioButtonSortDesc.isChecked()){
//                    rvMessageRecontest.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
                    messageRecontestAdapter.notifyDataSetChanged();
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    linearLayoutManager.setReverseLayout(true);
                    linearLayoutManager.setStackFromEnd(true);
                    rvMessageRecontest.setLayoutManager(linearLayoutManager);
                    btnSort.setText("Urutkan : Terbaru");

                }else if(radioButtonSortAz.isChecked()){
                    Collections.sort(messageRecontests, new Comparator<messageRecontest>() {
                        @Override
                        public int compare(messageRecontest lhs, messageRecontest rhs) {
                            return lhs.getName().compareTo(rhs.getName());
                        }
                    });
                    messageRecontestAdapter.notifyDataSetChanged();
                    btnSort.setText("Urutkan : A - Z");

                }else if(radioButtonSortZa.isChecked()){
                    Collections.sort(messageRecontests, new Comparator<messageRecontest>() {
                        @Override
                        public int compare(messageRecontest lhs, messageRecontest rhs) {
                            return rhs.getName().compareTo(lhs.getName());
                        }
                    });
                    messageRecontestAdapter.notifyDataSetChanged();
                    btnSort.setText("Urutkan : Z - A");

                }

                    dialog.dismiss();
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
