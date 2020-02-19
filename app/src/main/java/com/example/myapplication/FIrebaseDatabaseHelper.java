package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FIrebaseDatabaseHelper {
    private final Context context;
    private final SharedPreferences sharedPreferences,prefButton;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceEvent;
    private List<Event> events = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Event> events, List<String> keys);
    }

    public FIrebaseDatabaseHelper(Context context) {
        this.context=context;
        sharedPreferences = context.getSharedPreferences("city",Context.MODE_PRIVATE);
        prefButton = context.getSharedPreferences("pressedButton",Context.MODE_PRIVATE);
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceEvent = mDatabase.getReference("events");
    }

    public void readEvents(final DataStatus dataStatus){
        mReferenceEvent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                events.clear();
                List<String> keys = new ArrayList<>();

                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Event event = keyNode.getValue(Event.class);

                    if(prefButton.getString("button", "button").equals("all")){

                        if(event.getLocation().equals(sharedPreferences.getString("city","zero"))){
                            events.add(event);
                        }
                    }
                    else if(prefButton.getString("button","button") == "search"){
                        if(event.getLocation().equals(sharedPreferences.getString("city","zero")) && event.getDate().equals(prefButton.getString("date","Some date"))){
                            events.add(event);
                        }
                    }
                }
                dataStatus.DataIsLoaded(events,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
