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

    public void onButtonClick(View view) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_place, new ListQuestionsFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    public void onButtonClickAddQuestion(View view) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_place, new AddQuestionFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    public void onButtonClickShowQuestion(View view,int pos) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_place, new ShowQuestionFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
}
