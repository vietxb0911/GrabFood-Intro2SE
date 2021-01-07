package com.example.grabfood.Restaurant;
import com.example.grabfood.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListOrdersActivity extends AppCompatActivity {
    private ListView lvOrder;
    private OrderAdapter orderAdapter;
    private Button mBtnReturn;
    private List<Order> mOrders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_list_orders2);
        lvOrder = (ListView) findViewById(R.id.lvOrder);

        mOrders = new ArrayList<>();
        mOrders = getListOrder();
        orderAdapter = new OrderAdapter(this,mOrders);
        lvOrder.setAdapter(orderAdapter);
        lvOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              //  Navigation.findNavController(view).navigate(R.id.action_listOrdersFragment_to_detailOrderFragment);
            detailOrder();
            }
        });



    }
    private List<Order> getListOrder(){
        List<Order> list = new ArrayList<>();
        list.add(new Order("#1","Delivery","10/10/2020"));
        list.add(new Order("#2","Delivery","11/11/2020"));
        list.add(new Order("#3","Delivery","12/12/2020"));
        list.add(new Order("#4","Preparing","12/12/2020"));
        list.add(new Order("#5","Preparing","12/12/2020"));

        Log.d("1", "getListOrder: "+list.size());
        return list;
    }

    private  void detailOrder(){
        Intent intent = new Intent(this,DetailOrderActivity.class);
        startActivity(intent);
    }


}