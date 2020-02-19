package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class CreateEvent extends AppCompatActivity {

    private EditText edName,edLocation,edType,edDate,edTime;

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private FirebaseDatabase mFirebaseDatabase;
    private ProgressDialog progressDialog;
    private String userID;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        edDate=findViewById(R.id.edDate);
        edLocation=findViewById(R.id.edLocation);
        edName=findViewById(R.id.edName);
        edTime=findViewById(R.id.edTime);
        edType=findViewById(R.id.edType);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef= mFirebaseDatabase.getReference();

        edDate.setOnClickListener(view -> {

            Calendar cal = Calendar.getInstance();
            int day= cal.get(Calendar.DAY_OF_MONTH);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);

            DatePickerDialog dialog = new DatePickerDialog(
                    CreateEvent.this,
                    android.R.style.Theme_Holo_Dialog_MinWidth,
                    mDateSetListener,
                    day,month,year);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
            dialog.show();
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month+=1;
                String date=day+"."+month+"."+year+".";
                Toast.makeText(CreateEvent.this,date,Toast.LENGTH_SHORT).show();
                edDate.setText(date);
            }
        };
    }

    private void resetFields() {
        edLocation.setText("");
        edDate.setText("");
        edName.setText("");
        edType.setText("");
        edTime.setText("");
    }
    public void goToCityList(View view) {
        Intent intent = new Intent(CreateEvent.this,CityList.class);
        finish();
        startActivity(intent);
    }

    public void SavaEvent(View view) {
        String key = mFirebaseDatabase.getReference("id").push().getKey();

        String date = edDate.getText().toString();
        String name = edName.getText().toString();
        String location = edLocation.getText().toString();
        String time = edTime.getText().toString();
        String type = edType.getText().toString();

        Event event = new Event(name,date,time,location,type);
        myRef.child("events").child(key).setValue(event);

        resetFields();
    }
}
