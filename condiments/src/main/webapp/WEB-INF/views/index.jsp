<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Sandwich Condiments</title></head>
<body>
<h2>Chọn gia vị cho Sandwich</h2>

<form action="<%= request.getContextPath() %>/save" method="post">
    <c:forEach var="item" items="${allCondiments}">
        <label><input type="checkbox" name="condiment" value="${item}"> ${item}</label><br/>
    </c:forEach>
    <br/>
    <button type="submit">Lưu lựa chọn</button>
</form>
</body>
</html>
