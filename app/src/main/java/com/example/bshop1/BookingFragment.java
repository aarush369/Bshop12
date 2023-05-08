package com.example.bshop1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bshop1.models.model;

import java.util.Objects;


public class BookingFragment extends Fragment {

    String iurl,name, place;

    public BookingFragment() {
        // Required empty public constructor
    }
    public BookingFragment(String iurl,String name,String place) {
        this.iurl = iurl;
        this.name = name;
        this.place = place;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_booking, container, false);
        ImageView imageView = view.findViewById(R.id.imageViewR);
        TextView textView = view.findViewById(R.id.tvbrow);
        TextView textView1 = view.findViewById(R.id.tvbrow1);

        textView.setText(name);
        textView1.setText(place);
        Glide.with(requireContext()).load(iurl).into(imageView);

        return view;

    }

    public void onBackpressed(){
        AppCompatActivity activity =(AppCompatActivity) getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).addToBackStack(null).commit();
    }
}