package es.upm.grise.cruiseControl;

public class CruiseControl {
	
	private RoadInformation roadInformation;
	private Speedometer speedometer;
	private Integer speedLimit;
	private Integer speedSet;
	private boolean enabled = false;
	
	/*
	 * Constructor
	 */
	
	public CruiseControl(RoadInformation roadInformation, Speedometer speedometer) {}
	
	/*
	 * Method to code/test
	 */
	
	public void setSpeedSet(int speedSet) {}
	
	/* 
	 * Method to code/test
	 */
	
	public void setSpeedLimit(int speedLimit) {}
	
	/* 
	 * Method to code/test
	 */
	
	public void disable() {}
	
	/* 
	 * Others getters and setters
	 */
	
	public boolean isEnabled() {
		return enabled;
	}

	public Integer getSpeedLimit() {
		return speedLimit;
	}

	public Integer getSpeedSet() {
		return speedSet;
	}

}
