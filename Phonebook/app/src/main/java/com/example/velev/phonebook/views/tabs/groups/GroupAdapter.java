package com.example.velev.phonebook.views.tabs.groups;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.velev.phonebook.R;
import com.example.velev.phonebook.data.models.GroupModel;

import java.util.ArrayList;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupHolder> {
    private List<GroupModel> groups;
    private ItemClickListener listener;

    public GroupAdapter(List<GroupModel> groups, ItemClickListener listener) {
        if (groups == null) {
            this.groups = new ArrayList<>();
        } else {
            this.groups = groups;
        }

        this.listener = listener;
    }

    @Override
    public GroupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_group_row, parent, false);

        return new GroupHolder(view);
    }

    @Override
    public void onBindViewHolder(GroupHolder holder, int position) {
        GroupModel group = this.groups.get(position);

        holder.tvGroupName.setText(group.getName());
    }

    @Override
    public int getItemCount() {
        return this.groups.size();
    }

    public void swapData(List<GroupModel> groups) {
        this.groups.clear();
        this.groups.addAll(groups);
        notifyDataSetChanged();
    }

    interface ItemClickListener {
        void onItemClick();
    }

    public class GroupHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvGroupName;

        public GroupHolder(View view) {
            super(view);

            this.tvGroupName = (TextView) view.findViewById(R.id.tv_group_name);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // TODO do stuff
        }
    }
}
