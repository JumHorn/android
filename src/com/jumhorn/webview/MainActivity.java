package com.jumhorn.webview;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.webview);

      // init webview
      WebView myWebView = (WebView) findViewById(R.id.webview);;
	  myWebView.loadUrl("https://www.jumhorn.com:50443/main.html");
	  WebSettings webSettings = myWebView.getSettings();
	  webSettings.setJavaScriptEnabled(true);
   }
}