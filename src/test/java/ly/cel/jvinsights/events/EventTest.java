package ly.cel.jvinsights.events;

import junit.framework.TestCase;
import ly.cel.jvinsights.EventManager;
import ly.cel.jvinsights.client.Client;
import ly.cel.jvinsights.config.ConfigProperties;
import ly.cel.jvinsights.models.TestEvent;

public class EventTest extends TestCase {

  public EventTest(String name) {
    super(name);
  }
  
  public void testEvent() throws Exception{
    Client client = new Client(ConfigProperties.getValue(ConfigProperties.ACCOUNT_ID_PROPERTY_KEY), 
                               ConfigProperties.getValue(ConfigProperties.ACCOUNT_KEY_PROPERTY_KEY),
                               ConfigProperties.getValue(ConfigProperties.QUERY_KEY_PROPERTY_KEY));
    EventManager eventManager = new EventManager(client, "ly.cel.jvinsights.events.EventTest:testEvent");
    
    TestEvent testEvent = new TestEvent("one", "two");
    
    eventManager.persist(testEvent);
    eventManager.flush();
  }
}
