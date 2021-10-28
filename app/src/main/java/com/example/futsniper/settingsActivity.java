package com.example.futsniper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class settingsActivity extends AppCompatActivity {

    static Context parentContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        parentContext = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        EditText maxCards = findViewById(R.id.maxSeenCards);
        EditText maxRefresh = findViewById(R.id.maxRefreshPrice);
        EditText refreshTime = findViewById(R.id.refreshTime);
        Button buttonSave = findViewById(R.id.button2);
        Switch consumables = findViewById(R.id.consumables);
        Switch sendToTransferList = findViewById(R.id.sendToTransferList);
        Switch sendToClub = findViewById(R.id.sendToClub);

        consumables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(consumables.isChecked() && !sendToTransferList.isChecked()){
                    sendToClub.setEnabled(true);
                }else{
                    sendToClub.setChecked(false);
                    sendToClub.setEnabled(false);
                    sendToTransferList.setEnabled(true);
                }
            }
        });

        sendToTransferList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sendToTransferList.isChecked()){
                    sendToClub.setChecked(false);
                    sendToClub.setEnabled(false);
                }else if(consumables.isChecked()){
                    sendToClub.setEnabled(true);
                }
            }
        });

        sendToClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sendToClub.isChecked()){
                    consumables.setChecked(true);
                    sendToTransferList.setChecked(false);
                    sendToTransferList.setEnabled(false);
                }else{
                    sendToTransferList.setEnabled(true);
                }
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(maxCards.getText().toString().equals("")){
                    sharedPreferencesSave("5", "maxCards");
                }else{
                    sharedPreferencesSave(maxCards.getText().toString(), "maxCards");
                }

                if(maxRefresh.getText().toString().equals("")){
                    sharedPreferencesSave("700", "maxRefresh");
                }else{
                    sharedPreferencesSave(maxRefresh.getText().toString(), "maxRefresh");
                }

                if(refreshTime.getText().toString().equals("")){
                    sharedPreferencesSave("500", "refreshTime");
                }else{
                    sharedPreferencesSave(refreshTime.getText().toString(), "refreshTime");
                }

                if(consumables.isChecked()){
                    sharedPreferencesSave("true", "consumables");
                }else{
                    sharedPreferencesSave("false", "consumables");
                }

                if(sendToTransferList.isChecked()){
                    sharedPreferencesSave("true", "sendToTransferList");
                }else{
                    sharedPreferencesSave("false", "sendToTransferList");
                }

                if(sendToClub.isChecked()){
                    sharedPreferencesSave("true", "sendToClub");
                }else{
                    sharedPreferencesSave("false", "sendToClub");
                }

                Intent intent = new Intent(settingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void sharedPreferencesSave(String text, String key) {
        SharedPreferences sharedPref = this.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, text);
        editor.apply();
    }
}