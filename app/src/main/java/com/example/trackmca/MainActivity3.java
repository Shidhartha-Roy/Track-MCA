package com.example.trackmca;

import static com.example.trackmca.MainActivity.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    private TextView tocView;
    private TextView adsView;
    private TextView cnView;
    private TextView seView;
    private TextView dsView;
    private TextView labView;
    private TextView avgView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tocView = findViewById(R.id.textView17);
        adsView = findViewById(R.id.textView16);
        cnView = findViewById(R.id.textView19);
        seView = findViewById(R.id.textView15);
        dsView = findViewById(R.id.textView20);
        labView = findViewById(R.id.textView18);
        avgView= findViewById(R.id.textView22);
        getSupportActionBar().hide();

        float TOCperc = getTocPercent();
        float ADSperc = getAdsPercent();
        float CNperc = getCnPercent();
        float SEperc = getSePercent();
        float DSperc = getDsPercent();
        float LABperc = getLabPercent();
        float AVGperc = getAvgPercent();


        String tocValue = Float.toString(TOCperc);
        tocView.setText(tocValue);
        String adsValue = Float.toString(ADSperc);
        adsView.setText(adsValue);
        String cnValue = Float.toString(CNperc);
        cnView.setText(cnValue);
        String seValue = Float.toString(SEperc);
        seView.setText(seValue);
        String dsValue = Float.toString(DSperc);
        dsView.setText(dsValue);
        String labValue = Float.toString(LABperc);
        labView.setText(labValue);
        String avgValue = Float.toString(AVGperc);
        avgView.setText(avgValue);


    }
}