<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Item" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= ((Item)request.getAttribute("item")).getName() %></title>
</head>
<body>
    <%
        Item item = (Item) request.getAttribute("item");
        if (item != null) {
    %>
        <h2><%= item.getName() %></h2>
        <img src="<%= item.getImage() %>" width="300" alt="Изображение"><br>
        <p><%= item.getDescription() %></p>
        <p><strong>Цена:</strong> <%= item.getPrice() %> руб.</p>
    <%
        } else {
    %>
        <p>Товар не найден.</p>
    <%
        }
    %>

    <a href="catalog">← Назад в каталог</a>
</body>
</html>
