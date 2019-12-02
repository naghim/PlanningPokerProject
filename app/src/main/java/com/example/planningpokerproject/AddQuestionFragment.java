package com.example.planningpokerproject;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class AddQuestionFragment extends Fragment {

    private EditText mQuestionDescriptionEditText;
    private Button mStartDateButton, mEndDateButton, mInsertButton;
    private CheckBox mIsActiveCheckBox;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    private Globals globals = Globals.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        final View view = inflater.inflate(R.layout.add_question_fragment, parent, false);

        this.mQuestionDescriptionEditText = view.findViewById(R.id.questionDescriptionEditText);
        this.mStartDateButton = view.findViewById(R.id.startDateButton);
        this.mEndDateButton = view.findViewById(R.id.endDateButton);
        this.mInsertButton = view.findViewById(R.id.insertButton);
        this.mIsActiveCheckBox = view.findViewById(R.id.isActiveCheckBox);

        // Datepicker for date
        mStartDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mStartDateButton.setText(year + ". "  + (month +1) + ". " + dayOfMonth);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        // Datepicker for date
        mEndDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mEndDateButton.setText(year + ". "  + (month +1) + ". " + dayOfMonth);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        // Listener for insert question button
        mInsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!mStartDateButton.getText().toString().equals("Pick start date") &&
                   !mEndDateButton.getText().toString().equals("Pick start date")){
                    DatabaseReference addGroupAndTimeDatabaseReference = FirebaseDatabase.getInstance()
                            .getReference("groups").child(globals.getGroupName());
                    addGroupAndTimeDatabaseReference.child("start_time").setValue(mStartDateButton.getText().toString());
                    addGroupAndTimeDatabaseReference.child("end_time").setValue(mEndDateButton.getText().toString());
                }

                String cQuestionID = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
                String cUser = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();

                DatabaseReference addQuestionDatabaseReference = FirebaseDatabase.getInstance()
                        .getReference("groups").child(globals.getGroupName()).child("questions").child(cQuestionID);
                addQuestionDatabaseReference.child("question_txt").setValue(mQuestionDescriptionEditText.getText().toString());
                addQuestionDatabaseReference.child("is_active").setValue(mIsActiveCheckBox.isChecked() == true ? 1 : 0);

                Toast.makeText(getContext(), "New question created",
                        Toast.LENGTH_SHORT).show();

                ((MainActivity)getActivity()).onButtonClick(view);
            }
        });
        return view;
    }
}