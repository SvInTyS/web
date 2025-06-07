<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, model.Item" %>
<%@ page import="catalog.CatalogServlet.Item" %>
<jsp:include page="header.jsp" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Каталог</title>
</head>
<body>
<h2>Каталог товаров</h2>

<%
    List<Item> items = (List<Item>) request.getAttribute("items");
    if (items != null) {
        for (Item item : items) {
%>
            <div style="border:1px solid #ccc; padding:10px; margin:10px;">
                <h3><%= item.name %></h3>
                <p><%= item.description %></p>
                <p>Цена: <strong><%= item.price %></strong> руб.</p>
                <img src="<%= item.image %>" alt="Товар" width="200"><br>
                <a href="item?id=<%= item.id %>">Подробнее</a>
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