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

import com.model.DataBaseConnectivity;

/**
 * Servlet implementation class HospitalRegistrationServlet
 */
public class HospitalRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HospitalRegistrationServlet() {
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
		
		String hname = request.getParameter("hname");
		String hadd = request.getParameter("hadd");
		String hdesc = request.getParameter("hdesc");
		String hcon = request.getParameter("hcon");
		String city = request.getParameter("city");
		String spec = request.getParameter("spec");
		
		Connection connection = null;
		Statement statement = null;
		int i=0;		
		String query = "insert into hosp_details values(hosseq.nextval, '"+hname+"','"+hadd+"','"+hdesc+"','"+hcon+"',sysdate,'"+spec+"','"+city+"')";
		
		
		try {
			connection = DataBaseConnectivity.getMyConnection();
			statement = connection.createStatement();
			i =statement.executeUpdate(query);
			if(i>0)
			{
				response.getWriter().print("hospital registration sucess");
				response.sendRedirect("adminhome.html");
			}
			else
			{
				response.getWriter().print("hospital registration fail");	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally
		{
			DataBaseConnectivity.closeAll(connection, statement, null);
		}
		
		
		
		
		
	}

}
