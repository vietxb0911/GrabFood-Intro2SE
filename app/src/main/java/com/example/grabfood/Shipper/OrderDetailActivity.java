package com.example.grabfood.Shipper;
import com.example.grabfood.R;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {

    private Button button;
    private Button backBtn;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_order_detail);

        backBtn = (Button) findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        initOrderList();
        initStatusButton();
    }

    private void initStatusButton() {
        final String[] options = {"Order received", "Are moving to the restaurant", "Waiting for order",
                "On the way to the delivery location", "Successfully receive"};
        final String[] colors = {"#4CAF50", "#FFEB3B","#FFEB3B","#FFEB3B","#4CAF50"};
        textView = (TextView) findViewById(R.id.status_text);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, options);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Updating current status");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText(options[which]);
                textView.setTextColor(Color.parseColor(colors[which]));
            }
        });
        final AlertDialog a = builder.create();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.show();
            }
        });

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