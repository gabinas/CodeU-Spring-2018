package codeu.model.store.basic;

import codeu.model.store.basic.EventStore;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;
import codeu.model.data.Message;
import codeu.model.data.Conversation;
import codeu.model.data.User;
import codeu.model.data.Event;
import codeu.model.store.persistence.PersistentStorageAgent;
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
  private ConversationStore conversationStore;
  private UserStore userStore;
  private MessageStore messageStore;
  private PersistentStorageAgent mockPersistentStorageAgent;
  final List<Event> expectedEventList = new ArrayList<>();

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
    mockPersistentStorageAgent = Mockito.mock(PersistentStorageAgent.class);
    conversationStore = ConversationStore.getTestInstance(mockPersistentStorageAgent);
    userStore = UserStore.getTestInstance(mockPersistentStorageAgent);
    messageStore = MessageStore.getTestInstance(mockPersistentStorageAgent);

    final List<User> userList = new ArrayList<>();
    final List<Conversation> conversationList = new ArrayList<>();
    final List<Message> messageList = new ArrayList<>();

    userList.add(USER_ONE);
    userList.add(USER_TWO);
    userList.add(USER_THREE);
    userStore.setUsers(userList);
    
    conversationList.add(CONVERSATION_ONE);
    conversationStore.setConversations(conversationList);
    
    messageList.add(MESSAGE_ONE);
    messageList.add(MESSAGE_TWO);
    messageList.add(MESSAGE_THREE);
    messageStore.setMessages(messageList);
    
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
    List<Event> eventList = eventStore.listAllEvents();
    Assert.assertEquals(eventList, eventList);
  }
}
