package com.example.grabfood.Customer;
import com.example.grabfood.R;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    public ArrayList<Food> list = new ArrayList<Food>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Order");

        Button btnAdd = (Button) findViewById(R.id.addbtn);
        ImageView img = (ImageView) findViewById(R.id.img);
        TextView foodInfo = (TextView) findViewById(R.id.info);

        Uri imgUri = Uri.parse("android.resource://" + getPackageName() + "/drawable/" + "food");
        img.setImageURI(imgUri);

        String name = "Hamburger";
        int price = 30000;
        Food tmp = new Food(name, price);

        foodInfo.setText(name + " " + Integer.toString(price) + "vnd");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(tmp);
                Toast.makeText(OrderActivity.this, "Add to basket successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
