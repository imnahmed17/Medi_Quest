package com.example.mediquest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    TextView textView;
    EditText fullName, userName, u_email, phoneNo, u_password;
    Button btnSignUp;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore fstore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirebaseAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        fullName = findViewById(R.id.fullnameId);
        userName = findViewById(R.id.usernameId);
        u_email = findViewById(R.id.emailId);
        phoneNo = findViewById(R.id.phoneNoId);
        u_password = findViewById(R.id.passwordId);

        btnSignUp = findViewById(R.id.signupButtonId);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullname = fullName.getText().toString();
                final String username = userName.getText().toString();
                final String email = u_email.getText().toString();
                final String phone = phoneNo.getText().toString();
                String password = u_password.getText().toString();

                if(fullname.isEmpty()) {
                    fullName.setError("Please enter your full name");
                    fullName.requestFocus();
                }
                else if(username.isEmpty()) {
                    userName.setError("Please enter an username");
                    userName.requestFocus();
                }
                else if(email.isEmpty()) {
                    u_email.setError("Please enter an email address");
                    u_email.requestFocus();
                }
                else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    u_email.setError("Please enter a valid email address");
                    u_email.requestFocus();
                }
                else if(phone.isEmpty()) {
                    phoneNo.setError("Please enter your phone number");
                    phoneNo.requestFocus();
                }
                else if(phone.length() < 11) {
                    phoneNo.setError("Provide a correct phone number");
                    phoneNo.requestFocus();
                }
                else if(password.isEmpty()) {
                    u_password.setError("Enter your password");
                    u_password.requestFocus();
                }
                else if(password.length() < 6) {
                    u_password.setError("Minimum length of a password should be 6");
                    u_password.requestFocus();
                }
                else if(email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && password.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                //Send Varification Email
                                FirebaseUser fuser = mFirebaseAuth.getCurrentUser();
                                fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(SignUpActivity.this, "Verification Email Has Been Sent", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
                                    }
                                });
                                //Store user information in Firebase
                                Toast.makeText(SignUpActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                userID = mFirebaseAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fstore.collection("users").document(userID);
                                Map<String, Object> user = new HashMap<>();
                                user.put("FullName", fullname);
                                user.put("Username", username);
                                user.put("Email", email);
                                user.put("Phone", phone);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "onSuccess: user Profile is created for " + userID);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure: " + e.toString());
                                    }
                                });
                                startActivity(new Intent(SignUpActivity.this, HomePageActivity.class));
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(SignUpActivity.this, "Error Ocurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textView = (TextView) findViewById(R.id.alreadyLoginTextView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}