/**
 * Copyright 2011 Mark Wyszomierski
 */
package com.foursquare.android.oauth;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * http://groups.google.com/group/foursquare-api/web/oauth?pli=1
 * 
 * @date May 17, 2011
 * @author Mark Wyszomierski (markww@gmail.com)
 */
public class ActivityWebView extends Activity 
{
	private static final String TAG = "ActivityWebView";
	
	/**
	 * Get these values after registering your oauth app at: https://foursquare.com/oauth/
	 */
	public static final String CALLBACK_URL = "your callback url";
	public static final String CLIENT_ID = "your client id";
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        
        String url = 
	        "https://foursquare.com/oauth2/authenticate" +
	        	"?client_id=" + CLIENT_ID + 
	        	"&response_type=code" + 
	        	"&redirect_uri=" + CALLBACK_URL;

        WebView webview = (WebView)findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.addJavascriptInterface(new JavascriptAccessor(), "javascriptAccessor");
        webview.setWebViewClient(new WebViewClient() {});
        webview.loadUrl(url);
    }

    private class JavascriptAccessor {
        @SuppressWarnings("unused")
		public void getOAuthToken(String token) {
            // Our web page will execute this method for us with the access token
        	// if oauth worked correctly. We could then finish this activity and
        	// use the token for making API calls.
        	Log.v(TAG, "OAuth complete, token: [" + token + "].");
        	
        	Toast.makeText(ActivityWebView.this, "Token: " + token, Toast.LENGTH_SHORT).show();
        }
    }
}