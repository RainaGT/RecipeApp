package com.example.recipeapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Models.Step;
import com.example.recipeapp.R;

import java.util.List;

public class InstructionStepsAdapter extends RecyclerView.Adapter<InstructionStepViewHolder> {

    Context context;
    List<Step> list;

    public InstructionStepsAdapter(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instrutions_steps,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull InstructionStepViewHolder holder, int position) {

        holder.text_instrutcions_step_number.setText(String.valueOf(list.get(position).number));
        holder.text_instrutcions_step_title.setText(list.get(position).step);

        holder.recycler_instructions_ingredienst.setHasFixedSize(true);
        holder.recycler_instructions_ingredienst.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        InstructionsIngredientsAdapter ingredientsAdapter = new InstructionsIngredientsAdapter(context,list.get(position).ingredients);
        holder.recycler_instructions_ingredienst.setAdapter(ingredientsAdapter);

        holder.recycler_instructions_equipments.setHasFixedSize(true);
        holder.recycler_instructions_equipments.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        InstructionsEquipmentsAdapter instructionsEquipmentsAdapter = new InstructionsEquipmentsAdapter(context,list.get(position).equipment);
        holder.recycler_instructions_equipments.setAdapter(instructionsEquipmentsAdapter);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionStepViewHolder extends RecyclerView.ViewHolder{

    TextView text_instrutcions_step_number,text_instrutcions_step_title;
    RecyclerView recycler_instructions_equipments,recycler_instructions_ingredienst;
    public InstructionStepViewHolder(@NonNull View itemView) {
        super(itemView);
        text_instrutcions_step_number = itemView.findViewById(R.id.text_instrutcions_step_number);
        text_instrutcions_step_title = itemView.findViewById(R.id.text_instrutcions_step_title);
        recycler_instructions_equipments = itemView.findViewById(R.id.recycler_instructions_equipments);
        recycler_instructions_ingredienst = itemView.findViewById(R.id.recycler_instructions_ingredienst);
    }
}