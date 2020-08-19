package com.example.surveyapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.surveyapp.Adapter.ServeyResponseAdapter;
import com.example.surveyapp.Model.UserResponse;
import com.example.surveyapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashBoardActivity extends AppCompatActivity {
    private Toolbar dToolbar;
    private TextView textAnswer, numberAnswer, multiAnswer, dropDownAnswer, checkBoxAnswer;
    public static final String TAG = "DashBoard";
    private static final String PREFS_TAG = "SharedPrefs";
    private static final String PRODUCT_TAG = "MyProduct";
    private RecyclerView mRecyclerView;
    private ServeyResponseAdapter responseAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<UserResponse> userResponseInfoList = new ArrayList<>();
    private TextView current, seeAll;
    private LinearLayout currentDataLayout;
    private CircleImageView btDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        initView();

        dToolbar.setTitle(getString(R.string.dashboard));
        Intent intent = getIntent();

        textAnswer.setText(intent.getStringExtra("textAns"));
        numberAnswer.setText(intent.getStringExtra("numberAns"));
        multiAnswer.setText(intent.getStringExtra("multiChooseAns"));
        dropDownAnswer.setText(intent.getStringExtra("dropDownAns"));
        checkBoxAnswer.setText(intent.getStringExtra("checkBoxAns"));
        Log.d(TAG, "onCreate: " + checkBoxAnswer);

        List<UserResponse> userResponseList = getDataFromSharedPreferences();
        Log.d(TAG, "onCreate: " +userResponseList);
        for(UserResponse userResponse : userResponseList){

            userResponseInfoList.add(userResponse);

            Log.d(TAG, "onCreateloop: " + userResponse.toString());
        }

        mRecyclerView = findViewById(R.id.userResponseRecycleView);
        responseAdapter = new ServeyResponseAdapter(DashBoardActivity.this, userResponseInfoList);
        layoutManager = new LinearLayoutManager(DashBoardActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(responseAdapter);


        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDataLayout.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            }
        });
        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.setVisibility(View.GONE);
                currentDataLayout.setVisibility(View.VISIBLE);
            }
        });

        btDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DashBoardActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }

    private List<UserResponse> getDataFromSharedPreferences(){
        Gson gson = new Gson();
        List<UserResponse> productFromShared = new ArrayList<>();
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE);
        String jsonPreferences = sharedPref.getString(PRODUCT_TAG, "");

        Type type = new TypeToken<List<UserResponse>>() {}.getType();
        productFromShared = gson.fromJson(jsonPreferences, type);

        Log.d(TAG, "getDataFromSharedPreferences: " +productFromShared);
        return productFromShared;
    }


    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dToolbar = findViewById(R.id.toolbar);
        }
        textAnswer = findViewById(R.id.tvText);
        numberAnswer = findViewById(R.id.tvNumber);
        multiAnswer = findViewById(R.id.tvModeType);
        dropDownAnswer = findViewById(R.id.tvReviewType);
        checkBoxAnswer = findViewById(R.id.tvUseableType);
        btDone = findViewById(R.id.done);
        currentDataLayout = findViewById(R.id.linearLayout);

        current = findViewById(R.id.currentData);
        seeAll = findViewById(R.id.seeAllBt);
    }
}