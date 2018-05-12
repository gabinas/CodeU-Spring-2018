<%@ page import="java.util.List"%>
<%@ page import="codeu.model.data.User"%>
<%@ page import="codeu.model.data.Message"%>
<%@ page import="codeu.model.store.basic.UserStore"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
User user = (User) request.getAttribute("user");
List<Message> messages = (List<Message>) request.getAttribute("messages");
%>

<!DOCTYPE html>
<html>
<head>
<title>Profile</title>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<style>
#bio_edit {
	display: inline-block;
	width: 100%;
	height: 4em;
	margin: auto;
	font-family: Helvetica, Arial, Verdana, sans-serif;
	font-size: normal;
}

#sent_msgs {
	border: 1px solid black;
	background-color: white;
	width: 100%;
	height: 6em;
	margin: auto;
	font-family: Helvetica, Arial, Verdana, sans-serif;
	font-size: normal;
	overflow: auto;
}
</style>

<script>
  // scroll the div to the bottom
  function scroll() {
    var userDiv = document.getElementById('users');
    userDiv.scrollTop = userDiv.scrollHeight;
  };
</script>
</head>

<body onload="scroll()">

  <nav>
    <a id="navTitle" href="/">TeamRocket Chat App</a> 
    <a href="/conversations">Conversations</a>
    <%
    if (request.getSession().getAttribute("user") != null) {
      %>
      <a href="/feed">Activity Feed</a> 
      <a>Hello <%=request.getSession().getAttribute("user")%>!</a>
      <%
    } else {
      %>
      <a href="/login">Login</a> <a href="/register">Register</a>
      <%
    }
    %>
  </nav>

  <div id="container">
    <h1>USER's Profile</h1>
    <hr />
    <h2>About USER</h2>
    <p id="bio">The user has not yet added a bio.</p>
    <h3>Edit your About Me (only you can see this)</h3>
    <textarea id="bio_edit">The user has not yet added a bio.</textarea>
    <button name="submit" type="submit">Submit</button>
    <hr />
    <h2>USER's Sent Messages</h2>
    <div id="sent_msgs">
      <ul>
      
     
      </ul>
    </div>
  </div>
</body>

</html>
