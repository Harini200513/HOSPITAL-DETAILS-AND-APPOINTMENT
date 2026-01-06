<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.model.DataBaseConnectivity"%>
<%@page import="java.sql.SQLException"%><html>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>view doctors</title>
<script type="text/javascript">
function test(ev)
{
	localStorage.did = ev.id;
	window.location= "bookappointment.html"
}
</script>
<style>
body{background-image:url("./images/iStock_000011596716_Medium.jpg");}
</style>
</head>
<body>
<br><br><br><br><br><br>
<table align="center" border="1">
<tr>
<th>doctor name</th>
<th>doctor qualification</th>
<th>doctor experience</th>
<th>doctor description</th>
<th>doctor timings</th>
<th>book appointment</th>
</tr>
<%
String hid = request.getParameter("hid");

Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;

String query ="select * from doct_details where hosp_id='"+hid+"'";

connection = DataBaseConnectivity.getMyConnection();
try {
	statement = connection.createStatement();
	resultSet = statement.executeQuery(query);
	while(resultSet.next())
	{
%>
<tr>
<td><%=resultSet.getString(2) %></td>
<td><%=resultSet.getString(4) %></td>
<td><%=resultSet.getString(5) %></td>
<td><%=resultSet.getString(6) %></td>
<td><%=resultSet.getString(8) %></td>
<td><input type="button" value="Book appointment"  id="<%=resultSet.getString(1)%>" onclick="test(this)"></input></td>
</tr>


<%		
	}
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
finally
{
	DataBaseConnectivity.closeAll(connection, statement, resultSet);
}
%>
</table>
</body>
</html>