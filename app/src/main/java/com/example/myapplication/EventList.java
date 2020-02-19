package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class EventList extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        mRecyclerView=findViewById(R.id.RecyclerView);

        new FIrebaseDatabaseHelper(this).readEvents((events, keys)
                -> new RecyclerView_Config().setConfig(mRecyclerView,EventList.this,events,keys));
    }

    public void goToCity(View view) {
        Intent intent = new Intent(EventList.this,City.class);
        finish();
        startActivity(intent);
    }
}
