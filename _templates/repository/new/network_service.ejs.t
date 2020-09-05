---
to:  <%= module %>/src/main/java/com/justadeveloper96/pokedex/<%= module %>/data/<%=h.changeCase.snake(name) %>/repository/network/<%=name %>Api.kt
---
<%
name_snake = h.changeCase.snake(name)
%>
package com.justadeveloper96.pokedex.<%= module %>.data.<%=name_snake %>.repository.network
import com.justadeveloper96.pokedex.<%= module %>.data.<%=name_snake %>.repository.model.<%=name %>
import com.justadeveloper96.pokedex.<%= module %>.data.<%=name_snake %>.repository.network.model.<%=name %>ResponseModel
import com.justadeveloper96.pokedex.<%= module %>.data.<%=name_snake %>.repository.network.mapper.toDomainModel
import com.justadeveloper96.pokedex.helpers.api.NetworkResult
import com.justadeveloper96.pokedex.helpers.extensions.execute
import retrofit2.http.GET
import retrofit2.Retrofit
import javax.inject.Inject

class <%=name %>Api(@Inject private val retrofit:Retrofit): I<%=name %>Api {

    private val service = retrofit.create(IRetrofitService::class.java)

    override suspend fun get():NetworkResult<<%=name %>>{
        return execute({ service.get() },{i-> i.toDomainModel() })
    }

    private interface IRetrofitService{

        companion object {
            const val ENDPOINT = ""
        }

        @GET(ENDPOINT)
        suspend fun get(): <%=name %>ResponseModel

    }
}