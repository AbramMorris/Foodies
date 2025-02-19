package com.example.foodrecpie.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.Details.MealDetailAdapter;
import com.example.foodrecpie.Details.MealIngredients;
import com.example.foodrecpie.Home.MealDetailsPresenter;
import com.example.foodrecpie.Home.MealDetailsViewInterface;
import com.example.foodrecpie.Model.MealDetailResponse;
import com.example.foodrecpie.Presenter.MealDetailPresenter;
import com.example.foodrecpie.Network.Repo;
import com.example.foodrecpie.databinding.FragmentMealDetailsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Meal_Details extends Fragment implements MealDetailsViewInterface {
    private FragmentMealDetailsBinding binding;
    private MealDetailsPresenter presenter;
    String VideoUrl;
    private  int stepNo = 0;
    ArrayList<MealIngredients> resultToShow ;
    MealDetailAdapter adapter ;
    ToggleButton addToFavourite ;
    FirebaseAuth firebaseAuth;
    private Meal selectedSearchMeal;
    FirebaseUser user ;
    MealDetailPresenter present;


    public Meal_Details() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MealDetailsPresenter(this, Repo.getInstance());
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
//        binding.btnAddMealDetailToFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            Boolean clicked = false ;
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(user!= null){
//                    if (!clicked){
//                        // holder.addToFavourite.setChecked(false);
//                        clicked = true;
//                        addToFavourite.setBackgroundResource(R.drawable.baseline_favorite_24);
//                        Toast.makeText(getContext(), "meal added to your favourite list", Toast.LENGTH_SHORT).show();
//                        selectedSearchMeal.setDay("0");
//                        //  listner.onAddToFavorite(response);
//                        present.addToFavorite(selectedSearchMeal);
////                        FirebaseDataBase.addFavouriteToFirebase(getContext(),selectedSearchMeal);
//
//                    }
//                } else {
//                    Toast.makeText(getContext(), "You need to login to be able to save meals to your favourit list", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//        binding.btnAddMealDetailToFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//            }
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_meal__details, container, false);
        binding = FragmentMealDetailsBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String mealid = Meal_DetailsArgs.fromBundle(getArguments()).getMealId();
        Bundle args = getArguments();
        if (args != null && args.containsKey("mealId")) {
            String strMeal = args.getString("mealId");
            presenter.getMealDetails(strMeal);
            Log.i("Abraaaaaam", "onViewCreated: "+strMeal);
        }
    }

    @Override
    public void showMealDetails(MealDetailResponse.MealsDTO meal) {
        binding.mealname.setText(meal.getStrMeal());
        binding.mealContry.setText(meal.getStrArea());
        binding.mealCategory.setText(meal.getStrCategory());
        binding.textView3.setText(meal.getStrInstructions());
        resultToShow = prepareIngredient(meal);
        adapter = new MealDetailAdapter( getContext());
        adapter.setList(resultToShow);
        adapter.notifyDataSetChanged();
        StringTokenizer st = new StringTokenizer(meal.getStrInstructions(), ".");
        while (st.hasMoreTokens()){
            binding.steps.append("Step "+String.valueOf(stepNo+1)+"\n"+st.nextToken()+"\n");
            stepNo++;

        }

        Glide.with(this).load(meal.getStrMealThumb()).into(binding.imgDetailsMeal);

        if(meal.getIdMeal().equalsIgnoreCase("52953")) {
            VideoUrl = "https://www.youtube.com/watch?v=omnQWLBe6tg";
        } else {
            VideoUrl = meal.getStrYoutube();
        }
        binding.ybv.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                if(VideoUrl !=null && !VideoUrl.isEmpty() && !VideoUrl.equalsIgnoreCase("")){
                    VideoUrl = VideoUrl.substring(VideoUrl.indexOf("=") + 1);
                    StringTokenizer st = new StringTokenizer(VideoUrl, "&");
                    VideoUrl = st.nextToken();
                    youTubePlayer.loadVideo(VideoUrl, 0);
                    youTubePlayer.pause();
                }
            }
        });
    }

    public ArrayList<MealIngredients> prepareIngredient (MealDetailResponse.MealsDTO meal){
        ArrayList<MealIngredients> ingredientsList = new ArrayList<>();
        if (meal.getStrIngredient1()!=null&&  !meal.getStrIngredient1().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient1(),meal.getStrMeasure1()));
        if (meal.getStrIngredient2()!=null &&  !meal.getStrIngredient2().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient2(), meal.getStrMeasure2()));
        if (meal.getStrIngredient3()!=null &&  !meal.getStrIngredient3().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient3(), meal.getStrMeasure3()));
        if (meal.getStrIngredient4()!=null &&  !meal.getStrIngredient4().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient4(), meal.getStrMeasure4()));
        if (meal.getStrIngredient5()!=null &&  !meal.getStrIngredient5().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient5(), meal.getStrMeasure5()));
        if (meal.getStrIngredient6()!=null &&  !meal.getStrIngredient6().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient6(), meal.getStrMeasure6()));
        if (meal.getStrIngredient7()!=null &&  !meal.getStrIngredient7().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient7(), meal.getStrMeasure7()));
        if (meal.getStrIngredient8()!=null &&  !meal.getStrIngredient8().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient8(), meal.getStrMeasure8()));
        if (meal.getStrIngredient9()!=null &&  !meal.getStrIngredient9().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient9(), meal.getStrMeasure9()));
        if (meal.getStrIngredient10()!=null &&  !meal.getStrIngredient10().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient10(), meal.getStrMeasure10()));
        if (meal.getStrIngredient11()!=null &&  !meal.getStrIngredient11().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient11(), meal.getStrMeasure11()));
        if (meal.getStrIngredient12()!=null &&  !meal.getStrIngredient12().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient12(), meal.getStrMeasure12()));
        if (meal.getStrIngredient13()!=null &&  !meal.getStrIngredient13().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient13(), meal.getStrMeasure13()));
        if (meal.getStrIngredient14()!=null &&  !meal.getStrIngredient14().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient14(), meal.getStrMeasure14()));
        if (meal.getStrIngredient15()!=null &&  !meal.getStrIngredient15().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient15(), meal.getStrMeasure15()));
        if (meal.getStrIngredient16()!=null &&  !meal.getStrIngredient16().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient16(), meal.getStrMeasure16()));
        if (meal.getStrIngredient17()!=null &&  !meal.getStrIngredient17().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient17(), meal.getStrMeasure17()));
        if (meal.getStrIngredient18()!=null &&  !meal.getStrIngredient18().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient18(), meal.getStrMeasure18()));
        if (meal.getStrIngredient19()!=null &&  !meal.getStrIngredient19().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient19(), meal.getStrMeasure19()));
        if (meal.getStrIngredient20()!=null && !meal.getStrIngredient20().isEmpty()){
            ingredientsList.add(new MealIngredients(meal.getStrIngredient20(), meal.getStrMeasure20()));
        }
        return  ingredientsList;
    }
    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
