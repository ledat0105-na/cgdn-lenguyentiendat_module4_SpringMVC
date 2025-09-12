<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Từ điển Anh - Việt</title>
</head>
<body>
<h2>Từ điển Anh → Việt</h2>
<form action="translate" method="post">
    <label>Nhập từ tiếng Anh:</label>
    <input type="text" name="word" required autofocus/>
    <button type="submit">Tra cứu</button>
</form>
</body>
</html>
