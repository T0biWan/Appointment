package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import classes.Appointment;
import exceptions.WrongDateException;
import exceptions.WrongTimeException;

public class AppointmentTest {
	
	static Appointment apDefault, apSpecific, apSpecific2;
	
	@BeforeClass
    public static void beforeClass() throws WrongTimeException, WrongDateException{
		apDefault = new Appointment(); 
		apSpecific = new Appointment("12.12.2015", "12:00", "13:00", "Uni", "Mathe Klausur", "Wir schreiben eine dicke Klausur");
		apSpecific2 = new Appointment("05.04.2015", "10:00", "14:00", "Persönlich", "Geburtstag", "Feiern");
	}

	@Test
	public void createDefaultAppointment() throws WrongTimeException, WrongDateException {
		new Appointment();
	}

	@Test
	public void createSpecificAppointment() throws WrongTimeException, WrongDateException {
		new Appointment("12.12.2015", "12:00", "13:00", "Uni", "Mathe Klausur", "Wir schreiben eine dicke Klausur");
	}
	
	@Test
	public void setDatum() throws WrongDateException {
		apDefault.setDatum("18.12.2006");
		assertEquals("18.12.2006", apDefault.getDatum());
	}
	
	@Test
	public void setStartUhrzeit() throws WrongTimeException {
		apDefault.setStartUhrzeit("09:00");
		assertEquals("09:00", apDefault.getStartUhrzeit());
	}
	
	@Test
	public void setEndUhrzeit() throws WrongTimeException {
		apDefault.setEndUhrzeit("10:00");
		assertEquals("10:00", apDefault.getEndUhrzeit());
	}
	
	@Test
	public void setTerminkategorie() {
		apDefault.setTerminkategorie("Test Katgorie");
		assertEquals("Test Katgorie", apDefault.getTerminkategorie());
	}
	
	@Test
	public void setTerminbezeichnung() {
		apDefault.setTerminbezeichnung("Test Bezeichnung");
		assertEquals("Test Bezeichnung", apDefault.getTerminbezeichnung());
	}
	
	@Test
	public void setTerminbeschreibung() {
		apDefault.setTerminbeschreibung("Test Beschreibung");
		assertEquals("Test Beschreibung", apDefault.getTerminbeschreibung());
	}
	
	/*
	 * Specific AP Test 
	 */
	
	@Test
	public void getDatum() throws WrongDateException  {
		assertEquals("12.12.2015", apSpecific.getDatum());
	}
	
	@Test
	public void getStartUhrzeit() throws WrongTimeException {
		assertEquals("12:00", apSpecific.getStartUhrzeit());
	}
	
	@Test
	public void getEndUhrzeit() throws WrongTimeException {
		assertEquals("13:00", apSpecific.getEndUhrzeit());
	}
	
	@Test
	public void getTerminkategorie() {
		assertEquals("Uni", apSpecific.getTerminkategorie());
	}
	
	@Test
	public void getTerminbezeichnung() {
		assertEquals("Mathe Klausur", apSpecific.getTerminbezeichnung());
	}
	
	@Test
	public void getTerminbeschreibung() {
		assertEquals("Wir schreiben eine dicke Klausur", apSpecific.getTerminbeschreibung());
	}
	
	@Test
	public void getDauer() {
		assertEquals("1:0", apSpecific.getDauer());
	}

	/*
	 * Null String Test
	 */
	
	@Test (expected = WrongDateException.class)
	public void setNullDatum() throws WrongDateException  {
		apDefault.setDatum(null);
	}
	
	@Test (expected = WrongTimeException.class)
	public void setNullStartUhrzeit() throws WrongTimeException {
		apDefault.setStartUhrzeit(null);
	}
	
	@Test (expected = WrongTimeException.class)
	public void setNullEndUhrzeit() throws WrongTimeException {
		apDefault.setEndUhrzeit(null);
	}
	
	@Test
	public void setNullTerminkategorie() {
		apDefault.setTerminkategorie(null);
		assertEquals(null, apDefault.getTerminkategorie());
	}
	
	@Test
	public void setNullTerminbezeichnung() {
		apDefault.setTerminbezeichnung(null);
		assertEquals(null, apDefault.getTerminbezeichnung());
	}
	
	@Test
	public void setNullTerminbeschreibung() {
		apDefault.setTerminbeschreibung(null);
		assertEquals(null, apDefault.getTerminbeschreibung());
	}
	
	/*
	 * Empty String Test
	 */
	
	@Test (expected = WrongDateException.class)
	public void setEmptyDatum() throws WrongDateException  {
		apDefault.setDatum("");
	}
	
	@Test (expected = WrongTimeException.class)
	public void setEmptyStartUhrzeit() throws WrongTimeException {
		apDefault.setStartUhrzeit("");
	}
	
	@Test (expected = WrongTimeException.class)
	public void setEmptyEndUhrzeit() throws WrongTimeException {
		apDefault.setEndUhrzeit("");
	}
	
	@Test
	public void setEmptyTerminkategorie() {
		apDefault.setTerminkategorie("");
		assertEquals("", apDefault.getTerminkategorie());
	}
	
	@Test
	public void setvTerminbezeichnung() {
		apDefault.setTerminbezeichnung("");
		assertEquals("", apDefault.getTerminbezeichnung());
	}
	
	@Test
	public void setEmptyTerminbeschreibung() {
		apDefault.setTerminbeschreibung("");
		assertEquals("", apDefault.getTerminbeschreibung());
	}
	
	/*
	 * Wrong Time Test
	 * */
	
	@Test (expected = WrongTimeException.class)
	public void setStartUhrzeitAfterEndUhrzeit() throws WrongTimeException {
		apSpecific2.setStartUhrzeit("23:00");
	}
	
	@Test (expected = WrongTimeException.class)
	public void setEndUhrzeitBeforStartUhrzeit() throws WrongTimeException {
		apSpecific2.setEndUhrzeit("01:00");
	}
	
	@Test (expected = WrongTimeException.class)
	public void setStartUhrzeitOver24() throws WrongTimeException {
		apSpecific2.setStartUhrzeit("25:00");
	}
	
	@Test (expected = WrongTimeException.class)
	public void setEndUhrzeitOver24() throws WrongTimeException {
		apSpecific2.setEndUhrzeit("24:01");
	}
}
