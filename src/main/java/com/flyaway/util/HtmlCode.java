package com.flyaway.util;

public class HtmlCode {

    public static final String ADMIN_LOGIN = "<html><body></div>\r\n"
            + "      <form action=adminLoginPost method=post>\r\n"
            + "      <label for=\"inputEmail\">Email address</label>\r\n"
            + "      <input type=\"email\" name=\"emailEntered\" placeholder=\"Enter email\">\r\n"
            + "      <br><small id=\"emailHelp\" Current Email : admin@gmail.com</small>\r\n"
            + "      <label for=\"inputPassword\" >Password</label>\r\n"
            + "      <input type=\"password\" name=\"passwordEntered\" placeholder=\"Password\">\r\n"
            + "      <br><small id=\"passwordHelp\" Current Password : admin ></small>\r\n"
            + "      </div>\r\n"
            + "      <button type=\"submit\" value=\"Submit\">Login</button>\r\n"
            + "      </form>\r\n</body></html>";

    public static final String HOME_PAGE = "<html><body>\r\n"
            + "<a href=adminLogin>Admin Login</a><br>\r\n"
            + "<a href=searchFlight>Search Flight</a>\r\n"
            + "</body></html>";

    public static final String ADMIN_DASHBOARD = "<html><body>" + " <a href=changePassword> Change Password</a><br>"

            + " <a href=addFlight>  add flight </a><br>" + " <a href=viewFlight>  View flight Detail </a>";

    public static final String ADD_FLIGHT = "<html><body>" + "	      <form action=addFlightDetail method=post>\r\n"

            + "		<label for=flight_name>Flight Name</label>\r\n"
            + "		<input type=text\" name=flight_name placeholder='Enter Flight Name'>\r\n"
            + "		<label for=source>Source</label>\r\n"
            + "		<input type=text name=source placeholder= 'Source (Airport Code)'>\r\n"
			+ " 	<label for=destination>Destination</label>\r\n"
            + "		<input type=text name=destination placeholder= 'Destination (Airport Code)'>\r\n"
			+ " 		<label for=fare>Fare</label>\r\n"
            + "		<input type=number name=fare placeholder='Enter Amount'>\r\n"
			+ " 		<label for=date>Date</label>\r\n"
            + "		<input type=date name=date placeholder= date>\r\n"
            + "		<button type=add Flight class=\\\"btn btn-success btn-block\\\" value=\\\"Submit\\\">Add Flight</button>\r\n"
            + "		</form></body></html>";

    public static final String SEARCH_FLIGHT = "<html><body>"
            + "	<form action=searchFlightCustomer method=post>\r\n"
            + "	<label for=source>source</label>\r\n"
            + "	<input type=text name=source_airport_code placeholder='FROM (Airport Code)'>\r\n"
            + "	<label for=source>Destination</label>\r\n"
            + " <input type=text name=destination_airport_code placeholder='TO (Airport Code)'>\r\n"
            + "	<input type=date name=date placeholder= date>\r\n"
            + "	<button type=Search Flight class=\\\"btn btn-success btn-block\\\" value=Submit>Search Flight</button>\r\n"
            + "	</form></body></html>";


    public static final String ADD_PASSENGER = "<html><body>   <form action=bookFlight method=post>\r\n" +
            "           <label for=passenger name>Passenger Name</label>\r\n" +
            "           <input type=text name=passenger_name placeholder='Enter Name'>\r\n" +
            "\t			<label for=email>Email</label>\n" +
            "\t         <input type=email name=email placeholder='Enter Email'>\r\n" +
            "\t			<label for=phone>Phone Number</label>\n" +
            "\t         <input type=text name=phone placeholder='Enter Phone No'>\r\n" +
            "\t			<label for=phone>No of Passengers</label>\n" +
            "\t         <input type=number name=passenger_size placeholder= 'No of Travellers '>\r\n" +
            "\t         <button type=Book Flight value=Submit>Book Flight</button>\r\n" +
            "\t      	</form></body></html>";


    public static final String MAKE_PAYMENT = "<html><body>   <form action=final_booking method=post>\n"

            +	"\t               <label for=card name>Card number \t </label>\n"
            +	"\t              <input type=text name=card_number  placeholder= 'XXXX-XXXX-XXXX-XXXX' required=true>\n<br>"
			+ 	"\t			<label for=expiry_date>Expiry Date </label>\n"
			+ 	"\t			<input type=text name=expiry_date placeholder= 'MM/YY' required=true>\n"
			+ 	"\t			<label for=customer name>CVV </label>\n"
			+ 	"\t            <input type=number name=cvv placeholder='XXX' required=true><br>\n"
			+	"\t				<label for=customer name>Card Holder Name </label>\n"
			+ 	"\t   		<input type=customer name=customer placeholder='Card Holder Name' required=true>\n<br>"
			+ 	"\t         <button type= Make Payment value=Submit>Make Payment</button>\n"
			+ 	"\t                </form></body></html>";


	public static final String CHANGE_PASSWORD="<html><body></div>\r\n"
			+ "      <form action=changePasswordFinal method=put>\r\n"
			+ "      <label for=\"inputEmail\">Password </label>\r\n"
			+ "      <input type=\"password\" name=\"password\" placeholder=\"Enter Old Password\">\r\n"

			+ "      <label for=\"inputPassword\" >New Password</label>\r\n"
			+ "      <input type=\"password\" name=\"new_password\" placeholder=\"Password\">\r\n"
			+ "      <button type=\"submit\" value=\"Submit\">update Password</button>\r\n"
			+ "      </form>\r\n</body></html>";


}
