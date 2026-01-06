<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.model.DataBaseConnectivity"%>
<%@page import="java.sql.SQLException"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>appointmentsfordoctor</title>
<center><h1><font color=>Registered Patients</font></h1></center>

</head>
<body>
<table align="center" border="1">
<tr>
<th>appointment date </th>
<th>patient  name</th>
<th>appointment time </th>
</tr>
<%
HttpSession httpSession = request.getSession(false);
String did = httpSession.getAttribute("did").toString();

Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;

String query ="select * from APPOINT_DETAILS where doct_id='"+did+"'";

connection = DataBaseConnectivity.getMyConnection();
try {
	statement = connection.createStatement();
	resultSet = statement.executeQuery(query);
	while(resultSet.next())
	{
%>
<tr>
<td><%=resultSet.getString(2) %></td>
<td><%=resultSet.getString(5) %></td>
<td><%=resultSet.getString(6) %></td>
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