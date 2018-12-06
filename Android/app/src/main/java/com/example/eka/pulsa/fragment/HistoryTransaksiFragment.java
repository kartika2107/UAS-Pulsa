package com.example.eka.pulsa.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.eka.pulsa.R;
import com.example.eka.pulsa.adapter.HistoryAdapter;
import com.example.eka.pulsa.pojo.History;
import com.example.eka.pulsa.util.ConstantNetwork;
import com.example.eka.pulsa.util.SessionHandler;


import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryTransaksiFragment extends Fragment {

    private final String TAG_LIST_HISTORY = "TAG_LIST_HISTORY";

    private SessionHandler handler;

    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private List<History> histories = new ArrayList<>();


    public HistoryTransaksiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        handler = new SessionHandler(container.getContext());
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history_transaksi, container, false);

        recyclerView = v.findViewById(R.id.rv_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new HistoryAdapter(histories);
        recyclerView.setAdapter(adapter);


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AndroidNetworking.forceCancel(TAG_LIST_HISTORY);

        AndroidNetworking.post(ConstantNetwork.HISTORY)
                .addBodyParameter("C_USERNAME",  handler.getString("C_USERNAME", ""))
                .setTag(TAG_LIST_HISTORY)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray array = response.optJSONArray("DataRow");
                        for (int i=0;i<array.length();i++) {
                            JSONObject item = array.optJSONObject(i);

                            History history = new History();
                            history.setN_TRANSAKSI(item.optString("N_TRANSAKSI"));
                            history.setC_ID(item.optString("C_ID"));
                            history.setC_STATUS(item.optInt("C_STATUS") == 1);
                            history.setV_PHONE(item.optString("V_PHONE"));
                            history.setN_SELL(item.optLong("N_SELL"));
                            history.setV_TOKEN_ID(item.optString("V_TOKEN_ID"));

                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            try {
                                history.setD_TIME(format.parse(item.optString("D_TIME")));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            histories.add(history);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        AndroidNetworking.forceCancel(TAG_LIST_HISTORY);
    }
}
