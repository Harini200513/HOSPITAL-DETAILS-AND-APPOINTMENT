package com.controller;

import java.io.IOException;
import java.sql.Connection;
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
 * Servlet implementation class FeedBackInsertServlet
 */
public class FeedBackInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedBackInsertServlet() {
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
		String fdesc = request.getParameter("fdesc");
		
		Connection connection = null;
		Statement statement = null;
		String query = "insert into HOSPITAL_REVIEW values(feedseq.nextval,'"+fdesc+"',sysdate,'"+username+"')";
		System.out.println("query is::"+query);

		int i=0;
		connection = DataBaseConnectivity.getMyConnection();
		
		try {
			statement = connection.createStatement();
			i= statement.executeUpdate(query);
			
			if(i>0)
			{
			
				System.out.println(".........if.......");
				response.getWriter().print("feedback sucessfully submitted");
				RequestDispatcher dispatcher = request.getRequestDispatcher("patienthome.html");
				dispatcher.forward(request, response);
				
			}
			else
			{
				response.getWriter().print("feedback insertion fail");
				RequestDispatcher dispatcher = request.getRequestDispatcher("feedback.html");
				dispatcher.include(request, response);				
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
