package com.example.surveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.surveyapp.Model.UserResponse;

import de.hdodenhof.circleimageview.CircleImageView;

public class ServeyDetailsActivity extends AppCompatActivity {

   private UserResponse userResponse = new UserResponse();
    private TextView textAnswer, numberAnswer, multiAnswer, dropDownAnswer, checkBoxAnswer, tvTime;
    private CircleImageView btDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servey_details);

        initView();
        Intent intent = getIntent();
        userResponse = (UserResponse) intent.getSerializableExtra("responseObject");

        tvTime.setText(userResponse.getTimeStamp());
        textAnswer.setText(userResponse.getuTextAns());
        numberAnswer.setText(userResponse.getuNumberAns());
        multiAnswer.setText(userResponse.getuMultiAns());
        dropDownAnswer.setText(userResponse.getuDropDownAns());
        checkBoxAnswer.setText(userResponse.getuCheckBoxAns());

        btDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ServeyDetailsActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });

    }

    private void initView() {
        textAnswer = findViewById(R.id.tvText);
        numberAnswer = findViewById(R.id.tvNumber);
        multiAnswer = findViewById(R.id.tvModeType);
        dropDownAnswer = findViewById(R.id.tvReviewType);
        checkBoxAnswer = findViewById(R.id.tvUseableType);
        btDone = findViewById(R.id.done);

        tvTime = findViewById(R.id.subTime);
    }
}