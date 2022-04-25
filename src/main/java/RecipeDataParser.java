import com.google.gson.*;

import Util.Business;
import Util.Businesses;
import Util.Category;
import Util.Constant;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that pretends to be the Yelp API
 */
public class RecipeDataParser {
    private static Boolean ready = false;

    /**
     * Initializes the DB with json data
     *
     * @param responseString the Yelp json string
     */
    public static void Init(String responseString) {
        if (ready) {
            return;
        }
        
        String recipes = "INSERT INTO recipes (id, name_of_recipe, ingredients, categories, instructions) VALUES (?, ?, ?, ?, ?)";
        
        Connection conn;
        PreparedStatement sql = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //TODO check if you've done the initialization
            
        	conn = DriverManager.getConnection(Constant.DBUrl, Constant.DBUserName, Constant.DBPassword);
			sql = conn.prepareStatement(recipes);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ready = true;
        
        Gson gson = new Gson();
        Recipe[] recipies = null;
        
        try {
	        recipies = gson.fromJson(responseString, Recipe[].class);
        } catch (JsonSyntaxException e) {
			System.err.println(e.getMessage());
			System.out.println("restaurant file is ill formatted.");
		}
        //TODO iterate the businessHelper array and insert every business into the DB
        Integer detailsId = 1, ratingId = 1;
        for (int i=0; i<recipies.length; i++) {
        	Recipe recipe = recipies[i];
        	List<String> ingredients = recipe.getIngredients();
        	List<String> filters = recipe.getFilters();
        	String ing = "", filt = "";
        	
        	for(int j=0; j<ingredients.size(); j++) {
        		ing += ingredients.get(j);
        	}
        	
        	for(int k=0; k<filters.size(); k++) {
        		filt += filters.get(k);
        	}
        	
        	// add the recipe
        	try {
        		sql.setString(1, recipe.getId());
        		sql.setString(2, recipe.getNameOfRecipe());
        		sql.setString(3, ing);
        		sql.setString(4, filt);
        		sql.setString(5, recipe.getSteps());
        		
        		int row = sql.executeUpdate(); //the number of rows affected
        	} catch (SQLException e) {
    			// TODO Auto-generated catch block
        		System.out.println(e.getMessage());
    			System.out.println("could not add the restaurant details");
    		}
        }
    }

    public static Recipe getRecipe(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sql = "SELECT id, name_of_recipe, ingredients, categories, instructions "
            		+ "FROM recipes "
            		+ "WHERE id = '" + id + "';";
            
            Connection conn = DriverManager.getConnection(Constant.DBUrl, Constant.DBUserName, Constant.DBPassword);
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			String rest_id = null, name_of_recipe = null, ingredients = null, categories = null, instructions = null;
			
			if(rs.next()) {
				rest_id = rs.getString("id");
				name_of_recipe = rs.getString("name_of_recipe");
				ingredients = rs.getString("ingredients");
				categories = rs.getString("categories");
				instructions = rs.getString("instructions"); 
			}
			
			Recipe recipe = new Recipe(rest_id, name_of_recipe, ingredients, categories, instructions);
			return recipe;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
        	System.out.println(e.getMessage());
        	System.out.println("could not find a restaurant in getBusiness function");
		}
        //TODO return business based on id
        return null;
    }

    /**
     * @param keyWord    the search keyword
     * @param sort       the sort option (price, review count, rating)
     * @param searchType search in category or name
     * @return the list of business matching the criteria
     */
    public static ArrayList<Recipe> getRecipes(String keyWord, String sort, String searchType) {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        String sql = "";
        
        System.out.println(keyWord);
        System.out.println(sort);
        System.out.println(searchType);
        //sort = sort.toLowerCase();
        if(sort.equals("price")) {sort = "estimated_price";}
        else if (sort.equals("review")) {sort = "review_count";}
        
        if(searchType.equals("name")) {
        	System.out.println("in name..");
        	if(sort.equals("estimated_price")) {
        		System.out.println("in est price..");
        		sql = "SELECT r.restaurant_id FROM Restaurant r, Restaurant_details rd WHERE r.details_id = rd.details_id AND r.restaurant_name LIKE '%" + keyWord + "%' ORDER BY rd." + sort + ";";
        	}
        	else {
        		System.out.println("in review count..");
        		sql = "SELECT r.restaurant_id FROM Restaurant r, Rating_details rd WHERE r.rating_id = rd.rating_id AND r.restaurant_name LIKE '%" + keyWord + "%' ORDER BY rd." + sort + " desc;";
        	}
        }
        else {
        	System.out.println("category..");
        	if(sort.equals("estimated_price")) {
        		System.out.println("in est price..");
        		sql = "SELECT r.restaurant_id FROM Restaurant r, Restaurant_details rd, Category c WHERE r.details_id = rd.details_id AND r.restaurant_id = c.restaurant_id AND c.category_name LIKE '%" + keyWord + "%' ORDER BY rd." + sort + ";";
        	}
        	else {
        		System.out.println("in review count..");
        		sql = "SELECT r.restaurant_id FROM Restaurant r, Rating_details rd, Category c WHERE r.rating_id = rd.rating_id AND r.restaurant_id = c.restaurant_id AND c.category_name LIKE '%" + keyWord + "%' ORDER BY rd." + sort + " desc;";
        	}
        }
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constant.url, Constant.DBUserName, Constant.DBPassword);
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			if(rs.next()) {
				while(rs.next()) {
					String rest_id = rs.getString("restaurant_id");
					//System.out.println(rest_id);
					businesses.add(getBusiness(rest_id));
				}
			}else {
				return null;
			}
			
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
        	System.out.println(e.getMessage());
        	System.out.println("could not find restaurants in getBusinesses function");
		}
        //TODO get list of business based on the param
        if(businesses.isEmpty()) {
        	System.out.println("did not find any restaurants");
        }
        return businesses;

    }
}

//Code adapted from https://stackoverflow.com/questions/23070298/get-nested-json-object-with-gson-using-retrofit
class BusinessDeserializer implements JsonDeserializer<Recipes> {
    @Override
    public Recipes deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        JsonElement content = je.getAsJsonObject();
        return new Gson().fromJson(content, Recipes.class);
    }
}