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

package com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.dao

import androidx.room.Dao
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.dao.model.PokemonDaoModel
import kotlinx.coroutines.flow.Flow

@Dao
interface IPokemonDao {

    fun insert(list: List<PokemonDaoModel>)

    fun insert(item: PokemonDaoModel)

    fun all(): Flow<List<PokemonDaoModel>>

    fun deleteAll()
}
