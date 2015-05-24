package tests;

import static org.junit.Assert.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import klassen.Appointment;

import org.junit.Before;
import org.junit.Test;

import exceptions.FormatException;
import exceptions.TimeException;

public class AppointmentTest {

	//Getter
	@Test
	public void testGetter() throws FormatException, TimeException {
		Appointment a = new Appointment("28.05.15", "Pamela besuchen", "17:00", "20:30", "Urlaub", "Dresden");
		assertEquals("Urlaub", a.getKategorie());
	}
	
//	@Test
//	public void testGetterProperty() throws FormatException, TimeException {
//		Appointment a = new Appointment("28.05.15", "Pamela besuchen", "17:00", "20:30", "Urlaub", "Dresden");
//		StringProperty kategorie = new SimpleStringProperty();
//		kategorie.set("Urlaub");
//		assertEquals(kategorie , a.kategorie());
//	}
	
	
	

}
