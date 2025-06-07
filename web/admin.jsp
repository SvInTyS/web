<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Item" %> <%-- ← изменено с catalog.CatalogServlet.Item на model.Item --%>
<jsp:include page="header.jsp" />


<%
    String role = (String) session.getAttribute("role");
    if (role == null || !"admin".equals(role)) {
        response.sendRedirect("catalog.jsp");
        return;
    }
%>

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
                <h3><%= item.getName() %></h3>
                <p><%= item.getDescription() %></p>
                <p><strong><%= item.getPrice() %></strong> руб.</p>
                <a href="editItem.jsp?id=<%= item.getId() %>">✏️ Редактировать</a> |
                <a href="deleteItem?id=<%= item.getId() %>" onclick="return confirm('Удалить этот товар?')">🗑️ Удалить</a>
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
