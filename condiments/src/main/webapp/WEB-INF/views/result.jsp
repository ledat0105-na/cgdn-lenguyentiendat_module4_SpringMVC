<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Kết quả</title></head>
<body>
<h2>Gia vị đã chọn</h2>

<c:choose>
    <c:when test="${empty selection.selected}">
        <p>Bạn chưa chọn gia vị nào.</p>
    </c:when>
    <c:otherwise>
        <ul>
            <c:forEach var="c" items="${selection.selected}">
                <li>${c}</li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>

<p><a href="<c:url value='/'/>">Chọn lại</a></p>
</body>
</html>
