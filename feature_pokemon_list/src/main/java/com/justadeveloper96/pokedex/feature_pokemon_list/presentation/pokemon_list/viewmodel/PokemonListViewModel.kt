package com.justadeveloper96.pokedex.feature_pokemon_list.presentation.pokemon_list.viewmodel

import androidx.lifecycle.viewModelScope
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.IPokemonRepository
import com.justadeveloper96.pokedex.helpers.api.Loading
import com.justadeveloper96.pokedex.helpers.api.Success
import com.justadeveloper96.pokedex.helpers.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonListViewModel @Inject constructor(private val repository: IPokemonRepository) :
    BaseViewModel<UIState, UIEvent>(), IPokemonListViewModel {
    override val initialState
        get() = UIState(true, listOf())

    private val limit = 10
    private var offset = 0

    private var moreAvailable = false

    override fun fetch() {
        viewModelScope.launch {
            repository.get(offset, limit).collect {
                when (it) {
                    is Loading -> {
                        state.value = UIState(loading = true, list = it.data?.data ?: listOf())
                        it.data?.let {
                            moreAvailable = it.moreAvailable
                        }
                    }
                    is Success -> {
                        state.value = UIState(list = it.data.data)
                        moreAvailable = it.data.moreAvailable
                    }
                }
            }
        }
    }
}