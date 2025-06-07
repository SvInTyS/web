<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, model.Item" %>
<jsp:include page="header.jsp" />


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style.css">
    <title>Каталог</title>
</head>
<body>
<h2>Каталог товаров</h2>

<%
    List<Item> items = (List<Item>) request.getAttribute("items");
    if (items != null) {
        for (Item item : items) {
%>
            <div style="border:1px solid #ccc; padding:10px; margin:10px; width:300px; float:left;">
                    <img src="<%= item.getImage() %>" width="100%" alt="Изображение"><br>
                    <h3><%= item.getName() %></h3>
                    <p><%= item.getDescription() %></p>
                    <p><strong>Цена:</strong> <%= item.getPrice() %> руб.</p>
                    <a href="item?id=<%= item.getId() %>">Подробнее</a>
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