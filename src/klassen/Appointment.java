package klassen;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment {
	//Attribute
	private StringProperty datum		= new SimpleStringProperty();
	private StringProperty titel		= new SimpleStringProperty();
	private StringProperty startzeit	= new SimpleStringProperty();
	private StringProperty endzeit		= new SimpleStringProperty();
	private StringProperty kategorie	= new SimpleStringProperty();
	private StringProperty notiz		= new SimpleStringProperty();
	private StringProperty appointnemnt = new SimpleStringProperty();

	
	//Konstruktor
	public Appointment (String datum, String titel, String startzeit, String endzeit, String kategorie, String notiz) {
		setDatum(datum);
		setTitel(titel);
		setStartzeit(startzeit);
		setEndzeit(endzeit);
		setKategorie(kategorie);
		setNotiz(notiz);
		appointnemnt.bind(datum.concat(""));
	}
	
	//Standartkonstruktor
	public Appointment () {
		
	}
	
	//Kopierkonstruktor
	
	//Getter - StringProperty
	public StringProperty datum() {
		return datum;
	}
	
	public StringProperty startzeit() {
		return startzeit;
	}
	
	public StringProperty endzeit() {
		return endzeit;
	}
	
	public StringProperty titel() {
		return titel;
	}
	
	public StringProperty notiz() {
		return notiz;
	}
	
	public StringProperty kategorie() {
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
	public void setDatum(String datum) {
		this.datum.set(datum);
	}
	
	public void setStartzeit(String startzeit) {
		this.startzeit.set(startzeit);
	}

	public void setEndzeit(String endzeit) {
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
	public String toString() {
		String appointmentString = "Appointment:\nDatum:\t\t"+ getDatum()+ "\nTitel:\t\t"+ getTitel()+  "\nStartzeit:\t"+ getStartzeit()+ "\nEndzeit:\t"+ getEndzeit()+ "\nKategorie:\t"+ getKategorie()+ "\nNotiz:\t\t"+ getNotiz();
		return appointmentString;
	}
	

	
	//Main
	public static void main(String[] args) {
		//Appointment erstellen & alle Methoden usw. ausführen.
		Appointment test = new Appointment("28.05.15", "Pamela besuchen", "17:00", "20:30", "Urlaub", "Dresden");
		System.out.println(test.toString());
	}
}
