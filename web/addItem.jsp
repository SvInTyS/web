<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="adminHeader.jsp" />
<jsp:include page="header.jsp" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style.css">
    <title>Добавить товар</title>
</head>
<body>
    <h2>Добавление нового товара</h2>
    <form action="addItem" method="post">
        Название: <input type="text" name="name"><br>
        Описание:<br>
        <textarea name="description" rows="4" cols="40"></textarea><br>
        Цена: <input type="text" name="price"><br>
        Путь к изображению (например: static/img1.jpg):<br>
        <input type="text" name="image"><br><br>
        <input type="submit" value="Добавить">
    </form>
    <p><a href="admin">← Назад в админку</a></p>
</body>
</html>