package com.jumpcloud.backendassignment;

public class Action {  
    // Variables
	public String action;
    public double time;
    
    // Constructor
    public Action (String action, double time) {
    	super();
    	this.action = action;
    	this.time = time;
    }
    
    // Getter and setter methods for variables
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
      this.action = action;
    }
      
    public double getTime() {
      return time;
    }

    public void setTime(int time) {
      this.time = time;
    }  
}    