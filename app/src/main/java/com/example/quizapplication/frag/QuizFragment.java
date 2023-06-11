package com.example.quizapplication.frag;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.quizapplication.MainActivity;
import com.example.quizapplication.R;
import com.example.quizapplication.databinding.FragmentQuizBinding;
import com.example.quizapplication.model.Question;


public class QuizFragment extends Fragment {


    private FragmentQuizBinding binding;
    private int counter = 0;
    private int correct_score = 0;
    private int wrong = 0;

    private int minute=5;

    private int sec=60;
    private int unattempted = 0;

    //total question 15
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQuizBinding.inflate(inflater, container, false);


        binding.mainmenu.setOnClickListener(v -> {


            // Exit confirmation dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setMessage("Are you sure you want to exit?");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getContext(),MainActivity.class));
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });


                builder.show();
        });


        Handler handler=new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sec==0){
                    minute--;
                    sec=60;
                }

                if(minute<0){
                  showSecondFragment(new ResultFragment());
                }
                if(sec<10){
                    binding.timeLeft.setText(minute+":0"+sec);
                }else{
                    binding.timeLeft.setText(minute+":"+sec);
                }

                sec--;
                handler.postDelayed(this,1000);

            }
        },1000);



        Question question = MainActivity.questionList.get(counter);
        binding.questions.setText(question.getQuestion());
        binding.op1.setText(question.getOptions().get(0));
        binding.op2.setText(question.getOptions().get(1));
        binding.op3.setText(question.getOptions().get(2));
        binding.op4.setText(question.getOptions().get(3));




        binding.previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter == 0) {
                    Toast.makeText(getContext(), "Already at starting", Toast.LENGTH_SHORT).show();
                    return;
                }
                counter--;
                binding.records.setText("Attempted" + (counter + 1) + "/15");
                Question question = MainActivity.questionList.get(counter);
                binding.questions.setText(question.getQuestion());
                binding.op1.setText(question.getOptions().get(0));
                binding.op2.setText(question.getOptions().get(1));
                binding.op3.setText(question.getOptions().get(2));
                binding.op4.setText(question.getOptions().get(3));

            }
        });

        binding.nextBtn.setOnClickListener(v -> {

// Get the selected radio button's id
            int selectedRadioButtonId = binding.optionsGroup.getCheckedRadioButtonId();
            Question question1 = MainActivity.questionList.get(counter);

            if (selectedRadioButtonId != -1) {
                // Find the selected radio button using the id
                RadioButton selectedRadioButton = binding.optionsGroup.findViewById(selectedRadioButtonId);
                // Do something with the selected radio button
                String ans = (String) selectedRadioButton.getTag();
                if (ans.equals(question1.getCorrectAnswer())) {
                    correct_score++;
                } else {
                    wrong++;
                }
            } else {
                unattempted++;
            }

            counter++;

            if (counter ==15) {
                showSecondFragment(new ResultFragment());
                return;
            }
            binding.records.setText("Attempted" + (counter + 1) + "/15");
            Question question2 = MainActivity.questionList.get(counter);
            binding.questions.setText(question2.getQuestion());
            binding.op1.setText(question2.getOptions().get(0));
            binding.op2.setText(question2.getOptions().get(1));
            binding.op3.setText(question2.getOptions().get(2));
            binding.op4.setText(question2.getOptions().get(3));

            binding.optionsGroup.clearCheck();
            if (counter == 14) {
                binding.nextBtn.setText("Submit");
            }

        });
        return binding.getRoot();

    }



    public void showSecondFragment(Fragment fragment) {
        // Create an instance of the second Fragment

        // Create a Bundle to pass data
        Bundle bundle = new Bundle();
        bundle.putInt("correct_score", correct_score);
        bundle.putInt("wrong_score", wrong);
        bundle.putInt("unattempted", unattempted);
        // Set the arguments of the second Fragment
        fragment.setArguments(bundle);

        // Get the FragmentManager
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        // Start a FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the FrameLayout container in the MainActivity with the second Fragment
        fragmentTransaction.replace(R.id.frag_container, fragment);

        // Commit the transaction
        fragmentTransaction.commit();
    }
}
