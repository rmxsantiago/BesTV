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

package com.pimenta.bestv.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v17.leanback.widget.DiffCallback;

import com.pimenta.bestv.BesTV;
import com.pimenta.bestv.repository.MediaRepository;
import com.pimenta.bestv.repository.entity.Movie;

import java.util.List;

/**
 * Created by marcus on 11-02-2018.
 */
public class TopMovieGridFragment extends AbstractMovieGridFragment {

    private static final String TYPE = "TYPE";

    private MediaRepository.MovieListType mMovieListType;

    public static TopMovieGridFragment newInstance(MediaRepository.MovieListType movieListType, boolean showProgress) {
        Bundle args = new Bundle();
        args.putSerializable(TYPE, movieListType);
        args.putBoolean(SHOW_PROGRESS, showProgress);

        TopMovieGridFragment topMovieGridFragment = new TopMovieGridFragment();
        topMovieGridFragment.setArguments(args);
        topMovieGridFragment.mMovieListType = movieListType;
        topMovieGridFragment.mShowProgress = showProgress;
        return topMovieGridFragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mMovieListType == null) {
            mMovieListType = (MediaRepository.MovieListType) getArguments().getSerializable(TYPE);
            mShowProgress = getArguments().getBoolean(SHOW_PROGRESS);
        }
    }

    @Override
    void loadData() {
        mPresenter.loadMoviesByType(mMovieListType);
    }

    @Override
    public void loadMorePages() {
        if (!mMovieListType.equals(MediaRepository.MovieListType.FAVORITES)) {
            super.loadMorePages();
        }
    }

    @Override
    public void refreshDada() {
        if (mMovieListType.equals(MediaRepository.MovieListType.FAVORITES)) {
            super.loadMorePages();
        }
    }

    @Override
    public void onMoviesLoaded(final List<Movie> movies) {
        if (mMovieListType.equals(MediaRepository.MovieListType.FAVORITES)) {
            mRowsAdapter.setItems(movies, new DiffCallback<Movie>() {
                @Override
                public boolean areItemsTheSame(@NonNull final Movie oldItem, @NonNull final Movie newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull final Movie oldItem, @NonNull final Movie newItem) {
                    return oldItem.equals(newItem);
                }
            });
            getProgressBarManager().hide();
            getMainFragmentAdapter().getFragmentHost().notifyDataReady(getMainFragmentAdapter());
            return;
        }
        super.onMoviesLoaded(movies);
    }

    @Override
    protected void injectPresenter() {
        BesTV.getApplicationComponent().inject(this);
    }
}