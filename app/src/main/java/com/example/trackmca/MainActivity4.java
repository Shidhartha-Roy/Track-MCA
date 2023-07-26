package com.example.trackmca;

import static android.content.Context.MODE_MULTI_PROCESS;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity4 extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Button searchButton;

    private TextView statusTxt;
    private TextView check2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        initDatePicker();
        dateButton = findViewById(R.id.button4);
        searchButton = findViewById(R.id.button5);
        Spinner selectSub = (Spinner) findViewById(R.id.subspinner);
        statusTxt = findViewById(R.id.textView26);
        getSupportActionBar().hide();

        //Searching for the status(complete)
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity4.this, "Fetching Status", Toast.LENGTH_SHORT).show();
                String dateTxt = dateButton.getText().toString();
                String subTxt = selectSub.getSelectedItem().toString();
                String key = dateTxt+subTxt;

                SharedPreferences shrd = getSharedPreferences("TrackMCA",MODE_MULTI_PROCESS);
                String status = shrd.getString(key,"Record not found");
                statusTxt.setText(status);



            }
        });



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.subjects, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        selectSub.setAdapter(adapter);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = makeDateString(dayOfMonth, month, year);
                dateButton.setText(date);

            }

        };

        //Collecting The values
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, dayOfMonth);

    }

            private String makeDateString(int dayOfMonth, int month, int year) {
                return getMonthFormat(month) +"-"+ dayOfMonth + "-" + year;
            }

            private String getMonthFormat(int month) {
                if(month == 1){
                    return "Jan";
                    }
                if(month == 2){
                    return "Feb";
                    }
                if(month == 3){
                    return "Mar";
                }
                if(month == 4){
                    return "Apr";
                }
                if(month == 5){
                    return "May";
                }
                if(month == 6){
                    return "Jun";
                }
                if(month == 7){
                    return "Jul";
                }
                if(month == 8){
                    return "Aug";
                }
                if(month == 9){
                    return "Sep";
                }
                if(month == 10){
                    return "Oct";
                }
                if(month == 11){
                    return "Nov";
                }
                if(month == 12){
                    return "Dec";
                }
                return "Jan";

                }



            public void openDatePicker(View view) {
                datePickerDialog.show();
            }



}
