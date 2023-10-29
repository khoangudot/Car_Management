package com.example.car_management.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.car_management.Adapters.CarAdapter;
import com.example.car_management.Data.CarDBHelper;
import com.example.car_management.Models.CarModel;
import com.example.car_management.R;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context;
    ArrayList<CarModel> carModels;
    RecyclerView carRecyclerView;
    CarAdapter carAdapter;

    Button addNew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carModels = new ArrayList<>();
        carRecyclerView = findViewById(R.id.main_RecyclerView);
        addNew = findViewById(R.id.main_btnAddNew);

        CarDBHelper dbHelper =  new CarDBHelper(this);
        carRecyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
        carModels = dbHelper.getAllCars();

        carRecyclerView.setHasFixedSize(true);
        carAdapter =  new CarAdapter(carModels,MainActivity.this);
        carRecyclerView.setAdapter(carAdapter);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, CreateCarActivity.class);
                startActivity(intent);
            }
        });
    }
}