package com.example.recipeapp.Listeners;

import com.example.recipeapp.Models.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipesListner {
    void didFetch(List<SimilarRecipeResponse>response , String message );
    void didError(String message);
}
