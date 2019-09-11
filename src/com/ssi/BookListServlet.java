package com.ssi;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssi.utility.DBConnect;

@WebServlet("/BookListServlet")
public class BookListServlet extends HttpServlet {
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
			String sql="SELECT bcode,title from books where subject=?";
			try {
				ps=con.prepareStatement(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String subject=request.getParameter("subject");
		
		PrintWriter out=response.getWriter();
		try{
		ps.setString(1, subject);
		ResultSet rs=ps.executeQuery();
		out.println("<html>");
		out.println("<html><body>");
		out.println("<h3>Select The Desired Title</h3>");
		out.println("<hr>");
		while(rs.next()){
			String code=rs.getString(1);
			String title=rs.getString(2);
			
			out.println("<a href=BookDetailsServlet?code="+code+">");
			out.println(title);
			out.println("</a><br>");
		}
		out.println("<hr>");
		out.println("<a href=SubjectPageServlet>Subject-Page</a>");
		out.println("<a href=buyerpage.jsp>Buyer-Page</a>");
		out.println("</body></html>");
		subject = subject.replace(" ", "_");
		Cookie c1 = new Cookie("subjectchoice",subject); 
		c1.setMaxAge(60*60*24*7);
		response.addCookie(c1);
		}catch(Exception e){
			out.println(e);
		}
	}
}