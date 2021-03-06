/*
 * Copyright (C) 2018 Marcus Pimenta
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.pimenta.bestv.repository.remote.api.tmdb;

import com.pimenta.bestv.repository.entity.CastList;
import com.pimenta.bestv.repository.entity.Movie;
import com.pimenta.bestv.repository.entity.MovieList;
import com.pimenta.bestv.repository.entity.VideoList;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by marcus on 11-02-2018.
 */
public interface MovieApi {

    @GET("movie/{movie_id}")
    Call<Movie> getMovie(@Path("movie_id") int movie_id, @Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/{movie_id}/credits")
    Single<CastList> getCastByMovie(@Path("movie_id") int movie_id, @Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/{movie_id}/recommendations")
    Single<MovieList> getRecommendationByMovie(@Path("movie_id") int movie_id, @Query("api_key") String apiKey, @Query("language") String language,
            @Query("page") int page);

    @GET("movie/{movie_id}/similar")
    Single<MovieList> getSimilarByMovie(@Path("movie_id") int movie_id, @Query("api_key") String apiKey, @Query("language") String language,
            @Query("page") int page);

    @GET("movie/{movie_id}/videos")
    Single<VideoList> getVideosByMovie(@Path("movie_id") int movie_id, @Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/now_playing")
    Single<MovieList> getNowPlayingMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);

    @GET("movie/popular")
    Single<MovieList> getPopularMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);

    @GET("movie/top_rated")
    Single<MovieList> getTopRatedMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);

    @GET("movie/upcoming")
    Single<MovieList> getUpComingMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);

    @GET("search/movie")
    Single<MovieList> searchMoviesByQuery(@Query("api_key") String apiKey, @Query("query") String query, @Query("language") String language,
            @Query("page") int page);

}