package com.example.bshop1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.bshop1.Utils.FirebaseUtils;
import com.example.bshop1.models.UserModels;
import com.google.firebase.Timestamp;

public class LoginUserNameActivity extends AppCompatActivity {
    Button letInBt;
    EditText etUserName;
    ProgressBar pb3;
    UserModels userModels;
    String phoneNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user_name);

        letInBt = findViewById(R.id.letMeIn);
        pb3 = findViewById(R.id.proBar3);
        etUserName = findViewById(R.id.loginName);
        phoneNum = getIntent().getExtras().getString("phone");

        getUserName();

        setInProgressBar(false);



        letInBt.setOnClickListener(v -> {
            setInProgressBar(true);
            String userName = etUserName.getText().toString();
            if(userName.isEmpty()|| userName.length()<3){
                etUserName.setError("Fill proper name");
                return;
            }
            if(userModels != null){
                userModels.setUserName(userName);
            }
            else {

                userModels = new UserModels(phoneNum,userName, Timestamp.now());
            }
            FirebaseUtils.currentUserDetails().set(userModels).addOnCompleteListener(task -> {
                setInProgressBar(false);
                if(task.isSuccessful()){

                    Intent intent = new Intent(LoginUserNameActivity.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

            });

        });



    }

    private void getUserName() {
        setInProgressBar(true);
        FirebaseUtils.currentUserDetails().get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                userModels= task.getResult().toObject(UserModels.class);
                if(userModels != null){
                    etUserName.setText(userModels.getUserName());

                }



            }
        });


    }

    void setInProgressBar(boolean inProgressBar){
        if(inProgressBar){
            pb3.setVisibility(View.VISIBLE);
            letInBt.setVisibility(View.GONE);
        }
        else {
            pb3.setVisibility(View.GONE);
            letInBt.setVisibility(View.VISIBLE);
        }


    }
}