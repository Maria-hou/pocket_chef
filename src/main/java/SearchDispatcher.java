import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.*;
import java.nio.charset.StandardCharsets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet implementation class LoginDispatcher
 */
@WebServlet("/SearchDispatcher")
public class SearchDispatcher extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public SearchDispatcher() {
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = getServletContext();

        InputStream stream = RecipeDataParser.class.getClassLoader().getResourceAsStream("/recipes.json");
        System.out.println("stream part");
        String json = null;
        try {
        	json = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        	//System.out.println(json);
        }catch (IOException e){
        	System.out.println("stream not");
        }
        
        RecipeDataParser.Init(json);
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
    	
    	String ingredients = request.getParameter("ingredients");
    	String filters = request.getParameter("filters");
    	
    	ArrayList<Recipe> ex = RecipeDataParser.getRecipes(ingredients, filters);
    	for(int i=0; i<ex.size(); i++) {
    		System.out.println(ex.get(i).getId());
    	}
    	
    	Cookie cookie_ing = new Cookie("ingredients", ingredients);
    	cookie_ing.setMaxAge(60*60);
    	Cookie cookie_filt = new Cookie("filters", filters);
    	cookie_filt.setMaxAge(60*60);
    	
    	response.addCookie(cookie_ing);
    	response.addCookie(cookie_filt);
		
    	response.sendRedirect("search.jsp");
	}

}