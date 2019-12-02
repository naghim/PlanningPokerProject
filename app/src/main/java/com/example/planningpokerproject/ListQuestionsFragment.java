package com.example.planningpokerproject;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokerproject.Model.Group;
import com.example.planningpokerproject.Model.Question;
import com.example.planningpokerproject.interfaces.ItemLongClickListener;
import com.example.planningpokerproject.interfaces.OnItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListQuestionsFragment extends Fragment {

    private FloatingActionButton mFab ;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<RecyclerItem> recycleHobbyItemsList;
    private Context mContext;
    private DatabaseReference databaseReference; // reference to the db
    private Globals globals = Globals.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.list_questions_fragment, parent, false);

        this.mFab = view.findViewById(R.id.fab);
        this.mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).onButtonClickAddQuestion(view);
            }
        });

        this.createRecycleList();
        this.mContext = getContext();
        this.buildRecycleView(view);

        return view;
    }

    // Create RecyclerView.
    public void createRecycleList(){
        recycleHobbyItemsList = new ArrayList<>();
        this.getAllData();
    }

    // Query all questions from a group.
    public void getAllData(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("groups").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                recycleHobbyItemsList.clear();
                for (DataSnapshot iter : dataSnapshot.getChildren()) {
                   if (iter.getKey().equals(globals.getGroupName())){
                        Group group = iter.getValue(Group.class);
                        if (group.getQuestionList() != null){
                            for (Question question : group.getQuestionList().values()) {
                                if(question != null){
                                    RecyclerItem recyclerItem = new RecyclerItem(
                                            question.getQuestionText(),
                                            group.getStart_time(),
                                            group.getEnd_time(),
                                            question.getIsactive() == 1 ? true : false);
                                    recycleHobbyItemsList.add(recyclerItem);
                                }
                            }
                        }
                    }
                }

                mAdapter.notifyDataSetChanged(); // if data has been changed, rebuilds the list...
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // if query doesn't succeed...
                throw databaseError.toException();
            }
        });
    }

    // Build RecyclerView.
    public void buildRecycleView(View view){
        mRecyclerView = view.findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this.mContext);
        mAdapter = new RecyclerAdapter(recycleHobbyItemsList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view , int position) {
                globals.setQuestionText(recycleHobbyItemsList.listIterator(position).next().getTask_description());
                ((MainActivity)getActivity()).onButtonClickShowQuestion(view,position);
            }
        });

        mAdapter.setItemLongClickListener(new ItemLongClickListener() {
            @Override
            public void onItemLongClick(View v, final int pos) {
                databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("groups")
                        .child(globals.getGroupName())
                        .child("questions")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        RecyclerItem cHobby = recycleHobbyItemsList.get(pos);
                        for (DataSnapshot iter : dataSnapshot.getChildren()) {/**/
                            Question question = iter.getValue(Question.class);
                            if (question.is_active == 1 && question.getQuestionText() != cHobby.getTask_description()){
                                databaseReference.child("groups")
                                        .child(globals.getGroupName())
                                        .child("questions")
                                        .child(iter.getKey()).child("is_active").setValue(0);
                            }
                            if (question.is_active == 0 && question.getQuestionText().equals(cHobby.getTask_description())){
                                databaseReference.child("groups")
                                        .child(globals.getGroupName())
                                        .child("questions")
                                        .child(iter.getKey()).child("is_active").setValue(1);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // if query doesn't succeed...
                        throw databaseError.toException();
                    }
                });
        }
        });
    }
}
