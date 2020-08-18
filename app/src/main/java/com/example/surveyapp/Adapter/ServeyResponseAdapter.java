package com.example.surveyapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surveyapp.Model.UserResponse;
import com.example.surveyapp.R;
import com.example.surveyapp.ServeyDetailsActivity;

import java.util.ArrayList;

public class ServeyResponseAdapter extends RecyclerView.Adapter<ServeyResponseAdapter.ViewHolder> {
    private Context context;
    private ArrayList<UserResponse> aUserResponseList;

    public ServeyResponseAdapter(Context context, ArrayList<UserResponse> aUserResponseList) {
        this.context = context;
        this.aUserResponseList = aUserResponseList;
    }

    @NonNull
    @Override
    public ServeyResponseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.serveylist_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ServeyResponseAdapter.ViewHolder holder, int position) {

        final UserResponse userResponse = aUserResponseList.get(position);

        String time = userResponse.getTimeStamp();
        String phone = userResponse.getuNumberAns();

        holder.tvSubmissionTime.setText(time);
        holder.tvPhone.setText(phone);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ServeyDetailsActivity.class);
                intent.putExtra("responseObject", userResponse);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return aUserResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSubmissionTime, tvPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubmissionTime = itemView.findViewById(R.id.submissionTime);
            tvPhone = itemView.findViewById(R.id.phoneTV);

        }
    }
}
