---
to:  <%= module %>/src/main/java/com/justadeveloper96/pokedex/<%= module %>/data/<%=h.changeCase.snake(name) %>/repository/network/model/<%=name %>ResponseModel.kt
---
<%
name_snake = h.changeCase.snake(name)
%>
package com.justadeveloper96.pokedex.<%= module %>.data.<%=name_snake %>.repository.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey


data class <%=name %>ResponseModel (
        val id: String
)