package com.example.grabfood.Helper;
import com.example.grabfood.Login.LoginActivity;
import com.example.grabfood.R;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class AccountFragment extends Fragment {

    private String TAG = "AccountFragment";


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null){
            return null;
        }

        View view = inflater.inflate(R.layout.fragment_account, container, false);
        // Inflate the layout for this fragment
        SharedPreferences myPref = getActivity().getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
        final SharedPreferences.Editor prefEditor = myPref.edit();
        final String user = myPref.getString("userName","");

        ImageView imgAccount = (ImageView) view.findViewById(R.id.imgAccount);

        imgAccount.setImageResource(R.drawable.aava);

        Log.d(TAG,"onCreate: Started.");
        ListView mListView = (ListView) view.findViewById(R.id.accountList);

        AccountItem manage = new AccountItem("Manage Account Information", BitmapFactory.decodeResource(getResources(), R.drawable.acc));
        AccountItem history = new AccountItem("Order History", BitmapFactory.decodeResource(getResources(), R.drawable.history));
        AccountItem location = new AccountItem("My Delivery Address", BitmapFactory.decodeResource(getResources(), R.drawable.llo));
        AccountItem logout = new AccountItem("Log Out", BitmapFactory.decodeResource(getResources(), R.drawable.logout));

        ArrayList<AccountItem> itemList = new ArrayList<>();
        itemList.add(manage);
        itemList.add(history);
        itemList.add(location);
        itemList.add(logout);

        ListAdapter adapter = new ListAdapter(getActivity(), R.layout.account_list,itemList);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                switch(position)
                {
                    default:
                        break;
                    case 3:
                        Log.e(TAG, "AccountFragment: Logout");
                        getActivity().finishAndRemoveTask();
                        break;
                }
            }
        });
        return view;
    }
}
