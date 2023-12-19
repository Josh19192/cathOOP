package com.example.mycarcollection;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateAct extends AppCompatActivity {

    EditText name;
    EditText brand;
    EditText year;
    EditText price;
    EditText date;
    Button update_button, delete_button;
    String id1,name1,brand1,year1,price1,date1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name = findViewById(R.id.txt_carname2);
        brand = findViewById(R.id.txt_brand2);
        year = findViewById(R.id.txt_year2);
        price = findViewById(R.id.txt_price2);
        date = findViewById(R.id.txt_date2);
        update_button = findViewById(R.id.update_button);
        getAndSetIntentData();
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper myDB = new DBHelper(UpdateAct.this);
                myDB.updateData(id1, name.getText().toString(), brand.getText().toString(), year.getText().toString(), price.getText().toString(), date.getText().toString());

                Intent intent = new Intent(UpdateAct.this, MainActivity.class);
                startActivity(intent);
            }
        });
        delete_button = findViewById(R.id.delete_button);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               confirmDialog();
            }
        });



    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("brand") && getIntent().hasExtra("year") &&
                getIntent().hasExtra("price") && getIntent().hasExtra("date") ){
           //getting
            id1 = getIntent().getStringExtra("id");
            name1 = getIntent().getStringExtra("name");
            brand1 = getIntent().getStringExtra("brand");
            year1 = getIntent().getStringExtra("year");
            price1 = getIntent().getStringExtra("price");
            date1 = getIntent().getStringExtra("date");

            //setting
          name.setText(name1);
          brand.setText(brand1);
          year.setText(year1);
          price.setText(price1);
          date.setText(date1);
        }else{
            Toast.makeText(this,"No Data!",Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setTitle("Delete " + name1 + " ?");
        build.setMessage("Are you sure want to Delete " + name1 + " ?");
        build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper myDB = new DBHelper(UpdateAct.this);
                myDB.deleteData(id1);

                Intent intent = new Intent(UpdateAct.this, MainActivity.class);
                startActivity(intent);

            }
        });
        build.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        build.create().show();

    }
}