package com.example.trackmca;

import static android.content.Context.MODE_MULTI_PROCESS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    //Widget declaration
    private Button checkAtten;
    private Button checkStatus;
    private FloatingActionButton addAtten;
    private Switch btnToggleDark;


    //Variable declaration
    private static float tocPercent;
    private static float adsPercent;
    private static float cntPercent;
    private static float sePercent;
    private static float dsPercent;
    private static float avgPercent;
    private static float labPercent;
    private static float percentage;
//    private boolean check1 = true;
    private boolean check2 = true;

//    public void setCheck1(boolean check1) {
//        this.check1 = check1;
//    }

    public void setCheck2(boolean check2) {
        this.check2 = check2;
    }






    //Getters
    public static float getAvgPercent() {
        return avgPercent;
    }

    public static float getTocPercent() {
        return tocPercent;
    }

    public static float getAdsPercent() {
        return adsPercent;
    }

    public static float getCnPercent() {
        return cntPercent;
    }

    public static float getSePercent() {
        return sePercent;
    }

    public static float getDsPercent() {
        return dsPercent;
    }

    public static float getLabPercent() {
        return labPercent;
    }



    //Driver code
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkAtten = findViewById(R.id.button);
        checkStatus = findViewById(R.id.button2);
        addAtten = findViewById(R.id.floatingActionButton);
        btnToggleDark = findViewById(R.id.switch1);
        getSupportActionBar().hide();


        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_MULTI_PROCESS);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        btnToggleDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isDarkModeOn) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putBoolean("isDarkModeOn", false);
                    editor.apply();
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putBoolean("isDarkModeOn", true);
                    editor.apply();
                }
            }
        });

        //Generating Attendance Report
        checkAtten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Fetching Attendance", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);

                tocPercent = calculatePerc("tocClass count", "tocAttend count");
                adsPercent = calculatePerc("adsClass count", "adsAttend count");
                cntPercent = calculatePerc("cnClass count", "cnAttend count");
                sePercent = calculatePerc("seClass count","seAttend count");
                dsPercent = calculatePerc("dsClass count", "dsAttend count");
                labPercent = calculatePerc("labClass count", "labAttend count");

                int toc = checkSubcount("tocClass count");
                int ads = checkSubcount("adsClass count");
                int cn =  checkSubcount("cnClass count");
                int se =  checkSubcount("seClass count");
                int ds =  checkSubcount("dsClass count");
                int lab =  checkSubcount("labClass count");
                int diff = toc+ads+cn+se+ds+lab;


                avgPercent = (tocPercent+adsPercent+cntPercent+sePercent+dsPercent+labPercent)/(6-diff);


            }
            //Class value check
            private int checkSubcount(String adsClass_count) {
                int count = 0;
                SharedPreferences getValue = getSharedPreferences("TrackMCA", MODE_MULTI_PROCESS);
                int classValue = getValue.getInt(adsClass_count, 0);
                if(classValue==0){
                    count++;
                }
                return count;
            }
            //Percentage calculate
            private float calculatePerc(String Class_count, String Attend_count) {
                SharedPreferences getValue = getSharedPreferences("TrackMCA", MODE_MULTI_PROCESS);
                int classValue = getValue.getInt(Class_count, 0);
                int attendValue = getValue.getInt(Attend_count, 0);

                if(classValue!=0){

                    return percentage = attendValue * 100 / classValue;
                }
                else{
                    if(check2){
                        Toast.makeText(MainActivity.this, "Some Classes have not begun", Toast.LENGTH_SHORT).show();
                        setCheck2(false);
                    }

                }
                return 0;
            }


        });

        //Checking Specific Status
        checkStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivity4.class);
                startActivity(intent);
            }
        });

        //Adding Attendance Record
        addAtten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}