package com.salesRep;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DBconnection;

@WebServlet("/submitStock")
public class Stock extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
	Connection c;
	PreparedStatement ps;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pname = request.getParameter("pname");
		String pquantity = request.getParameter("quantity");
		String option = request.getParameter("option");
		response.setContentType("text/html");
		try {
			c=DBconnection.getConnection();
			if(option.equals("add")) {
				ps=c.prepareStatement("insert into Stock values(?,?)");
				ps.setString(1,pname);
				ps.setString(2,pquantity);
				ps.execute();
				response.sendRedirect("success.html");
			}else if(option.equals("delete")) {
				ps=c.prepareStatement("delete from Stock where productname=?");
				ps.setString(1, pname);
				ps.execute();
				response.sendRedirect("delete.html");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
