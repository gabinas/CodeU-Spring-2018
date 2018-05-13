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
	height: 500px;
	margin: auto;
	font-family: Helvetica, Arial, Verdana, sans-serif;
	font-size: normal;
	overflow-y: scroll
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
  <%
  String profileUser = user.getName();
  %>
    <h1><%=profileUser%>'s Profile</h1>
    <hr />
    <h2>About <%=profileUser%></h2>
    <p id="bio"><%=user.getBio()%></p>
    <%
    if (request.getSession().getAttribute("user").equals(user.getName())) {
    %>
      <h3>Edit your About Me (only you can see this)</h3>
      <form action="/users/<%=user.getName()%>" method="POST">
        <textarea id="bio_edit" name="bio">Change your bio in here</textarea>
        <button type="submit">Submit</button>
      </form>
    <%
    }
    %>
    <hr />
    <h2><%=profileUser%>'s Sent Messages</h2>
    <div id="sent_msgs">
      <ul>
        <%
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss a zzz yyyy");
        for (Message message : messages) {
          Date myDate = Date.from(message.getCreationTime());
          String time = formatter.format(myDate);
        %>
          <li><strong><a><%=time%></a>:</strong> <%=message.getContent()%></li>
        <%
        }
        %>
      </ul>
    </div>
    <hr />
  </div>
</body>

</html>
