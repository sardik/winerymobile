package com.winery.winerymobile.ui;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.snackbar.Snackbar;
import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.APIhelper.BaseApiService;
import com.winery.winerymobile.ui.APIhelper.UtilsApi;
import com.winery.winerymobile.ui.CreditCardSubmission.BankSubmissionForm;
import com.winery.winerymobile.ui.CreditCardSubmission.FormUploadDocumentSelfie;
import com.winery.winerymobile.ui.JurtulTransaction.ListTransacationPendingJurtul;
import com.winery.winerymobile.ui.VerifikatorTransaction.ListTransactionDetailWaitingVerif;
import com.winery.winerymobile.ui.VerifikatorTransaction.ListTransactionWaitingVerif;
import com.winery.winerymobile.ui.adapter.LIstHistoriCCAdapter;
import com.winery.winerymobile.ui.adapter.ListHistoriCCVerif_JurtulAdapter;
import com.winery.winerymobile.ui.dbhelper.SessionManagement;
import com.winery.winerymobile.ui.helper.Utils;
import com.winery.winerymobile.ui.model.HistoryCc;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    /** ButterKnife Code **/
    @BindView(R.id.appBar)
    com.google.android.material.appbar.AppBarLayout appBar;
    @BindView(R.id.toolbar_main)
    com.google.android.material.appbar.MaterialToolbar toolbarMain;
    @BindView(R.id.toolbar_title)
    ImageView toolbarTitle;
    @BindView(R.id.tab_layout)
    com.google.android.material.tabs.TabLayout tabLayout;
    @BindView(R.id.view_pager)
    androidx.viewpager.widget.ViewPager viewPager;
    /** ButterKnife Code **/

    SectionsPagerAdapter adapter;
    private Unbinder unbinder;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        ButterKnife.bind(this, view);

        initComponent();
        return view;
    }

    private void initComponent() {
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
//        LinearLayout tabStrip = ((LinearLayout)tabLayout.getChildAt(0));
//        for(int i = 0; i < tabStrip.getChildCount(); i++) {
////            viewPager.disableScroll(true);
//            tabStrip.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    return true;
//                }
//            });
//        }
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new SectionsPagerAdapter(getChildFragmentManager());
        adapter.addFragment(ListHistoryCC.newInstance(1), "Kartu Kredit");
        adapter.addFragment(BlankFragment.newInstance(2), "Kartu tambahan");
        adapter.addFragment(BlankFragment.newInstance(3), "KTA");
        viewPager.setAdapter(adapter);

    };

    //////////////////////////////// setup section pager ////////////////////////////////////

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }

    //////////////////////////////////  blank fragment /////////////////////////////////////////

    public static class BlankFragment extends Fragment {


        public BlankFragment() {
        }

        public static BlankFragment newInstance(int sectionNumber) {
            BlankFragment fragment = new BlankFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

            return rootView;
        }
    }

    ////////////////////////////////// fragment item ///////////////////////////////////////////
    // section 1
    public static class ListHistoryCC extends Fragment {

        /**
         * ButterKnife Code
         **/
        @BindView(R.id.et_start_date)
        com.google.android.material.textfield.TextInputEditText etStartDate;
        @BindView(R.id.et_end_date)
        com.google.android.material.textfield.TextInputEditText etEndDate;
        @BindView(R.id.btn_search)
        ImageButton btnSearch;
        @BindView(R.id.et_search)
        com.google.android.material.textfield.TextInputEditText etSearch;
        @BindView(R.id.tv_no_data)
        TextView tvNoData;
        @BindView(R.id.rv_history_cc)
        androidx.recyclerview.widget.RecyclerView rvHistoryCc;

        /**
         * ButterKnife Code
         **/

        @OnClick(R.id.et_start_date) void getcalendar() {

            new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, month);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    String formatTanggal = "yyyy-MM-dd";
                    SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                    etStartDate.setText(sdf.format(myCalendar.getTime()));
                    }
                },
                    myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }


        @OnClick(R.id.et_end_date) void getcalendarend() {

            new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, month);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    String formatTanggal = "yyyy-MM-dd";
                    SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                    etEndDate.setText(sdf.format(myCalendar.getTime()));
                }
            },
                    myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }

        @OnClick(R.id.btn_search) void filterdata() {

            if(etStartDate.getText().toString().equals("") || etStartDate.getText().toString().equals(null)){
                etStartDate.setError("Tanggal Start tidak boleh kosong");

            }else if(etEndDate.getText().toString().equals("") || etEndDate.getText().toString().equals(null)){
                etEndDate.setError("Tanggal End date tidak boleh kosong");

            }else{

                SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateBeforeString = etStartDate.getText().toString();
                String dateAfterString = etEndDate.getText().toString();

                float daysBetween = 0;
                try {
                    Date dateBefore = myFormat.parse(dateBeforeString);
                    Date dateAfter = myFormat.parse(dateAfterString);
                    long difference = dateAfter.getTime() - dateBefore.getTime();
                    daysBetween = (difference / (1000*60*60*24));
                    System.out.println("Number of Days between dates: "+daysBetween);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(daysBetween > 14){
                    snackBarInfo();
                }else{
                    HashMap<String, String> user = sessionManagement.getUserDetails();
                    String loginAs = user.get(SessionManagement.KEY_LOGIN_AS);

                    if(loginAs.equals("sales")){
                        requestGetHistoryCc(etStartDate.getText().toString(), etEndDate.getText().toString());
                    }else if(loginAs.equals("verifikator")){
                        requestGetHistoryInputVerif(etStartDate.getText().toString(), etEndDate.getText().toString());
                    }else if(loginAs.equals("jurutulis")){
                        requestGetHistoryInputJurtul(etStartDate.getText().toString(), etEndDate.getText().toString());

                    }
                }
            }
        }


        List<HistoryCc> historyCcList;
        LIstHistoriCCAdapter lIstHistoriCCAdapter;
        ListHistoriCCVerif_JurtulAdapter listHistoriCCVerif_jurtulAdapter;
        SessionManagement sessionManagement;
        BaseApiService mApiService;
        ProgressDialog loading;
        Calendar myCalendar;


        public ListHistoryCC() {
        }

        public static ListHistoryCC newInstance(int sectionNumber) {
            ListHistoryCC fragment = new ListHistoryCC();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_list_history_cc, container, false);
            ButterKnife.bind(this,rootView);

            return rootView;
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            sessionManagement = new SessionManagement(getContext());
            mApiService = UtilsApi.getAPIService();
            myCalendar = Calendar.getInstance();

            HashMap<String, String> user = sessionManagement.getUserDetails();
            String loginAs = user.get(SessionManagement.KEY_LOGIN_AS);

            if(loginAs.equals("sales")){
                requestGetHistoryCc("", "");
            }else if(loginAs.equals("verifikator")){
                requestGetHistoryInputVerif("", "");
            }else if(loginAs.equals("jurutulis")){
                requestGetHistoryInputJurtul("","");
            }

            etSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                }

                @Override
                public void afterTextChanged(Editable s) {
                    HashMap<String, String> user = sessionManagement.getUserDetails();
                    String loginAs = user.get(SessionManagement.KEY_LOGIN_AS);

                    if(loginAs.equals("sales")){
                        lIstHistoriCCAdapter.getFilter().filter(s);
                    }else if(loginAs.equals("verifikator")){
                        listHistoriCCVerif_jurtulAdapter.getFilter().filter(s);
                    }else if(loginAs.equals("jurutulis")){
                        listHistoriCCVerif_jurtulAdapter.getFilter().filter(s);
                    }



                }
            });

            etStartDate.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                }

                @Override
                public void afterTextChanged(Editable s) {

                    if(etStartDate.getText().length() != 0){
                        etStartDate.setError(null);
                    }

                }
            });

            etEndDate.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                }

                @Override
                public void afterTextChanged(Editable s) {

                    if(etEndDate.getText().length() != 0){
                        etEndDate.setError(null);
                    }

                }
            });
        }
        // list history input sales cc
        private void requestGetHistoryCc(String startDate, String endDate ){
            loading = ProgressDialog.show(getContext(), null, "Harap Tunggu...", true, false);
            HashMap<String, String> user = sessionManagement.getUserDetails();
            String code = user.get(SessionManagement.KEY_SALES_CODE);

            Log.d("id", "requestGetHistoryCc: "+code);

            mApiService.getListHistoryCc(startDate, endDate,code)
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()){

                                try {
                                    JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                    if (jsonRESULTS.getInt("status") == 200){

                                        JSONArray m_jsonData = jsonRESULTS.getJSONArray("data");
//                                        JSONArray arr_jsonArticle = m_jsonData.getJSONArray("articles");

                                        historyCcList = new ArrayList<>();
                                        for(int i = 0; i < m_jsonData.length(); i++) {
                                            JSONObject jsonObject = m_jsonData.getJSONObject(i);

                                            String id = jsonObject.getString("id");
                                            String dateInput = jsonObject.getString("date_input");
                                            String name = jsonObject.getString("name");
                                            String nik = jsonObject.getString("nik");
                                            String tanggalLahir = jsonObject.getString("tanggal_lahir");


                                            historyCcList.add(new HistoryCc(id, dateInput, name, nik, tanggalLahir));
                                        }
                                        loading.dismiss();

                                        if(m_jsonData.length() == 0){
                                            tvNoData.setVisibility(View.VISIBLE);
                                        }else{
                                            tvNoData.setVisibility(View.GONE);
                                            rvHistoryCc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                                            lIstHistoriCCAdapter = new LIstHistoriCCAdapter(getActivity(), historyCcList, R.layout.adapter_history_list);
                                            rvHistoryCc.setAdapter(lIstHistoriCCAdapter);
                                        }



                                    } else {
                                        String error_message = jsonRESULTS.getString("message");
                                        Toast.makeText(getContext(), error_message, Toast.LENGTH_SHORT).show();
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

        // list history input verif
        private void requestGetHistoryInputVerif(String startDate, String endDate ){
            loading = ProgressDialog.show(getContext(), null, "Harap Tunggu...", true, false);
            HashMap<String, String> user = sessionManagement.getUserDetails();
            String code = user.get(SessionManagement.KEY_SALES_CODE);

            mApiService.getListVerifyHistoryInputVerif(startDate, endDate,code)
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()){

                                try {
                                    JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                    if (jsonRESULTS.getInt("status") == 200){

                                        JSONArray m_jsonData = jsonRESULTS.getJSONArray("data");
//                                        JSONArray arr_jsonArticle = m_jsonData.getJSONArray("articles");

                                        historyCcList = new ArrayList<>();
                                        for(int i = 0; i < m_jsonData.length(); i++) {
                                            JSONObject jsonObject = m_jsonData.getJSONObject(i);

                                            String id = jsonObject.getString("id");
                                            String dateInput = jsonObject.getString("tanggal_verif");
                                            String name = jsonObject.getString("name");
                                            String nik = jsonObject.getString("nik");
                                            String tanggalLahir = jsonObject.getString("tanggal_lahir");


                                            historyCcList.add(new HistoryCc(id, dateInput, name, nik, tanggalLahir));
                                        }
                                        loading.dismiss();

                                        if(m_jsonData.length() == 0){
                                            tvNoData.setVisibility(View.VISIBLE);
                                        }else{
                                            tvNoData.setVisibility(View.GONE);
                                            rvHistoryCc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                                            listHistoriCCVerif_jurtulAdapter = new ListHistoriCCVerif_JurtulAdapter(getActivity(), historyCcList, R.layout.adapter_history_list);
                                            rvHistoryCc.setAdapter(listHistoriCCVerif_jurtulAdapter);
                                        }



                                    } else {
                                        String error_message = jsonRESULTS.getString("message");
                                        Toast.makeText(getContext(), error_message, Toast.LENGTH_SHORT).show();
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

        // list history input jurtul
        private void requestGetHistoryInputJurtul(String startDate, String endDate ){
            loading = ProgressDialog.show(getContext(), null, "Harap Tunggu...", true, false);
            HashMap<String, String> user = sessionManagement.getUserDetails();
            String code = user.get(SessionManagement.KEY_SALES_CODE);

            mApiService.getListJurtulHistoryInputJurtul(startDate, endDate,code)
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()){

                                try {
                                    JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                    if (jsonRESULTS.getInt("status") == 200){

                                        JSONArray m_jsonData = jsonRESULTS.getJSONArray("data");
//                                        JSONArray arr_jsonArticle = m_jsonData.getJSONArray("articles");

                                        historyCcList = new ArrayList<>();
                                        for(int i = 0; i < m_jsonData.length(); i++) {
                                            JSONObject jsonObject = m_jsonData.getJSONObject(i);

                                            String id = jsonObject.getString("id");
                                            String dateInput = jsonObject.getString("date_input");
                                            String name = jsonObject.getString("name");
                                            String nik = jsonObject.getString("nik");
                                            String tanggalLahir = jsonObject.getString("tanggal_lahir");


                                            historyCcList.add(new HistoryCc(id, dateInput, name, nik, tanggalLahir));
                                        }
                                        loading.dismiss();

                                        if(m_jsonData.length() == 0){
                                            tvNoData.setVisibility(View.VISIBLE);
                                        }else{
                                            tvNoData.setVisibility(View.GONE);
                                            rvHistoryCc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                                            listHistoriCCVerif_jurtulAdapter = new ListHistoriCCVerif_JurtulAdapter(getActivity(), historyCcList, R.layout.adapter_history_list);
                                            rvHistoryCc.setAdapter(listHistoriCCVerif_jurtulAdapter);
                                        }



                                    } else {
                                        String error_message = jsonRESULTS.getString("message");
                                        Toast.makeText(getContext(), error_message, Toast.LENGTH_SHORT).show();
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


        public void snackBarInfo(){
            Snackbar snackbar = Snackbar.make(getView().findViewById(R.id.btn_filter), "range maksimal filter 14 hari", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            snackbar.show();
        }

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
