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

package com.pimenta.bestv.presenter;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.pimenta.bestv.R;
import com.pimenta.bestv.connector.TmdbConnector;
import com.pimenta.bestv.manager.ImageManager;
import com.pimenta.bestv.model.Cast;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by marcus on 05-04-2018.
 */
public class CastDetailsPresenter extends AbstractPresenter<CastDetailsCallback> {

    private Application mApplication;
    private TmdbConnector mTmdbConnector;
    private ImageManager mImageManager;

    @Inject
    public CastDetailsPresenter(Application application, TmdbConnector tmdbConnector, ImageManager imageManager) {
        super();
        mApplication = application;
        mTmdbConnector = tmdbConnector;
        mImageManager = imageManager;
    }

    /**
     * Load the {@link Cast} details
     *
     * @param cast {@link Cast}
     */
    public void loadCastDetails(Cast cast) {
        mCompositeDisposable.add(Single.create((SingleOnSubscribe<Cast>) e -> {
                    final Cast castResult = mTmdbConnector.getCastDetails(cast);
                    if (castResult != null) {
                        e.onSuccess(castResult);
                    } else {
                        e.onError(new AssertionError());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(castResult -> {
                    if (mCallback != null) {
                        mCallback.onCastLoaded(castResult);
                    }
                }, throwable -> {
                    if (mCallback != null) {
                        mCallback.onCastLoaded(null);
                    }
                }));
    }

    /**
     * Loads the {@link Cast} image
     *
     * @param cast {@link Cast}
     */
    public void loadCastImage(Cast cast) {
        mImageManager.loadImage(String.format(mApplication.getString(R.string.tmdb_load_image_url_api), cast.getProfilePath()),
                new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull final Drawable resource, @Nullable final Transition<? super Drawable> transition) {
                        if (mCallback != null) {
                            mCallback.onCardImageLoaded(resource);
                        }
                    }

                    @Override
                    public void onLoadFailed(@Nullable final Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        if (mCallback != null) {
                            mCallback.onCardImageLoaded(null);
                        }
                    }
                });
    }

}