package com.example.planningpokerproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import android.widget.Button;
import android.widget.EditText;

public class MainFragment extends Fragment {

    private Button mLoginButton;
    private EditText mGroupNameEditText;
    private Globals globals = Globals.getInstance();
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        final View view = inflater.inflate(R.layout.main_fragment, parent, false);
        mGroupNameEditText = view.findViewById(R.id.groupNameEditText);
        mLoginButton = view.findViewById(R.id.loginButton);

        mLoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                globals.setGroupName(mGroupNameEditText.getText().toString());
                ((MainActivity)getActivity()).onButtonClick(view);
            }});
        return view;
    }


    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }
}
