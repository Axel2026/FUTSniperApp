package com.example.futsniper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.ea.com/en-gb/fifa/ultimate-team/web-app/");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebView.setWebContentsDebuggingEnabled(true);

        Button settingsButton = (Button) findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, settingsActivity.class);
                startActivity(intent);
            }
        });

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                String script =
                        "var sendToMyClubFlag = JSClass.getSharedPreferencesFunString('sendToClub', 'false');" +
                                "var sendToTransferListFlag = JSClass.getSharedPreferencesFunString('consumables', 'sendToTransferList');" +
                                "var cardsSeen = 0;" +
                                "var refreshTimeSet = JSClass.getSharedPreferencesFun('refreshTime', '500');" +
                                "var maxRefreshPrice = JSClass.getSharedPreferencesFun('maxRefresh', '700');" +
                                "var maxCardsSeen = JSClass.getSharedPreferencesFun('maxCards', '5');" +
                                "var consumables = JSClass.getSharedPreferencesFunString('consumables', 'false');" +
                                "var inputFields = 7;" +
                                "async function FutExtension() {" +
                                "    var refreshTime = getRandomIntInclusive(500, 600);" +
                                "    if (maxRefreshPrice < 15000000 && cardsSeen < maxCardsSeen &&" +
                                "        (checkTab('ut-search-filter-control--row-image') === inputFields)) {" +
                                "        mouseClick('btn-standard call-to-action', 0);" +
                                "" +
                                "        await sleep(refreshTime);" +
                                "        var playerAvailable = document.getElementsByClassName('rowContent has-tap-callback');" +
                                "" +
                                "        if (playerAvailable[0]) {" +
                                "            mouseClick('rowContent has-tap-callback', 0);" +
                                "            await sleep(400);" +
                                "            mouseClick('btn-standard buyButton currency-coins', 0);" +
                                "" +
                                "            await sleep(300);" +
                                "            mouseClick('btn-text', 2);" +
                                "            cardsSeen++;" +
                                "            await sleep(800);" +
                                "            sendTo();" +
                                "            await sleep(50);" +
                                "            mouseClick('ut-navigation-button-control', 0);" +
                                "        }" +
                                "" +
                                "        mouseClick('ut-navigation-button-control', 0);" +
                                "        await sleep(refreshTime);" +
                                "        if (parseInt(getInputFieldValue()) < parseInt(maxRefreshPrice)) {" +
                                "            mouseClick('btn-standard increment-value', 0);" +
                                "        } else {" +
                                "            mouseClick('flat camel-case', 0);" +
                                "        }" +
                                "    } else {" +
                                "        JSClass.getToast('FUTSniper has been stopped' + 'Cards found: ' + cardsSeen);" +
                                "        return 0;" +
                                "    }" +
                                "    await sleep((refreshTime)*(8/5));" +
                                "    await FutExtension();" +
                                "}" +
                                "function triggerMouseEvent(node, eventType) {" +
                                "    var clickEvent = document.createEvent('MouseEvents');" +
                                "    clickEvent.initEvent(eventType, true, true);" +
                                "    node.dispatchEvent(clickEvent);" +
                                "}" +

                                "function mouseClick(className, index) {" +
                                "    var element = document.getElementsByClassName(className);" +
                                "" +
                                "    if (element[index]) {" +
                                "        triggerMouseEvent(element[index], 'mouseover');" +
                                "        triggerMouseEvent(element[index], 'mousedown');" +
                                "        triggerMouseEvent(element[index], 'mouseup');" +
                                "        triggerMouseEvent(element[index], 'click');" +
                                "    }" +
                                "}" +
                                "function sleep(ms) {" +
                                "    return new Promise(resolve => setTimeout(resolve, ms));" +
                                "}" +
                                "function checkTab(checkedClass) {" +
                                "    var el = document.getElementsByClassName(checkedClass);" +
                                "    return el.length;" +
                                "}" +
                                "function makeStopButton() {" +
                                "    var button = document.getElementById('buttonStop');" +
                                "    if (!button) {" +
                                "        var buttonStop = document.createElement('button');" +
                                "        buttonStop.innerHTML = 'Stop';" +
                                "        buttonStop.className = 'btn-standard';" +
                                "        buttonStop.id = 'buttonStop';" +
                                "        buttonStop.onclick = function () {" +
                                "            maxRefreshPrice = 15000001;" +
                                "        };" +
                                "        var box = document.getElementsByClassName('button-container');" +
                                "        box[0].appendChild(buttonStop);" +
                                "    }" +
                                "}" +
                                "function getRandomIntInclusive(min, max) {" +
                                "    min = Math.ceil(min);" +
                                "    max = Math.floor(max);" +
                                "    return Math.floor(Math.random() * (max - min + 1)) + min;" +
                                "}" +
                                "" +
                                "function getInputFieldValue() {" +
                                "    var inputField = document.getElementsByTagName('input');" +
                                "    if (inputField[1].value === ''){ return 0;}" +
                                "    return inputField[1].value.replace(/,/g, '');" +
                                "}" +
                                "async function sendTo() {" +
                                "    var el = document.getElementsByClassName('btn-text');" +
                                "for(var i = 0; i < el.length; i++){"+
                                "    if (el) {" +
                                "            mouseClick('rowContent has-tap-callback', 0);" +
                                "    if (sendToTransferListFlag) {" +
                                "        if (el[i].innerHTML === 'Send to Transfer List') {" +
                                "            mouseClick('btn-text', i);" +
                                "        }" +
                                "    } else if (sendToMyClubFlag) {" +
                                "        if (el[i].innerHTML === 'Send to My Club') {" +
                                "            mouseClick('btn-text', i);" +
                                "        }" +
                                "    }" +
                                "}" +
                                "}" +
                                "}" +
                                "" +
                                "function setInputFields() {" +
                                "if(consumables === 'true'){" +
                                "inputFields = 3;" +
                                "}" +
                                "}" +
                                "" +
                                "setTimeout(() => {" +
                                "setInputFields();" +
                                "    makeStopButton();" +
                                "    FutExtension();" +
                                "}, 500);";
                Button button = (Button) findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        webView.evaluateJavascript(script, null);
                    }
                });
                webView.evaluateJavascript(script, null);
            }
        });
        webView.addJavascriptInterface(new JSClass(this), "JSClass");
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}