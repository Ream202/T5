package com.bk.t5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>{
    private ArrayList<Student> studentList;
    private Context context;

    public StudentAdapter(Context context, ArrayList<Student> list){//constructor
        this.studentList = list;
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_row_list_activity, parent, false); // Inflate the custom layout
        return new StudentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Student student = studentList.get(position); // Get the data model based on position

        holder.tvName.setText(student.getName());
        holder.tvCourse.setText(student.getCourse());
        holder.tvMatric.setText(student.getMatric());
        Picasso.get().load(student.getPics()).into(holder.ivPics);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName, tvCourse, tvMatric;
        public ImageView ivPics;

        public ViewHolder(View itemView){
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvCourse =  itemView.findViewById(R.id.tvCourse);
            tvMatric = itemView.findViewById(R.id.tvMatric);
            ivPics = itemView.findViewById(R.id.ivPic);
        }
    }
}
