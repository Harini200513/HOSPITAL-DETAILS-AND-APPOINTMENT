package com.controller;

import gvjava.org.json.JSONArray;
import gvjava.org.json.JSONException;
import gvjava.org.json.JSONObject;

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

import com.model.DataBaseConnectivity;
import com.model.MessageUtility;

/**
 * Servlet implementation class ForgotPasswordServlet
 */
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPasswordServlet() {
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
		
		String username = request.getParameter("uname");
		System.out.println(" username is:::" + username);
		
		
		
		Connection connection=null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String query = "select CONTACT_NO,PASSWORD from USER_DETAILS where USER_NAME='"+username+"'" ;
		
		connection = DataBaseConnectivity.getMyConnection();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			
			if(resultSet.next())
			{
				response.getWriter().print("your password is sent to your mobile number");
			//	MessageUtility.sendPassword(resultSet.getString(1), resultSet.getString(2));
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
				dispatcher.include(request, response);
			}
			
			else
			{
				response.getWriter().print("invalid credentials");
				RequestDispatcher dispatcher = request.getRequestDispatcher("forgotpassword.html");
				dispatcher.include(request, response);
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
