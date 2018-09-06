package com.example.raj.searchmovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShowMovie extends AppCompatActivity {
        RecyclerView recyclerView;
        UserAdapter adapter;
        ArrayList<UserData> list=new ArrayList<>();
        String parameters="";
        String query="https://api.themoviedb.org/3/search/movie?api_key=079e564ec5a35687cc698cbb3221f3d4&language=en-US&page=1&query=";
        RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movie);
        String parameters = getIntent().getStringExtra("movie");
        query=buildQuery(parameters,query);
        requestQueue = Singleton.getInstance(this).getRequestQueue();

        JsonObjectRequest obj = new JsonObjectRequest(Request.Method.GET, query, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array=response.getJSONArray("results");
                    for (int i = 0; i <array.length() ; i++) {
                        JSONObject result = array.getJSONObject(i);
                        String title=result.getString("title");
                        StringBuilder temp= new StringBuilder("http://image.tmdb.org/t/p/w185");
                        temp.append(result.getString("poster_path"));
                        String image=temp.toString();
                        String desc=result.getString("overview");
                        UserData data = new UserData();
                        data.setTitle(title);
                        data.setDesc(desc);
                        data.setImage(image);
                        list.add(data);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    adapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShowMovie.this, "Does not work", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(obj);
        recyclerView=findViewById(R.id.recyclerView);
        adapter=new UserAdapter(this,list);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }

    public String buildQuery(String parameters,String query) {
        String[] params  =parameters.split(" ");
        StringBuilder temp=new StringBuilder(query);
        for (String i:params) {
            temp.append(i);
            temp.append("+");
        }
        return temp.substring(0,temp.length()-1);
    }
    //public void getList(String query, RequestQueue requestQueue){
        //final ArrayList<UserData> list =new ArrayList<>();

    //}
}
