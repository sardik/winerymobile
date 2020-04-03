package com.winery.winerymobile.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.DetailHistoryTransactionInputJurtul;
import com.winery.winerymobile.ui.DetailHistoryTransactionInputSales;
import com.winery.winerymobile.ui.DetailHistoryTransaksiInputVerif;
import com.winery.winerymobile.ui.VerifikatorTransaction.ListTransactionDetailWaitingVerif;
import com.winery.winerymobile.ui.dbhelper.SessionManagement;
import com.winery.winerymobile.ui.helper.Utils;
import com.winery.winerymobile.ui.model.HistoryCc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListHistoriCCVerif_JurtulAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private List<HistoryCc> items = new ArrayList<>();
    private List<HistoryCc> itemsFilter = new ArrayList<>();
    private Context ctx;
    private int layout_id;

    SessionManagement sessionManagement;

    public ListHistoriCCVerif_JurtulAdapter(Context context, List<HistoryCc> items, int layout_id) {
        this.items = items;
        this.itemsFilter = items;
        ctx = context;
        this.layout_id = layout_id;
        sessionManagement = new SessionManagement(ctx);

    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvDateOfBirth, tvNik, tvInsertDate;
        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            tvName = (TextView) v.findViewById(R.id.tv_name);
            tvDateOfBirth = (TextView) v.findViewById(R.id.tv_date_of_birth);
            tvNik = (TextView) v.findViewById(R.id.tv_nik);
            tvInsertDate = (TextView) v.findViewById(R.id.tv_insert_date);
            lyt_parent = (View) v.findViewById(R.id.lyt_parent);

        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(layout_id, parent, false);
        vh = new ListHistoriCCVerif_JurtulAdapter.OriginalViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        HistoryCc obj = items.get(position);
        if (holder instanceof ListHistoriCCVerif_JurtulAdapter.OriginalViewHolder) {
            ListHistoriCCVerif_JurtulAdapter.OriginalViewHolder view = (ListHistoriCCVerif_JurtulAdapter.OriginalViewHolder) holder;
            view.tvName.setText(obj.getName());
            view.tvDateOfBirth.setText(Utils.getDateThreeLetter(obj.getTanggal_lahir()));
            view.tvNik.setText(obj.getNik());
            view.tvInsertDate.setText(Utils.getDateThreeLetterTransaction(obj.getDateInput()));

            view.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // get user data from session
                    HashMap<String, String> user = sessionManagement.getUserDetails();
                    String loginAs = user.get(SessionManagement.KEY_LOGIN_AS);

                    Log.d("loginAs", "onClick: "+loginAs);

                    if(loginAs.equals("sales")){
                        Intent intent = new Intent(ctx, DetailHistoryTransactionInputSales.class);
                        intent.putExtra("param", obj.getId());
                        ctx.startActivity(intent);
                    }else if(loginAs.equals("verifikator")){
                        Intent intent = new Intent(ctx, DetailHistoryTransaksiInputVerif.class);
                        intent.putExtra("param", obj.getId());
                        ctx.startActivity(intent);
                    }else if(loginAs.equals("jurutulis")){
                        Intent intent = new Intent(ctx, DetailHistoryTransactionInputJurtul.class);
                        intent.putExtra("param", obj.getId());
                        ctx.startActivity(intent);

                    }

                    // saving state
                    sessionManagement.savetransactionID(obj.getId());


                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String charString = constraint.toString();

                if (charString.isEmpty()){
                    items = itemsFilter;
                }else{

                    List<HistoryCc> filterList = new ArrayList<>();

                    for (HistoryCc data : itemsFilter){

                        if (data.getName().toLowerCase().contains(charString)){
                            filterList.add(data);
                        }else if (data.getNik().toLowerCase().contains(charString)){
                            filterList.add(data);
                        }
                    }

                    items = filterList;

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = items;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                items = (List<HistoryCc>) results.values;
                notifyDataSetChanged();
            }
        };

    }
}

