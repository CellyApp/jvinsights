package ly.cel.jvinsights.client;

import org.json.JSONArray;
import org.json.JSONObject;

/*
 * An interface for developing a New Relic client
 * 
 * @author Thomas Schreiber <thomas@cel.ly>
 */
public interface ClientInterface {
  /*
   * This method is used to create a json event to be pushed to the NR API
   * 
   * @param json  An array of data representing a new event
   * 
   * @return JSONObject
   */
  public JSONObject insert(JSONArray json);
  
  /*
   * This method is used to create a query to be pushed to the NR API
   * 
   * @param nrql  The query string
   * 
   * @return JSONObject
   */
  public JSONObject query(String nrql);
}
