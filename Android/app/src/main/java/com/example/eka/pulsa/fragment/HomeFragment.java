package com.example.eka.pulsa.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.eka.pulsa.pojo.PaketData;
import com.example.eka.pulsa.pojo.Pulsa;
import com.example.eka.pulsa.pojo.Token;
import com.example.eka.pulsa.slider.FragmentSlider;
import com.example.eka.pulsa.R;
import com.example.eka.pulsa.slider.SliderIndicator;
import com.example.eka.pulsa.slider.SliderPagerAdapter;
import com.example.eka.pulsa.slider.SliderView;
import com.example.eka.pulsa.util.ConstantNetwork;
import com.example.eka.pulsa.util.SessionHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    public static final String TAG_PULSA = "TAG_PULSA";
    public static final String TAG_PAKET_DATA = "TAG_PAKET_DATA";
    public static final String TAG_TOKEN = "TAG_TOKEN";

    private SessionHandler handler;

    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;

    private SliderView sliderView;
    private LinearLayout mLinearLayout;

    private Button buttonPaketData;
    private Button buttonPulsa;
    private Button buttonToken;


    private Spinner spinnerPaketData;
    private Spinner spinnerPulsa;
    private Spinner spinnerToken;


    private List<PaketData> paketDataList = new ArrayList<>();
    private List<Pulsa> pulsaList = new ArrayList<>();
    private List<Token> tokenList = new ArrayList<>();

    private ArrayAdapter<PaketData> paketDataAdapter;
    private ArrayAdapter<Pulsa> pulsaAdapter;
    private ArrayAdapter<Token> tokenAdapter;


    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        handler = new SessionHandler(getContext());
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_home, container, false);

        sliderView = v.findViewById(R.id.sliderView);
        mLinearLayout = v.findViewById(R.id.pagesContainer);

        spinnerPaketData = v.findViewById(R.id.spinner_paket_data);
        spinnerPulsa = v.findViewById(R.id.spinner_pulsa);
        spinnerToken = v.findViewById(R.id.spinner_token);

        buttonPaketData = v.findViewById(R.id.btn_spinner_paket_data);
        buttonPulsa = v.findViewById(R.id.btn_spinner_pulsa);
        buttonToken = v.findViewById(R.id.btn_spinner_token);

        buttonPaketData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View va) {
                PaketData data = (PaketData) spinnerPaketData.getSelectedItem();
                AndroidNetworking.post(ConstantNetwork.TAMBAH_KERANJANG)
                        .addBodyParameter("C_TYPE", String.valueOf(1))
                        .addBodyParameter("C_ID", data.getC_ID())
                        .addBodyParameter("C_USERNAME", handler.getString("C_USERNAME", ""))
                        .addBodyParameter("V_PHONE", ((EditText)v.findViewById(R.id.et_paket_data)).getText().toString())
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if (response.optBoolean("success")) {
                                    Toast.makeText(getActivity(), "Ditambahkan ke keranjang", Toast.LENGTH_SHORT).show();
                                    ((EditText)v.findViewById(R.id.et_paket_data)).setText("");
                                } else
                                    Toast.makeText(getActivity(), "Gagal menambahkan ke keranjang", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onError(ANError anError) {
                                Toast.makeText(getActivity(), "Gagal menambahkan ke keranjang", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

        buttonPulsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vs) {
                Pulsa pulsa = (Pulsa) spinnerPulsa.getSelectedItem();
                AndroidNetworking.post(ConstantNetwork.TAMBAH_KERANJANG)
                        .addBodyParameter("C_TYPE", String.valueOf(2))
                        .addBodyParameter("C_ID", pulsa.getC_ID())
                        .addBodyParameter("C_USERNAME", handler.getString("C_USERNAME", ""))
                        .addBodyParameter("V_PHONE", ((EditText)v.findViewById(R.id.et_pulsa)).getText().toString())
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if (response.optBoolean("success")) {
                                    Toast.makeText(getActivity(), "Ditambahkan ke keranjang", Toast.LENGTH_SHORT).show();
                                    ((EditText)v.findViewById(R.id.et_pulsa)).setText("");
                                } else
                                    Toast.makeText(getActivity(), "Gagal menambahkan ke keranjang", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onError(ANError anError) {
                                Toast.makeText(getActivity(), "Gagal menambahkan ke keranjang", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        buttonToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vs) {
                Token token = (Token) spinnerToken.getSelectedItem();
                AndroidNetworking.post(ConstantNetwork.TAMBAH_KERANJANG)
                        .addBodyParameter("C_TYPE", String.valueOf(3))
                        .addBodyParameter("C_ID", token.getC_ID())
                        .addBodyParameter("V_TOKEN_ID", ((EditText)v.findViewById(R.id.et_token2)).getText().toString())
                        .addBodyParameter("C_USERNAME", handler.getString("C_USERNAME", ""))
                        .addBodyParameter("V_PHONE", ((EditText)v.findViewById(R.id.et_token)).getText().toString())
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if (response.optBoolean("success")) {
                                    Toast.makeText(getActivity(), "Ditambahkan ke keranjang", Toast.LENGTH_SHORT).show();
                                    ((EditText)v.findViewById(R.id.et_token)).setText("");
                                    ((EditText)v.findViewById(R.id.et_token2)).setText("");

                                } else
                                    Toast.makeText(getActivity(), "Gagal menambahkan ke keranjang", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onError(ANError anError) {
                                Toast.makeText(getActivity(), "Gagal menambahkan ke keranjang", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


        setupSlider();

        setupAdapter();

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AndroidNetworking.forceCancel(TAG_PAKET_DATA);
        AndroidNetworking.forceCancel(TAG_PULSA);
        AndroidNetworking.forceCancel(TAG_TOKEN);


        AndroidNetworking.post(ConstantNetwork.PAKET_DATA)
                .setTag(TAG_PAKET_DATA)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray array = response.optJSONArray("DataRow");
                        for (int i=0;i<array.length();i++) {
                            JSONObject item = array.optJSONObject(i);
                            PaketData data = new PaketData();
                            data.setC_ID(item.optString("C_ID"));
                            data.setV_PROVIDER(item.optString("V_PROVIDER"));
                            data.setV_DESC(item.optString("V_DESC"));
                            data.setN_SELL(item.optLong("V_DESC"));
                            paketDataList.add(data);
                        }
                        paketDataAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

        AndroidNetworking.post(ConstantNetwork.PULSA)
                .setTag(TAG_PULSA)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray array = response.optJSONArray("DataRow");
                        for (int i=0;i<array.length();i++) {
                            JSONObject item = array.optJSONObject(i);
                            Pulsa pulsa = new Pulsa();
                            pulsa.setC_ID(item.optString("C_ID"));
                            pulsa.setV_PROVIDER(item.optString("V_PROVIDER"));
                            pulsa.setN_PULSA(item.optLong("N_PULSA"));
                            pulsa.setN_SELL(item.optLong("V_DESC"));
                            pulsaList.add(pulsa);
                        }
                        pulsaAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

        AndroidNetworking.post(ConstantNetwork.TOKEN)
                .setTag(TAG_TOKEN)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray array = response.optJSONArray("DataRow");
                        for (int i=0;i<array.length();i++) {
                            JSONObject item = array.optJSONObject(i);
                            Token token = new Token();
                            token.setC_ID(item.optString("C_ID"));
                            token.setN_PULSA(item.optLong("N_PULSA"));
                            token.setN_SELL(item.optLong("V_DESC"));
                            tokenList.add(token);
                        }
                        tokenAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private void setupAdapter() {
        paketDataAdapter = new ArrayAdapter<PaketData>(getContext(), android.R.layout.simple_list_item_1, paketDataList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null)
                    convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

                ((TextView)convertView.findViewById(android.R.id.text1)).setText(getItem(position).getV_DESC());
                return convertView;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null)
                    convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);

                ((TextView)convertView.findViewById(android.R.id.text1)).setText(getItem(position).getV_DESC());
                return convertView;
            }
        };
        pulsaAdapter = new ArrayAdapter<Pulsa>(getContext(), android.R.layout.simple_list_item_1, pulsaList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null)
                    convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

                ((TextView)convertView.findViewById(android.R.id.text1)).setText("Pulsa " + getItem(position).getN_PULSA());
                return convertView;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null)
                    convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_spinner_dropdown_item,
                            parent, false);

                ((TextView)convertView.findViewById(android.R.id.text1)).setText("Pulsa " + getItem(position).getN_PULSA());
                return convertView;
            }
        };
        tokenAdapter = new ArrayAdapter<Token>(getContext(), android.R.layout.simple_list_item_1, tokenList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null)
                    convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

                ((TextView)convertView.findViewById(android.R.id.text1)).setText("Token Listrik " + getItem(position).getN_PULSA());
                return convertView;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null)
                    convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);

                ((TextView)convertView.findViewById(android.R.id.text1)).setText("Token Listrik " + getItem(position).getN_PULSA());
                return convertView;
            }
        };
        spinnerPaketData.setAdapter(paketDataAdapter);
        spinnerPulsa.setAdapter(pulsaAdapter);
        spinnerToken.setAdapter(tokenAdapter);
    }


    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("https://promo.sepulsa.com/JGOS/img/080617_phoenix1140_jgossepulsa.png?1556"));
        fragments.add(FragmentSlider.newInstance("https://sepulsameowth.s3.amazonaws.com/img/promo_banner/55fa508a48ca07ee66916942098eb8af.png"));
        fragments.add(FragmentSlider.newInstance("https://img2.ralali.id/passthrough/assets/img/event//Untung-Jualan-Pulsa-Indosat-1524545055.jpg"));
        fragments.add(FragmentSlider.newInstance("https://sepulsameowth.s3.amazonaws.com/img/promo_banner/824c44c9072530f15f07b0894b89b9b4.png"));

        mAdapter = new SliderPagerAdapter(getChildFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(getActivity(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        AndroidNetworking.forceCancel(TAG_PAKET_DATA);
        AndroidNetworking.forceCancel(TAG_PULSA);
        AndroidNetworking.forceCancel(TAG_TOKEN);
    }
}
