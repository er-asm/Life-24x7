package com.asmlabs.darklordpc.life24x7app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by darklordpc on 21/09/2017.
 */

public class YoutubeRecyclerView extends  RecyclerView.Adapter<YoutubeRecyclerView.VideoViewHolder>  {

    List<YoutubeVideoURL> youtubeVideoList;
    ArrayList<String> qid_list, quote_list;
    Vector<YoutubeVideoURL> context;


    public YoutubeRecyclerView(List<YoutubeVideoURL> youtubeVideoList) {
        this.youtubeVideoList = youtubeVideoList;
    }
    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtubelist,parent,false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VideoViewHolder holder, final int position) {
            holder.videoweb.loadData(youtubeVideoList.get(position).getVideourl(),"text/html","utf-8");

    }

    @Override
    public int getItemCount() {
        return youtubeVideoList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{
        WebView videoweb;

        public VideoViewHolder(View itemView) {
            super(itemView);

            videoweb = (WebView)itemView.findViewById(R.id.videowebview);
            videoweb.getSettings().setJavaScriptEnabled(true);
            //videoweb.getSettings().setPluginState(WebSettings.PluginState.ON);
            //videoweb.setWebChromeClient(new WebChromeClient(){});
        }
    }

    @Override
    public void onViewAttachedToWindow(VideoViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(VideoViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }
}
