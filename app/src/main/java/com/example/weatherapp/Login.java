package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class Login extends AppCompatActivity {
    LoginButton loginButton;
    CallbackManager mCallbackManager;
    //    private SignInButton signInGoogle;
    private GoogleSignInClient mGoogleSignInClient;
//    private int RC_SIGN_IN = 1;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    EditText edEmail, edPassword;
    Button btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = (EditText) findViewById(R.id.ed_username);
        edPassword = (EditText) findViewById(R.id.ed_password);

        //initialise Firebase
        mAuth = FirebaseAuth.getInstance();

        btnSignin = findViewById(R.id.btn_signin);
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edEmail.getText().toString().trim();
                String password = edPassword.getText().toString().trim();



                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(Login.this, "Password too short", Toast.LENGTH_SHORT).show();
                    return;
                }
                // code sementara karena firebase blom bisa
                else {
//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                    Toast.makeText(Login.this, "Login Successed", Toast.LENGTH_SHORT).show();
                    mAuth.signInWithEmailAndPassword(email,password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
//                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        updateUI(user);
                                        Toast.makeText(Login.this, "Login Successed", Toast.LENGTH_SHORT).show();

                                    } else {

                                        Toast.makeText(Login.this, "Login Failed or User not Available", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }


            }
        });

        TextView btnSignup = findViewById(R.id.tv_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
                finish();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }

    private void signUp(){
        Intent goRegister = new Intent(Login.this, SignUp.class);
        startActivity(goRegister);
    }
    public void updateUI (FirebaseUser currentUser){
        Intent main = new Intent(this,MainActivity.class);
        startActivity(main);

    }
}