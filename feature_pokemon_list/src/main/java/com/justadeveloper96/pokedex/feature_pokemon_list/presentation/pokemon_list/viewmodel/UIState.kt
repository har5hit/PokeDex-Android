package com.justadeveloper96.pokedex.feature_pokemon_list.presentation.pokemon_list.viewmodel

data class UIState(
    val loading: Boolean = false,
    val list: List<PokemonUiModel>
)
