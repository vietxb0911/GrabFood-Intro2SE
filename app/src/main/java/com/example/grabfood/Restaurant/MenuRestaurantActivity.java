package com.example.grabfood.Restaurant;
import com.example.grabfood.R;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
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
    Dialog dialog;

    private int  REQUEST_UPLOAD_FOOD_PHOTO = 1;
    private int  REQUEST_IMAGE_CAPTURE = 2;

    private String TAG  = "MenuRestaurantActivity";

    private Button btnAdd;
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
        ImageView ivFood;
        Food food = mFoods.get(i);
        btnDone = (Button)dialog.findViewById(R.id.btnDone);
        btnBack = (Button)dialog.findViewById(R.id.btnCancel);
        ivFood = (ImageView)dialog.findViewById(R.id.imFood);

        btnBack.setText("DELETE");
        edName = (EditText)dialog.findViewById(R.id.edName);
        edPrice =(EditText)dialog.findViewById(R.id.edPrice);
        edName.setText(food.name);
        edPrice.setText(food.price);
        ivFood.setImageBitmap(food.getBitmap());
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
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_PROGRESS);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_add_food);
        dialog.setCanceledOnTouchOutside(false);


        EditText edName,edPrice;
        ImageView edBitmap;
        ImageButton btnUpload, btnCamera;
        Button btnDone,btnBack;


        btnUpload = (ImageButton)dialog.findViewById(R.id.uploadBtn);
        btnCamera = (ImageButton)dialog.findViewById(R.id.cameraBtn);
        btnDone = (Button)dialog.findViewById(R.id.btnDone);
        btnBack = (Button)dialog.findViewById(R.id.btnCancel);
        edName = (EditText)dialog.findViewById(R.id.edName);
        edPrice =(EditText)dialog.findViewById(R.id.edPrice);
        edBitmap = (ImageView) dialog.findViewById(R.id.edBitmap);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFoodImage();
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePictureIntent();
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
                    BitmapDrawable drawable = (BitmapDrawable) edBitmap.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    Food food = new Food(edName.getText().toString(),edPrice.getText().toString(), bitmap);
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


    private void chooseFoodImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_UPLOAD_FOOD_PHOTO);
    }

    private void takePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            Log.e(TAG, "takePictureIntent: successfully");
        } catch (ActivityNotFoundException e) {
            // display error state to the user
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_UPLOAD_FOOD_PHOTO && data!=null){
            Uri selectedImage = data.getData();

            InputStream inputStream = null;
            try {
                inputStream = getContentResolver().openInputStream(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ImageView ivFood = (ImageView) dialog.findViewById(R.id.edBitmap);
            ivFood.setImageBitmap(bitmap);
            Log.e(TAG, "Image received");
        }

        if (requestCode == REQUEST_IMAGE_CAPTURE && data!=null){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView ivFood;
            ivFood = (ImageView) dialog.findViewById(R.id.edBitmap);
            ivFood.setImageBitmap(imageBitmap);
        }
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
}