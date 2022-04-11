package com.flyaway.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flyaway.util.DbConnection;
import com.flyaway.util.HtmlCode;


public class AdminDashboardServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter printWriter=resp.getWriter();
		if(req.getServletPath().equals("/changePassword")) {
			System.out.println("update password");
			printWriter.append(HtmlCode.CHANGE_PASSWORD);
		}
		else if(req.getServletPath().equals("/changePasswordFinal")) {

			System.out.println("updating password");
			String password=req.getParameter("password");
			String new_password=req.getParameter("new_password");
			int user_id = (Integer)req.getSession().getAttribute("user_id");
			try {
				if(updatePassword(password,new_password,user_id)>0) {
					printWriter.append("<html><body>password updated successfully<br>");
					printWriter.append(HtmlCode.ADMIN_DASHBOARD);
					printWriter.append("</body></html>");
				}
				else {
					printWriter.append("failed to  update successfully");
					printWriter.append(HtmlCode.ADMIN_DASHBOARD);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			printWriter.append(HtmlCode.ADMIN_DASHBOARD);
		}
	}




	public int updatePassword(String password,String newPassword,int id) throws SQLException {
		Connection connection= DbConnection.getConnection();
		System.out.println(id);
		PreparedStatement preparedStatement = connection.prepareStatement("update admin set password=? where password=? and id=?");
		preparedStatement.setString(1,newPassword);
		preparedStatement.setString(2,password);
		preparedStatement.setInt(3,id);
		return preparedStatement.executeUpdate();
	}
	
	
	
	

}
