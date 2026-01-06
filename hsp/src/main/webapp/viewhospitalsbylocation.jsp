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
<title>viewhospital</title>
<center><img src="images/image8.jpg" width="50%" height="200"></center>
<script type="text/javascript">
function test(ev)
{
	localStorage.hid = ev.id;
	window.location = "confirmtoshowdoctor.html";
}

</script>
<link href="css/ddmenu.css" rel="stylesheet" type="text/css" />
    <script src="js/ddmenu.js" type="text/javascript"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
a:link {color:black;background-color:rgb(220,220,220);}      /* unvisited link */
a:visited {color:black;}  /* visited link */
a:hover {color:green;}  /* mouse over link */
a:active {color:green;}  /* selected link */
a:link {text-decoration:none;}    /* unvisited link */
a:visited {text-decoration:none;} /* visited link */
a:hover {text-decoration:none;}   /* mouse over link */
a:active {text-decoration:underline;}  /* selected link */
body{background-image:url("./images/19159207-Rays-of-light-in-the-corridor-of-the-hospital--Stock-Photo-hospital-medical-background.jpg");}
</style>
</head>
<marquee><h2><font color=green>select your preferences</font></h2></marquee>
<br></br>
<body>
<nav id="ddmenu">

    <ul>
     
	 <li><a href="serachdoctorsbylocation.html"><b>Serach hospitals</b></a></li>
	 <li><a href="feedback.html"><b>feedback</b></a></li>
	 <li><a href="home.html"><b>logout</b></a></li>
		
</ul>
</nav><br>
<br>
<table align="center" border="1">
<tr>
<th>hospital name</th>
<th>hospital address</th>
<th>hospital description</th>
<th>contact number</th>
<th>view doctors</th>
</tr>
<%
String cityid = request.getParameter("city");

Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;

String query ="select * from hosp_details where city_id='"+cityid+"'";

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
<td><%=resultSet.getString(5) %></td>
<td><input type="button" value="viewdoctors"  id="<%=resultSet.getString(1)%>" onclick="test(this)"></input></td>
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