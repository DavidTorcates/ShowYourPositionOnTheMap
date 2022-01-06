package com.david.uberclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    Button sign;
    Button login;
    TextInputEditText mTextInputUser;
    TextInputEditText mTextInputPassword;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.btnLogin);
        sign= findViewById(R.id.btnSignInFromLogin);
        mTextInputUser= findViewById(R.id.textInputUser);
        mTextInputPassword = findViewById(R.id.textInputPassword);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSelectAuth();
            }
        });
    }
    private void Login(){
        String user = mTextInputUser.getText().toString();
        String password = mTextInputPassword.getText().toString();

        if(!user.isEmpty() && !password.isEmpty()){
            if(password.length() >= 6 ){
                mAuth.signInWithEmailAndPassword(user,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            goToMap();
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"username or password are incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }else{
                Toast.makeText(LoginActivity.this,"the password must have more than 6 characters", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(LoginActivity.this,"username or password are obligatory", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToMap(){
        Intent intent = new Intent(LoginActivity.this, MapActivity.class);
        startActivity(intent);
    }

    private void goToSelectAuth(){
        Intent intent = new Intent(LoginActivity.this, SigIn.class);
        startActivity(intent);
    }
}