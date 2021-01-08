package com.example.grabfood.Customer;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.grabfood.Customer.book_rcv_x1.Book;
import com.example.grabfood.Customer.book_rcv_x1.CustomAdapterListBook;
import com.example.grabfood.R;

import java.util.ArrayList;

import static com.example.grabfood.Customer.book_rcv_x1.Book.get_TotalPaymentAmount;
import static com.example.grabfood.Customer.book_rcv_x1.Book.mycart;

public class Activity_my_cart extends AppCompatActivity {

    ListView listViewItem;
    ArrayList<Book> listBook;
    TextView tv_total_mycart;
    AppCompatButton btn_order_mycart;
    ImageView img_restaurant_mycart;
    String restaurant_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        getSupportActionBar().hide();

        buildListView();
    }

    private void buildListView() {
        btn_order_mycart = findViewById(R.id.btn_order_mycart);
        tv_total_mycart = findViewById(R.id.tv_total_mycart);


        int position = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            restaurant_name=extras.getString("name_restaurant");
        }


        int total = get_TotalPaymentAmount();
        tv_total_mycart.setText("Tổng cộng:   " + String.valueOf(total/1000)+".000 VNĐ");

        btn_order_mycart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedbackDialog(Gravity.CENTER);
            }
        });


        listViewItem = (ListView) findViewById(R.id.listviewMycart);
        listViewItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        listBook = mycart;
        CustomAdapterListBook adapter = new CustomAdapterListBook(this, R.layout.custom_list_view_item, listBook);
        listViewItem.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void openFeedbackDialog(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_my_check);
        Window window = dialog.getWindow();
        if(window==null) return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        dialog.setCancelable(false);


        TextView tv_dialog_name_restaurant = dialog.findViewById(R.id.tv_dialog_mycheck_restaurantname);
        TextView tv_dialog_total = dialog.findViewById(R.id.tv_dialog_mycheck_total);
        AppCompatButton btn_dialog_yes = dialog.findViewById(R.id.btn_dialog_mycheck_yes);


        tv_dialog_name_restaurant.setText(restaurant_name);
        tv_dialog_total.setText(String.valueOf(get_TotalPaymentAmount()/1000)+".000 VNĐ");



        btn_dialog_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dialog.dismiss();
                Intent resultIntent = getIntent();
                setResult(RESULT_OK, resultIntent);
               finish();

            }
        });

        dialog.show();

    }
}
