package com.example.minimo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoActivity extends AppCompatActivity {

    APIInterface apiIface;
    MyRecyclerViewAdapter adapter;
    List<GithubRepos> reposList = new ArrayList<>();
    GithubRepos repos;
    private ProgressBar progressBar;
    private String username;
    private GithubUserClass user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        apiIface = APIClient.getClient().create(APIInterface.class);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        final Intent getUser_intent = getIntent();

        username = getUser_intent.getStringExtra("username");



        Call<GithubRepos> call = apiIface.getRepos(username);
        call.enqueue(new Callback<GithubRepos>() {
            @Override
            public void onResponse(Call<GithubRepos> call, Response<GithubRepos> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    GithubRepos repos = response.body();
                    //showProgress( false );
                    RecyclerView recyclerView = (RecyclerView)findViewById(R.id.RecyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter (new MyRecyclerViewAdapter(reposList, getApplicationContext()));
                    adapter = new MyRecyclerViewAdapter(reposList, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<GithubRepos> call, Throwable throwable) {
                call.cancel();
            }
        });

        Call<GithubUserClass> call2 = apiIface.getUser(username);
        call2.enqueue(new Callback<GithubUserClass>() {
            @Override
            public void onResponse(Call<GithubUserClass> call, Response<GithubUserClass> response) {

                progressBar.setVisibility(View.GONE);

                TextView username = findViewById(R.id.textView2);
                TextView followers = findViewById(R.id.textView3);
                TextView following = findViewById(R.id.textView4);
                ImageView pic = findViewById(R.id.imageView);

                user = response.body();

                username.setText(user.getLogin());
                followers.setText("Number of Followers: "+user.getFollowers());
                following.setText("Number of Following: "+user.getFollowing());
                Picasso.get().load(user.getAvatar_url()).into(pic);
            }

            @Override
            public void onFailure(Call<GithubUserClass> call, Throwable throwable) {

            }
        });
    }


}