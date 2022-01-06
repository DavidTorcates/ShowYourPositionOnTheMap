package com.david.uberclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.david.uberclone.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SigIn extends AppCompatActivity {
   Toolbar mToolbar;
   FirebaseAuth mAuth;
   DatabaseReference mDatabase;

   //Views
    Button mSingIn;
    TextInputEditText mTextUser;
    TextInputEditText mTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sig_in);

        mToolbar=findViewById(R.id.toolbar);
        mSingIn = findViewById(R.id.btnSing);
        mTextUser = findViewById(R.id.txtInputUSer);
        mTextPassword = findViewById(R.id.textInputPassword);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Sing In");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    void registerUser(){
        String user = mTextUser.getText().toString();
        String password = mTextPassword.getText().toString();

        if (!user.isEmpty() && !password.isEmpty()){
            if (password.length() >= 6){
                mAuth.createUserWithEmailAndPassword(user,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        String id = mAuth.getCurrentUser().getUid();
                        saveUser(id,user,password);
                    }    else{
                        Toast.makeText(SigIn.this,"A problem has occurred", Toast.LENGTH_SHORT).show();
                    }
                    }
                });
            }else{
                Toast.makeText(SigIn.this,"the password must have more than 6 characters", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(SigIn.this,"username or password are obligatory", Toast.LENGTH_SHORT).show();
        }
    }

    void saveUser(String id,String user, String password){
        Users newUser = new Users();
        newUser.setId(id);
        newUser.setMail(user);
        newUser.setPassword(password);

        mDatabase.child("Users").child(id).setValue(newUser).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Toast.makeText(SigIn.this,"you have successfully registered", Toast.LENGTH_SHORT).show();
                goToLogin();
            }    else{
                Toast.makeText(SigIn.this,"A problem has occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void goToLogin(){
        Intent intent = new Intent(SigIn.this, LoginActivity.class);
        startActivity(intent);
    }

}