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

package com.pimenta.bestv.manager;

/**
 * Created by marcus on 04/07/18.
 */
public interface PreferenceManager {

    /**
     * Gets the {@link String} value from {@link android.content.SharedPreferences}
     *
     * @param key      Key to get the value
     * @param defValue Default value
     *
     * @return {@link String}
     */
    String getFromPersistence(String key, String defValue);

    /**
     * Applies to persistence in {@link android.content.SharedPreferences}
     *
     * @param key   Key to save the value
     * @param value Value to be saved
     */
    void applyToPersistence(String key, String value);

}