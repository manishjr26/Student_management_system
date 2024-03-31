package s;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@WebServlet("/edit")
public class EditServlet extends HttpServlet 
{
	 private final static String query = "select name,email,number,dob,city from user where id=?";
	 public static Connection getConnection() throws ClassNotFoundException,SQLException
		{
			
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/user_management","root","tiger@123");
				return cn;	
		}
	 
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
		{
			
			Connection cn=null;
			PrintWriter pw=resp.getWriter();
			resp.setContentType("text/html");
						try 
						{
							cn = getConnection();
							PreparedStatement ps=cn.prepareStatement(query);
							int id=Integer.parseInt(req.getParameter("id"));
							ps.setInt(1, id);
							ResultSet rs=ps.executeQuery();
							rs.next();
							pw.println("<!DOCTYPE html>");
							pw.println("<html>");
							pw.println("<head>");
							pw.println("<title>Show Data</title>");
							pw.println("<link rel='stylesheet' href='css/bootstrap.css'>");
							pw.println("</head>");
							pw.println("<body>");
							pw.print("<br>");
							pw.println("<div style='margin:auto;width:500px;margin-top:100px;'>");
				            pw.println("<form action='edit?id="+id+"' method='post'>");
				            pw.println("<table class='table table-hover table-striped'>");
				            pw.println("<tr>");
				            pw.println("<td>Name</td>");
				            pw.println("<td><input type='text' name='name' value='"+rs.getString(1)+"'></td>");
				            pw.println("</tr>");
				            pw.println("<tr>");
				            pw.println("<td>Email</td>");
				            pw.println("<td><input type='email' name='email' value='"+rs.getString(2)+"'></td>");
				            pw.println("</tr>");
				            pw.println("<tr>");
				            pw.println("<td>Number</td>");
				            pw.println("<td><input type='text' name='mobile' value='"+rs.getString(3)+"'></td>");
				            pw.println("</tr>");
				            pw.println("<tr>");
				            pw.println("<td>DOB</td>");
				            pw.println("<td><input type='date' name='dob' value='"+rs.getString(4)+"'></td>");
				            pw.println("</tr>");
				            pw.println("<tr>");
				            pw.println("<td>City</td>");
				            pw.println("<td><input type='text' name='city' value='"+rs.getString(5)+"'></td>");
				            pw.println("</tr>");
				        
				            pw.println("<tr>");
				            pw.println("<td><button type='submit' class='btn btn-outline-success'>Edit</button></td>");
				            pw.println("<td><button type='reset' class='btn btn-outline-danger'>Cancel</button></td>");
				            pw.println("</tr>");
				            pw.println("</table>");
				            pw.println("</form>");
							
							    
							}catch (SQLException|ClassNotFoundException  e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						pw.println("<div class='container'>"); // Container for buttons
						pw.println("<div class='row'>"); // Bootstrap row for inline elements
						pw.println("<div class='col-md-6 text-center'>"); // Bootstrap column for button
						pw.println("<a href='index.html'><button class='btn btn-outline-primary'>Insert Record</button></a>");
						pw.println("</div>"); // Close column
						pw.println("<div class='col-md-6 text-center'>"); // Bootstrap column for button
						//pw.println("<a href='deleteurl'><button class='btn btn-outline-warning'>Delete Record</button></a>");
						pw.println("</div>"); // Close column
						pw.println("</div>"); // Close row
						pw.println("</div>"); 
						try {
							cn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		}
	 
	 @Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doPost(req, resp);
		}
}
