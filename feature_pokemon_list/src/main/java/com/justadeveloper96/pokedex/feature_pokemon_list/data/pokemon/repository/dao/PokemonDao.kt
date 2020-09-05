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
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.dao.mapper.toDaoModel
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.dao.mapper.toDomainModel
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.dao.model.PokemonDaoModel
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


@Dao
interface PokemonDao : IPokemonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun _insert(list: List<PokemonDaoModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun _insert(item: PokemonDaoModel)

    @Query("SELECT * FROM PokemonDaoModel")
    fun _all(): Flow<List<PokemonDaoModel>>

    @Query("DELETE FROM PokemonDaoModel")
    override fun removeAll()

    override fun insert(list: List<Pokemon>) {
        _insert(list.map { it.toDaoModel() })
    }

    override fun insert(item: Pokemon) {
        _insert(item.toDaoModel())
    }


    override fun all(): Flow<List<Pokemon>> {
        return _all().map { it.map { it.toDomainModel() } }
    }
}