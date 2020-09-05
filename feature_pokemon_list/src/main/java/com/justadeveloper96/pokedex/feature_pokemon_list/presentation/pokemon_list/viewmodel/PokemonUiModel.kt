package com.justadeveloper96.pokedex.feature_pokemon_list.presentation.pokemon_list.viewmodel

import android.graphics.Color
import com.justadeveloper96.pokedex.feature_pokemon_list.data.pokemon.repository.model.Pokemon
import java.util.*


data class PokemonUiModel(
    val name: String,
    val url: String
) {
    init {
        val rnd: Random = Random()
        var color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

}

fun Pokemon.toPokemonUiModel(): PokemonUiModel {
    return PokemonUiModel(
        name.capitalize(), url
    )
}