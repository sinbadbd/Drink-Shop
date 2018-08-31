package com.sinbaddrinkshop.drinkshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button signIn, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signIn = (Button) findViewById(R.id.signIn);
        signUp = (Button) findViewById(R.id.signUp);

        signIn.setOnClickListener((View.OnClickListener) this);
        signUp.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        if (v == signIn) {
            startActivity(new Intent(this, SignInActivity.class));
        } else if (v == signUp) {
            startActivity(new Intent(this, SignUpActivity.class));
        }
    }
}
