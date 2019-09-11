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

import com.ssi.utility.DBConnect;

@WebServlet("/SaveUser")
public class SaveUser extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Connection con;
	private PreparedStatement ps;
	
	
	public void init(){
		DBConnect connection = DBConnect.connect(getServletContext());
		try {
			con = connection.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql="insert into users values(?,?,?,?,?,?)";
		try {
			ps=con.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void destroy(){
		try{
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//reads-request
		String userid=request.getParameter("userid");
		String password=request.getParameter("password");
		String username=request.getParameter("username");
		String address=request.getParameter("address");
		String mobile=request.getParameter("mobile");
		String email=request.getParameter("email");
		//process
		try{
			ps.setString(1, userid);
			ps.setString(2, password);
			ps.setString(3, username);
			ps.setString(4, address);
			ps.setString(5, mobile);
			ps.setString(6, email);
			ps.executeUpdate();
			out.println("Registration Completed");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
		}catch(Exception e){
			out.println(e);
		}
		//provides-response
		
	}

}
