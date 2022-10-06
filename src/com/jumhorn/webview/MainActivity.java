package com.jumhorn.webview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;

public class MainActivity extends Activity {
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.webview);

      // init webview
      WebView myWebView = (WebView) findViewById(R.id.webview);
      myWebView.loadUrl("http://www.jumhorn.com:50443/main.html");
      WebSettings webSettings = myWebView.getSettings();
      webSettings.setJavaScriptEnabled(true);
   }
}