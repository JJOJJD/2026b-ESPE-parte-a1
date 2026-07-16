package es.upm.grise.cruiseControl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import es.upm.grise.cruiseControl.exceptions.CannotSetSpeedLimitException;
import es.upm.grise.cruiseControl.exceptions.IncorrectSpeedLimitException;
import es.upm.grise.cruiseControl.exceptions.IncorrectSpeedSetException;
import es.upm.grise.cruiseControl.exceptions.SpeedSetAboveSpeedLimitException;

class CruiseControlTest {

	@Test
	public void testSetSpeedSetValid() throws Exception {
		CruiseControl cc = new CruiseControl(new RoadInformation(), new Speedometer());
		cc.setSpeedSet(100);
		assertEquals(100, cc.getSpeedSet());
		assertEquals(true, cc.isEnabled());
	}

	@Test
	public void testSetSpeedSetZeroOrNegativeThrowsException() {
		CruiseControl cc = new CruiseControl(new RoadInformation(), new Speedometer());
		assertThrows(IncorrectSpeedSetException.class, () -> cc.setSpeedSet(0));
		assertThrows(IncorrectSpeedSetException.class, () -> cc.setSpeedSet(-10));
	}

	@Test
	public void testSetSpeedSetAboveSpeedLimitThrowsException() throws Exception {
		CruiseControl cc = new CruiseControl(new RoadInformation(), new Speedometer());
		cc.setSpeedLimit(120);
		assertThrows(SpeedSetAboveSpeedLimitException.class, () -> cc.setSpeedSet(130));
	}

	@Test
	public void testSetSpeedLimitValid() throws Exception {
		CruiseControl cc = new CruiseControl(new RoadInformation(), new Speedometer());
		cc.setSpeedLimit(120);
		assertEquals(120, cc.getSpeedLimit());
	}

	@Test
	public void testSetSpeedLimitZeroOrNegativeThrowsException() {
		CruiseControl cc = new CruiseControl(new RoadInformation(), new Speedometer());
		assertThrows(IncorrectSpeedLimitException.class, () -> cc.setSpeedLimit(0));
		assertThrows(IncorrectSpeedLimitException.class, () -> cc.setSpeedLimit(-5));
	}

	@Test
	public void testSetSpeedLimitWhenSpeedSetAlreadyInitializedThrowsException() throws Exception {
		CruiseControl cc = new CruiseControl(new RoadInformation(), new Speedometer());
		cc.setSpeedSet(100);
		assertThrows(CannotSetSpeedLimitException.class, () -> cc.setSpeedLimit(120));
	}

	@Test
	public void testDisable() throws Exception {
		CruiseControl cc = new CruiseControl(new RoadInformation(), new Speedometer());
		cc.setSpeedSet(100);
		cc.disable();
		assertFalse(cc.isEnabled());
		assertEquals(null, cc.getSpeedSet());
	}

	@Test
	public void testNextCommandIdleWhenNotEnabled() {
		CruiseControl cc = new CruiseControl(new RoadInformation(), new Speedometer());
		assertEquals(Command.IDLE, cc.nextCommand().command);
	}

	@Test
	public void testNextCommandIdleWhenDisabled() throws Exception {
		CruiseControl cc = new CruiseControl(new RoadInformation(), new Speedometer());
		cc.setSpeedSet(100);
		cc.disable();
		assertEquals(Command.IDLE, cc.nextCommand().command);
	}

	@Test
	public void testNextCommandReduceWhenCurrentSpeedAboveSpeedSet() throws Exception {
		RoadInformation road = new RoadInformation(120, 60);
		Speedometer speedo = new Speedometer(110);
		CruiseControl cc = new CruiseControl(road, speedo);
		cc.setSpeedSet(100);
		assertEquals(Command.REDUCE, cc.nextCommand().command);
	}

	@Test
	public void testNextCommandIncreaseWhenCurrentSpeedBelowMinSpeed() throws Exception {
		RoadInformation road = new RoadInformation(120, 60);
		Speedometer speedo = new Speedometer(50);
		CruiseControl cc = new CruiseControl(road, speedo);
		cc.setSpeedSet(70);
		assertEquals(Command.INCREASE, cc.nextCommand().command);
	}

	@Test
	public void testNextCommandIncreaseWhenCurrentSpeedBelowSpeedSet() throws Exception {
		RoadInformation road = new RoadInformation(120, 60);
		Speedometer speedo = new Speedometer(80);
		CruiseControl cc = new CruiseControl(road, speedo);
		cc.setSpeedSet(100);
		assertEquals(Command.INCREASE, cc.nextCommand().command);
	}

	@Test
	public void testNextCommandReduceWhenCurrentSpeedAboveMaxSpeed() throws Exception {
		RoadInformation road = new RoadInformation(120, 60);
		Speedometer speedo = new Speedometer(130);
		CruiseControl cc = new CruiseControl(road, speedo);
		cc.setSpeedSet(120);
		assertEquals(Command.REDUCE, cc.nextCommand().command);
	}

	@Test
	public void testNextCommandKeepWhenCurrentSpeedEqualsSpeedSet() throws Exception {
		RoadInformation road = new RoadInformation(120, 60);
		Speedometer speedo = new Speedometer(100);
		CruiseControl cc = new CruiseControl(road, speedo);
		cc.setSpeedSet(100);
		assertEquals(Command.KEEP, cc.nextCommand().command);
	}
}
