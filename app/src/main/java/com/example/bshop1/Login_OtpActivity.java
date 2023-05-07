package com.example.bshop1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bshop1.Utils.AndroidUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Login_OtpActivity extends AppCompatActivity {
    String PhoneNum;
    EditText otpInput;
    Button btNext;
    ProgressBar pb2;
    TextView tvResend;
    Long timeOutSec = 60L;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String verificationCode;
    PhoneAuthProvider.ForceResendingToken ResendingToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_otp);




        FirebaseApp.initializeApp(this);
        otpInput = findViewById(R.id.otpInput);
        btNext = findViewById(R.id.login_next);
        pb2 = findViewById(R.id.proBar2);
        tvResend = findViewById(R.id.resendOtp);


        PhoneNum = getIntent().getExtras().getString("phone");
        sendOTP(PhoneNum,false);

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enterOtp = otpInput.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode,enterOtp);
                signIn(credential);
                setInProgressBar(true);




            }
        });
        tvResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOTP(PhoneNum,true);
            }
        });


    }


    void sendOTP(String phoneNum,boolean isResend){
        startResendTimer();
        setInProgressBar(true);
        PhoneAuthOptions.Builder builder =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNum)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signIn(phoneAuthCredential);
                                setInProgressBar(false);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                AndroidUtils.showToast(getApplicationContext(),"OTP Verification Failed");
                                setInProgressBar(false);

                            }


                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                verificationCode = s;
                                ResendingToken = forceResendingToken;
                                AndroidUtils.showToast(getApplicationContext(),"OTP Sent SuccessFul");
                                setInProgressBar(false);




                            }
                        });
        if(isResend){
            PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(ResendingToken).build());
        }
        else {
            PhoneAuthProvider.verifyPhoneNumber(builder.build());
        }




    }



    private void signIn(PhoneAuthCredential phoneAuthCredential) {
        setInProgressBar(true);
        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                setInProgressBar(false);
                if(task.isSuccessful()){

                    Intent intent = new Intent(Login_OtpActivity.this, LoginUserNameActivity.class);
                    intent.putExtra("phone",PhoneNum);
                    startActivity(intent);



                }
                else {


                    AndroidUtils.showToast(getApplicationContext(),"verification Failed");
                }
            }
        });

    }

    void setInProgressBar(boolean inProgressBar){
        if(inProgressBar){
            pb2.setVisibility(View.VISIBLE);
            btNext.setVisibility(View.GONE);
        }
        else {
            pb2.setVisibility(View.GONE);
            btNext.setVisibility(View.VISIBLE);
        }


    }




    private void startResendTimer() {
        tvResend.setEnabled(false);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeOutSec--;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvResend.setText("resend otp in " + timeOutSec + " second");
                    }
                });
                if (timeOutSec <= 0) {
                    timeOutSec = 60L;
                    timer.cancel();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvResend.setEnabled(true);
                        }
                    });
                }
            }
        }, 0, 1000);
    }





}