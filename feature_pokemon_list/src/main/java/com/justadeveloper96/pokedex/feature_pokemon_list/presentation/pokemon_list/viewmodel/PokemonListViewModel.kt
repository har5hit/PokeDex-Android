package com.justadeveloper96.pokedex.feature_pokemon_list.presentation.pokemon_list.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.IPokemonRepository
import com.justadeveloper96.pokedex.helpers.api.Loading
import com.justadeveloper96.pokedex.helpers.api.Success
import com.justadeveloper96.pokedex.helpers.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class PokemonListViewModel @ViewModelInject constructor(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val repository: IPokemonRepository
) :
    BaseViewModel<UIState, UIEvent>(), IPokemonListViewModel {
    override val initialState
        get() = UIState(true, listOf())

    private val limit = 10
    private var offset = 0

    private var loading = false
    private var moreAvailable = true

    override fun fetch() {
        viewModelScope.launch(coroutineDispatcher) {
            if (moreAvailable && !loading) {
                loading = true
                fetchNewItems()
            }
        }
    }

    private suspend fun fetchNewItems() {
        repository.get(offset, limit).collect {
            when (it) {
                is Loading -> {
                    state.value = UIState(
                        loading = true,
                        list = it.data?.data?.map { it.toPokemonUiModel() } ?: listOf())
                }
                is Success -> {
                    loading = false
                    offset = it.data.data.size
                    state.value = UIState(list = it.data.data.map { it.toPokemonUiModel() })
                    moreAvailable = it.data.moreAvailable
                }
                is Error -> {
                    loading = false
                    event.value = Message("Error!")
                }
            }
        }
    }
}