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
 * Servlet implementation class DetailsDispatcher
 */
@WebServlet("/DetailsDispatcher")
public class DetailsDispatcher extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public DetailsDispatcher() {
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //TODO
    	response.setContentType("text/html");
    	
    	System.out.println("in detail dispatcher");
    	
    	String recipe_id = request.getParameter("recipe_id");
		String user_email = null;
		
		Cookie[] cookies  = request.getCookies(); 
    	
    	if(cookies != null) {
        	for (Cookie aCookie : cookies)
    		{
    			if(aCookie.getName().equals("email")) {
    				user_email = aCookie.getValue();
    			}

        	}
    	}
    	
    	if(user_email != null) {
    		j.RecipeDataParser.insertPastRecipe(recipe_id, user_email);
    	}
	}

}