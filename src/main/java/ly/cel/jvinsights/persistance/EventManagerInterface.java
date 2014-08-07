package ly.cel.jvinsights.persistance;

import ly.cel.jvinsights.AbstractEvent;

/*
 * This interface can be implemented in order to create a utility to queue the
 * events and queries, and then send them as a batch to the NR API.
 * 
 * @author Thomas Schreiber <thomas@cel.ly>
 */
public interface EventManagerInterface {
  
  /*
   * add an event to the persit queue
   * 
   * @param event  The event to add to the persit queue
   */
  public void persist(AbstractEvent event);
  
  /*
   * send persisted events to the New Relic API
   */
  public void flush();
}
