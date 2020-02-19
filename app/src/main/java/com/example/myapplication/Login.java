package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private FirebaseDatabase mFirebaseDatabase;
    private ProgressDialog progressDialog;

    EditText edEmail;
    EditText edPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        btnLogin.setOnClickListener(this);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef= mFirebaseDatabase.getReference();

    }

    private void userLogin(){
        String email = edEmail.getText().toString().trim();
        String password = edPassword.getText().toString().trim();

        if (edEmail.getText().toString().isEmpty() || edPassword.getText().toString().isEmpty()) {
            Toast.makeText(Login.this, "Authentication filed.",
                    Toast.LENGTH_SHORT).show();
        }
        progressDialog.setMessage("Waiting...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, task -> {
                    progressDialog.dismiss();
                    if(task.isSuccessful()){
                        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("flag","true").apply();

                        Intent intent = new Intent(Login.this,CityList.class);
                        finish();
                        startActivity(intent);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v == btnLogin){
            userLogin();
        }
    }

    public void goToCityList(View view) {
        //myRef.child("login").setValue(false);
        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("flag","false").apply();
        Intent intent = new Intent(Login.this,CityList.class);
        finish();
        startActivity(intent);
    }

    public void goToRegistration(View view) {
        Intent intent = new Intent(Login.this,Registration.class);
        startActivity(intent);
    }
}
