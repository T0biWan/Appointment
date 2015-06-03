package klassen;

import exceptions.FormatException;
import exceptions.StringIsEmptyException;
import exceptions.WertebereichException;
import exceptions.ZeitenKollisionException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment {
	//Attribute
	private StringProperty	datum			= new SimpleStringProperty();
	private StringProperty	titel			= new SimpleStringProperty();
	private StringProperty	startzeit		= new SimpleStringProperty();
	private StringProperty	endzeit			= new SimpleStringProperty();
	private StringProperty	kategorie		= new SimpleStringProperty();
	private StringProperty	notiz			= new SimpleStringProperty();
	private Zeitrechnung	zeitrechner		= new Zeitrechnung();
	
	//Konstruktor
	// zwei Methoden testeZeitString endzeit und startzeit, ist der String leer? Stimmt das Format?
	
	public Appointment (String datum, String titel, String startzeit, String endzeit, String kategorie, String notiz) throws FormatException, WertebereichException, ZeitenKollisionException, StringIsEmptyException {
		setDatum(datum);
		setTitel(titel);
		setStartzeit(startzeit);
		setEndzeit(endzeit);
		//prüfen ob Start und Endzeit okay sind
		if(!testeZeitFenster(getStartzeit(), getEndzeit())) {
			throw new WertebereichException ("Endzeit liegt vor Startzeit");
		}
		setKategorie(kategorie);
		setNotiz(notiz);
	}
	
	//Standartkonstruktor
	public Appointment () throws FormatException, WertebereichException {
		//Setze Datum zu heutigem Datum.
//		DateFormat gewünschtesDatumFormat = new SimpleDateFormat("dd.MM.yyyy");
//		setDatum(gewünschtesDatumFormat.format(datumHeute));
//		DateFormat gewünschtesZeitFormat = new SimpleDateFormat ("HH:mm");
//		setStartzeit(gewünschtesZeitFormat.format(datumHeute));
//		setStartzeit(gewünschtesZeitFormat.format("12:15"));
//		setStartzeit(gewünschtesZeitFormat.format(datumHeute));
//		setEndzeit(gewünschtesZeitFormat.format(datumHeute));
	}
	
	//Kopierkonstruktor
	// z.B. wenn man drei mal den selben Termin hat, oder durch Regelmaessigkeit
	public Appointment (Appointment termin) throws FormatException, WertebereichException, ZeitenKollisionException, StringIsEmptyException {
		setDatum(termin.getDatum());
		setTitel(termin.getTitel());
		setStartzeit(termin.getStartzeit());
		setEndzeit(termin.getEndzeit());
		setKategorie(termin.getKategorie());
		setNotiz(termin.getNotiz());
	}
	
	//Getter - StringProperty
	public StringProperty datumProperty() {
		return datum;
	}
	
	public StringProperty startzeitProperty() {
		return startzeit;
	}
	
	public StringProperty endzeitProperty() {
		return endzeit;
	}
	
	public StringProperty titelProperty() {
		return titel;
	}
	
	public StringProperty notizProperty() {
		return notiz;
	}
	
	public StringProperty kategorieProperty() {
		return kategorie;
	}
	
	//Getter - String
	public String getDatum() {
		return datum.get();
	}
	
	public String getStartzeit() {
		return startzeit.get();
	}
	
	public String getEndzeit() {
		return endzeit.get();
	}
	
	public String getTitel() {
		return titel.get();
	}
	
	public String getNotiz() {
		return notiz.get();
	}
	
	public String getKategorie() {
		return kategorie.get();
	}
	
	//Setter
	
	// 
	public void setDatum(String datum) throws FormatException, WertebereichException, StringIsEmptyException {
		testeDatumString(datum);
		this.datum.set(datum);
	}
	
	public void setStartzeit(String startzeit) throws FormatException, WertebereichException, ZeitenKollisionException, StringIsEmptyException {
		testeZeitString(startzeit);
		this.startzeit.set(startzeit);
	}

	public void setEndzeit(String endzeit) throws FormatException, WertebereichException, ZeitenKollisionException, StringIsEmptyException {
		testeZeitString(endzeit);
		this.endzeit.set(endzeit);
	}

	public void setTitel(String titel) {
		this.titel.set(titel);
	}

	public void setNotiz(String notiz) {
		this.notiz.set(notiz);
	}

	public void setKategorie(String kategorie) {
		this.kategorie.set(kategorie);
	}

	//Methoden
	// 1:1 zu CD String Methode
	// Gibt Werte aus
	public String toString() {
		String appointmentString = 	"Appointment:\nDatum:\t\t"+ getDatum()+
									"\nTitel:\t\t"+ getTitel()+ 
									"\nStartzeit:\t"+ getStartzeit()+
									"\nEndzeit:\t"+ getEndzeit()+
									"\nKategorie:\t"+ getKategorie()+
									"\nNotiz:\t\t"+ getNotiz();
		return appointmentString;
	}
	// Die Dauer des Termins, muss positiv sein (endzeit ist grösser als startzeit)
	// Rechnet gegebene Zeiten in Sekunden um, zeitrechner wird aufgerufen, die Klasse Zeitrechnung wird aufgerufen
	public boolean testeZeitFenster (String startzeit, String endzeit) throws FormatException, WertebereichException {
		int startzeitInSekunden = zeitrechner.stringTimeToIntSeconds(startzeit);
		int endzeitInSekunden = zeitrechner.stringTimeToIntSeconds(endzeit);
		int ergebnis = endzeitInSekunden - startzeitInSekunden;
		if(ergebnis < 0) {
			return false;
		} else {
			return true;
		}
	}
	
	// maximal 10 Zeichen für TT.MM.YYYY
 	public boolean testeDatumFormat (String datum) {
		if(datum.contains("/") && (datum.length() == 10)) {
			return true;
		} else {
			return false;
		}
	}
	
 	// Tage dürfen nicht mehr als 31 sein. 
	public boolean testeDatumWertebereich (String datum) {
		int tag;
		int monat;
		int jahr;
		
		//String zerlegen
		String [] datumArr;
		datumArr=datum.split("/");
		
		tag = Integer.parseInt(datumArr[0]);
		monat = Integer.parseInt(datumArr[1]);
		jahr = Integer.parseInt(datumArr[2]);
		
		//Hint: jahr < 1000 da 1000 die kleinste vierstellige, nat., Zahl ist.
		// mindestens 1 bis 31 (tage), 1 bis 12 (Monate) und 1000 (Jahre), kleinste Vierstellige Zahl
		if((tag < 1) | (tag > 31) | (monat < 1) | (monat > 12) | (jahr < 1000)) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean testeZeitFormat (String zeit) {
		if(zeit.contains(":") & (zeit.length() < 6)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean testeZeitWertebreich (String zeit) {
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
	
	public void testeZeitString (String zeit) throws FormatException, WertebereichException, StringIsEmptyException {
		if (zeit == null) {
			throw new StringIsEmptyException("Der übergebene Zeitstring ist leer.");
		}
		if(!(testeZeitFormat(zeit))) {
			throw new FormatException("Uhrzeiten müssen folgendes Format haben: \"HH:MM\".");
		}
		
		if(!(testeZeitWertebreich(zeit))) {
			throw new WertebereichException("Zeit liegt nicht im Wertebereich.");
		}
	}
	
	// ist der String leer? Teste DatumFormat, 
	public void testeDatumString (String datum) throws FormatException, WertebereichException, StringIsEmptyException {
		if (datum == null) {
			throw new StringIsEmptyException("Der übergebene Datumstring ist leer.");
		}
		if(!(testeDatumFormat(datum))) {
			throw new FormatException("Datum muss folgendes Format haben: \"DD/MM/YYYY\".");
		}
		
		if(!(testeDatumWertebereich(datum))) {
			throw new WertebereichException("Datum liegt nicht im geforderten Wertebereich.");
		}
	}
	
	// Nimmt alle Strings oder null entgegen. Methode, die einen Termin ändern kann, z.B. Zeiten ändern
	// nur setter werden verwendet, testen durch.
	// Zeitfenster einschieben, welches die event. neue Uhrzeit und event. neue Endzeit überprüft
	
	public void changeAppointment (String datum, String titel, String startzeit, String endzeit, String kategorie, String notiz) throws FormatException, WertebereichException, ZeitenKollisionException, StringIsEmptyException {
		if(!(datum == null)) {
			setDatum(datum);
		}
		
		if(!(titel == null)) {
			setTitel(titel);
		}
		
		if(!(startzeit == null)) {
			setStartzeit(startzeit);
		}
		
		if(!(endzeit == null)) {
			setEndzeit(endzeit);
		}
		
		if(!testeZeitFenster(getStartzeit(), getEndzeit())) {
			throw new WertebereichException ("Endzeit liegt vor Startzeit");
		}
		
		if(!(kategorie == null)) {
			setKategorie(kategorie);
		}
		
		if(!(notiz == null)) {
			setNotiz(notiz);
		}
	}
	
	// Main erstellt ein Appointment
	// Beispielhaft ein Appointment bauen, damit der User sich anzeigen lassen kann, was hier eigentlich passiert.
	
	public static void main(String[] args) {
		try {
			Appointment termin = new Appointment();
			termin.testeDatumWertebereich("28/05/2015");
			
		} catch (FormatException | WertebereichException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
}
