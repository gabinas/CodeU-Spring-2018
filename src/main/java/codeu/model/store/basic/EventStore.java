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

public class EventStore {

  List<Conversation> conversations = ConversationStore.getInstance().getAllConversations();
  List<Message> messages = MessageStore.getInstance().getAllMessages();
  List<User> users = UserStore.getInstance().getAllUsers();

  /**
   * Clones every list of events, and returns all of the events up to date
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
}
