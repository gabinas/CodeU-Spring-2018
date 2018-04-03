package codeu.controller;

import codeu.model.store.basic.UserStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.data.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.UUID;

/**
*Servlet class responsible for User profile pages.
*/

public class ProfilePageServlet extends HttpServlet {

  // Store class that gives access to Users
  private UserStore userStore;

  // Store class that gives access to Messages.
  private MessageStore messageStore;

/**
  * This function fires when a user navigates to the profile page. It grabs the user name
  * from the URL, finds the corresponding User, and fetches the user info.
  * It then forwards to profile.jsp for rendering
  */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

      String requestUrl = request.getRequestURI();
      String userName = requestUrl.substring("/profile/".length());

      User user = userStore.getUser(userName);

      if ( user == null) {
        // couldn't find user, redirect to home page
        response.sendRedirect("/index");
        return;
      }

      request.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request, response);



    }
}
