package com.sinbaddrinkshop.drinkshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.sinbaddrinkshop.drinkshop.Model.Result;
import com.sinbaddrinkshop.drinkshop.Retrofit.APIService;
import com.sinbaddrinkshop.drinkshop.utils.Common;
import com.sinbaddrinkshop.drinkshop.utils.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginButton;

    APIService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        mApiService = Common.getApiService();

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View v) {

        if (v == loginButton) {
            userLogin();
        }
    }

    private void userLogin() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();

        final MaterialEditText edit_email = (MaterialEditText) findViewById(R.id.edit_email);
        final MaterialEditText edit_password = (MaterialEditText) findViewById(R.id.edit_password);

        String email = edit_email.getText().toString().trim();
        String password = edit_password.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Common.ToastMessage(this, "Required missing Field");
            progressDialog.dismiss();
            return;
        }


        mApiService.userLogin(email, password)
                .enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        progressDialog.dismiss();
                        if (!response.body().getError()) {
                            finish();
                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(response.body().getUser());
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        } else {
                            Common.ToastMessage(getApplicationContext(), "Invalid email or password");
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        progressDialog.dismiss();
                        Common.ToastMessage(getApplicationContext(), t.getMessage());
                    }
                });

    }


}
