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

package com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository

import com.justadeveloper96.pokedex.core.api.Loading
import com.justadeveloper96.pokedex.core.api.Success
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.dao.IPokemonDao
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.dao.mapper.toDaoModel
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.dao.mapper.toDomainModel
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.network.IPokemonApi
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.network.mapper.toDomainModel
import com.justadeveloper96.pokedex.helpers.pagination.PaginatedList
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonApi: IPokemonApi,
    private val pokemonDao: IPokemonDao
) : IPokemonRepository {

    override suspend fun get(offset: Int, limit: Int) =
        combine(
            pokemonDao.all(), fetchPokemonList(offset, limit)
        ) { a, b ->
            val data =
                PaginatedList(a.map { it.toDomainModel() }, maxOf(a.size, b.data?.total ?: 0))
            b.modify(data)
        }

    private suspend fun fetchPokemonList(offset: Int, limit: Int) = flow {
        emit(Loading())
        val result = pokemonApi.get(offset, limit)
        if (result is Success) {
            if (offset == 0) {
                pokemonDao.deleteAll()
            }
            pokemonDao.insert(result.data.results.map { it.toDaoModel() })
        }
        emit(result.map { PaginatedList(it.results.map { it.toDomainModel() }, it.count) })
    }
}
