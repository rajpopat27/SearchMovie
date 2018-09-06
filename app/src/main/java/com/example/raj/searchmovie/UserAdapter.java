package com.example.raj.searchmovie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

/**
 * Created by RAJ on 3/1/2018.
 */

class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
    LayoutInflater inflater;
    Context context;
    ArrayList<UserData> arrayList;
    public UserAdapter(Context context,ArrayList<UserData> list){
        this.context=context;
        arrayList=list;
    }
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater=LayoutInflater.from(parent.getContext());
        View row= inflater.inflate(R.layout.rowlayout,parent,false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, int position) {
            holder.title.setText(arrayList.get(position).getTitle());
            holder.desc.setText(arrayList.get(position).getDesc());
            holder.poster.setImageUrl(arrayList.get(position).getImage(),Singleton.getInstance(context).getImageLoader());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,desc;
        NetworkImageView poster;
        public ViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.movieTitle);
            desc=itemView.findViewById(R.id.movieDesc);
            poster=(NetworkImageView)itemView.findViewById(R.id.movieImage);
        }
    }
}
