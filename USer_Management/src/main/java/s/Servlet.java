package s;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/register")
public class Servlet extends HttpServlet 
{
	
	public static Connection getConnection() throws ClassNotFoundException,SQLException
	{
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/user_management","root","tiger@123");
			return cn;	
	}
	private final static String query="insert into user(name,email,number,dob,city) values(?,?,?,?,?)";
 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String number=req.getParameter("number");
		String dob=req.getParameter("dob");
		String city=req.getParameter("city");
		Connection cn = null;
		PrintWriter pw=resp.getWriter();
		resp.setContentType("text/html");
					
					try 
					{
						cn = getConnection();
						PreparedStatement ps=cn.prepareStatement(query);
						ps.setString(1,name);
						ps.setString(2,email);
						ps.setString(3,number);
						ps.setString(4,dob);
						ps.setString(5,city);
						int count=ps.executeUpdate();
						if(count==1)
						{
							pw.print("<h2>Record Registered.</h2>");
						}
						else
						{
							pw.print("<h2>Record not Registered.</h2>");
						}
					} catch (SQLException|ClassNotFoundException  e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					pw.println("<!DOCTYPE html>");
					pw.println("<html>");
					pw.println("<head>");
					pw.println("<title>Show Data</title>");
					pw.println("<link rel='stylesheet' href='css/bootstrap.css'>");
					pw.println("</head>");
					pw.println("<body>");
					
					
					//pw.println("<a href='index.html'><button class='btn btn-outline-custom h-40 w-200'>Insert Record</button> </a>");

					pw.println("<a href='index.html'><button class='btn btn-outline-success h-40 w-200'>Insert Record</button> </a>");
					pw.println("<a href='show'><button class='btn btn-outline-primary'>View Record</button> </a>");
					//pw.println("<a><button class='btn btn-outline-danger'>Delete Record</button> </a>");
					pw.println("</body>");
					pw.println("</html>");
	
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
