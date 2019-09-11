package com.ssi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssi.utility.DBConnect;
import com.ssi.utility.Product;

@WebServlet("/CartManager")
public class CartManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Product> cart;
	private Connection con;

	@Override
	public void init() throws ServletException {
		cart = new ArrayList<Product>();
		DBConnect connection = DBConnect.connect(getServletContext());
		try {
			con = connection.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bcode = request.getParameter("BCode");
		String price = request.getParameter("price");
		String sql = "Select TITLE,AUTHOR,SUBJECT from books where bcode="+bcode;
		
		PreparedStatement stmt;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Product item = new Product();
		try {
			item.setBcode(bcode);
			item.setTitle(rs.getString(1));
			item.setAuthor(rs.getString(2));
			item.setSubject(rs.getString(3));
			item.setPrice(price);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		cart.add(item);
		HttpSession session = request.getSession();
		session.setAttribute("CartList", cart);
		response.sendRedirect("SubjectPageServlet");
	}
}
