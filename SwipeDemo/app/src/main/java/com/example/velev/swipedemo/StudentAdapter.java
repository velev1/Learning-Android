package com.example.velev.swipedemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.velev.swipedemo.data.models.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 25.7.2017 г..
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {

    private List<Student> students;

    public StudentAdapter(List<Student> students){
        if(students != null) {
            this.students = students;
        } else {
            this.students = new ArrayList<>();
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Student student  = students.get(position);

        holder.firstName.setText(student.getFirstName());
        holder.lastName.setText(student.getLastName());
        holder.facNumber.setText(student.getFacultyNumber());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView firstName;
        private TextView lastName;
        private TextView facNumber;

        public MyViewHolder(View view) {
            super(view);

            firstName = (TextView) view.findViewById(R.id.tv_first_name);
            lastName = (TextView) view.findViewById(R.id.tv_last_name);
            facNumber = (TextView) view.findViewById(R.id.tv_fac_number);
        }
    }
}
