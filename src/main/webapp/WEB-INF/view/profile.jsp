<!DOCTYPE html>
<html>
<head>
 <title>Profile</title>
 <link rel="stylesheet" href="/css/main.css">
 <style>
    #bio_edit{
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
</head>

<body>
 <nav>
   <a id="navTitle" href="/">CodeU Chat App</a>
   <a href="/conversations">Conversations</a>
   <% if(request.getSession().getAttribute("user") != null){ %>
   	 <a href="/feed">Activity Feed</a>
     <a>Hello <%= request.getSession().getAttribute("user") %>!</a>
   <% } else{ %>
     <a href="/login">Login</a>
   <% } %>
 </nav>

<div id="container">
  <h1>USER's Profile</h1>
  <hr/>
  <h2>About USER</h2>
  <p id = "bio">
     The user has not yet added a bio.
  </p>
  <h3>Edit your About Me (only you can see this)</h3>
    <textarea id = "bio_edit">The user has not yet added a bio.</textarea>
    <button name = "submit" type = "submit">Submit</button>
  <hr/>
  <h2>USER's Sent Messages</h2>
  <div id = "sent_msgs">
  </div>
</div>
</body>

</html>
