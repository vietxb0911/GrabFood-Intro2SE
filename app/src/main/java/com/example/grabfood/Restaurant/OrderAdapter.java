package com.example.grabfood.Restaurant;
import com.example.grabfood.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class OrderAdapter extends ArrayAdapter<Order>{

    public OrderAdapter(@NonNull Context context, int resource) {
        super(context,0, resource);
    }

    public OrderAdapter(Context context, List<Order> mOrders) {
        super(context,0,mOrders);
    }

    public static class ViewHolder{
        TextView tvID,tvTime,tvStatus;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_order,null);
            viewHolder = new ViewHolder();
            viewHolder.tvID=(TextView)convertView.findViewById(R.id.tvID);
            viewHolder.tvTime=(TextView)convertView.findViewById(R.id.tvTime);
            viewHolder.tvStatus=(TextView)convertView.findViewById(R.id.tvStatus);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Order item =getItem(position);
        bindData(item,viewHolder);

        return convertView;

    }

    private void bindData(Order item, ViewHolder viewHolder) {
        viewHolder.tvStatus.setText("Status: "+item.getStatus());
        viewHolder.tvTime.setText("Time: "+item.getTime());
        viewHolder.tvID.setText(item.getID());

    }
}

