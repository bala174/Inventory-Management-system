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

@WebServlet("/submitCustomer")
public class Customer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
	Connection c;
	PreparedStatement ps;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		String clocation = request.getParameter("cloc");
		String cphone = request.getParameter("cphone");
		String option = request.getParameter("option");
		response.setContentType("text/html");
		try {
			c=DBconnection.getConnection();
			if(option.equals("add")) {
				ps=c.prepareStatement("insert into Customer values(?,?,?,?)");
				ps.setString(1, cid);
				ps.setString(2, cname);
				ps.setString(3, clocation);
				ps.setString(4, cphone);
				ps.execute();
				response.sendRedirect("success.html");
			}else if(option.equals("delete")) {
				ps=c.prepareStatement("delete from Customer where customerid=?");
				ps.setString(1, cid);
				ps.execute();
				response.sendRedirect("delete.html");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
