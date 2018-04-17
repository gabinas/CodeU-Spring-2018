<!DOCTYPE html>
<html>
<head>
	<title><%= "Activity Feed" %></title>
	<link rel="stylesheet" href="/css/main.css" type="text/css">
	<style>
	    #feed {
	      background-color: white;
	      height: 500px;
	      overflow-y: scroll
	    }
  	</style>
  	<script>
    // scroll the feed div to the bottom
    	function scrollFeed() {
    		var feedDiv = document.getElementById('feed');
    		feedDiv.scrollTop = feedDiv.scrollHeight;
	    };
    </script>
</head>
<body onload="scrollFeed()">
	<nav>
		<a id="navTitle" href="/">CodeU Chat App</a>
		<a href="/conversations">Conversations</a>
			<% if (request.getSession().getAttribute("user") != null) { %>
		<a>Hello <%= request.getSession().getAttribute("user") %>!</a>
		<% } else { %>
			<a href="/login">Login</a>
			<a href="/register">Register</a>
		<% } %>
		<a href="/about.jsp">About</a>
	</nav>
	
	<div id="container">
		<h1><%= "Activity Feed" %></h1>
			<a href="" style="float: right">&#8635;</a></h1>
		<hr/>
		
		<div id="feed">
			<!-- The events will be rendered here -->
		</div>
		
		<hr/>
		
	</div>
</body>
</html>
