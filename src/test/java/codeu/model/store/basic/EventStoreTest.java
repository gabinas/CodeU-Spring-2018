package codeu.model.store.basic;

import codeu.model.data.Event;
import codeu.model.data.User;
import codeu.model.data.Message;
import codeu.model.data.Conversation;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class EventStoreTest {

  private EventStore eventStore;
  final List<Event> expectedEventList = new ArrayList<>();
  final List<Event> userList = new ArrayList<>();
  final List<Event> conversationList = new ArrayList<>();
  final List<Event> messageList = new ArrayList<>();

  private final User USER_ONE =
      new User(UUID.randomUUID(), "test_username_one", "password one", Instant.ofEpochMilli(1000));
  private final User USER_TWO =
      new User(UUID.randomUUID(), "test_username_two", "password two", Instant.ofEpochMilli(2000));
  private final User USER_THREE =
      new User(UUID.randomUUID(), "test_username_three", "password three", Instant.ofEpochMilli(6000));

  private final UUID CONVERSATION_ID_ONE = UUID.randomUUID();
  private final Conversation CONVERSATION_ONE =
      new Conversation(
          CONVERSATION_ID_ONE, UUID.randomUUID(), "conversation_one", Instant.ofEpochMilli(3000));

  private final Message MESSAGE_ONE =
      new Message(
          UUID.randomUUID(),
          CONVERSATION_ID_ONE,
          UUID.randomUUID(),
          "message one",
          Instant.ofEpochMilli(4000));
  private final Message MESSAGE_TWO =
      new Message(
          UUID.randomUUID(),
          CONVERSATION_ID_ONE,
          UUID.randomUUID(),
          "message two",
          Instant.ofEpochMilli(5000));
  private final Message MESSAGE_THREE =
      new Message(
          UUID.randomUUID(),
          UUID.randomUUID(),
          UUID.randomUUID(),
          "message three",
          Instant.ofEpochMilli(7000));

  @Before
  public void setup() {
    eventStore = new EventStore();

    userList.add(USER_ONE);
    userList.add(USER_TWO);
    userList.add(USER_THREE);
    
    conversationList.add(CONVERSATION_ONE);
    
    messageList.add(MESSAGE_ONE);
    messageList.add(MESSAGE_TWO);
    messageList.add(MESSAGE_THREE);
    
    expectedEventList.add(USER_ONE);
    expectedEventList.add(USER_TWO);
    expectedEventList.add(CONVERSATION_ONE);
    expectedEventList.add(MESSAGE_ONE);
    expectedEventList.add(MESSAGE_TWO);
    expectedEventList.add(USER_THREE);
    expectedEventList.add(MESSAGE_THREE);
  }

  @Test
  public void testListAllEvents() {
    List<Event> eventList = eventStore.mergeLists(conversationList, messageList);
    eventList = eventStore.mergeLists(eventList, userList);
    Assert.assertEquals(expectedEventList, eventList);
  }

  private void assertEquals(Conversation expectedConversation, Conversation actualConversation) {
    Assert.assertEquals(expectedConversation.getId(), actualConversation.getId());
    Assert.assertEquals(expectedConversation.getOwnerId(), actualConversation.getOwnerId());
    Assert.assertEquals(expectedConversation.getTitle(), actualConversation.getTitle());
    Assert.assertEquals(
        expectedConversation.getCreationTime(), actualConversation.getCreationTime());
  }
}
