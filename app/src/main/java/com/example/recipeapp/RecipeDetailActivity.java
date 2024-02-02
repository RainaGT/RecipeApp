package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipeapp.Adapters.IngredientsAdapter;
import com.example.recipeapp.Adapters.InstructionsAdapter;
import com.example.recipeapp.Adapters.SimilarRecipeAdapter;
import com.example.recipeapp.Listeners.InstructionsListner;
import com.example.recipeapp.Listeners.RecipeClickListner;
import com.example.recipeapp.Listeners.RecipeDetailListner;
import com.example.recipeapp.Listeners.SimilarRecipesListner;
import com.example.recipeapp.Models.InstructionsResponse;
import com.example.recipeapp.Models.RecipeDetailsResponse;
import com.example.recipeapp.Models.SimilarRecipeResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity {
    int id;
    TextView text_meal_name,text_meal_source,text_meal_summary;
    ImageView image_meal_image;
    RecyclerView recycler_meal_ingredients,recycler_meal_similar,recycler_meal_instructions;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;
    SimilarRecipeAdapter similarRecipeAdapter;
    InstructionsAdapter instructionsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        findViews();

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListner,id);
        manager.getSimilarRecipes(similarRecipesListner,id);
        manager.getInstructions(instructionsListner,id);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        dialog.show();

    }

    private void findViews() {
        text_meal_name = findViewById(R.id.text_meal_name);
        text_meal_source = findViewById(R.id.text_meal_source);
        text_meal_summary = findViewById(R.id.text_meal_summary);
        image_meal_image = findViewById(R.id.image_meal_image);
        recycler_meal_ingredients = findViewById(R.id.recycler_meal_ingredients);
        recycler_meal_similar = findViewById(R.id.recycler_meal_similar);
        recycler_meal_instructions = findViewById(R.id.recycler_meal_instructions);

    }

    private final RecipeDetailListner recipeDetailsListner = new RecipeDetailListner() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            text_meal_name.setText(response.title);
            text_meal_source.setText(response.sourceName);
            text_meal_summary.setText(response.summary);
            Picasso.get().load(response.image).into(image_meal_image);


            recycler_meal_ingredients.setHasFixedSize(true);
            recycler_meal_ingredients.setLayoutManager(new LinearLayoutManager(RecipeDetailActivity.this,LinearLayoutManager.HORIZONTAL,false));
            ingredientsAdapter = new IngredientsAdapter(RecipeDetailActivity.this,response.extendedIngredients);
            recycler_meal_ingredients.setAdapter(ingredientsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailActivity.this, message,Toast.LENGTH_SHORT).show();

        }
    };

    private final SimilarRecipesListner similarRecipesListner = new SimilarRecipesListner() {
        @Override
        public void didFetch(List<SimilarRecipeResponse> response, String message) {

            recycler_meal_similar.setHasFixedSize(true);
            recycler_meal_similar.setLayoutManager(new LinearLayoutManager(RecipeDetailActivity.this,LinearLayoutManager.HORIZONTAL,false));

            similarRecipeAdapter = new SimilarRecipeAdapter(RecipeDetailActivity.this,response,recipeClickListner);
            recycler_meal_similar.setAdapter(similarRecipeAdapter);
        }

        @Override
        public void didError(String message) {

            Toast.makeText(RecipeDetailActivity.this,message,Toast.LENGTH_SHORT).show();
        }
    };

    private final RecipeClickListner recipeClickListner = new RecipeClickListner() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(RecipeDetailActivity.this,RecipeDetailActivity.class)
                    .putExtra("id",id));


        }
    };

    private final InstructionsListner instructionsListner = new InstructionsListner() {
        @Override
        public void didFetch(List<InstructionsResponse> response, String message) {
            recycler_meal_instructions.setHasFixedSize(true);
            recycler_meal_instructions.setLayoutManager(new LinearLayoutManager(RecipeDetailActivity.this,LinearLayoutManager.VERTICAL,false));
            instructionsAdapter = new InstructionsAdapter(RecipeDetailActivity.this,response);
            recycler_meal_instructions.setAdapter(instructionsAdapter);
        }

        @Override
        public void didError(String message) {

        }
    };
}