package com.asmlabs.darklordpc.life24x7app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    ArrayList<String> qid_list, quote_list;
    Context context;


    public RecyclerViewAdapter(Context context,ArrayList<String> qid_list, ArrayList<String> quote_list) {
        this.qid_list = qid_list;
        this.quote_list= quote_list;

        this.context=context;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        String sqid = qid_list.get(position);
        final String squote = quote_list.get(position);

        holder.largeText.setText(sqid +". " + squote);
       // holder.quote.setText(squote);
        holder.sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBodyText =  holder.largeText.getText().toString() + "\n\n Download Life 24x7 : Motivation is Everywhere.\n" +
                        "And Enjoy the new creative Way of getting Information.\n" +
                        "Read Motivation Stories, Watch Video Biography, Read and Share Quotes from famous Personalities.\n" +
                        "------------------------\n" +
                        "Download and Share. https://play.google.com/store/apps/details?id=com.asmlabs.darklordpc.life24x7app";
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject/Title");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                context.startActivity(Intent.createChooser(intent, "Choose sharing method"));
            }
        });

    }

    @Override
    public int getItemCount() {
        return qid_list.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.datalistquotes, parent, false);
        return new MyViewHolder(v);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView largeText;
        public ImageButton sharebtn;

        public MyViewHolder(View view) {
            super(view);
            largeText= (TextView) view.findViewById(R.id.swamitext);
            sharebtn =  (ImageButton) view.findViewById(R.id.sharebtn);

        }
    }

}