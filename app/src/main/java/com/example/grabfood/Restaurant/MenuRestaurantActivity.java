package com.example.grabfood.Restaurant;
import com.example.grabfood.R;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MenuRestaurantActivity extends AppCompatActivity {
    ListView listView;
    FoodAdapter foodAdapter;
    List<Food> mFoods;
    private Button btnReturn,btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_restaurant);
    btnReturn = (Button)findViewById(R.id.btnReturn1);
    btnAdd=(Button)findViewById(R.id.btnAddFood);
    listView = findViewById(R.id.lvFood);
    getListFood();


    foodAdapter = new FoodAdapter(this,mFoods);
        listView.setAdapter(foodAdapter);

        btnReturn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            returnMainScreen();
           // Navigation.findNavController(view).navigate(R.id.action_menuRestaurantFragment_to_restaurantFragment);
        }
    });
        btnAdd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DialogChangeLayout();
        }
    });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            update(mFoods,i);
        }
    });




}

    private void update(List<Food> mFoods, int i) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_PROGRESS);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_add_food);
        dialog.setCanceledOnTouchOutside(false);
        EditText edName,edPrice;
        Button btnDone,btnBack;
        Food food = mFoods.get(i);
        btnDone = (Button)dialog.findViewById(R.id.btnDone);
        btnBack = (Button)dialog.findViewById(R.id.btnCancle);
        btnBack.setText("DELETE");
        edName = (EditText)dialog.findViewById(R.id.edName);
        edPrice =(EditText)dialog.findViewById(R.id.edPrice);
        edName.setText(food.name);
        edPrice.setText(food.price);
        btnDone.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                dialog.cancel();
                food.setName(edName.getText().toString());
                food.setPrice(edPrice.getText().toString());
                if(edName==null||edName.equals("")){
                    notification("Yêu cầu nhập Name");
                }
                else {
                    foodAdapter.notifyDataSetChanged();
                }

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                mFoods.remove(i);
                foodAdapter.notifyDataSetChanged();
            }
        });
        dialog.show();
    }

    void getListFood(){
        mFoods = new ArrayList<>();
        Intent intent = getIntent();
        ArrayList<String>name = intent.getStringArrayListExtra("name");
        ArrayList<String>price = intent.getStringArrayListExtra("price");
        for(int i=0; i<name.size() ;i++){
            mFoods.add(new Food(name.get(i),price.get(i)));
        }
    }
    private void DialogChangeLayout(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_PROGRESS);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_add_food);
        dialog.setCanceledOnTouchOutside(false);
        EditText edName,edPrice;
        Button btnDone,btnBack;
        btnDone = (Button)dialog.findViewById(R.id.btnDone);
        btnBack = (Button)dialog.findViewById(R.id.btnCancle);
        edName = (EditText)dialog.findViewById(R.id.edName);
        edPrice =(EditText)dialog.findViewById(R.id.edPrice);
        btnDone.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(edName.getText().toString()==null||edName.getText().toString().equals("")){
                    notification("Yêu cầu nhập Name");
                }
                else if(edPrice.getText().toString()==null||edPrice.getText().toString().equals("")){
                    notification("Yêu cầu nhập Price");
                }
                else {
                    dialog.cancel();
                    Food food = new Food(edName.getText().toString(),edPrice.getText().toString());
                    mFoods.add(food);
                    foodAdapter.notifyDataSetChanged();

                }

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
    void notification(String string){
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
    }
    private void returnMainScreen(){
        ArrayList<String>name,price;
        name= new ArrayList<>();
        price = new ArrayList<>();
        for(int i=0;i<mFoods.size();i++){
            name.add(mFoods.get(i).name);
            price.add(mFoods.get(i).price);
        }
        Intent intent = new Intent();
        intent.putStringArrayListExtra("retName",name);
        intent.putStringArrayListExtra("retPrice",price);
        setResult(RESULT_OK,intent);
        finish();
    }

}