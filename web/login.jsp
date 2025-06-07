<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>



<html>
<head>
    <title>Вход</title>
</head>
<body>
    <h2>Вход</h2>
    <form method="post" action="login">
        <label>Логин:</label>
        <input type="text" name="login" required><br>
        <label>Пароль:</label>
        <input type="password" name="password" required><br>
        <input type="submit" value="Войти">
    </form>

    <p style="color:red;">
        <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
    </p>
</body>
</html>