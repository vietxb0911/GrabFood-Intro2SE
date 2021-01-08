package com.example.grabfood.Customer.Rating;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.grabfood.R;

public class ReviewActivity extends AppCompatActivity {

    static public int review_order;
    static public int review_shipper;
    static public int review_restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_review);

        setTitle("Review");

        ImageButton order_rate_btn = (ImageButton) findViewById(R.id.ord);
        ImageButton shipper_rate_btn = (ImageButton) findViewById(R.id.shipper);
        ImageButton restaurant_rate_btn = (ImageButton) findViewById(R.id.res);
        Button proceed_btn = (Button) findViewById(R.id.proceedBtn);
        order_rate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ReviewActivity.this);
                builder.setTitle("Danh gia");
                String[] datas = {"1 sao", "2 sao", "3 sao", "4 sao", "5 sao"};
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
                        review_order = checkedItem[0];
                        Toast.makeText(ReviewActivity.this,"Ban da danh gia don hang " + datas[review_order],
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ReviewActivity.this,"Ban chua danh gia don hang",
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        shipper_rate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ReviewActivity.this);
                builder.setTitle("Danh gia");
                String[] datas = {"1 sao", "2 sao", "3 sao", "4 sao", "5 sao"};
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
                        review_shipper = checkedItem[0];
                        Toast.makeText(ReviewActivity.this,"Ban da danh gia shipper " + datas[review_shipper],
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ReviewActivity.this,"Ban chua danh gia shipper",
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        restaurant_rate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ReviewActivity.this);
                builder.setTitle("Danh gia");
                String[] datas = {"1 sao", "2 sao", "3 sao", "4 sao", "5 sao"};
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
                        review_restaurant = checkedItem[0];
                        Toast.makeText(ReviewActivity.this,"Ban da danh gia nha hang " + datas[review_restaurant],
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ReviewActivity.this,"Ban chua danh gia nha hang",
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        proceed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
