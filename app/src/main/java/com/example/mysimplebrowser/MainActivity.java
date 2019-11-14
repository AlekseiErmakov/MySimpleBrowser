package com.example.mysimplebrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private WebView web;
    private AutoCompleteTextView TextView;
    private Button GoToWeb;
    private WebSettings ws;
    private ArrayAdapter adapter;
    private final static String URLstart ="http:/";
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

        web.loadUrl(URLstart+url);
    }
    public void addText(){
        TextView = (AutoCompleteTextView)findViewById(R.id.textV);

        TextView.setOnEditorActionListener(new EditText.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                if (actionId == EditorInfo.IME_ACTION_DONE)
                {
                   goToWebsite();
                    return true;
                }
                return false;
            }

        });
        adapter=new ArrayAdapter(this,android.R.layout.select_dialog_item,DataBaseSites.spisok);
        TextView.setThreshold(2);
        TextView.setAdapter(adapter);

    }

    public static class DataBaseSites{
        public static ArrayList<String> spisok = new ArrayList<>();
        public static void addToSpisok(){
            spisok.add("vk.com/");
            spisok.add("yandex.ru/");
            spisok.add("google.com/");
            spisok.add("mail.ru/");
            spisok.add("hh.ru/");
            spisok.add("youtube.com/");
            spisok.add("matchtv.ru/");
            spisok.add("pikabu.ru/");
            spisok.add("javarush.ru/");
            spisok.add("uralairlines.ru/");

        }
    }
}
