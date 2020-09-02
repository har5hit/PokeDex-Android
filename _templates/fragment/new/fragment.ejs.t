---
to: <%= module %>/src/main/java/com/justadeveloper96/pokedex/<%= module %>/presentation/<%=h.changeCase.snake(name) %>/fragment/<%=name %>Fragment.kt
---
<%
folder = h.changeCase.snake(name)
name_snake = h.changeCase.snake(name)
%>
package com.justadeveloper96.pokedex.<%= module %>.presentation.<%=folder %>.fragment


import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import android.view.View
import android.util.Log
import androidx.fragment.app.viewModels
import com.justadeveloper96.pokedex.<%= module %>.R
import com.justadeveloper96.pokedex.<%= module %>.databinding.Fragment<%=name %>Binding
import com.justadeveloper96.pokedex.<%= module %>.presentation.<%=folder %>.viewmodel.<%=name %>ViewModel
import com.justadeveloper96.pokedex.<%= module %>.presentation.<%=folder %>.viewmodel.UIEvent
import com.justadeveloper96.pokedex.<%= module %>.presentation.<%=folder %>.viewmodel.UIState
import com.justadeveloper96.pokedex.helpers.fragment.BaseFragment
import com.justadeveloper96.pokedex.helpers.view.IView
import javax.inject.Inject

class <%=name %>Fragment : BaseFragment<Fragment<%=name %>Binding>(),IView<UIState,UIEvent> {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: <%=name %>ViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()

    }
    override val layout: Int =  R.layout.fragment_<%= name_snake %>

    private fun setupView() {
    }

    override fun onEvent(event: UIEvent) {
       Log.d(TAG,"event: $event")

    }

    private val TAG = "<%=name %>Fragment"

    override fun render(state: UIState) {
       Log.d(TAG,"render: $state")
    }

}