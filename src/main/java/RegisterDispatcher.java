import javax.servlet.ServletException;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import j.Constant;

/**
 * Servlet implementation class RegisterDispatcher
 */
@WebServlet("/RegisterDispatcher")
public class RegisterDispatcher extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private String message;
    public void init() throws ServletException {
    	message = "Register Servlet";
    }
    
    /**
     * Default constructor.
     */
    public RegisterDispatcher() {
    	super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html");
    	String error = "";
    	String emailError = "";
    	String nameError = "";
    	String passError = "";
    	String confirmError = "";
    	String checkboxError = "";
    	String email = request.getParameter("email");
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm_password");
		String checkbox = request.getParameter("checkbox");
		Boolean invalid = false;
		HttpSession session = request.getSession();
		if (email.contentEquals(""))
		{
			error += "<p>Email missing.</p>";
			emailError = "<p>Email missing.</p>";
			invalid = true;
		}
		else if(!Helper.isValidEmail(email))
		{
			error+= "<p>Email is invalid.</p>";
			emailError += "<p>Email is invalid.</p>";
			invalid = true;
		}
		if (username.contentEquals(""))
		{
			error += "</p>Name missing.</p>";
			nameError = "</p>Name missing.</p>";
			invalid = true;
		}
		else if(!Helper.validName(username))
		{
			error+= "<p>Invalid name.</p>";
			nameError += "<p>Invalid name.</p>";
			invalid = true;
		}
		if (password.contentEquals(""))
		{
			error += "<p>Password missing.</p>";
			passError = "<p>Password missing.</p>";
			invalid = true;
		}
		else if (confirmPassword.contentEquals(""))
		{
			error += "<p>Confirm password missing.</p>";
			confirmError = "<p>Confirm password missing.</p>";
			invalid = true;
		}
		else if(!confirmPassword.equals(password))
		{
			error+= "<p>Passwords do not match.</p>";
			confirmError += "<p>Passwords do not match.</p>";
			invalid = true;
		}
		if(checkbox == null)
		{
			error += "<p>Please agree to the terms and conditions.</p>";
			checkboxError = "<p>Please agree to the terms and conditions.</p>";
			invalid = true;
		}
		Pattern p = Pattern.compile("[^A-Za-z0-9]");
	    Matcher m = p.matcher(username);
	    boolean hasSpecial = m.find();
	    if(hasSpecial)
	    {
	    	error += "<p>Please make sure there are no special characters in your name</p>";
	    	nameError += "<p>Please make sure there are no special characters in your name</p>";
	    	invalid = true;
	    }

		
		String sql = "SELECT * FROM user_info WHERE email=?";
		String ins = "INSERT INTO user_info (email, username, password) VALUES (?, ?, ?)";
		//get an instance of JDBC driver
		Connection conn = null;
		
		if (error.equals("") && !invalid)
		{
			if(username.contains(" "))
			{
				username = username.replace(" ", "*");
			}
			Cookie cookie1=new Cookie("name", username);
			Cookie cookie2=new Cookie("email", email);
			response.addCookie(cookie1);
			response.addCookie(cookie2);
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(Constant.DBUrl, Constant.DBUserName, Constant.DBPassword);
				PreparedStatement psEmail = conn.prepareStatement(sql);
				psEmail.setString(1, email);
				ResultSet rsEmail = psEmail.executeQuery();
				
				//check if email is already present in database
				if(rsEmail.next())
				{
					if(rsEmail.getRow() > 0)
					{
						emailError = "<p>This user has already been registered.</p>";
						request.setAttribute("registerError", "true");
						request.setAttribute("emailError", emailError);
						request.getRequestDispatcher("/login-register.jsp").include(request, response);
					}
					else
					{
						//insert new user into database
						PreparedStatement newuserps = conn.prepareStatement(ins);
						newuserps.setString(1, email);
						newuserps.setString(2, username);
						newuserps.setString(3, password);
						int row = newuserps.executeUpdate();
						System.out.println(String.format("Number of rows affected %d", row));
						response.sendRedirect("index.jsp");
					}
				}
				else
				{
					//insert new user into database
					PreparedStatement newuserps = conn.prepareStatement(ins);
					newuserps.setString(1, email);
					newuserps.setString(2, username);
					newuserps.setString(3, password);
					int row = newuserps.executeUpdate();
					System.out.println(String.format("Number of rows affected %d", row));
					response.sendRedirect("index.jsp");
				}
				
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			//response.sendRedirect("index.jsp");
		}
	    else
		{
			request.setAttribute("registerError", error);
			request.setAttribute("emailError", emailError);
			request.setAttribute("nameError", nameError);
			request.setAttribute("passError", passError);
			request.setAttribute("confirmError", confirmError);
			request.setAttribute("checkboxError", checkboxError);
			request.getRequestDispatcher("/login-register.jsp").include(request, response);
		}

	}
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}