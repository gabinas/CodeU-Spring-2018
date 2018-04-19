<!DOCTYPE html>
<html>
<head>
 <title>Profile</title>
 <meta charset="utf-8">
 <link rel="stylesheet" href="/css/main.css">
   <style>
    .edit{
      display: none;
    }
    #bio_edit{
     width: 100%;
     height: 4em;
     margin: auto;
     font-family: Helvetica, Arial, Verdana, sans-serif;
    }
   #sent_msgs {
     border: 1px solid black;
     background-color: white;
     width: 100%;
     height: 6em;
     margin: auto;
     font-family: Helvetica, Arial, Verdana, sans-serif;
     overflow: auto;
    }
 </style>
 <script type = "text/javascript">

 function showTextArea() {
   document.getElementById("edit_button").style.display = "none";
   var editClass = document.getElementsByClassName("edit");
   var i;
   for (i = 0; i < editClass.length; i++) {
     editClass[i].style.display = "inline-block";
   }
 }

 function collapseTextArea() {
    document.getElementById("edit_button").style.display = "inline-block";
    document.getElementById("description").innerHTML = document.getElementById("bio_edit").value;
    var editClass = document.getElementsByClassName("edit");
    var i;
    for (i = 0; i < editClass.length; i++) {
      editClass[i].style.display = "none";
    }
  }

  function cancelEdit() {
    document.getElementById("edit_button").style.display = "inline-block";
    var editClass = document.getElementsByClassName("edit");
    var i;
    for (i = 0; i < editClass.length; i++) {
      editClass[i].style.display = "none";
    }
  }

 </script>
</head>

<body>
 <nav>
   <a id="navTitle" href="/">CodeU Chat App</a>
   <a href="/conversations">Conversations</a>
   <% if(request.getSession().getAttribute("user") != null){ %>
     <a>Hello <%= request.getSession().getAttribute("user") %>!</a>
   <% } else{ %>
     <a href="/login">Login</a>
   <% } %>
 </nav>

<div id="container">
  <h1>USER's Profile</h1>
  <hr/>

  <h2>About USER</h2>
  <p id="description">
     The user has not yet added a bio.
  </p>
  <button id = "edit_button" type="button" value="Edit" onclick="showTextArea()">Edit</button>
  
  <h3 class="edit">Edit your About Me</h3>
  <textarea class="edit" id="bio_edit"></textarea>
  <button class="edit" type="button" value="Submit" onclick="collapseTextArea()">Submit</button>
  <button class="edit" type="button" value="Cancel" onclick="cancelEdit()">Cancel</button>
  <hr/>

  <h2>USER's Sent Messages</h2>
  <div id="sent_msgs">
  </div>
</div>
</body>

</html>
