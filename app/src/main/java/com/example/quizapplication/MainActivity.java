package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.quizapplication.frag.QuizFragment;
import com.example.quizapplication.frag.Records_Fragment;
import com.example.quizapplication.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    //Quiz application
    // Gk question and 4 options
    //selection option and storing the correct answer in a variable and after finishing the qiz showing the score on the last fragment
    //having different question in each round
    //3 fragment
    //1-> menu
    //2-> question fragment
    //3-> result fragment
    //question wille be stored in  a arrray list
    //theme will be bule and white
    ImageView imageView,previous,exitfrag;
    LinearLayout l1;


    public static ArrayList<Question> questionList = new ArrayList<>();
    public  FrameLayout frameLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.start_quiz);
        previous = findViewById(R.id.previous);
        exitfrag = findViewById(R.id.exit_me);
        l1=findViewById(R.id.l1);
        frameLayout=findViewById(R.id.frag_container);
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                setquestion();
            }
        });


        exitfrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        previous.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Records_Fragment records_fragment = new Records_Fragment();
                startfragment(records_fragment);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.GONE);
                QuizFragment newFragment = new QuizFragment();
                startfragment(newFragment);
            }
        });

    }




    public void startfragment(Fragment fragment) {
        frameLayout.setVisibility(View.VISIBLE);
        FragmentManager childFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = childFragmentManager.beginTransaction();
        transaction.add(R.id.frag_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void onBackPressed() {
        l1.setVisibility(View.VISIBLE);
        frameLayout.setVisibility(View.GONE);
    }

    private void setquestion(){


// Science Questions
        questionList.add(new Question(
                "What is the smallest unit of matter?",
                new ArrayList<>(List.of("Atom", "Molecule", "Cell", "Particle")),
                "3"
        ));
        questionList.add(new Question(
                "What is the largest planet in our solar system?",
                new ArrayList<>(List.of("Saturn", "Neptune", "Jupiter", "Mars")),
                "1"
        ));
        questionList.add(new Question(
                "What is the chemical symbol for gold?",
                new ArrayList<>(List.of("Au", "Ag", "Cu", "Fe")),
                "2"
        ));
        questionList.add(new Question(
                "What is the process by which plants convert sunlight into energy?",
                new ArrayList<>(List.of("Photosynthesis", "Respiration", "Transpiration", "Fermentation")),
                "0"
        ));
        questionList.add(new Question(
                "Which scientist is credited with the theory of general relativity?",
                new ArrayList<>(List.of("Albert Einstein", "Isaac Newton", "Galileo Galilei", "Stephen Hawking")),
                "3"
        ));
        questionList.add(new Question(
                "What is the largest organ in the human body?",
                new ArrayList<>(List.of("Skin", "Liver", "Heart", "Brain")),
                "2"
        ));
        questionList.add(new Question(
                "What is the chemical symbol for water?",
                new ArrayList<>(List.of("H2O", "CO2", "O2", "NaCl")),
                "0"
        ));
        questionList.add(new Question(
                "Which gas makes up the majority of Earth's atmosphere?",
                new ArrayList<>(List.of("Nitrogen", "Oxygen", "Carbon dioxide", "Argon")),
                "1"
        ));
        questionList.add(new Question(
                "What is the primary function of the mitochondria?",
                new ArrayList<>(List.of("Produce energy", "Store genetic information", "Control cell division", "Synthesize proteins")),
                "0"
        ));
        questionList.add(new Question(
                "What is the chemical formula for table salt?",
                new ArrayList<>(List.of("NaCl", "H2O", "CO2", "KCl")),
                "0"
        ));

// Indian Politics Questions
        questionList.add(new Question(
                "Who is the current Prime Minister of India?",
                new ArrayList<>(List.of("Narendra Modi", "Rahul Gandhi", "Amit Shah", "Manmohan Singh")),
                "1"
        ));
        questionList.add(new Question(
                "Which political party is currently in power in India?",
                new ArrayList<>(List.of("BJP", "Congress", "AAP", "SP")),
                "3"
        ));
        questionList.add(new Question(
                "Who was the first Prime Minister of India?",
                new ArrayList<>(List.of("Jawaharlal Nehru", "Indira Gandhi", "Rajiv Gandhi", "Lal Bahadur Shastri")),
                "0"
        ));
        questionList.add(new Question(
                "When did India gain independence from British rule?",
                new ArrayList<>(List.of("1947", "1950", "1957", "1962")),
                "2"
        ));
        questionList.add(new Question(
                "Which political party was in power during the Emergency period in India?",
                new ArrayList<>(List.of("Congress", "BJP", "CPI", "Janata Party")),
                "3"
        ));
    }
}