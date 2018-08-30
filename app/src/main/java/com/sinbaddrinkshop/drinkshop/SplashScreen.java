package com.sinbaddrinkshop.drinkshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.sinbaddrinkshop.drinkshop.Model.CheckUserResponse;
import com.sinbaddrinkshop.drinkshop.Model.User;
import com.sinbaddrinkshop.drinkshop.Retrofit.IDrinkShopAPI;
import com.sinbaddrinkshop.drinkshop.utils.Common;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashScreen extends AppCompatActivity {

    Button buttonContinue;

    private final static int REQUEST_CODE = 1000;

    IDrinkShopAPI mService;

    //  RetrofitClient retrofitClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // userAPI = RetrofitClient.getRetrofit().create(UserAPI.class);
        //  retrofitClient = new RetrofitClient();


        mService = Common.getAPI();

        Log.d("api", mService.toString());


        buttonContinue = (Button) findViewById(R.id.buttonContinue);
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoginPage(LoginType.PHONE);
            }
        });
    }


    private void startLoginPage(LoginType loginType) {

        Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder builder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(loginType, AccountKitActivity.ResponseType.TOKEN);

        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, builder.build());
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            AccountKitLoginResult result = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);


            if (result.getError() != null) {
                Toast.makeText(this, "" + result.getError().getErrorType().getMessage(), Toast.LENGTH_LONG).show();
            } else if (result.wasCancelled()) {

                Toast.makeText(this, "Cancel", Toast.LENGTH_LONG).show();

            } else {
                if (result.getAccessToken() != null) {

                    final SpotsDialog alertDialog = new SpotsDialog(SplashScreen.this);
                    alertDialog.show();
                    alertDialog.setMessage("Please wait...");

                    // Get user phone and check exits server data
                    System.out.print("hi imran");
                    AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                        @Override
                        public void onSuccess(final Account account) {

                            mService.checkUserExist(account.getPhoneNumber().toString())
                                    .enqueue(new Callback<CheckUserResponse>() {
                                        @Override

                                        public void onResponse(Call<CheckUserResponse> call, Response<CheckUserResponse> response) {
                                            CheckUserResponse userResponse = response.body();

                                            Log.d("response", userResponse.toString());
                                            System.out.print("hi imran");


                                            if (response.isSuccessful()) {
                                                if (userResponse.isExists()) {
                                                    alertDialog.dismiss();
                                                } else {
                                                    alertDialog.dismiss();
                                                    showRegisterDialog(account.getPhoneNumber().toString());
                                                }

                                            }
//                                            if (userResponse.isExists()) {
//                                                //user already exits, just start activity
//                                                alertDialog.dismiss();
//                                            } else {
//                                                alertDialog.dismiss();
//                                                showRegisterDialog(account.getPhoneNumber().toString());
//                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<CheckUserResponse> call, Throwable t) {

                                            ToastMessage(t.getMessage());
                                        }
                                    });
                        }

                        @Override
                        public void onError(AccountKitError accountKitError) {
                            Log.d("Error", accountKitError.getErrorType().toString());
                        }
                    });
                }
            }

        }
    }

    private void showRegisterDialog(final String phone) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(SplashScreen.this);
        alertDialog.setTitle("Register");
        LayoutInflater inflater = this.getLayoutInflater();
        View register_layout = inflater.inflate(R.layout.signup_activity, null);

//        MaterialEditText name = (MaterialEditText)register_layout.findViewById(R.id.na);

        final MaterialEditText edit_name = (MaterialEditText) register_layout.findViewById(R.id.edit_name);
        final MaterialEditText edit_address = (MaterialEditText) register_layout.findViewById(R.id.edit_address);
        final MaterialEditText edit_country = (MaterialEditText) register_layout.findViewById(R.id.edit_country);

        final MaterialEditText edit_phone = (MaterialEditText) register_layout.findViewById(R.id.edit_phone);

        Button buttonRegister = (Button) register_layout.findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.create().dismiss();

                if (TextUtils.isEmpty(edit_phone.getText().toString())) {
                    ToastMessage("Please enter your name");
                    return;
                }

                if (TextUtils.isEmpty(edit_name.getText().toString())) {
                    ToastMessage("Please enter your Address");
                    return;
                }
                if (TextUtils.isEmpty(edit_country.getText().toString())) {
                    ToastMessage("Please enter your City");
                    return;
                }

                if (TextUtils.isEmpty(edit_address.getText().toString())) {
                    ToastMessage("Please enter your Country");
                    return;
                }


                final SpotsDialog watingDialog = new SpotsDialog(SplashScreen.this);
                watingDialog.show();
                watingDialog.setMessage("Please wait...");


                mService.registerNameUser(phone,
                        edit_name.getText().toString(),
                        edit_country.getText().toString(),
                        edit_address.getText().toString())
                        .enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                watingDialog.dismiss();
                                User user = response.body();
                                if (TextUtils.isEmpty(user.getError_msg())) {

                                    ToastMessage("Register Successfully");
                                }

                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                watingDialog.dismiss();
                            }
                        });

            }
            //close dialog

        });

        alertDialog.setView(register_layout);
        alertDialog.show();
    }

    private void ToastMessage(String message) {
        Toast.makeText(SplashScreen.this, message, Toast.LENGTH_LONG).show();
    }
}
