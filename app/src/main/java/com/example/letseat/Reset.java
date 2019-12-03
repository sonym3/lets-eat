package com.example.letseat;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Reset extends AppCompatActivity {
    Button btnReset;
    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        btnReset=(Button)findViewById(R.id.btnReset);
        email=(EditText)findViewById(R.id.email);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String xyz=email.getText().toString();
                FirebaseAuth.getInstance().sendPasswordResetEmail(xyz)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Reset link sent to your email id!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Reset.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });
            }
        });
    }
}
