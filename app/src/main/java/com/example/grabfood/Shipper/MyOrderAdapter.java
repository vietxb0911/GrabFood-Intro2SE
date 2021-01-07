package com.example.grabfood.Shipper;
import com.example.grabfood.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyOrderAdapter extends ArrayAdapter<MyOrder> {
    public MyOrderAdapter(Context context, ArrayList<MyOrder> items){
        super(context, 0, items);
    };

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
        {
            convertView = createView();
        }
        ImageView imageView = (ImageView)convertView.findViewById(R.id.order_img);
        imageView.setImageResource(R.drawable.pho_food);
        TextView order_name = (TextView)convertView.findViewById(R.id.order_name);
        TextView order_price = (TextView)convertView.findViewById(R.id.order_price);
        TextView order_quantity = (TextView)convertView.findViewById(R.id.order_quantity);
        MyOrder item = getItem(position);
        order_name.setText(item.getName());
        order_price.setText(item.getPrice());
        order_quantity.setText(item.getQuantity());
        return convertView;
    }

    private View createView() {
        View view = LayoutInflater.from(this.getContext()).inflate(R.layout.order_layout, null);
        return view;
    }
}
