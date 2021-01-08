package com.example.grabfood.Login;
import com.example.grabfood.Customer.MainCustomerActivity;
import com.example.grabfood.Customer.MainCustomerActivity_;
import com.example.grabfood.Helper.User;
import com.example.grabfood.MainActivity;
import com.example.grabfood.R;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.grabfood.Restaurant.MainRestaurantActivity;
import com.example.grabfood.Shipper.MainShipperActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.ceryle.radiorealbutton.RadioRealButtonGroup;

public class SignUpActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private String TAG = "SignUpActivity";
    private RadioRealButtonGroup btn_group;
    private Button mSignUpBtn;
    private TextView mPhoneNumber, mPassword1, mPassword2, mEmail, mFullname, mAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mPhoneNumber = (TextView) findViewById(R.id.reg_phonenumber);
        mPassword1 = (TextView) findViewById(R.id.reg_password);
        mPassword2 = (TextView) findViewById(R.id.reg_password2);
        mEmail = (TextView) findViewById(R.id.reg_email);
        mAddress = (TextView) findViewById(R.id.reg_address);
        mFullname = (TextView) findViewById(R.id.reg_fullname);

        btn_group = (RadioRealButtonGroup) findViewById(R.id.user_type_group_btn);
        btn_group.setPosition(0);

        mSignUpBtn = (Button) findViewById(R.id.btn_register);
        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonenumber = mPhoneNumber.getText().toString();
                String password1 = mPassword1.getText().toString();
                String password2 = mPassword2.getText().toString();
                String email = mEmail.getText().toString();
                String fullname = mFullname.getText().toString();
                String address = mAddress.getText().toString();
                int type = btn_group.getPosition();
                User user = generateUser(phonenumber, password1, address, email, fullname, type);
                goToHomepage(user);
            }
        });


    }

    public User generateUser(String phonenumber, String password, String address, String email, String fullname, int type) {
        User user = new User(phonenumber, password, address, email, fullname, type); //ObjectClass for Users
        mDatabase.child("users").child(phonenumber).setValue(user);
        Log.e(TAG, "User created");
        return user;
    }


    private void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void goToHomepage(User user){
        int type = user.getType();
        switch (type){
            case 0:
                goToMainCustomerActivity();
                break;
            case 1:
                goToMainShipperActivity();
                break;
            case 2:
                goToMainRestaurantActivity();
                break;
            default:
                break;
        }
    }

    private void goToMainRestaurantActivity(){
        Intent intent = new Intent(this, MainRestaurantActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void goToMainShipperActivity(){
        Intent intent = new Intent(this, MainShipperActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void goToMainCustomerActivity(){
        Intent intent = new Intent(this, MainCustomerActivity.class);
        startActivity(intent);
        this.finish();
    }
}