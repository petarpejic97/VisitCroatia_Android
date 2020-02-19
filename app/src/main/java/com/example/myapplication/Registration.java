package com.example.myapplication;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Registration extends AppCompatActivity {

    private EditText edName,edEmail,edPhoneNumber,edPassword,edConfPassword;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private FirebaseDatabase mFirebaseDatabase;
    private ProgressDialog progressDialog;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edName=findViewById(R.id.edName);
        edEmail=findViewById(R.id.edEmail);
        edPhoneNumber=findViewById(R.id.edPhoneNumber);
        edPassword=findViewById(R.id.edPassword);
        edConfPassword=findViewById(R.id.edConfPassword);

        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef= mFirebaseDatabase.getReference();
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(Registration.this,Login.class);
        finish();
        startActivity(intent);
    }

    public void registration(View view) {
        CheckFields();
    }

    private void CheckFields() {
        if (edName.getText().toString().isEmpty() || edEmail.getText().toString().isEmpty() || edPhoneNumber.getText().toString().isEmpty()
                || edPassword.getText().toString().isEmpty() || edConfPassword.getText().toString().isEmpty()) {
            Toast.makeText(this, "Some fields are empty!",Toast.LENGTH_LONG).show();
        }
        else if (edPassword.getText().toString().length() < 6) {
            Toast.makeText(this, "Your password is too short!",Toast.LENGTH_LONG).show();

        }
        else if (!edPassword.getText().toString().equals(edConfPassword.getText().toString())) {
            Toast.makeText(this, "Passwords are not matched!",Toast.LENGTH_LONG).show();
        }
        else{
            progressDialog.setMessage("Waiting...");
            progressDialog.show();
            createUser(edEmail.getText().toString(),edPassword.getText().toString());
        }

    }

    private void createUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Registration.this,"Uspjelo",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        saveToDatabase();
                    } else {
                        Toast.makeText(Registration.this,"Nije uspjelo",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                });
    }

    private void saveToDatabase() {
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        String  email=edEmail.getText().toString();
        String name=edName.getText().toString();
        String number=edPhoneNumber.getText().toString();

        User user2 = new User(name,number,email);
        myRef.child("users").child(userID).setValue(user2);

        resetFields();
    }

    private void resetFields() {
        edEmail.setText("");
        edConfPassword.setText("");
        edName.setText("");
        edPassword.setText("");
        edPhoneNumber.setText("");
    }
}
