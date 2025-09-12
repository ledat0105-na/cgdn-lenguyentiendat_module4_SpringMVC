<%--
  Created by IntelliJ IDEA.
  User: LEDAT
  Date: 9/12/2025
  Time: 8:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Kết quả tra cứu</title>
</head>
<body>
<h2>Kết quả</h2>

<c:choose>
    <c:when test="${found}">
        <p><b>${word}</b> → <b>${meaning}</b></p>
    </c:when>
    <c:otherwise>
        <p>Không tìm thấy nghĩa cho từ: <b>${word}</b></p>
    </c:otherwise>
</c:choose>

<p><a href="<c:url value='/'/>">Tra từ khác</a></p>
</body>
</html>
