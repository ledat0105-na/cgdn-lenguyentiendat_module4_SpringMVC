<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Updated</title></head>
<body>
<h2>Updated Settings</h2>

<p>Language: <b>${component.language}</b></p>
<p>Page size: <b>${component.pageSize}</b></p>
<p>Spams filter: <b>${component.spamFilter}</b></p>
<p>Signature:</p>
<pre>${component.signature}</pre>

<p><a href="<%= request.getContextPath() %>/">Back</a></p>
</body>
</html>
