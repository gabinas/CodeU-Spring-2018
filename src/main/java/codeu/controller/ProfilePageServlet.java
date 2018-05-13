package codeu.controller;

import codeu.model.store.basic.UserStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.data.Message;
import codeu.model.data.User;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Servlet class responsible for User profile pages.
 */

public class ProfilePageServlet extends HttpServlet {

	// Store class that gives access to Users
	private UserStore userStore;

	// Store class that gives access to Messages.
	private MessageStore messageStore;
	
	/** Set up state for handling profile requests. */
  @Override
  public void init() throws ServletException {
	  super.init();
	  setMessageStore(MessageStore.getInstance());
	  setUserStore(UserStore.getInstance());
  }

  /**
   * Sets the MessageStore used by this servlet. This function provides a common setup method for
   * use by the test framework or the servlet's init() function.
   */
  void setMessageStore(MessageStore messageStore) {
    this.messageStore = messageStore;
  }

  /**
   * Sets the UserStore used by this servlet. This function provides a common setup method for use
   * by the test framework or the servlet's init() function.
   */
  void setUserStore(UserStore userStore) {
    this.userStore = userStore;
  }

	/**
	 * This function fires when a user navigates to the profile page. It grabs the
	 * user name from the URL, finds the corresponding User, and fetches the user
	 * info. It then forwards to users.jsp for rendering
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
	
		String requestUrl = request.getRequestURI();
	  String username = requestUrl.substring("/users/".length());
	  
	  User user = userStore.getUser(username);
	  if(user == null) {
	  	// couldn't find user, redirect to home
	  	System.out.println("User was non existent: " + username);
	  	response.sendRedirect("/conversations");
	  	return;
	  }
	  
	  System.out.println(username);
	  System.out.println(user.getId());
	  
	  UUID userId = user.getId();
	  
	  List<Message> messages = messageStore.getMessagesFromUser(userId);
	  
	  request.setAttribute("user", user);
	  request.setAttribute("messages", messages);
	  
		request.getRequestDispatcher("/WEB-INF/view/users.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
	
		String username = (String) request.getSession().getAttribute("user");
		if(username == null) {
			//user is not logged in, don't let them change the info
			System.out.println("User not found: " + username);
			return;
		}
		
		User user = userStore.getUser(username);
		if(user == null) {
			//couldn't find user, don't let them change info
			response.sendRedirect("/WEB-INF/index.jsp");
			return;
		}
		
		String requestUrl = request.getRequestURI();
		String profileUsername = requestUrl.substring("/users/".length());
		
		if(!username.equals(profileUsername)) {
			request.setAttribute("error", "You are not authorized to change this.");
			return;
		}
		
		String bio = request.getParameter("bio");
		
		User profileUser = userStore.getUser(profileUsername);
		profileUser.setBio(bio);
						
		// redirect to a GET request
		response.sendRedirect("/users/" + username);
	}

}
