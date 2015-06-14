package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import klassen.Appointment;
import exceptions.FormatException;
import exceptions.StringIsEmptyException;
import exceptions.WertebereichException;
import exceptions.ZeitenKollisionException;


public class AppointmentTest {
	
	static Appointment apDefault, apSpecific, apSpecific2;
	
	@BeforeClass
    public static void beforeClass() throws FormatException, WertebereichException,ZeitenKollisionException,StringIsEmptyException{
		apDefault = new Appointment(); 
		apSpecific = new Appointment("12.12.2015", "12:00", "13:00", "Uni", "Mathe Klausur", "Wir schreiben eine dicke Klausur");
		apSpecific2 = new Appointment("05.04.2015", "10:00", "14:00", "Persönlich", "Geburtstag", "Feiern");
	}

	@Test
	public void createDefaultAppointment() throws FormatException, WertebereichException,ZeitenKollisionException,StringIsEmptyException {
		new Appointment();
	}

	@Test
	public void createSpecificAppointment() throws FormatException, WertebereichException,ZeitenKollisionException,StringIsEmptyException {
		new Appointment("12.12.2015", "12:00", "13:00", "Uni", "Mathe Klausur", "Wir schreiben eine dicke Klausur");
	}
	
	@Test
	public void setDatum() throws FormatException, WertebereichException,ZeitenKollisionException,StringIsEmptyException {
		apDefault.setDatum("18.12.2006");
		assertEquals("18.12.2006", apDefault.getDatum());
	}
	
	@Test
	public void setStartzeit() throws FormatException, WertebereichException,ZeitenKollisionException,StringIsEmptyException {
		apDefault.setStartzeit("09:00");
		assertEquals("09:00", apDefault.getStartzeit());
	}
	
	@Test
	public void setEndzeit() throws FormatException, WertebereichException,ZeitenKollisionException,StringIsEmptyException {
		apDefault.setEndzeit("10:00");
		assertEquals("10:00", apDefault.getEndzeit());
	}
	
	@Test
	public void setKategorie() {
		apDefault.setKategorie("Test Katgorie");
		assertEquals("Test Katgorie", apDefault.getKategorie());
	}
	
	@Test
	public void setTitel() {
		apDefault.setTitel("Test Bezeichnung");
		assertEquals("Test Bezeichnung", apDefault.getTitel());
	}
	
	@Test
	public void setNotiz() {
		apDefault.setNotiz("Test Beschreibung");
		assertEquals("Test Beschreibung", apDefault.getNotiz());
	}
	
	/*
	 * Specific AP Test 
	 */
	
	@Test
	public void getDatum() throws FormatException, WertebereichException,ZeitenKollisionException,StringIsEmptyException  {
		assertEquals("12.12.2015", apSpecific.getDatum());
	}
	
	@Test
	public void getStartzeit() throws FormatException, WertebereichException,ZeitenKollisionException,StringIsEmptyException {
		assertEquals("12:00", apSpecific.getStartzeit());
	}
	
	@Test
	public void getEndzeit() throws FormatException, WertebereichException,ZeitenKollisionException,StringIsEmptyException {
		assertEquals("13:00", apSpecific.getEndzeit());
	}
	
	@Test
	public void getKategorie() {
		assertEquals("Uni", apSpecific.getKategorie());
	}
	
	@Test
	public void getTitel() {
		assertEquals("Mathe Klausur", apSpecific.getTitel());
	}
	
	@Test
	public void getNotiz() {
		assertEquals("Wir schreiben eine dicke Klausur", apSpecific.getNotiz());
	}

	/*
	 * Null String Test
	 */
	
	@Test (expected = StringIsEmptyException.class)
	public void setNullDatum() throws FormatException, WertebereichException,ZeitenKollisionException,StringIsEmptyException  {
		apDefault.setDatum(null);
	}
	
	@Test (expected = StringIsEmptyException.class)
	public void setNullStartzeit() throws FormatException, WertebereichException,ZeitenKollisionException,StringIsEmptyException {
		apDefault.setStartzeit(null);
	}
	
	@Test (expected = StringIsEmptyException.class)
	public void setNullEndzeit() throws FormatException, WertebereichException,ZeitenKollisionException,StringIsEmptyException {
		apDefault.setEndzeit(null);
	}
	
	@Test
	public void setNullKategorie() {
		apDefault.setKategorie(null);
		assertEquals(null, apDefault.getKategorie());
	}
	
	@Test
	public void setNullTitel() {
		apDefault.setTitel(null);
		assertEquals(null, apDefault.getTitel());
	}
	
	@Test
	public void setNullNotiz() {
		apDefault.setNotiz(null);
		assertEquals(null, apDefault.getNotiz());
	}
	
	/*
	 * Empty String Test
	 */
	
	@Test (expected = StringIsEmptyException.class)
	public void setEmptyDatum() throws FormatException, WertebereichException,ZeitenKollisionException,StringIsEmptyException  {
		apDefault.setDatum("");
	}
	
	@Test (expected = StringIsEmptyException.class)
	public void setEmptyStartzeit() throws FormatException, WertebereichException,ZeitenKollisionException,StringIsEmptyException {
		apDefault.setStartzeit("");
	}
	
	@Test (expected = StringIsEmptyException.class)
	public void setEmptyEndzeit() throws FormatException, WertebereichException,ZeitenKollisionException,StringIsEmptyException {
		apDefault.setEndzeit("");
	}
	
	@Test
	public void setEmptyKategorie() {
		apDefault.setKategorie("");
		assertEquals("", apDefault.getKategorie());
	}
	
	@Test
	public void setvTitel() {
		apDefault.setTitel("");
		assertEquals("", apDefault.getTitel());
	}
	
	@Test
	public void setEmptyNotiz() {
		apDefault.setNotiz("");
		assertEquals("", apDefault.getNotiz());
	}
	
	/*
	 * Wrong Time Test
	 * */
	
	@Test (expected = ZeitenKollisionException.class)
	public void setStartzeitAfterEndzeit() throws FormatException, WertebereichException,ZeitenKollisionException,StringIsEmptyException {
		apSpecific2.setStartzeit("23:00");
	}
	
	@Test (expected = ZeitenKollisionException.class)
	public void setEndzeitBeforStartzeit() throws FormatException, WertebereichException,ZeitenKollisionException,StringIsEmptyException {
		apSpecific2.setEndzeit("01:00");
	}
	
	@Test (expected = ZeitenKollisionException.class)
	public void setStartzeitOver24() throws FormatException, WertebereichException,ZeitenKollisionException,StringIsEmptyException {
		apSpecific2.setStartzeit("25:00");
	}
	
	@Test (expected = ZeitenKollisionException.class)
	public void setEndzeitOver24() throws FormatException, WertebereichException,ZeitenKollisionException,StringIsEmptyException {
		apSpecific2.setEndzeit("24:01");
	}

}
