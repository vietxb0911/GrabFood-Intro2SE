package com.example.grabfood.Customer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.grabfood.R;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {

    ArrayList<Food> listFood;
    FoodAdapter foodListViewAdapter;
    ListView listViewFood;
    int kindPayment;
    int sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        setTitle("Payment");

        listFood = new ArrayList<>();
        listFood.add(new Food("Com tam", 30000));
        listFood.add(new Food("Banh mi", 20000));
        listFood.add(new Food("Hamburger", 50000));
        listFood.add(new Food("Ga ran", 40000));
        listFood.add(new Food("Tra sua", 30000));
        listFood.add(new Food("Xoi ga", 35000));
        listFood.add(new Food("Ca phe", 20000));
        listFood.add(new Food("Chao long", 25000));

        foodListViewAdapter = new FoodAdapter(listFood);

        listViewFood = findViewById(R.id.listfood);
        listViewFood.setAdapter(foodListViewAdapter);


        //Lắng nghe bắt sự kiện một phần tử danh sách được chọn
        listViewFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food food = (Food) foodListViewAdapter.getItem(position);
                Toast.makeText(PaymentActivity.this, food.getName(), Toast.LENGTH_LONG).show();
            }
        });

        Button pay_btn = findViewById(R.id.pay);
        TextView sum_money = findViewById(R.id.tongtien);

        for (int i = 0; i < listFood.size(); i++)
            sum += listFood.get(i).getPrice();

        sum_money.setText("Tong: "+ Integer.toString(sum));

        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
                builder.setTitle("Hinh thuc thanh toan");
                String[] datas = {"Thanh toan bang tien mat", "Thanh toan bang vi momo", "Thanh toan bang the ngan hang"};
                final int[] checkedItem = {0};

                builder.setSingleChoiceItems(datas, checkedItem[0], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkedItem[0] = which;
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        kindPayment = checkedItem[0];
                        Toast.makeText(PaymentActivity.this,"Ban da chon " + datas[kindPayment],
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Cancel", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
