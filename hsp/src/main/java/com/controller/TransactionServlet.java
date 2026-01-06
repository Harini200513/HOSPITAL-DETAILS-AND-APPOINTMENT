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
 * Servlet implementation class TransactionServlet
 */
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionServlet() {
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
		
		HttpSession httpSession = request.getSession(false);
		String uname = httpSession.getAttribute("uname").toString();
		String accnum = request.getParameter("accnum");
		String accname = request.getParameter("accname");
		String accpin = request.getParameter("accpin");
		
		Connection connection = null;
		Statement statement = null;
		Statement statement1 = null;
		Statement statement2 = null;
		Statement statement3 = null;
		ResultSet resultSet = null;
		
		String query = "select * from acnt_details where ACNT_NUMBER='"+accnum+"' and ACNT_HOLDER='"+accname+"' and PIN_NUMBER ='"+accpin+"' and CURENT_BALENCE>100";
		
		connection = DataBaseConnectivity.getMyConnection();
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			if(resultSet.next())
			{
				String query1 = "update ACNT_DETAILS set CURENT_BALENCE=CURENT_BALENCE+100 where ACNT_NUMBER='"+accnum+"'";
				String query2 = "update ACNT_DETAILS set CURENT_BALENCE=CURENT_BALENCE+100 where ACNT_NUMBER='9999'";
				String query3 = "insert into TRANSACTION_DETAILS values (transeq.nextval,'9999','100',sysdate,'"+accnum+"')";
				
				statement1  = connection.createStatement();
				int i = statement1.executeUpdate(query1);
				if(i>0)
				{
					statement2 = connection.createStatement();
					int j = statement2.executeUpdate(query2);
					if(j>0)
					{
						statement3 = connection.createStatement();
						int z = statement3.executeUpdate(query3);
						if(z>0)
						{
							connection.commit();
							response.getWriter().print("Transaction sucess and appointment also sucessfully booked");	
							RequestDispatcher dispatcher = request.getRequestDispatcher("patienthome.html");
							dispatcher.include(request, response);
						}
						else
						{
							response.getWriter().print("invalid credentials or insufficient balance");	
						}
						
						
					}
					else
					{
						response.getWriter().print("invalid credentials or insufficient balance...");	
					}
					
				}
				else
				{
					response.getWriter().print("invalid credentials or insufficient balance..");	
				}
			}
			else
			{
			response.getWriter().print("invalid credentials or insufficient balance.");	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
