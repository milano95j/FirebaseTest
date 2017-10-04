package com.example.aliy.realtimedb;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {





    private TextView login_txt_email;
    private TextView login_txt_password;
    private Button btn_login_submit;
    private Button btn_signup_redirect;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAutheStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_main);




        //ELSE if SDK_INT more than 16
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);




        //IMPLEMENTATION STARTS...
        login_txt_email = (TextView) findViewById(R.id.login_txt_email);
        login_txt_password = (TextView) findViewById(R.id.login_txt_password);
        btn_login_submit = (Button) findViewById(R.id.btn_login_submit);
        btn_signup_redirect = (Button) findViewById(R.id.btn_send_sign_up);





        btn_signup_redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment signup_fragment = new Signup_fragment();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.signup_container, signup_fragment, signup_fragment.getTag()).commit();



            }
        });


        //FIREBASE IMPLEMENTATION

        mAuth = FirebaseAuth.getInstance();
        mAutheStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(mAuth.getCurrentUser() != null){
                    Toast.makeText(getApplicationContext(), "User uid: " + mAuth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
                }



            }
        };





        btn_login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();

                String email = login_txt_email.getText().toString();
                String password = login_txt_password.getText().toString();

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "You are not registered to this system!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });













    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAutheStateListener);
    }
}
