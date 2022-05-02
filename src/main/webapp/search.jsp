<!DOCTYPE html>
<html>
    <head>
        <title>Search for Recipes</title>
    </head>
    <body>
		 <div id="search_page">
        <form action="SearchDispatcher" method="GET">
        	<div id="dropdown">
	            <input type="search" name="keyWord" id="message" placeholder="Enter name or category here...">
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