package com.example.raj.searchmovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Search extends AppCompatActivity {
    EditText movieName;
    Button go;
    String movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        movieName=findViewById(R.id.movieName);
        go=findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movie=movieName.getText().toString();
                Intent intent=new Intent(Search.this,ShowMovie.class);
                intent.putExtra("movie",movie);
                startActivity(intent);
            }
        });
    }
}
