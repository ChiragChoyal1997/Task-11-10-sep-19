package com.ssi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssi.utility.DBConnect;

public class VerifyUser extends HttpServlet {
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
		
		String sql="SELECT username FROM USERS where userid=? AND password=?";
		try {
			ps=con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out=response.getWriter();
		String userid=request.getParameter("userid");
		String password=request.getParameter("password");
		String utype=request.getParameter("utype");
		ServletConfig config = this.getServletConfig();
		try{
			if(utype.equals("owner")){
				if(userid.equals(config.getInitParameter("UserAdmin")) && password.equals(config.getInitParameter("AdminPass"))){
					response.sendRedirect("adminpage.jsp");
				}else{
					out.println("INVALID CREDENTIALS FOR ADMIN");
				}
				
			}else{
				ps.setString(1,userid);
				ps.setString(2,password);
				ResultSet rs=ps.executeQuery();
				if(rs.next()){
					//whether user want to save the password
					String choice=request.getParameter("save");
					if(choice!=null){
						
						Cookie c1=new Cookie("id",userid);
						Cookie c2=new Cookie("pw", password);
						
						c1.setMaxAge(60*60*24*7);
						c2.setMaxAge(60*60*24*7);
						
						response.addCookie(c1);
						response.addCookie(c2);
						
						HttpSession session = request.getSession();
						session.setAttribute("UserId", userid);
					}				
					response.sendRedirect("buyerpage.jsp");				
				}else{
					out.println("INVALID BUYER CREDENTIALS");
				}
			}
		}catch(Exception e){
			out.println(e);
		}
	}
}
