package com.jumpcloud.backendassignment;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

// Imports for GSON jar classes for associated JSON functionality 
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParseException;

//For test of average key-value pairs in getStats()
//import java.util.stream.Stream;

public class BackendAssignment {

  // Global JSON array of all data for call to getStats() to calculate averages
  static JsonArray at = new JsonArray();

  public static void main(String[] args) {
    	
    System.out.println("JumpCloud Backend Assignment");
    
    // Create new Gson object to process JSON data
    Gson gson = new Gson();

    // Manually create test data array since UI not developed to send data
    System.out.println("Input JSON:");
    
    Action actionObject = new Action("jump", 100); 
    String sJson = gson.toJson(actionObject);
    System.out.println("Element 1: " + sJson);
    addAction(sJson);
        
    actionObject = new Action("run", 75); 
    sJson = gson.toJson(actionObject);
    System.out.println("Element 2: " + sJson);
    addAction(sJson);
        
    actionObject = new Action("jump", 200); 
    sJson = gson.toJson(actionObject);
    System.out.println("Element 3: " + sJson);
    addAction(sJson);

    // Additional input entry to test averages for run action
    actionObject = new Action("run", 25); 
    sJson = gson.toJson(actionObject);
    System.out.println("Element 4: " + sJson);
    addAction(sJson);

    // Print array to confirm JSON data and format
    String aJson = gson.toJson(at);
    System.out.println("JSON Array: " + aJson);
    
    System.out.println("");

    // Test call for getStats() since UI not developed to call method directly
    String avgJson = getStats();
    System.out.println("Average JSON Array: " + avgJson);

    // Initial attempt to manually create JSON array for test data
    //JsonObject jo = null;
    //jo = generateJson(jo);
    //System.out.println(jo.toString());
    	 
  }

  // Method to concatenate JSON data to JSON array
  public static String addAction (String s) throws JsonParseException {
    
    try {
      at.add(new Gson().fromJson(s, JsonObject.class));
    } catch (JsonParseException jpe) {
      jpe.printStackTrace();
      return (jpe.getMessage());
    }

    return ("JSON element successfully added to array.");
  }

/*    
  public static JsonObject generateJson(JsonObject jo) throws JsonException {
    // Test code for educational purposes
    try {
      jo = Json.createObjectBuilder()
             .add("data", Json.createArrayBuilder()
               .add(Json.createObjectBuilder()
                 .add("action", "jump")
                 .add("time", "100"))
               .add(Json.createObjectBuilder()
                 .add("action", "run")
                 .add("time", "75"))
               .add(Json.createObjectBuilder()
                 .add("action", "jump")
                 .add("time", "200"))
			   ).build();
    
    } catch (JsonException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
      return jo;
  }
*/
    
  public static String getStats() throws JsonParseException{
	JsonArray avgJsonArray = new JsonArray();
	
	try {
      System.out.println("getStats():");		
	  // Create new Gson object to process JSON data
      Gson gson = new Gson(); 

      // Create array of Action objects from JSON
      Action[] actionArray = gson.fromJson(at, Action[].class);  
  	  for(Action a : actionArray) {
        System.out.println("Action object from JSON array: " + a.action + ", " + "Time: " + a.time);
      }

  	  // Create hash map with each unique action and its average time from Action array
      Map<String, Double> averages = 
        Arrays.stream(actionArray)
          .collect(Collectors.groupingBy(a -> a.getAction(),
            Collectors.averagingDouble(a -> a.getTime())));
    
      // Check hash map key and value pairs for debugging purposes
      //Stream.of(averages.keySet().toString()).forEach(System.out::println);
      //Stream.of(averages.values().toString()).forEach(System.out::println);

      // Create JSON array for each hash map key (action) value (average time) pair
      for (Entry<String, Double> mapEntry : averages.entrySet()) {
    	Action avgAction = new Action(mapEntry.getKey(), mapEntry.getValue());
    	avgJsonArray.add(new Gson().fromJson(gson.toJson(avgAction), JsonObject.class));
    	System.out.println("Average by action: " + gson.toJson(avgAction));
      } 
  	  return gson.toJson(avgJsonArray);
	}
	catch (JsonParseException jpe){
      jpe.printStackTrace();
      return (jpe.getMessage());
	}
  }
  
}
