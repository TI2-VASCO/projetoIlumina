package model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;  

public class Doacao {
	protected int ID;
	protected String data;
	protected double valor;
	protected int doadorID;
	
	public Doacao() {
		this(-1, "", 0.0, -1);
	}
	
	public Doacao(int id, String data, double valor, int doadorId) {
		this.ID = id;
     	this.data = data;
     	this.valor = valor;
		this.doadorID = doadorId;
	}
	
	public Doacao(int id, Timestamp data, double valor, int doadorId) {
		this.ID = id;
		this.data = data.toString();
		this.valor = valor;
		this.doadorID = doadorId;
	}
	
	//GETTERS
	public int getID() {
		return this.ID;
	}
	
	public String getData() {
		return this.data;
	}
	
	public double getValor() {
		return this.valor;
	}
	
	public int getDoadorID() {
		return this.doadorID;
	}
	
	public Timestamp getTimestamp() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
		Date date = null;
		try {
			date = formatter.parse(this.data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp ts = new Timestamp(date.getTime());
		
		return ts;
	}
	
	//SETTERS
	public void setID(int id) {
		this.ID = id;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public void setDoadorID(int doadorId) {
		this.doadorID = doadorId;
	}
}
