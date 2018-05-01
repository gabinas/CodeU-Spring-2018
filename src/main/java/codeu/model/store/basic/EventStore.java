package codeu.model.store.basic;

import java.util.List;
import java.util.ArrayList;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;
import codeu.model.data.Message;
import codeu.model.data.Conversation;
import codeu.model.data.User;
import codeu.model.data.Event;
import codeu.model.store.persistence.PersistentStorageAgent;

public class EventStore {

  List<Conversation> conversations;
  List<Message> messages;
  List<User> users;

  public static EventStore getInstance() {
    EventStore instance = new EventStore();

    List<Conversation> conversations = ConversationStore.getInstance().getAllConversations();
    List<Message> messages = MessageStore.getInstance().getAllMessages();
    List<User> users = UserStore.getInstance().getAllUsers();

    instance.setUsers(users);
    instance.setMessages(messages);
    instance.setConversations(conversations);

    return instance;
  }

  /**
   * Instance getter function used for testing. Supply a mock for PersistentStorageAgent.
   *
   * @param messageStore, conversationStore, and userStore, mocks used for testing
   */
  public static EventStore getTestInstance(MessageStore messageStore, ConversationStore conversationStore, UserStore userStore) {
    EventStore instance = new EventStore();

    List<Conversation> conversations = conversationStore.getAllConversations();
    List<Message> messages = messageStore.getAllMessages();
    List<User> users = userStore.getAllUsers();

    instance.setUsers(users);
    instance.setMessages(messages);
    instance.setConversations(conversations);

    return instance;
  }

  /**
   * Returns a list of all events up to date in chronological order
   */
  public List<Event> listAllEvents() {

    List<Event> events = new ArrayList<>();
    List<Event> co = new ArrayList<>();
    List<Event> me = new ArrayList<>();
    List<Event> us = new ArrayList<>();

    for (Conversation c : conversations) 
      co.add(c);
  
    for (Message m : messages) 
      me.add(m);

    for (User u : users)
      us.add(u);
    
    events = mergeLists(co, me);
    events = mergeLists(events, us);

    return events;
  }
  

  /**
   * Takes two sorted lists and merges while keeping the sort
   * @param firstList
   * @param secondList
   * @return sorted merge of second and first list
   */
  public List<Event> mergeLists(List<Event> firstList, List<Event> secondList) {
    List<Event> output = new ArrayList<>();

    // Create compare to method for if statements
    while (!firstList.isEmpty() && !secondList.isEmpty()) {
      if (firstList.get(0).getCreationTime().compareTo(secondList.get(0).getCreationTime()) < 0) {
        output.add(firstList.get(0));
        firstList.remove(0);
      } else {
        output.add(secondList.get(0));
        secondList.remove(0);
      }
    }

    if (!firstList.isEmpty()) {
      output.addAll(firstList);
    }

    if (!secondList.isEmpty()) {
      output.addAll(secondList);
    }

    return output;
  }

  /** Sets the List of Conversations stored by this EventStore. */
  public void setConversations(List<Conversation> conversations) {
    this.conversations = conversations;
  }

  /** Sets the List of Users stored by this EventStore. */
  public void setUsers(List<User> users) {
    this.users = users;
  }

  /** Sets the List of Messages stored by this EventStore. */
  public void setMessages(List<Message> messages) {
    this.messages = messages;
  }
}
