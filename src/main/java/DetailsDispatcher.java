import javax.servlet.ServletException;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import j.RecipeDataParser;

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
    	
    	if(user_email != null && !RecipeDataParser.checkRecipe(recipe_id)) {
    		j.RecipeDataParser.insertPastRecipe(recipe_id, user_email);
    	}
    	
    	System.out.println("IN DETAILS DISPATCHER");
    	String nameRes = request.getParameter("name_res");
    	System.out.println(nameRes);
    	request.setAttribute("name_res", nameRes);

    	String image_url = request.getParameter("image_url");
    	System.out.println(image_url);
    	request.setAttribute("image_url", image_url);
    	
    	String filters= request.getParameter("cats");
    	System.out.println(filters);
    	request.setAttribute("cats", filters);
    	
    	String ingredients = request.getParameter("ingredients");
    	System.out.println(ingredients);
    	request.setAttribute("ingredients", ingredients);
    	
    	String steps = request.getParameter("steps");
    	System.out.println(steps);
    	request.setAttribute("steps", steps);
    	
    	String urlToWebsite = request.getParameter("url");
    	System.out.println(urlToWebsite);
    	request.setAttribute("urlToWebsite", urlToWebsite);
    	
    	   	
//		 request.setAttribute("name", nameRest);
//		 request.setAttribute("category", cat);
//		 request.setAttribute("image", image_url);
//		 request.setAttribute("price", price);
//		 request.setAttribute("rating", rating);
//		 request.setAttribute("address", address);
		 request.getRequestDispatcher("/Details.jsp").forward(request, response);
    	
    	
	}

}