package com.ssi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssi.utility.Product;

@WebServlet("/RemoveItem")
public class RemoveItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		String index  = request.getParameter("index");
		HttpSession session = request.getSession(false);
		ArrayList<Product> list= (ArrayList<Product>)session.getAttribute("CartList");
		list.remove(Integer.parseInt(index));
		session.setAttribute("CartList", list);
		PrintWriter out = response.getWriter();
		out.print("Item Removed from cart");
		RequestDispatcher rd = request.getRequestDispatcher("ShowCart.jsp");
		rd.include(request, response);
	}

}
