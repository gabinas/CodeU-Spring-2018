package codeu.controller;

import codeu.model.data.User;
import codeu.model.store.basic.UserStore;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;

public class RegisterServletTest {

 private RegisterServlet registerServlet;
 private HttpServletRequest mockRequest;
 private HttpServletResponse mockResponse;
 private RequestDispatcher mockRequestDispatcher;

 @Before
 public void setup() throws IOException {
   registerServlet = new RegisterServlet();
   mockRequest = Mockito.mock(HttpServletRequest.class);
   mockResponse = Mockito.mock(HttpServletResponse.class);
   mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);
   Mockito.when(mockRequest.getRequestDispatcher(Mockito.any()))
   .thenReturn(mockRequestDispatcher);
 }

 @Test
 public void testDoGet() throws IOException, ServletException {
   registerServlet.doGet(mockRequest, mockResponse);

   verify(mockRequest).getRequestDispatcher("/WEB-INF/view/register.jsp");
 }

 @Test
  public void testDoPost_badUsername() throws IOException, ServletException {
    Mockito.when(mockRequest.getParameter("username")).thenReturn("bad !@#$% username");

    registerServlet.doPost(mockRequest, mockResponse);

    verify(mockRequest)
        .setAttribute("error", "Please enter only letters, numbers, and spaces.");
    verify(mockRequestDispatcher).forward(mockRequest, mockResponse);
  }

  @Test
  public void testDoPost_newUser() throws IOException, ServletException {
    Mockito.when(mockRequest.getParameter("username")).thenReturn("test username");

    UserStore mockUserStore = Mockito.mock(UserStore.class);
    Mockito.when(mockUserStore.isUserRegistered("test username")).thenReturn(false);
    registerServlet.setUserStore(mockUserStore);

    registerServlet.doPost(mockRequest, mockResponse);

    ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

    verify(mockUserStore).addUser(userArgumentCaptor.capture());
    Assert.assertEquals(userArgumentCaptor.getValue().getName(), "test username");

    verify(mockResponse).sendRedirect("/login");
  }

  @Test
  public void testDoPost_existingUser() throws IOException, ServletException {
    Mockito.when(mockRequest.getParameter("username")).thenReturn("test username");

    UserStore mockUserStore = Mockito.mock(UserStore.class);
    Mockito.when(mockUserStore.isUserRegistered("test username")).thenReturn(true);
    registerServlet.setUserStore(mockUserStore);

    registerServlet.doPost(mockRequest, mockResponse);

    verify(mockUserStore, Mockito.never()).addUser(Mockito.any(User.class));

    verify(mockRequest)
        .setAttribute("error", "That username is already taken.");
    verify(mockRequestDispatcher).forward(mockRequest, mockResponse);
  }
}
