package klassen;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import exceptions.FormatException;
import exceptions.TimeException;

public class Main {

	public static void main(String[] args) {
		//Appointment erstellen & alle Methoden usw. ausführen.
		try {
//			Date			datumHeute		= new Date();
//			DateFormat gewünschtesZeitFormat = new SimpleDateFormat ("HH:mm");
//			System.out.println(gewünschtesZeitFormat.format(datumHeute));
			
//			System.out.println(zeit.stringTimeToIntSeconds(":"));
			
			Appointment testTermin1 = new Appointment("28.05.15", "Pamela besuchen", "17:00", "20:30", "Urlaub", "Dresden");
			Zeitrechnung zeit = new Zeitrechnung();
			System.out.println(testTermin1);
			testTermin1.changeTime("00:00", "21:00");
			System.out.println(testTermin1);
			
			Appointment testTermin2 = new Appointment();
			System.out.println(testTermin2);

		} catch (FormatException | TimeException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
