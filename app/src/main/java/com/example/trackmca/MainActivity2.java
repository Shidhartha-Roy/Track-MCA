package com.example.trackmca;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    private TextView day;
    private TextView date;
    private Button submit;

    //Subject TextViews
    private TextView tocTxt;
    private TextView adsTxt;
    private TextView cnTxt;
    private TextView seTxt;
    private TextView dsTxt;
    private TextView labTxt;
    private TextView check;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        day = findViewById(R.id.textView2);
        date = findViewById(R.id.textView4);

        //Subject
        tocTxt = findViewById(R.id.textView);
        adsTxt = findViewById(R.id.textView3);
        cnTxt = findViewById(R.id.textView5);
        seTxt = findViewById(R.id.textView6);
        dsTxt = findViewById(R.id.textView7);
        labTxt = findViewById(R.id.textView8);

        submit = findViewById(R.id.button3);


        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();

        //Extracting Current Day
        String dayOfTheWeek = sdf.format(d);

        //Extracting current Date
        CharSequence s  = DateFormat.format("MMM-d-yyyy", d.getTime());

        //Displaying extracted info
        day.setText(dayOfTheWeek);
        date.setText(s);

        Spinner selectTOC = (Spinner) findViewById(R.id.spinner_attendance);
        Spinner selectLAB = (Spinner) findViewById(R.id.spinner_attendance2);
        Spinner selectDS = (Spinner) findViewById(R.id.spinner_attendance3);
        Spinner selectSE = (Spinner) findViewById(R.id.spinner_attendance4);
        Spinner selectCN = (Spinner) findViewById(R.id.spinner_attendance5);
        Spinner selectAds = (Spinner) findViewById(R.id.spinner_attendance6);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.attendance, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        selectTOC.setAdapter(adapter);
        selectLAB.setAdapter(adapter);
        selectDS.setAdapter(adapter);
        selectSE.setAdapter(adapter);
        selectCN.setAdapter(adapter);
        selectAds.setAdapter(adapter);

        //Routine wise selection
        String test = day.getText().toString();
        if(test.equals("Monday")){
            selectTOC.setEnabled(false);
            selectCN.setEnabled(false);

        }
        else if(test.equals("Tuesday")){
            selectDS.setEnabled(false);
            selectSE.setEnabled(false);
        }
        else if(test.equals("Wednesday")){
            selectAds.setEnabled(false);
            selectSE.setEnabled(false);
        }
        else if(test.equals("Thursday")){
            selectAds.setEnabled(false);
            selectTOC.setEnabled(false);
        }
        else if(test.equals("Friday")){
            selectCN.setEnabled(false);
            selectDS.setEnabled(false);
        }
        else{
            selectTOC.setEnabled(false);
            selectCN.setEnabled(false);
            selectDS.setEnabled(false);
            selectAds.setEnabled(false);
            selectSE.setEnabled(false);
            selectLAB.setEnabled(false);
            submit.setEnabled(false);
        }




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "Adding Record", Toast.LENGTH_SHORT).show();
                String keyDate = date.getText().toString();

                //TOC Specific
                String tocSub = tocTxt.getText().toString();
                String tocStat = selectTOC.getSelectedItem().toString();
                String keyTOC = keyDate+tocSub;
                addAttendance(keyTOC,tocStat,"tocClass count","tocAttend count");

                //ADS Specific
                String adsSub = adsTxt.getText().toString();
                String adsStat = selectAds.getSelectedItem().toString();
                String keyADS = keyDate+adsSub;
                addAttendance(keyADS,adsStat,"adsClass count","adsAttend count");

                //CN Specific
                String cnSub = cnTxt.getText().toString();
                String cnStat = selectCN.getSelectedItem().toString();
                String keyCN = keyDate+cnSub;
                addAttendance(keyCN,cnStat,"cnClass count","cnAttend count");

                //SE specific
                String seSub = seTxt.getText().toString();
                String seStat = selectSE.getSelectedItem().toString();
                String keySE = keyDate+seSub;
                addAttendance(keySE,seStat,"seClass count","seAttend count");

                //DS Specific
                String dsSub = dsTxt.getText().toString();
                String dsStat = selectDS.getSelectedItem().toString();
                String keyDS = keyDate+dsSub;
                addAttendance(keyDS,dsStat,"dsClass count","dsAttend count");

                //LAB Specific
                String labSub = labTxt.getText().toString();
                String labStat = selectLAB.getSelectedItem().toString();
                String keyLAB = keyDate+labSub;
                addAttendance(keyLAB,labStat,"labClass count","labAttend count");

                Toast.makeText(MainActivity2.this, "Record Saved", Toast.LENGTH_SHORT).show();


            }

            private void addAttendance(String keyTOC, String tocStat,String subClass, String subAttend) {
                //Getting the previous values of the file
                SharedPreferences getValue = getSharedPreferences("TrackMCA",MODE_MULTI_PROCESS);
                int classCount = getValue.getInt(subClass,0);
                int attendCount = getValue.getInt(subAttend,0);

                //Editing the files
//                SharedPreferences setShrd = getSharedPreferences("TrackMCA",MODE_MULTI_PROCESS);
                SharedPreferences.Editor editor = getValue.edit();

                //Updating values of Class and Attendance
                if(classCount<=0){
                    editor.putInt(subClass,0);
                    editor.putInt(subAttend,0);

                }
                else{
                    editor.putInt(subClass,classCount);
                    editor.putInt(subAttend,attendCount);

                }


                //Entering the status
                editor.putString(keyTOC,tocStat);
                editor.apply();

                //Solution to overwriting problem but not complete
//                Map<String,?> keys = getValue.getAll();
//                String[] keyNames = new String[keys.size()];
//                int i = 0;
//
//                for(Map.Entry<String,?> entry : keys.entrySet()){
//                    if(entry.getValue() instanceof String){
//                        keyNames[i] = entry.getKey();
//                        i++;
//                    }
//                }
//                for(int j =0;j<keyNames.length;j++){
//                    if(keyNames[j].equals(keyTOC)){
//                        return;
//                    }
//                }


                if(tocStat.toLowerCase().equals("present")){
                    classCount++;
                    attendCount++;
                    editor.putInt(subClass,classCount);
                    editor.putInt(subAttend,attendCount);
                    editor.apply();

                }
                else if(tocStat.toLowerCase().equals("absent")){
                    classCount++;
                    editor.putInt(subClass,classCount);
                    editor.apply();
                }
                else{
                    editor.putInt(subClass,classCount);
                    editor.putInt(subAttend,attendCount);
                    editor.apply();
                }
            }
        });


    }


}