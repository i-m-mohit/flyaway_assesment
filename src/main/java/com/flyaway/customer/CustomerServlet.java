package com.flyaway.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyaway.admin.Flight;
import com.flyaway.util.DbConnection;
import com.flyaway.util.HtmlCode;


public class CustomerServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// search flight
		System.out.println("kola");
		PrintWriter printWriter = resp.getWriter();
		printWriter.append(HtmlCode.SEARCH_FLIGHT);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter printWriter = resp.getWriter();
		HttpSession session=req.getSession();
		try {
		if(req.getServletPath().equals("/searchFlightCustomer")) {

				String source = req.getParameter("source_airport_code");
				String destination = req.getParameter("destination_airport_code");
				String date = req.getParameter("date");


					List<Flight> list = SearchFlight(req, source, destination, date);
					System.out.println(list.size());
					printWriter.append("<html><head>");
					if(list.size()==0) {
						printWriter.append("<b><center>No flight found</center></b>");
						printWriter.append(HtmlCode.SEARCH_FLIGHT);

					}
					else {
						list.forEach(l -> {
							System.out.println(l.getDestination());
							printWriter.append("<form action=bookFlightView method=post>\r\n"
									+ " <input type=hidden name=flight_id value=" + l.getFlightId() + ">\n"
									+ " <input type=hidden name=fare value=" + l.getFare() + ">\n"
									+ "<label> SOURCE : " + l.getSource().toUpperCase(Locale.ROOT) + "</label><br>"
									+ "<label> DESTINATION : " + l.getDestination().toUpperCase() + "</label>\n <br>"
									+ "<label >Date : " + l.getDate() + "</label>\n<br>"
									+ "<label > Fare : " + l.getFare() + " </label>\r\n<br>"
									+ "<button type=Search Flight  value=Submit>Book Now</button>\r\n<br>"
									+ "			                </form>");
							printWriter.append("--------------------------------------------------");

						});
					}
					printWriter.append("</body></html>");
				}
		else if(req.getServletPath().equals("/bookFlightView"))
		{
			int flight_id = Integer.parseInt(req.getParameter("flight_id"));
			int fare = Integer.parseInt(req.getParameter("fare"));

			session.setAttribute("flight_id",flight_id);
			session.setAttribute("fare",fare);
			printWriter.append(HtmlCode.ADD_PASSENGER);

		}
		else if(req.getServletPath().equals("/bookFlight")) {
			String name = req.getParameter("passenger_name");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			int passenger_size = Integer.valueOf(req.getParameter("passenger_size"));
			int flight_id= (int) session.getAttribute("flight_id");
			int fare=(int)session.getAttribute("fare");
			Customer customer =new Customer(flight_id,name,email,phone,passenger_size,fare*passenger_size);
			session.setAttribute("customer",customer);

			RequestDispatcher requestDispatcher=req.getRequestDispatcher("payment");
			requestDispatcher.forward(req,resp);
		}
		else if(req.getServletPath().equals("/payment")) {
			Customer customer=(Customer) session.getAttribute("customer");
			printWriter.append("<html><body> Make Payment of "+customer.getTotal_fare()+"<br>");
			printWriter.append("-------------------------------------------------<br>");
			printWriter.append(HtmlCode.MAKE_PAYMENT);
		}

		else if(req.getServletPath().equals("/final_booking")) {
			Customer customer=(Customer) session.getAttribute("customer");
			int i = bookFlight(customer.getName(), customer.getEmail(), customer.getPhone(), customer.getPassenger_size(), customer.getTotal_fare(), customer.getFlight_id());
		if(i>0) {
			Flight flight = getFlightById((int)session.getAttribute("flight_id"));
			if(Objects.nonNull(flight)) {


				printWriter.append("<html><body>payment successfully<br>");
				printWriter.append("_______________________________________<br>");
				printWriter.append("Flight Name "+flight.getFlightName()+"<br>");
				printWriter.append("Source "+flight.getSource()+"<br>");
				printWriter.append("Destination "+flight.getDestination()+"<br>");
				printWriter.append("Date "+flight.getDate()+"<br>");
				printWriter.append("Customer Name " + customer.getName() + "<br>");
				printWriter.append("Email " + customer.getEmail() + "<br>");
				printWriter.append("phone " + customer.getPhone() + "<br>");
				printWriter.append("Passenger Size " + customer.getPassenger_size() + "<br>");
				printWriter.append("Total Fare " + customer.getTotal_fare() + "<br>");
				printWriter.append("<button onclick=window.print()>Print this page</button>");
				printWriter.append("</body></html>");
			}

		}
		}


				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	public List<Flight> SearchFlight(HttpServletRequest request, String source, String destination, String date)
			throws SQLException {
		Connection con = DbConnection.getConnection();
		PreparedStatement preparedStatement = con
				.prepareStatement("select * from flight where source=? and destination=? and date=?");
		preparedStatement.setString(2, destination);
		preparedStatement.setString(1, source);
		preparedStatement.setString(3, date);
		ResultSet resultSet = preparedStatement.executeQuery();

		List<Flight> flightList = new ArrayList<>();
		while (resultSet.next()) {
			Flight flight = new Flight();
			flight.setSource(resultSet.getString(3));
			flight.setFlightId(resultSet.getInt(1));
			flight.setDestination(resultSet.getString(4));
			flight.setFare(resultSet.getInt(5));
			flight.setDate(resultSet.getString(6));
			System.out.println(resultSet.getString(6));
			flight.setFlightName(resultSet.getString(2));
			flightList.add(flight);
			

		}
		;
		return flightList;

	}

	public int bookFlight(String name,String email,String phone,int passenger_size,int flight_id,int fare) throws SQLException {
		Connection connection=DbConnection.getConnection();

		PreparedStatement preparedStatement = connection.prepareStatement("insert into flight_book(name,email,phone,passenger_size,fare,flight_id) values(?,?,?,?,?,?)");
	preparedStatement.setString(1,name);
	preparedStatement.setString(2,email);
	preparedStatement.setString(3,phone);
	preparedStatement.setInt(4,passenger_size);
	preparedStatement.setInt(5,fare);
	preparedStatement.setInt(6,flight_id);
		int i =preparedStatement.executeUpdate();
		return i;
	}


	public Flight getFlightById(int id) throws SQLException {
		Connection con = DbConnection.getConnection();
		PreparedStatement preparedStatement = con.prepareStatement("select * from flight where flight_id=?");
		preparedStatement.setInt(1,id);
		ResultSet resultSet = preparedStatement.executeQuery();
   while (resultSet.next()) {
	  Flight flight=new Flight();
	  flight.setDate(resultSet.getString("date"));
	  flight.setFlightName(resultSet.getString("flight_name"));
	  flight.setSource(resultSet.getString("source"));
	  flight.setDestination(resultSet.getString("destination"));
	  return flight;
   }
   return null;
	}


}
