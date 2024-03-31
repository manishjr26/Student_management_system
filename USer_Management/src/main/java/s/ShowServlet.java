package s;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/show")
public class ShowServlet extends HttpServlet
{
	public static Connection getConnection() throws ClassNotFoundException,SQLException
	{
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/user_management","root","tiger@123");
			return cn;	
	}
	private final static String query="select * from user";
 
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
						ResultSet rs=ps.executeQuery();
						pw.println("<!DOCTYPE html>");
						pw.println("<html>");
						pw.println("<head>");
						pw.println("<title>Show Data</title>");
						pw.println("<link rel='stylesheet' href='css/bootstrap.css'>");
						pw.println("</head>");
						pw.println("<body>");
						pw.print("<br>");
						pw.println("<div class='container' style='max-width: 900px;'>"); // Container for responsive layout
						pw.println("<table class=\"table table-striped table-bordered table-hover text-center\">");
						pw.println("<thead class=\"bg-info\">"); // Table header with background color
						pw.println("<tr>");
						pw.println("<th>Name</th>");
						pw.println("<th>Email</th>");
						pw.println("<th>Number</th>");
						pw.println("<th>DOB</th>");
						pw.println("<th>City</th>");
						pw.println("<th>ID</th>");
						pw.println("<th>Edit</th>");
						pw.println("<th>Delete</th>");
						pw.println("</tr>");
						pw.println("</thead>");
						pw.println("<tbody>");

						while (rs.next()) {
						    pw.println("<tr>");
						    pw.println("<td>" + rs.getString(1) + "</td>");
						    pw.println("<td>" + rs.getString(2) + "</td>");
						    pw.println("<td>" + rs.getString(3) + "</td>");
						    pw.println("<td>" + rs.getString(4) + "</td>");
						    pw.println("<td>" + rs.getString(5) + "</td>");
						    pw.println("<td>" + rs.getInt(6) + "</td>");
						    pw.println("<td><a href='edit?id="+rs.getInt(6)+ "'> Edit</a></td>");
						    pw.println("<td><a href='delete?id="+rs.getInt(6)+ "'> Delete</a></td>");
						   // pw.println("<td><a href='delete?id='+rs.getInt(6)+''>Delete </a></td>");
						    pw.println("</tr>");
						}

						pw.println("</tbody>");
						pw.println("</table>");
						pw.println("</div>"); // Close container
						pw.println("</body>");
						pw.println("</html>");
						
					} catch (SQLException|ClassNotFoundException  e) {
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
