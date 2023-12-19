package com.example.mycarcollection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    private  Context context;
    private static final String DB_NAME = "MyCarCollection1.db";
    private static  final int DB_VERSION =1;
    private static final String table_name = "my_cars";
    private static final String column_id = "id";
    private static final String column_carname = "car_name";
    private static final String column_brand = "car_brand";
    private static final String column_year = "year_model";
    private static final String column_price = "price";
    private static final String column_date = "purchase_date";

     DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name +
                " (" + column_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                column_carname + " TEXT NOT NULL, " +
                column_brand + " TEXT NOT NULL, " +
                column_year + " INTEGER NOT NULL, " +
                column_price + " INTEGER NOT NULL, " +
                column_date + " DATE);";
                db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int ii) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);

    }
    void addCar(String name,String brand, int year,int price, String purchase){
        if (name == null || brand == null) {
            // Handle the case where any of the required parameters is null
            Toast.makeText(context, "Please provide all required information", Toast.LENGTH_SHORT).show();
            return; // Exit the method to avoid attempting to insert null values
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(column_carname,name);
        cv.put(column_brand,brand);
        cv.put(column_year,year);
        cv.put(column_price,price);
        cv.put(column_date, purchase);

        long result = db.insert(table_name,null,cv);
        if(result == -1){
            Toast.makeText(context,"Add Failed!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Succesfully added to collection!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + table_name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor= null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    void updateData(String row_id, String name2, String brand2, String year2, String price2,String date2){
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues cv = new ContentValues();
         cv.put(column_carname, name2);
         cv.put(column_brand, brand2);
         cv.put(column_year, year2);
         cv.put(column_price, price2);
         cv.put(column_date, date2);

         long result = db.update(table_name, cv, "id=?", new String[] {row_id});
         if(result == -1){
             Toast.makeText(context, "Update Failed!", Toast.LENGTH_SHORT).show();
         }
         else{
             Toast.makeText(context, "Successfully Update!", Toast.LENGTH_SHORT).show();
         }
    }

    void deleteData(String row_id){
         SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(table_name, "id=?", new String[]{row_id});
         if(result == -1){
             Toast.makeText(context, "Failed to Delete!", Toast.LENGTH_SHORT).show();
         }else{
             Toast.makeText(context, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
         }
    }

}
