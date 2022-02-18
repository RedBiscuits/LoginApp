package com.example.loginappdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //Normal Login
        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        //Login button
        MaterialButton login_btn = (MaterialButton) findViewById(R.id.login_btn);
        //login button listener
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("meow")//is cat
                &&  password.getText().toString().equals("meow")){
                    Toast.makeText(MainActivity.this , "You are a cat :3 "
                            ,Toast.LENGTH_LONG).show();
                }else if (username.getText().toString().equals("")//is empty
                        &&  password.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this , "Dude say smth :/ "
                            ,Toast.LENGTH_LONG).show();
                }else {//is not cat
                    Toast.makeText(MainActivity.this , "You are not a cat :c "
                            ,Toast.LENGTH_LONG).show();
                }
            }
        });//Listener

        //Google Login
        ImageView googleBtn = findViewById(R.id.google_img);
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login_intent = Login.googleLogin(MainActivity.this);
                startActivityForResult(login_intent,1000);
            }
        });//google listener

        //facebook login
        ImageView facebookBtn = findViewById(R.id.facebook_img);

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        startActivity(new Intent(MainActivity.this, FacebookActivity.class));
                        finish();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this , "Cancelled "
                                ,Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(FacebookException exception) {

                        Toast.makeText(MainActivity.this , exception.toString()
                                ,Toast.LENGTH_LONG).show();
                    }
                });


        facebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this
                        , Arrays.asList("public_profile"));
            }
        });

        TextView forgot_password = findViewById(R.id.forgot_password);
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,
                        "Hope you remember it :)" , Toast.LENGTH_LONG).show();
            }
        });


    }//OnCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(MainActivity.this , "Oops something went wrong try again please. "
                        ,Toast.LENGTH_LONG).show();
            }
        }
    }

    void navigateToSecondActivity(){
        finish();
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);
    }
}