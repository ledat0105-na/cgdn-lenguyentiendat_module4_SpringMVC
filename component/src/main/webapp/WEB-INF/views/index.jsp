<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Settings</title></head>
<body>
<h2>Settings</h2>

<form:form modelAttribute="component" action="${pageContext.request.contextPath}/update" method="post">
    <p>
        <label>Languages</label><br/>
        <form:select path="language" items="${languages}"/>
    </p>

    <p>
        <label>Page Size</label><br/>
        Show <form:select path="pageSize" items="${pageSizes}"/> emails per page
    </p>

    <p>
        <form:checkbox path="spamFilter"/> Enable spams filter
    </p>

    <p>
        <label>Signature:</label><br/>
        <form:textarea path="signature" rows="4" cols="40"/>
    </p>

    <p>
        <button type="submit">Update</button>
    </p>
</form:form>

<form action="${pageContext.request.contextPath}/cancel" method="post">
    <button type="submit">Cancel</button>
</form>
</body>
</html>
