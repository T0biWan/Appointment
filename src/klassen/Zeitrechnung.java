package klassen;

public class Zeitrechnung {
	//Konstruktor
	public Zeitrechnung() {
	}
	
	//Methoden
	public int stringTimeToIntSeconds (String zeit) {
		int stunden;
		int minuten;
		int sekunden;
		
		//Gegebenen String zerlegen
		String [] zeitArr;
		zeitArr=zeit.split(":");

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
