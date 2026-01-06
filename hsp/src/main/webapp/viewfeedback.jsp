<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.sql.Connection"%><html>

<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.model.DataBaseConnectivity"%>
<%@page import="java.sql.SQLException"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>view feedback</title>
<style>
a:link {color:black;background-color:rgb(220,220,220);}      /* unvisited link */
a:visited {color:black;}  /* visited link */
a:hover {color:green;}  /* mouse over link */
a:active {color:green;}  /* selected link */
a:link {text-decoration:none;}    /* unvisited link */
a:visited {text-decoration:none;} /* visited link */
a:hover {text-decoration:underline;}   /* mouse over link */
a:active {text-decoration:underline;}  /* selected link */
body{
background-image:url("./images/background-heart-beating-line-medicine-36023802.jpg" ); 
  }</style>
</head>
<body>
<table>
<tr>
<td><a href="hospitalregistration.html"><font size="4">Addhospitals</font></a></td>
<td>&nbsp;&nbsp;<a href="doctorregistration.html"><font size="4">Adddoctors</font></a></td>
<td>&nbsp;&nbsp;<a href="viewfeedback.jsp"><font size="4">view feedback</font></a></td>
<td>&nbsp;&nbsp;<a href="home.html"><font size="4">logout</font></a></td>
<center><h1><font color=green>view feedback</font></h1></center>
</tr>
</table><br></br>
<table align="center" border="1">
<tr>
<th>Feed back description </th>
<th>given date</th>
<th>given by</th>
</tr>
<%


Connection connection =null;
Statement statement = null;
ResultSet resultSet = null;

String query ="select * from HOSPITAL_REVIEW ";

connection = DataBaseConnectivity.getMyConnection();
try {
	statement = connection.createStatement();
	resultSet = statement.executeQuery(query);
	while(resultSet.next())
	{
%>
<tr>
<td><%=resultSet.getString(2) %></td>
<td><%=resultSet.getString(3) %></td>
<td><%=resultSet.getString(4) %></td>
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