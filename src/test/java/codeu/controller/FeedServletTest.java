package codeu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;

public class FeedServletTest {

  private FeedServlet feedServlet;
  private HttpServletRequest mockRequest;
  private PrintWriter mockPrintWriter;
  private HttpServletResponse mockResponse;

  @Before
  public void setup() throws IOException {
    feedServlet = new FeedServlet();
    mockRequest = Mockito.mock(HttpServletRequest.class);
    mockPrintWriter = Mockito.mock(PrintWriter.class);
    mockResponse = Mockito.mock(HttpServletResponse.class);
    Mockito.when(mockResponse.getWriter()).thenReturn(mockPrintWriter);
  }

  @Test
  public void testDoGet() throws IOException, ServletException {
    feedServlet.doGet(mockRequest, mockResponse);

    verify(mockPrintWriter).println("<h1>FeedServlet GET request.</h1>");
  }
}
