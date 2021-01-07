package com.example.grabfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.grabfood.Login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private int LOGOUT_REQUEST = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button logout_btn = (Button) findViewById(R.id.link_logout);
        logout_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goToLoginActivity();
            }
        });
    }

    private void goToLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("requestCode", LOGOUT_REQUEST);
        startActivity(intent);
        this.finish();
    }
}