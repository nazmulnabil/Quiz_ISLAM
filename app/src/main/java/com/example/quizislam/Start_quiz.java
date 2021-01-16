package com.example.quizislam;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentTransitionImpl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



public class Start_quiz extends Fragment {

  Button btnstartquiz;
  TextView titletv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_start_quiz, container, false);
        titletv=view.findViewById(R.id.tvtitle);
        titletv.setText("ISLAMIC QUIZ");

        btnstartquiz=view.findViewById(R.id.button_start_quiz);
        btnstartquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frag_container, new quizfrag()).addToBackStack(null).commit();
            }
        });



        return view;


    }




}
