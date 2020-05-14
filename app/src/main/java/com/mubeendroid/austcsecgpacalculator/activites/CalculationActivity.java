package com.mubeendroid.austcsecgpacalculator.activites;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.mubeendroid.austcsecgpacalculator.fragments.GradeFragment;
import com.mubeendroid.austcsecgpacalculator.utils.DatabaseHelper;
import com.mubeendroid.austcsecgpacalculator.utils.Information;
import com.mubeendroid.austcsecgpacalculator.R;

import java.util.LinkedHashMap;
import java.util.Map;

public class CalculationActivity extends AppCompatActivity {

    LinearLayout rootLayout;
    TextView heading;
    ImageView back;
    Button calculate, grade;
    Map<String, Double> map;
    DatabaseHelper databaseHelper;
    int year = 0, semester = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        rootLayout = findViewById(R.id.root_layout);
        heading = findViewById(R.id.heading);
        calculate = findViewById(R.id.calculate);
        back = findViewById(R.id.back);
        grade = findViewById(R.id.grade);
        Information information = new Information();
        map = new LinkedHashMap<>();
        databaseHelper = new DatabaseHelper(CalculationActivity.this);

        ////////////////////////////////// Get data from previous activity ///////////////////////////////////////////
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            switch (extras.getString("fetch")) {
                case "11":
                    heading.setText("1st year 1st semester");
                    map = information.get11Data();
                    year = semester = 1;
                    break;
                case "12":
                    heading.setText("1st year 2nd semester");
                    map = information.get12Data();
                    year = 1;
                    semester = 2;
                    break;
                case "21":
                    heading.setText("2nd year 1st semester");
                    map = information.get21Data();
                    year = 2;
                    semester = 1;
                    break;
                case "22":
                    heading.setText("2nd year 2nd semester");
                    map = information.get22Data();
                    year = semester = 2;
                    break;
                case "31":
                    heading.setText("3rd year 1st semester");
                    map = information.get31Data();
                    year = 3;
                    semester = 1;
                    break;
                case "32":
                    heading.setText("3rd year 2nd semester");
                    map = information.get32Data();
                    year = 3;
                    semester = 2;
                    break;
                case "41":
                    heading.setText("4th year 1st semester");
                    map = information.get41Data();
                    year = 4;
                    semester = 1;
                    break;
                case "42":
                    heading.setText("4th year 2nd semester");
                    map = information.get42Data();
                    year = 4;
                    semester = 2;
                    break;
            }
        }

        ///////////////////////// Generate user interface based on the data fetched from Information class //////////////////////////////////
        int i = map.size();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            LinearLayout parent = new LinearLayout(CalculationActivity.this);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 32, 8, 32);
            parent.setLayoutParams(params);
            parent.setOrientation(LinearLayout.HORIZONTAL);

            TextView tv = new TextView(CalculationActivity.this);
            tv.setTextSize(16f);
            LinearLayout.LayoutParams textParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            textParam.gravity = Gravity.CENTER;
            tv.setLayoutParams(textParam);
            tv.setText(entry.getKey());
            tv.setGravity(Gravity.CENTER_VERTICAL);

            EditText et = new EditText(CalculationActivity.this);
            et.setTextSize(16f);
            et.setHint("Grade");
            et.setGravity(Gravity.CENTER_VERTICAL);
            et.setTag(entry.getKey());
            et.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            LinearLayout.LayoutParams editTextParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f);
            editTextParam.gravity = Gravity.CENTER;
            et.setLayoutParams(editTextParam);

            parent.addView(tv);
            parent.addView(et);
            rootLayout.addView(parent);

            if (i > 1) {
                View view = new View(CalculationActivity.this);
                view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
                view.setBackgroundColor(Color.parseColor("#80dadada"));
                rootLayout.addView(view);
            }
            i--;
        }

        grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GradeFragment gradeFragment = new GradeFragment();
                gradeFragment.show(getSupportFragmentManager(), "GradeFragment");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ////////////////////////////////// Calculate gpa based on the grades user provided ///////////////////////////////////////////
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double gpa = 0.0, sum = 0.0;
                for (Map.Entry<String, Double> entry : map.entrySet()) {
                    EditText et = rootLayout.findViewWithTag(entry.getKey());
                    if (TextUtils.isEmpty(et.getText().toString()) || !checkGrade(et.getText().toString())) {
                        Toast.makeText(CalculationActivity.this, "Please enter your grade in " + entry.getKey() + " correctly", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    gpa = gpa + (Double.parseDouble(et.getText().toString()) * entry.getValue());
                    sum += entry.getValue();
                }
                gpa = gpa / sum;
                final String result = String.format("%.2f", gpa);
                AlertDialog.Builder builder = new AlertDialog.Builder(CalculationActivity.this);
                builder.setMessage("Your average GPA is " + result).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).setNegativeButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        databaseHelper.insertData(year, semester, Double.parseDouble(result));
                        Toast.makeText(CalculationActivity.this, "GPA saved successfully", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alert = builder.create();
                alert.setTitle("GPA");
                alert.show();
            }
        });
    }

    ////////////////////////////////// Validate grade provided by user ///////////////////////////////////////////
    public boolean checkGrade(String grade) {
        try {
            double d = Double.parseDouble(grade);
            if (d < 0.0 || d > 4.0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
