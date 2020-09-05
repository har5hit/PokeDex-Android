---
to:  <%= module %>/src/main/java/com/justadeveloper96/pokedex/<%= module %>/data/<%=h.changeCase.snake(name) %>/repository/<%=name %>Repository.kt
---
<%
name_snake = h.changeCase.snake(name)
name_camel = h.changeCase.camel(name)
%>
package com.justadeveloper96.pokedex.<%= module %>.data.<%= name_snake %>.repository

import com.justadeveloper96.pokedex.<%= module %>.data.<%= name_snake %>.repository.dao.I<%=name %>Dao
import com.justadeveloper96.pokedex.<%= module %>.data.<%= name_snake %>.repository.network.I<%=name %>Api
import javax.inject.Inject

class <%=name %>Repository @Inject constructor(
    private val <%= name_camel %>Dao: I<%=name %>Dao,
    private val <%= name_camel %>Api: I<%=name %>Api
) : I<%=name %>Repository {

}