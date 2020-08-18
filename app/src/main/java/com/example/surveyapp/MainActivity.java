package com.example.surveyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.surveyapp.Adapter.ReviewAdapter;
import com.example.surveyapp.Api.ApiInterface;
import com.example.surveyapp.Api.RetrofitClient;
import com.example.surveyapp.Api.Servey;
import com.example.surveyapp.Model.Review;
import com.example.surveyapp.Model.UserResponse;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private TextView tvTextQuestion, tvNumberQuestion, tvMultipleChooseQuestion, tvDropDownQuestion, tvCheckboxQuestion;
    private EditText etTextAnswer, etNumberAnswer;
    private RadioGroup rB;
    private ProgressBar mProgresbar;
    private ConstraintLayout mConstant;
    private String modeType;
    private String reviewValue[];
    private String timeStamp;
    private String clickedProductTypeName;
    private RadioButton rb1, rb2, rb3, rb4, rb5;
    private Spinner spProductReview;
    private CheckBox cb1, cb2;
    private String cbAns;
    private Button submitBtn;
    private UserResponse userResponse;
    private ArrayList<Servey> serverQuestionList;
    private ArrayList<UserResponse> userResponseList;
    private ArrayList<Review> mProductTypeList;
    private ReviewAdapter mAdapter;
    private static final String PREFS_TAG = "SharedPrefs";
    private static final String PRODUCT_TAG = "MyProduct";
    private String textAns, numberAns, multiChooseAns, dropDownAns, checkBoxAns;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userResponseList = new ArrayList<>();

        initView();

        LoadServeyQuestions();

        loadOptions();

