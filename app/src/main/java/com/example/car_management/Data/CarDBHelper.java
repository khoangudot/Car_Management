package com.example.car_management.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.car_management.Models.CarModel;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;

public class CarDBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final  String DATABASE_NAME = "CarPE";
    private static  final int VERSION = 1;

    private static final String TABLE_NAME = "CarData";
    private static final String COLUMN_CarId = "CarId";
    private static final String COLUMN_PlateNo = "PlateNo";
    private static final String COLUMN_Owner = "Owner";
    private static final String COLUMN_Type = "Type";
    private static final String COLUMN_Price = "Price";
    private static final String COLUMN_Description = "Description";



    public CarDBHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME +
                "( " + COLUMN_CarId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PlateNo + " TEXT, " +
                COLUMN_Owner + " TEXT, " +
                COLUMN_Type + " TEXT, " +
                COLUMN_Price + " TEXT, " +
                COLUMN_Description + " TEXT);";

        db.execSQL(sql);

        ArrayList<CarModel> carModels = new ArrayList<>();
        carModels.add(new CarModel(1, "29A-29232", "Nguyễn Văn Hoàng", "Toyota Vios", new BigDecimal("123"), ""));
        carModels.add(new CarModel(2, "29A-29233", "Nguyễn Văn Hải", "KíAeltos", new BigDecimal("300"), ""));
        carModels.add(new CarModel(3, "29A-29231", "Hoàng Thị Anh", "", new BigDecimal("400"), ""));


        for (CarModel carModel : carModels) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_PlateNo, carModel.getCarPlateNo());
            values.put(COLUMN_Owner, carModel.getCarOwnerName());
            values.put(COLUMN_Type, carModel.getCarType());
            values.put(COLUMN_Price, carModel.getCarPrice().toString());
            values.put(COLUMN_Description, carModel.getCarDescription());

            db.insert(TABLE_NAME, null, values);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if(i!=i1){
            db.execSQL("Drop Table If Exists " +  TABLE_NAME);
            onCreate(db);
        }
    }

    // Create
    public long InsertCar(String plateNo, String ownerName, String type, BigDecimal price, String description){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_PlateNo, plateNo);
        values.put(COLUMN_Owner, ownerName);
        values.put(COLUMN_Type, type);
        values.put(COLUMN_Price, String.valueOf(price));

        values.put(COLUMN_Description, description);

        long result = db.insert(TABLE_NAME, null, values);

        return result;
    }

    public ArrayList<CarModel> getAllCars(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<CarModel> carModels = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int carId = cursor.getInt(0);
            String plateNo = cursor.getString(1);
            String ownerName = cursor.getString(2);
            String type = cursor.getString(3);
            BigDecimal price = BigDecimal.valueOf(Long.parseLong(cursor.getString(4)));
            String description = cursor.getString(5);

            carModels.add(new CarModel(carId,plateNo,ownerName, type,price,description));
            cursor.moveToNext();
        }
        cursor.close();
        return  carModels;
    }


}
