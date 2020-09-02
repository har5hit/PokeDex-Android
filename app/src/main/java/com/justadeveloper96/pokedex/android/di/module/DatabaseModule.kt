/*
 * Copyright 2020 Harshith Shetty (justadeveloper96@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.justadeveloper96.pokedex.android.di.module

import android.content.Context
import androidx.room.Room
import com.justadeveloper96.pokedex.android.data.database.AppDatabase
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.dao.IPokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Provides
    fun database(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "pokdex-app"
        ).build()
    }

    @Provides
    fun pokemonDao(database: AppDatabase): IPokemonDao {
        return database.pokemonDao()
    }

}