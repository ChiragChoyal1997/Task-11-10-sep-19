package com.ssi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssi.utility.DBConnect;

@WebServlet("/BookDetailsServlet")
public class BookDetailsServlet extends HttpServlet {
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
	String sql="SELECT * from books where bcode=?";
	try {
		ps=con.prepareStatement(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String code=request.getParameter("code");
		String bcode = null;
		try{
		ps.setInt(1, Integer.parseInt(code));
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			bcode=rs.getString(1);
			String title=rs.getString(2);
			String author=rs.getString(3);
			String subject=rs.getString(4);
			String price=rs.getString(5);	
			boolean isfound = false;
			Cookie ck[]=request.getCookies();
			int s1=0;
			if(ck!=null) {
				for(Cookie c:ck){
					String name=c.getName();
					if(name.equals(bcode)){
						s1 = Integer.parseInt(c.getValue())+1;
						c.setValue(String.valueOf(Integer.parseInt(c.getValue())+1));
						response.addCookie(c);
						isfound = true;
						break;
						}
					}
					if(!isfound) {
						Cookie c1 = new Cookie(bcode,String.valueOf(1));
						c1.setMaxAge(60*60*24*7);
						response.addCookie(c1);
					}
			}
			else {
				Cookie c2 = new Cookie(bcode,String.valueOf(1));
				c2.setMaxAge(60*60*24*7);
				response.addCookie(c2);
			}
			
			request.setAttribute("BCode", bcode);
			request.setAttribute("Title", title);
			request.setAttribute("Author", author);
			request.setAttribute("Subject", subject);
			int newprice = Integer.parseInt(price);
			request.setAttribute("Price", newprice);
			request.setAttribute("count", s1);
			RequestDispatcher rd = request.getRequestDispatcher("BookDetail.jsp");
			rd.forward(request, response);
//			if(s1<=5) {
//				out.println("<td>"+newprice+"</td>");
//			}
//			else if (s1>5 && s1<10) {
//				out.println("<td>"+(newprice+newprice*0.1)+"</td>");
//			}
//			else {
//				out.println("<td>"+(newprice+newprice*0.2)+"</td>");
//			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
