package com.example.car_management.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.car_management.Adapters.CarAdapter;
import com.example.car_management.Adapters.TypeAdapter;
import com.example.car_management.Data.CarDBHelper;
import com.example.car_management.Models.CarModel;
import com.example.car_management.Models.CarType;
import com.example.car_management.R;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CreateCarActivity extends AppCompatActivity {
    Context context;
    EditText txtPlateNo, txtOwnerName, txtPrice, txtDescription;
    Spinner spinner_type;
    Button createBtn;
    ImageView backToList;

    private TypeAdapter typeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_car);
        txtPlateNo = findViewById(R.id.create_carPlateNo);
        txtOwnerName = findViewById(R.id.create_OwnerName);
        spinner_type = findViewById(R.id.create_type);
        txtPrice = findViewById(R.id.create_Price);
        txtDescription = findViewById(R.id.create_Description);
        createBtn = findViewById(R.id.create_btnCreate);
        backToList = findViewById(R.id.create_backToList);

        ArrayList<CarType> carTypes = new ArrayList<>();
        carTypes.add(new CarType("Toyota Vios"));
        carTypes.add(new CarType("KiA Seltos"));

        typeAdapter = new TypeAdapter(CreateCarActivity.this, carTypes);
        spinner_type.setAdapter(typeAdapter);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plateNoStr = txtPlateNo.getText().toString();
                String ownerNameStr = txtOwnerName.getText().toString();
                CarType selectedType = (CarType) spinner_type.getSelectedItem();
                String carType = selectedType.getTypeName();
                BigDecimal priceStr = null;
                try {
                    priceStr = new BigDecimal(txtPrice.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(CreateCarActivity.this, "Giá  không hợp lệ", Toast.LENGTH_SHORT).show();
                }
                String descriptionStr = txtDescription.getText().toString();


                if (plateNoStr.isEmpty() || plateNoStr.length() > 10 || !plateNoStr.matches("^[0-9-]+$")) {
                    Toast.makeText(CreateCarActivity.this, "Số biển số không hợp lệ", Toast.LENGTH_SHORT).show();
                } else if (ownerNameStr.isEmpty() || ownerNameStr.length() > 50) {
                    Toast.makeText(CreateCarActivity.this, "Tên chủ xe không hợp lệ", Toast.LENGTH_SHORT).show();
                } else if (priceStr.compareTo(BigDecimal.ZERO) <= 0 || priceStr.toString().length() > 5) {
                    Toast.makeText(CreateCarActivity.this, "Giá không hợp lệ", Toast.LENGTH_SHORT).show();
                } else if (descriptionStr.length() > 100) {
                    Toast.makeText(CreateCarActivity.this, "Mô tả quá dài", Toast.LENGTH_SHORT).show();
                } else {
                    CarDBHelper carDBHelper =  new CarDBHelper(CreateCarActivity.this);
                    long result =  carDBHelper.InsertCar(plateNoStr,ownerNameStr,carType,priceStr,descriptionStr);
                    if(result == -1){
                        Toast.makeText(CreateCarActivity.this, "Thêm mới thất bại", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(CreateCarActivity.this, "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                        txtPlateNo.setText("");
                        txtOwnerName.setText("");
                        txtPrice.setText("");
                        txtDescription.setText("");
                    }
                }
            }
        });

        backToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateCarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}