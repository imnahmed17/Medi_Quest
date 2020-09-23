package com.example.mediquest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import maes.tech.intentanim.CustomIntent;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    //Toolbar toolbar;
    LinearLayout l1, l2, l3, l4, l5, l6, l7, l8, l9;
    public CardView card1, card2, card3, card4, card5, card6, card7, card8, card9;
    TextView name, email, card1Text, card2Text, card3Text, card4Text, card5Text, card6Text, card7Text, card8Text, card9Text;
    ImageView menuicon;
    CircleImageView profilePic;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore fStore;
    StorageReference storageReference;
    LinearLayout contentView;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mFirebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        userId = mFirebaseAuth.getCurrentUser().getUid();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        //toolbar = findViewById(R.id.toolbar);
        l1 = findViewById(R.id.layout1id);
        l2 = findViewById(R.id.layout2id);
        l3 = findViewById(R.id.layout3id);
        l4 = findViewById(R.id.layout4id);
        l5 = findViewById(R.id.layout5id);
        l6 = findViewById(R.id.layout6id);
        l7 = findViewById(R.id.layout7id);
        l8 = findViewById(R.id.layout8id);
        l9 = findViewById(R.id.layout9id);
        card1 = (CardView) findViewById(R.id.card1Id);
        card2 = (CardView) findViewById(R.id.card2Id);
        card3 = (CardView) findViewById(R.id.card3Id);
        card4 = (CardView) findViewById(R.id.card4Id);
        card5 = (CardView) findViewById(R.id.card5Id);
        card6 = (CardView) findViewById(R.id.card6Id);
        card7 = (CardView) findViewById(R.id.card7Id);
        card8 = (CardView) findViewById(R.id.card8Id);
        card9 = (CardView) findViewById(R.id.card9Id);
        card1Text = (TextView) findViewById(R.id.card1_text_id);
        card2Text = (TextView) findViewById(R.id.card2_text_id);
        card3Text = (TextView) findViewById(R.id.card3_text_id);
        card4Text = (TextView) findViewById(R.id.card4_text_id);
        card5Text = (TextView) findViewById(R.id.card5_text_id);
        card6Text = (TextView) findViewById(R.id.card6_text_id);
        card7Text = (TextView) findViewById(R.id.card7_text_id);
        card8Text = (TextView) findViewById(R.id.card8_text_id);
        card9Text = (TextView) findViewById(R.id.card9_text_id);
        menuicon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);

        //hView = navigationView.getHeaderView(0);
        name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.name_id);
        email = (TextView) navigationView.getHeaderView(0).findViewById(R.id.email_id);
        profilePic = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.image_id);

        StorageReference profileRef = storageReference.child("users/"+mFirebaseAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profilePic);
            }
        });

        // Fetch User Data From Firestore
        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value.exists()) {
                    name.setText(value.getString("FullName"));
                    email.setText(value.getString("Email"));
                }
                else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });

        navigationDrawer();

        load_setting();

        card1.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card6.setOnClickListener(this);
        card7.setOnClickListener(this);
        card9.setOnClickListener(this);
    }
    // Dark Mood
    private void load_setting() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        boolean chk_night = sp.getBoolean("NIGHT", false);
        if(chk_night) {
            l1.setBackgroundColor(Color.parseColor("#141d26"));
            l2.setBackgroundColor(Color.parseColor("#141d26"));
            l3.setBackgroundColor(Color.parseColor("#141d26"));
            l4.setBackgroundColor(Color.parseColor("#141d26"));
            l5.setBackgroundColor(Color.parseColor("#141d26"));
            l6.setBackgroundColor(Color.parseColor("#141d26"));
            l7.setBackgroundColor(Color.parseColor("#141d26"));
            l8.setBackgroundColor(Color.parseColor("#141d26"));
            l9.setBackgroundColor(Color.parseColor("#141d26"));
            navigationView.setBackgroundColor(Color.parseColor("#141d26"));
            navigationView.setItemTextColor(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            navigationView.setItemIconTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            card1Text.setTextColor(Color.parseColor("#ffffff"));
            card2Text.setTextColor(Color.parseColor("#ffffff"));
            card3Text.setTextColor(Color.parseColor("#ffffff"));
            card4Text.setTextColor(Color.parseColor("#ffffff"));
            card5Text.setTextColor(Color.parseColor("#ffffff"));
            card6Text.setTextColor(Color.parseColor("#ffffff"));
            card7Text.setTextColor(Color.parseColor("#ffffff"));
            card8Text.setTextColor(Color.parseColor("#ffffff"));
            card9Text.setTextColor(Color.parseColor("#ffffff"));
        }
        else {
            l1.setBackgroundColor(Color.parseColor("#ffffff"));
            l2.setBackgroundColor(Color.parseColor("#ffffff"));
            l3.setBackgroundColor(Color.parseColor("#ffffff"));
            l4.setBackgroundColor(Color.parseColor("#ffffff"));
            l5.setBackgroundColor(Color.parseColor("#ffffff"));
            l6.setBackgroundColor(Color.parseColor("#ffffff"));
            l7.setBackgroundColor(Color.parseColor("#ffffff"));
            l8.setBackgroundColor(Color.parseColor("#ffffff"));
            l9.setBackgroundColor(Color.parseColor("#ffffff"));
            navigationView.setBackgroundColor(Color.parseColor("#ffffff"));
            navigationView.setItemTextColor(ColorStateList.valueOf(Color.parseColor("#6200EE")));
            navigationView.setItemIconTintList(ColorStateList.valueOf(Color.parseColor("#6200EE")));
            card1Text.setTextColor(Color.parseColor("#6f6f6f"));
            card2Text.setTextColor(Color.parseColor("#6f6f6f"));
            card3Text.setTextColor(Color.parseColor("#6f6f6f"));
            card4Text.setTextColor(Color.parseColor("#6f6f6f"));
            card5Text.setTextColor(Color.parseColor("#6f6f6f"));
            card6Text.setTextColor(Color.parseColor("#6f6f6f"));
            card7Text.setTextColor(Color.parseColor("#6f6f6f"));
            card8Text.setTextColor(Color.parseColor("#6f6f6f"));
            card9Text.setTextColor(Color.parseColor("#6f6f6f"));
        }

        String orien = sp.getString("ORIENTATION", "false");
        if("1".equals(orien)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
        }
        else if("2".equals(orien)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        else if("3".equals(orien)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    private void navigationDrawer() {
        //setSupportActionBar(toolbar);
        navigationView.bringToFront();
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawerLayout.addDrawerListener(toggle);
        //toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorBgApps1));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);
                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.card1Id:
                i = new Intent(HomePageActivity.this, HospitalActivity.class);
                startActivity(i);
                CustomIntent.customType(HomePageActivity.this, "left-to-right");
                break;

            case R.id.card3Id:
                i = new Intent(HomePageActivity.this, AmbulanceActivity.class);
                startActivity(i);
                CustomIntent.customType(HomePageActivity.this, "left-to-right");
                break;

            case R.id.card4Id:
                i = new Intent(HomePageActivity.this, BloodBankActivity.class);
                startActivity(i);
                CustomIntent.customType(HomePageActivity.this, "left-to-right");
                break;

            case R.id.card6Id:
                i = new Intent(HomePageActivity.this, HealthTipsActivity.class);
                startActivity(i);
                CustomIntent.customType(HomePageActivity.this, "left-to-right");
                break;

            case R.id.card7Id:
                i = new Intent(HomePageActivity.this, BMIActivity.class);
                startActivity(i);
                CustomIntent.customType(HomePageActivity.this, "left-to-right");
                break;

            case R.id.card9Id:
                FirebaseAuth.getInstance().signOut();
                finish();
                i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                CustomIntent.customType(HomePageActivity.this, "fadein-to-fadeout");
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent i;
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_profile:
                i = new Intent(HomePageActivity.this, ProfileActivity.class);
                startActivity(i);
                CustomIntent.customType(HomePageActivity.this, "left-to-right");
                break;

            case R.id.nav_settings:
                i = new Intent(HomePageActivity.this, Preference.class);
                startActivity(i);
                CustomIntent.customType(HomePageActivity.this, "left-to-right");
                break;

            case R.id.nav_share:
                i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "share demo");
                String shareMessage = "https://play.google.com/store/app/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                i.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(i, "share by"));
                break;
        }
        //drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        load_setting();
        super.onResume();
    }
}