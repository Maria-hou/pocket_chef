<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="Recipe, RecipeDataParser, java.util.ArrayList" %>
         
<!DOCTYPE html>
<html>
    <head>
        <title>Search for Recipes</title>
         <%
        //TODO perform search
        Cookie[] cookies  = request.getCookies(); // get the cookies
        	String ingredients = null;
        	String filters = null;
        	
        	if(cookies != null) {
	        	for (Cookie aCookie : cookies)
	    		{
	    			if(aCookie.getName().equals("ingredients")) {
	    				ingredients = aCookie.getValue();
	    			}
	    			else if(aCookie.getName().equals("filters")) {
	    				filters = aCookie.getValue();
	    			}
	        	}
        	}
        	
        	
        	ArrayList<Recipe> recipes = null;
        	if(ingredients == null || filters == null) {
				System.out.println("Smth wrong with the search form, cookies no info");
        	}
        	else {
        		recipes = RecipeDataParser.getRecipes(ingredients, filters); //THIS ARRAY CAN BE USED TO DISPLAY THE SEARCH RESULTS
        	}
        //TODO check if logged in
    %>
    </head>
    <body>
				 <div id="search_page">
        <form action="SearchDispatcher" method="GET">
        	<div id="dropdown">
	            <input type="search" name="ingredients" id="message" placeholder="Enter name or category here...">
        	</div>
        	<!--<div id="rest_name_field">
        		<textarea id="message" placeholder="Enter name or category here..."></textarea>
        	</div> -->
        	<div id="button">
        		<button id="submit_btn" type="submit">
        			<img src="search.png" alt="search_glass" />
        		</button>
        	</div>
        </form>
        </div>
    </body>
</html>