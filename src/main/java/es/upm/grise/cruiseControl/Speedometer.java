package es.upm.grise.cruiseControl;

public class Speedometer {
	
	private int fakeCurrentSpeed = 100;
	
	// Useful for testing
	public Speedometer() {		
	}
	
	// Useful for testing
	public Speedometer(int fakeCurrentSpeed) {
		
		this.fakeCurrentSpeed = fakeCurrentSpeed;
		
	}

	// Get the current speed
	int getCurrentSpeed() {
		
		return fakeCurrentSpeed;
		
	}

}
