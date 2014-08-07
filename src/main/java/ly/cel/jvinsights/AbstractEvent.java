package ly.cel.jvinsights;

/*
 * Classes that extend AbstractEvent can be used to add arbitrary paramaters
 * to send along with an event to New Relic
 * 
 * @author Thomas Schreiber <thomas@cel.ly>
 */
public abstract class AbstractEvent {
  protected String appName;
  protected String eventType;
  
  abstract public AbstractEvent clone();
  
  /*
   * Return the eventType for this event
   * 
   * @return String
   */
  public String getEventType() {
    return this.eventType;
  }
  
  /*
   * Set the eventType for this event
   * 
   * @param eventType  String to set as eventType
   */
  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

  /*
   * Getter for appName
   * 
   * @return String
   */
  public String getAppName() {
    return appName;
  }
  
  /*
   * Setter for appName
   * 
   * @param appName  String to set as appName
   */
  public void setAppName(String appName) {
    this.appName = appName;
  }
  
}
