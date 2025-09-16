<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Máy tính cá nhân</title>
</head>
<body>
<h2>Máy tính cá nhân</h2>

<form action="<%= request.getContextPath() %>/calc" method="post">
    <label>Số A:</label>
    <input type="number" name="a" step="0.000001" required>
    <br><br>

    <label>Phép toán:</label>
    <select name="op">
        <option value="add">+</option>
        <option value="sub">−</option>
        <option value="mul">×</option>
        <option value="div">÷</option>
    </select>
    <br><br>

    <label>Số B:</label>
    <input type="number" name="b" step="0.000001" required>
    <br><br>

    <button type="submit">Tính</button>
</form>
</body>
</html>
