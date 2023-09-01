package com.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DBconnection;

@WebServlet("/ViewStocks")
public class ViewStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
	Connection c;
	PreparedStatement ps;
	ResultSet rs;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		pw.println("<html><head><title>View</title>"
				+ "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\">"
				+ "<style>body {\r\n"
				+ "	background-image: url(\"image/inventory.png\");\r\n"
				+ "	background-repeat: no-repeat;\r\n"
				+ "	background-size: 1750px;\r\n"
				+ "}</style></head><body><div class=container>");
		pw.println("<table style=margin-top:60px class=table>");
		pw.println("<tr><td colspan=2><h2 style=text-align:center>Stock Details</h2></td></tr>");
		pw.println("<th>Product_name</th><th>Quantity</th>");
		try {
			c=DBconnection.getConnection();
			ps=c.prepareStatement("select * from Stock");
			rs=ps.executeQuery();
			while(rs.next()) {
				pw.println("<tr><td>" + rs.getString(1) + "</td><td>" + rs.getString(2) + "</td></tr>");
			}
			pw.print("</table>");
			pw.println("</div></body></html>");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
