package com.example.planningpokerproject;

import androidx.annotation.NonNull;

import com.example.planningpokerproject.Model.Group;
import com.example.planningpokerproject.Model.Question;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private List<Question> questions;

    public interface DataStatus{
        void DataIsLoaded(List<Question> items, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
    }

    // Constructor
    public FirebaseDatabaseHelper(){
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("groups");
        questions = new ArrayList<>();
    }

}
