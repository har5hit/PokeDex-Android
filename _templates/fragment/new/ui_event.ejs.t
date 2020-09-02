---
to: <%= module %>/src/main/java/com/justadeveloper96/pokedex/<%= module %>//presentation/<%=h.changeCase.snake(name) %>/viewmodel/UIEvent.kt
---
<%
 folder = h.changeCase.snake(name)
 name_snake = h.changeCase.snake(name)
%>
package com.justadeveloper96.pokedex.<%= module %>.presentation.<%=folder %>.viewmodel

sealed class UIEvent

data class Message(val message: String?) : UIEvent()