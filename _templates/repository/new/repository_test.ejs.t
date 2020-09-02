---
to: <%= module %>/src/test/java/com/justadeveloper96/pokedex/<%= module %>/data/<%=h.changeCase.snake(name) %>/repository/<%=name %>RepositoryTest.kt
---
<%
 folder = h.changeCase.snake(name)
 name_snake = h.changeCase.snake(name)
%>
package com.justadeveloper96.pokedex.<%= module %>.data.<%=folder %>.repository

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.first


class <%=name %>RepositoryTest: StringSpec({

    val repository = <%=name %>Repository()

    "test"{

    }
})