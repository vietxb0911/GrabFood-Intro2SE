package com.example.grabfood.Restaurant;
import com.example.grabfood.R;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FoodAdapter extends ArrayAdapter<Food> {

    public FoodAdapter(@NonNull Context context, int resource) {
        super(context,0, resource);
    }

    public FoodAdapter(Context context, List<Food> mFoods) {
        super(context,0,mFoods);
    }

    //Food, FoodAdapter, Menu, Manifest

    public static class ViewHolder{
        TextView tvName;
        TextView tvPrice;
        TextView tvCount;
        ImageView imFood;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_food,null);
            viewHolder = new ViewHolder();
            viewHolder.tvName=(TextView)convertView.findViewById(R.id.tvName);
            viewHolder.tvPrice=(TextView)convertView.findViewById(R.id.tvPrice);
            viewHolder.tvCount=(TextView)convertView.findViewById(R.id.tvCount);
            viewHolder.imFood = (ImageView) convertView.findViewById(R.id.imFood);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        Food item =getItem(position);
        bindData(item,viewHolder);
        return convertView;
    }

    private void bindData(Food item, ViewHolder viewHolder) {
        viewHolder.tvName.setText(item.name);
        viewHolder.tvPrice.setText(item.price+" VND");
        viewHolder.tvCount.setText(item.count);
        //viewHolder.imFood.setImageBitmap(item.getBitmap());
        if(item.uri!=null) {
            viewHolder.imFood.setImageURI(item.uri);
        }
    }
}
