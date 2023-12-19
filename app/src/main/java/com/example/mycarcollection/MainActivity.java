package com.example.mycarcollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    DBHelper myDB;
    ArrayList<String > car_id, car_name, car_brand, year, price, date;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);

            }
        });

        myDB = new DBHelper(MainActivity.this);
        car_id = new ArrayList<>();
        car_name = new ArrayList<>();
        car_brand = new ArrayList<>();
        year = new ArrayList<>();
        price = new ArrayList<>();
        date = new ArrayList<>();

        displayAllData();

        customAdapter = new CustomAdapter(MainActivity.this, car_id,car_name,car_brand,year,price,date);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }

    void displayAllData(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No Data!", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                car_id.add(cursor.getString(0));
                car_name.add(cursor.getString(1));
                car_brand.add(cursor.getString(2));
                year.add(cursor.getString(3));
                price.add(cursor.getString(4));
                date.add(cursor.getString(5));


            }
        }
    }
}