/////Time stamp Automated
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeStamp  = dateFormat.format(new Date());


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textAns = etTextAnswer.getText().toString().trim();
                numberAns = etNumberAnswer.getText().toString().trim();
                multiChooseAns = modeType;
                dropDownAns = clickedProductTypeName;
                checkBoxAns = cbAns;

                if (textAns.isEmpty()) {
                    etTextAnswer.setError("Home is required!");
                    etTextAnswer.requestFocus();
                    return;
                }

                if(multiChooseAns==null){
                    Toast.makeText(MainActivity.this, "No 3 is required!", Toast.LENGTH_SHORT).show();
                }

                if(dropDownAns==null){
                    Toast.makeText(MainActivity.this, "No 4 is required!", Toast.LENGTH_SHORT).show();
                }

                if(checkBoxAns==null){
                    Toast.makeText(MainActivity.this, "No 4 is required!", Toast.LENGTH_SHORT).show();
                }

                else{
                    Intent intent = new Intent(MainActivity.this, SuccessMessageActivity.class);

                    intent.putExtra("textAns", textAns);
                    intent.putExtra("numberAns", numberAns);
                    intent.putExtra("multiChooseAns", multiChooseAns);
                    intent.putExtra("dropDownAns", dropDownAns);
                    intent.putExtra("checkBoxAns", checkBoxAns);

                    startActivity(intent);
                    finish();
                    userResponse = new UserResponse(timeStamp, textAns, numberAns, multiChooseAns, dropDownAns, checkBoxAns);
                    addInJSONArray(userResponse);


                    etTextAnswer.setText("");
                    etNumberAnswer.setText("");
                }
            }
        });


    }


    private void addInJSONArray(UserResponse userResponse) {
        Gson gson = new Gson();
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE);

        String jsonSaved = sharedPref.getString(PRODUCT_TAG, "");
        String jsonNewproductToAdd = gson.toJson(userResponse);

        JSONArray jsonArrayProduct= new JSONArray();

        try {
            if(jsonSaved.length()!=0){
                jsonArrayProduct = new JSONArray(jsonSaved);
            }
            jsonArrayProduct.put(new JSONObject(jsonNewproductToAdd));
            //Log.d(TAG, "addInJSONArray: " + jsonNewproductToAdd);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //SAVE NEW ARRAY
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PRODUCT_TAG, String.valueOf(jsonArrayProduct));
        editor.commit();

        Log.d(TAG, "addInJSONArray: " +jsonArrayProduct);
    }


    private void LoadServeyQuestions() {
        serverQuestionList = new ArrayList<>();
        final String strUrl = RetrofitClient.BASE_URL + "getSurvey";
        Retrofit retrofit = RetrofitClient.getRetrofitClient();
        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<List<Servey>> call = api.getByUrlString(strUrl);

        call.enqueue(new Callback<List<Servey>>() {
            @Override
            public void onResponse(Call<List<Servey>> call, Response<List<Servey>> response) {

                if (response.code() == 200) {
                    serverQuestionList.addAll(response.body());

                    setQuestion(serverQuestionList);


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

    private void setQuestion(ArrayList<Servey> serverQuestionList) {
        mProgresbar.setVisibility(View.GONE);
        mConstant.setVisibility(View.VISIBLE);

        for (Servey servey : serverQuestionList) {
            Log.d(TAG, "setQuestion: " + servey.toString());
            if (servey.getType().equals("text")) {
                tvTextQuestion.setText(servey.getQuestion()+"*");
            } else if (servey.getType().equals("number")) {
                tvNumberQuestion.setText(servey.getQuestion());
            } else if (servey.getType().equals("multiple choice")) {
                tvMultipleChooseQuestion.setText(servey.getQuestion()+"*");
                final String options = servey.getOptions();
                String value[] = options.split(",");
                rb1.setText(value[0]);
                rb2.setText(value[1]);
                rb3.setText(value[2]);
                rb4.setText(value[3]);
                rb5.setText(value[4]);
                Log.d(TAG, "setQuestionOption: " + Arrays.toString(value));

            } else if (servey.getType().equals("dropdown")) {
                tvDropDownQuestion.setText(servey.getQuestion()+"*");
                final String options = servey.getOptions();
                reviewValue = options.split(",");
                initList();
                mAdapter = new ReviewAdapter(this, mProductTypeList);
                spProductReview.setAdapter(mAdapter);
                spProductReview.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Review clickedType = (Review) parent.getItemAtPosition(position);

                        clickedProductTypeName = clickedType.getReviewType();

                        Toast.makeText(MainActivity.this, clickedProductTypeName + " is selected !", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            } else if (servey.getType().equals("Checkbox")) {
                tvCheckboxQuestion.setText(servey.getQuestion()+"*");
            }
        }
    }

    private void loadOptions() {
        //Checkbox code
        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                //checkbox clicked
                if(checked){
                    //do code
                    cbAns = "Yes";
                    cb2.setChecked(false);
                    Toast.makeText(MainActivity.this, "you checked YES !", Toast.LENGTH_SHORT).show();
                }
                else{
                    //do code
                    Toast.makeText(MainActivity.this, "you unchecked Yes !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                //checkbox clicked
                if(checked){
                    //do code
                    cbAns = "No";
                    cb1.setChecked(false);
                    Toast.makeText(MainActivity.this, "you checked No !", Toast.LENGTH_SHORT).show();
                }
                else{
                    //do code
                    Toast.makeText(MainActivity.this, "you unchecked No!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Radio button code

        rB.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbType1 = (RadioButton) rB.findViewById(rB.getCheckedRadioButtonId());
                modeType = (String) rbType1.getText();

                Toast.makeText(MainActivity.this, modeType + " is Clicked!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onCreate: " + modeType);
            }
        });

    }

    private void initList() {
        mProductTypeList = new ArrayList<>();
        mProductTypeList.add(new Review("Select Product Type..."));
        Log.d(TAG, "setQuestionOptions: " + Arrays.toString(reviewValue));
        mProductTypeList.add(new Review(reviewValue[0]));
        mProductTypeList.add(new Review(reviewValue[1]));
        mProductTypeList.add(new Review(reviewValue[2]));
        mProductTypeList.add(new Review(reviewValue[3]));
        mProductTypeList.add(new Review(reviewValue[4]));
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
        rB = findViewById(R.id.typeOfMultiAns);
        cb1 = findViewById(R.id.checkBox1);
        cb2 = findViewById(R.id.checkBox2);
        submitBtn = findViewById(R.id.btSubmit);

        mProgresbar = findViewById(R.id.progressBar);
        mConstant = findViewById(R.id.constraintLayout);

        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        rb5 = findViewById(R.id.radioButton5);
    }

}