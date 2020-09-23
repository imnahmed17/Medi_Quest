package com.example.mediquest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AmbulanceActivity extends AppCompatActivity {

    WebView webviewID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);

        webviewID = findViewById(R.id.webviewID);

        WebSettings webSettings =  webviewID.getSettings() ;
        webSettings.setJavaScriptEnabled(true);

        webviewID.setWebViewClient(new WebViewClient());
        webviewID.loadUrl("https://uralems.com/booknow/");
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