package com.example.car_management.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.car_management.Models.CarModel;
import com.example.car_management.R;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    ArrayList<CarModel> carModels;
    Context context;

    public CarAdapter(ArrayList<CarModel> carModels, Context context) {
        this.carModels = carModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_car_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.CarId.setText(String.valueOf(carModels.get(position).getCarId()));
        holder.PlateNo.setText(carModels.get(position).getCarPlateNo());
        holder.CarOwnerName.setText(carModels.get(position).getCarOwnerName());
        holder.CarPrice.setText(String.format("%,.2f",carModels.get(position).getCarPrice()));
        holder.CarType.setText(carModels.get(position).getCarType());


    }

    @Override
    public int getItemCount() {
        return carModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView CarId, PlateNo, CarOwnerName, CarType, CarPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CarId = itemView.findViewById(R.id.item_carId);
            PlateNo = itemView.findViewById(R.id.item_plateNo);
            CarOwnerName = itemView.findViewById(R.id.item_carOwner);
            CarType = itemView.findViewById(R.id.item_type);
            CarPrice = itemView.findViewById(R.id.item_price);
        }
    }
}
