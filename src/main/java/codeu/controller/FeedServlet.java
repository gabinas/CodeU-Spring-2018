package codeu.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
  * Servlet class responsible for activity feed.
  */
public class FeedServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {

    request.getRequestDispatcher("/WEB-INF/view/feed.jsp").forward(request, response);
  }
}
