package com.example.grabfood.Customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grabfood.Customer.category_rcv_x2.Category;
import com.example.grabfood.Customer.category_rcv_x2.CategoryAdapter;
import com.example.grabfood.Customer.viewpager.Photo;
import com.example.grabfood.Customer.viewpager.PhotoAdapter;
import com.example.grabfood.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

import static com.example.grabfood.Customer.category_rcv_x2.Category.getListCategory;

public class UserHomeFragment extends Fragment {
    private String TAG = "UserHomeFragment";
    private int REQUEST_CODE_CHECKOUT = 123;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;
    private List<Photo> mListPhoto;
    private Timer mTimer;
    private ArrayList<Category> listCategory;

    private RecyclerView rcvCategory;
    private CategoryAdapter categoryAdapter;

    public UserHomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null){
            return null;
        }

        View view = inflater.inflate(R.layout.user_homepage_fragment, container, false);
        buildViewPager(view);
        buildRecycleView(view);
        return view;
        // Inflate the layout for this fragment
    }

    private void buildViewPager(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        circleIndicator = (CircleIndicator) view.findViewById(R.id.circle_indicator);

        mListPhoto = getListPhoto();
        photoAdapter = new PhotoAdapter(view.getContext(), mListPhoto);
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

    void buildRecycleView(View view){
        rcvCategory = (RecyclerView) view.findViewById(R.id.rcv_category);
        categoryAdapter = new CategoryAdapter(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL,false);
        rcvCategory.setLayoutManager(linearLayoutManager);
        listCategory = getListCategory();
        categoryAdapter.setData(listCategory);
        rcvCategory.setAdapter(categoryAdapter);

        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Category category=listCategory.get(position);

                //Toast.makeText(getBaseContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Activity_restaurant_info.class);
                intent.putExtra("restaurantID",position);
                startActivityForResult(intent, REQUEST_CODE_CHECKOUT);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHECKOUT && requestCode == getActivity().RESULT_OK){
            Log.e(TAG, "onActivityResult: OK");
            ((MainCustomerActivity) getActivity()).setOrderFragment();
        }
    }
}