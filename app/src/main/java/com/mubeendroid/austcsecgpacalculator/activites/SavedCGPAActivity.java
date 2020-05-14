package com.mubeendroid.austcsecgpacalculator.activites;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mubeendroid.austcsecgpacalculator.adapters.CGPAAdapter;
import com.mubeendroid.austcsecgpacalculator.models.GPA;
import com.mubeendroid.austcsecgpacalculator.utils.DatabaseHelper;
import com.mubeendroid.austcsecgpacalculator.R;

import java.util.ArrayList;

public class SavedCGPAActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView cgpa;
    ImageView back;
    LinearLayout cgpaLayout;
    CGPAAdapter adapter;
    ArrayList<GPA> gpas;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_cgpa);

        recyclerView = findViewById(R.id.recyclerView);
        cgpa = findViewById(R.id.cgpa);
        cgpaLayout = findViewById(R.id.cgpa_layout);
        back = findViewById(R.id.back);
        gpas = new ArrayList<>();
        adapter = new CGPAAdapter(SavedCGPAActivity.this, gpas);
        recyclerView.setLayoutManager(new LinearLayoutManager(SavedCGPAActivity.this));
        recyclerView.setAdapter(adapter);
        databaseHelper = new DatabaseHelper(SavedCGPAActivity.this);

        ////////////////////////////////// Fetch data from database adn show in recyclerview ///////////////////////////////////////////
        Cursor res = databaseHelper.getData();
        if (res.getCount() == 0) {
            Toast.makeText(SavedCGPAActivity.this, "No record found", Toast.LENGTH_SHORT).show();
        } else {
            double sum = 0.0;
            int i = 0;
            while (res.moveToNext()) {
                GPA gpa = new GPA();
                gpa.setYear(res.getInt(1));
                gpa.setSemester(res.getInt(2));
                gpa.setResult(res.getDouble(3));
                gpas.add(gpa);
                adapter.notifyItemInserted(gpas.size());
                sum += res.getDouble(3);
                i++;
            }
            cgpa.setText(String.format("%.2f", (sum / i)));
            cgpaLayout.setVisibility(View.VISIBLE);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
