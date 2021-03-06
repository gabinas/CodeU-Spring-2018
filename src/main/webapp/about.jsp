<%--
  Copyright 2017 Google Inc.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
--%>
<!DOCTYPE html>
<html>
<head>
  <title>TeamRocket Chat App</title>
  <link rel="stylesheet" href="/css/main.css">
</head>
<body>

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

  <div id="container">
    <div
      style="width:75%; margin-left:auto; margin-right:auto; margin-top: 50px;">

      <h1>About Us</h1>

      <ul>
        <li><strong>Russell Cagle:</strong> Program Advisor and software engineer at Google</li>
        <li><strong>Gabriela Lugo:</strong> Computer Science major and Mathematics minor at The University
        	of British Columbia</li>
        <li><strong>Martin Landin:</strong> Computer Science Major at Cal-State Monterey Bay</li>
        <li>
          <strong>Mary Clare Shen:</strong> Computer Science major at Harvey Mudd College
        </li>
        <li><strong>Shining Liu:</strong> Web Design and Engineering major at Santa Clara University</li>
      </ul>
    </div>
  </div>
</body>
</html>
