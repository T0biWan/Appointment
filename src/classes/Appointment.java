package classes;

import java.text.*;
import java.util.Date;

import exceptions.WrongTimeException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Appointment - Ist ein ADT das seine Attribute auch als Properties enthält.
 * Dabei kann der Benutzer zwischen den Rückgabetypen String und Property
 * wählen. 
 * 
 * */

public class Appointment {	
	
	/**
	 * Wir instantiieren unser Startdatum und unser Enddatum (https://docs.oracle.com/javase/8/docs/api/java/util/Date.html) aus denen wir später unser Datum und unsere Zeiten berechnen.
	 * Java Date gibt uns immer das Datum in Form von Mon May 04 09:51:52 CDT 2009 aus was wir dann mit Hilfe der Pattern "zuschneiden" können
	 * Des weiteren definieren wir unsere Datums- und Zeitformate, mit denen wir später Eingaben parse (überprüfen) und Ausgeben.
	 * Hierbei werden sogenannte Pattern benutzt, die symbolisch für unsere Formate stehen. Quasi wie wenn man Platzhalter 
	 * schreiben würde z.B. xx.xx.xxxx. Welche es gibt erfährt man unter http://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
	 */
	
	private Date startDate = new Date();
	private Date endDate = new Date();
	// das Pattern "dd.MM.yyyy" steht für d = Day in month , M = Month in year , y = Year 
	private DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	// das Pattern "HH:mm" steht für H = Hour in day (0-23) , m = Minute in hour
	private DateFormat timeFormat = new SimpleDateFormat("HH:mm");
	
	/**
	 * Hier werden alle unsere Properties initialisiert. Damit auch bei einem leeren Appointment alles glatt geht 
	 * kreiere ich per Default Datum und Zeit von Heute in unserem gewünschten Format.
	 */
	private StringProperty datum = new SimpleStringProperty(dateFormat.format(startDate));
	private StringProperty startUhrzeit = new SimpleStringProperty(timeFormat.format(startDate));
	private StringProperty endUhrzeit = new SimpleStringProperty(timeFormat.format(endDate));
	private StringProperty terminkategorie = new SimpleStringProperty();
	private StringProperty terminbezeichnung = new SimpleStringProperty(); 
	private StringProperty terminbeschreibung = new SimpleStringProperty();
	private StringProperty dauer = new SimpleStringProperty(zeitDauer());
	
	// Standardkonstruktor
	public Appointment(){

	}
	
	// Kopierkonstruktor
	public Appointment(Appointment appointment) throws WrongTimeException{
		this.setDatum(appointment.getDatum());
		this.setStartUhrzeit(appointment.getStartUhrzeit());
		this.setEndUhrzeit(appointment.getEndUhrzeit());
		this.setTerminkategorie(appointment.getTerminkategorie());
		this.setTerminbezeichnung(appointment.getTerminbezeichnung());
		this.setTerminbeschreibung(appointment.getTerminbeschreibung());
	}
	
	// Sofortkonstruktor ;)
	public Appointment(String datum, String startUhrzeit, String endUhrzeit, String terminkategorie, String terminbezeichnung, String terminbeschreibung) throws WrongTimeException {
		this.setDatum(datum);
		this.setStartUhrzeit(startUhrzeit);
		this.setEndUhrzeit(endUhrzeit);
		this.setTerminkategorie(terminkategorie);
		this.setTerminbezeichnung(terminbezeichnung);
		this.setTerminbeschreibung(terminbeschreibung);
	}
	
	// Getter und Setter (normal und für Property)
	
	public String getDatum() {
		return datum.get();
	}

	public void setDatum(String datum) {

		// Kontrolle ob es ein Datum gibt
		if(datum != null && !datum.isEmpty()){
	        try {
	        	// Wir schauen ob das eingegebene Datum das richtige Format hat
	        	startDate = dateFormat.parse(datum);
	        } catch (ParseException e) {
	        	// uuuppssss hat es nicht
	            e.printStackTrace();
	        }
		}
		// wir übergeben unser Start Datum an unsere Property - wenn kein Datum übergeben wurde dann nehmen wir das Default Datum (Heute)
        this.datum.set(dateFormat.format(startDate));
	}
	
	public StringProperty getDatumProperty(){ return datum;}

	public String getStartUhrzeit() {
		return startUhrzeit.get();
	}

	public void setStartUhrzeit(String startUhrzeit) throws WrongTimeException {
		// Kontrolle ob es eine Uhrzeit gibt
		if(startUhrzeit != null && !startUhrzeit.isEmpty()){
	        try {
	        	// Wir schauen ob die eingegebene Zeit das richtige Format hat
	        	Date startDateNew = timeFormat.parse(startUhrzeit);
	        	// wir kontrollieren, ob die Startzeit wirklich vor der Endzeit liegt 
	    		if(!startDateNew.before(endDate))
	    			throw new WrongTimeException("Der Start (" + startUhrzeit + ") des Termines muss vor dem Ende (" + timeFormat.format(endDate) + ") liegen.");
	    		// Wir überschreiben unser Datum mit dem Neuen
	    		startDate = startDateNew;
	        } catch (ParseException e) {
	        	// uuuppssss hat es nicht
	            e.printStackTrace();
	        }
		}
				
		// wir übergeben unsere Start Zeit an unsere Property - dies geschieht vollautomatisch über unser Zeitformat (timeFormat) und dem startDate. 
		// Wir extrahieren also die Zeit aus unserem Datumsobjekt. 
		// wenn keine Zeit übergeben wurde dann nehmen wir die Default Zeit (Jetzt)
		this.startUhrzeit.set(timeFormat.format(startDate));
	}
	
	public StringProperty getStartUhrzeitProperty(){ return startUhrzeit;}

