package com.example.planningpokerproject;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokerproject.Model.Group;
import com.example.planningpokerproject.Model.Question;
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
    private DatabaseReference databaseReference;//ramutatunk ezzel egy cimre
    private Globals globals = Globals.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
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

    public void createRecycleList(){
        recycleHobbyItemsList = new ArrayList<>();
        this.getAllData();
    }

    public void getAllData(){
        databaseReference = FirebaseDatabase.getInstance().getReference();//ezzel ferunk hozza
        databaseReference.child("groups").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //ez csinal egy masolatot es itt toltom le az adatokat
                for (DataSnapshot iter : dataSnapshot.getChildren()) {/**/
                   if (iter.getKey().equals(globals.getGroupName())){
                        Group group = iter.getValue(Group.class);
                        for (Question question : group.getQuestionList()) {
                            if(question != null){
                                RecyclerItem recyclerItem = new RecyclerItem(
                                        question.getQuestionText(),
                                        group.getStart_time(),
                                        group.getEnd_time());
                                recycleHobbyItemsList.add(recyclerItem);
                            }
                        }
                    }
                }

                mAdapter.notifyDataSetChanged();//megnezi , hogy mi valtozott s beteszi ha valtozas tortent
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //ha nem sikerul az adat lekerdezes
                throw databaseError.toException();
            }
        });
    }

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
    }
}
