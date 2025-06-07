<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="header.jsp" />
<link rel="stylesheet" href="style.css">


<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Вход</title>
</head>
<body>
    <h2>Вход</h2>
    <form method="post" action="login">
        <label>Логин:</label>
        <input type="text" name="username" required><br> <!-- исправлено -->
        <label>Пароль:</label>
        <input type="password" name="password" required><br>
        <input type="submit" value="Войти">
    </form>

    <p style="color:red;">
        <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
    </p>
</body>
</html>