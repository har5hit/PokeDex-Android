package com.justadeveloper96.pokedex.feature_pokemon_list.presentation.pokemon_list.fragment


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.justadeveloper96.pokedex.feature_pokemon_list.R
import com.justadeveloper96.pokedex.feature_pokemon_list.databinding.FragmentPokemonListBinding
import com.justadeveloper96.pokedex.feature_pokemon_list.presentation.pokemon_list.viewmodel.PokemonListViewModel
import com.justadeveloper96.pokedex.feature_pokemon_list.presentation.pokemon_list.viewmodel.UIEvent
import com.justadeveloper96.pokedex.feature_pokemon_list.presentation.pokemon_list.viewmodel.UIState
import com.justadeveloper96.pokedex.helpers.fragment.BaseFragment
import com.justadeveloper96.pokedex.helpers.view.IView
import javax.inject.Inject

class PokemonListFragment : BaseFragment<FragmentPokemonListBinding>(), IView<UIState, UIEvent> {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: PokemonListViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()

    }

    override val layout: Int = R.layout.fragment_pokemon_list

    private fun setupView() {
    }

    override fun onEvent(event: UIEvent) {
        Log.d(TAG, "event: $event")

    }

    private val TAG = "PokemonListFragment"

    override fun render(state: UIState) {
        Log.d(TAG, "render: $state")
    }

}