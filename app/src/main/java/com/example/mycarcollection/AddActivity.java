package com.example.mycarcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class AddActivity extends AppCompatActivity {

    EditText txtName, txtBrand,txtYear,txtPrice;
    Button addButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        String currentDate = getCurrentDate();


        txtName = findViewById(R.id.txt_carname);
        txtBrand = findViewById(R.id.txt_brand);
        txtYear = findViewById(R.id.txt_year);
        txtPrice = findViewById(R.id.txt_price);
        addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    DBHelper myDB = new DBHelper(AddActivity.this);
                    myDB.addCar(txtName.getText().toString().trim(),
                            txtBrand.getText().toString().trim(),
                            Integer.valueOf(txtYear.getText().toString().trim()),
                            Integer.valueOf(txtPrice.getText().toString().trim()),
                            currentDate);

                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);

             }
        });
    }










    private String getCurrentDate() {
        // Get the current date in the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(new Date());

    }
}