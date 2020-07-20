package com.example.myapplicatio;// Add/change Your Package Name If You Are Copying And Pasting It On Existing App


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WelcomeAdapter extends RecyclerView.Adapter<WelcomeAdapter.WelcomeViewHolder> {

    private List<WelcomeItem> welcomeItems;

    public WelcomeAdapter(List<WelcomeItem> welcomeItems) {
        this.welcomeItems = welcomeItems;
    }

    @NonNull
    @Override
    public WelcomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WelcomeViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container,parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull WelcomeViewHolder holder, int position) {
        holder.setWelcomeData(welcomeItems.get(position));
    }

    @Override
    public int getItemCount() {
        return welcomeItems.size();
    }

    class WelcomeViewHolder extends RecyclerView.ViewHolder{
        private TextView textTitle;
        private TextView textDescription;
        private ImageView imageWelcome;

        WelcomeViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDescription);
            imageWelcome = itemView.findViewById(R.id.imageWelcome);
        }

        void setWelcomeData(WelcomeItem welcomeItem){
            textTitle.setText(welcomeItem.getTitle());
            textDescription.setText(welcomeItem.getDescription());
            imageWelcome.setImageResource(welcomeItem.getImage());
        }
    }
}
