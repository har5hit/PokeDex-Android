---
to: <%= module %>/src/test/java/com/justadeveloper96/pokedex/<%= module %>/presentation/<%=h.changeCase.snake(name) %>/viewmodel/<%=name %>ViewModelTest.kt
---
<%
 folder = h.changeCase.snake(name)
 name_snake = h.changeCase.snake(name)
%>
package com.justadeveloper96.pokedex.<%= module %>.presentation.<%=folder %>.viewmodel

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.first


class <%=name %>ViewModelTest: StringSpec({

    val viewModel = <%=name %>ViewModel()

    "inital state test"{
        viewModel.state.first() shouldBe UIState(true)
    }
})