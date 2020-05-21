package com.winery.winerymobile.ui.JurtulTransaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.APIhelper.BaseApiService;
import com.winery.winerymobile.ui.APIhelper.UtilsApi;
import com.winery.winerymobile.ui.DetailHistoryTransaksiInputVerif;
import com.winery.winerymobile.ui.VerifikatorTransaction.ListTransactionWaitingVerif;
import com.winery.winerymobile.ui.adapter.LIstHistoriCCAdapter;
import com.winery.winerymobile.ui.dbhelper.SessionManagement;
import com.winery.winerymobile.ui.model.HistoryCc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTransacationPendingJurtul extends AppCompatActivity {

    /**
     * ButterKnife Code
     **/
    @BindView(R.id.ll_parent_date)
    LinearLayout llParentDate;
    @BindView(R.id.et_start_date)
    com.google.android.material.textfield.TextInputEditText etStartDate;
    @BindView(R.id.et_end_date)
    com.google.android.material.textfield.TextInputEditText etEndDate;
    @BindView(R.id.btn_filter)
    LinearLayout btnFilter;
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

    @OnClick(R.id.et_start_date)
    void getcalendar() {

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
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


    @OnClick(R.id.et_end_date)
    void getcalendarend() {

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
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

    @OnClick(R.id.btn_search)
    void filterdata() {

        if (etStartDate.getText().toString().equals("") || etStartDate.getText().toString().equals(null)) {
            etStartDate.setError("Tanggal Start tidak boleh kosong");

        } else if (etEndDate.getText().toString().equals("") || etEndDate.getText().toString().equals(null)) {
            etEndDate.setError("Tanggal End date tidak boleh kosong");

        } else {

            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateBeforeString = etStartDate.getText().toString();
            String dateAfterString = etEndDate.getText().toString();

            float daysBetween = 0;
            try {
                Date dateBefore = myFormat.parse(dateBeforeString);
                Date dateAfter = myFormat.parse(dateAfterString);
                long difference = dateAfter.getTime() - dateBefore.getTime();
                daysBetween = (difference / (1000 * 60 * 60 * 24));
                System.out.println("Number of Days between dates: " + daysBetween);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (daysBetween > 14) {
                snackBarInfo();
            } else {
                requestGetHistoryPendingJurtul(etStartDate.getText().toString(), etEndDate.getText().toString());
            }
        }
    }

    List<HistoryCc> historyCcList;
    LIstHistoriCCAdapter lIstHistoriCCAdapter;
    SessionManagement sessionManagement;
    BaseApiService mApiService;
    ProgressDialog loading;
    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_transacation_pending_jurtul);

        ButterKnife.bind(this);

        sessionManagement = new SessionManagement(this);
        mApiService = UtilsApi.getAPIService();
        myCalendar = Calendar.getInstance();

        initToolbar();

        requestGetHistoryPendingJurtul("", "");

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                lIstHistoriCCAdapter.getFilter().filter(s);

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

                if (etStartDate.getText().length() != 0) {
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

                if (etEndDate.getText().length() != 0) {
                    etEndDate.setError(null);
                }

            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setNavigationIcon(R.drawable.ic_nav_white);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void requestGetHistoryPendingJurtul(String startDate, String endDate) {
        loading = ProgressDialog.show(ListTransacationPendingJurtul.this, null, "Harap Tunggu...", true, false);
        HashMap<String, String> user = sessionManagement.getUserDetails();
        String code = user.get(SessionManagement.KEY_SALES_CODE);

        mApiService.getListdataPendingJurtul(startDate, endDate, code)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {

                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getInt("status") == 200) {

                                    JSONArray m_jsonData = jsonRESULTS.getJSONArray("data");
//                                        JSONArray arr_jsonArticle = m_jsonData.getJSONArray("articles");

                                    historyCcList = new ArrayList<>();
                                    for (int i = 0; i < m_jsonData.length(); i++) {
                                        JSONObject jsonObject = m_jsonData.getJSONObject(i);

                                        String id = jsonObject.getString("id");
                                        String dateInput = jsonObject.getString("date_input");
                                        String name = jsonObject.getString("name");
                                        String nik = jsonObject.getString("nik");
                                        String tanggalLahir = jsonObject.getString("tanggal_lahir");


                                        historyCcList.add(new HistoryCc(id, dateInput, name, nik, tanggalLahir));
                                    }
                                    loading.dismiss();

                                    if (m_jsonData.length() == 0) {
                                        tvNoData.setVisibility(View.VISIBLE);
                                    } else {
                                        tvNoData.setVisibility(View.GONE);
                                        rvHistoryCc.setLayoutManager(new LinearLayoutManager(ListTransacationPendingJurtul.this, LinearLayoutManager.VERTICAL, false));
                                        lIstHistoriCCAdapter = new LIstHistoriCCAdapter(ListTransacationPendingJurtul.this, historyCcList, R.layout.adapter_history_list);
                                        rvHistoryCc.setAdapter(lIstHistoriCCAdapter);
                                    }


                                } else {
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(ListTransacationPendingJurtul.this, error_message, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(ListTransacationPendingJurtul.this, error_message, Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                });
    }

    public void snackBarInfo() {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.btn_filter), "range maksimal filter 14 hari", Snackbar.LENGTH_SHORT)
                .setAction("Action", null);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        snackbar.show();
    }

    @Override
    protected void onStop(){
        Log.d("onstop", "onStop: jalanbanksubmissionform");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("odestroy", "onDestroy: jalanbanksubmissionform");
        super.onDestroy();
    }
}