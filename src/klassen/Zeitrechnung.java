package klassen;

import exceptions.FormatException;
import exceptions.WertebereichException;

public class Zeitrechnung {
	//Konstruktor
	public Zeitrechnung() {
	}
	
	//Methoden
	public int stringTimeToIntSeconds (String zeit) throws FormatException, WertebereichException {
		int stunden;
		int minuten;
		int sekunden;
		
		//String testen, ohne ':' oder zu lang --> Exception!
		if(!(testeFormat(zeit))) {
			throw new FormatException("Uhrzeiten müssen folgendes Format haben: \"HH:MM\"");
		}
		
		if(!testeZeitraum(zeit)) {
			throw new WertebereichException("Zeit liegt nicht im Wertebereich (0 - 59).");
		}
		
		//String zerlegen
		String [] zeitArr;
		zeitArr=zeit.split(":");
		
		//Vervollständige, falls Eingabe lautet ":"
		if(zeitArr.length < 1) {
			try {
				zeitArr[0].isEmpty();
			} catch (Exception e) {
				String [] zeitArr2 = {"0", "0"};
				zeitArr = zeitArr2;
			}
		}
		
		//Vervollständige, falls Eingabe lautet: ":5" oder "1:"
		if(zeitArr.length < 2) {
			try {
				zeitArr[0].isEmpty();
			} catch (Exception e) {
				String [] zeitArr2 = {"0", zeitArr[1]};
				zeitArr = zeitArr2;
			}
			
			try {
				zeitArr[1].isEmpty();
			} catch (Exception e) {
				String [] zeitArr2 = {zeitArr[0], "0"};
				zeitArr = zeitArr2;
			}
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
	
	public boolean testeFormat (String zeit) {
		if(zeit.contains(":") & (zeit.length() < 6)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean testeZeitraum (String zeit) {
		int stunden;
		int minuten;
		
		//String zerlegen
		String [] zeitArr;
		zeitArr=zeit.split(":");
		
		//Vervollständige, falls Eingabe lautet ":"
		if(zeitArr.length < 1) {
			try {
				zeitArr[0].isEmpty();
			} catch (Exception e) {
				String [] zeitArr2 = {"0", "0"};
				zeitArr = zeitArr2;
			}
		}
		
		//Vervollständige, falls Eingabe lautet: ":5" oder "1:"
		if(zeitArr.length < 2) {
			try {
				zeitArr[0].isEmpty();
			} catch (Exception e) {
				String [] zeitArr2 = {"0", zeitArr[1]};
				zeitArr = zeitArr2;
			}
			
			try {
				zeitArr[1].isEmpty();
			} catch (Exception e) {
				String [] zeitArr2 = {zeitArr[0], "0"};
				zeitArr = zeitArr2;
			}
		}

		//Strings zu int casten
		stunden = Integer.parseInt(zeitArr[0]);
		minuten = Integer.parseInt(zeitArr[1]);
		
		if(stunden > 23 | stunden < 0 | minuten > 59 | minuten < 0) {
			return false;
		} else {
			return true;
		}
	}
}
