package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.DataBaseConnectivity;

/**
 * Servlet implementation class AddAccountDeatils
 */
public class AddAccountDeatils extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAccountDeatils() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		String uname = httpSession.getAttribute("uname").toString();
		String accnum = request.getParameter("accnum");
		String accname = request.getParameter("accname");
		String accpin = request.getParameter("accpin");
		String ambal = request.getParameter("mbal");
		String accbal = request.getParameter("cbal");
		String branch = request.getParameter("branch");
		Connection connection = null;
		Statement statement = null;
		
		ResultSet resultSet = null;
		int i;
		
		String query = "insert into ACNT_DETAILS values('"+accnum+"','"+accname+"','"+accpin+"','"+ambal+"','"+accbal+"','"+branch+"')";
		
		connection = DataBaseConnectivity.getMyConnection();
		try {
			connection = DataBaseConnectivity.getMyConnection();
			statement = connection.createStatement();
			i =statement.executeUpdate(query);
			if(i>0)
			{
				response.sendRedirect("patienthome.html");
			}
			else
			{
				response.getWriter().print("addaccountdetails.html");	
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
