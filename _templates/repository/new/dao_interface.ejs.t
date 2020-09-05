---
to:  <%= module %>/src/main/java/com/justadeveloper96/pokedex/<%= module %>/data/<%=h.changeCase.snake(name) %>/repository/dao/I<%=name %>Dao.kt
---
<%
name_snake = h.changeCase.snake(name)
%>
package com.justadeveloper96.pokedex.<%= module %>.data.<%=name_snake %>.repository.dao
import com.justadeveloper96.pokedex.<%= module %>.data.<%= name_snake %>.repository.model.<%=name %>
import kotlinx.coroutines.flow.Flow


interface I<%=name %>Dao {

    fun insert(list: List<<%=name %>>)

    fun insert(item: <%=name %>)

    fun all(): Flow<List<<%=name %>>>

    fun removeAll()

}