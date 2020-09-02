---
to: <%= module %>/src/main/java/com/justadeveloper96/pokedex/<%= module %>/presentation/<%=h.changeCase.snake(name) %>/viewmodel/I<%=name %>Viewmodel.kt
---
<%
 folder = h.changeCase.snake(name)
 name_snake = h.changeCase.snake(name)
%>
package com.justadeveloper96.pokedex.<%= module %>.presentation.<%=folder %>.viewmodel
import com.justadeveloper96.pokedex.helpers.viewmodel.IViewModel


interface I<%=name %>ViewModel: I<%=name %>Action, IViewModel<UIState,UIEvent>{

}