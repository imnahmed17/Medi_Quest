package com.example.mediquest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import maes.tech.intentanim.CustomIntent;

public class EditProfileActivity extends AppCompatActivity {

    public static final String TAG = "tag";
    EditText editfName, edituName, editemail, editphone;
    Button saveBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent data = getIntent();
        String fullName = data.getStringExtra("FullName");
        String userName = data.getStringExtra("Username");
        String email = data.getStringExtra("Email");
        String phone = data.getStringExtra("Phone");

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();

        editfName = findViewById(R.id.editFName);
        edituName = findViewById(R.id.editUName);
        editemail = findViewById(R.id.editEmail);
        editphone = findViewById(R.id.editPhone);
        saveBtn = findViewById(R.id.saveButton);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editfName.getText().toString().isEmpty() || edituName.getText().toString().isEmpty() || editemail.getText().toString().isEmpty() || editphone.getText().toString().isEmpty()) {
                    Toast.makeText(EditProfileActivity.this, "One or multiple fields are empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                final String email = editemail.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference docRef = fStore.collection("users").document(user.getUid());
                        Map<String, Object> edited = new HashMap<>();
                        edited.put("Email", email);
                        edited.put("FullName", editfName.getText().toString());
                        edited.put("Username", edituName.getText().toString());
                        edited.put("Phone", editphone.getText().toString());
                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(EditProfileActivity.this, "Profile is Updated.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                                CustomIntent.customType(EditProfileActivity.this, "left-to-right");
                                finish();
                            }
                        });
                        //Toast.makeText(EditProfileActivity.this, "Email is changed.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        editfName.setText(fullName);
        edituName.setText(userName);
        editemail.setText(email);
        editphone.setText(phone);

        Log.d(TAG, "onCreate: " + fullName + " " + userName + " " + email + " " + phone);
    }
}