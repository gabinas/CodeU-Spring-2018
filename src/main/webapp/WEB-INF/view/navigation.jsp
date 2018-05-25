<!DOCTYPE HTML>

<html>
 <nav>
   <a id="navTitle" href="/">Team Rocket Chat App</a>
   <% if (request.getSession().getAttribute("user") != null) { %>
   <a>Hello <%= request.getSession().getAttribute("user") %>!</a>
   <% } else { %>
     <a href="/login">Login</a>
     <a href="/register">Register</a>
   <% } %>
   <a href="/feed">Feed</a>
   <a href="/conversations">Conversations</a>
   <a href="/testdata">Load Test Data</a>
   <a href="/about.jsp">About</a>
 </nav>
</html>
