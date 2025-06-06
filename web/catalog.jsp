<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Каталог</title>
</head>
<body>

<%
    HttpSession session = request.getSession(false);
    String username = (session != null) ? (String) session.getAttribute("username") : null;
    String role = (session != null) ? (String) session.getAttribute("role") : null;

    if (username != null) {
%>
    <p>Вы вошли как: <strong><%= username %></strong> (<%= role %>)</p>
    <form action="logout" method="post">
        <input type="submit" value="Выйти">
    </form>
<%
    } else {
%>
    <p>Вы не авторизованы. <a href="login.jsp">Войти</a></p>
<%
    }
%>
<hr>

<!-- Каталог можно вывести здесь -->

</body>
</html>