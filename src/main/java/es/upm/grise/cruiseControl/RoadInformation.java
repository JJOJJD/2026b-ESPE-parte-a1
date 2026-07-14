package es.upm.grise.cruiseControl;

public class RoadInformation {
	
	// As in an Spanish highway
	private int fakeMaxSpeed = 120;
	private int fakeMinSpeed = 60;

	// Useful for testing
	public RoadInformation() {
	}

	// Useful for testing
	public RoadInformation(int fakeMaxSpeed, int fakeMinSpeed) {
		this.fakeMaxSpeed = fakeMaxSpeed;
		this.fakeMinSpeed = fakeMinSpeed;
	}
	
	// Returns the road's max speed
	public int getMaxSpeed() {
		
		return fakeMaxSpeed;
		
	}
	
	// Returns the road's min speed
	public int getMinSpeed() {
		
		return fakeMinSpeed;
		
	}

}
