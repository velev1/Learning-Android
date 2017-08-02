package com.example.velev.swipedemo.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.velev.swipedemo.R;
import com.example.velev.swipedemo.data.models.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 25.7.2017 г..
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {

    private List<Student> students;
    private List<Student> swipedData;

    public StudentAdapter(List<Student> students){
        this.swipedData = new ArrayList<>();
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

        // for the regular
        holder.firstName.setText(student.getFirstName());
        holder.lastName.setText(student.getLastName());
        holder.facNumber.setText(student.getFacultyNumber());

            if(swipedData.contains(student)) {
                // show swiped layout
                holder.regularLayout.setVisibility(View.GONE);
                holder.swipeLayout.setVisibility(View.VISIBLE);
            } else {
                // show regular layout
                holder.regularLayout.setVisibility(View.VISIBLE);
                holder.swipeLayout.setVisibility(View.GONE);
            }
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void pendingRemoval(int position) {

        final Student student = students.get(position);
        if (!swipedData.contains(student)) {
            swipedData.add(student);
            // this will redraw row in "undo" state
            notifyItemChanged(position);
            // let's create, store and post a runnable to remove the data
//            Runnable pendingRemovalRunnable = new Runnable() {
//                @Override
//                public void run() {
//                    remove(dataList.indexOf(data));
//                }
//            };
           // handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
           // pendingRunnables.put(data, pendingRemovalRunnable);
        }
    }

    public boolean isPendingRemoval(int position) {
        Student student = students.get(position);
        return swipedData.contains(student);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout regularLayout;
        private RelativeLayout swipeLayout;

        private TextView firstName;
        private TextView lastName;
        private TextView facNumber;

        private TextView delete;
        private TextView undo;

        public MyViewHolder(View view) {
            super(view);

            regularLayout = (RelativeLayout) view.findViewById(R.id.regular_row);
            firstName = (TextView) view.findViewById(R.id.tv_first_name);
            lastName = (TextView) view.findViewById(R.id.tv_last_name);
            facNumber = (TextView) view.findViewById(R.id.tv_fac_number);

            swipeLayout = (RelativeLayout) view.findViewById(R.id.swipe_row);
            delete = (TextView) view.findViewById(R.id.delete);
            undo = (TextView) view.findViewById(R.id.undo);


//            regularLayout = (LinearLayout) view.findViewById(R.id.regularLayout);
//            listItem = (TextView) view.findViewById(R.id.list_item);
//            swipeLayout = (LinearLayout) view.findViewById(R.id.swipeLayout);
//            undo = (TextView) view.findViewById(R.id.undo);
        }
    }
}
