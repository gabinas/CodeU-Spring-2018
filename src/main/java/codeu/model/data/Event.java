package codeu.model.data;

import java.time.Instant;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

public class Event {
  private final UUID id;
  private final Instant creation;

  public Event(UUID id, Instant creation){
    this.id = id;
    this.creation = creation;
  }

  /** Returns the ID of this User. */
  public UUID getId(){
    return id;
  }

  /** Returns the creation time of this User. */
  public Instant getCreationTime(){
    return creation;
  }

}
