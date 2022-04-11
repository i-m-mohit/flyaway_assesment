package com.flyaway.admin;

import com.flyaway.util.DbConnection;
import com.flyaway.util.HtmlCode;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AdminServlet extends HttpServlet  {
public String path="";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter printWriter=resp.getWriter();
	
			
					printWriter.append(HtmlCode.ADMIN_LOGIN);
		   
		   
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
     //http://localhost:8080/MOHIT_FlyAway/adminLogin
		 System.out.println(req.getServletPath());		
        String email = req.getParameter("emailEntered");
        String pass = req.getParameter("passwordEntered");
        
        if(isValidAdmin(req,email, pass)) {
        path="adminDashBoard";
resp.sendRedirect(path);
        }
        else {
        	PrintWriter printWriter=resp.getWriter();
        	printWriter.append("<html><body>invalid username or password");
        	printWriter.append(HtmlCode.ADMIN_LOGIN);
			printWriter.append("</body></html>");
        	
        
          }
        
        
        
		
	}
	
	public boolean isValidAdmin(HttpServletRequest httpServletRequest,String email,String pass) {
		Connection connection= DbConnection.getConnection();
		try {
		PreparedStatement preparedStatement=connection.prepareStatement("select * from flyaway.admin where email= ? and password= ?");
	preparedStatement.setString(1, email);
	preparedStatement.setString(2, pass);
		
	ResultSet set=preparedStatement.executeQuery();
int result=	0;
while(set.next()){
   result++;
	HttpSession httpSession=httpServletRequest.getSession();
	httpSession.setAttribute("user_id",set.getInt(1));
}
              if(result>0) {
				
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}


}
