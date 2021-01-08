package com.example.grabfood.Login;
import com.example.grabfood.Customer.MainCustomerActivity;
import com.example.grabfood.Helper.User;
import com.example.grabfood.MainActivity;
import com.example.grabfood.R;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.grabfood.Restaurant.MainRestaurantActivity;
import com.example.grabfood.Shipper.MainShipperActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import com.facebook.FacebookSdk;

public class    LoginActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private String TAG = "LoginActivity";
    private EditText mPhoneNumber, mPassword;
    private TextView mToSignUp;
    private Button loginBtn;
    private int RC_SIGN_IN = 0;
    private int LOGOUT_REQUEST = 123;


    private CallbackManager mCallbackManager;
    private FirebaseAuth mFirebaseAuth;
    private LoginButton signInFacebook;
    private AccessTokenTracker accessTokenTracker;
    GoogleSignInClient mGoogleSignInClient;
    SignInButton signInGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mPhoneNumber = (EditText) findViewById(R.id.input_phonenumber);
        mPassword = (EditText) findViewById(R.id.input_password);
        mToSignUp = (TextView) findViewById(R.id.link_signup);
        loginBtn = (Button) findViewById(R.id.btn_login);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");

        mCallbackManager = CallbackManager.Factory.create();
        signInFacebook = (LoginButton) findViewById(R.id.facebookButton);
        signInFacebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e(TAG, "Facebook log in success");
                handleFacebookToken(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {
                Log.e(TAG, "Facebook log in failed");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, "Facebook log in failed");
            }
        });

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken == null){
                    mFirebaseAuth.signOut();
                }
            }
        };


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signInGoogle = (SignInButton) findViewById(R.id.googleButton);
        signInGoogle.setSize(SignInButton.SIZE_STANDARD);

        signInGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.googleButton:
                        signInGoogle();
                        break;
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = mPhoneNumber.getText().toString();
                String password = mPassword.getText().toString();
                final Query phoneQuery = databaseReference.child(phoneNumber);
                phoneQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Log.e(TAG, "Phone number found");
                            User user = dataSnapshot.getValue(User.class);

                            String true_password = user.getPassword();
                            if (true_password.equals(password)){
                                Log.e(TAG, "Log in successsfully, type: "+ String.valueOf(user.getType()));
                                goToHomepage(user);
                            }
                            else {
                                Log.e(TAG, "Wrong password");
                            }
                        }
                        else{
                            Log.e(TAG, "Phone number not found");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });


        mToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUpActivity();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if( getIntent().getExtras() != null)
        {
            int requestCode = getIntent().getExtras().getInt("requestCode");
            if (requestCode == LOGOUT_REQUEST){
                signOutGoogle();
            }
        }

    }

    private void signInGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOutGoogle(){
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.e(TAG, "Google log out: successfully");
                    }
                });
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());

        }
    }

    private void handleFacebookToken(AccessToken token){
        Log.d(TAG, "haandlde Facecbook Token " + token);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());

        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d(TAG, "sign in with credential: successful");
                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                }
                else{
                    Log.d(TAG, "sign in with credential: failure", task.getException());

                }
            }
        });
    }

    private void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void goToSignUpActivity(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void goToHomepage(User user){
        int type = user.getType();
        switch (type){
            case 0:
                goToCustomerActivity();
                break;
            case 1:
                goToShipperActivity();
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
//        this.finish();
    }

    private void goToShipperActivity(){
        Intent intent = new Intent(this, MainShipperActivity.class);
        startActivity(intent);
//        this.finish();
    }

    private void goToCustomerActivity(){
        Intent intent = new Intent(this, MainCustomerActivity.class);
        startActivity(intent);
//        this.finish();
    }
}