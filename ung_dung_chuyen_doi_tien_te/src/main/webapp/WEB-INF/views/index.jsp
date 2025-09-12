<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Currency Converter</title>
</head>
<body>
<h2>Chuyển đổi USD → VND</h2>

<form action="<%= request.getContextPath() %>/convert" method="post">
    <label>Tỉ giá của VND sang USD:</label>
    <input type="number" step="0.01" min="0" name="rate" required>
    <br><br>

    <label>Số tiền USD:</label>
    <input type="number" step="0.01" min="0" name="usd" required>
    <br><br>

    <button type="submit">Chuyển đổi</button>
</form>
</body>
</html>
