package klassen;

import exceptions.FormatExceptions;
import exceptions.TimeException;

public class Main {

	public static void main(String[] args) {
		//Appointment erstellen & alle Methoden usw. ausführen.
		try {
			Appointment test = new Appointment("28.05.15", "Pamela besuchen", "17:00", "20:30", "Urlaub", "Dresden");
			System.out.println(test);
			test.changeTime("00:00", "21:00");
			System.out.println(test);
		} catch (FormatExceptions | TimeException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
