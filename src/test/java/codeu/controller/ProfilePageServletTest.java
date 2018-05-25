// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package codeu.controller;

import codeu.model.data.Conversation;
import codeu.model.data.Message;
import codeu.model.data.User;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;

public class ProfilePageServletTest {

  private ProfilePageServlet profilePageServlet;
  private HttpServletRequest mockRequest;
  private HttpSession mockSession;
  private HttpServletResponse mockResponse;
  private RequestDispatcher mockRequestDispatcher;
  private MessageStore mockMessageStore;
  private UserStore mockUserStore;

  @Before
  public void setup() {
    profilePageServlet = new ProfilePageServlet();

    mockRequest = Mockito.mock(HttpServletRequest.class);
    mockSession = Mockito.mock(HttpSession.class);
    Mockito.when(mockRequest.getSession()).thenReturn(mockSession);

    mockResponse = Mockito.mock(HttpServletResponse.class);
    mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);
    Mockito.when(mockRequest.getRequestDispatcher("/WEB-INF/view/users.jsp"))
        .thenReturn(mockRequestDispatcher);

    mockMessageStore = Mockito.mock(MessageStore.class);
    profilePageServlet.setMessageStore(mockMessageStore);

    mockUserStore = Mockito.mock(UserStore.class);
    profilePageServlet.setUserStore(mockUserStore);
  }

  @Test
  public void testDoGet() throws IOException, ServletException {
    Mockito.when(mockRequest.getRequestURI()).thenReturn("/users/test_user");

    UUID fakeUserId = UUID.randomUUID();
    User fakeUser =
        new User(fakeUserId, "test_user", "password", "test_bio", Instant.now());
    Mockito.when(mockUserStore.getUser("test_user"))
        .thenReturn(fakeUser);

    List<Message> fakeMessageList = new ArrayList<>();
    fakeMessageList.add(
        new Message(
            UUID.randomUUID(),
            UUID.randomUUID(),
            fakeUserId,
            "test message",
            Instant.now()));
    Mockito.when(mockMessageStore.getMessagesFromUser(fakeUserId))
        .thenReturn(fakeMessageList);

    profilePageServlet.doGet(mockRequest, mockResponse);

    verify(mockRequest).setAttribute("user", fakeUser);
    verify(mockRequest).setAttribute("messages", fakeMessageList);
    verify(mockRequestDispatcher).forward(mockRequest, mockResponse);
  }

  @Test
  public void testDoGet_UserNotFound() throws IOException, ServletException {
    Mockito.when(mockRequest.getRequestURI()).thenReturn("/users/user_dne");
    Mockito.when(mockSession.getAttribute("user")).thenReturn("test_user");

    User fakeUser = new User(UUID.randomUUID(), "test_user", "password", "test_bio", Instant.now());
    Mockito.when(mockUserStore.getUser("test_user")).thenReturn(fakeUser);

    Mockito.when(mockUserStore.getUser("user_dne"))
        .thenReturn(null);

    profilePageServlet.doGet(mockRequest, mockResponse);

    verify(mockResponse).sendRedirect("/conversations");
  }

  @Test
  public void testDoPost_InvalidUser() throws IOException, ServletException {
    Mockito.when(mockSession.getAttribute("user")).thenReturn("test_username");
    Mockito.when(mockUserStore.getUser("test_username")).thenReturn(null);

    profilePageServlet.doPost(mockRequest, mockResponse);

    verify(mockResponse).sendRedirect("/WEB-INF/index.jsp");
  }

  @Test
  public void testDoPost_UnauthorizedUser() throws IOException, ServletException {
    Mockito.when(mockRequest.getRequestURI()).thenReturn("/users/test_profile");

    UUID fakeUserId = UUID.randomUUID();
    User fakeUser =
        new User(fakeUserId, "test_user", "password", "test_bio", Instant.now());

    Mockito.when(mockSession.getAttribute("user")).thenReturn("test_user");
    Mockito.when(mockUserStore.getUser("test_user")).thenReturn(fakeUser);

    profilePageServlet.doPost(mockRequest, mockResponse);

    verify(mockRequest).setAttribute("error", "You are not authorized to change this.");
  }

  @Test
  public void testDoPost_UpdatesBio() throws IOException, ServletException {
    Mockito.when(mockRequest.getRequestURI()).thenReturn("/users/test_user");

    UUID fakeUserId = UUID.randomUUID();
    User fakeUser =
        new User(fakeUserId, "test_user", "password", "test_bio", Instant.now());

    Mockito.when(mockSession.getAttribute("user")).thenReturn("test_user");
    Mockito.when(mockUserStore.getUser("test_user")).thenReturn(fakeUser);

    String newBio = "new bio";

    Mockito.when(mockRequest.getParameter("bio")).thenReturn(newBio);

    profilePageServlet.doPost(mockRequest, mockResponse);

    Assert.assertEquals(newBio, fakeUser.getBio());
    verify(mockResponse).sendRedirect("/users/test_user");

  }

}
