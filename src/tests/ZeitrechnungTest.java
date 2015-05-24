package tests;

import static org.junit.Assert.*;
import klassen.Zeitrechnung;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import exceptions.FormatException;
import exceptions.TimeException;

public class ZeitrechnungTest {
	//Attribute
	Zeitrechnung t		= new Zeitrechnung();
	String start		= "12:00";
	
	//StringTimeToIntSeconds
	@Test (expected = FormatException.class)
	public void testStringTimeToIntSecondsException1 () throws FormatException, TimeException {
		t.stringTimeToIntSeconds("5");
	}
	
	@Test (expected = TimeException.class)
	public void testStringTimeToIntSecondsException2 () throws FormatException, TimeException {
		t.stringTimeToIntSeconds("24:59");
	}
	
	@Test 
	public void testStringTimeToIntSeconds () throws FormatException, TimeException {
		assertEquals(43200, t.stringTimeToIntSeconds(start));
		assertEquals(0, t.stringTimeToIntSeconds(":"));
	}
	
	//StundeZuSekunde
	@Test
	public void testStundeZuSekunde () {
		assertEquals(43200, t.stundeZuSekunde(12));
	}
	
	//MinuteZuSekunde
	@Test
	public void testMinuteZuSekunde () {
		assertEquals(720, t.minuteZuSekunde(12));
	}
}
