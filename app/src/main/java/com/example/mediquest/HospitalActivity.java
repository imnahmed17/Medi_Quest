package com.example.mediquest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import maes.tech.intentanim.CustomIntent;

public class HospitalActivity extends AppCompatActivity {

    ImageView backicon;
    SearchView searchView;
    ListView listView;
    ArrayAdapter<String> ad;

    String[] names = {"Ad-din Medical College & Hospital, Dhaka", "Green Life Hospital Limited", "Green View Clinic (Green Road)", "Labaid Diagnostic Center (Old Dhaka City)",
            "Labaid Specialized Hospital (Dhanmondi)", "Popular Diagnostic Center Ltd (Chashara)", "Popular Diagnostic Center Ltd (Mirpur)", "Popular Diagnostic Center Ltd (Shantinagar)",
            "Popular Diagnostic Center Ltd (Shyamoli)", "Popular Diagnostic Center Ltd (Uttara)", "Samorita Hospital Ltd", "Square Hospitals Ltd"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        backicon = (ImageView) findViewById(R.id.back_icon_Id);
        searchView = findViewById(R.id.search_id);
        listView = findViewById(R.id.listView_id);

        ad = new ArrayAdapter<>(HospitalActivity.this, android.R.layout.simple_list_item_activated_1, names);
        listView.setAdapter(ad);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ad.getFilter().filter(newText);
                if(listView.getVisibility() == View.GONE) {
                    listView.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    Intent i = new Intent(HospitalActivity.this, AddinMedicalCollegeandHospital.class);
                    startActivity(i);
                    CustomIntent.customType(HospitalActivity.this, "left-to-right");
                }
                if(position == 1) {
                    //nmActivity();
                }
                if(position == 2) {
                    //alActivity();
                }
                if(position == 3) {
                    //deptActivity();
                }
                if(position == 4) {
                    //caActivity();
                }
                if(position == 5) {
                    //assemblyActivity();
                }
            }
        });

        // Back to previous page
        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HospitalActivity.this, HomePageActivity.class);
                startActivity(i);
                CustomIntent.customType(HospitalActivity.this, "fadein-to-fadeout");
            }
        });
    }
}