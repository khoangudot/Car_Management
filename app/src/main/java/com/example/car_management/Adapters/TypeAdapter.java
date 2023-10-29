package com.example.car_management.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.car_management.Models.CarType;
import com.example.car_management.R;

import java.util.ArrayList;

public class TypeAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CarType> carTypes;

    public TypeAdapter(Context context, ArrayList<CarType> carTypes) {
        this.context = context;
        this.carTypes = carTypes;
    }

    @Override
    public int getCount() {
        return carTypes.size();
    }

    @Override
    public Object getItem(int position) {
        return carTypes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = LayoutInflater.from(context).inflate(R.layout.type_item,parent,false);

        TextView txtTypeName = root.findViewById(R.id.item_typeName);
        txtTypeName.setText(carTypes.get(position).getTypeName());
        return root;
    }
}
