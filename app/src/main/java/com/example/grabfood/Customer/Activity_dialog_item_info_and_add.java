package com.example.grabfood.Customer;
import com.example.grabfood.R;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.grabfood.Customer.book_rcv_x1.Book;

public class Activity_dialog_item_info_and_add extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_iteminfo_and_add);
    }

    private void openFeedbackDialog(int gravity, Book book){

        ImageView img_dialog_item =(ImageView)findViewById(R.id.img_dialog_item);
        TextView tv_dialog_name_item = (TextView)findViewById(R.id.tv_dialog_name_item);
        TextView tv_dialog_price_item = (TextView)findViewById(R.id.tv_dialog_price_item);

        img_dialog_item.setImageResource(book.getResourcId());
        tv_dialog_name_item.setText(book.getName());
        tv_dialog_price_item.setText(book.getPrice());

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_iteminfo_and_add);
        Window window = dialog.getWindow();
        if(window==null) return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.BOTTOM == gravity)
            dialog.setCancelable(true);
        else
            dialog.setCancelable(false);


        AppCompatButton btnIncItem = (AppCompatButton)findViewById(R.id.btn_inc_item);
        AppCompatButton btnDecItem = (AppCompatButton)findViewById(R.id.btn_dec_item);
        Button btnAddToBasket = (Button)findViewById(R.id.btn_add_to_basket);

        btnIncItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnDecItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnAddToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
