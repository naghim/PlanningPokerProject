package com.example.planningpokerproject.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.PropertyName;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Question {

    @PropertyName("is_active")
    public int is_active;

    @PropertyName("question_txt")
    public String question_txt;

    @PropertyName("user_resp")
    public HashMap<String, User> user_resp;

    public Question(){}

    public Question( HashMap<String, User>  user_resp, int is_active, String question_txt){
        this.user_resp = user_resp;
        this.is_active = is_active;
        this.question_txt = question_txt;
    }

    public HashMap<String,User> getUser_resp()
    {
        return this.user_resp;
    }

    public int getIsactive()
    {
        return this.is_active;
    }

    public String getQuestionText()
    {
        return this.question_txt;
    }

    public void setActive(int active) {

        is_active = active;
    }

    public void setQuestionText(String questionText) {

        this.question_txt = questionText;
    }

    public void setuser_resp( HashMap<String, User>  user_resp) {

        this.user_resp = user_resp;
    }
}
