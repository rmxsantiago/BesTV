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

package com.pimenta.bestv.dagger.module;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.pimenta.bestv.BuildConfig;
import com.pimenta.bestv.repository.remote.api.tmdb.GenreApi;
import com.pimenta.bestv.repository.remote.api.tmdb.MovieApi;
import com.pimenta.bestv.repository.remote.api.tmdb.PersonApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marcus on 11-05-2018.
 */
@Module
public class MediaModule {

    @Provides
    @Singleton
    @Named("Tmdb")
    Retrofit provideTmdbRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.TMDB_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    MovieApi provideMovieApi(@Named("Tmdb") Retrofit retrofit) {
        return retrofit.create(MovieApi.class);
    }

    @Provides
    @Singleton
    GenreApi providePersonGenreApi(@Named("Tmdb") Retrofit retrofit) {
        return retrofit.create(GenreApi.class);
    }

    @Provides
    @Singleton
    PersonApi providePersonApi(@Named("Tmdb") Retrofit retrofit) {
        return retrofit.create(PersonApi.class);
    }

}