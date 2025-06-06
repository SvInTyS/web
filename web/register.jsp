<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
</head>
<body>
    <h2>Регистрация</h2>
    <form method="post" action="${pageContext.request.contextPath}/register">
        Логин: <input type="text" name="username"><br>
        Пароль: <input type="password" name="password"><br>
        <input type="submit" value="Зарегистрироваться">
    </form>
</body>
</html>