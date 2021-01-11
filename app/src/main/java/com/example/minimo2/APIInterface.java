package com.example.minimo2;

import com.example.minimo2.GithubFollowers;
import com.example.minimo2.GithubRepos;
import com.example.minimo2.GithubUserClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("/users/{username}")
    Call<GithubUserClass> getUser(@Path("username") String username);

    @GET("/users/{username}/repos")
    Call<GithubRepos> getRepos(@Path("username") String username);


}

