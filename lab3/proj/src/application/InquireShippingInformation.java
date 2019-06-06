package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InquireShippingInformation {
	private final IntegerProperty id;
	private final StringProperty gno;
	private final StringProperty name;
	private final StringProperty place;
	private final StringProperty factory;
	private final IntegerProperty amount;
	
	public InquireShippingInformation(Integer id, String gno, String name, String place, String factory, Integer amount) {
		// TODO Auto-generated constructor stub
		this.id = new SimpleIntegerProperty(id);
		this.gno = new SimpleStringProperty(gno);
		this.name = new SimpleStringProperty(name);
		this.place = new SimpleStringProperty(place);
		this.factory = new SimpleStringProperty(factory);
		this.amount = new SimpleIntegerProperty(amount);
	}
	
	public Integer getId() {
		return id.get();
	}
	
	public void setId(Integer id) {
		this.id.set(id);
	}
	
	public Integer getAmount() {
		return amount.get();
	}
	
	public void setAmount(Integer amount) {
		this.amount.set(amount);
	}
	
	public String getFactory() {
		return factory.get();
	}
	
	public void setFactory(String factory) {
		this.factory.set(factory);
	}
	
	public String getGno() {
		return gno.get();
	}
	
	public void setGno(String gno) {
		this.gno.set(gno);
	}
	
	public String getName() {
		return name.get();
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public String getPlace() {
		return place.get();
	}
	
	public void setPlace(String place) {
		this.place.set(place);
	}
}
