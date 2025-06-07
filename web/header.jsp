<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    HttpSession session = request.getSession(false);
    String username = (session != null) ? (String) session.getAttribute("username") : null;
    String role = (session != null) ? (String) session.getAttribute("role") : null;
%>

<div style="background-color: #f0f0f0; padding: 10px;">
<% if (username != null) { %>
    <span>Вы вошли как: <strong><%= username %></strong> (<%= role %>)</span>
    <% if ("admin".equals(role)) { %>
        | <a href="admin">🔧 Админ-панель</a>
    <% } %>
    | <form action="logout" method="post" style="display:inline;">
        <input type="submit" value="Выйти">
      </form>
<% } else { %>
    <span>Вы не авторизованы. <a href="login.jsp">Войти</a></span>
<% } %>
</div>
<hr>
