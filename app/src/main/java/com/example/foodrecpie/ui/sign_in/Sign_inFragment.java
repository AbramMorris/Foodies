package com.example.foodrecpie.ui.sign_in;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.foodrecpie.MainActivity;
import com.example.foodrecpie.R;
import com.example.foodrecpie.databinding.FragmentSignInBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Sign_inFragment extends Fragment {
    private TextView signup;
    private AppCompatButton btn_signin, btn_google, btn_guest;
    private FirebaseAuth mAuth;
    private GoogleSignInClient googleSignInClient;
    private ProgressDialog pd;
    private NavController navController;
    private FragmentSignInBinding binding;
    private static final int GOOGLE_SIGN_IN_REQUEST_CODE = 100;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity activity = (MainActivity) requireActivity();
        activity.hideBottomNav();

        // Initialize UI Components
        signup = view.findViewById(R.id.textView);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        btn_signin = view.findViewById(R.id.button);
        btn_google = view.findViewById(R.id.button2);
        btn_guest = view.findViewById(R.id.guest);
        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(requireContext());

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            navController.navigate(R.id.action_navigation_notifications_to_navigation_home2);
        }

        // Google Sign-In Setup
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso);

        // Sign-Up Navigation
        signup.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_navigation_notifications_to_signUp));

        // Login Button Click
        btn_signin.setOnClickListener(v -> loginUser(
                binding.editTextText.getText().toString(),
                binding.editTextTextPassword.getText().toString()
        ));

        // Guest Login Click
        btn_guest.setOnClickListener(v -> {
            signInGuest();
            navController.navigate(R.id.action_navigation_notifications_to_navigation_home2);
        });

        // Google Sign-In Click
        btn_google.setOnClickListener(v -> googleLogin());
    }

    private void signInGuest() {
        mAuth.signInAnonymously()
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(requireContext(), "Guest signed in", Toast.LENGTH_SHORT).show();
                        Log.d("GuestLogin", "Guest signed in: " + (user != null ? user.getUid() : "null"));
                    } else {
                        Toast.makeText(requireContext(), "Guest sign-in failed", Toast.LENGTH_SHORT).show();
                        Log.w("GuestLogin", "Guest sign-in failed", task.getException());
                    }
                });
    }

    private void googleLogin() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN_IN_REQUEST_CODE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    signInByGoogle(account.getIdToken());
                }
            } catch (ApiException e) {
                Toast.makeText(requireContext(), "Google Sign-In failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void signInByGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnSuccessListener(authResult -> {
                    Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    navController.navigate(R.id.action_navigation_notifications_to_navigation_home2);
                })
                .addOnFailureListener(e -> Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void loginUser(String email, String userPassword) {
        if (email.isEmpty() || userPassword.isEmpty()) {
            Toast.makeText(getContext(), "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, userPassword)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        navController.navigate(R.id.action_navigation_notifications_to_navigation_home2);
                        Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
