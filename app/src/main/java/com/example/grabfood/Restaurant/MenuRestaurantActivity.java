package com.example.grabfood.Restaurant;
import com.example.grabfood.R;

import android.Manifest;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MenuRestaurantActivity extends AppCompatActivity {
    ListView listView;
    FoodAdapter foodAdapter;
    List<Food> mFoods;
    ImageView mImageView;
    ImageView imageView;
    Uri uri_image;
    private Button btnReturn,btnAdd;
    private static final int PERMISSION_CODE=1000;
    private int IMAGE_CAPTURE_CODE=1001;

    private String TAG  = "MenuRestaurantActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu_restaurant);

        btnAdd=(Button)findViewById(R.id.btnAddFood);
        listView = findViewById(R.id.lvFood);
        getListFood();


        foodAdapter = new FoodAdapter(this,mFoods);
        listView.setAdapter(foodAdapter);

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
        //ImageView ivFood;
        Food food = mFoods.get(i);
        btnDone = (Button)dialog.findViewById(R.id.btnDone);
        btnBack = (Button)dialog.findViewById(R.id.btnCancel);
        // ivFood = (ImageView)dialog.findViewById(R.id.imFood);

        btnBack.setText("DELETE");
        edName = (EditText)dialog.findViewById(R.id.edName);
        edPrice =(EditText)dialog.findViewById(R.id.edPrice);
        edName.setText(food.name);
        edPrice.setText(food.price);
        //  ivFood.setImageBitmap(food.getBitmap());
        mImageView=(ImageView)dialog.findViewById(R.id.edBitmap);
        mImageView.setImageURI(food.uri);
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
        mImageView=(ImageView)dialog.findViewById(R.id.edBitmap);
        ImageButton btnUpload, btnCamera;
        Button btnDone,btnBack;


        btnUpload = (ImageButton)dialog.findViewById(R.id.uploadBtn);
        btnCamera = (ImageButton)dialog.findViewById(R.id.cameraBtn);
        btnDone = (Button)dialog.findViewById(R.id.btnDone);
        btnBack = (Button)dialog.findViewById(R.id.btnCancel);
        edName = (EditText)dialog.findViewById(R.id.edName);
        edPrice =(EditText)dialog.findViewById(R.id.edPrice);


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFoodImage();
            }
        });




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
                    Food food = new Food(edName.getText().toString(),edPrice.getText().toString(), uri_image);
                    uri_image=null;
                    mFoods.add(food);
                    foodAdapter.notifyDataSetChanged();

                }

            }
        });
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView=(ImageView)dialog.findViewById(R.id.edBitmap);
                ShowCamera();
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


    private void chooseFoodImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    void ShowCamera(){
        //Intent intent = new Intent(this,Camera.class);
        //startActivityForResult(intent,1008);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.CAMERA)==
                    PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                            PackageManager.PERMISSION_DENIED){
                String[] permission = {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permission,PERMISSION_CODE);
            }
            else {
                openCamera();
            }
        }
        else
        {
            openCamera();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            mImageView.setImageURI(uri_image);
        }
    }
    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION,"From the Camera");
        uri_image = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values );
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,uri_image);
        startActivityForResult(cameraIntent,IMAGE_CAPTURE_CODE);
    }
    @Override
    public void onBackPressed() {
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
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openCamera();
                }
                else {
                    Toast.makeText(this,"Permission Denied...",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}