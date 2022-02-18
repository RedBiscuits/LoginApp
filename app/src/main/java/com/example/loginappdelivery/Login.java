package com.example.loginappdelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

public abstract class Login {
    static GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.
            Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
    static GoogleSignInClient googleSignInClient ;
    static boolean done=false;
    static int which_state = 0;
    static Intent googleLogin(Activity activity){
        googleSignInClient = GoogleSignIn.getClient(activity,googleSignInOptions);

        Intent signinIntent = googleSignInClient.getSignInIntent();
        done = false;
        return signinIntent;
    }

    static int googleSignOut(){
        googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                done = true;
            }
        });
        if (done)
            return 1;
        else
            return 0;
    }
}
