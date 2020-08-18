package com.example.surveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.surveyapp.Model.UserResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SuccessMessageActivity extends AppCompatActivity {

    private Button dashBoard;
    private String text, number, multiChoose, dropDown, checkBox;
   /* private static final String PREFS_TAG = "SharedPrefs";
    private static final String PRODUCT_TAG = "MyProduct";
    public static final String TAG ="Success";*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_message);

        dashBoard = findViewById(R.id.goToDash);
        Intent intent = getIntent();

         text = intent.getStringExtra("textAns");
         number = intent.getStringExtra("numberAns");
         multiChoose = intent.getStringExtra("multiChooseAns");
         dropDown = intent.getStringExtra("dropDownAns");
         checkBox = intent.getStringExtra("checkBoxAns");

      /* List<UserResponse> userResponseList = getDataFromSharedPreferences();
        Log.d(TAG, "onCreate: " +userResponseList);
        for(UserResponse userResponse : userResponseList){
            Log.d(TAG, "onCreateloop: " + userResponse.toString());
        }*/

        dashBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccessMessageActivity.this, DashBoardActivity.class);
                intent.putExtra("textAns", text);
                intent.putExtra("numberAns", number);
                intent.putExtra("multiChooseAns", multiChoose);
                intent.putExtra("dropDownAns", dropDown);
                intent.putExtra("checkBoxAns", checkBox);
                startActivity(intent);
                finish();
            }
        });
    }

 /*   private List<UserResponse> getDataFromSharedPreferences(){
        Gson gson = new Gson();
        List<UserResponse> productFromShared = new ArrayList<>();
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE);
        String jsonPreferences = sharedPref.getString(PRODUCT_TAG, "");

        Type type = new TypeToken<List<UserResponse>>() {}.getType();
        productFromShared = gson.fromJson(jsonPreferences, type);

        Log.d(TAG, "getDataFromSharedPreferences: " +productFromShared);
        return productFromShared;
    }*/
}