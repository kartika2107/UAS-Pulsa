package com.example.eka.pulsa.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eka.pulsa.R;
import com.example.eka.pulsa.pojo.History;

import java.text.SimpleDateFormat;
import java.util.List;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private final List<History> histories;

    public HistoryAdapter(List<History> histories) {
        this.histories = histories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_history_transaksi, viewGroup, false);
        ViewHolder v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final History name = histories.get(i);

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        viewHolder.no_transaksi.setText(name.getN_TRANSAKSI());
        viewHolder.kode.setText(name.getC_ID());
        viewHolder.tanggal_transaksi.setText(format.format(name.getD_TIME()));
        viewHolder.harga_transaksi.setText(String.valueOf(name.getN_SELL()));
        viewHolder.status_sukses.setText(name.isC_STATUS() ? "Sukses" : "Gagal");
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView no_transaksi;
        public TextView kode;
        public TextView tanggal_transaksi;
        public TextView harga_transaksi;
        public TextView status_sukses;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            no_transaksi = itemView.findViewById(R.id.tv_no_transaksi);
            kode = itemView.findViewById(R.id.tv_kode);
            tanggal_transaksi = itemView.findViewById(R.id.tv_tanggal);
            harga_transaksi = itemView.findViewById(R.id.tv_harga_transaksi);
            status_sukses = itemView.findViewById(R.id.tv_sukses);
        }
    }
}
