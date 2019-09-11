package com.ssi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssi.utility.DBConnect;

@WebServlet("/UpdateUserProfile")
public class UpdateUserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	
	@Override
	public void init() throws ServletException {
		DBConnect connection = DBConnect.connect(getServletContext());
		try {
			con = (Connection)connection.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("UserId");
		String password = request.getParameter("Password");
		String username = request.getParameter("UserName");
		String address = request.getParameter("Address");
		String mobile = request.getParameter("Mobile");
		String email = request.getParameter("Email");
		response.setContentType("text/html");
		String sql = "update users set userid=?, password=?, username=?, address=?, mobile=?, email=? where userid=?";
		try {
			HttpSession session = request.getSession();
			String user = (String)session.getAttribute("UserId");
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, userid);
			stmt.setString(2, password);
			stmt.setString(3, username);
			stmt.setString(4, address);
			stmt.setString(5, mobile);
			stmt.setString(6, email);
			stmt.setString(7, user);
			stmt.executeUpdate();
			
			PrintWriter out = response.getWriter();
			out.print("Profile Updated successfully !");
			RequestDispatcher rd = request.getRequestDispatcher("UpdateProfile.jsp");
			rd.include(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
