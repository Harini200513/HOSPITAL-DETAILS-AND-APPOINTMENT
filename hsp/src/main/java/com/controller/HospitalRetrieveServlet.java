package com.controller;

import gvjava.org.json.JSONArray;
import gvjava.org.json.JSONException;
import gvjava.org.json.JSONObject;

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
 * Servlet implementation class HospitalRetrieveServlet
 */
public class HospitalRetrieveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HospitalRetrieveServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String s = request.getParameter("s");
	Connection connection =null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	String query = "select * from hosp_details where spec_id='"+s+"'";
	
	connection = DataBaseConnectivity.getMyConnection();
	try {
		statement = connection.createStatement();
		resultSet = statement.executeQuery(query);
		
		JSONArray array = new JSONArray();
		while(resultSet.next())
		{
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("hosid", resultSet.getString(1));
				jsonObject.put("hosname", resultSet.getString(2));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			array.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("hos", array);
			response.getWriter().print(jsonObject);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
