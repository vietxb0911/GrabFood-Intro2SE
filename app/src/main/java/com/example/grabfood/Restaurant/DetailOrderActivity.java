package com.example.grabfood.Restaurant;
import com.example.grabfood.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DetailOrderActivity extends AppCompatActivity {
    private Button btnOk;
    private ListView lvFoods;
    private List<Food> mFoods;
    private FoodAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);
        lvFoods = (ListView)findViewById(R.id.lvFoods);

        mFoods = new ArrayList<>();
        mFoods= getListFood();
        foodAdapter = new FoodAdapter(this,mFoods);
        lvFoods.setAdapter(foodAdapter);
        btnOk = (Button)findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Navigation.findNavController(view).navigate(R.id.action_detailOrderFragment_to_listOrdersFragment);
                returnListOrdersScreen();
            }
        });

    }
    List<Food> getListFood(){
        List<Food> list= new ArrayList<>();
        list.add(new Food("Bún","1000","x2"));
        list.add(new Food("Cháo","1000","x1"));
        return list;
    }
    private void returnListOrdersScreen(){
        Intent intent = new Intent(this, ListOrdersActivity.class);
        startActivity(intent);
    }
}