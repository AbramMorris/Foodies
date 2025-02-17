package com.example.foodrecpie.ui.Sign_UP;

import static java.security.AccessController.getContext;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodrecpie.Model.MealsPOJO;
import com.example.foodrecpie.Network.NetworkCallBack;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SingUpPresenter {

    FirebaseAuth mAuth;
    SignUpViewInterface view;

    public SingUpPresenter(SignUpViewInterface view) {
        this.view = view;
        mAuth = FirebaseAuth.getInstance();
    }

    public void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    if (view != null) {
                        view.showSignUpSuccess();
                    }
                })
                .addOnFailureListener(e -> {
                    if (view != null) {
                        view.showSignUpFailure(e.getMessage());
                    }
                });
    }
//    @Override
//    public void signUp(String email, String password) {
//            mAuth.createUserWithEmailAndPassword(binding.email.getText().toString(),binding.nameSignupPassword.getText().toString())
//                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                        @Override
//                        public void onSuccess(AuthResult authResult) {
//                            Toast.makeText(getContext(), "Sign Up Successful", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(getContext(), "Sign Up Failed", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//    }
}
