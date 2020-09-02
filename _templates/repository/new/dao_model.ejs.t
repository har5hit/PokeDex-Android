---
to:  <%= module %>/src/main/java/com/justadeveloper96/pokedex/<%= module %>/data/<%=h.changeCase.snake(name) %>/repository/dao/model/<%=name %>DaoModel.kt
---
<%
name_snake = h.changeCase.snake(name)
%>
package com.justadeveloper96.pokedex.<%= module %>.data.<%=name_snake %>.repository.dao.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class <%=name %>DaoModel (
        @PrimaryKey
        val id: String
)