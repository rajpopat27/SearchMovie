package com.example.raj.searchmovie;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by RAJ on 3/1/2018.
 */

public class Singleton {
    RequestQueue requestQueue;
    static Context context;
    static Singleton instance;
    ImageLoader imageLoader;

    public Singleton(Context context) {
        this.context=context;
        requestQueue=getRequestQueue();
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            LruCache<String,Bitmap> cache=new LruCache<>(10);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                    cache.put(url, bitmap);
            }
        });
    }

    public RequestQueue getRequestQueue() {
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }
    public static Singleton getInstance(Context context){
        if (instance==null){
            instance=new Singleton(context);
        }
        return instance;
    }
    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
