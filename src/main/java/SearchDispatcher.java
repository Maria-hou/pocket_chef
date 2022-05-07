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

        InputStream stream = j.RecipeDataParser.class.getClassLoader().getResourceAsStream("/recipes.json");
        System.out.println("stream part");
        String json = null;
        try {
        	json = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        	//System.out.println(json);
        }catch (IOException e){
        	System.out.println("stream not");
        }
        
        j.RecipeDataParser.Init(json);
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
    	String filters = "";
    	String vegetarian = request.getParameter("vegetarian");
    	if (vegetarian != null) {filters += vegetarian + ", ";}
    	String vegan = request.getParameter("vegan");
    	if (vegan != null) {filters += vegan + ", ";}
    	String gluten = request.getParameter("gluten");
    	if (gluten != null) {filters += gluten + ", ";}
    	String asian = request.getParameter("asian");
    	if (asian != null) {filters += asian + ", ";}
    	String mediterra = request.getParameter("mediterra");
    	if (mediterra != null) {filters += mediterra + ", ";}
    	
    	System.out.println(filters);
    	
    	String newIng = ingredients.replace(",", "=");
    	newIng = newIng.replace(" ", "*");
    	Cookie cookie_ing = new Cookie("ingredients", newIng);
    	cookie_ing.setMaxAge(60*60);
    	String newFilt = filters.replace(",", "=");
    	newFilt = newFilt.replace(" ", "*");
    	System.out.println(newFilt);
    	Cookie cookie_filt = new Cookie("filters", newFilt);
    	cookie_filt.setMaxAge(60*60);
    	
    	response.addCookie(cookie_ing);
    	response.addCookie(cookie_filt);
		
    	response.sendRedirect("search-result.jsp");
	}

}