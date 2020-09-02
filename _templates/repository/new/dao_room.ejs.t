---
to:  <%= module %>/src/main/java/com/justadeveloper96/pokedex/<%= module %>/data/<%=h.changeCase.snake(name) %>/repository/dao/I<%=name %>Dao.kt
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


@Dao
interface I<%=name %>Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(list: List<<%=name %>DaoModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: <%=name %>DaoModel)

    @Query("DELETE FROM <%=name %>DaoModel")
    fun removeAll()

}