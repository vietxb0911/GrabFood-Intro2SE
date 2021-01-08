package com.example.grabfood.Customer;
import com.example.grabfood.R;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.grabfood.Customer.category_rcv_x2.Category;
import com.example.grabfood.Customer.category_rcv_x2.CategoryAdapter;
import com.example.grabfood.Customer.viewpager.Photo;
import com.example.grabfood.Customer.viewpager.PhotoAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

import static com.example.grabfood.Customer.category_rcv_x2.Category.getListCategory;

public class MainCustomerActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;
    private List<Photo> mListPhoto;
    private Timer mTimer;
    private ArrayList<Category> listCategory;

    private RecyclerView rcvCategory;
    private CategoryAdapter categoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_customer);

        buildViewPager();
        buildRecycleView();

    }

    private void buildViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        circleIndicator = (CircleIndicator) findViewById(R.id.circle_indicator);

        mListPhoto = getListPhoto();
        photoAdapter = new PhotoAdapter(this, mListPhoto);
        viewPager.setAdapter(photoAdapter);

        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        autoSlideImage();
    }

    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.bg_restaurant_1));
        list.add(new Photo(R.drawable.bg_restaurant_2));
        list.add(new Photo(R.drawable.bg_restaurant_3));
        list.add(new Photo(R.drawable.bg_restaurant_4));

        return list;
    }

    private void autoSlideImage(){
        if(mListPhoto == null || mListPhoto.isEmpty() || viewPager==null) return;

        //Init timer
        if (mTimer==null){
            mTimer = new Timer();
        }
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = mListPhoto.size() - 1;
                        if(currentItem < totalItem){
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        }
                        else
                            viewPager.setCurrentItem(0);
                    }
                });
            }
        },500,3000);
    }

    void buildRecycleView(){
        rcvCategory = (RecyclerView) findViewById(R.id.rcv_category);
        categoryAdapter = new CategoryAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rcvCategory.setLayoutManager(linearLayoutManager);
        listCategory = getListCategory();
        categoryAdapter.setData(listCategory);
        rcvCategory.setAdapter(categoryAdapter);

        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Category category=listCategory.get(position);

                //Toast.makeText(getBaseContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainCustomerActivity.this, Activity_restaurant_info.class);
                intent.putExtra("restaurantID",position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer!=null){
            mTimer.cancel();
            mTimer=null;
        }
        finishAndRemoveTask();
    }
}