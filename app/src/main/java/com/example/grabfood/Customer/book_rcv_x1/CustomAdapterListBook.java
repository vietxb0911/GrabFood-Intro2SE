package com.example.grabfood.Customer.book_rcv_x1;
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

public class CustomAdapterListBook extends ArrayAdapter<Book> {
    Context context;
    int resource;
    ArrayList<Book> books;

    public CustomAdapterListBook(@NonNull Context context, int resource, @NonNull ArrayList<Book> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.books = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowItem = inflater.inflate(resource,parent,false);


        ImageView imgItem = rowItem.findViewById(R.id.img_item);
        TextView tvItemName = rowItem.findViewById(R.id.tv_name_item);
        TextView tvItemPrice = rowItem.findViewById(R.id.tv_price_item);
        TextView tvItemCount =  rowItem.findViewById(R.id.tv_count_item);

        imgItem.setImageResource(books.get(position).getResourcId());
        tvItemName.setText(books.get(position).getName());
        tvItemPrice.setText(String.valueOf(Integer.valueOf(books.get(position).getPrice()).intValue()/1000)+".000đ");
        if(books.get(position).getCount()==0) {
            tvItemCount.setText("");
        }
        else
            tvItemCount.setText("x"+String.valueOf(books.get(position).getCount()));


        return rowItem;
    }
}
