package com.example.grabfood.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.grabfood.R;

public class MainCustomerActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_customer2);

        Button ord_btn = (Button) findViewById(R.id.ord);
        Button pay_btn = (Button) findViewById(R.id.pay);
        Button rev_btn = (Button) findViewById(R.id.rev);

        ord_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCustomerActivity2.this, OrderActivity.class);
                startActivity(intent);
            }
        });

        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCustomerActivity2.this, PaymentActivity.class);
                startActivity(intent);
            }
        });

        rev_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCustomerActivity2.this, ReviewActivity.class);
                startActivity(intent);
            }
        });
    }
}