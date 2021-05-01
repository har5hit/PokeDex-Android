package com.justadeveloper96.pokedex.feature_pokemon_list.presentation.pokemon_list.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.justadeveloper96.pokedex.core.api.Loading
import com.justadeveloper96.pokedex.core.api.NetworkException
import com.justadeveloper96.pokedex.core.api.Success
import com.justadeveloper96.pokedex.core.api.Unsuccessful
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.IPokemonRepository
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
    private var list = listOf<PokemonUiModel>()

    override fun refresh() {
        clearFlags()
        fetch()
    }

    private fun clearFlags() {
        offset = 0
        moreAvailable = true
    }

    override fun fetch() {
        viewModelScope.launch(coroutineDispatcher) {
            if (moreAvailable && !loading) {
                loading = true
                invalidate()
                fetchNewItems()
            }
        }
    }

    private suspend fun fetchNewItems() {
        repository.get(offset, limit).collect {
            when (it) {
                is Loading -> {
                    loading = true
                    list = it.data?.data?.map { it.toPokemonUiModel() } ?: listOf()
                }
                is Success -> {
                    loading = false
                    offset = it.data.data.size
                    list = it.data.data.map { it.toPokemonUiModel() }
                    moreAvailable = it.data.moreAvailable
                }
                is Unsuccessful, is NetworkException -> {
                    loading = false
                    event.offer(Message(it.error))
                }
            }
            invalidate()
        }
    }

    private fun invalidate() {
        state.value = UIState(
            loading = loading,
            list = list
        )
    }
}
