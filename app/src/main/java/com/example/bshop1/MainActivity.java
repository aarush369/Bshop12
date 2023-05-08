package com.example.bshop1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.accounts.Account;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bshop1.Utils.FirebaseUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView btn;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // In AnotherActivity



        HomeFragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, "");
        fragmentTransaction.commit();
btn = findViewById(R.id.btnV);
//        Fragment fragment;
//        fragment = new HomeFragment();
//        loadFragment(fragment);
        btn.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                if (item.getItemId() == R.id.HomeID) {
                    fragment = new HomeFragment();


                } else if (item.getItemId() == R.id.AccountID) {
                    fragment = new AccountFragment();
                }
                return loadingFragment(fragment);
            }
        });


    }

    private boolean loadingFragment(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//frame_container is your layout name in xml file
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

return true;
    }



//    private void loadFragment(Fragment fragment) {
//
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
////frame_container is your layout name in xml file
//        transaction.replace(R.id.container, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }
}
