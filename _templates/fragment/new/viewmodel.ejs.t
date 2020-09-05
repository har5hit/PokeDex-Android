---
to: <%= module %>/src/main/java/com/justadeveloper96/pokedex/<%= module %>/presentation/<%=h.changeCase.snake(name) %>/viewmodel/<%=name %>ViewModel.kt
---
<%
 folder = h.changeCase.snake(name)
 name_snake = h.changeCase.snake(name)
%>
package com.justadeveloper96.pokedex.<%= module %>.presentation.<%=folder %>.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import kotlinx.coroutines.CoroutineDispatcher
import com.justadeveloper96.pokedex.helpers.viewmodel.BaseViewModel

class <%=name %>ViewModel @ViewModelInject constructor(
    private val coroutineDispatcher: CoroutineDispatcher
):BaseViewModel<UIState, UIEvent>(), I<%=name %>ViewModel{
    override val initialState
            get() = UIState(true)
}