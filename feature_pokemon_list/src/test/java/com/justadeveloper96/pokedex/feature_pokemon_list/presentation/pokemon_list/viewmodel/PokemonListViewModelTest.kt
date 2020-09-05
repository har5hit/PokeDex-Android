package com.justadeveloper96.pokedex.feature_pokemon_list.presentation.pokemon_list.viewmodel

import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.IPokemonRepository
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.model.Pokemon
import com.justadeveloper96.pokedex.helpers.api.Loading
import com.justadeveloper96.pokedex.helpers.api.NetworkResult
import com.justadeveloper96.pokedex.helpers.api.Success
import com.justadeveloper96.pokedex.helpers.pagination.PaginatedList
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.first


class PokemonListViewModelTest : StringSpec({

    val repository: IPokemonRepository = mockk<IPokemonRepository>()

    val viewModel = PokemonListViewModel(repository)

    "inital state test"{

        val dataChannel = Channel<NetworkResult<PaginatedList<Pokemon>>>(1)
        val data = PaginatedList(List(10) { i -> Pokemon(i) })
        coEvery { repository.get(0, 10) } returns dataChannel.consumeAsFlow()

        dataChannel.offer(Loading(data))

        viewModel.fetch()

        viewModel.state.first() shouldBe UIState(loading = true, list = data.data)

        dataChannel.offer(Success(data))

        viewModel.state.first() shouldBe UIState(list = data.data)
    }
})