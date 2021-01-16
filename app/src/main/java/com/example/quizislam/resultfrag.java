package com.example.quizislam;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class resultfrag extends Fragment {

 private TextView result_tv,highscore_tv;
 private Button btnplayagain;
 int result,highscore;
    Start_quiz sq;
    Bundle bundle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_resultfrag, container, false);
        result_tv=view.findViewById(R.id.resultid);
       highscore_tv=view.findViewById(R.id.hstvid);

        btnplayagain=view.findViewById(R.id.play_againid);
        Bundle bundle=this.getArguments();
         int score= bundle.getInt("score");
           this.result=score;

        result_tv.setText("your score is:"+score);


        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("hs",Context.MODE_PRIVATE);
        highscore=sharedPreferences.getInt("highscorekey",0);
        if(score>highscore){
           highscore=result;
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt("highscorekey",highscore);
            editor.commit();
            highscore_tv.setText("Highscore: "+score);

        }else{
            highscore_tv.setText("Highscore: "+highscore);

        }

        btnplayagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Start_quiz sq=new Start_quiz();
                getFragmentManager().beginTransaction().replace(R.id.frag_container,sq).addToBackStack(null).commit();
            }
        });


        return view;
    }

}
