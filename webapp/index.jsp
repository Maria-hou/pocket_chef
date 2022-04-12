<!DOCTYPE html>
<html>
    <head>
        <title>Pocket Chef Home</title>
    </head>
    <body>
	<h1>home page</h1>
	<%
	//can put this code into a separate java file if u want and just include it
	//this is for if u want the user's name to show up across all pages (would be better to move this code into whatever file handles the tab navigator to reuse the code)
		Cookie cookies[]=request.getCookies();
		Boolean loggedin=false;
		int nameIndex = 0;
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
	%>
	<%if(cookies.length > 1 && cookies[nameIndex].getValue() != null) { 
	  	String name = cookies[nameIndex].getValue();
	  	name = name.replace("*", " ");%>
	  	Hi, <%= name %>
	<%loggedin=true;}%>
	<a style="font-size: 22px;" href="http://localhost:8080/pocket_chef/search.html">Get Started</a>
	<%if(!loggedin) { %>
	<a href="http://localhost:8080/pocket_chef/login-register.html">Login/Register</a>
	<%} else { %>
	  	<a href="LogoutDispatcher">Logout</a>
	<%} %>
	
    </body>
</html>