package com.example.surveyapp.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET()
    Call<List<Servey>> getByUrlString(
            @Url String url
    );
/*
    @GET()
    Call<List<UserPost>> getByPostUrl(
            @Url String url
    );

    @GET()
    Call<List<CommentsResponse>> getByCommentsUrl(
            @Url String url
    );

    @GET()
    Call<List<PhotoAlbum>> getByAlbumsUrl(
            @Url String url
    );
    @GET()
    Call<List<UserPhoto>> getByPhotosUrl(
            @Url String url
    );*/

}

