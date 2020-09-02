---
to: <%= module %>/src/main/java/com/justadeveloper96/pokedex/<%= module %>/presentation/<%=h.changeCase.snake(name) %>/viewmodel/<%=name %>ViewModel.kt
---
<%
 folder = h.changeCase.snake(name)
 name_snake = h.changeCase.snake(name)
%>
package com.justadeveloper96.pokedex.<%= module %>.presentation.<%=folder %>.viewmodel

import javax.inject.Inject
import com.justadeveloper96.pokedex.helpers.viewmodel.BaseViewModel

class <%=name %>ViewModel @Inject constructor():BaseViewModel<UIState, UIEvent>(), I<%=name %>ViewModel{
    override val initialState
            get() = UIState(true)
}