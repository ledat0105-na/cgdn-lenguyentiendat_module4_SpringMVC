<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Kết quả</title>
</head>
<body>
<h2>Kết quả</h2>

<p>A = <b>${form.a}</b></p>
<p>Phép toán = <b>${form.op}</b></p>
<p>B = <b>${form.b}</b></p>

<p style="color:red">${form.error}</p>
<p><b>${form.result}</b></p>

<p><a href="<%= request.getContextPath() %>/">Tính lại</a></p>
</body>
</html>
