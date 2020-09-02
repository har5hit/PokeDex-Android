---
to: <%= module %>/src/main/res/layout/fragment_<%= h.changeCase.snake(name) %>.xml
---
<?xml version="1.0" encoding="utf-8"?>
<%
 folder = h.changeCase.snake(name)
 name_snake = h.changeCase.snake(name)
%>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">
    <FrameLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".presentation.<%= folder %>.fragment.<%= name %>Fragment">
        <TextView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:text="Hello" />

    </FrameLayout>
</layout>
