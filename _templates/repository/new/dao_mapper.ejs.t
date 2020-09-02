---
to:  <%= module %>/src/main/java/com/justadeveloper96/pokedex/<%= module %>/data/<%=h.changeCase.snake(name) %>/repository/dao/mapper/<%=name %>DaoModelMapper.kt
---
<%
name_snake = h.changeCase.snake(name)
%>
package com.justadeveloper96.pokedex.<%= module %>.data.<%=name_snake %>.repository.dao.mapper

import com.justadeveloper96.pokedex.<%= module %>.data.<%=name_snake %>.repository.dao.model.<%=name %>DaoModel
import com.justadeveloper96.pokedex.<%= module %>.data.<%=name_snake %>.repository.model.<%=name %>

fun <%=name %>DaoModel.to<%=name %>():<%=name %> {
    return <%=name %>(
        id
    )
}
