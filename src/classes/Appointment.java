package classes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment {
	
	private StringProperty datum,startUhrzeit, endUhrzeit, terminkategorie, terminbezeichnung, terminbeschreibung, dauer; 
	private Date dt = new Date();
	
	public Appointment(){
		datum = new SimpleStringProperty(dt.toString());
		startUhrzeit = new SimpleStringProperty();
		endUhrzeit = new SimpleStringProperty();
		terminkategorie = new SimpleStringProperty();
		terminbezeichnung = new SimpleStringProperty(); 
		terminbeschreibung = new SimpleStringProperty();
		dauer = new SimpleStringProperty();
	}
	
	public String getDatum() {
		return datum.get();
	}

	public void setDatum(String datum) {
		
		if(datum != null && !datum.isEmpty()){
			
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			
	        try {
	        	dt = df.parse(datum);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
		}
		
        this.datum.set(dt.toString());
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
		this.endUhrzeit.set(endUhrzeit);
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
	
	public String getDauer() {
		return dauer.get();
	}
	
	public StringProperty getDauerProperty(){ return dauer;}
	

	public static void main(String[] args) {
		Appointment ap = new Appointment();
		
		System.out.println(ap.getDatum());
		
		ap.setDatum("12.07.2015");
		
		System.out.println(ap.getDatum());
	
	}

}
