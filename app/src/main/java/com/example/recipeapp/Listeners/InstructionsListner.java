package com.example.recipeapp.Listeners;

import com.example.recipeapp.Models.InstructionsResponse;

import java.util.List;

public interface InstructionsListner {
    void didFetch(List<InstructionsResponse> response, String message);
    void didError(String message);
}
