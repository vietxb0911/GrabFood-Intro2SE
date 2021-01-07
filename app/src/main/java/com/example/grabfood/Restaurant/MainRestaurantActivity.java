 package com.example.grabfood.Restaurant;
 import com.example.grabfood.MainActivity;
 import com.example.grabfood.R;

 import android.content.Intent;
 import android.os.Bundle;
 import android.util.Log;
 import android.view.View;
 import android.widget.Button;

 import androidx.annotation.Nullable;
 import androidx.appcompat.app.AppCompatActivity;

 import java.util.ArrayList;
 import java.util.List;

 public class MainRestaurantActivity extends AppCompatActivity implements View.OnClickListener {
     private Button mBtnOrder,mBtnMenu;
     ArrayList<String> name,price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_restaurant);
        mBtnMenu = findViewById(R.id.btnMenu);
        mBtnOrder = findViewById(R.id.btnOrder);
        if(name==null){
            name = new ArrayList<>();
        }
        if(price==null){
            price=new ArrayList<>();
        }
        mBtnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showMenuRestaurant();
               // Navigation.findNavController(view).navigate(R.id.action_restaurantFragment_to_menuRestaurantFragment);


            }
        });
        mBtnOrder.setOnClickListener(this);


    }
    private void showOrders(){
        Log.d("ddd", "showOrders: ");
        Intent intent = new Intent(this, ListOrdersActivity.class);
        startActivity(intent);
    }
    private void showMenuRestaurant(){
        Intent intent = new Intent(this, MenuRestaurantActivity.class);
        intent.putStringArrayListExtra("name",name);
        intent.putStringArrayListExtra("price",price);
        startActivityForResult(intent,3008);


    }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==3008){
            if(resultCode==RESULT_OK){
                name=data.getStringArrayListExtra("retName");
                price=data.getStringArrayListExtra("retPrice");
            }
        }
         super.onActivityResult(requestCode, resultCode, data);

     }

     @Override
     public void onClick(View view) {
        if(view.getId()==R.id.btnOrder){

            showOrders();

        }

    }
     List<Food> getListFood(){
         List<Food> list= new ArrayList<>();
         list.add(new Food("Bún","1000"));
         list.add(new Food("Cháo","1000"));
         return list;
     }
 }