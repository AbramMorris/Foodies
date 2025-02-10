package com.example.foodrecpie.ui.sign_in;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.foodrecpie.R;
import com.example.foodrecpie.databinding.FragmentSignInBinding;

public class Sign_inFragment extends Fragment {
    TextView signup;
    Button btn_signin ;
    Button btn_google;

    private FragmentSignInBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Sign_inViewModel signinViewModel =
                new ViewModelProvider(this).get(Sign_inViewModel.class);

        binding = FragmentSignInBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signup = view.findViewById(R.id.textView);
        btn_signin = view.findViewById(R.id.button);
        btn_google = view.findViewById(R.id.button2);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_navigation_notifications_to_signUp);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}