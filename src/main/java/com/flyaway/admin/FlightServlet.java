package com.flyaway.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flyaway.util.DbConnection;
import com.flyaway.util.HtmlCode;


public class FlightServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter printWriter=resp.getWriter();
		System.out.println(req.getServletPath());
		if(req.getServletPath().equals("/addFlight"))
		{
		 printWriter.append(HtmlCode.ADD_FLIGHT);
		}
		 else {
			printWriter.append("<html><body>");
			viewFlight(printWriter);
			  printWriter.append(HtmlCode.ADMIN_DASHBOARD);
			  printWriter.append("</body></html>");
		}
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter printWriter=resp.getWriter();
		//add flight
		  String flightName  = req.getParameter("flight_name");
		  String source = req.getParameter("source");
		  String destination = req.getParameter("destination");
		  int fare = Integer.valueOf(req.getParameter("fare"));
		  System.out.println(req.getParameter("date"));
		String date=req.getParameter("date");
		  if(addFlightDetail(flightName, source, destination, fare, date)>0) {
			  printWriter.append("<html><body>Flight Detail "+ flightName +" added successfully <br>");
			  printWriter.append(HtmlCode.ADMIN_DASHBOARD);
			  printWriter.append("</body></html>");
		  }
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
	
	public int addFlightDetail(String flightName,String source,String destination,int fare,String date) {
		Connection con= DbConnection.getConnection();
		try {
		PreparedStatement preparedStatement	=con.prepareCall("insert into flight(flight_name,source,destination,fare,date) values(?,?,?,?,?)");
	preparedStatement.setString(1,flightName);
	preparedStatement.setString(2,source);
	preparedStatement.setString(3, destination);
	preparedStatement.setInt(4, fare);
	preparedStatement.setString(5, date);
		int res=	preparedStatement.executeUpdate();
		if(res>0) {
			return res;
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fare;
		
	}
	
	public void viewFlight(PrintWriter printWriter) {
		Connection con=DbConnection.getConnection();
		try {
			PreparedStatement preparedStatement	=con.prepareCall("select * from flight");
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				System.out.println(resultSet.getString(2));
				printWriter.append("flight name: "+ resultSet.getString(2)+"<br>");
				printWriter.append("source: "+resultSet.getString(3)+"<br>");
				printWriter.append("destination: "+resultSet.getString(4)+"<br>");
				printWriter.append("fare: "+resultSet.getString(5)+"<br>");
				printWriter.append("travel date: "+resultSet.getString(6)+"<br>");
				printWriter.append("------------------------------------------<br>");
				
			}
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	

}
