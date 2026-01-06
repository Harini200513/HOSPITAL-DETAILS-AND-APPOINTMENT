package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.DataBaseConnectivity;

/**
 * Servlet implementation class PatientRegistrationServlet
 */
public class PatientRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientRegistrationServlet() {
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
		
		String puname = request.getParameter("Username");
		String fname = request.getParameter("Firstname");
		String lname = request.getParameter("Lastname");
		String pwd = request.getParameter("Password");
		String gender = request.getParameter("Gender");
		String emailid = request.getParameter("email");
		String contactno = request.getParameter("Phonenumber");
		String address = request.getParameter("addr");
		String city = request.getParameter("city");
		
		Connection connection = null;
		Statement statement = null;
		int i=0;		
		String query = "insert into user_details values('"+puname+"','"+fname+"','"+lname+"','"+pwd+"','"+gender+"','"+emailid+"','"+contactno+"','"+address+"','"+city+"')";
		
		try {
			connection = DataBaseConnectivity.getMyConnection();
			statement = connection.createStatement();
			i =statement.executeUpdate(query);
			if(i>0)
			{
				response.getWriter().print("patient registration sucess");
				response.sendRedirect("home.html");
			}
			else
			{
				response.getWriter().print("hospital registration fail");	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
