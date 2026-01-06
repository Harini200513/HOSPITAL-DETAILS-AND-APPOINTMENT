package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.DataBaseConnectivity;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		
		String utype= request.getParameter("usertype");
		String Username = request.getParameter("Username");
		String Password = request.getParameter("Password");
		
		if("admin".equalsIgnoreCase(utype))
		{
			if("admin".equalsIgnoreCase(Username)&&"admin".equalsIgnoreCase(Password))
			{
				System.out.println("in adidjkfhsjk");
				RequestDispatcher dispatcher = request.getRequestDispatcher("adminhome.html");
				dispatcher.include(request, response);
			}
			else
			{
				response.getWriter().print("invalid login credentials");
			}
		}
		if("doctor".equalsIgnoreCase(utype))
		{
			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;
			
			String query ="select * from doct_details where doct_name='"+Username+"' and password ='"+Password+"'";
			
			connection = DataBaseConnectivity.getMyConnection();
			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);
				if(resultSet.next())
				{
					HttpSession httpSession = request.getSession(true);
					httpSession.setAttribute("uname", Username);
					httpSession.setAttribute("did", resultSet.getString(1));
					RequestDispatcher dispatcher = request.getRequestDispatcher("doctorhome.html");
					dispatcher.include(request, response);
				}
				else
				{
					response.getWriter().print("invalid login credentials");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				DataBaseConnectivity.closeAll(connection, statement, resultSet);
			}

		}
		if("patient".equalsIgnoreCase(utype))
		{
			
			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;
			
			String query ="select * from user_details where user_name='"+Username+"' and password ='"+Password+"'";
			
			connection = DataBaseConnectivity.getMyConnection();
			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);
				if(resultSet.next())
				{
					HttpSession httpSession = request.getSession(true);
					httpSession.setAttribute("uname", Username);
					RequestDispatcher dispatcher = request.getRequestDispatcher("patienthome.html");
					dispatcher.include(request, response);
				}
				else
				{
					response.getWriter().print("invalid login credentials");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				DataBaseConnectivity.closeAll(connection, statement, resultSet);
			}

			
		}

		
	}

}
