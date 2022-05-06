<!-- <%@ page import="java.io.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html>
<head>
<html>
<style>


.checkboxes {
  text-align:center;
}
.checkboxes input{
  margin: 0px 0px 0px 0px;
}

.checkboxes label{
  margin: 0px 25px 0px 3px;
  color: #5B7C7D;
}

</style>
    <head>
        <title>Search</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="index.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Inter:wght@700&display=swap" rel="stylesheet">

	<link
	href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
	rel="stylesheet">
	
	<script src="https://kit.fontawesome.com/3204349982.js"
	crossorigin="anonymous"></script>
    </head>
    <body>
    	<!-- HEADER -->
        	<nav class="navbar navbar-expand-lg" style="background-color: #5b7b7d">
			<div class="container-fluid" >
	        	<a href="index.html">
	        		<img src="image 2 (Traced).png" class="img-fluid" alt="Pocket Chef" style="max-width: 50%">
	        	</a>
	        	
        		  <div style = "font-size: 20px; color: white; font-family: 'Inter', sans-serif;"> 
     			 Welcome Guest! <!--  collect name of guest here IF they are logged in -->
     			 </div>
	        
	        	<ul class="navbar-nav ml-auto" style="font-family: 'Inter', sans-serif;">
	        		<li style="padding-right: 2vw;" class="nav-item"><a href="login-register.html" style="color:#FFFFFF;">Login/Register</a></li>
	        		<li class="nav-item"><a href="search.html"style="color:#FFFFFF;">Get Started</a></li>
	        	</ul>
        	</div>
        	</nav>
        
        
        <form> 
        <div id="center-text" style=" position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); font-family: 'Inter', sans-serif;">
        		
        		
        		  <div style = "font-size: 20px; color: #5B7C7D; font-family: 'Inter', sans-serif; text-align:center"> 
     			 Input ingredients you have in your fridge and separate by comma
     			 </div>
     			 <div style="display: inline-block;" > 
     			 
	     			 <label for="text "> <input type="text" id="wordsearch"
						name="wordsearch" style="width: 800px; margin: 5px; border-radius: 10px; padding-right: 0px;display: inline-block;">
					</label>
		
					<button class="btn" style = "background-color: #72B8C9; float: right; border: none; color: white; padding-top: 8px;padding-right: 5px; text-align: center; text-decoration: none; display: inline-block; overflow: hidden; padding-right: .5em;">
						<i class="fa fa-search"></i>
					</button>
				
				</div>
				
				<div class = "checkboxes"> 
					<input type="checkbox" name="filter" value="vegetarian" style = "color: #5B7C7D"/> <label> Vegetarian </label>
					<input type="checkbox" name="filter" value="vegan" /> <label> Vegan </label>
					<input type="checkbox" name="filter" value="gluten" /> <label> Gluten-Free </label>
					<br> <br>
					<input type="checkbox" name="filter" value="asian" style = "color: #5B7C7D"/> <label> Asian </label>
					<input type="checkbox" name="filter" value="Mediterranean" /> <label> Mediterranean </label>
				</div>
     			 
        		
        	</div>
        	</form>
      
    
        
        
         <div id="margin-bottom" style=" position: absolute; top: 80%; left: 10%; font-family: 'Inter', sans-serif;">
        	<label style="color: #72B8C9; font-size: 20px;"> List of Ingredients we have in our database: </label> <br>
        	<label style="color: #5B7C7D; font-size: 20px;"> apples, pineapples, oranges, flour, eggs, onion, bellpepper,  yogurt, olive oil +  30 more items </label>
        	</div>

		
    </body>
</html>