	public String getEndUhrzeit() {
		return endUhrzeit.get();
	}
	
	public void setEndUhrzeit(String endUhrzeit) throws WrongTimeException {

		// WICHTIG: wir arbeiten hier mit einem anderem Datum (endDate), damit wir später die Zeitdifferenz richtig berechnen können
		
		// Kontrolle ob es eine Uhrzeit gibt
		if(endUhrzeit != null && !endUhrzeit.isEmpty()){
	        try {
	        	// Wir schauen ob die eingegebene Zeit das richtige Format hat
	        	Date endDateNew = timeFormat.parse(endUhrzeit);
	        	
	        	// wir kontrollieren, ob die Endzeit wirklich nach der Startzeit liegt
	    		if(!endDateNew.after(startDate))
	    			throw new WrongTimeException("Das Ende (" + endUhrzeit + ") des Termines darf nicht vor dem Start (" + timeFormat.format(startDate) + ") liegen.");
	    		
	    		// Wir überschreiben unser Datum mit dem Neuen
	    		endDate = endDateNew;
	        } catch (ParseException e) {
	        	// uuuppssss hat es nicht
	            e.printStackTrace();
	        }
		}
		
		// wir übergeben unsere End Zeit an unsere Property
		// Wir extrahieren also die Zeit aus unserem Datumsobjekt. 
		// wenn keine Zeit übergeben wurde dann nehmen wir die Default Zeit (Jetzt)
		this.endUhrzeit.set(timeFormat.format(endDate));
	}
	
	public StringProperty getEndUhrzeitProperty(){ return endUhrzeit;}

	public String getTerminkategorie() {
		return terminkategorie.get();
	}

	public void setTerminkategorie(String terminkategorie) {
		this.terminkategorie.set(terminkategorie);
	}
	
	public StringProperty getTerminkategorieProperty(){ return terminkategorie;}

	public String getTerminbezeichnung() {
		return terminbezeichnung.get();
	}

	public void setTerminbezeichnung(String terminbezeichnung) {
		this.terminbezeichnung.set(terminbezeichnung);
	}
	
	public StringProperty getTerminbezeichnungProperty(){ return terminbezeichnung;}

	public String getTerminbeschreibung() {
		return terminbeschreibung.get();
	}

	public void setTerminbeschreibung(String terminbeschreibung) {
		this.terminbeschreibung.set(terminbeschreibung);
	}
	
	public StringProperty getTerminbeschreibungProperty(){ return terminbeschreibung;}
	
	public String getDauer() {
		// Berechnung der Dauer
		return zeitDauer();
	}
	
	public StringProperty getDauerProperty(){ dauer.set(zeitDauer());return dauer;}
	
	private String zeitDauer() {
		/**
		 * Hier kann man die Stärke des Dateobjektes sehen.
		 * getTimes() gibt uns die Zeit zwischen dem January 1, 1970, 00:00:00 GMT und dem gespeicherten Datum in Millisekunden an.
		 * Auf diese Zeit haben sich die JavaProgrammiere einst geeinigt.... Quasi unser 0 A.D.
		 */
		
		// errechnet die Differenz zwischen unseren zwei Daten
		long diff = endDate.getTime() - startDate.getTime();
		// gibt die Differenz in Minuten an
		long diffMinutes = diff / (60 * 1000) % 60;
		// gibt die Differenz in Stunden an
		long diffHours = diff / (60 * 60 * 1000) % 24;
		// From long in String um
		return String.valueOf(diffHours + ":" + diffMinutes);
	}
	
	public String toString(){
		String str = 
				"\nDatum: " + this.getDatum() + 
				"\nStart Uhrzeit: " + this.getStartUhrzeit() +
				"\nEnd Uhrzeit: " + this.getEndUhrzeit() +
				"\nDauer: " + this.getDauer() +
				"\nTerminkategorie: " + this.getTerminkategorie() +
				"\nTerminbezeichnung: " + this.getTerminbezeichnung() +
				"\nTerminbeschreibung: " + this.getTerminbeschreibung();
		return str;
	}

	public static void main(String[] args) {
		Appointment ap1 = new Appointment();
		
		/*
		 * Generelle Initialisierung
		 * */
		
		System.out.println();
		System.out.println("Generelle Initialisierung");
		System.out.println();
		
		System.out.println("Datum: " + ap1.getDatum());
		System.out.println("Start Uhrzeit: " + ap1.getStartUhrzeit());
		System.out.println("End Uhrzeit: " + ap1.getEndUhrzeit());
		System.out.println("Dauer: " + ap1.getDauer());
		System.out.println("Terminkategorie: " + ap1.getTerminkategorie());
		System.out.println("Terminbezeichnung: " + ap1.getTerminbezeichnung());
		System.out.println("Terminbeschreibung: " + ap1.getTerminbeschreibung());
		
		/*
		 * Spezifische Initialisierung
		 * */
		
		System.out.println();
		System.out.println("Spezifische Initialisierung");
		System.out.println();
		
		try {
			
			Appointment ap2 = new Appointment("12.12.2015", "10:00", "33:35", "Uni", "Mathe Klausur", "Wir schreiben eine dicke Klausur");
			
			System.out.println("Datum: " + ap2.getDatum());
			System.out.println("Start Uhrzeit: " + ap2.getStartUhrzeit());
			System.out.println("End Uhrzeit: " + ap2.getEndUhrzeit());
			System.out.println("Dauer: " + ap2.getDauer());
			System.out.println("Terminkategorie: " + ap2.getTerminkategorie());
			System.out.println("Terminbezeichnung: " + ap2.getTerminbezeichnung());
			System.out.println("Terminbeschreibung: " + ap2.getTerminbeschreibung());
		} catch (WrongTimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
