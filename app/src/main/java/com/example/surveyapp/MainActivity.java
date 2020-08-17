package com.example.surveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.surveyapp.Api.ApiInterface;
import com.example.surveyapp.Api.RetrofitClient;
import com.example.surveyapp.Api.Servey;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private TextView tvTextQuestion, tvNumberQuestion, tvMultipleChooseQuestion, tvDropDownQuestion, tvCheckboxQuestion;
    private EditText etTextAnswer, etNumberAnswer;
    private RadioGroup rB;
    private Spinner spProductReview;
    private CheckBox cb1, cb2;
    private Button submitBtn;
    private ArrayList<Servey> serverQuestionList;
    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        LoadServeyQuestions();
    }

    private void LoadServeyQuestions() {
        serverQuestionList = new ArrayList<>();
        String strUrl = RetrofitClient.BASE_URL+"getSurvey";
        Retrofit retrofit = RetrofitClient.getRetrofitClient();
        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<List<Servey>> call = api.getByUrlString(strUrl);

        call.enqueue(new Callback<List<Servey>>() {
            @Override
            public void onResponse(Call<List<Servey>> call, Response<List<Servey>> response) {

                if(response.code() == 200){
                    serverQuestionList.addAll(response.body());
                    Log.d(TAG, "onResponse: " + serverQuestionList.get(0).getQuestion() +
                            serverQuestionList.get(1).getQuestion() +
                            serverQuestionList.get(2).getQuestion() +
                            serverQuestionList.get(3).getQuestion() +
                            serverQuestionList.get(4).getQuestion());

                }
            }

            @Override
            public void onFailure(Call<List<Servey>> call, Throwable t) {

            }
        });

    }

    private void initView() {
        tvTextQuestion = findViewById(R.id.textQuestion);
        tvNumberQuestion = findViewById(R.id.numberQuestion);
        tvMultipleChooseQuestion = findViewById(R.id.multipleQuestion);
        tvDropDownQuestion = findViewById(R.id.dropDownQuestion);
        tvCheckboxQuestion = findViewById(R.id.checkboxQuestion);

        etTextAnswer = findViewById(R.id.editText);
        etNumberAnswer = findViewById(R.id.editNumber);

        spProductReview = findViewById(R.id.spinner);
        cb1 = findViewById(R.id.checkBox1);
        cb2 = findViewById(R.id.checkBox2);
        submitBtn = findViewById(R.id.btSubmit);
    }
}