<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("role");
%>

<div style="background-color: #f0f0f0; padding: 10px;">
    <% if (username != null) { %>
        <span>Вы вошли как: <strong><%= username %></strong> (<%= role %>)</span>
        | <a href="<%=request.getContextPath()%>/catalog">🏠 Главная</a>

        <% if ("admin".equals(role)) { %>
            | <a href="<%=request.getContextPath()%>/admin">🔧 Админ-панель</a>
        <% } %>

        | <form action="<%=request.getContextPath()%>/logout" method="post" style="display:inline;">
            <input type="submit" value="Выйти">
          </form>
    <% } else { %>
        <span>Вы не авторизованы. <a href="<%=request.getContextPath()%>/login.jsp">Войти</a> или <a href="<%=request.getContextPath()%>/register.jsp">Зарегистрироваться</a></span>
    <% } %>
</div>
<hr>