<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%@ page import="java.util.ArrayList" %>
<%@ page import="OrganizerApp2.DBHandler" %>
<%@ page import="OrganizerApp2.OrgEvent" %>
<%@ page import="org.sqlite.*" %>
<%@ page import="java.sql.SQLException" %>
<JarScanner scanManifest="false"/>
<html>
<head>
  <meta charset="UTF-8">
  <title>Events</title>
</head>
<body>

<script type="text/javascript">
  function ValidateDate() {
    var ar = document.getElementById("date");
    var valid = true;
    if (ar.value.length <= 0) {
      alert("Field of date name is empty!")
      valid = false
    }
    ;
    return valid;
  }
</script>
<script type="text/javascript">
  function ValidateDesc() {
    var ar = document.getElementById("desc");
    var valid = true;
    if (ar.value.length <= 0) {
      alert("Field of description is empty!")
      valid = false
    }
    ;
    return valid;
  }
</script>
<h2>Books List</h2>
<p><a href='<c:url value="/createEvent.jsp" />'>Create new</a></p>
<form method="post" action="dateS?action=submit" style="display: inline" onsubmit="return ValidateDate()">
  <label>Find by date</label><br>
  <label>Date:</label>
  <input type="text" name="date" id="date"/>
  <input type="submit" value="Search"/>
</form>
<br>
<br>
<form method="post" action="descS?action=submit" style="display: inline" onsubmit="return ValidateDesc()">
  <label>Find by description</label><br>
  <label>Description:</label>
  <input type="text" name="desc" id = "desc"/>
  <input type="submit" value="Search"/>
</form>
<%
  ArrayList<OrgEvent> orgEvents = new ArrayList<OrgEvent>();
  DBHandler dbHandler;
  try {
    dbHandler = new DBHandler();
    orgEvents.add(new OrgEvent(0,"d","d","25",2));
    orgEvents = dbHandler.getAllEvents();
  } catch (SQLException e) {
    e.printStackTrace();
  }


  pageContext.setAttribute("orgEvents", orgEvents);
%>
<br><br>
<table>
  <tr><th>Name</th><th>Date</th><th>Description</th><th>ID</th><th></th></tr>
  <c:forEach var="orgEvent" items="${orgEvents}">
    <tr><td>${orgEvent.name}</td>
      <td>${orgEvent.date}</td>
      <td>${orgEvent.description}</td>
      <td>${orgEvent.typeID}</td>
      <td>

        <a href='<c:url value="/editEvent.jsp?eventID=${orgEvent.ID}"  />'>Edit</a> |
        <form method="post" action='<c:url value="deleteEventS?action=submit" />' style="display:inline;">
          <input type="hidden" name="id" value="${orgEvent.ID}">
          <input type="submit" value="Delete">
        </form>
      </td></tr>
  </c:forEach>
</table>
</body>
</html>