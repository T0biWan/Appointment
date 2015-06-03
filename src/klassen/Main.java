package klassen;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import exceptions.FormatException;
import exceptions.StringIsEmptyException;
import exceptions.WertebereichException;
import exceptions.ZeitenKollisionException;

public class Main {
	public static void main(String[] args) {
		//Appointment erstellen & alle Methoden usw. ausführen.
		try {
			//Attribute
			Appointment termin1 = new Appointment("28/05/2015", "Pamela besuchen", "17:00", "20:30", "Urlaub", "Dresden");
			System.out.println();
			
		} catch (FormatException | WertebereichException | ZeitenKollisionException | StringIsEmptyException | ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
