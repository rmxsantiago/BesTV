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

import com.pimenta.bestv.repository.entity.GenreList;
import com.pimenta.bestv.repository.entity.MovieList;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by marcus on 08-02-2018.
 */
public interface GenreApi {

    @GET("genre/movie/list")
    Single<GenreList> getGenres(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("genre/{genre_id}/movies")
    Single<MovieList> getMovies(@Path("genre_id") int genreId, @Query("api_key") String apiKey, @Query("language") String language,
            @Query("include_adult") boolean includeAdult, @Query("page") int page);

}