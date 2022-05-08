<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.SQLException" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "javax.servlet.http.HttpServlet" %>
<%@ page import = "javax.servlet.http.HttpServletRequest" %>
<%@ page import = "javax.servlet.http.HttpServletResponse" %>
<!DOCTYPE html>
<html>
<head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="index.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Inter:wght@700&display=swap" rel="stylesheet">
		<meta charset="ISO-8859-1">
		<title>Details</title>
</head>
<body>

    	<!-- HEADER/Check For Login -->
        	<!-- HEADER -->
        	<nav class="navbar navbar-expand-lg" style="background-color: #5b7b7d">
			<div class="container-fluid" >
	        	<a href="index.jsp">
	        		<img src="image 2 (Traced).png" class="img-fluid" alt="Pocket Chef" style="max-width: 50%">
	        	</a>
	        
	       		<%
				//can put this code into a separate java file if u want and just include it
				//this is for if u want the user's name to show up across all pages (would be better to move this code into whatever file handles the tab navigator to reuse the code)
				Cookie cookies[]=null;
				int nameIndex = 0;
				Boolean loggedin=false;
				if (request.getCookies()!=null){
					 cookies=request.getCookies();
					
					
					for(int i=1; cookies!=null && i<cookies.length; i++)
					{
						if(cookies[i].getName() != null)
						{
							if((cookies[i].getName()).equals("name"))
							{
								//System.out.println(cookies[i].getValue());
								nameIndex = i;
							}
						}
					}
				
					if(cookies.length > 1 && cookies[nameIndex].getValue() != null && nameIndex !=0) { 
					  	String name = cookies[nameIndex].getValue();
					  	name = name.replace("*", " ");%>
					  	
					  		<p class="nav-item my-auto" style="color:#FFFFFF; font-family: 'Inter', sans-serif;">Hi, <%= name %></p>
					  	
						<%loggedin=true;} 
					else { 
						loggedin = false;
					}}%>
				
			
				<%if(!loggedin) { %>
					<ul class="navbar-nav ml-auto" style="font-family: 'Inter', sans-serif;">
		        		<li style="padding-right: 2vw;" class="nav-item"><a href="login-register.jsp" style="color:#FFFFFF;">Login/Register</a></li>
		        		<li class="nav-item"><a href="search.jsp"style="color:#FFFFFF;">Get Started</a></li>
		        	</ul>
				<%} else { %>
					<ul class="navbar-nav ml-auto" style="font-family: 'Inter', sans-serif;">
						<li class="nav-item"><a href="history.jsp">History</a><li>
		        		<li style="padding-right: 2vw;" class="nav-item"><a href="LogoutDispatcher" style="color:#FFFFFF;">Logout</a></li>
		        		<li class="nav-item"><a href="search.jsp"style="color:#FFFFFF;">Get Started</a></li>
		        	</ul>
				  	
				<%} %> 
	        
        	</div>
        	</nav>
			


<% 
	String image_url = (String)request.getAttribute("image_url");
	String resName = (String)request.getAttribute("name_res");
	String newResName = resName.replace("=", " ");
	String categories = (String)request.getAttribute("cats");
	String newCategories = categories.replace("=", " ");
	String ingredients = (String)request.getAttribute("ingredients");
	String newIngredients = ingredients.replace("=", " ");
	String steps = (String)request.getAttribute("steps");
	String newSteps = steps.replace("=", " ");
	String url = (String)request.getAttribute("urlToWebsite");
	String newUrl = url.replace("=", " ");
%>
<h1 style = "margin-left: 30px;"> <%= newResName %> </h1>
				<div class = img>
						<!--  IMAGE -->
						<img src= <%=image_url%> style="border-radius: 10%" width="100" height="100">
						 </div>
						<div class = descript>
						<!--  OUTPUT INFO HERE, DUMMY code for now, put in real stuff later -->
						Categories: <%= newCategories %>
						<br>
						Ingredients: <%= newIngredients %>
						<br>
						Steps:	<%= newSteps %>
						<br>
						<% if (newUrl != "") { %>
						 <a href= <%= newUrl %> >More Info</a>
						 <% } %>
						<br>
						</div>
						

</body>
</html>