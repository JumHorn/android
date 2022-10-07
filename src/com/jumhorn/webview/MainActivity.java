package com.jumhorn.webview;

import com.jumhorn.R;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.webview);

      // init webview
      WebView webView = (WebView) findViewById(R.id.webview);
      WebSettings webSettings = webView.getSettings();
      webSettings.setJavaScriptEnabled(true);
      webSettings.setDomStorageEnabled(true);
      webSettings.setLoadWithOverviewMode(true);
      webSettings.setUseWideViewPort(true);
      webSettings.setBuiltInZoomControls(true);
      webSettings.setDisplayZoomControls(false);
      webSettings.setSupportZoom(true);
      webSettings.setDefaultTextEncodingName("utf-8");

      webView.setWebViewClient(new WebViewClient());
      webView.addJavascriptInterface(new WebAppInterface(this), "Android");
      webView.loadUrl("http://www.jumhorn.com:50443/main.html");
   }
}