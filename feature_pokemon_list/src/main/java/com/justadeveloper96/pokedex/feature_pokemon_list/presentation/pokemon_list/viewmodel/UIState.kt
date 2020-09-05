package com.justadeveloper96.pokedex.feature_pokemon_list.presentation.pokemon_list.viewmodel

import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.model.Pokemon

data class UIState(
    val loading: Boolean = false,
    val list: List<Pokemon>
)