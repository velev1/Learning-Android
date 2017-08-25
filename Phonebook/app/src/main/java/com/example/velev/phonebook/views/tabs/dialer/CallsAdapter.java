package com.example.velev.phonebook.views.tabs.dialer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.velev.phonebook.R;
import com.example.velev.phonebook.data.models.CallModel;

import java.util.ArrayList;
import java.util.List;

// TODO click listener

public class CallsAdapter extends RecyclerView.Adapter<CallsAdapter.CallsHolder> {

    private List<CallModel> calls;

    public CallsAdapter(List<CallModel> calls) {
        if (calls == null) {
            this.calls = new ArrayList<>();
        } else {
            this.calls = calls;
        }
    }

    @Override
    public CallsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_call_log_row, parent, false);


        return new CallsHolder(view);
    }

    @Override
    public void onBindViewHolder(CallsHolder holder, int position) {
        CallModel call = this.calls.get(position);

        if(call.getName() == null) {
            holder.tvName.setText(call.getPhoneNumber());
        } else {
            holder.tvName.setText(call.getName());
        }

        holder.tvDate.setText(call.getCallDateTime());
    }

    @Override
    public int getItemCount() {
        return this.calls.size();
    }

    public void swapData(List<CallModel> data) {
        this.calls.clear();
        this.calls.addAll(data);
        notifyDataSetChanged();
    }

    public class CallsHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvDate;

        public CallsHolder(View view) {
            super(view);

            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvDate = (TextView) view.findViewById(R.id.tv_date);
        }
    }
}
