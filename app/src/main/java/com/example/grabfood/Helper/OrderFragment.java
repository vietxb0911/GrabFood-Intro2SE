package com.example.grabfood.Helper;
import com.example.grabfood.R;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OrderFragment extends Fragment {

    private String TAG = "OrderFragment";

    public OrderFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (container == null){
            return null;
        }

        Log.d(TAG,"onCreate: Started.");
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        return view;
    }

}
