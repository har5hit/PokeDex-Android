---
to:  <%= module %>/src/main/java/com/justadeveloper96/pokedex/<%= module %>/data/<%=h.changeCase.snake(name) %>/repository/model/<%=name %>.kt
---
<%
name_snake = h.changeCase.snake(name)
%>
package com.justadeveloper96.pokedex.<%= module %>.data.<%=name_snake %>.repository.model

data class <%=name %>(val id:String)
