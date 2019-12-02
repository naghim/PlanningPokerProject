package com.example.planningpokerproject;

// Global variables
public class Globals {
    private static Globals instance;

    private String groupName;

    private String questionText;

    // Restricts the constructor from being instantiated.
    private Globals(){}

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }

    public void setGroupName(String data){
        this.groupName=data;
    }

    public String getGroupName(){
        return this.groupName;
    }

    public void setQuestionText(String text) {
        this.questionText = text;
    }

    public String getQuestionText(){
        return this.questionText;
    }
}
