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
 * Servlet implementation class SpecializationRetrieveServlet
 */
public class SpecializationRetrieveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpecializationRetrieveServlet() {
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
		Connection connection =null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String query = "select * from SPECIALIZATION";
		
		connection = DataBaseConnectivity.getMyConnection();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			JSONArray array = new JSONArray();
			while(resultSet.next())
			{
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("specid", resultSet.getString(1));
					jsonObject.put("specname", resultSet.getString(2));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				array.put(jsonObject);
			}
			
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("spec", array);
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
