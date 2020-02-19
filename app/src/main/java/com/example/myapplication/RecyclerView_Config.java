package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {

    private Context mContext;
    private EventsAdapter mEventsAdapter;

    public void setConfig(RecyclerView recyclerView,Context context, List<Event> events,List<String> keys){
        mContext=context;
        mEventsAdapter = new EventsAdapter(events,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mEventsAdapter);
    }

    class EventItemView extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mType;
        private TextView mDate;
        private TextView mTime;

        private String key;

        public EventItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.event_item, parent, false));

            mTitle = itemView.findViewById(R.id.title);
            mType = itemView.findViewById(R.id.type);
            mDate = itemView.findViewById(R.id.date);
            mTime = itemView.findViewById(R.id.time);
        }

        public void bind(Event event, String key) {
            mTitle.setText(event.getName());
            mType.setText(event.getType());
            mDate.setText(event.getDate());
            mTime.setText(event.getTime());

            this.key = key;
        }
    }
    class EventsAdapter extends RecyclerView.Adapter<EventItemView>{
            private List<Event> mEventList;
            private List<String> mKeys;

            public EventsAdapter(List<Event> mEventList,List<String> mKeys){
                this.mEventList = mEventList;
                this.mKeys = mKeys;
            }

            @NonNull
            @Override
            public EventItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new EventItemView(parent);
            }

            @Override
            public void onBindViewHolder(@NonNull EventItemView holder, int position) {
                holder.bind(mEventList.get(position),mKeys.get(position));
            }

            @Override
            public int getItemCount() {
                return mEventList.size();
            }
        }

}
