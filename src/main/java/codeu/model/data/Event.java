package codeu.model.data;

import java.time.Instant;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

public class Event implements Comparable<Event> {
  private final UUID id;
  private final Instant creation;

  public Event(UUID id, Instant creation) {
    this.id = id;
    this.creation = creation;
  }

  /** Returns the ID of this Event. */
  public UUID getId() {
    return id;
  }

  /** Returns the creation time of this Event. */
  public Instant getCreationTime(){
    return creation;
  }

  @Override
  public int compareTo(Event otherEvent) {
    if (this.getCreationTime().isBefore(otherEvent.getCreationTime())) {
      return -1;
    } else if(this.getCreationTime().isAfter(otherEvent.getCreationTime())) {
    	return 1;
    } else {
      return 0;
    }
  }
}
