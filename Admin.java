

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.Statement;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		PrintWriter out=response.getWriter();
		
		String uname=request.getParameter("uname");
		String password=request.getParameter("password");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
			//String query="select * from admin where uname=? and password=?"; 
			//PreparedStatement ps=conn.prepareStatement(query);
			//ps.executeUpdate();
			java.sql.Statement t=conn.createStatement();
			ResultSet rs=t.executeQuery("select * from admin");
		
			while(rs.next())
			{
				String u=rs.getString("uname");
				String p=rs.getString("password");
				//out.println("Userid:"+ u +"pword: "+p );
				
				if(u.equals(uname) && p.equals(password)) {
					//out.print("<h1>Welcome</h1><h1>"+ u +"</h1>");
					response.sendRedirect("A_Access.html"); 
				}
				else {
					
					response.sendRedirect("Admin.html?error=invalid");
					//out.print("<html><body><script>");
					//out.print("alert('Please Enter Valid Credentials')");
					//out.print(" window.location.href =Admin.html");
					//out.print("</script></body></html>");
					
				}
				
			}
			
		}
		catch(Exception e){
			out.println(e);
			
		}
	}

}
