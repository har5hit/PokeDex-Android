package com.justadeveloper96.pokedex.feature_pokemon_list.presentation.pokemon_list.viewmodel

sealed class UIEvent

data class Message(val message: String?) : UIEvent()
