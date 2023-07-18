package com.example.kahot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;



public class RegisterActivity extends AppCompatActivity {

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextID;
    private EditText editTextDateOfBirth;
    private EditText editTextCity;
    private EditText editTextStreet;
    private EditText editTextHouse;
    private EditText editTextPhoneNumber;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonRegister;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize views
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextID = findViewById(R.id.editTextID);
        editTextDateOfBirth = findViewById(R.id.editTextDateOfBirth);
        editTextCity = findViewById(R.id.editTextCity);
        editTextStreet = findViewById(R.id.editTextStreet);
        editTextHouse = findViewById(R.id.editTextHouse);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Register button click listener
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user input
                String firstName = editTextFirstName.getText().toString().trim();
                String lastName = editTextLastName.getText().toString().trim();
                String idNumber = editTextID.getText().toString().trim();
                String dateOfBirth = editTextDateOfBirth.getText().toString().trim();
                String city = editTextCity.getText().toString().trim();
                String street = editTextStreet.getText().toString().trim();
                String house = editTextHouse.getText().toString().trim();
                String phoneNumber = editTextPhoneNumber.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Validate user input (optional)

                // Register the user with Firebase Authentication
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Registration successful
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    if (user != null) {
                                        // Save additional user data to Firestore or Realtime Database
                                        saveUserData(user.getUid(), firstName, lastName, idNumber, dateOfBirth, city, street, house, phoneNumber);
                                    }
                                    Toast.makeText(RegisterActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                    // Proceed to login or main activity
                                    // startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    // finish();
                                } else {
                                    // Registration failed
                                    String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                                    // Handle registration failure based on the error code
                                    Toast.makeText(RegisterActivity.this, "Registration Failed: " + errorCode, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void saveUserData(String userId, String firstName, String lastName, String idNumber, String dateOfBirth, String city, String street, String house, String phoneNumber) {
        // TODO: Implement saving user data to Firestore or Realtime Database
        // You can use Firebase Firestore or Realtime Database to save the user data
    }
}
