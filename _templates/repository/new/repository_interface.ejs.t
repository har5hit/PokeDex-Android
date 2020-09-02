---
to:  <%= module %>/src/main/java/com/justadeveloper96/pokedex/<%= module %>/data/<%=h.changeCase.snake(name) %>/repository/I<%=name %>Repository.kt
---
<%
name_snake = h.changeCase.snake(name)
%>
package com.justadeveloper96.pokedex.<%= module %>.data.<%=name_snake %>.repository

interface I<%=name %>Repository