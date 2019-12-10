<%@ page import="OrganizerApp2.OrgEvent" %>
<%@ page import="OrganizerApp2.DBHandler" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit event</title>
</head>
<body>
<%
    int id = Integer.parseInt(request.getParameter("eventID").toString());
    try {
        DBHandler dbHandler = new DBHandler();
        OrgEvent orgEvent = dbHandler.findEventByID(id);
        if(orgEvent != null)
        {
            pageContext.setAttribute("orgEvent", orgEvent);

        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
%>
<h3>Edit orgEvent</h3>
<form method="post" action="editEventS?action=submit">
    <input type="hidden" value="${orgEvent.ID}" name="id" />
    <label>Name</label><br>
    <input name="name" value="${orgEvent.name}"/><br><br>
    <label>Date</label><br>
    <input name="date" value="${orgEvent.date}"/><br><br>
    <label>Description</label><br>
    <input name="desc" value="${orgEvent.description}"/><br><br>
    <label>TypeID</label><br>
    <input name="typeID" type="number" value="${orgEvent.typeID}"/><br><br>
    <button type="submit">Save</button>
</form>
<p><a href="index.jsp">Back</a></p>
</body>
</html>
