package com.mubeendroid.austcsecgpacalculator.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.mubeendroid.austcsecgpacalculator.R;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    Button proceed;
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    LinearLayout drawerRelative;
    ListAdapter menuAdapter;
    ImageView menu;
    String semester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.layout_drawer);
        mDrawerList = findViewById(R.id.left_drawer);
        drawerRelative = findViewById(R.id.drawer_relative);
        menu = findViewById(R.id.menu);
        spinner = findViewById(R.id.spinner);
        proceed = findViewById(R.id.proceed);

        ////////////////////////////////// Initialize menu items ///////////////////////////////////////////
        String menuItems[] = {"See Saved CGPA", "About"};
        menuAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, menuItems);
        mDrawerList.setAdapter(menuAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedFromList = mDrawerList.getItemAtPosition(i).toString();
                if (selectedFromList.equals("See Saved CGPA")) {
                    startActivity(new Intent(MainActivity.this, SavedCGPAActivity.class));
                } else if (selectedFromList.equals("About")) {
                    startActivity(new Intent(MainActivity.this, AboutActivity.class));
                }
                mDrawerLayout.closeDrawer(drawerRelative);
            }
        });

        ////////////////////////////////// Open drawer on menu icon click ///////////////////////////////////////////
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDrawerLayout.isDrawerOpen(drawerRelative)) {
                    mDrawerLayout.closeDrawer(drawerRelative);
                } else {
                    mDrawerLayout.openDrawer(drawerRelative);
                }
            }
        });

        ////////////////////////////////// Initialize year and semester spinner ///////////////////////////////////////////
        adapter = ArrayAdapter.createFromResource(this, R.array.semester, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                semester = spinner.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ////////////////////////////////// Open Calculation activity on proceed button click ///////////////////////////////////////////
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalculationActivity.class);
                switch (semester) {
                    case "Select year and semester":
                        Toast.makeText(MainActivity.this, "Please select your year and semester", Toast.LENGTH_SHORT).show();
                        return;
                    case "1st year 1st semester":
                        intent.putExtra("fetch", "11");
                        break;
                    case "1st year 2nd semester":
                        intent.putExtra("fetch", "12");
                        break;
                    case "2nd year 1st semester":
                        intent.putExtra("fetch", "21");
                        break;
                    case "2nd year 2nd semester":
                        intent.putExtra("fetch", "22");
                        break;
                    case "3rd year 1st semester":
                        intent.putExtra("fetch", "31");
                        break;
                    case "3rd year 2nd semester":
                        intent.putExtra("fetch", "32");
                        break;
                    case "4th year 1st semester":
                        intent.putExtra("fetch", "41");
                        break;
                    case "4th year 2nd semester":
                        intent.putExtra("fetch", "42");
                        break;
                }
                startActivity(intent);
            }
        });
    }
}
