package com.example.aliy.realtimedb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Signup_fragment extends Fragment {


    private Button btn_signup_submit;


    public Signup_fragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        btn_signup_submit = (Button) view.findViewById(R.id.btn_sign_up_submit);

        btn_signup_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Submitted", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
