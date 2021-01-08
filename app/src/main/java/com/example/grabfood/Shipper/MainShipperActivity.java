package com.example.grabfood.Shipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.example.grabfood.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainShipperActivity extends AppCompatActivity {

    private String TAG = "MainShipperActivity";
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    private ShipperHomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_shipper);

        homeFragment = new ShipperHomeFragment();
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

        radioBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                args.putInt("flag", 1);
                homeFragment = new ShipperHomeFragment();
                homeFragment.setArguments(args);
                setFragment(homeFragment);
                Log.e(TAG, "onCreate: radioBtn1 Clicked");
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
                        return true;
                    case R.id.navigation_account:
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