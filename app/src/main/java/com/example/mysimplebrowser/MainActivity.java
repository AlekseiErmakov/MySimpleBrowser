package com.example.mysimplebrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    WebView web;
    AutoCompleteTextView TextView;
    Button GoToWeb;
    WebSettings ws;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseSites.addToSpisok();
        addWebView();
        addText();
        addButton();

    }
    public void addWebView(){
        web = (WebView)findViewById(R.id.webview);
        ws =web.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        web.setWebViewClient(new WebViewClient());
    }
    public void onBackPressed(){
        if (web.canGoBack())
            web.goBack();
        else
            super.onBackPressed();
    }
    public void addButton(){
       GoToWeb = (Button)findViewById(R.id.go);
       GoToWeb.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               goToWebsite();
           }
       });

    }
    public void goToWebsite(){
        String url = String.valueOf(TextView.getText());
        System.out.println(url);
        web.loadUrl(url);
    }
    public void addText(){
        TextView = (AutoCompleteTextView)findViewById(R.id.textV);

        TextView.setOnEditorActionListener( new android.widget.TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView exampleView, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    goToWebsite();//match this behavior to your 'Send' (or Confirm) button
                }
                return true;
            }
    });
    }







    public static class DataBaseSites{
        public static ArrayList<String> spisok = new ArrayList<>();
        public static void addToSpisok(){
            spisok.add("http://vk.com/");
            spisok.add("http://yandex.ru/");
            spisok.add("http://google.com/");
            spisok.add("http://mail.ru/");
            spisok.add("http://hh.ru/");
            spisok.add("http://youtube.com/");
            spisok.add("http://matchtv.ru/");
            spisok.add("http://pikabu.ru/");
            spisok.add("http://javarush.ru/");
            spisok.add("https://www.uralairlines.ru/");

        }
    }
}
