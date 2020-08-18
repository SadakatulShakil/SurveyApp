package com.example.surveyapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.surveyapp.Model.Review;
import com.example.surveyapp.R;

import java.util.ArrayList;

public class ReviewAdapter extends ArrayAdapter<Review> {
    private ArrayList<Review> reviewTypeArrayList;
    private Context context;
    public ReviewAdapter(@NonNull Context context, ArrayList<Review> reviewTypeArrayList) {
        super(context, 0, reviewTypeArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }


    @Override
    public boolean isEnabled(int position) {
        return position == 0 ? false : true;
    }

    private View initView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.review_type, parent, false);

        }

        TextView productTypeName = convertView.findViewById(R.id.productType);
        Review currentCurrentType = getItem(position);

        if (position == 0) {
            productTypeName.setTextColor(Color.GRAY);
        } else {
            productTypeName.setTextColor(Color.BLACK);

        }
        if (currentCurrentType != null) {
            productTypeName.setText(currentCurrentType.getReviewType());
        }

        return convertView;
    }
}
