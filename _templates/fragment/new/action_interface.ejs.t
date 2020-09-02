---
to: <%= module %>/src/main/java/com/justadeveloper96/pokedex/<%= module %>/presentation/<%=h.changeCase.snake(name) %>/viewmodel/I<%=name %>Action.kt
---
<%
 folder = h.changeCase.snake(name)
 name_snake = h.changeCase.snake(name)
%>
package com.justadeveloper96.pokedex.<%= module %>.presentation.<%=folder %>.viewmodel


interface I<%=name %>Action{

}