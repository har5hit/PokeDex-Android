---
to:  <%= module %>/src/main/java/com/justadeveloper96/pokedex/<%= module %>/data/<%=h.changeCase.snake(name) %>/repository/network/mapper/<%=name %>ResponseModelMapper.kt
---
<%
name_snake = h.changeCase.snake(name)
%>
package com.justadeveloper96.pokedex.<%= module %>.data.<%=name_snake %>.repository.network.mapper

import com.justadeveloper96.pokedex.<%= module %>.data.<%=name_snake %>.repository.network.model.<%=name %>ResponseModel
import com.justadeveloper96.pokedex.<%= module %>.data.<%=name_snake %>.repository.model.<%=name %>

fun <%=name %>ResponseModel.to<%=name %>():<%=name %> {
    return <%=name %>(
        id
    )
}
