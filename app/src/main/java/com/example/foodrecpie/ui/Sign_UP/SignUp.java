package com.example.foodrecpie.ui.Sign_UP;

import android.content.Context;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.foodrecpie.Network.NetworkConnection;
import com.example.foodrecpie.R;
import com.example.foodrecpie.databinding.FragmentSignUpBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends Fragment implements SignUpViewInterface{

    private FragmentSignUpBinding binding;
    FirebaseAuth mAuth;
    SingUpPresenter presenter;
    private NavController navController;

    public SignUp() {
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        presenter = new SingUpPresenter(this);
        boolean isConnected = NetworkConnection.getConnectivity(requireContext());
        if (isConnected) {
            Toast.makeText(this.requireContext(), "Network is connected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.requireContext(), "Network is not connected", Toast.LENGTH_SHORT).show();
        }
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(NetworkConnection.getConnectivity(requireContext())) {

                    String email = binding.email.getText().toString().trim();
                    String password = binding.nameSignupPassword.getText().toString().trim();

                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        binding.email.setError("Invalid Email");
                        binding.email.setFocusable(true);
                    } else if (password.length() < 8) {
                        binding.nameSignupPassword.setError("Invalid Password");
                        binding.nameSignupPassword.setFocusable(true);
                    }else {
                        // calling method
                        presenter.signUp(email, password);
                    }
                }  else {
                    Toast.makeText(getContext(), "There is no internet connection " + "\n" +"Please reconnect and try again", Toast.LENGTH_SHORT).show();
                }
            }});
    }


    @Override
    public void showSignUpSuccess() {
        Toast.makeText(getContext(), "Sign Up Successful", Toast.LENGTH_SHORT).show();
        navController.navigate(R.id.action_signUp_to_navigation_home);
    }

    @Override
    public void showSignUpFailure(String errorMessage) {
        Toast.makeText(getContext(), "Sign Up Failed: " + errorMessage, Toast.LENGTH_SHORT).show();
    }
}