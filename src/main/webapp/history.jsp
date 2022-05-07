<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="j.Recipe, j.RecipeDataParser, java.util.ArrayList" %>

<%@ page import="java.io.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Pocket Chef Home</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="index.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Inter:wght@700&display=swap" rel="stylesheet">
		<script src="https://kit.fontawesome.com/3204349982.js"
	crossorigin="anonymous"></script>
    </head>
    <!-- HEADER -->
        	<nav class="navbar navbar-expand-lg" style="background-color: #5b7b7d">
			<div class="container-fluid" >
	        	<a href="index.jsp">
	        		<img src="image 2 (Traced).png" class="img-fluid" alt="Pocket Chef" style="max-width: 50%">
	        	</a>
	        
	       		<%
				//can put this code into a separate java file if u want and just include it
				//this is for if u want the user's name to show up across all pages (would be better to move this code into whatever file handles the tab navigator to reuse the code)
					Cookie cookies1[]=request.getCookies();
					Boolean loggedin=false;
					int nameIndex = 0;
					for(int i=1; cookies1!=null && i<cookies1.length; i++)
					{
						if(cookies1[i].getName() != null)
						{
							if((cookies1[i].getName()).equals("name"))
							{
								//System.out.println(cookies[i].getValue());
								nameIndex = i;
							}
						}
					}
				%>
				<%if(cookies1.length > 1 && cookies1[nameIndex].getValue() != null) { 
				  	String name = cookies1[nameIndex].getValue();
				  	name = name.replace("*", " ");%>
				  	
				  		<p class="nav-item my-auto" style="color:#FFFFFF; font-family: 'Inter', sans-serif;">Hi, <%= name %></p>
				  	
				<%loggedin=true;}%>
				
			
				<%if(!loggedin) { %>
					<ul class="navbar-nav ml-auto" style="font-family: 'Inter', sans-serif;">
		        		<li style="padding-right: 2vw;" class="nav-item"><a href="login-register.jsp" style="color:#FFFFFF;">Login/Register</a></li>
		        		<li class="nav-item"><a href="search.jsp"style="color:#FFFFFF;">Get Started</a></li>
		        	</ul>
				<%} else { %>
					<ul class="navbar-nav ml-auto" style="font-family: 'Inter', sans-serif;">
		        		<li style="padding-right: 2vw;" class="nav-item"><a href="LogoutDispatcher" style="color:#FFFFFF;">Logout</a></li>
		        		<li class="nav-item"><a href="search.jsp"style="color:#FFFFFF;">Get Started</a></li>
		        	</ul>
				  	
				<%} %> 
	        
        	</div>
        	</nav>
        	<!-- RESULTS -->
        	<div class="container" >
        	<% 
        	for (Recipe r: recipes){%>
			        		
			    <br>
				<br>
				<br>
				
        		<div class = img>
						<img  src="<%=r.getImageUrl() %>" style="border-radius:10%; width:150px; height:150px;  margin-left: 20px; object-fit: cover;">
					</div>	
				<div class=format ">
				<!--  TO BE CHANGED -->
						<form action="DetailsDispatcher" method="POST"> 
							<button action="DetailsDispatcher" method="POST" style="font-size: 14px; background: none; border: none;padding: 0;text-decoration: underline; color: blue;" >
							<%= r.getNameOfRecipe() %>
							</button>
							<%= r.getNameOfRecipe() %>
							<input  name="name" value="<% r.getNameOfRecipe(); %>" style="display:none;">
							<input style="display:none;" name="imageUrl" value="<%=r.getImageUrl()%>">
							<input style="display:none;" name ="ingredients" value="<%=r.getProducts()%>" >
							<!-- <input style="display:none;" name="price" value="${restaurant.price}"> 
							<input style="display:none;" name="rating" value="${restaurant.rating}"> 
							<input style="display:none;" name="address" value="${restaurant.address}"> 
							<input style="display:none;" name="cat" value="${restaurant.cat}"> 
							<input style="display:none;" name="phone" value="${restaurant.phone}"> -->
						</form>
						</div>
				</div>
				
				<hr>
        	<% }%>
        	
        	</div>
        	  </body>
        </html>