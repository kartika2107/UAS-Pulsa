package com.example.eka.pulsa.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.eka.pulsa.R;
import com.example.eka.pulsa.pojo.Cart;
import com.example.eka.pulsa.util.ConstantNetwork;
import com.example.eka.pulsa.util.SessionHandler;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private final List<Cart> carts;

    public CartAdapter(List<Cart> carts) {
        this.carts = carts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_keranjang_belanja,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Cart name = carts.get(i);
        viewHolder.image_produk.setImageBitmap(name.getImageProduk());
        viewHolder.no_hp.setText(name.getV_PHONE());
        viewHolder.nama_produk.setText(name.getNamaProduk());
        viewHolder.harga_produk.setText(String.valueOf(name.getN_SELL()));

        viewHolder.button_bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> param = new HashMap<>();
                param.put("C_ID", name.getC_ID());
                param.put("C_USERNAME", new SessionHandler(viewHolder.no_hp.getContext()).getString("C_USERNAME", ""));
                param.put("N_SELL", String.valueOf(name.getN_SELL()));
                param.put("C_STATUS", "1");

                AndroidNetworking.post(ConstantNetwork.TRANS)
                        .addBodyParameter(param)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                carts.remove(i);
                                notifyDataSetChanged();
                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });
            }
        });
        viewHolder.image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidNetworking.post(ConstantNetwork.TAMBAH_KERANJANG)
                        .addBodyParameter("C_ID", name.getC_ID())
                        .addBodyParameter("C_USERNAME", new SessionHandler(viewHolder.no_hp.getContext()).getString("C_USERNAME", ""))
                        .addBodyParameter("L_DELETE", "1")
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                carts.remove(i);
                                notifyDataSetChanged();
                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });
            }
        });

        Log.d("Keranjang Adapter", "pos " + i);
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image_produk;
        public TextView no_hp;
        public TextView nama_produk;
        public TextView harga_produk;
        public ImageView image_delete;
        public Button button_bayar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_produk = itemView.findViewById(R.id.image_produk);
            no_hp = itemView.findViewById(R.id.tv_nohp);
            nama_produk = itemView.findViewById(R.id.tv_nama_produk);
            harga_produk = itemView.findViewById(R.id.tv_harga_produk);
            image_delete = itemView.findViewById(R.id.image_delete);
            button_bayar = itemView.findViewById(R.id.btn_bayar);
        }
    }
}
