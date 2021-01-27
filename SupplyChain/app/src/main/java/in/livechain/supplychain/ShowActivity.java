package in.livechain.supplychain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import java.util.Scanner;

import static in.livechain.supplychain.Database.url_path;

public class ShowActivity extends AppCompatActivity {
    private WebView mywebView_show;
    ImageButton scanner_btn_2;
    ImageButton rate_btn_2;
    ImageButton info_btn_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        mywebView_show=(WebView) findViewById(R.id.webview_show);
        scanner_btn_2 = findViewById(R.id.scanner_btn_2);
        rate_btn_2 = findViewById(R.id.rate_btn_2);
        info_btn_2 = findViewById(R.id.info_btn_2);
        mywebView_show.setWebViewClient(new WebViewClient());
        mywebView_show.loadUrl(url_path);
        WebSettings webSettings = mywebView_show.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);

        scanner_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScannerActivity.class));
            }
        });

        rate_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://play.google.com/store/apps/details?id=in.livechain.supplychain");
            }
        });

        info_btn_2.setOnClickListener(new View.OnClickListener() {
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
            super.onPageStarted(view, url_path, favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            Database.url_path = url;
            view.loadUrl(url_path);
            return true;
        }
    }
    @Override
    public void onBackPressed(){
        if(mywebView_show.canGoBack()) {
            mywebView_show.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
//    public void goOnUrl() {
//        mywebView_show.loadUrl(url_path);
//    }
}