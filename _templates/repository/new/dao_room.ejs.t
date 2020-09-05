---
to:  <%= module %>/src/main/java/com/justadeveloper96/pokedex/<%= module %>/data/<%=h.changeCase.snake(name) %>/repository/dao/<%=name %>Dao.kt
---
<%
name_snake = h.changeCase.snake(name)
%>
package com.justadeveloper96.pokedex.<%= module %>.data.<%=name_snake %>.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.justadeveloper96.pokedex.<%= module %>.data.<%= name_snake %>.repository.dao.model.<%=name %>DaoModel
import com.justadeveloper96.pokedex.<%= module %>.data.<%= name_snake %>.repository.dao.mapper.toDaoModel
import com.justadeveloper96.pokedex.<%= module %>.data.<%= name_snake %>.repository.dao.mapper.toDomainModel
import com.justadeveloper96.pokedex.<%= module %>.data.<%= name_snake %>.repository.model.<%=name %>
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


@Dao
interface <%=name %>Dao:I<%=name %>Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun _insert(list: List<<%=name %>DaoModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun _insert(item: <%=name %>DaoModel)

    @Query("SELECT * FROM <%=name %>DaoModel")
    fun _all(): Flow<List<<%=name %>DaoModel>>

    @Query("DELETE FROM <%=name %>DaoModel")
    override fun removeAll()

    override fun insert(list: List<<%=name %>>) {
        _insert(list.map { it.toDaoModel() })
    }

    override fun insert(item: <%=name %>) {
        _insert(item.toDaoModel())
    }


    override fun all(): Flow<List<<%=name %>>> {
        return _all().map { it.map { it.toDomainModel() } }
    }

}