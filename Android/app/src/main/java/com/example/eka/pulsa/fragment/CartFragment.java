package com.example.eka.pulsa.fragment;


import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.eka.pulsa.R;
import com.example.eka.pulsa.adapter.CartAdapter;
import com.example.eka.pulsa.pojo.Cart;
import com.example.eka.pulsa.util.ConstantNetwork;
import com.example.eka.pulsa.util.SessionHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private List<Cart> carts = new ArrayList<>();
    private SessionHandler handler;

    private final String TAG_CART_LIST = "TAG_CART_LIST";


    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        handler = new SessionHandler(getContext());
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_keranjang_belanja, container, false);

        recyclerView = v.findViewById(R.id.rv_keranjang_belanja);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new CartAdapter(carts);
        recyclerView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AndroidNetworking.forceCancel(TAG_CART_LIST);
        AndroidNetworking.post(ConstantNetwork.KERANJANG)
                .setTag(TAG_CART_LIST)
                .addBodyParameter("C_USERNAME", handler.getString("C_USERNAME", ""))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray array = response.optJSONArray("DataRow");
                        for (int i=0;i<array.length();i++) {
                            JSONObject item = array.optJSONObject(i);
                            Cart cart = new Cart();
                            cart.setC_ID(item.optString("C_ID"));
                            cart.setC_TYPE(item.optInt("C_TYPE"));
                            cart.setV_PHONE(item.optString("V_PHONE"));
                            cart.setN_SELL(item.optLong("nominal"));

                            if (cart.getC_TYPE() == 1) {
                                cart.setImageProduk(BitmapFactory.decodeResource(getActivity().getResources(),
                                        R.drawable.image_indosat));
                                cart.setNamaProduk("Paket Data Indosat");

                            } else if (cart.getC_TYPE() == 2) {
                                cart.setImageProduk(BitmapFactory.decodeResource(getActivity().getResources(),
                                        R.drawable.image_axis));
                                cart.setNamaProduk("Pulsa Axis");

                            } else if (cart.getC_TYPE() == 3) {
                                cart.setImageProduk(BitmapFactory.decodeResource(getActivity().getResources(),
                                        R.drawable.image_pln));
                                cart.setNamaProduk("Token Listrik");
                            }

                            carts.add(cart);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }
}
