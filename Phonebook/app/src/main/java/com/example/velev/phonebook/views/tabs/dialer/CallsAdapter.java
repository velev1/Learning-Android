package com.example.velev.phonebook.views.tabs.dialer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.velev.phonebook.R;
import com.example.velev.phonebook.data.models.CallModel;

import java.util.List;

public class CallsAdapter extends ArrayAdapter<CallModel>{

    private Context context;
    private int resource;
    private View view;
    private Holder holder;
    private List<CallModel> calls;

    public CallsAdapter(Context context, int resource, List<CallModel> calls) {
        super(context, resource, calls);

        this.context = context;
        this.resource = resource;
        this.calls = calls;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(resource, parent, false);

        holder = new Holder();
        holder.name = (TextView) view.findViewById(R.id.tv_name);
        holder.date = (TextView) view.findViewById(R.id.tv_date);

        holder.name.setText(calls.get(position).getName());
        holder.date.setText(calls.get(position).getCallDateTime().toString());

        return view;
    }

    private class Holder {
        TextView name;
        TextView date;
    }
}
