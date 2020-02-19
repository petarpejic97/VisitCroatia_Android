package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CityList extends AppCompatActivity {

    ListView listView;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private Boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        listView = findViewById(R.id.listView);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(CityList.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.cities));
        listView.setOnItemClickListener((adapterView, view, i, l) -> {

            SharedPreferences sharedPreferences = getSharedPreferences("city", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("city",listView.getItemAtPosition(i).toString()).apply();

            Intent intent = new Intent(CityList.this,City.class);
            startActivity(intent);
        });
        listView.setAdapter(mAdapter);
    }

    public void goToLogin(View view) {
        myRef.child("login").setValue(false);
        Intent intent = new Intent(CityList.this,Login.class);
        finish();
        startActivity(intent);
    }

    public void goToCreateEvent(View view) {
        //citanje jednog podatka
        /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean value = dataSnapshot.child("login").getValue(Boolean.class);
                if(value.equals(true)){
                    Intent intent = new Intent(CityList.this,CreateEvent.class);
                    finish();
                    startActivity(intent);
                }
                else{
                    Toast.makeText(CityList.this,"You must be logged",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });*/
        //Bundle bundle = getIntent().getExtras();
        SharedPreferences result = getSharedPreferences("login", Context.MODE_PRIVATE);
        if(result.getString("flag", "false").equals("true")){
            Intent intent = new Intent(CityList.this,CreateEvent.class);
            finish();
            startActivity(intent);
        }
        else{
            Toast.makeText(CityList.this,"You must be logged", Toast.LENGTH_SHORT).show();
        }

    }


}
