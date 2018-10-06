package com.asmlabs.darklordpc.life24x7app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapterInspstories extends RecyclerView.Adapter<GridAdapterInspstories.ViewHolder> {

    ArrayList<String> alName;
    ArrayList<Integer> alImage;
    Context context;

    public GridAdapterInspstories(Context context, ArrayList<String> alName, ArrayList<Integer> alImage) {
        super();
        this.context = context;
        this.alName = alName;
        this.alImage = alImage;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvSpecies.setText(alName.get(i));
        viewHolder.imgThumbnail.setImageResource(alImage.get(i));

        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                /*if (isLongClick) {
                    Toast.makeText(context, "#" + position + " - " + alName.get(position) + " (Long click)", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, Motivational_Quotes.class));
                } else {
                    Toast.makeText(context, "#" + position + " - " + alName.get(position), Toast.LENGTH_SHORT).show();
                }*/
                if(position==0){
                    context.startActivity(new Intent(context, SwamiVivekanandStory.class));
                }
                if(position==1){
                    context.startActivity(new Intent(context, MahatmaGandhiLifeStory.class));
                }
                if(position==2){
                    context.startActivity(new Intent(context, SardarVallabhaiPatelStory.class));
                }
                if(position==3){
                    context.startActivity(new Intent(context, RajendraPrasadStory.class));
                }
                if(position==4){
                    context.startActivity(new Intent(context, GautamaBuddhaStory.class));
                }
                if(position==5){
                    context.startActivity(new Intent(context, SradhaStory.class));
                }
                if(position==6){
                    context.startActivity(new Intent(context, APJStory.class));
                }
                if(position==7){
                    context.startActivity(new Intent(context, ModiStory.class));
                }
                if(position==8){
                    context.startActivity(new Intent(context, DalaiLamaStory.class));
                }
                if(position==9){
                    context.startActivity(new Intent(context, LincolnStory.class));
                }
                if(position==10){
                    context.startActivity(new Intent(context, DhiruBhaiStory.class));
                }
                if(position==11){
                    context.startActivity(new Intent(context, DsineyStory.class));
                }
                if(position==12){
                    context.startActivity(new Intent(context, AlibabaStory.class));
                }
                if(position==13){
                    context.startActivity(new Intent(context, NirmaStory.class));
                }
                if(position==14){
                    context.startActivity(new Intent(context, FlipkartStory.class));
                }
                if(position==15){
                    context.startActivity(new Intent(context, GoogleStory.class));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return alName.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public ImageView imgThumbnail;
        public TextView tvSpecies;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            tvSpecies = (TextView) itemView.findViewById(R.id.tv_species);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }

}