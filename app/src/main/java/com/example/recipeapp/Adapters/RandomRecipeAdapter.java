package com.example.recipeapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Listeners.RecipeClickListner;
import com.example.recipeapp.Models.Recipe;
import com.example.recipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder>{

    Context context;
    List<Recipe> list;
    RecipeClickListner listner;

    public RandomRecipeAdapter(Context context, List<Recipe> list,RecipeClickListner listner) {
        this.context = context;
        this.list = list;
        this.listner = listner;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {

        holder.text_title.setText(list.get(position).title);
        holder.text_title.setSelected(true);
        holder.text_likes.setText(list.get(position).aggregateLikes+" Likes");
        holder.text_serving.setText(list.get(position).servings+" Servings");
        holder.text_time.setText(list.get(position).readyInMinutes+" Minutes");
        Picasso.get().load(list.get(position).image).into(holder.image_food);

        holder.random_list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class RandomRecipeViewHolder extends RecyclerView.ViewHolder{
    CardView random_list_container;
    TextView text_title,text_serving,text_likes,text_time;
    ImageView image_food;



    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);

        random_list_container =itemView.findViewById(R.id.random_list_container);
        text_likes = itemView.findViewById(R.id.text_likes);
        text_serving = itemView.findViewById(R.id.text_serving);
        text_time = itemView.findViewById(R.id.text_time);
        text_title = itemView.findViewById(R.id.text_title);
        image_food =itemView.findViewById(R.id.image_food);
    }
}
