package com.example.quizapplication.frag;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quizapplication.MainActivity;
import com.example.quizapplication.R;
import com.example.quizapplication.databinding.FragmentResultBinding;


public class ResultFragment extends Fragment {




private FragmentResultBinding bindingg;
private int correct_score,wrong,unattempted;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bindingg=FragmentResultBinding.inflate(inflater,container,false);
        // Retrieve the data passed from the sending Fragment
        Bundle bundle = getArguments();
        if (bundle != null) {
             correct_score = bundle.getInt("correct_score");
             wrong=bundle.getInt("wrong_score");
             unattempted=bundle.getInt("unattempted");
        }

        bindingg.correctAns.setText("Correct Answer:"+correct_score);
        bindingg.wrongAns.setText("Wrong Answer:"+wrong);
        bindingg.unattempt.setText("Unattempted:"+unattempted);


        bindingg.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });


        return bindingg.getRoot();
    }
}