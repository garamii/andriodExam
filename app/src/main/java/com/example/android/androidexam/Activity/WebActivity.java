package com.example.android.androidexam.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.example.android.androidexam.R;

public class WebActivity extends AppCompatActivity implements View.OnKeyListener {
    private WebView mWebView;
    private EditText mAdreess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        mWebView = (WebView) findViewById(R.id.web_view);
        mAdreess = (EditText) findViewById(R.id.et_input_web);

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);

        mAdreess.setOnKeyListener(this);
    }


    private void loadUrl(String url){
        mWebView.loadUrl(url);

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_ENTER){
            loadUrl("http://"+mAdreess.getText().toString());
            return true;

        }
        return false;
    }
}
