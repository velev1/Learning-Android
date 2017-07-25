package com.example.velev.eventreminder.views.displayEventView;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.velev.eventreminder.R;
import com.example.velev.eventreminder.data.models.Event;
import com.example.velev.eventreminder.views.eventDetailsViewTest.EventDetailsActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    private static final String EVENT_KEY = "EVENT_KEY";

    private Event swipedEvent;
    private int swipedPosition;


    private List<Event> events;

    public EventAdapter(List<Event> events) {
        if(events == null){
            this.events = new ArrayList<>();
        } else {
            this.events = events;
        }
    }

    public Event getSwipedEvent() {
        return swipedEvent;
    }

    public void setSwipedEvent(Event swipedEvent) {
        this.swipedEvent = swipedEvent;
    }

    public int getSwipedPosition() {
        return swipedPosition;
    }

    public void setSwipedPosition(int swipedPosition) {
        this.swipedPosition = swipedPosition;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Event event = events.get(position);
        holder.eventName.setText(event.getName());
        holder.eventDescription.setText(event.getDescription());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
        String strDate = dateFormat.format(event.getDate());

        holder.eventDate.setText(strDate);

        final int itemPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), EventDetailsActivity.class);
                intent.putExtra(EVENT_KEY, events.get(itemPosition));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.events.size();
    }

    public void swapData(List<Event> data) {
        this.events.clear();
        this.events.addAll(data);
        notifyDataSetChanged();
    }

    public Event getEvent(int position) {
        return this.events.get(position);
    }

    public void deleteEventAt(int position){
        this.events.remove(position);
    }

    public void insertEventAt(int position, Event event){
        this.events.add(position, event);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

//        private static final String EVENT_KEY = "EVENT_KEY";

        private TextView eventName;
        private TextView eventDate;
        private TextView eventDescription;

        public MyViewHolder(final View view) {
            super(view);

            eventName = (TextView) view.findViewById(R.id.tv_name);
            eventDate = (TextView) view.findViewById(R.id.tv_date);
            eventDescription = (TextView) view.findViewById(R.id.tv_description);

//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    final int position = getAdapterPosition();
////                    Intent intent = new Intent(view.getContext(), EventDetailsActivity.class);
////                    intent.putExtra(EVENT_KEY, events.get(position));
////                    view.getContext().startActivity(intent);
//
//                    Toast.makeText(view.getContext(), "it works " + String.valueOf(position), Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }
}
