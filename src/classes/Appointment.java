package classes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment {
	
	private StringProperty datum,startUhrzeit, endUhrzeit, terminkategorie, terminbezeichnung, terminbeschreibung;
	private SimpleIntegerProperty dauer; 
	
	public Appointment(){
		datum = new SimpleStringProperty();
		startUhrzeit = new SimpleStringProperty();
		endUhrzeit = new SimpleStringProperty();
		terminkategorie = new SimpleStringProperty();
		terminbezeichnung = new SimpleStringProperty(); 
		terminbeschreibung = new SimpleStringProperty();
		dauer = new SimpleIntegerProperty();
		
		this.strToMin(endUhrzeit.get());
		this.strToMin(startUhrzeit.get());
		
		//dauer.bind( );
	}
	
	public String getDatum() {
		return datum.get();
	}

	public void setDatum(String datum) {
        this.datum.set(datum);
	}
	
	public StringProperty getDatumProperty(){ return datum;}

	public String getStartUhrzeit() {
		return startUhrzeit.get();
	}

	public void setStartUhrzeit(String startUhrzeit) {
		this.startUhrzeit.set(startUhrzeit);
	}
	
	public StringProperty getStartUhrzeitProperty(){ return startUhrzeit;}

	public String getEndUhrzeit() {
		return endUhrzeit.get();
	}

	public void setEndUhrzeit(String endUhrzeit) {
		
		if(strToMin(endUhrzeit) - strToMin(startUhrzeit.get()) > 0 ){
			this.endUhrzeit.set(endUhrzeit);
		}else{
			System.out.println("Enduhrzeit muss größer als Startuhrzeit sein");
		}
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

	public void setTerminbezeichnung(
			String terminbezeichnung) {
		this.terminbezeichnung.set(terminbezeichnung);
	}
	
	public StringProperty getTerminbezeichnungProperty(){ return terminbezeichnung;}

	public String getTerminbeschreibung() {
		return terminbeschreibung.get();
	}

	public void setTerminbeschreibung(
			String terminbeschreibung) {
		this.terminbeschreibung.set(terminbeschreibung);
	}
	
	public StringProperty getTerminbeschreibungProperty(){ return terminbeschreibung;}
	
	public int getDauer() {
		return dauer.get();
	}
	
	public IntegerProperty getDauerProperty(){ return dauer;}
	
	private IntegerProperty strToMin(String str){
		
		String[] time = str.split(":");
		
		IntegerProperty i = new SimpleIntegerProperty(Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]));
		
		return i;
	}

	public static void main(String[] args) {
		Appointment ap = new Appointment();

		ap.setStartUhrzeit("10:30");
		ap.setEndUhrzeit("11:30");

	}

}
