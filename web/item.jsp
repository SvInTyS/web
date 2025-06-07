<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="header.jsp" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style.css">
    <title><%= request.getAttribute("name") %></title>
</head>
<body>
    <h2><%= request.getAttribute("name") %></h2>
    <img src="<%= request.getAttribute("image") %>" width="300" alt="Изображение"><br>
    <p><%= request.getAttribute("description") %></p>
    <p><strong>Цена:</strong> <%= request.getAttribute("price") %> руб.</p>
    <a href="catalog">← Назад в каталог</a>
</body>
</html>
