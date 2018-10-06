package com.asmlabs.darklordpc.life24x7app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

public class InspiringLifeStories extends AppCompatActivity {
    android.support.v7.app.ActionBar actionBar;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    ArrayList<String> alName;
    ArrayList<Integer> alImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspiring_life_stories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        // TODO: Remove the redundant calls to getSupportActionBar()
        // and use variable actionBar instead
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBodyText ="\"If you can dream it, you can do it.” —Walt Disney\"\n\nFor more awesome things,Download Life 24x7 : Motivation is Everywhere.\n" +
                        "And Enjoy the new creative Way of getting Information.\n" +
                        "Read Motivation Stories, Watch Video Biography, Read and Share Quotes from famous Personalities.\n" +
                        "------------------------\n" +
                        "Download and Share. https://play.google.com/store/apps/details?id=com.asmlabs.darklordpc.life24x7app";
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject/Title");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(intent, "Choose sharing method"));
            }
        });

        alName = new ArrayList<>(Arrays.asList("Swami Vivekanand", "Mahatma Gandhi","sardar vallabhai patel","Rajendra Prasad",
                    "Lord Gautama Buddha","Srvepalli Radhakrishnan","Dr. APJ Abdul Kalam","Narendra Modi","Dalai Lama",
                    "Abraham Lincoln","Dhiru Bhai Ambani, Reliance","Walt Disney, Disney","Jack Ma, Alibaba","Karsanbhai Patel, Nirma",
                    "Flipkart Founders","Google Founders"

                   )
        );
        alImage = new ArrayList<>(Arrays.asList(R.drawable.swamiji,R.drawable.gandhiji,R.drawable.sardarji,R.drawable.rajendraprasad,
                R.drawable.buddha,R.drawable.spradhakrishnan,R.drawable.apjkalam,R.drawable.indiapm,R.drawable.dalailama,
                R.drawable.abrahamlincon,R.drawable.reliancefounder,R.drawable.waltdisney,R.drawable.jackma,R.drawable.nirmafounder,
                R.drawable.flipkartceo,R.drawable.googles));

        // Calling the RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new GridAdapterInspstories(InspiringLifeStories.this, alName, alImage);
        mRecyclerView.setAdapter(mAdapter);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
