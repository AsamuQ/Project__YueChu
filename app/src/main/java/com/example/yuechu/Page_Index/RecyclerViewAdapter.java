package com.example.yuechu.Page_Index;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuechu.R;
import com.example.yuechu.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<Recipe> recipesList;
    private OnRecyclerViewItemClickListener listener=null;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPortrait;
        TextView tvName, tvdes;

        public ViewHolder(View itemView) {
            super(itemView);
            imgPortrait = itemView.findViewById(R.id.imgPortrait);
            tvName = itemView.findViewById(R.id.tvName);
            tvdes = itemView.findViewById(R.id.tvDes);

        }
    }

    public RecyclerViewAdapter(Context context) {
        this.context=context;
        recipesList=new ArrayList<>();
    }

    public RecyclerViewAdapter(List<Recipe> list,Context context) {
        this.recipesList=list;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Recipe recipe = recipesList.get(position);
        Glide.with(context).load(recipe.getPortrait()).into(holder.imgPortrait);
        holder.tvName.setText(recipe.getName());
        holder.tvdes.setText(recipe.getDescription());
        if (listener!=null){
            View.OnClickListener mlistener=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.myClick(v,position);
                }
            };
            holder.imgPortrait.setOnClickListener(mlistener);
            holder.tvName.setOnClickListener(mlistener);
            holder.tvdes.setOnClickListener(mlistener);
        }
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    public Recipe getRecipe(int position){return recipesList.get(position);}

    //下拉刷新用
    public void refreshRecipes(List<Recipe> list){
        List<Recipe> newRecipe=new ArrayList<>();
        for (Recipe r: list){
            if (!this.recipesList.equals(r)){
                newRecipe.add(r);
            }
        }
        this.recipesList.addAll(0,newRecipe);
        this.notifyDataSetChanged();
    }
    //上拉加载用
    public void addRecipes(List<Recipe> list){
        this.recipesList.addAll(list);
        this.notifyDataSetChanged();
    }

//    item点击处理
    public interface OnRecyclerViewItemClickListener{
        void myClick(View view,int position);
    }

    public void setOnItemClickListenner(OnRecyclerViewItemClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View v) {
        int position=(int)v.getTag();
        if (listener!=null){
            listener.myClick(v,position);
        }
    }
}
