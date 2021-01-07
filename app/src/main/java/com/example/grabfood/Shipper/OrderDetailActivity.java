package com.example.grabfood.Shipper;
import com.example.grabfood.R;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initOrderList();
    }

    private void initOrderList() {
        ListView listview = (ListView)findViewById(R.id.order_list);
        ArrayList<MyOrder> items =new ArrayList<MyOrder>();
        items.add(getOrderItem());
        items.add(getOrderItem());
        items.add(getOrderItem());

        MyOrderAdapter adapter = new MyOrderAdapter(this, items);
        listview.setAdapter(adapter);
        updateTotalPrice();
    }

    private void updateTotalPrice() {
    }

    private MyOrder getOrderItem() {
        MyOrder myOrder = new MyOrder("Banh beo", 20000, 2);
        return myOrder;
    }


}