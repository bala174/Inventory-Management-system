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

@WebServlet("/submitProduct")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
	Connection c;
	PreparedStatement ps;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		String pcode = request.getParameter("pcode");
		String pname = request.getParameter("pname");
		String costprice = request.getParameter("pcp");
		String sellprice = request.getParameter("psp");
		String option = request.getParameter("option");
		
		response.setContentType("text/html");
		try {
			c=DBconnection.getConnection();
			if(option.equals("add")) {
				ps=c.prepareStatement("insert into Product values(?,?,?,?,?)");
				ps.setString(1,pid);
				ps.setString(2,pcode);
				ps.setString(3,pname);
				ps.setString(4,costprice);
				ps.setString(5,sellprice);
				ps.execute();
				response.sendRedirect("success.html");
			}else if(option.equals("delete")) {
				ps=c.prepareStatement("delete from product where productid=?");
				ps.setString(1, pid);
				ps.execute();
				response.sendRedirect("delete.html");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
