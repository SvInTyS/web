<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="header.jsp" />
<%@ page import="model.Item" %>
<link rel="stylesheet" href="style.css">


<%
    Item item = (Item) request.getAttribute("item");
%>

<h2>Редактирование товара</h2>
<form method="post" action="editItem">
    <input type="hidden" name="id" value="<%= item.getId() %>">
    Название: <input type="text" name="name" value="<%= item.getName() %>" required><br>
    Описание:<br><textarea name="description" required><%= item.getDescription() %></textarea><br>
    Цена: <input type="number" name="price" step="0.01" value="<%= item.getPrice() %>" required><br>
    Ссылка на изображение: <input type="text" name="image" value="<%= item.getImage() %>" required><br>
    <input type="submit" value="Сохранить">
</form>