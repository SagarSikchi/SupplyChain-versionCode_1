package in.livechain.supplychain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private WebView mywebView_main;
    ImageButton scanner_btn_1;
    ImageButton rate_btn_1;
    ImageButton info_btn_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mywebView_main=(WebView) findViewById(R.id.webview_main);
        scanner_btn_1 = findViewById(R.id.scanner_btn_1);
        rate_btn_1 = findViewById(R.id.rate_btn_1);
        info_btn_1 = findViewById(R.id.info_btn_1);

        mywebView_main.setWebViewClient(new WebViewClient());
        mywebView_main.loadUrl("https://livechain.in/");
        WebSettings webSettings=mywebView_main.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);


        scanner_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScannerActivity.class));
            }
        });

        rate_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(getApplicationContext(), ShowActivity.class));
                // after app publishing direct to play store
                gotoUrl("https://play.google.com/store/apps/details?id=in.livechain.supplychain");
            }
        });

        info_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AboutUsActivity.class));
            }
        });
    }

    private void gotoUrl(String url_path) {
        Uri uri = Uri.parse(url_path);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    public class mywebClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            Database.url_path = url;
            startActivity(new Intent(getApplicationContext(), ShowActivity.class));
            // view.loadUrl(url);
            return true;
        }
    }
    @Override
    public void onBackPressed(){
        if(mywebView_main.canGoBack()) {
            mywebView_main.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}