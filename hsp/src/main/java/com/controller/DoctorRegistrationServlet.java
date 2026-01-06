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
 * Servlet implementation class DoctorRegistrationServlet
 */
public class DoctorRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorRegistrationServlet() {
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
		

		String dname = request.getParameter("dname");
		String dpass = request.getParameter("dpass");
		String dqual = request.getParameter("dqual");
		String dexp = request.getParameter("dexp");
		String ddesc = request.getParameter("ddesc");
		String hospid = request.getParameter("hos");
		String times = request.getParameter("times");
		
		Connection connection = null;
		Statement statement = null;
		int i=0;		
		String query = "insert into doct_details values(hosseq.nextval, '"+dname+"','"+dpass+"','"+dqual+"','"+dexp+"','"+ddesc+"','"+hospid+"','"+times+"')";
		System.out.println("query is:::::"+query);
		
		
		try {
			connection = DataBaseConnectivity.getMyConnection();
			statement = connection.createStatement();
			i =statement.executeUpdate(query);
			if(i>0)
			{
				response.getWriter().print("doctor registration sucess");
				response.sendRedirect("adminhome.html");
			}
			else
			{
				response.getWriter().print("doctor registration fail");	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
