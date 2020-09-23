package com.example.mediquest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import maes.tech.intentanim.CustomIntent;

public class AddinMedicalCollegeandHospital extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    ImageView backicon;
    TextView call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addin_medical_collegeand_hospital);

        backicon = (ImageView) findViewById(R.id.back_icon_Id);
        call = (TextView) findViewById(R.id.call_id);

        // Back to previous page
        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddinMedicalCollegeandHospital.this, HospitalActivity.class);
                startActivity(i);
                CustomIntent.customType(AddinMedicalCollegeandHospital.this, "fadein-to-fadeout");
            }
        });
    }
}