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

package com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.network

import com.justadeveloper96.pokedex.core.api.AppNetworkResult
import com.justadeveloper96.pokedex.core.api.execute
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.model.Pokemon
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.network.mapper.toDomainModel
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.network.model.PokemonListResponseModel
import com.justadeveloper96.pokedex.helpers.pagination.PaginatedList
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class PokemonApi @Inject constructor(retrofit: Retrofit) : IPokemonApi {

    private val service = retrofit.create(IRetrofitService::class.java)

    override suspend fun get(offset: Int, limit: Int): AppNetworkResult<PaginatedList<Pokemon>> {
        return execute(
            { service.get(offset, limit) },
            { i -> PaginatedList(i.results.map { it.toDomainModel() }, i.count) }
        )
    }

    private interface IRetrofitService {

        companion object {
            private const val ENDPOINT = "/api/v2/pokemon"
        }

        @GET(ENDPOINT)
        suspend fun get(
            @Query("offset") offset: Int,
            @Query("limit") limit: Int
        ): Response<PokemonListResponseModel>
    }
}
