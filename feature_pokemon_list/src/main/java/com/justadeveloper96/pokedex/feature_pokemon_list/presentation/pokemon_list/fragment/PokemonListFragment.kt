package com.justadeveloper96.pokedex.feature_pokemon_list.presentation.pokemon_list.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.justadeveloper96.pokedex.feature_pokemon_list.R
import com.justadeveloper96.pokedex.feature_pokemon_list.databinding.FragmentPokemonListBinding
import com.justadeveloper96.pokedex.feature_pokemon_list.presentation.pokemon_list.adapter.PokemonListAdapter
import com.justadeveloper96.pokedex.feature_pokemon_list.presentation.pokemon_list.viewmodel.Message
import com.justadeveloper96.pokedex.feature_pokemon_list.presentation.pokemon_list.viewmodel.PokemonListViewModel
import com.justadeveloper96.pokedex.feature_pokemon_list.presentation.pokemon_list.viewmodel.UIEvent
import com.justadeveloper96.pokedex.feature_pokemon_list.presentation.pokemon_list.viewmodel.UIState
import com.justadeveloper96.pokedex.helpers.extensions.onEndReached
import com.justadeveloper96.pokedex.helpers.fragment.BaseFragment
import com.justadeveloper96.pokedex.helpers.view.IView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonListFragment : BaseFragment<FragmentPokemonListBinding>(), IView<UIState, UIEvent> {

    val viewModel: PokemonListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        subscribe()
        viewModel.fetch()
    }

    private fun subscribe() {
        lifecycleScope.launch {
            viewModel.state.collect {
                render(it)
            }
        }
        lifecycleScope.launch {
            viewModel.event.consumeAsFlow().collect {
                it?.let { it1 -> onEvent(it1) }
            }
        }
    }

    override val layout: Int
        get() = R.layout.fragment_pokemon_list

    val adapter = PokemonListAdapter()

    private fun setupView() {
        binding.refresh.setOnRefreshListener {
            viewModel.refresh()
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.onEndReached {
            viewModel.fetch()
        }
    }

    override fun onEvent(event: UIEvent) {
        Log.d(TAG, "event: $event")
        when (event) {
            is Message -> {
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val TAG = "PokemonListFragment"

    override fun render(state: UIState) {
        Log.d(TAG, "render: $state")
        Log.d(TAG, "list: ${state.list}")
        adapter.items = state.list
        binding.refresh.isRefreshing = state.loading
    }
}
