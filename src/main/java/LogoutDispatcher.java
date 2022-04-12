import javax.servlet.ServletException;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;
import java.sql.SQLException;

/**
 * Servlet implementation class LogoutDispatcher
 */
@WebServlet("/LogoutDispatcher")
public class LogoutDispatcher extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        //Remove userID cookie
    	Cookie cookie1=new Cookie("name","");
    	cookie1.setMaxAge(0);
    	Cookie cookie2=new Cookie("email","");
    	cookie2.setMaxAge(0);
    	response.addCookie(cookie1);
    	response.addCookie(cookie2);
    	response.sendRedirect("index.jsp");
		
	}

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doGet(request, response);
    }

}
