package com.example.mycarcollection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaDrm;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.net.Inet4Address;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHlder> {
    private Context context;
    private ArrayList car_id, car_name,car_brand, year,price,date;


    CustomAdapter(Context context, ArrayList car_id, ArrayList car_name, ArrayList car_brand,ArrayList year,ArrayList price, ArrayList date){
        this.context = context;
        this.car_id = car_id;
        this.car_name = car_name;
        this.car_brand = car_brand;
        this.year = year;
        this.price = price;
        this.date = date;
    }

    @NonNull
    @Override
    public MyViewHlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);

        return new MyViewHlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHlder holder, @SuppressLint("RecyclerView") int position) {

        holder.car_id.setText(String.valueOf(car_id.get(position)));
        holder.car_name.setText(String.valueOf(car_name.get(position)));
        holder.car_brand.setText(String.valueOf(car_brand.get(position)));
        holder.year.setText(String.valueOf(year.get(position)));
        holder.price.setText(String.valueOf("Price: PHP"+ price.get(position)));
        holder.date.setText(String.valueOf("DOP: " + date.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateAct.class );
                intent.putExtra("id", String.valueOf(car_id.get(position)));
                intent.putExtra("name", String.valueOf(car_name.get(position)));
                intent.putExtra("brand", String.valueOf(car_brand.get(position)));
                intent.putExtra("year", String.valueOf(year.get(position)));
                intent.putExtra("price", String.valueOf(price.get(position)));
                intent.putExtra("date", String.valueOf(date.get(position)));
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return car_id.size();
    }

    public class MyViewHlder extends RecyclerView.ViewHolder{
        TextView car_id, car_name, car_brand,year,price,date;
       LinearLayout mainLayout;
        public MyViewHlder(@NonNull View itemView) {
            super(itemView);

            car_id = itemView.findViewById(R.id.car_id_txt);
            car_name = itemView.findViewById(R.id.car_name_txt);
            car_brand = itemView.findViewById(R.id.car_brand_txt);
            year = itemView.findViewById(R.id.year_txt);
            price = itemView.findViewById(R.id.price_txt);
            date = itemView.findViewById(R.id.date_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
