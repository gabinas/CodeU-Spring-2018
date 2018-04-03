package codeu.model.data;

import java.util.List;
import java.util.ArrayList;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;

public class ActivityFeed {


  List<Event> conversations = new ArrayList<>();
  List<Event> messages = new ArrayList<>();
  List<Event> users = new ArrayList<>();
  List<Event> events = new ArrayList<>();

  public void listAllEvents(){
    conversations.addAll(ConversationStore.getInstance().getAllConversations());
    messages.addAll(MessageStore.getInstance().getAllMessages());
    users.addAll(UserStore.getInstance().getAllUsers());
    events = mergeLists(conversations, messages);
    events = mergeLists(events, users);
  }

  public List<Event> mergeLists(List<Event> firstList, List<Event> secondList){
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

    if(!firstList.isEmpty()){
      output.addAll(firstList);
    }

    if(!secondList.isEmpty()){
      output.addAll(secondList);
    }

    return output;
  }

}
