package com.example.letseat;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {


    private EditText emailPerson, passwordPerson,passwordConf;
    private Button regBtn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        passwordConf = (EditText)findViewById(R.id.registerConfrmPassword);
        emailPerson = (EditText) findViewById(R.id.registerEmail);
        passwordPerson = (EditText) findViewById(R.id.registerPassword);
        regBtn=(Button)findViewById(R.id.btnRegisterRegister);
        progressBar = (ProgressBar)findViewById(R.id.progressbarregister);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                String email, password, passwordConfirmation;
                email = emailPerson.getText().toString();
                password = passwordPerson.getText().toString();
                passwordConfirmation =passwordConf.getText().toString();
                if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(passwordConfirmation))
                {

                }

                if(password.equals(passwordConfirmation))
                {
                    //smile please


                }
                else {
                    Toast.makeText(getApplicationContext(), "Password mismatch", Toast.LENGTH_LONG).show();
                    passwordPerson.setText("");
                    passwordConf.setText("");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.GONE);

                                    Intent intent = new Intent(Register.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });

            }
        });

    }
}

