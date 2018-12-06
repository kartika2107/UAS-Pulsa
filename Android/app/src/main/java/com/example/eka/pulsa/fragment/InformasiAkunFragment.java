package com.example.eka.pulsa.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.eka.pulsa.R;
import com.example.eka.pulsa.util.ConstantNetwork;
import com.example.eka.pulsa.util.SessionHandler;

import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformasiAkunFragment extends Fragment {

    private final String TAG_PROFILE = "TAG_PROFILE";

    private SessionHandler handler;


    public InformasiAkunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        handler = new SessionHandler(container.getContext());
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_informasi_akun, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AndroidNetworking.forceCancel(TAG_PROFILE);

        AndroidNetworking.post(ConstantNetwork.PROFILE)
                .addBodyParameter("C_USERNAME", handler.getString("C_USERNAME", ""))
                .addBodyParameter("V_PASSWORD", handler.getString("C_PASSWORD", ""))
                .setTag(TAG_PROFILE)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (!response.optBoolean("success")) {
                            Toast.makeText(getActivity(), "Gagal menampilkan profil.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        JSONObject profile = response.optJSONObject("profile");
                        ((EditText)view.findViewById(R.id.et_username)).setText(profile.optString("C_USERNAME"));
                        ((EditText)view.findViewById(R.id.et_fullname)).setText(profile.optString("V_FULLNAME"));
                        ((EditText)view.findViewById(R.id.et_phone)).setText(profile.optString("V_NOHP"));
                        ((EditText)view.findViewById(R.id.et_email)).setText(profile.optString("V_MAIL"));

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        AndroidNetworking.forceCancel(TAG_PROFILE);
    }
}
