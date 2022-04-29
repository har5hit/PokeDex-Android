package com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository

import com.justadeveloper96.pokedex.core.api.Loading
import com.justadeveloper96.pokedex.core.api.Success
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.dao.IPokemonDao
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.dao.mapper.toDomainModel
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.dao.model.PokemonDaoModel
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.network.IPokemonApi
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.network.mapper.toDomainModel
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.network.model.PokemonListResponseModel
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.network.model.PokemonResponseModel
import com.justadeveloper96.pokedex.helpers.pagination.PaginatedList
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList

class PokemonRepositoryTest : StringSpec({

    val api = mockk<IPokemonApi>()
    val dao: IPokemonDao = FakePokemonDao()

    val repository = PokemonRepository(api, dao)

    "first load local data and then update with network data" {

        val localData = List(10) { i -> PokemonDaoModel(i.toString(), i.toString()) }
        dao.insert(localData)

        val networkData =
            List(10) { i -> PokemonResponseModel((i * 2).toString(), (i * 2).toString()) }
        coEvery { api.get(0, 10) } returns Success(PokemonListResponseModel(20, networkData), 200)

        val states = repository.get(0, 10).take(2).toList(mutableListOf())

        val localDataList = localData.map { it.toDomainModel() }
        val networkDataList = networkData.map { it.toDomainModel() }
        states shouldBe listOf(
            Loading(
                PaginatedList(
                    localDataList,
                    localDataList.size
                )
            ),
            Success(
                PaginatedList(
                    networkDataList,
                    20
                ),
                200
            )
        )
    }
})
