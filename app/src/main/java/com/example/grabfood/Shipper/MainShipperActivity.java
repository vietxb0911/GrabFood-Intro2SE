package com.example.grabfood.Shipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.example.grabfood.Helper.AccountFragment;
import com.example.grabfood.Helper.OrderFragment;
import com.example.grabfood.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainShipperActivity extends AppCompatActivity {

    private String TAG = "MainShipperActivity";
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    private ShipperHomeFragment homeFragment;
    private AccountFragment accountFragment;
    private OrderFragment orderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_shipper);

        homeFragment = new ShipperHomeFragment();
        accountFragment = new AccountFragment();
        orderFragment = new OrderFragment();

        Bundle args = new Bundle();
        args.putInt("flag", 0);
        homeFragment.setArguments(args);
        setFragment(homeFragment);

        RadioButton radioBtn0 = findViewById(R.id.radio0);
        RadioButton radioBtn1 = findViewById(R.id.radio1);
        radioBtn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onCreate: radioBtn0 Clicked");
                homeFragment = new ShipperHomeFragment();
                args.putInt("flag", 0);
                homeFragment.setArguments(args);
                setFragment(homeFragment);

            }
        });

        ProgressBar pb = (ProgressBar) findViewById(R.id.pb);



        radioBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setVisibility(View.VISIBLE);

                final Handler handler = new Handler();
                final Runnable r = new Runnable() {
                    public void run() {
                        args.putInt("flag", 1);
                        homeFragment = new ShipperHomeFragment();
                        homeFragment.setArguments(args);
                        setFragment(homeFragment);
                        pb.setVisibility(View.GONE);
                        Log.e(TAG, "onCreate: radioBtn1 Clicked");
                    }
                };
                handler.postDelayed(r,3000);
            }
        });

        frameLayout = (FrameLayout) findViewById(R.id.flContainer);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch(item.getItemId()){
                    case R.id.navigation_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.navigation_search:
                        return true;
                    case R.id.navigation_order:
                        setFragment(orderFragment);
                        return true;
                    case R.id.navigation_account:
                        setFragment(accountFragment);
                        return true;
                    default:
                        return true;
                }
            }
        });
    }

    private void setFragment(Fragment fragment){

        Fragment curFragment = getSupportFragmentManager().findFragmentById(R.id.flContainer);
        if(curFragment != null)
            getSupportFragmentManager().beginTransaction().remove(curFragment).commit();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flContainer, fragment);
        fragmentTransaction.commit();
    }
}