package com.example.stenyo.stenyo_desafio_android.api.service;

import com.example.stenyo.stenyo_desafio_android.models.PullRequest;
import com.example.stenyo.stenyo_desafio_android.models.Repositories;
import com.example.stenyo.stenyo_desafio_android.models.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RepositoriesService {
/* @GET("auction/{id}/bids")
    Observable<Response<List<Bid>>> bids(@Path("id") int id,@Query("page") int page);
* */
    @GET("search/repositories?q=language:Java&sort=stars")//&page={page}
    Observable<Response<Repositories>> list(@Query("page") int page);

    @GET("repos/{user}/{repos}/pulls")//&page={page}
    Observable<Response<List<PullRequest>>> listRep(@Path("user") String user, @Path("repos") String repos);


}
