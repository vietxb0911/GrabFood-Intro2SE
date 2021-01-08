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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.grabfood.Customer.category_rcv_x2.Category;
import com.example.grabfood.Customer.book_rcv_x1.Book;
import com.example.grabfood.Customer.book_rcv_x1.CustomAdapterListBook;

import java.util.ArrayList;

//intent id_backgroud, string name, list<Item>
public class Activity_restaurant_info extends AppCompatActivity {

    ListView listViewItem;
    ArrayList<Book> listBook;
    Category restaurantInfo;
    ImageView img_background_restaurant;
    TextView tv_name_restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_restaurant_info);

        int position = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = extras.getInt("restaurantID");
        }
        ArrayList<Category> categoryArrayList = restaurantInfo.getListCategory();
        restaurantInfo = categoryArrayList.get(position);

        buildListView();

    }

    private void buildListView() {
        img_background_restaurant = findViewById(R.id.img_restaurant_info);
        tv_name_restaurant = findViewById(R.id.tv_name_restaurant);

        img_background_restaurant.setImageResource(restaurantInfo.getResourceBackground());
        tv_name_restaurant.setText(restaurantInfo.getNameCategory());

        listViewItem = (ListView) findViewById(R.id.listviewItem);

        listViewItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = listBook.get(position);
                openFeedbackDialog(Gravity.CENTER,book);
//                Toast.makeText(Activity_restaurant_info.this,String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });

        listBook = restaurantInfo.getBooks();
        CustomAdapterListBook adapter = new CustomAdapterListBook(this,R.layout.custom_list_view_item, listBook);
        listViewItem.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void openFeedbackDialog(int gravity,Book book){



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

        dialog.setCancelable(true);


        ImageView img_dialog_item = dialog.findViewById(R.id.img_dialog_item);
        TextView tv_dialog_name_item = dialog.findViewById(R.id.tv_dialog_name_item);
        TextView tv_dialog_price_item = dialog.findViewById(R.id.tv_dialog_price_item);
        TextView tv_dialog_restaurant_name = dialog.findViewById(R.id.tv_dialog_restaurantname);


        img_dialog_item.setImageResource(book.getResourcId());
        tv_dialog_name_item.setText(book.getName());
        tv_dialog_price_item.setText(book.getPrice());
        tv_dialog_restaurant_name.setText(restaurantInfo.getNameCategory());


        AppCompatButton btnIncItem = dialog.findViewById(R.id.btn_inc_item);
        AppCompatButton btnDecItem = dialog.findViewById(R.id.btn_dec_item);
        AppCompatButton btnAddToBasket = dialog.findViewById(R.id.btn_add_to_basket);
        TextView tv_dialog_count = dialog.findViewById(R.id.tv_dialog_count);

        tv_dialog_count.setText("0");
        dialog.show();
        btnIncItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Activity_restaurant_info.this,"Tăng số lượng", Toast.LENGTH_SHORT).show();
                Integer count =Integer.valueOf(tv_dialog_count.getText().toString());
                tv_dialog_count.setText(String.valueOf(count.intValue()+1));
            }
        });

        btnDecItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Activity_restaurant_info.this,"Giảm số lượng", Toast.LENGTH_SHORT).show();
                Integer count =Integer.valueOf(tv_dialog_count.getText().toString());
                if(count.intValue()==0) count=Integer.valueOf("1");
                tv_dialog_count.setText(String.valueOf(count.intValue()-1));
            }
        });

        btnAddToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Activity_restaurant_info.this,"Thêm vào giỏ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
