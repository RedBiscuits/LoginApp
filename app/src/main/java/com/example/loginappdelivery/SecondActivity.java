package com.example.loginappdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

public class SecondActivity extends AppCompatActivity {

    TextView name;
    TextView email;
    ImageView picture;
    Button signout_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getSupportActionBar().hide();

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        picture=findViewById(R.id.google_picture);
        signout_button=findViewById(R.id.signout_button);

        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(googleSignInAccount != null)
        {
            String personName=googleSignInAccount.getDisplayName();
            String personEmail = googleSignInAccount.getEmail();
            Uri image_url = googleSignInAccount.getPhotoUrl();
            name.setText(personName);
            email.setText(personEmail);
            Picasso.get().load(image_url).into(picture);
        }

        signout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int success = Login.googleSignOut();
                if(success == 1){
                    finish();
                    startActivity(new Intent(SecondActivity.this, MainActivity.class));
                }
                else
                    Toast.makeText(SecondActivity.this, ":)"
                            , Toast.LENGTH_SHORT).show();
            }
        });

    }

}