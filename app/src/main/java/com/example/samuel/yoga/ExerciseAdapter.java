package com.example.samuel.yoga;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Samuel on 06/09/2017.
 */

public class ExerciseAdapter  extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>{

   private List<Exercise> exerciseList;
    private Context ctx;


    public ExerciseAdapter(List<Exercise> exerciseList,Context ctx) {
        this.exerciseList = exerciseList;
        this.ctx = ctx;
    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view  = inflater.inflate(R.layout.exercise_list,parent,false);

        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ExerciseViewHolder holder, final int position) {
        holder.exImage.setImageResource(exerciseList.get(position).getImageId());
        holder.exText.setText(exerciseList.get(position).getName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx,DetailsActivity.class);
                intent.putExtra("image",(exerciseList.get(position).getImageId()));
                intent.putExtra("name",exerciseList.get(position).getName());
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseList.size() ;
    }

    class ExerciseViewHolder extends RecyclerView.ViewHolder{

        private ImageView exImage;
        private TextView exText;
        private LinearLayout layout;

        public ExerciseViewHolder(View itemView) {
            super(itemView);
            exImage = itemView.findViewById(R.id.exercise_image);
            exText = itemView.findViewById(R.id.exercise_text);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}
