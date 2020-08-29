//package com.example.weatherapp;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthCredential;
//import com.google.firebase.auth.EmailAuthProvider;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//
//public class UpdateProfile extends AppCompatActivity {
//
////    private static final String TAG = EditProfileActivity.class.getSimpleName();
//    Button btnsave;
//    FirebaseUser user;
//    private FirebaseAuth firebaseAuth;
//    private TextView textViewemailname;
//    private DatabaseReference databaseReference;
//    private EditText editTextEmail;
//    private EditText editTextPass;
//    private EditText editTextVPass;
////    private FirebaseStorage firebaseStorage;
//    private static int PICK_IMAGE = 123;
////    Uri imagePath;
////    private StorageReference storageReference;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile);
//
//        firebaseAuth = FirebaseAuth.getInstance();
//        user = FirebaseAuth.getInstance().getCurrentUser();
//        if (firebaseAuth.getCurrentUser() == null){
//            finish();
//            startActivity(new Intent(getApplicationContext(),Login.class));
//        }
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//        editTextEmail = (EditText)findViewById(R.id.text_email);
//        editTextPass = (EditText)findViewById(R.id.text_password);
//        editTextPass.setText(null);
//        editTextVPass = (EditText)findViewById(R.id.text_vpassword);
//        btnsave=(Button)findViewById(R.id.button_save);
//        btnsave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(!editTextEmail.getText().toString().equals(user.getEmail())){
//                    updateEmail(editTextEmail.getText().toString());
//                    Toast.makeText(getApplicationContext(),"Email has been updated",Toast.LENGTH_SHORT).show();
//                }
//                else if (!editTextPass.getText().toString().isEmpty()){
//                    if (editTextPass.getText().toString().equals(editTextVPass.getText().toString())){
//                        updatePass(editTextPass.getText().toString());
//                    }
//                    else {
//                        Toast.makeText(getApplicationContext(),"Password not match",Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else {
//                    Toast.makeText(getApplicationContext(),"Data not change",Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//        });
////
//        editTextEmail.setText(user.getEmail());
////        editTextPass.setText(user.);
////        btnsave.setOnClickListener(this);
////        textViewemailname=(TextView)findViewById(R.id.textViewEmailAdress);
////        textViewemailname.setText(user.getEmail());
////        profileImageView = findViewById(R.id.update_imageView);
////        firebaseStorage = FirebaseStorage.getInstance();
////        storageReference = firebaseStorage.getReference();
//
//    }
//
//    private void updatePass(final String pass) {
//        // Get auth credentials from the user for re-authentication. The example below shows
//        // email and password credentials but there are multiple possible providers,
//        // such as GoogleAuthProvider or FacebookAuthProvider.
//        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(),"password");
//
//        // Prompt the user to re-provide their sign-in credentials
//        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                Log.d("reauth", "User re-authenticated.");
//                user.updatePassword(pass)
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()) {
//                                    Log.d("updatePass", "User password updated.");
//                                    Toast.makeText(getApplicationContext(),"Password has been updated",Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//            }
//        });
//    }
//
//    private void updateEmail(String email){
//        user.updateEmail(email)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Log.d("CHANGE EMAIL", "User email address updated.");
//                        }
//                    }
//                });
//    }
//
//    private void deleteAcc() {
//        // Get auth credentials from the user for re-authentication. The example below shows
//        // email and password credentials but there are multiple possible providers,
//        // such as GoogleAuthProvider or FacebookAuthProvider.
//        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(),"password");
//
//        // Prompt the user to re-provide their sign-in credentials
//        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                Log.d("reauth", "User re-authenticated.");
//                user.delete()
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()) {
//                                    Toast.makeText(getApplicationContext(),"Account has been deleted",Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(getApplicationContext(),Login.class));
//                                    Log.d("deleteAcc", "User account deleted.");
//                                }
//                            }
//                        });
//            }
//        });
//    }
//
//    private void signOut(){
//        firebaseAuth.signOut();
//        startActivity(new Intent(getApplicationContext(),Login.class));
//        Log.d("signout", "User sign out.");
//    }
//}