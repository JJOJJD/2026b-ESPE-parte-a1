package es.upm.grise.cruiseControl;

import es.upm.grise.cruiseControl.exceptions.CannotSetSpeedLimitException;
import es.upm.grise.cruiseControl.exceptions.IncorrectSpeedLimitException;
import es.upm.grise.cruiseControl.exceptions.IncorrectSpeedSetException;
import es.upm.grise.cruiseControl.exceptions.SpeedSetAboveSpeedLimitException;

public class CruiseControl {
	
	private RoadInformation roadInformation;
	private Speedometer speedometer;
	private Integer speedLimit;
	private Integer speedSet;
	private boolean enabled = false;
	
	/*
	 * Constructor
	 */
	
	public CruiseControl(RoadInformation roadInformation, Speedometer speedometer) {
		this.roadInformation = roadInformation;
		this.speedometer = speedometer;
		this.speedSet = null;
		this.speedLimit = null;
	}
	
	/*
	 * Method to code/test
	 */
	
	public void setSpeedSet(int speedSet) throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		if (speedSet <= 0) {
			throw new IncorrectSpeedSetException();
		}
		if (this.speedLimit != null && speedSet > this.speedLimit) {
			throw new SpeedSetAboveSpeedLimitException();
		}
		this.speedSet = speedSet;
		this.enabled = true;
	}
	
	/* 
	 * Method to code/test
	 */
	
	public void setSpeedLimit(int speedLimit) throws IncorrectSpeedLimitException, CannotSetSpeedLimitException {
		if (speedLimit <= 0) {
			throw new IncorrectSpeedLimitException();
		}
		if (this.speedSet != null) {
			throw new CannotSetSpeedLimitException();
		}
		this.speedLimit = speedLimit;
	}
	
	/* 
	 * Method to code/test
	 */
	
	public void disable() {
		this.enabled = false;
		this.speedSet = null;
	}
	
	public Response nextCommand() {
		Response response = new Response();
		
		if (!enabled || this.speedSet == null) {
			response.command = Command.IDLE;
			return response;
		}
		
		int currentSpeed = speedometer.getCurrentSpeed();
		
		if (currentSpeed > this.speedSet) {
			response.command = Command.REDUCE;
			return response;
		}
		if (currentSpeed < roadInformation.getMinSpeed()) {
			response.command = Command.INCREASE;
			return response;
		}
		if (currentSpeed < this.speedSet) {
			response.command = Command.INCREASE;
			return response;
		}
		if (currentSpeed > roadInformation.getMaxSpeed()) {
			response.command = Command.REDUCE;
			return response;
		}
		if (currentSpeed == this.speedSet) {
			response.command = Command.KEEP;
			return response;
		}
		
		response.command = Command.IDLE;
		return response;
	}
	
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
