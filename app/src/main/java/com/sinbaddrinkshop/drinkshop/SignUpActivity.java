package com.sinbaddrinkshop.drinkshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.sinbaddrinkshop.drinkshop.Model.Result;
import com.sinbaddrinkshop.drinkshop.Model.User;
import com.sinbaddrinkshop.drinkshop.Retrofit.APIService;
import com.sinbaddrinkshop.drinkshop.utils.Common;
import com.sinbaddrinkshop.drinkshop.utils.SharedPrefManager;

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
        //    final MaterialEditText edit_name = (MaterialEditText)


        radioGroup = (RadioGroup) findViewById(R.id.radioGender);


        buttonRegister.setOnClickListener((View.OnClickListener) this);
    }

    private void userSingUp() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();

        final RadioButton radioSex = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());

        final MaterialEditText edit_name = (MaterialEditText) findViewById(R.id.edit_name);
        final MaterialEditText edit_email = (MaterialEditText) findViewById(R.id.edit_email);
        final MaterialEditText edit_password = (MaterialEditText) findViewById(R.id.edit_password);

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
        if (TextUtils.isEmpty(name) ||
                TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(gender)) {
            Common.ToastMessage(this, "Required missing field");
            progressDialog.dismiss();
            return;
        }
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                //  startActivity(new Intent(SignUpActivity.this, SignInActivity.class));

                // If no error
                if (!response.body().getError()) {
                    finish();
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(response.body().getUser());
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }

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
