package com.justadeveloper96.pokedex.feature_pokemon_list.presentation.pokemon_list.viewmodel

import com.justadeveloper96.pokedex.core.api.AppNetworkResult
import com.justadeveloper96.pokedex.core.api.Loading
import com.justadeveloper96.pokedex.core.api.Success
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.IPokemonRepository
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.model.Pokemon
import com.justadeveloper96.pokedex.helpers.pagination.PaginatedList
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher

class PokemonListViewModelTest : StringSpec({

    val repository: IPokemonRepository = mockk()

    val dispatcher = TestCoroutineDispatcher()

    "inital state test" {
        val viewModel = PokemonListViewModel(dispatcher, repository)

        val dataChannel = Channel<AppNetworkResult<PaginatedList<Pokemon>>>(1)
        val data = PaginatedList(List(10) { i -> Pokemon(i.toString(), i.toString()) })
        coEvery { repository.get(0, 10) } returns dataChannel.consumeAsFlow()

        dataChannel.trySend(Loading(data))

        viewModel.fetch()

        viewModel.state.first() shouldBe UIState(
            loading = true,
            list = data.data.map { it.toPokemonUiModel() }
        )

        dataChannel.trySend(Success(data, 200))

        viewModel.state.first() shouldBe UIState(list = data.data.map { it.toPokemonUiModel() })
    }

    "pagination for new items and refresh" {
        val viewModel = PokemonListViewModel(dispatcher, repository)

        val dataChannel = Channel<AppNetworkResult<PaginatedList<Pokemon>>>(1)
        val data1 = PaginatedList(List(10) { i -> Pokemon(i.toString(), i.toString()) }, total = 20)
        val data2 = PaginatedList(List(20) { i -> Pokemon(i.toString(), i.toString()) }, total = 20)
        coEvery { repository.get(eq(0), eq(10)) } answers {
            dataChannel.trySend(Success(data1, 200))
            dataChannel.consumeAsFlow()
        }
        coEvery { repository.get(eq(10), eq(10)) } answers {
            dataChannel.trySend(Success(data2, 200))
            dataChannel.consumeAsFlow()
        }

        viewModel.fetch()

        viewModel.state.first() shouldBe UIState(list = data1.data.map { it.toPokemonUiModel() })

        viewModel.fetch()

        viewModel.state.first() shouldBe UIState(list = data2.data.map { it.toPokemonUiModel() })

        viewModel.fetch()

        coVerify(inverse = true) { repository.get(eq(30), eq(10)) }

        viewModel.refresh()

        viewModel.state.first() shouldBe UIState(list = data1.data.map { it.toPokemonUiModel() })
    }
})
