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
 * Servlet implementation class AppointmentBooking
 */
public class AppointmentBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointmentBooking() {
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
		
		HttpSession httpSession = request.getSession(false);
		String username = httpSession.getAttribute("uname").toString();
		String adate = request.getParameter("bdate");
		String atime = request.getParameter("time");
		String did = request.getParameter("did");
		
		Connection connection = null;
		Statement statement = null;
		int i=0;
		
		String query = "insert into appoint_details values(appseq.nextval,'"+adate+"','new','"+did+"','"+username+"','"+atime+"')";
		
		connection = DataBaseConnectivity.getMyConnection();
		try {
			statement = connection.createStatement();
			i = statement.executeUpdate(query);
			if(i>0)
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("transaction.html");
				dispatcher.include(request, response);	
			}
			else
			{
				response.getWriter().print("booking fail");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
