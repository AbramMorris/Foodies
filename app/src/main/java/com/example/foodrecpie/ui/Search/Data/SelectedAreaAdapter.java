package com.example.foodrecpie.ui.Search.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.Network.NetworkConnection;
import com.example.foodrecpie.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class SelectedAreaAdapter extends RecyclerView.Adapter<SelectedAreaAdapter.AreaHolder>{

    ArrayList<Meal> myMeals;
    Context context ;

    SelectedAreaOnClickListner listner ;

    FirebaseAuth firebaseAuth;

    FirebaseUser user ;
    private Meal meal;


    public SelectedAreaAdapter(Context context , Meal myMeals, SelectedAreaOnClickListner listner) {
        this.context = context;
        this.meal = myMeals;
//        myMeals= new ArrayList<>();
        this.listner=listner;
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
    }


    public void setList(ArrayList<Meal> updateMales )
    {this.myMeals = updateMales;}
    @NonNull
    @Override
    public AreaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.selected_area_view , parent , false);
        AreaHolder vh = new AreaHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AreaHolder holder, int position) {
        String [] days ={"Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};
        Meal meal = myMeals.get(position);
        holder.title.setText(meal.getStrArea());
        Glide.with(context).load(meal.getImgUrl()).into(holder.thumbnails);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,days);
        holder.autoCompleteTextView.setAdapter(adapter);
        holder.autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null){
                holder.autoCompleteTextView.showDropDown();
                } else  {
                    Toast.makeText(context, "You need to login to be able to save meals to your week calendar plan", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        holder.autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String day = parent.getItemAtPosition(position).toString();
//                switch (day) {
//                    case "Saturday":
//                        meal.setDay("1");
////                        listner.onAddToFavorite(meal);
//                        FirebaseDataBase.addPlanToFirebase(context,meal);
//                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
//                        break;
//                    case "Sunday":
//                        meal.setDay("2");
////                        listner.onAddToFavorite(meal);
//                        FirebaseDataBase.addPlanToFirebase(context,meal);
//                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
//                        break;
//                    case "Monday":
//                        meal.setDay("3");
////                        listner.onAddToFavorite(meal);
//                        FirebaseDataBase.addPlanToFirebase(context,meal);
//                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
//                        break;
//                    case "Tuesday":
//                        meal.setDay("4");
////                        listner.onAddToFavorite(meal);
//                        FirebaseDataBase.addPlanToFirebase(context,meal);
//                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case "Wednesday":
//                        meal.setDay("5");
////                        listner.onAddToFavorite(meal);
//                        FirebaseDataBase.addPlanToFirebase(context,meal);
//                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case "Thursday":
//                        meal.setDay("6");
////                        listner.onAddToFavorite(meal);
//                        FirebaseDataBase.addPlanToFirebase(context,meal);
//                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
//                        break;
//                    case "Friday":
//                        meal.setDay("7");
////                        listner.onAddToFavorite(meal);
//                        FirebaseDataBase.addPlanToFirebase(context,meal);
//                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
//                        break;
//
//
//                }
//            }
//        });

        holder.addToFavourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Boolean clicked = false ;
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(user != null){
                if (!clicked){
                    // holder.addToFavourite.setChecked(false);
                    clicked = true;
//                    holder.addToFavourite.setBackgroundResource(R.drawable.baseline_favorite_24);
//                    Toast.makeText(context, "Meal added to your favourite list", Toast.LENGTH_SHORT).show();
//                    meal.setDay("0");
////                    listner.onAddToFavorite(meal);
//                    FirebaseDataBase.addFavouriteToFirebase(context,meal);



                }
                } else {
                    Toast.makeText(context, "You need to login to be able to save meals to your favourit list ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetworkConnection.getConnectivity(context)) {
                    listner.ShowMealDetails(meal);
                }else{
                    Toast.makeText(context, "There is no internet connection " + "\n" +"Please reconnect and try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return myMeals.size();
    }

    public class AreaHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView title ;

        ImageView thumbnails;

        ToggleButton addToFavourite ;

        AutoCompleteTextView autoCompleteTextView;


        public AreaHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.cons_lay_Selected_area);
            title = itemView.findViewById(R.id.tv_calender_meal_name);
            thumbnails = itemView.findViewById(R.id.img_calender_meal);
            addToFavourite= itemView.findViewById(R.id.btn_addToFav);
            autoCompleteTextView=itemView.findViewById(R.id.days_drop_dawn);


        }


    }
}
