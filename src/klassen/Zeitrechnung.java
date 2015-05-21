package klassen;

import exceptions.FormatExceptions;

public class Zeitrechnung {
	//Konstruktor
	public Zeitrechnung() {
	}
	
	//Methoden
	public int stringTimeToIntSeconds (String zeit) throws FormatExceptions {
		int stunden;
		int minuten;
		int sekunden;
		
		//String testen, ohne ':' --> Exception!
		if(!(zeit.contains(":"))) {
			throw new FormatExceptions("Uhrzeiten müssen folgendes Format haben: \"HH:MM\"");
		}
		
		//String zerlegen
		String [] zeitArr;
		zeitArr=zeit.split(":");
		
		//Strings auf richtige Länge prüfen
		if(zeitArr[0].length() > 2 | zeitArr[1].length() > 2) {
			throw new FormatExceptions("Uhrzeiten müssen folgendes Format haben: \"HH:MM\"");
		}

		//Strings zu int casten
		stunden = Integer.parseInt(zeitArr[0]);
		minuten = Integer.parseInt(zeitArr[1]);
		
		//Zeiten in Sekunden umrechnen und addieren
		sekunden = stundeZuSekunde(stunden) + minuteZuSekunde(minuten);
		
		return sekunden;
	}
	
	public int stundeZuSekunde (int stunde) {
		int sekunde = stunde * 3600;
		return sekunde;
	}
	
	public int minuteZuSekunde (int minute) {
		int sekunde = minute * 60;
		return sekunde;
	}
	
}
