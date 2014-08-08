package ly.cel.jvinsights.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

/*
 * A basic client for connecting to the New Relic insights API
 * 
 * @author Thomas Schreiber
 */
public class Client implements ClientInterface {
  
  private static String URL_PREFIX = "https://insights-collector.newrelic.com/v1/accounts/";
  
  private String accountId;
  private String insertKey;
  private String queryKey;
  
  /*
   * Default Constructor
   * 
   * @param accountId
   * @param insertKey
   * @param queryKey
   */
  public Client(String accountId, String insertKey, String queryKey) {
    this.accountId = accountId;
    this.insertKey = insertKey;
    this.queryKey = queryKey;
  }

  /*
   * (non-Javadoc)
   * @see ly.cel.jvinsights.client.ClientInterface#insert(org.json.JSONArray)
   */
  public JSONObject insert(JSONArray json) {
    String url = URL_PREFIX + this.accountId + "/events";
    String response = post(url, this.insertKey, json);
    if (response != null && response.length()>0) {
      return new JSONObject(response);
    }
    return new JSONObject();
  }

  /*
   * (non-Javadoc)
   * @see ly.cel.jvinsights.client.ClientInterface#query(java.lang.String)
   */
  public JSONObject query(String nrql) {
    String url = URL_PREFIX + this.accountId + "/query";
    String response = get(url, this.queryKey, nrql);
    if (response != null && response.length()>0) {
      return new JSONObject(response);
    }
    return new JSONObject();
  }

  /*
   * A helper method to post a json array to an endpoint
   * 
   * @param endpoint  The url endpoint of the api
   * @param key       The secret key to post events 
   * @param json      The data to be sent
   * 
   * @return String
   */
  private String post(String endpoint, String key, JSONArray json) {
    String line;
    String response = "";
    byte[] postDataBytes = null;

    if (json != null) {
      try {
        postDataBytes = json.toString().getBytes("UTF-8");
      } catch (UnsupportedEncodingException e) {
            
      }
    }

    try {
      URL url = new URL(endpoint);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setInstanceFollowRedirects(false);
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/json"); 
      connection.setRequestProperty("X-Insert-Key", key);
      connection.setRequestProperty("Content-Length", "" + Integer.toString(postDataBytes.length));
      connection.setDoOutput(true);
      connection.getOutputStream().write(postDataBytes);
      
      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
      while ((line = in.readLine()) != null) {
        response += line;
      }

    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return response;
  }

  /*
   * A helper method to make a NRQL query from a given endpoint
   * 
   * @param endpoint  The url endpoint for the API
   * @param key       The secret query key
   * @param nrql      The query
   * 
   * @return String
   */
  private String get(String endpoint, String key, String nrql) {
    String line;
    String response = "";

    try {
      URL url = new URL(endpoint);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setInstanceFollowRedirects(false);
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Accept", "application/json"); 
      connection.setRequestProperty("X-Query-Key", key);
      connection.setRequestProperty("Content-Length", "" + Integer.toString(nrql.getBytes().length));
      connection.setDoOutput(true);
      connection.setDoInput(true);
      
      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
      while ((line = in.readLine()) != null) {
        response += line;
      }

    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return response;
  }
}
