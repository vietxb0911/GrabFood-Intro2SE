package com.example.grabfood.Customer;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.grabfood.R;

import java.util.ArrayList;

public class FoodAdapter extends BaseAdapter {
    final ArrayList<Food> listFood;

    FoodAdapter(ArrayList<Food> listFood) {
        this.listFood = listFood;
    }

    @Override
    public int getCount() {
        return listFood.size();
    }

    @Override
    public Object getItem(int position) {
        return listFood.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewFood;
        if (convertView == null) {
            viewFood = View.inflate(parent.getContext(), R.layout.food_item, null);
        } else viewFood = convertView;

        //Bind sữ liệu phần tử vào View
        Food food = (Food) getItem(position);
        ((TextView) viewFood.findViewById(R.id.foodname)).setText(String.format("%s ", food.getName()));
        ((TextView) viewFood.findViewById(R.id.foodprice)).setText(String.format("%d vnd", food.getPrice()));

        return viewFood;
    }
}
