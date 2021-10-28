package com.example.futsniper;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Button;
import android.widget.Toast;

public class JSClass {
    private Context ctx;
    Context childContext = settingsActivity.parentContext;

    public JSClass(Context ctx) {
        this.ctx = ctx;
    }

    @JavascriptInterface
    public void getToast(String text) {
        Toast.makeText(ctx, text, Toast.LENGTH_LONG).show();
    }

    @JavascriptInterface
    public int getSharedPreferencesFun(String key, String defaultValue) {
        SharedPreferences sharedPref = this.childContext.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        String txt = sharedPref.getString(key, defaultValue);
        return Integer.parseInt(txt);
    }

    @JavascriptInterface
    public String getSharedPreferencesFunString(String key, String defaultValue) {
        SharedPreferences sharedPref = this.childContext.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        String txt = sharedPref.getString(key, defaultValue);
        return txt;
    }
}
