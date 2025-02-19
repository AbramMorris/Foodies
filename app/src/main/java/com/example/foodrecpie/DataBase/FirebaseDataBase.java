package com.example.foodrecpie.DataBase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodrecpie.CountryArea.Model.Meal;
import com.example.foodrecpie.Network.DailyInspireRemoteDataSorce;
import com.example.foodrecpie.Network.Repo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseDataBase {
    public  static void  addFavouriteToFirebase(Context context, Meal myMeal) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()==null){
            Toast.makeText(context, "you\re not logged in", Toast.LENGTH_SHORT).show();
        }
        else {

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food Planner's Users");
            ref.child(firebaseAuth.getUid()).child("Favorites").child(myMeal.getStrMeal()).setValue(myMeal)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                          //  Toast.makeText(context, "added to firebase", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public static void addPlanToFirebase(Context context, Meal myMeal) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()==null){
            Toast.makeText(context, "you\re not logged in", Toast.LENGTH_SHORT).show();
        }
        else {

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food Planner's Users");
            ref.child(firebaseAuth.getUid()).child("Plan").child(myMeal.getDay()).child(myMeal.getStrMeal()).setValue(myMeal)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                           // Toast.makeText(context, "added to firebase", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


        }
    }

    public static void getFavouriteFromFirebase(Context context, FirebaseUser user) {

        DatabaseReference rootFav = FirebaseDatabase.getInstance().getReference().child("Food Planner's Users").child(user.getUid()).child("Favorites");
        rootFav.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren() ){
                    Meal meal = dataSnapshot.getValue(Meal.class);
//                   Repo repo =  Repo.getInstance(DailyInspireRemoteDataSorce.getInstance(), DataBaseRepository.getInstance(context),context);
//                   repo.insert(meal);
                Log.i("finaaaaaaaal",meal.getStrMeal()+""+meal.getIdMeal());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("test",error.getMessage());
            }
        });
    }


    public static void getPlanFromFireBase(Context context, FirebaseUser user , String day ) {
        DatabaseReference rootPlan = FirebaseDatabase.getInstance().getReference().child("Food Planner's Users").child(user.getUid()).child("Plan").child(day);
        rootPlan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren() ) {
                    Meal meal = dataSnapshot.getValue(Meal.class);
//                    Repo repo =  Repo.getInstance(DailyInspireRemoteDataSorce.getInstance(), DataBaseRepository.getInstance(context),context);
//                   repo.insert(meal);
                    Log.i("finaaaaaaaal",meal.getStrMeal()+""+meal.getIdMeal());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("test",error.getMessage());
            }
        });

    }


    public static void removeFavouriteFromFirebase(Context context, Meal myMeal) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()==null){
            Toast.makeText(context, "you\re not logged in", Toast.LENGTH_SHORT).show();
        }
        else {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food Planner's Users");
            ref.child(firebaseAuth.getUid()).child("Favorites").child(myMeal.getStrMeal()).removeValue()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                         //   Toast.makeText(context, "removed from your favList", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "failed to remove from your favList", Toast.LENGTH_SHORT).show();

                        }
                    });

        }
    }

    public static void removePlanFromFireBase(Context context, Meal myMeal) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()==null){
            Toast.makeText(context, "you\re not logged in", Toast.LENGTH_SHORT).show();
        }
        else {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food Planner's Users");
            ref.child(firebaseAuth.getUid()).child("Plan").child(myMeal.getDay()).child(myMeal.getStrMeal()).removeValue()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                         //   Toast.makeText(context, "removed firebase", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    }


}
