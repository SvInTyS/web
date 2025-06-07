<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="adminHeader.jsp" />
<%@ page import="java.util.List" %>
<%@ page import="catalog.CatalogServlet.Item" %>
<jsp:include page="header.jsp" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Админ-панель</title>
</head>
<body>
    <h2>Администрирование каталога</h2>
    <a href="addItem.jsp">➕ Добавить новый товар</a>
    <hr>

<%
    List<Item> items = (List<Item>) request.getAttribute("items");
    if (items != null) {
        for (Item item : items) {
%>
            <div style="border:1px solid #ccc; padding:10px; margin:10px;">
                <h3><%= item.name %></h3>
                <p><%= item.description %></p>
                <p><strong><%= item.price %></strong> руб.</p>
                <a href="editItem.jsp?id=<%= item.id %>">✏️ Редактировать</a> |
                <a href="deleteItem?id=<%= item.id %>" onclick="return confirm('Удалить этот товар?')">🗑️ Удалить</a>
            </div>
<%
        }
    } else {
%>
    <p>Нет товаров для отображения.</p>
<%
    }
%>
</body>
</html>