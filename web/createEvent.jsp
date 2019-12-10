<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Event</title>
</head>
<body>

<h3>New Event</h3>
<form method="post" action="createEventS?action=submit">
    <label>Name</label><br>
    <input name="name"/><br><br>
    <label>Date</label><br>
    <input name="date"/><br><br>
    <label>Description</label><br>
    <input name="description"/><br><br>
    <label>typeID</label><br>
    <input name="typeID" type="number" /><br><br>
    <button type="submit">Save</button>
</form>
<p><a href="index.jsp">Back</a></p>
</body>
</html>
