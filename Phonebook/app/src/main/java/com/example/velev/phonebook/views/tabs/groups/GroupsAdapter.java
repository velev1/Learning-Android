package com.example.velev.phonebook.views.tabs.groups;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.velev.phonebook.R;
import com.example.velev.phonebook.data.models.GroupModel;

import java.util.List;

public class GroupsAdapter extends ArrayAdapter<GroupModel>{
    private final List<GroupModel> groups;
    private View view;
    private Context context;
    private int resource;

    public GroupsAdapter(@NonNull Context context, @LayoutRes int resource, List<GroupModel> groups) {
        super(context, resource, groups);

        this.context = context;
        this.resource = resource;
        this.groups = groups;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = inflater.inflate(this.resource, parent, false);

        Holder holder = new Holder();
        holder.name = (TextView) view.findViewById(R.id.tv_group_name);

        holder.name.setText(this.groups.get(position).getName());

        return this.view;
    }

    private class Holder {
        TextView name;
    }
}
