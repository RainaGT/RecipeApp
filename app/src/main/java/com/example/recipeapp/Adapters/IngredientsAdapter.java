package com.example.recipeapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Models.ExtendedIngredient;
import com.example.recipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder>{

    Context context;
    List<ExtendedIngredient> list;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_meal_ingredients,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {

        holder.text_ingredients_name.setText(list.get(position).name);
        holder.text_ingredients_name.setSelected(true);
        holder.text_ingredients_quantity.setText(list.get(position).original);
        holder.text_ingredients_quantity.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.image_ingredients);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class IngredientsViewHolder extends RecyclerView.ViewHolder{

    TextView text_ingredients_quantity,text_ingredients_name;
    ImageView image_ingredients;

    public IngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        image_ingredients = itemView.findViewById(R.id.image_ingredients);
        text_ingredients_quantity = itemView.findViewById(R.id.text_ingredients_quantity);
        text_ingredients_name = itemView.findViewById(R.id.text_ingredients_name);


    }
}
