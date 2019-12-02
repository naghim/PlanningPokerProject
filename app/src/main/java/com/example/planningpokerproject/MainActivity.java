package com.example.planningpokerproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_place, new MainFragment());
        ft.commit();
    }

    // On login button click: after the user signed in, shows the questions for the group. Initially it will be an empty list.
    public void onButtonClick(View view) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_place, new ListQuestionsFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    // On back pressed.
    public void onButtonBackClick(View view){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.replace( R.id.fragment_place, new ListQuestionsFragment() ).addToBackStack( "tag" ).commit();
    }

    // On add question button click: Shows add question fragment.
    public void onButtonClickAddQuestion(View view) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_place, new AddQuestionFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    // Shows votes for a particular question.
    public void onButtonClickShowQuestion(View view,int pos) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_place, new ShowQuestionFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
}
