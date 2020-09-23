package com.example.mediquest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BloodBankActivity extends AppCompatActivity {

    WebView webviewID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank);

        webviewID = findViewById(R.id.webviewID);

        WebSettings webSettings =  webviewID.getSettings() ;
        webSettings.setJavaScriptEnabled(true);

        webviewID.setWebViewClient(new WebViewClient());
        webviewID.loadUrl("https://www.save.life/");
    }
    @Override
    public void onBackPressed() {
        if(webviewID.canGoBack())
        {
            webviewID.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}