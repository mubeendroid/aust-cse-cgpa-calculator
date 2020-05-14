package com.mubeendroid.austcsecgpacalculator.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mubeendroid.austcsecgpacalculator.R;
import com.mubeendroid.austcsecgpacalculator.activites.MainActivity;
import com.mubeendroid.austcsecgpacalculator.models.GPA;
import com.mubeendroid.austcsecgpacalculator.utils.DatabaseHelper;

import java.util.ArrayList;

public class CGPAAdapter extends RecyclerView.Adapter<CGPAAdapter.MyViewHolder> {

    Context context;
    ArrayList<GPA> cgpas;
    DatabaseHelper databaseHelper;

    public CGPAAdapter(Context context, ArrayList<GPA> users) {
        this.context = context;
        this.cgpas = users;
        databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public CGPAAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_saved_cgpa, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CGPAAdapter.MyViewHolder holder, final int position) {
        holder.year.setText(cgpas.get(position).getYear() + "");
        holder.semester.setText(cgpas.get(position).getSemester() + "");
        holder.gpa.setText(cgpas.get(position).getResult() + "");
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteData(Integer.parseInt(holder.year.getText().toString()), Integer.parseInt(holder.semester.getText().toString()));
                Toast.makeText(context, "Record deleted successfully", Toast.LENGTH_SHORT).show();
                holder.view.setVisibility(View.GONE);
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cgpas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView year, semester, gpa;
        ImageView delete;
        View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            try {
                year = itemView.findViewById(R.id.year);
                semester = itemView.findViewById(R.id.semester);
                gpa = itemView.findViewById(R.id.gpa);
                delete = itemView.findViewById(R.id.delete);
                view = itemView;
            } catch (Exception e) {

            }
        }
    }
}