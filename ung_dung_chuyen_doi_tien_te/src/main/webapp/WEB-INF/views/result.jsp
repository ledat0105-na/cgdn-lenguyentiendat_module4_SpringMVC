<%--
  Created by IntelliJ IDEA.
  User: LEDAT
  Date: 9/12/2025
  Time: 8:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Kết quả</title></head>
<body>
<h2>Kết quả chuyển đổi</h2>

<p>Tỉ giá cần đổi: <b>${conversion.rate} VND</b></p>
<p>Số tiền đổi sang USD: <b>${conversion.usd} USD</b></p>
<p>Số tiền VNĐ: <b>${conversion.vnd} VND</b></p>

<p><a href="<%= request.getContextPath() %>/">Tính lại</a></p>

</body>
</html>
