---
to:  <%= module %>/src/main/java/com/justadeveloper96/pokedex/<%= module %>/data/<%=h.changeCase.snake(name) %>/repository/network/I<%=name %>Api.kt
---
<%
name_snake = h.changeCase.snake(name)
%>
package com.justadeveloper96.pokedex.<%= module %>.data.<%=name_snake %>.repository.network

import com.justadeveloper96.pokedex.<%= module %>.data.<%=name_snake %>.repository.model.<%=name %>
import com.justadeveloper96.pokedex.helpers.api.NetworkResult

interface I<%=name %>Api {
    suspend fun get():NetworkResult<<%=name %>>
}