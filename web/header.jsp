<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div style="background-color: #f0f0f0; padding: 10px;">
    <% if (username != null) { %>
        <span>Вы вошли как: <strong><%= username %></strong> (<%= role %>)</span>
        | <a href="<%=request.getContextPath()%>/catalog.jsp">🏠 Главная</a>
        <% if ("admin".equals(role)) { %>
            | <a href="<%=request.getContextPath()%>/admin">🔧 Админ-панель</a>
        <% } %>
        | <form action="<%=request.getContextPath()%>/logout" method="post" style="display:inline;">
            <input type="submit" value="Выйти">
          </form>
    <% } else { %>
        <span>Вы не авторизованы. <a href="<%=request.getContextPath()%>/login.jsp">Войти</a></span>
    <% } %>
</div>
<hr>