package com.example.grabfood.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.grabfood.Helper.AccountFragment;
import com.example.grabfood.Helper.OrderFragment;
import com.example.grabfood.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainCustomerActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    private UserHomeFragment homeFragment;
    private AccountFragment accountFragment;
    private OrderFragment orderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_customer);

        homeFragment = new UserHomeFragment();
        accountFragment = new AccountFragment();
        orderFragment = new OrderFragment();

        setFragment(homeFragment);

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
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flContainer, fragment);
        fragmentTransaction.commit();
    }

    public void setOrderFragment() {
        if (orderFragment == null) {
            orderFragment = new OrderFragment();
        }
        setFragment(orderFragment);
    }
}