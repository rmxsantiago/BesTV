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

package com.pimenta.bestv;

import android.app.Application;
import android.os.StrictMode;
import android.util.Log;

import com.pimenta.bestv.dagger.ApplicationComponent;
import com.pimenta.bestv.dagger.DaggerApplicationComponent;
import com.pimenta.bestv.dagger.module.ApplicationModule;

/**
 * Created by marcus on 07-02-2018.
 */
public class BesTV extends Application {

    private static final String TAG = BesTV.class.getSimpleName();

    private static ApplicationComponent sApplicationComponent;

    public static BesTV get() {
        return (BesTV) sApplicationComponent.getApplication();
    }

    public static ApplicationComponent getApplicationComponent() {
        return sApplicationComponent;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "[onCreate]");
        super.onCreate();

        switch (BuildConfig.BUILD_TYPE) {
            case "debug":
                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                        .detectAll()
                        .penaltyLog()
                        .build());
                StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                        .detectLeakedSqlLiteObjects()
                        .detectLeakedClosableObjects()
                        .penaltyLog()
                        .penaltyDeath()
                        .build());
                break;
        }

        sApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}