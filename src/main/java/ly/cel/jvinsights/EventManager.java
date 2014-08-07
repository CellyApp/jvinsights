package ly.cel.jvinsights;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cedarsoftware.util.io.JsonWriter;

import ly.cel.jvinsights.client.ClientInterface;
import ly.cel.jvinsights.persistance.EventManagerInterface;

/*
 * Manages the events to be sent to the New Relic Insights API
 * 
 * @author Thomas Schreiber <thomas@cel.ly>
 */
public class EventManager implements EventManagerInterface {
  private List<AbstractEvent> persistedEvents;
  private ClientInterface client;
  private String appName;
  
  /*
   * Default Constructor
   * 
   * @param client
   * @param appName
   */
  public EventManager(ClientInterface client, String appName) {
    this.client = client;
    this.appName = appName;
    this.persistedEvents = new ArrayList<AbstractEvent>();
  }

  /*
   * (non-Javadoc)
   * @see ly.cel.jvinsights.persistance.EventManagerInterface#persist(ly.cel.jvinsights.AbstractEvent)
   */
  public void persist(AbstractEvent event) {
    AbstractEvent clonedEvent = event.clone();
    clonedEvent.setAppName(appName);
    persistedEvents.add(clonedEvent);
  }

  /*
   * (non-Javadoc)
   * @see ly.cel.jvinsights.persistance.EventManagerInterface#flush()
   */
  public void flush() {
    try {
      JSONArray json = new JSONArray();
      for (AbstractEvent e : this.persistedEvents) {
        String jsonString = JsonWriter.objectToJson(e);
        JSONObject jo = new JSONObject(jsonString);
        jo.remove("@type");
        json.put(jo);
      }
      this.client.insert(json);
      this.persistedEvents.clear();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
