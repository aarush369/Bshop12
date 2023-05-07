package com.example.bshop1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.hbb20.CountryCodePicker;

public class PhoneLoginActivity extends AppCompatActivity {
    CountryCodePicker ccp;
    EditText phoneInput;
    Button bt_Sent_otp;
    ProgressBar pb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);
        ccp = findViewById(R.id.login_ccp);
        phoneInput = findViewById(R.id.mo_num);
        pb1 = findViewById(R.id.proBar1);
        bt_Sent_otp = findViewById(R.id.send_otp);

        ccp.registerCarrierNumberEditText(phoneInput);
        pb1.setVisibility(View.GONE);
        bt_Sent_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!ccp.isValidFullNumber()){
                    phoneInput.setError("number not valid");
                    return;
                }
                Intent intent = new Intent(PhoneLoginActivity.this, Login_OtpActivity.class);
                intent.putExtra("phone",ccp.getFullNumberWithPlus());
                startActivity(intent);


            }
        });





    }
}