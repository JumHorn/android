package com.jumhorn.webview;

import android.content.Context;
import android.widget.Button;
import android.widget.Toast;
import android.app.AlertDialog;
import android.webkit.JavascriptInterface;

public class WebAppInterface {
    Context mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

	//show alert
	@JavascriptInterface
    public void showAlert(String msg) {
		new AlertDialog.Builder(mContext)
		.setMessage(msg)
		// A null listener allows the button to dismiss the dialog and take no further action.
		.setNegativeButton(android.R.string.no, null)
		.show();
    }
}