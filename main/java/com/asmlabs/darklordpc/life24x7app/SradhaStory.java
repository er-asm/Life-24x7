package com.asmlabs.darklordpc.life24x7app;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SradhaStory extends AppCompatActivity {
    TextView largeText;
    RecyclerView recyclerView;
    JSONArray quotes = null;
    android.support.v7.app.ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sradha_story);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);

        try {

            JSONObject jsonObj = new JSONObject(loadJSONFromAsset());
            quotes = jsonObj.getJSONArray("biography");
            ArrayList<String> qid_list=new ArrayList<>();
            ArrayList<String> quote_list=new ArrayList<>();


            for (int i = 0; i < quotes.length(); i++) {
                JSONObject c = quotes.getJSONObject(i);
                String sqid = c.getString("qid");
                String squote = c.getString("qdata");
                qid_list.add(sqid);
                quote_list.add(squote);

            }

            RecyclerViewAdapterStories adapter=new RecyclerViewAdapterStories(this,qid_list,quote_list);
            recyclerView.setAdapter(adapter);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("lifestories/spradhastory.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
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




