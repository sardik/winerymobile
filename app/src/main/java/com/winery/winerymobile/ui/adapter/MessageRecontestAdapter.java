package com.winery.winerymobile.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.Recontest.ChangeDocumentRecontestActivity;
import com.winery.winerymobile.ui.Recontest.DetailDataCustomerRecontestActivity;
import com.winery.winerymobile.ui.model.messageRecontest;

import java.util.ArrayList;
import java.util.List;

public class MessageRecontestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<messageRecontest> items = new ArrayList<>();
    private Context ctx;
    private int layout_id;

    public MessageRecontestAdapter(Context context, List<messageRecontest> items, int layout_id) {
        this.items = items;
        ctx = context;
        this.layout_id = layout_id;

    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvDate, tvTotalBank, tvStatusRejects, tvBanklist;
        public View lyt_parent;
        public MaterialButton btnRecontest;

        public OriginalViewHolder(View v) {
            super(v);
            tvName = (TextView) v.findViewById(R.id.tv_name);
            tvDate = (TextView) v.findViewById(R.id.tv_date);
            tvTotalBank = (TextView) v.findViewById(R.id.total_bank);
            tvStatusRejects = (TextView) v.findViewById(R.id.tv_status_reject);
            tvBanklist = (TextView) v.findViewById(R.id.bank_list);
            lyt_parent = (View) v.findViewById(R.id.lyt_parent);

            btnRecontest = (MaterialButton) v.findViewById(R.id.btn_recontest);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(layout_id, parent, false);
        vh = new MessageRecontestAdapter.OriginalViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        messageRecontest obj = items.get(position);
        if (holder instanceof MessageRecontestAdapter.OriginalViewHolder) {
            MessageRecontestAdapter.OriginalViewHolder view = (MessageRecontestAdapter.OriginalViewHolder) holder;
            view.tvName.setText(obj.getName());
            view.tvDate.setText(obj.getTanggal());
            view.tvBanklist.setText(obj.getBankss());
            view.tvStatusRejects.setText(obj.getStatusBanks());
            view.tvTotalBank.setText(obj.getTotal_banks()+" Bank");

            view.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ctx, DetailDataCustomerRecontestActivity.class);
                    intent.putExtra("idtransaction", obj.getId());
                    intent.putExtra("statusrjverif", obj.getStatusBanks());
                    ctx.startActivity(intent);
                    }

            });

            view.btnRecontest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ctx, ChangeDocumentRecontestActivity.class);
                    intent.putExtra("param", obj.getId());
                    intent.putExtra("status", "direct");
                    ctx.startActivity(intent);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
