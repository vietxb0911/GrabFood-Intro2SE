package com.example.grabfood.Restaurant;
import com.example.grabfood.R;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private String TAG = "HomeFragment";
    Context mContext;
    private Button mBtnOrder,mBtnMenu;
    ArrayList<String> name,price;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.restaurant_homepage_fragment, container, false);


        mBtnMenu = view.findViewById(R.id.btnMenu);
        mBtnOrder = view.findViewById(R.id.btnOrder);

        if(name==null){
            name = new ArrayList<>();
        }
        if(price==null){
            price=new ArrayList<>();
        }

        mBtnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenuRestaurant();
            }
        });
        mBtnOrder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showOrders();
            }
        });

        Log.e(TAG, "onCreateView");
        return view;

    }

    private void showOrders(){
        Log.d("ddd", "showOrders: ");
        Intent intent = new Intent(getActivity(), ListOrdersActivity.class);
        startActivity(intent);
    }
    private void showMenuRestaurant(){
        Intent intent = new Intent(getActivity(), MenuRestaurantActivity.class);
        intent.putStringArrayListExtra("name",name);
        intent.putStringArrayListExtra("price",price);
        startActivityForResult(intent,3008);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==3008){
            if(resultCode==getActivity().RESULT_OK){
                name=data.getStringArrayListExtra("retName");
                price=data.getStringArrayListExtra("retPrice");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    List<Food> getListFood(){
        List<Food> list= new ArrayList<>();
        list.add(new Food("Bún","1000"));
        list.add(new Food("Cháo","1000"));
        return list;
    }



}
