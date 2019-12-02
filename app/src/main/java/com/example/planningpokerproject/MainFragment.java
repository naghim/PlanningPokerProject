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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.main_fragment, parent, false);
        mGroupNameEditText = view.findViewById(R.id.groupNameEditText);
        mLoginButton = view.findViewById(R.id.loginButton);

        // Listener for the login button
        mLoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                globals.setGroupName(mGroupNameEditText.getText().toString());
                ((MainActivity)getActivity()).onButtonClick(view);
            }});
        return view;
    }
}
