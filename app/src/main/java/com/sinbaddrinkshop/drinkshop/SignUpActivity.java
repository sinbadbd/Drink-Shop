package com.sinbaddrinkshop.drinkshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.sinbaddrinkshop.drinkshop.Model.Result;
import com.sinbaddrinkshop.drinkshop.Model.User;
import com.sinbaddrinkshop.drinkshop.Retrofit.APIService;
import com.sinbaddrinkshop.drinkshop.utils.Common;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText edit_name, edit_email, edit_password;
    private RadioGroup radioGroup;

    APIService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        mApiService = Common.getApiService();

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_password = (EditText) findViewById(R.id.edit_password);


        radioGroup = (RadioGroup) findViewById(R.id.radioGender);


        buttonRegister.setOnClickListener((View.OnClickListener) this);
    }

    private void userSingUp() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();

        final RadioButton radioSex = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());

        String name = edit_name.getText().toString().trim();
        String email = edit_email.getText().toString().trim();
        String password = edit_password.getText().toString().trim();
        String gender = radioSex.getText().toString();

        User user = new User(name, email, password, gender);

        Call<Result> call = mApiService.createUser(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getGender()
        );

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public void onClick(View v) {
        if (v == buttonRegister) {
            userSingUp();
        }
    }
}
