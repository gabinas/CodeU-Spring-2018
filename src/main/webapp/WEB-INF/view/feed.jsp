<%@ page import="java.util.List"%>
<%@ page import="codeu.model.data.Conversation"%>
<%@ page import="codeu.model.data.Message"%>
<%@ page import="codeu.model.data.User"%>
<%@ page import="codeu.model.data.Event"%>
<%@ page import="codeu.model.store.basic.UserStore"%>
<%@ page import="codeu.model.store.basic.ConversationStore"%>
<%@ page import="codeu.model.store.basic.MessageStore"%>
<%@ page import="codeu.model.store.basic.EventStore"%>
<%@ page import="java.time.Instant"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%
  EventStore events = EventStore.getInstance();
  List<Event> allEvents = events.listAllEvents();
%>

<!DOCTYPE html>
<html>
<head>
  <title><%="Activity Feed"%></title>
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
  
  <jsp:include page="navigation.jsp" />

  <div id="container">
    <h1><%="Activity Feed"%></h1>
    <a href="" style="float: right">&#8635;</a>
    <hr />

    <div id="feed">
      <ul>
        <%
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss a zzz yyyy");
        for (Event event : allEvents) {
          Date myDate = Date.from(event.getCreationTime());
          String time = formatter.format(myDate);
          String renderEvent = event.toString();
        %>
          <li><strong><%=time%>:</strong> <%=renderEvent%></li>
        <%
        }
        %>
      </ul>
    </div>

    <hr />

  </div>
</body>
</html>
