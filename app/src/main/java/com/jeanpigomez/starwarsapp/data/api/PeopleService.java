package com.jeanpigomez.starwarsapp.data.api;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PeopleService {
    @GET("people/")
    Flowable<PeopleResponse> loadCharacters(@Query("page") int page);
}