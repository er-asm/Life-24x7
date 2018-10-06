package com.asmlabs.darklordpc.life24x7app;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Vector;

public class MotivationalVideos extends AppCompatActivity{
    android.support.v7.app.ActionBar actionBar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    JSONArray quotes = null;
    ImageView netimage;
    private Menu mymenu;
    Vector<YoutubeVideoURL> youtubeVideos = new Vector<YoutubeVideoURL>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivational_videos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        isNetworkConnectionAvailable();

    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("motivationalvideos/videos.json");
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
    public void checkNetworkConnection(){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("No internet Connection");
        builder.setMessage("Please turn on internet connection to continue");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public boolean isNetworkConnectionAvailable(){
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if(isConnected) {
            //do if network available
            try {

                JSONObject jsonObj = new JSONObject(loadJSONFromAsset());
                quotes = jsonObj.getJSONArray("videoslist");
                ArrayList<String> qid_list=new ArrayList<>();
                ArrayList<String> quote_list=new ArrayList<>();


                for (int i = 0; i < quotes.length(); i++) {
                    JSONObject c = quotes.getJSONObject(i);
                    String sqid = c.getString("qid");
                    String squote = c.getString("qdata");
                    qid_list.add(sqid);
                    quote_list.add(squote);
                    //vdoname.setText(sqid);
                    youtubeVideos.add(new YoutubeVideoURL("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+squote+"?ecver=1\" frameborder=\"0\" allowfullscreen></iframe>"));
                }

                recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));


                YoutubeRecyclerView youtubeRecyclerView = new YoutubeRecyclerView(youtubeVideos);
                recyclerView.setAdapter(youtubeRecyclerView);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("Network", "Connected");
            return true;
        }
        else{
            checkNetworkConnection();
            netimage = (ImageView)findViewById(R.id.networkimg);
            TextView netstatus = (TextView)findViewById(R.id.netstatus);
            netstatus.setVisibility(View.VISIBLE);
            netimage.setVisibility(View.VISIBLE);
            Log.d("Network","Not Connected");
            return false;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_refresh:
                new UpdateTask(this).execute();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Add our menu
        getMenuInflater().inflate(R.menu.refreshmenu, menu);

        // We should save our menu so we can use it to reset our updater.
        mymenu = menu;

        //
        return true;
    }


    public void resetUpdating()
    {
        // Get our refresh item from the menu
        MenuItem m = mymenu.findItem(R.id.action_refresh);
        if(m.getActionView()!=null)
        {
            // Remove the animation.
            m.getActionView().clearAnimation();
            m.setActionView(null);
        }
    }
}



