package com.asmlabs.darklordpc.life24x7app;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by darklordpc on 21/09/2017.
 */


public class UpdateTask extends AsyncTask<Void, Void, Void> {

    private Context mCon;

    public UpdateTask(Context con)
    {
        mCon = con;
    }

    @Override
    protected Void doInBackground(Void... nope) {
        try {
            // Set a time to simulate a long update process.
            Thread.sleep(4000);

            return null;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Void nope) {
        // Give some feedback on the UI.
        Toast.makeText(mCon, "Finished Loading",
                Toast.LENGTH_LONG).show();

        // Change the menu back
        ((MotivationalVideos) mCon).resetUpdating();
    }
}