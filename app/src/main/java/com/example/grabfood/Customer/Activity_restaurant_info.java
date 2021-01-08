package com.example.grabfood.Customer;
import com.example.grabfood.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.grabfood.Customer.category_rcv_x2.Category;
import com.example.grabfood.Customer.book_rcv_x1.Book;
import com.example.grabfood.Customer.book_rcv_x1.CustomAdapterListBook;

import java.util.ArrayList;

import static com.example.grabfood.Customer.book_rcv_x1.Book.addFoodtoCart;
import static com.example.grabfood.Customer.book_rcv_x1.Book.get_Count;
import static com.example.grabfood.Customer.book_rcv_x1.Book.mycart;


//intent id_backgroud, string name, list<Item>
public class Activity_restaurant_info extends AppCompatActivity {
    private int REQUEST_CODE_CHECKOUT = 123;
    private String TAG = "Activity_restaurant_info";
    ListView listViewItem;
    ArrayList<Book> listBook;
    Category restaurantInfo;
    ImageView img_background_restaurant;
    TextView tv_name_restaurant;
    TextView tv_check_restaurant;
    int count=0;
    int sum=0;
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
        tv_check_restaurant = findViewById(R.id.tv_check_restaurant);

        img_background_restaurant.setImageResource(restaurantInfo.getResourceBackground());
        tv_name_restaurant.setText(restaurantInfo.getNameCategory());
        tv_check_restaurant.setText("");
        mycart.clear();

        tv_check_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_restaurant_info.this, Activity_my_cart.class);
                intent.putExtra("src_bg_restaurant",restaurantInfo.getResourceBackground());
                intent.putExtra("name_restaurant",restaurantInfo.getNameCategory());
                startActivityForResult(intent, REQUEST_CODE_CHECKOUT);
            }
        });


        listViewItem = (ListView) findViewById(R.id.listviewItem);

        listViewItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = listBook.get(position);
                openFeedbackDialog(Gravity.CENTER,book);
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
        tv_dialog_price_item.setText(String.valueOf(Integer.valueOf(book.getPrice()).intValue()/1000)+".000đ");
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

                Integer count =Integer.valueOf(tv_dialog_count.getText().toString());
                Integer price =Integer.valueOf(book.getPrice());
                if (count.intValue()>0){
                    addFoodtoCart(book.getResourcId(),book.getName(),  price.toString(),  count.intValue());
                }
                Log.i("check", price.toString() + "     " + count.toString() + String.valueOf(mycart.size()));
                dialog.dismiss();
                if ( get_Count()> 0 )
                {
                    tv_check_restaurant.setText("Giỏ hàng >");
                }
                else
                {
                    tv_check_restaurant.setText("");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CHECKOUT && resultCode==RESULT_OK){
            Intent resultIntent = getIntent();
            setResult(RESULT_OK, resultIntent);
            finish();


        }
    }
}
